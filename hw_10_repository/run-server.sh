#!/bin/sh

cd ../repo_server
mvn clean install
java -jar .\target\repo_server-0.0.1-SNAPSHOT.jar
