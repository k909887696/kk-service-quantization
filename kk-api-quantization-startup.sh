#!/bin/bash
export JAVA_HOME=/application/java/jdk1.8.0_111
export JRE_HOME=/application/java/jdk1.8.0_111/jre
export PATH=$JAVA_HOME/bin/:$PATH
nohup java -Xms512M -Xmx1G -jar /application/kk-api/kk-api-quantization/kk-api-quantization-1.0-SNAPSHOT.jar   > /dev/null 2>&1 &
