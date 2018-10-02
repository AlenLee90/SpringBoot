package com.FittingRoomServer.FittingRoomServer.Domain;

public class RFIDData {
    private String epc;
    private String timestamp;
    private int antennaPort;
    public String getEpc() {
        return epc;
    }
    public void setEpc(String epc) {
        this.epc = epc;
    }
    public String getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
    public int getAntennaPort() {
        return antennaPort;
    }
    public void setAntennaPort(int antennaPort) {
        this.antennaPort = antennaPort;
    }
}
