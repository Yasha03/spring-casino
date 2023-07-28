package ru.itis.springbackend.dto.response;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
public class KenoGameResponse {

    private Integer countCorrectNumbers;

    private List<Integer> correctNumbers;

    private Long bet;

    private Long winning;
}
