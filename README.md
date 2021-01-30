# dropwizard-example-app

## Overview

The [dropwizad](https://www.dropwizard.io/en/latest/) example application that utilize [JDBI](http://jdbi.org/) to construct the mapping between database and objects.

Parts of the application:
* The `PersonMapper` provides mapping of `Person` Java class to database table

* The `PersonDao` provides the implementation of SQL queries.

* `migrations.xml` illustrates the usage of `dropwizard-migrations` which can create your database prior to running
your application for the first time.

* The `PersonResource` is a REST resource which use the `PersonDao` to create/retrieve/update/delete data from the database.


## Running The Application

To test the example application run the following commands.

* To create the example, package the application using [Apache Maven](https://maven.apache.org/).
```shell script
mvn clean package
```

* To run Postgres database in docker container
```shell script
docker run -d -ti --rm -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=<YOUR_PASSORD> -e POSTGRES_DB=example --name postgres -p 5432:5432 postgres:13.1-alpine
```

Replace `<YOUR_PASSWORD>` with the password.

* To initialize database run
```shell script
java -jar target/dropwizard-example-1.0.0-SNAPSHOT.jar db migrate config/example.yml
```

* To run the application
```shell script
POSTGRES_PASSWORD=<YOUR_PASSWORD> java -jar target/dropwizard-example-app-1.0.0-SNAPSHOT.jar server config/example.yml
```
Replace `<YOUR_PASSWORD>` with the password of postgres user.

## Usage
* To post data into the application - create person
```shell script
curl -H "Content-Type: application/json" -X POST -d '{"fullName":"Other Person","jobTitle":"Other Title"}' http://localhost:8080/person
```

* To get the list on people
```shell script
curl -X GET http://localhost:8080/person
```

## License
This project is licensed under the Apache License 2.0 ([LICENSE](./LICENSE.TXT))
