package com.example.ttok0801joinusexample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<User> listForBeanPropertyRowMapper(){

        String query = "SELECT * FROM user";
        return jdbcTemplate.query(query, new BeanPropertyRowMapper<User>(User.class));

    }


    public int insert(User user){
        String query = "INSERT INTO user(id,pw,email,username) VALUES(?,?,?,?)";
        System.out.println("---------------쿼리문은");
        System.out.println(query);
        return jdbcTemplate.update(query,user.getId(),user.getPw(),user.getEmail(),user.getUsername());

    }

}
