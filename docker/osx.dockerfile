FROM sickcodes/docker-osx:auto
ARG user=run
RUN useradd -ms /bin/bash --home-dir /repository $user

RUN mkdir -p ~/.gradle && \
 echo "org.gradle.daemon=false" >> ~/.gradle/gradle.properties \
 echo "org.gradle.console=plain" >> ~/.gradle/gradle.properties

WORKDIR /install-libmongoc
COPY scripts scripts
RUN ./scripts/setupOSX.sh

WORKDIR /install-gradle
COPY . .
RUN sudo chown -R $user "$PWD"
USER $user
RUN ./gradlew compile

WORKDIR /repository
USER root
RUN rm -rf /install*
USER $user

ENTRYPOINT ["./gradlew"]