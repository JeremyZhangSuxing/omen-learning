FROM maven:3-jdk-8 as builder
ENV MODULE="omen-learning"
ARG SETTINGS_XML
RUN mkdir -p ~/.m2 && echo -n $SETTINGS_XML | base64 -d > ~/.m2/settings.xml
ADD ./ /app
WORKDIR /app
RUN mvn package -Dmaven.test.skip=true --projects $MODULE

#FROM openjdk:8-jre-alpine
#ENV LANG zh_CN.UTF-8
#ENV MODULE="omen-learning-sample"
#ENV jarName="omen-learning-sample.jar"
#ENV TZ=Asia/Shanghai
#RUN ln -snf /usr/share/zoneinfo/TZ /etc/localtime && echo TZ /etc/localtime && echo TZ > /etc/timezone
#COPY --from=builder /app/$MODULE/target/$jarName /
#WORKDIR /
#ENTRYPOINT java $JAVA_OPTS -jar $jarName

FROM openjdk:8-jdk-alpine
COPY --from=hengyunabc/arthas:latest /opt/arthas /opt/arthas
ENV MODULE="mulan-order-core"
ENV JAR="mulan-order-core.jar"
RUN mkdir /app
COPY --from=builder /app/$MODULE/target/$JAR /app
#ADD $JAR /app
ENV TZ=Asia/Shanghai
WORKDIR /app
RUN apk add --no-cache tini
ENTRYPOINT ["/sbin/tini", "--"]
CMD java $JAVA_OPTS -jar $JAR
