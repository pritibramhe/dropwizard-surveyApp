
package com.example.survey.elastic;

import com.example.survey.core.SurveyData;
import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.xcontent.XContentType;

import java.io.IOException;

public class ElasticClient {

    private final RestHighLevelClient client;

    public ElasticClient(String url) {
        this.client = new RestHighLevelClient(RestClient.builder(HttpHost.create(url)));
    }

    public void indexSurvey(SurveyData data) {
        try {
            String json = String.format(
                    "{\"age\":%d,\"gender\":\"%s\",\"region\":\"%s\",\"surveyID\":\"%s\",\"score\":%d}",
                    data.age, data.gender, data.region, data.surveyID, data.score);
            IndexRequest request = new IndexRequest("surveys").source(json, XContentType.JSON);
            client.index(request, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
