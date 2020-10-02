package com.example.demo1.config;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
//@Component
//public class DateConverterConfig implements Converter<String,Date> {
//    @Override
//    public Date convert(String source){
//        Date date=null;
//        SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");
//        try{
//            date=sdf.parse(source);
//        }catch (Exception e){
//            System.out.println(e);
//            date=new Date();
//        }
//        return date;
//    }
//}
