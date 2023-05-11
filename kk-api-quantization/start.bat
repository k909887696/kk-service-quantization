
start javaw -Xms256m -Xmx1G -Xmn256m -jar -Dfile.encoding=utf-8 %1 --spring.profiles.active=%2 --spring.cloud.nacos.config.server-addr=%3 &