package ru.itis.springbackend.dto.response;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.time.Instant;
import java.util.List;

@Data
@SuperBuilder
public class KenoHistoryResponse {
    private Instant createdDate;

    private List<Integer> selectedNumbers;

    private List<Integer> correctNumbers;

    private Long bet;

    private Long winning;

    private Float coeff;
}
