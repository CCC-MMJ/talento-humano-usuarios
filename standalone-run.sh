#!/bin/bash

set -a
source .env
set +a

mvn clean package -DskipTests=true
java -jar auth/target/auth-0.0.1-SNAPSHOT.jar
