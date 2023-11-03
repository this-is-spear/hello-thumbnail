FROM openjdk:17-ea-oracle

COPY ./build/libs/*.jar app.jar

CMD echo 'sleep for initialze hbase' && sleep 30 && java -jar -javaagent:/pinpoint-agent/pinpoint-bootstrap-2.5.3.jar -Dpinpoint.agentId=thumbnail-app-1 -Dpinpoint.applicationName=thumbnail -Dpinpoint.profiler.profiles.active=local app.jar
