package io.github.maksymdolgykh.dropwizard.example;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;
import io.github.maksymdolgykh.dropwizard.micrometer.MicrometerBundleConfiguration;
import io.github.maksymdolgykh.dropwizard.micrometer.PrometheusConfiguration;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;


public class ExampleConfiguration extends Configuration implements MicrometerBundleConfiguration {
    @JsonProperty("prometheus")
    private PrometheusConfiguration prometheusConfiguration = new PrometheusConfiguration();

    @Override
    public PrometheusConfiguration getPrometheusConfiguration() {
        return prometheusConfiguration;
    }

    @Valid
    @NotNull
    private DataSourceFactory database = new DataSourceFactory();

    @JsonProperty("database")
    public DataSourceFactory getDataSourceFactory() {
        return database;
    }

    @JsonProperty("database")
    public void setDataSourceFactory(DataSourceFactory dataSourceFactory) {
        this.database = dataSourceFactory;
    }
    
}
