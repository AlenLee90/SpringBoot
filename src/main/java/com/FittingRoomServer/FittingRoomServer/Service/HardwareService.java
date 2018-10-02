package com.FittingRoomServer.FittingRoomServer.Service;

import com.FittingRoomServer.FittingRoomServer.Dao.RFIDLogDao;
import com.FittingRoomServer.FittingRoomServer.Domain.RFIDLog;
import com.FittingRoomServer.FittingRoomServer.Domain.ResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HardwareService {

    @Autowired
    private RFIDLogDao rfidLogDao = new RFIDLogDao();

    public ResponseObject updateProducts(List<RFIDLog> data){
        ResponseObject result = new ResponseObject();
        try {
            rfidLogDao.update(data);
            result.setCode(200);
            result.setMessage("SUCCEED");
        }catch (Exception e){
            result.setCode(400);
            result.setMessage("UPDATE OPERATATION FAILED");
        }
        return result;
    }
}
