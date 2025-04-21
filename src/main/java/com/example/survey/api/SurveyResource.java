
package com.example.survey.api;

import com.example.survey.core.SurveyData;
import com.example.survey.elastic.ElasticClient;
import io.swagger.v3.oas.annotations.Operation;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/surveys")
public class SurveyResource {

    private final ElasticClient client;

    public SurveyResource(ElasticClient client) {
        this.client = client;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Send survey information")
    public Response receiveSurvey(SurveyData data) {
        System.out.println("Received survey: " + data.surveyID);
        client.indexSurvey(data);
        return Response.ok().build();
    }
}
