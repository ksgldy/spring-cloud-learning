#!/usr/bin/env bash
mvn clean package -DskipTests=true
java -jar target/idc-provider2.jar --server.port=20021

