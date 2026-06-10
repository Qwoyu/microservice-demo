FROM docker.1ms.run/library/eclipse-temurin:8-jre-alpine
LABEL maintainer=gy
ENV JAVA_ARGS="-Dfile.encoding=UTF8 -Duser.timezone=GMT+08"
ENV JAR_FILE="eureka-service.jar"
COPY ./target/${JAR_FILE} ./
EXPOSE 8888
CMD java ${JAVA_ARGS} ${JAVA_OPTS} -jar ${JAR_FILE}