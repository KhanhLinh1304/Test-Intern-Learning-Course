package com.example.learning_intern.service;

import org.springframework.stereotype.Service;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class RegexService {
    public boolean regexUsername(String name){
        String regex = "^[a-zA-Z0-9]{6,10}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(name);
        return matcher.matches();
    }
    public boolean regexMobileNumber(String mobile){
        return !mobile.isEmpty() && mobile.matches("^[a-zA-Z0-9]{11}$");
    }
    public boolean regexPassword(String pwd){
        String regex = "^[a-zA-Z0-9]{8,16}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(pwd);
        return matcher.matches();
    }
    public boolean isValidMonthYear(String monthyear){
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-mm");
        try{
            YearMonth.parse(monthyear, format);
            return true;
        }catch (DateTimeParseException e){
            return false;
        }
    }
}
