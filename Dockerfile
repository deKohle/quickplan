FROM postgres
COPY ./quickplan.jar ./quickplan.jar
EXPOSE 80
EXPOSE 8080
EXPOSE 443
EXPOSE 8443
RUN apt install -y wget apt-transport-https gpg && \
wget -qO - https://packages.adoptium.net/artifactory/api/gpg/key/public | gpg --dearmor | tee /etc/apt/trusted.gpg.d/adoptium.gpg > /dev/null && \
echo "deb https://packages.adoptium.net/artifactory/deb $(awk -F= '/^VERSION_CODENAME/{print$2}' /etc/os-release) main" | tee /etc/apt/sources.list.d/adoptium.list && \
apt update && \
apt install temurin-17-jre -y && \
echo "localhost:5432:quickplan:postgres:Eisar1#?732" > ~/.pgpass && \
sudo -u postgres createdb quickplan
CMD [ "pg_ctl start && java -jar ./quickplan.jar" ]
