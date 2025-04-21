
package com.example.survey;

import com.example.survey.api.SurveyResource;
import com.example.survey.elastic.ElasticClient;
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.swagger.v3.jaxrs2.integration.resources.OpenApiResource;

public class SurveyApplication extends Application<SurveyConfiguration> {

    public static void main(String[] args) throws Exception {
        new SurveyApplication().run(args);
    }

    @Override
    public void initialize(Bootstrap<SurveyConfiguration> bootstrap) {
        // Serve Swagger UI static assets from the WebJar under /swagger-ui
        bootstrap.addBundle(new AssetsBundle(
                "/META-INF/resources/webjars/swagger-ui/4.15.5",
                "/swagger-ui",
                "index.html",
                "swagger-ui"));
    }

    @Override
    public void run(SurveyConfiguration configuration, Environment environment) {
        // Elasticsearch client
        ElasticClient client = new ElasticClient(configuration.getElasticsearchUrl());

        // Register JAX-RS resource
        environment.jersey().register(new SurveyResource(client));

        // Expose generated OpenAPI spec at /openapi.json
        environment.jersey().register(OpenApiResource.class);
    }
}
