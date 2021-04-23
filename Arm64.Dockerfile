#= Build ============================================================
FROM adoptopenjdk/openjdk11:aarch64-ubuntu-jdk-11.0.10_9 as build
WORKDIR /app

COPY build/libs/*jar /app
RUN jar -xf *.jar

#= Run ==============================================================
FROM adoptopenjdk/openjdk11:aarch64-ubuntu-jdk-11.0.10_9

VOLUME /tmp
VOLUME /log

COPY --from=build /app/BOOT-INF/lib /app/lib
COPY --from=build /app/META-INF /app/META-INF
COPY --from=build /app/BOOT-INF/classes /app

CMD java -cp app:app/lib/* li.doerf.es.bankaccount.BankaccountApplicationKt
