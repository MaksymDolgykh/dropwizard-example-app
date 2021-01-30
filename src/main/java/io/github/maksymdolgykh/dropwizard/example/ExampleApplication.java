package io.github.maksymdolgykh.dropwizard.example;

import io.dropwizard.Application;
import io.dropwizard.configuration.EnvironmentVariableSubstitutor;
import io.dropwizard.configuration.SubstitutingSourceProvider;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import io.github.maksymdolgykh.dropwizard.example.dao.PersonDao;
import io.github.maksymdolgykh.dropwizard.example.resources.PersonResource;
import org.jdbi.v3.core.Jdbi;
import io.dropwizard.jdbi3.JdbiFactory;


public class ExampleApplication extends Application<ExampleConfiguration> {
    public static void main(String[] args) throws Exception {
        new ExampleApplication().run(args);
    }

    @Override
    public void initialize(Bootstrap<ExampleConfiguration> bootstrap) {
        // Enable variable substitution with environment variables
        bootstrap.setConfigurationSourceProvider(
                new SubstitutingSourceProvider(
                        bootstrap.getConfigurationSourceProvider(),
                        new EnvironmentVariableSubstitutor(false)
                )
        );

        bootstrap.addBundle(new MigrationsBundle<ExampleConfiguration>() {
            @Override
            public DataSourceFactory getDataSourceFactory(ExampleConfiguration configuration) {
                return configuration.getDataSourceFactory();
            }
        });

    }

    @Override
    public void run(ExampleConfiguration configuration, Environment environment) {
        final JdbiFactory factory = new JdbiFactory();
        final Jdbi database = factory.build(environment, configuration.getDataSourceFactory(), "postgresql");
        final PersonDao personDao = database.onDemand(PersonDao.class);
        final PersonResource personResource = new PersonResource(personDao);
        
        environment.jersey().register(personResource);
        
    }
}
