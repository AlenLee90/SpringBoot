package com.FittingRoomServer.FittingRoomServer.Controller;

import com.FittingRoomServer.FittingRoomServer.Domain.RFIDData;
import com.FittingRoomServer.FittingRoomServer.Domain.RFIDLog;
import com.FittingRoomServer.FittingRoomServer.Domain.ResponseObject;
import com.FittingRoomServer.FittingRoomServer.Service.HardwareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HardwareController {

    @Autowired
    HardwareService hardwareService = new HardwareService();

    @RequestMapping(value="/v1/gu/japan/fittingRoom/",method = RequestMethod.POST)
    public ResponseObject updateProducts(@RequestBody List<RFIDLog> data){
        return hardwareService.updateProducts(data);
    }
}
