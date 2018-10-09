package com.FittingRoomServer.FittingRoomServer.Dao;

import com.FittingRoomServer.FittingRoomServer.Domain.ClientResponseObject;
import com.FittingRoomServer.FittingRoomServer.Domain.RFIDLog;
import com.FittingRoomServer.FittingRoomServer.Domain.RFIDObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Repository;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Repository
public class RFIDLogDao {

    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public List<ClientResponseObject> getClientResponseData(String antennaPort,String storeId,String beginDate,String endDate){
        RowMapper<ClientResponseObject> rowMapper = new BeanPropertyRowMapper<ClientResponseObject>(ClientResponseObject.class);
        String sql = "SELECT b.epc,c.product_id,c.product_name,c.color_code FROM (SELECT \n" +
                "\t(CASE WHEN COUNT(a.epc) >=5 THEN a.epc END) AS epc\n" +
                "FROM test_db.RFID_LOG  a\n" +
                "WHERE  antenna_port = ? \n" +
                "AND store_id = ? \n" +
                "AND (first_seen_timestamp BETWEEN ? AND ?) \n" +
                "GROUP BY a.epc) b,\n" +
                "product_master c\n" +
                "WHERE b.epc = c.epc\n" +
                "ORDER BY c.product_id ";
        List<ClientResponseObject> result = jdbcTemplate.query(sql, rowMapper, antennaPort, storeId, beginDate, endDate);
        try{
            result = jdbcTemplate.query(sql, rowMapper);
        }catch (Exception e){
            e.getStackTrace();
        }
        return result;
    }

    public boolean checkDataExists(String antennePort,String epc){
        String sql = "SELECT count(*) FROM rfid_log WHERE antenna_port = ? and epc=?";
        int count = jdbcTemplate.queryForObject(sql, Integer.class, antennePort, epc);
        if(count == 0) {
            return false;
        } else {
            return true;
        }
    }

    public void update(RFIDObject data,long errorTime){
//        List<RFIDLog> updateBatchData = new ArrayList<RFIDLog>();
        List<RFIDLog> insertBatchData = new ArrayList<RFIDLog>();
        for(RFIDLog temp : data.getTag_reads()){
//这一段的代码是因为speedway官网给的例子时间戳不一样
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSSX");
//            Date date = new Date();
//            try {
//                date = sdf.parse(temp.getFirstSeenTimestamp());
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }
//            DateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            String result = df2.format(date);

//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//            long unixTime = Long.valueOf(temp.getFirstSeenTimestamp().substring(0,10));
//            String formattedDtm = Instant.ofEpochSecond(unixTime)
//                    .atZone(ZoneId.of("Asia/Tokyo"))
//                    .format(formatter);

            Date date = new Date((Long.valueOf(temp.getFirstSeenTimestamp()))/1000-errorTime*1000);
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String formattedDtm = df.format(date);

            RFIDLog tempOne = new RFIDLog();
            tempOne.setReaderName(data.getReader_name());
            tempOne.setMacAddress(data.getMac_address());
            tempOne.setAntennaPort(temp.getAntennaPort());
            tempOne.setEpc(temp.getEpc());
            tempOne.setPeakRssi(temp.getPeakRssi());
            tempOne.setStoreId(1);
            tempOne.setIsHeartBeat(temp.getIsHeartBeat());
            tempOne.setFirstSeenTimestamp(formattedDtm);
//            if(checkDataExists(String.valueOf(temp.getAntennaPort()),temp.getEpc())==true){
//                updateBatchData.add(tempOne);
//            }else {
//                insertBatchData.add(tempOne);
//            }
            insertBatchData.add(tempOne);
        }
        if(!insertBatchData.isEmpty()){
            SqlParameterSource[] beanSources = SqlParameterSourceUtils.createBatch(insertBatchData.toArray());
            String sql = "INSERT INTO rfid_log(store_id,mac_address,antenna_port,epc,first_seen_timestamp,peak_rssi,reader_name) VALUES (:storeId,:macAddress,:antennaPort,:epc,:firstSeenTimestamp,:peakRssi,:readerName);";
            namedParameterJdbcTemplate.batchUpdate(sql, beanSources);
        }
//        if(!updateBatchData.isEmpty()){
//            SqlParameterSource[] beanSources = SqlParameterSourceUtils.createBatch(updateBatchData.toArray());
//            String sql = "UPDATE RFID_LOG SET first_seen_timestamp = :firstSeenTimestamp WHERE antenna_port = :antennaPort AND epc = :epc;";
//            namedParameterJdbcTemplate.batchUpdate(sql, beanSources);
//        }
    }

//    public boolean checkResponseExcute(String beforeRequestTime,String requestTime,String antennePort){
//        String sql = "SELECT count(*) FROM RFID_LOG WHERE antenna_port = ? AND (first_seen_timestamp BETWEEN ? AND ?)";
//        int count = jdbcTemplate.queryForObject(sql, Integer.class, antennePort, epc, beforeRequestTime, requestTime);
//        if(count >=5) {
//            return true;
//        } else {
//            return false;
//        }
//    }
}
