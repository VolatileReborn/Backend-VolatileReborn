#!/bin/sh


sh ./build.sh

echo "Build images"
docker build -t lyklove/backend_eureka_volatile_reborn:latest-mac  -f ./eureka-server/Dockerfile .
docker build -t lyklove/backend_zuul_volatile_reborn:latest-mac  -f ./collect-gateway/Dockerfile .
docker build -t lyklove/backend_volatile_reborn:latest-mac  -f ./collect-service/Dockerfile .

echo "Remove previous containers"
docker container rm -f backend_eureka_volatile_reborn
docker container rm -f backend_zuul_volatile_reborn
docker container rm -f backend_volatile_reborn


echo "Run containers"
docker container run -p 8001:8001 --name backend_eureka_volatile_reborn \
-d  lyklove/backend_eureka_volatile_reborn:latest-mac

docker container run -p 9999:9999 --name backend_zuul_volatile_reborn \
--net=host \
-d  lyklove/backend_zuul_volatile_reborn:latest-mac


docker container run --name backend_volatile_reborn \
--net=host \
-d  lyklove/backend_volatile_reborn:latest-mac





