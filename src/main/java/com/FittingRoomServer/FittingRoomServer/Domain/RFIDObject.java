package com.FittingRoomServer.FittingRoomServer.Domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class RFIDObject {
    private String readerName;
    private String macAddress;
    private List<RFIDLog> tagReads;
    @JsonProperty("reader_name")
    public String getReaderName() {
        return readerName;
    }
    public void setReaderName(String readerName) {
        this.readerName = readerName;
    }
    @JsonProperty("mac_address")
    public String getMacAddress() {
        return macAddress;
    }
    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }
    @JsonProperty("tag_reads")
    public List<RFIDLog> getTagReads() {
        return tagReads;
    }
    public void setTagReads(List<RFIDLog> tagReads) {
        this.tagReads = tagReads;
    }
}
