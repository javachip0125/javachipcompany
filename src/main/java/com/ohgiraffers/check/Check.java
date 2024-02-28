package com.ohgiraffers.check;

import develop.javachip.dao.ManagerDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

import static develop.javachip.common.JDBCTemplate.getConnection;

public class Check {



    public static void main(String[] args) {

        Connection con = getConnection();

        ManagerDAO dao = new ManagerDAO();
//        dao.Check_or_employee();











    }
}
