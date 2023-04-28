package com.soprasteria.adivinalapalabra.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
public class WordResponse {

    private boolean wordExists;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<PositionOfWordResponse> positionOfWordResponseList;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String errorMsg;

    public WordResponse(boolean wordExists, List<PositionOfWordResponse> positionOfWordResponseList) {
        this.wordExists = wordExists;
        this.positionOfWordResponseList = positionOfWordResponseList;
    }
}
