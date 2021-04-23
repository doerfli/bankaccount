#= Build ============================================================
FROM adoptopenjdk/openjdk11:alpine-slim as build
WORKDIR /app

COPY build/libs/*jar /app
RUN jar -xf *.jar

#= Run ==============================================================
FROM adoptopenjdk/openjdk11:alpine-slim

VOLUME /tmp
VOLUME /log

COPY --from=build /app/BOOT-INF/lib /app/lib
COPY --from=build /app/META-INF /app/META-INF
COPY --from=build /app/BOOT-INF/classes /app

CMD java -cp app:app/lib/* li.doerf.es.bankaccount.BankaccountApplicationKt
