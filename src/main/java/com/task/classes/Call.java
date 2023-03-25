package com.task.classes;

import com.task.enums.CallCode;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Call {
    private final CallCode callCode;
    private String startTime;
    private String endTime;
    private String duration;
    private double cost;

    private String buildDate(String time) {
        StringBuilder resultDate = new StringBuilder();
        for (int i = 0; i < time.length(); i++) {
            resultDate.append(time.charAt(i));
            if (i == 3 || i == 5) {
                resultDate.append("-");
            }
            if (i == 7) {
                resultDate.append(" ");
            }
            if (i == 9 || i == 11) {
                resultDate.append(":");
            }
        }
        return resultDate.toString();
    }
    private String convertDurationToString(long duration){
        return null;
    }
    private void countDuration(String startTime, String endTime) throws ParseException {
        this.startTime = buildDate(startTime);
        this.endTime = buildDate(endTime);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long duration = (dateFormat.parse(startTime).getTime() - dateFormat.parse(endTime).getTime()) / 1000;
        this.duration = convertDurationToString(duration);
    }

    public Call(CallCode callCode, String startTime, String endTime) {
        this.callCode = callCode;
        this.startTime = startTime;
        this.endTime = endTime;
        try {
            countDuration(startTime, endTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}
