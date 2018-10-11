package com.FittingRoomServer.FittingRoomServer.Domain;

import java.util.List;
import java.util.Map;

public class ClientResponseObject {
    private String productId;
    private String productName;
    private String colorCode;
    private List<Map<String,Object>> colorCodeArray;
    private String size;
    private List<Map<String,Object>> sizeArray;
    public String getProductId() {
        return productId;
    }
    public void setProductId(String productId) {
        this.productId = productId;
    }
    public String getProductName() {
        return productName;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }
    public String getColorCode() {
        return colorCode;
    }
    public void setColorCode(String colorCode) {
        this.colorCode = colorCode;
    }
    public List<Map<String,Object>> getColorCodeArray(){
        return colorCodeArray;
    }
    public void setColorCodeArray(List<Map<String,Object>> colorCodeArray){
        this.colorCodeArray = colorCodeArray;
    }
    public String getSize(){
        return size;
    }
    public void setSize(String size){
        this.size = size;
    }
    public List<Map<String,Object>> getSizeArray(){
        return sizeArray;
    }
    public void setSizeArray(List<Map<String,Object>> sizeArray){
        this.sizeArray = sizeArray;
    }
}
