FROM ubuntu/postgres
COPY ./quickplan.jar ./quickplan.jar
COPY ./docker-init.sql /docker-entrypoint-initdb.d/docker-init.sql
EXPOSE 80
EXPOSE 8080
EXPOSE 443
EXPOSE 8443
ENV POSTGRES_USER postgres
ENV POSTGRES_PASSWORD Eisar1#?732
ENV POSTGRES_DB quickplan
RUN apt update \
	&& apt install -y wget apt-transport-https gpg \
	&& wget -qO - https://packages.adoptium.net/artifactory/api/gpg/key/public | gpg --dearmor | tee /etc/apt/trusted.gpg.d/adoptium.gpg > /dev/null \
	&& echo "deb https://packages.adoptium.net/artifactory/deb $(awk -F= '/^VERSION_CODENAME/{print$2}' /etc/os-release) main" | tee /etc/apt/sources.list.d/adoptium.list \
	&& apt update \
	&& apt install temurin-17-jre -y \
	&& echo "localhost:5432:quickplan:postgres:Eisar1#?732" > ~/.pgpass
CMD [ "docker-entrypoint.sh && sleep 10 && java -jar ./quickplan.jar" ]
