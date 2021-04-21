#= Build ============================================================
FROM adoptopenjdk/openjdk11:alpine-slim as build
WORKDIR /app

COPY build.gradle.kts .
COPY gradlew .
COPY settings.gradle.kts .
COPY gradle gradle
COPY src src

RUN ./gradlew -Dorg.gradle.daemon=false :assemble
RUN mkdir -p build/libs/dependency && (cd build/libs/dependency; jar -xf ../*.jar)

#= Run ==============================================================
FROM adoptopenjdk/openjdk11:alpine-slim

VOLUME /tmp
VOLUME /log

COPY --from=build /app/build/libs/dependency/BOOT-INF/lib /app/lib
COPY --from=build /app/build/libs/dependency/META-INF /app/META-INF
COPY --from=build /app/build/libs/dependency/BOOT-INF/classes /app

ENTRYPOINT java -cp app:app/lib/* li.doerf.es.bankaccount.BankaccountApplicationKt
