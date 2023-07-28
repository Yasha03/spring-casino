package ru.itis.springbackend.dto.response;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.time.Instant;

@Data
@SuperBuilder
public class RouletteHistoryResponse {

    private Instant createdDate;

    private Long bet;

    private Long winning;

    private Boolean isWin;

}
