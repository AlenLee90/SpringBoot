package com.FittingRoomServer.FittingRoomServer.Domain;

import java.util.ArrayList;

public class ResponseObject {
    private int code;
    private String message;
    private ArrayList data;
    public int getCode() {
        return code;
    }
    public void setCode(int code) {
        this.code = code;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public ArrayList getData(){
        return data;
    }
    public void setData(ArrayList data) {
        this.data = data;
    }
}
