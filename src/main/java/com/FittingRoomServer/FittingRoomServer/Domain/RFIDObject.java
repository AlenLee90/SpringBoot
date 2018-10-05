package com.FittingRoomServer.FittingRoomServer.Domain;

import java.util.List;

public class RFIDObject {
    private String reader_name;
    private String mac_address;
    private List<RFIDLog> tag_reads;
    public String getReader_name() {
        return reader_name;
    }
    public void setReader_name(String reader_name) {
        this.reader_name = reader_name;
    }
    public String getMac_address() {
        return mac_address;
    }
    public void setMac_address(String mac_address) {
        this.mac_address = mac_address;
    }
    public List<RFIDLog> getTag_reads() {
        return tag_reads;
    }
    public void setTag_reads(List<RFIDLog> tag_reads) {
        this.tag_reads = tag_reads;
    }
}
