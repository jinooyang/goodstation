package com.enjoytrip.auth;

import com.enjoytrip.response.ResponseMessage;
import com.enjoytrip.response.StatusEnum;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class authController {
    @GetMapping("/user")
    public ResponseEntity<ResponseMessage> checkAuthUser(){
        ResponseMessage rm = new ResponseMessage();
        rm.setMessage("권한이 있습니다");
        rm.setData("auth",true);
        rm.setStatus(StatusEnum.OK);
        return new ResponseEntity<>(rm, HttpStatus.OK);
    }


}
