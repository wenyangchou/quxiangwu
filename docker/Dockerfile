FROM wenyangchou/jdk:v1
MAINTAINER zhouwenyang
ADD quxiangwu.jar  /root/app.jar
ADD profile /root/profile/
ADD imagePath /root/imagePath/
ADD quxiangwu.jks /root/quxiangwu.jks
EXPOSE 443
ENTRYPOINT ["java","-jar","/root/app.jar"]