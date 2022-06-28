FROM anapsix/alpine-java
CMD echo "Hello World"
COPY gs-maven-0.1.0.jar ./target
RUN java -jar target/gs-maven-0.1.0.jar
