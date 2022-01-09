#!/bin/sh

cd ../jpa_server
mvn clean install
java -jar .\target\jpa_server-0.0.1-SNAPSHOT.jar
