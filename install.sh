cd ./shared
mvn clean package
cd ..
cd ./postgres-app
mvn clean package
cd ..
cd ./mongodb-app
mvn clean package
cd ..
cd ./tx-coordinator
mvn clean package
cd ..
cd ./manager
mvn clean package
cd ..
cd ./alann-app
mvn clean package
cd ..
cd ./natan-app
mvn clean package
cd ..
sudo docker-compose up