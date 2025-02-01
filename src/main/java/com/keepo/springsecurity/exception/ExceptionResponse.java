package com.keepo.springsecurity.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

import static com.keepo.springsecurity.utils.date.DateUtils.date;


@Getter
@Setter
@AllArgsConstructor
@Builder
public class ExceptionResponse {

    private final String message;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private final String timestamp;

    public ExceptionResponse(String message) {
        this.message = message;
        this.timestamp = date(LocalDateTime.now());
    }
}