package com.enjoytrip.util;

import org.springframework.stereotype.Component;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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

    public static String convertToStringDate(String dateString) throws ParseException{
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
//        return dateFormat.parse(dateString).toString();
        // Parse the input date string
        LocalDate date = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        // Format the date as "yyyyMMdd"
        String formattedDate = date.format(DateTimeFormatter.ofPattern("yyyyMMdd"));

        return formattedDate;
    }



    public static Timestamp parseDateString(String dateString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        System.out.println("입력 받음 : " + dateString);
        System.out.println("dateFormat : " + dateFormat);
        try {
            java.util.Date utilDate = dateFormat.parse(dateString);
            System.out.println("반환되는 날 : " + utilDate);
            Timestamp timestamp = new Timestamp(utilDate.getTime());
            System.out.println("!!!+" + timestamp);
            return timestamp;
        } catch (ParseException e) {
            e.printStackTrace();
            return null; // 예외 발생 시 null 반환 또는 다른 적절한 처리
        }
    }

}
