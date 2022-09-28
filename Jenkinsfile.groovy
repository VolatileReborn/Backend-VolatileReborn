node("slave1") {
    def workspace = pwd()

    def git_branch = 'master'
    def git_repository = 'git@git.nju.edu.cn:191820133/backend-volatile.git'
    def vm_ip = '124.222.135.47'
    def vm_port = '22'
    def vm_user = 'lyk'

    def vm_project_place = "/usr/local/src"
    def vm_target_place = "/usr/local/src/target/"


    def IMAGE_NAME = 'volatile_backend'
    def IMAGE_NAME_WITH_TAG = 'volatile_backend:latest'
    def IMAGE_TO_RUN = 'lyklove/volatile_backend:latest'
    def CONTAINER_NAME = 'volatile_backend'

    stage('clone from gitlab into slave\'s workspace') {
        echo "workspace: ${workspace}"
        git branch: "${git_branch}", url: "${git_repository}"
    }


    stage('cd to build context') {
        echo "the context now is:"
        sh "ls -al"
        sh "cd ${workspace}"
        echo "cd to build context, now the context is:"
        sh "ls -al"

    }
    stage('build jar on slave machine, jacoco file generated') {

        sh 'mvn --version'
        sh "mvn  clean package jacoco:report -Dmaven.test.failure.ignore=true"
        echo "build finish on ${vm_ip}"

    }

    stage( 'testing, using jacoco' ) {
        jacoco (
                execPattern: '**/target/jacoco.exec',
                classPattern: '**/classes',
                sourcePattern: '**/src/main/java',
                exclusionPattern: '**/src/test*',
//                inclusionPattern: '**/com/hel/auto/service/*.class,**/com/hel/auto/controller/*.class',
        )
    }


    stage("build docker image"){
        sh "docker build -t ${IMAGE_NAME} ."
    }

    stage("login to dockerhub"){
        withCredentials([usernamePassword(credentialsId: 'DOCKERHUB_KEY', passwordVariable: 'password', usernameVariable: 'username')]) {
            sh 'docker login -u $username -p $password'
        }
    }

    stage("push to dockerhub"){
        echo "begin push to dockerhub"
        sh "docker image tag ${IMAGE_NAME_WITH_TAG} lyklove/${IMAGE_NAME_WITH_TAG}"
        sh "docker image push lyklove/${IMAGE_NAME_WITH_TAG}"
    }
    stage("clean previous image and container"){
        sh "docker container rm -f ${CONTAINER_NAME}"
        sh "docker image rm ${IMAGE_NAME_WITH_TAG}"
        sh "docker image rm ${IMAGE_TO_RUN}"
    }
    stage( "pull image" ){
        sh "docker pull  lyklove/${IMAGE_NAME_WITH_TAG}"
    }
    stage("run container") {
        sh "docker image ls"
        sh "docker container run --name ${CONTAINER_NAME} --net=host  -d ${IMAGE_TO_RUN}"
    }
    stage("signal gitlab: deployed"){
        updateGitlabCommitStatus name: 'deployed', state: 'success'
    }


}
