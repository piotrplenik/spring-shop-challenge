FROM gcr.io/google_appengine/openjdk
VOLUME /tmp
ADD com.teamlab.shopchallenge-1.0.war app.jar
RUN sh -c 'touch /app.jar'
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]
