#FROM frolvlad/alpine-oraclejdk8:slim

#for rpi https://github.com/chrisanthropic/docker-alpine-rpi-java8
FROM ctarwater/armhf-alpine-rpi-java8
VOLUME /tmp
ADD electric-usage-monitor-0.0.1.jar app.jar
EXPOSE 8080
#RUN sh -c 'touch /app.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]