#syntax=docker/dockerfile
FROM scratch

ADD  / DockerFile/helloworld_ajay.java
CMD ["helloworld_ajay.class"]
