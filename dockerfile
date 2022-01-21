#base image
FROM maven:3.8.1-jdk-11-slim@sha256:705fad546b8463c10a1e3a0d88a95c8da5a20f49732bc2daabd9d93ab165ca87 AS build
RUN mkdir /cakemgr
COPY . /cakemgr
WORKDIR /cakemgr
RUN mvn clean package

FROM adoptopenjdk/openjdk11:jre-11.0.9.1_1-alpine@sha256:b6ab039066382d39cfc843914ef1fc624aa60e2a16ede433509ccadd6d995b1f
RUN mkdir /app
RUN addgroup --system javauser && adduser -S -s /bin/false -G javauser javauser
COPY --from=build /cakemgr/target/cakemgr-0.0.1.jar /app/cakemgr-0.0.1.jar
WORKDIR /app
RUN chown -R javauser:javauser /app
USER javauser
ENTRYPOINT [ "java", "-jar", "cakemgr-0.0.1.jar" ]