package com.task.classes;

import com.task.enums.CallCode;
import com.task.enums.Tariff;

import java.util.ArrayList;
import java.util.HashMap;

public class Subscriber {
    public static HashMap<String, Subscriber> subscribers = new HashMap<>();
    private final String number;
    private final Tariff tariff;
    private final ArrayList<Call> calls = new ArrayList<>();

    public Subscriber(String number, Tariff tariff){
        this.number = number;
        this.tariff = tariff;
    }
    public void addCall(String code, String startTime, String endTime){
        calls.add(new Call(CallCode.getCallCodeByCode(code), startTime, endTime));
    }
    public String getNumber() {
        return number;
    }
}
