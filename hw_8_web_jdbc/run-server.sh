#!/bin/sh

cd ./jdbc_server
mvn clean install
java -jar .\target\jdbc_server-0.0.1-SNAPSHOT.jar
