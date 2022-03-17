FROM maven:3.6.3-jdk-11


RUN apt-get -y update \
    && apt-get -y upgrade \
    && rm -rf /var/lib/apt/lists/*

WORKDIR /opt/dropwizard-example-app

COPY . /opt/dropwizard-example-app
RUN mvn clean package



RUN printf '\
java -jar /opt/dropwizard-example-app/target/dropwizard-example-app-1.0.0-SNAPSHOT.jar db migrate config/example.yml\n \
java -jar /opt/dropwizard-example-app/target/dropwizard-example-app-1.0.0-SNAPSHOT.jar server config/example.yml\n' \
  > /start.sh \
  && chmod +x /start.sh

CMD ["/start.sh"]
