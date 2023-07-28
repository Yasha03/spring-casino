package ru.itis.springbackend.dto.response;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.time.Instant;
import java.util.List;

@Data
@SuperBuilder
public class SlotsHistoryResponse {

    private Instant createdDate;

    private Long bet;

    private List<Integer> numbers;

    private Long winning;

    private Float coeff;

}
