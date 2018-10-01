package com.FittingRoomServer.FittingRoomServer.Controller;

import com.FittingRoomServer.FittingRoomServer.Model.User;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class HelloController {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @RequestMapping("/")
    public List<User> getProducts() {
        RowMapper<User> rowMapper = new BeanPropertyRowMapper<User>(User.class);
        String sql = "select * from raid";
        List<User> temp = jdbcTemplate.query(sql, rowMapper);
//        User result = new User();
//        result.setId(temp.get(0).getId());
//        result.setName(temp.get(0).getName());
//        Map<String,Object> result = new HashMap<String, Object>();
//        result.put("id",temp.get(0).getId());
//        result.put("name",temp.get(0).getName());
        return temp;
    }

    @RequestMapping("/updateData")
    @ResponseBody
    public ArrayList<User> login(@RequestBody User request) {
        ArrayList<User> result = new ArrayList<User>();
//        RowMapper<User> rowMapper=new BeanPropertyRowMapper<User>(User.class);
//        String sql="select id,name,deptid from user";
//        List<Demo> users= jdbcTemplate.query(sql, rowMapper);
//        User user= jdbcTemplate.queryForObject(sql, rowMapper);
//        int count= jdbcTemplate.queryForObject(sql, Integer.class);
        for(int a = 0;a < 5;a++) {
            User user = new User();
            user.setId(a+1);
            user.setName(request.getId()+":"+request.getName());
            result.add(user);
        }
        return result;
    }
}
