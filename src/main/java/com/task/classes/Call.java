package com.task.classes;

import com.task.enums.CallCode;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Call {
    private long durationDecimal;
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
    private String makeStringDecimal(int x) {
        if (x < 10) {
            return "0" + x;
        } else {
            return Integer.toString(x);
        }
    }

    private String convertDurationToString(long duration) {
        int hours = (int) (duration / 3600);
        duration = duration % 3600;
        int minutes = (int) (duration / 60);
        duration = duration % 60;
        int seconds = (int) duration;
        return makeStringDecimal(hours) + ":" + makeStringDecimal(minutes) + ":" + makeStringDecimal(seconds);
    }

    public String getDuration() {
        return duration;
    }

    public CallCode getCallCode() {
        return callCode;
    }

    public String getEndTime() {
        return endTime;
    }

    public double getCost() {
        return cost;
    }

    public String getStartTime() {
        return startTime;
    }

    private void countDuration(String startTime, String endTime) throws ParseException {
        this.startTime = buildDate(startTime);
        this.endTime = buildDate(endTime);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long duration = (dateFormat.parse(this.endTime).getTime() - dateFormat.parse(this.startTime).getTime()) / 1000;
        this.durationDecimal = duration;
        this.duration = convertDurationToString(duration);
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public long getDurationDecimal() {
        return durationDecimal;
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
