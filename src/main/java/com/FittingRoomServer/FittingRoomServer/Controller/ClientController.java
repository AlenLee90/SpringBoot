package com.FittingRoomServer.FittingRoomServer.Controller;

import com.FittingRoomServer.FittingRoomServer.Domain.ClientResponseObject;
import com.FittingRoomServer.FittingRoomServer.Domain.ResponseJSONObject;
import com.FittingRoomServer.FittingRoomServer.Service.ClientService;
import com.FittingRoomServer.FittingRoomServer.Service.HardwareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ClientController {

    @Autowired
    private ClientService clientService = new ClientService();

    @Autowired
    HardwareService hardwareService = new HardwareService();

    @RequestMapping(value="/v1/gu/japan/fittingroom/products",method = RequestMethod.GET)
    public ResponseJSONObject getProducts(@RequestParam("antennaPort") String antennaPort, @RequestParam("storeId") String storeId) {
        return clientService.getProducts(antennaPort,storeId);
//        List<ClientResponseObject> temp = new ArrayList<ClientResponseObject>();
//        ClientResponseObject tempOne = new ClientResponseObject();
//        tempOne.setProductId("000001");
//        tempOne.setColorCode("red");
//        tempOne.setProductName("shirt");
//        ClientResponseObject tempTwo = new ClientResponseObject();
//        tempTwo.setProductId("000002");
//        tempTwo.setColorCode("blue");
//        tempTwo.setProductName("shoes");
//        ClientResponseObject tempThree = new ClientResponseObject();
//        tempThree.setProductId("000003");
//        tempThree.setColorCode("yellow");
//        tempThree.setProductName("jeans");
//        ClientResponseObject tempFour = new ClientResponseObject();
//        tempFour.setProductId("000004");
//        tempFour.setColorCode("white");
//        tempFour.setProductName("hat");
//        ClientResponseObject tempFive = new ClientResponseObject();
//        tempFive.setProductId("000005");
//        tempFive.setColorCode("black");
//        tempFive.setProductName("pants");
//        temp.add(tempOne);
//        temp.add(tempTwo);
//        temp.add(tempThree);
//        temp.add(tempFour);
//        temp.add(tempFive);
//        ResponseJSONObject result = new ResponseJSONObject();
//        result.setCode(200);
//        result.setMessage("OK");
//        result.setData((ArrayList) temp);
//        return result;
    }

//    @RequestMapping(value="/iphone/test",method = RequestMethod.POST)
//    public ResponseJSONObject test(@RequestBody String temp) {
//        List<RFIDLog> data = new ArrayList<RFIDLog>();
//        RFIDLog rfidLog = new RFIDLog();
//        rfidLog.setStoreId(999);
//        rfidLog.setFirstSeenTimestamp("2018-12-03 15:25:07");
//        rfidLog.setReaderName("test2");
//        rfidLog.setPeakRssi(55);
//        rfidLog.setMacAddress("dsgsdg");
//        rfidLog.setAntennaPort(12);
//        rfidLog.setEpc("aab");
//        data.add(rfidLog);
//        return hardwareService.updateRFIDLog(data);
//    }
}
