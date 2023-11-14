package com.enjoytrip.Exception;

import com.enjoytrip.response.ResponseMessage;
import com.enjoytrip.response.StatusEnum;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(LoginFailedException.class)
    public ResponseEntity<ResponseMessage> handleLoginFailedException(LoginFailedException ex) {
        return new ResponseEntity<>(
                new ResponseMessage("로그인 실패. 아이디 혹은 비밀번호를 확인해주세요", null, StatusEnum.FAIL),
                HttpStatus.UNAUTHORIZED);
    }
}