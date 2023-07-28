package ru.itis.springbackend.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class KenoGameRequest {

    private List<Integer> selectedNumbers;

    private Long bet;
}
