package com.soprasteria.adivinalapalabra.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.soprasteria.adivinalapalabra.model.enums.HitsStatus;
import lombok.Data;

import java.util.List;

@Data
public class WordResponse {

    private boolean wordExists;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<PositionOfWordResponse> positionOfWordResponseList;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String errorMsg;

    private boolean roundWin;

    public WordResponse(boolean wordExists, List<PositionOfWordResponse> positionOfWordResponseList) {
        this.wordExists = wordExists;
        this.positionOfWordResponseList = positionOfWordResponseList;
        if (positionOfWordResponseList != null) {
            roundWin = endOfGame(positionOfWordResponseList);
        }
    }
    private boolean endOfGame(List<PositionOfWordResponse> listPositionWordResponse) {
        return listPositionWordResponse.stream().allMatch(position -> position.getHitStatus().equals(HitsStatus.HIT));
    }
}
