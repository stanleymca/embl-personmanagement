FROM store/oracle/serverjre:1.8.0_241-b07

ARG JAR_PATH="build/libs/"
ENV JAVA_OPTS="-Xms256m -Xmx256m"

RUN echo "Environment variable JAVA_OPTS : $JAVA_OPTS || Argumets JAR_PATH : $JAR_PATH"

ADD $JAR_PATH/PersonManagement-0.0.1-SNAPSHOT.jar /opt/PersonManagement-0.0.1-SNAPSHOT.jar
ENTRYPOINT exec java -Dfile.encoding=UTF-8 -jar /opt/PersonManagement-0.0.1-SNAPSHOT.jar $JAVA_OPTS
EXPOSE 8085
