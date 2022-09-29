package com.example.BackendVolatile.util;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ActivityUtil {

    public static final Long oneDayLong = 1000 * 3600 * 24L;


    public Integer calActivity(List<Long> timeStamp, Integer n){
        int count = 0;
        long currentDay = System.currentTimeMillis() / oneDayLong;
        List<Long> dayStamp = new ArrayList<>();
        for(int i = 0; i< timeStamp.size();i++){
            dayStamp.add(timeStamp.get(i) / oneDayLong);
        }
        for(long i = currentDay; i > currentDay - n; i--){
            for(int j = 0; j < dayStamp.size(); j++){
                if(i == dayStamp.get(j)){
                    count++;
                    break;
                }
            }
        }

        return (int)(100.0 * count / n);
    }
}
