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

    /**
     * @param callCode  code of call
     * @param startTime call's start time
     * @param endTime   call's end time
     */
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

    /**
     * Converts time to a right format
     *
     * @param time time in incorrect format (yyyyMMddHHmmss)
     * @return time in correct format (yyyy-MM-dd HH:mm:ss)
     */
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

    /**
     * Numbers which are less than 10 should be represented with a starting zero (6 -> '06')
     *
     * @param x a number
     * @return a number as a String in right format (9 -> '09'; 10 -> '10')
     */
    private String makeNumberString(int x) {
        if (x < 10) {
            return "0" + x;
        } else {
            return Integer.toString(x);
        }
    }

    /**
     * Converts a time in seconds to a String in HH:mm:ss format
     *
     * @param duration in seconds
     * @return String in format HH:mm:ss
     */
    private String convertDurationToString(long duration) {
        int hours = (int) (duration / 3600);
        duration = duration % 3600;
        int minutes = (int) (duration / 60);
        duration = duration % 60;
        int seconds = (int) duration;
        return makeNumberString(hours) + ":" + makeNumberString(minutes) + ":" + makeNumberString(seconds);
    }

    /**
     * Counts duration of a call and stores a String and Numeric representation of duration
     *
     * @param startTime time when the call was started
     * @param endTime   time when the call was ended
     * @throws ParseException if date can not be parsed
     */
    private void countDuration(String startTime, String endTime) throws ParseException {
        this.startTime = buildDate(startTime);
        this.endTime = buildDate(endTime);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long duration = (dateFormat.parse(this.endTime).getTime() - dateFormat.parse(this.startTime).getTime()) / 1000;
        this.durationDecimal = duration;
        this.duration = convertDurationToString(duration);
    }

    /**
     * @param cost of call
     */
    public void setCost(double cost) {
        this.cost = cost;
    }

    /**
     * @return Numeric representation of duration
     */
    public long getDurationNumber() {
        return durationDecimal;
    }

    /**
     * @return String representation of duration
     */
    public String getDuration() {
        return duration;
    }

    /**
     * @return callCode of call
     */
    public CallCode getCallCode() {
        return callCode;
    }

    /**
     * @return end Time
     */
    public String getEndTime() {
        return endTime;
    }

    /**
     * @return cost of a call
     */
    public double getCost() {
        return cost;
    }

    /**
     * @return start Time
     */
    public String getStartTime() {
        return startTime;
    }
}
