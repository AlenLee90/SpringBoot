package com.FittingRoomServer.FittingRoomServer.Controller;

import com.FittingRoomServer.FittingRoomServer.Domain.ResponseJSONObject;
import com.FittingRoomServer.FittingRoomServer.Service.ClientService;
import com.FittingRoomServer.FittingRoomServer.Service.HardwareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ClientController {

    @Autowired
    private ClientService clientService = new ClientService();

    @Autowired
    HardwareService hardwareService = new HardwareService();

    @RequestMapping(value="/v1/gu/japan/fittingroom/products",method = RequestMethod.GET)
    public ResponseJSONObject getProducts(@RequestParam("antennaPort") String antennaPort, @RequestParam("storeId") String storeId) {
        return clientService.getProducts(antennaPort,storeId);
    }
}
