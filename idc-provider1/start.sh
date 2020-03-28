#!/usr/bin/env bash
mvn clean package -DskipTests=true
java -jar target/idc-provider1.jar --server.port=2001

