package com.FittingRoomServer.FittingRoomServer.Service;

import com.FittingRoomServer.FittingRoomServer.Dao.RFIDLogDao;
import com.FittingRoomServer.FittingRoomServer.Domain.RFIDLog;
import com.FittingRoomServer.FittingRoomServer.Domain.ResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClientService {

    @Autowired
    private RFIDLogDao rfidLogDao = new RFIDLogDao();

    public ResponseObject getProducts(String antennaPort){
        List<RFIDLog> temp= rfidLogDao.findByAntennaPort(antennaPort);
        ResponseObject result = new ResponseObject();
        if(temp.isEmpty()){
            result.setCode(404);
            result.setMessage("NOT FOUND DATA");
            result.setData((ArrayList) temp);
        } else {
            result.setCode(200);
            result.setMessage("OK");
            result.setData((ArrayList) temp);
        }
        return result;
    }
}
