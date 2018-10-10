sudo docker build -t image/database .
sudo docker run -p 5434:5432 -d --name database image/database
