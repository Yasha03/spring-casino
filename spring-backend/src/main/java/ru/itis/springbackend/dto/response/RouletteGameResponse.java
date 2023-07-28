package ru.itis.springbackend.dto.response;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class RouletteGameResponse {

    private Boolean isWin;

    private Integer deg;
}
