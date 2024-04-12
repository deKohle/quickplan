FROM ubuntu/postgres
COPY ./quickplan.jar ./quickplan.jar
EXPOSE 80
EXPOSE 8080
EXPOSE 443
EXPOSE 8443
RUN sudo apt install temurin-17-jre -y && \
	sudo echo "localhost:5432:quickplan:postgres:Eisar1#?732" > ~/.pgpass && \
	sudo -u postgres createdb quickplan
CMD [ "pg_ctl start && java -jar ./quickplan.jar" ]