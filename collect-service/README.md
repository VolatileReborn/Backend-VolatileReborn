# Visit Online

* Eureka: http://124.222.135.47:8001/
* Collect: http://124.222.135.47:9000/
   * Collect是个Spring项目， 没有网页， 只能通过Apifox等来进行测试
# Run Locally
1. Build:

   ```sh
   ./build.sh
   
   docker build \
    -t lyklove/backend_volatile_reborn:latest-linux \
    -f ./collect-service/Dockerfile \
    .
   
   docker build \
   -t lyklove/backend_eureka_volatile_reborn:latest-linux \
   -f ./eureka-server/Dockerfile \
   . 
   ```

2. Run:

   ```sh
   # 如果要使用容器， 则不要以指定端口的方式启动collect， 会出问题。应该直接从jar移动。
   # 当然， 直接用--net=host启动，就没问题了
   
   docker container run \
   --net=host  \
   --name backend_eureka_volatile_reborn \
   -d lyklove/backend_eureka_volatile_reborn:latest-linux
   
 
   docker container run --name backend_volatile_reborn \
   --net=host \
   -d  lyklove/backend_volatile_reborn:latest-linux
   
   
   ```

