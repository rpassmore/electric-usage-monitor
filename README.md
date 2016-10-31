electric-usage-monitor


Build
mvn clean package 

Run:
mvn spring-boot:run

Build and push built image to docker:
mvn package docker:build -DpushImage

Build the docker image
mvn package docker:build

ssh to raspberypi docker hist
ssh pirate@black-pearl

pull latest from docker hub to raspberry pi:
docker pull rpassmore/electric-usage-monitor:latest

run in docker:
docker run -d -p 8080:8080 rpassmore/electric-usage-monitor

Run in docker and dump to std out:
docker run -p 8080:8080 -a stdin -a stdout -i -t rpassmore/electric-usage-monitor /bin/bash

list running containers in docker:
docker ps


