package com.mapleleaf.hodgepdge.exception;

import com.mapleleaf.hodgepdge.common.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import reactor.core.publisher.Mono;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public Mono<Result> runtimeExceptionHandler(RuntimeException ex){
        log.error("RuntimeException: {}", ex.getMessage());
        return Mono.just(Result.error(ex.getMessage()));
    }
}

