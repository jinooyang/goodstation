package com.enjoytrip.response;

import lombok.Data;
import org.springframework.lang.Nullable;

import java.util.HashMap;
import java.util.Map;

@Data
public class ResponseMessage {
    private String message;
    private Map<String,Object> data;
    private StatusEnum status;

    public ResponseMessage(){
        this.status = StatusEnum.FAIL;
        this.data = null;
        this.message = null;
    }
    public void setData(String key, Object value) {
        if(this.data==null)data = new HashMap<>();
        data.put(key, value);
    }
}
