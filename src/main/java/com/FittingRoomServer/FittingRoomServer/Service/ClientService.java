package com.FittingRoomServer.FittingRoomServer.Service;

import com.FittingRoomServer.FittingRoomServer.Dao.RFIDLogDao;
import com.FittingRoomServer.FittingRoomServer.Domain.ClientResponseObject;
import com.FittingRoomServer.FittingRoomServer.Domain.ResponseJSONObject;
import com.FittingRoomServer.FittingRoomServer.Utils.ConstantsConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ClientService {

    @Autowired
    private RFIDLogDao rfidLogDao = new RFIDLogDao();

    public ResponseJSONObject getProducts(String antennaPort, String storeId){
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date dateOne = new Date(System.currentTimeMillis());
        Date dateTwo = new Date(System.currentTimeMillis()-(ConstantsConfig.SERVER_VERIFY_TIME+ ConstantsConfig.READER_UPDATE_INTERVAL));
        String endDate = df.format(dateOne);
        String beginDate = df.format(dateTwo);
        List<ClientResponseObject> temp= rfidLogDao.getClientResponseData(antennaPort,storeId,beginDate,endDate);
        ResponseJSONObject result = new ResponseJSONObject();
        if(temp.isEmpty()){
            result.setCode(ConstantsConfig.DATA_NOT_FOUND);
            result.setMessage("NOT FOUND DATA");
            result.setData((ArrayList) temp);
        } else {
            result.setCode(ConstantsConfig.EXECUTION_SUCCESS);
            result.setMessage("OK");
            result.setData((ArrayList) temp);
        }
        return result;
    }
}
