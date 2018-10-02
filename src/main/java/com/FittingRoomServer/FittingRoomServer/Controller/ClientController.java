package com.FittingRoomServer.FittingRoomServer.Controller;

import com.FittingRoomServer.FittingRoomServer.Domain.ResponseObject;
import com.FittingRoomServer.FittingRoomServer.Service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ClientController {

    @Autowired
    private ClientService clientService = new ClientService();

    @RequestMapping(value="/v1/gu/japan/fittingRoom/{antennaPort}/",method = RequestMethod.GET)
    public ResponseObject getProducts(@PathVariable String antennaPort) {
        return clientService.getProducts(antennaPort);
    }

}
