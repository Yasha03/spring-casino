package ru.itis.springbackend.dto.response;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
public class SlotsGameResponse {

    private List<Integer> numbers;

    private Long bet;

    private Float coeff;

    private Long winning;
}
