package com.FittingRoomServer.FittingRoomServer.Service;

import com.FittingRoomServer.FittingRoomServer.Dao.RFIDLogDao;
import com.FittingRoomServer.FittingRoomServer.Dao.RoomAntennaMapDao;
import com.FittingRoomServer.FittingRoomServer.Domain.RFIDLog;
import com.FittingRoomServer.FittingRoomServer.Domain.RFIDObject;
import com.FittingRoomServer.FittingRoomServer.Domain.ResponseJSONObject;
import com.FittingRoomServer.FittingRoomServer.Utils.ConstansConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class HardwareService {

    @Autowired
    private RFIDLogDao rfidLogDao = new RFIDLogDao();
    @Autowired
    private RoomAntennaMapDao roomAntennaMapDao = new RoomAntennaMapDao();

    public ResponseJSONObject updateProducts(RFIDObject data){
        ResponseJSONObject result = new ResponseJSONObject();
//        long errorTime = verifyAntennaport(data);

        //        List<RFIDLog> updateBatchData = new ArrayList<RFIDLog>();
        List<RFIDLog> insertBatchData = new ArrayList<RFIDLog>();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentTime = df.format(new Timestamp(System.currentTimeMillis()));
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





//            Date date = new Date((Long.valueOf(temp.getFirstSeenTimestamp()))/1000-errorTime*1000);
//            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
//            String formattedDtm = df.format(new Date(Long.valueOf(temp.getFirstSeenTimestamp())));

            int storeId = roomAntennaMapDao.findByMacAddressAndAntennaPort(data.getMac_address(),temp.getAntennaPort()).getStoreId();

            RFIDLog tempOne = new RFIDLog();
            tempOne.setReaderName(data.getReader_name());
            tempOne.setMacAddress(data.getMac_address());
            tempOne.setAntennaPort(temp.getAntennaPort());
            tempOne.setEpc(temp.getEpc());
            tempOne.setPeakRssi(temp.getPeakRssi());
            tempOne.setStoreId(storeId);
            tempOne.setFirstSeenTimestamp(temp.getFirstSeenTimestamp());
            tempOne.setCreatedAt(currentTime);
            tempOne.setUpdatedAt(currentTime);
            tempOne.setCreatedBy(this.getClass().getSimpleName());
            tempOne.setUpdatedBy(this.getClass().getSimpleName());
//            if(checkDataExists(String.valueOf(temp.getAntennaPort()),temp.getEpc())==true){
//                updateBatchData.add(tempOne);
//            }else {
//                insertBatchData.add(tempOne);
//            }
            insertBatchData.add(tempOne);
        }

//        try {
//            rfidLogDao.update(insertBatchData);
//            result.setCode(ConstansConfig.EXECUTION_SUCCESS);
//            result.setMessage("SUCCEED");
//        }catch (Exception e){
//            result.setCode(ConstansConfig.INVALID_REQUEST);
//            result.setMessage("UPDATE OPERATATION FAILED");
//        }
        rfidLogDao.update(insertBatchData);
        return result;
    }

//    public long verifyAntennaport(RFIDObject data){
//        Date date = new Date(Long.valueOf(data.getTag_reads().get(0).getFirstSeenTimestamp())/1000);
//        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String formattedDtm = df.format(date);
//        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Date dateTwo = new Date();
//        try {
//            dateTwo = format.parse(formattedDtm);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        Date dateThree = new Date();
//        long seconds = (dateTwo.getTime()-dateThree.getTime())/1000;
//        return seconds;
//    }
}
