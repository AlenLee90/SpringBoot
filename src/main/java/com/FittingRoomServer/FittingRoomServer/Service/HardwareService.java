package com.FittingRoomServer.FittingRoomServer.Service;

import com.FittingRoomServer.FittingRoomServer.Dao.RFIDLogDao;
import com.FittingRoomServer.FittingRoomServer.Domain.RFIDObject;
import com.FittingRoomServer.FittingRoomServer.Domain.ResponseJSONObject;
import com.FittingRoomServer.FittingRoomServer.Utils.ConstansConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class HardwareService {

    @Autowired
    private RFIDLogDao rfidLogDao = new RFIDLogDao();

    public ResponseJSONObject updateProducts(RFIDObject data){
        ResponseJSONObject result = new ResponseJSONObject();
        long errorTime = verifyAntennaport(data);
        try {
            rfidLogDao.update(data,errorTime);
            result.setCode(ConstansConfig.EXECUTION_SUCCESS);
            result.setMessage("SUCCEED");
        }catch (Exception e){
            result.setCode(ConstansConfig.INVALID_REQUEST);
            result.setMessage("UPDATE OPERATATION FAILED");
        }
        return result;
    }

    public long verifyAntennaport(RFIDObject data){
        Date date = new Date(Long.valueOf(data.getTag_reads().get(0).getFirstSeenTimestamp())/1000);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDtm = df.format(date);
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date dateTwo = new Date();
        try {
            dateTwo = format.parse(formattedDtm);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date dateThree = new Date();
        long seconds = (dateTwo.getTime()-dateThree.getTime())/1000;
        return seconds;
    }
}
