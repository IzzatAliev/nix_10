#!/bin/sh

cd ../server
mvn clean install
java -jar .\target\server-0.0.1-SNAPSHOT.jar
