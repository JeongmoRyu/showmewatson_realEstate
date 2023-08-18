cd docker_minikube_kubectl_install/S09P12A803

git pull origin master

sudo docker stop auth
sudo docker stop notice-producer
sudo docker stop view-count

sudo docker rm notice-consumer
sudo docker rm notice-producer
sudo docker rm view-count


sudo docker rmi -f auth
sudo docker rmi -f business
sudo docker build -t view-count .
cd docker_minikube_kubectl_install/S09P12A803

git pull origin master

sudo docker stop auth
sudo docker stop notice-producer
sudo docker stop view-count

sudo docker rm notice-consumer
sudo docker rm notice-producer
sudo docker rm view-count


sudo docker rmi -f auth
sudo docker rmi -f business
sudo docker build -t view-count .


# Business
docker run -p:8081:8081 -d  --name business --network watson_net -e DB_PASSWORD={PASSWORD} -e DB_URL=jdbc:mysql://I9A803.p.ssafy.io:3306 -e DB_USER=watson -e S3_ACCESS_KEY={S3_ACCESS_KEY} -e S3_SECRET_KEY={S3_SECRET_KEY} -e MONGODB_HOST=I9A803.p.ssafy.io -e MONGODB_PORT=27017 -e MONGODB_PW={PASSWORD} -e MONGODB_USER=watson business

# notice
sudo docker run -p 8082:8082 -d --name notice-producer --network watson_net -e DB_PASSWORD={PASSWORD} -e DB_URL=jdbc:mysql://I9A803.p.ssafy.io:3306 -e DB_USER=watson -e MONGODB_HOST=I9A803.p.ssafy.io -e MONGODB_PORT=27017 -e MONGODB_PW={PASSWORD} -e MONGODB_USER=watson notice-producer

sudo docker run -p 8083:8083 -d --name notice-consumer --network watson_net -e FCM_SCOPE=https://www.googleapis.com/auth/cloud-platform -e FCM_PATH=/firebase/firebase-adminsdk.json notice-consumer

# view-count
sudo docker run -p 8084:8084 -d --name view-count --network watson_net -e DB_URL=jdbc:mysql://I9A803.p.ssafy.io:3306 -e DB_USER=watson -e DB_PASSWORD={PASSWORD} -e REDIS-PASSWORD={PASSWORD} view-count
