FROM ubuntu/postgres
COPY ./quickplan.jar ./quickplan.jar
COPY ./start_docker.sh ./start_docker.sh
EXPOSE 80
EXPOSE 8080
EXPOSE 443
EXPOSE 8443
ENV POSTGRES_USER postgres
ENV POSTGRES_PASSWORD Eisar1#?732
ENV POSTGRES_DB quickplan
ENV POSTGRES_HOST localhost
RUN apt update \
	&& apt install -y wget apt-transport-https gpg \
	&& wget -qO - https://packages.adoptium.net/artifactory/api/gpg/key/public | gpg --dearmor | tee /etc/apt/trusted.gpg.d/adoptium.gpg > /dev/null \
	&& echo "deb https://packages.adoptium.net/artifactory/deb $(awk -F= '/^VERSION_CODENAME/{print$2}' /etc/os-release) main" | tee /etc/apt/sources.list.d/adoptium.list \
	&& apt update \
	&& apt install temurin-17-jre -y \
	&& mkdir /home/postgres \
	&& echo "localhost:5432:quickplan:postgres:Eisar1#?732" > /home/postgres/.pgpass \
	&& chmod 0600 /home/postgres/.pgpass \
	&& chown postgres /home/postgres/.pgpass \
	&& chmod +x ./start_docker.sh
ENTRYPOINT [ "./start_docker.sh" ]
