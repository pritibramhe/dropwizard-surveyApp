
package com.example.survey;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;

public class SurveyConfiguration extends Configuration {

    @JsonProperty("elasticsearchUrl")
    private String elasticsearchUrl = "http://elasticsearch:9200";

    public String getElasticsearchUrl() {
        return elasticsearchUrl;
    }
}
