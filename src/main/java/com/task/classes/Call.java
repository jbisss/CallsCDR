package com.task.classes;

import com.task.enums.CallCode;

public class Call {
    private CallCode callCode;
    private String startTime;
    private String endTime;
    private String duration;
    private double cost;
    private void countDuration(String startTime, String endTime){

    }
    public Call(CallCode callCode, String startTime, String endTime){
        this.callCode = callCode;
        this.startTime = startTime;
        this.endTime = endTime;
        countDuration(startTime, endTime);
    }
}
