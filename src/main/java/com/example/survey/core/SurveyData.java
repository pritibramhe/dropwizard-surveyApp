
package com.example.survey.core;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Survey data submitted by users")
public class SurveyData {

    @Schema(required = true, example = "30")
    public int age;

    @Schema(required = true, allowableValues = {"male", "female", "other"}, example = "male")
    public String gender;

    @Schema(required = true, example = "APAC")
    public String region;

    @Schema(required = true, example = "survey-123")
    public String surveyID;

    @Schema(required = true, minimum = "1", maximum = "5", example = "4")
    public int score;
}
