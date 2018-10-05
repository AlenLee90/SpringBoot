package com.FittingRoomServer.FittingRoomServer.Service;

import com.FittingRoomServer.FittingRoomServer.Dao.RFIDLogDao;
import com.FittingRoomServer.FittingRoomServer.Domain.RFIDObject;
import com.FittingRoomServer.FittingRoomServer.Domain.ResponseJSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HardwareService {

    @Autowired
    private RFIDLogDao rfidLogDao = new RFIDLogDao();

    public ResponseJSONObject updateProducts(RFIDObject data){
        ResponseJSONObject result = new ResponseJSONObject();
        try {
            rfidLogDao.update(data);
            result.setCode(200);
            result.setMessage("SUCCEED");
        }catch (Exception e){
            result.setCode(400);
            result.setMessage("UPDATE OPERATATION FAILED");
        }
//        rfidLogDao.update(data);
        return result;
    }
}
