#!/bin/bash
su postgres
docker-entrypoint.sh postgres &
sleep 10
echo "FIRE!"
pg_isready -U postgres -h localhost -p 5432 -d postgres://postgres@localhost:5432/quickplan
while [ $? -gt 0 ]
do
	echo 'Postgres is not ready yet...'
	sleep 1
	pg_isready -U postgres -h localhost -p 5432 -d postgres://postgres@localhost:5432/quickplan
done
java -jar ./quickplan.jar
