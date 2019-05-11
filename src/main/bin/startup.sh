#!/bin/bash

source /etc/profile

umask 077

WORK_HOME=$(cd "$(dirname "$0")"; pwd)/
echo "WORK_HOME: ${WORK_HOME}"

MAIN_CLASS=com.johnsonmoon.sql2excel.Sql2excelApplication
CLASSPATH="${WORK_HOME}/lib/*:$CLASS_PATH"
JAVA_OPTS="-Dwork.home=${WORK_HOME} -Xmx512m -Xms256m -Xss256K -XX:MaxMetaspaceSize=256m -XX:+UseParNewGC -XX:+UseConcMarkSweepGC -XX:+CMSClassUnloadingEnabled -XX:+ExplicitGCInvokesConcurrent"

java $JAVA_OPTS -cp $CLASSPATH $MAIN_CLASS >/dev/null 2>&1 &