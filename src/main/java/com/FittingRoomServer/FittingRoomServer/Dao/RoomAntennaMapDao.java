package com.FittingRoomServer.FittingRoomServer.Dao;

import com.FittingRoomServer.FittingRoomServer.Domain.RFIDLog;
import com.FittingRoomServer.FittingRoomServer.Domain.RoomAntennaMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class RoomAntennaMapDao {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public RoomAntennaMap findByMacAddressAndAntennaPort(String macAddress,int antennaPort){
        String sql = "SELECT * FROM TM_ROOM_ANTENNA_MAP WHERE mac_address = ? AND antenna_port = ?";
        RowMapper<RoomAntennaMap> rowMapper = new BeanPropertyRowMapper<RoomAntennaMap>(RoomAntennaMap.class);
        RoomAntennaMap temp = new RoomAntennaMap();
        try {
            temp = jdbcTemplate.queryForObject(sql, rowMapper, macAddress, antennaPort);
        }catch (Exception e){
            e.printStackTrace();
        }
        return temp;
    }
}
