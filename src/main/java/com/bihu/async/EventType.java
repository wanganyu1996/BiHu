package com.bihu.async;

/**
 * Created by wanganyu on 2017/12/18.
 */
public enum EventType {
    MAIL(1);
    private int value;
    EventType(int value){
        this.value=value;
    }
    public int getValue(){
        return value;
    }
}
