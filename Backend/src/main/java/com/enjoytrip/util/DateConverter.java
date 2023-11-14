package com.enjoytrip.util;

import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
@Component
public class DateConverter {
    public java.sql.Date convertStringToSqlDate(String dateString) throws ParseException {
        // 날짜 형식 지정
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        // 문자열을 Date로 파싱
        java.util.Date parsedDate = dateFormat.parse(dateString);

        // java.sql.Date로 변환
        return new java.sql.Date(parsedDate.getTime());
    }
}
