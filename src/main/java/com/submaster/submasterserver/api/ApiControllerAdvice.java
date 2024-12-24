package com.submaster.submasterserver.api;

import com.submaster.submasterserver.api.exception.SubMasterException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class ApiControllerAdvice {

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ApiResponse<Object> exception(Exception e) {
        log.error(e.getMessage());
        return ApiResponse.of(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "서버 로직 에러",
                null
        );
    }

    /**
     * validation Exception
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    public ApiResponse<Object> bindException(BindException e) {
        log.error(e.getMessage());
        return ApiResponse.of(
                HttpStatus.BAD_REQUEST,
                e.getBindingResult().getAllErrors().get(0).getDefaultMessage(),
                null
        );
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(SubMasterException.class)
    public ApiResponse<Object> SubMasterException(SubMasterException e) {
        log.error(e.getMessage());
        return ApiResponse.of(
                HttpStatus.BAD_REQUEST,
                e.getMessage(),
                null
        );
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public ApiResponse<Object> NotFoundIdxException(IllegalArgumentException e) {
        log.error(e.getMessage());
        return ApiResponse.of(
                HttpStatus.BAD_REQUEST,
                e.getMessage(),
                null
        );
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ApiResponse<Object> handleDataIntegrityViolation(DataIntegrityViolationException e) {
        log.error(e.getMessage());
        return ApiResponse.of(
                HttpStatus.BAD_REQUEST,
                "데이터 제약 조건으로 인해 작업에 실패했습니다.",
                null
        );
    }
}
