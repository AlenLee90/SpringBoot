package com.FittingRoomServer.FittingRoomServer.Dao;

import com.FittingRoomServer.FittingRoomServer.Domain.RFIDLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class RFIDLogDao {

    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public List<RFIDLog> findAll(){
        RowMapper<RFIDLog> rowMapper = new BeanPropertyRowMapper<RFIDLog>(RFIDLog.class);
        String sql = "SELECT store_id,mac_address,antenna_port,epc,first_seen_timestamp,peak_rssi,IFNULL(reader_name,'') AS reader_name FROM RFID_LOG";
        List<RFIDLog> result = jdbcTemplate.query(sql, rowMapper);
        try{
            result = jdbcTemplate.query(sql, rowMapper);
        }catch (Exception e){
            e.getStackTrace();
        }
        return result;
    }

    public List<RFIDLog> findByAntennaPort(String antennaPort){
        RowMapper<RFIDLog> rowMapper = new BeanPropertyRowMapper<RFIDLog>(RFIDLog.class);
        String sql = "SELECT store_id,mac_address,antenna_port,epc,first_seen_timestamp,peak_rssi,IFNULL(reader_name,'') AS reader_name FROM RFID_LOG WHERE antenna_port=?";
        List<RFIDLog> result = jdbcTemplate.query(sql, rowMapper,antennaPort);
        try{
            result = jdbcTemplate.query(sql, rowMapper);
        }catch (Exception e){
            e.getStackTrace();
        }
        return result;
    }

    public boolean checkDataExists(String storeId,String epc){
        String sql = "SELECT count(*) FROM RFID_LOG WHERE store_id = ? and epc=?";
        int count = jdbcTemplate.queryForObject(sql, Integer.class, storeId, epc);
        if(count == 0) {
            return false;
        } else {
            return true;
        }
    }

    public void update(List<RFIDLog> data){
        List<RFIDLog> updateBatchData = new ArrayList<RFIDLog>();
        List<RFIDLog> insertBatchData = new ArrayList<RFIDLog>();
        for(RFIDLog temp : data){
            if(checkDataExists(String.valueOf(temp.getStoreId()),temp.getEpc())==true){
                updateBatchData.add(temp);
            }else {
                insertBatchData.add(temp);
            }
        }
        if(!insertBatchData.isEmpty()){
            SqlParameterSource[] beanSources = SqlParameterSourceUtils.createBatch(insertBatchData.toArray());
            String sql = "INSERT INTO RFID_LOG(store_id,mac_address,antenna_port,epc,first_seen_timestamp,peak_rssi,reader_name) VALUES (:storeId,:macAddress,:antennaPort,:epc,:firstSeenTimestamp,:peakRssi,:readerName);";
            namedParameterJdbcTemplate.batchUpdate(sql, beanSources);
        }
        if(!updateBatchData.isEmpty()){
            SqlParameterSource[] beanSources = SqlParameterSourceUtils.createBatch(updateBatchData.toArray());
            String sql = "UPDATE RFID_LOG SET first_seen_timestamp = :firstSeenTimestamp WHERE store_id = :storeId AND epc = :epc;";
            namedParameterJdbcTemplate.batchUpdate(sql, beanSources);
        }
    }
}
