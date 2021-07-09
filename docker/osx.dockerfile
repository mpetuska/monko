FROM sickcodes/docker-osx:auto

RUN mkdir -p ~/.gradle && \
 echo "org.gradle.daemon=false" >> ~/.gradle/gradle.properties \
 echo "org.gradle.console=plain" >> ~/.gradle/gradle.properties

WORKDIR /install
COPY scripts/setupOSX.sh setup.sh
RUN rm -rf scripts/mongo-c-driver*
RUN ls -a scripts
RUN ./setup.sh

USER $USER
WORKDIR /repository
COPY gradle gradle
COPY gradlew gradlew
RUN ./gradlew --no-daemon --version

ENTRYPOINT ["./gradlew"]