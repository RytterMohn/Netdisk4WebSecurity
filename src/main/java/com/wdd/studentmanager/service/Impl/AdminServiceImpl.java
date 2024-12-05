package com.wdd.studentmanager.service.Impl;

import com.wdd.studentmanager.domain.Admin;
import com.wdd.studentmanager.mapper.AdminMapper;
import com.wdd.studentmanager.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.sql.*;
import java.util.ResourceBundle;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public Admin findByAdmin(Admin admin) {
//        return adminMapper.findByAdmin(admin);
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/studentmanager?useSSL=false&setUnicode=true&characterEncoding=utf8&useAffectedRows=true";
        String user = "root";
        String password = "weng2023";

        Connection conn = null;
        Statement stmt = null;
        try {
//            Class.forName(driver);

            conn = DriverManager.getConnection(url,user,password);

            stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery("select * from s_admin where username = " + admin.getUsername() + " and password = " + admin.getPassword());
            Admin readmin = new Admin();
            while(rs.next()){
                readmin.setUsername(rs.getString(1));
                readmin.setPassword(rs.getString(2));
            }
            return readmin;
        } catch(SQLException e){
            e.printStackTrace();
        }
        finally {
            if(conn != null) {
                try {
                    conn.close();
                } catch(SQLException e){
                    e.printStackTrace();
                }
            }
            if(stmt != null) {
                try {
                    stmt.close();
                } catch(SQLException e){
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    @Override
    public int editPswdByAdmin(Admin admin) {
        return adminMapper.editPswdByAdmin(admin);
    }

}

