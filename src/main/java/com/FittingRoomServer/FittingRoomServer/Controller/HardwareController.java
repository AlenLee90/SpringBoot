package com.FittingRoomServer.FittingRoomServer.Controller;

import com.FittingRoomServer.FittingRoomServer.Domain.RFIDObject;
import com.FittingRoomServer.FittingRoomServer.Domain.ResponseJSONObject;
import com.FittingRoomServer.FittingRoomServer.Service.HardwareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HardwareController {

    @Autowired
    HardwareService hardwareService = new HardwareService();

    @RequestMapping(value="/v1/gu/japan/fittingroom/products",method = RequestMethod.POST)
    public ResponseJSONObject updateProducts(@RequestBody RFIDObject data){
        return hardwareService.updateProducts(data);
    }
}
