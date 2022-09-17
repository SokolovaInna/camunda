package com.example.democamunda.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;


import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class CardDto {
    private Integer id;

    private Boolean actual;

    @NotNull
    private String invOrderNum;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonSerialize
    @JsonDeserialize
    private LocalDateTime created;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonSerialize
    @JsonDeserialize
    private LocalDateTime updated;
}
