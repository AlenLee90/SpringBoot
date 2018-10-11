package com.FittingRoomServer.FittingRoomServer.Domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RFIDLog {
    private int storeId;
    private String macAddress;
    private int antennaPort;
    private String epc;
    private String firstSeenTimestamp;
    private int peakRssi;
    private String readerName;
    private String createdAt;
    private String createdBy;
    private String updatedAt;
    private String updatedBy;
    public int getStoreId() {
        return storeId;
    }
    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }
    public String getMacAddress() {
        return macAddress;
    }
    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }
    public int getAntennaPort() {
        return antennaPort;
    }
    public void setAntennaPort(int antennaPort) {
        this.antennaPort = antennaPort;
    }
    public String getEpc() {
        return epc;
    }
    public void setEpc(String epc) {
        this.epc = epc;
    }
    public String getFirstSeenTimestamp() {
        return firstSeenTimestamp;
    }
    public void setFirstSeenTimestamp(String firstSeenTimestamp) {
        this.firstSeenTimestamp = firstSeenTimestamp;
    }
    public int getPeakRssi() {
        return peakRssi;
    }
    public void setPeakRssi(int peakRssi) {
        this.peakRssi = peakRssi;
    }
    public String getReaderName() {
        return readerName;
    }
    public void setReaderName(String readerName) {
        this.readerName = readerName;
    }
    public String getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
    public String getCreatedBy() {
        return createdBy;
    }
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
    public String getUpdatedAt() {
        return updatedAt;
    }
    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
    public String getUpdatedBy() {
        return updatedBy;
    }
    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }
}
