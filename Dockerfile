# 版本信息
#java：latest 为centos官方java运行环境镜像
FROM java:latest
MAINTAINER Nakpa

COPY /var/lib/jenkins/workspace/ErogeLib-Springboot/target/erogelib-0.0.1-SNAPSHOT.jar /var/lib/jenkins/workspace/jar

#复制文件到容器中的/var/lib/jenkins/workspace/jar
ADD  erogelib-0.0.1-SNAPSHOT.jar /var/lib/jenkins/workspace/jar

#重命名jar名
RUN mv /var/lib/jenkins/workspace/jar/erogelib-0.0.1-SNAPSHOT.jar  /var/lib/jenkins/workspace/jar/erogelib.jar

#开启内部服务端口项目端口
EXPOSE 1919
#默认的容器启动执行命令
CMD ["java","-jar","/var/lib/jenkins/workspace/jar/erogelib.jar"]