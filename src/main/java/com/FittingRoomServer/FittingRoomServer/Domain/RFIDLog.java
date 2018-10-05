package com.FittingRoomServer.FittingRoomServer.Domain;

public class RFIDLog {
    private int storeId;
    private String macAddress;
    private int antennaPort;
    private String epc;
    private String firstSeenTimestamp;
    private int peakRssi;
    private String readerName;
    private String isHeartBeat;
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
    public String getIsHeartBeat() {
        return isHeartBeat;
    }
    public void setIsHeartBeat(String isHeartBeat) {
        this.isHeartBeat = isHeartBeat;
    }
}
