node("slave1") {
    def workspace = pwd()

    def git_branch = 'master'
//    def git_repository = 'git@git.nju.edu.cn:191820133/backend-volatile.git' //Gitlab
    def git_repository = 'git@github.com:VolatileReborn/Backend-VolatileReborn.git' //Github

    def vm_ip = '124.222.135.47'
    def vm_port = '22'
    def vm_user = 'lyk'

    def vm_project_place = "/usr/local/src"
    def vm_target_place = "/usr/local/src/target/"


    def __PROJECT_NAME = 'volatile_reborn'

    def __PROJECT_TYPE = 'backend'

    def __DOCKERHUB_ACCOUNT = 'lyklove'
    def __IMAGE_TAG = 'latest-linux'


//    def PUBLIC_PORT = '81'
//    def CONTAINER_PORT = '80' // 80 for VUE

    def ORIGINAL_COLLECT_IMAGE_NAME = __PROJECT_TYPE + '_' + __PROJECT_NAME //backend_volatile_reborn

    def IMAGE_NAME_WITH_INITIAL_TAG = ORIGINAL_COLLECT_IMAGE_NAME + ':' + __IMAGE_TAG //backend_volatile_reborn:latest-linux
    def IMAGE_FULL_NAME = __DOCKERHUB_ACCOUNT + '/' + IMAGE_NAME_WITH_INITIAL_TAG // lyklove/backend_volatile_reborn:latest-linux

    def EUREKA_IMAGE_FULL_NAME = "lyklove/backend_eureka_volatile_reborn:latest-linux"


    def CONTAINER_NAME = ORIGINAL_COLLECT_IMAGE_NAME //backend_volatile_reborn
    def EUREKA_CONTAINER_NAME = "backend_eureka_volatile_reborn"

    def SERVICE_NAME = CONTAINER_NAME + '_svc' //backend_volatile_reborn_svc


    stage('clone from github into slave\'s workspace. Using branch: ' + "master") {
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

    //需要服务器安装maven
    stage('build jar on slave machine, inorder to generate jacoco file ') {

        sh 'mvn --version'

//        sh './build.sh'
        sh "mvn  clean package -X org.jacoco:jacoco-maven-plugin:report  -Dmaven.test.failure.ignore=true"
//        echo "build finish on ${vm_ip}"

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


    stage("build docker image, tag it"){

        def EUREKA_SERVICE_DOCKERFILE_PATH = './eureka-server/Dockerfile'
        sh "docker build -t ${EUREKA_IMAGE_FULL_NAME} -f ${EUREKA_SERVICE_DOCKERFILE_PATH} ."
        echo "Eureka build finished"


        def COLLECT_SERVICE_DOCKERFILE_PATH = './collect-service/Dockerfile'
        sh "docker build -t ${IMAGE_FULL_NAME} -f ${COLLECT_SERVICE_DOCKERFILE_PATH} ."
        echo "Collect build finished"


    }

    stage("login to dockerhub"){
        withCredentials([usernamePassword(credentialsId: 'DOCKERHUB_KEY', passwordVariable: 'password', usernameVariable: 'username')]) {
            sh 'docker login -u $username -p $password'
        }
    }

    stage("push to dockerhub"){
        echo "begin push to dockerhub"
        sh "docker image push ${IMAGE_FULL_NAME}"
        sh "docker image push ${EUREKA_IMAGE_FULL_NAME}"

    }


    stage("run container") {
        def IMAGE_TO_RUN = IMAGE_FULL_NAME
        def EUREKA_IMAGE_TO_RUN = EUREKA_IMAGE_FULL_NAME
        sh "docker image ls"

        def COLLECT_CONTAINER_PORT = 8000
        def COLLECT_HOST_PORT = 8000

        def EUREKA_CONTAINER_PORT = 8001
        def EUREKA_HOST_PORT = 8001


        sh "docker container run -p ${EUREKA_HOST_PORT}:${COLLECT_CONTAINER_PORT} --name ${EUREKA_CONTAINER_NAME}   -d ${EUREKA_IMAGE_TO_RUN}"
        sh "docker container run -p ${COLLECT_HOST_PORT}:${EUREKA_CONTAINER_PORT} --name ${CONTAINER_NAME}   -d ${IMAGE_TO_RUN}"

    }


//    stage("clean previous image and container"){
//        sh "docker container rm -f ${CONTAINER_NAME}"
//        sh "docker image rm ${IMAGE_NAME_WITH_TAG}"
//        sh "docker image rm ${IMAGE_TO_RUN}"
//    }
//    stage( "pull image" ){
//        sh "docker pull  lyklove/${IMAGE_NAME_WITH_TAG}"
//    }

//    stage("signal gitlab: deployed"){
//        updateGitlabCommitStatus name: 'deployed', state: 'success'
//    }


}
