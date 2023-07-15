#!/bin/bash

REPOSITORY=/home/ubuntu/app/
PROJECT_NAME=bfc-appmgmt

cd $REPOSITORY/$PROJECT_NAME/

echo "> git pull"

git pull

echo "> gradlew clean build start"

./gradlew clean build

echo "> directory move to repository"

cd $REPOSITORY

echo "> build file copy"

cp $REPOSITORY/$PROJECT_NAME/build/libs/*.jar $REPOSITORY/

echo "> check running application pid"

CURRENT_PID=$(pgrep -f ${PROJECT_NAME}.*jar)

echo "> running application pid is $CURRENT_PID"

if [ -z "$CURRENT_PID"]; then
  echo "> pass the kill pid"
else
  echo "> kill -15 $CURRENT_PID"
  kill -15 $CURRENT_PID
  sleep 5
fi

echo "> new application deploy"

JAR_NAME=$(ls -tr $REPOSITORY/ | grep jar | tail -n 1)

echo "> jar name is $JAR_NAME"

nohup java -jar -Dspring.profiles.active=prd $REPOSITORY/$JAR_NAME 2>&1 &