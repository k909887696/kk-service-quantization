#!/bin/bash
export JAVA_HOME=/application/java/jdk1.8.0_111
export JRE_HOME=/application/java/jdk1.8.0_111/jre
export PATH=$JAVA_HOME/bin/:$PATH
nohup java -Xms512M -Xmx2G -jar /application/kk-executor/kk-executor-quantization/kk-executor-quantization-1.0-SNAPSHOT.jar  > /dev/null 2>&1 &
