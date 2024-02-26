package com.javachip.model.dao;

import java.io.Closeable;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.*;

import static com.javachip.common.JDBCTemplate.close;
import static com.javachip.common.JDBCTemplate.getConnection;


public class JavachipDAO {

    Scanner sc = new Scanner(System.in);
    boolean logout;

    private Properties prop = new Properties();

    public void JavachipDTO() {// void는 리턴값을 넣고 삭제
        try {
            prop.loadFromXML(new FileInputStream("src/main/java/com/javachip/mapper/menu-query.xml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    String useriid = "1234";
    String userpwd = "1534";


    public String userCheck() {
        String userId = null;
        String userPw = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rset = null;

        System.out.print("ID : ");
        userId = sc.nextLine();
        System.out.print("비밀번호 : ");
        userPw = sc.nextLine();

        if (userId.equals(useriid) && userPw.equals(userpwd)) {
            System.out.println("로그인 성공!");
            logout = false;

        } else {
            System.out.println("아이디와 비밀번호가 틀렸습니다.");
        }
        return "ID";
    }


    public void systemLogout() {

        String yesOrNo = null;
        System.out.print("로그아웃");

        yesOrNo = sc.nextLine();
        if (yesOrNo.equals("Y")) {
            System.out.println("로그아웃 성공");
            logout = true;

            if (yesOrNo.equals("N")) {

                System.out.println("다시 돌아가겠습니다.");
                logout = false;
            }
        } else {
            System.out.println("잘못된 입력값입니다.");
        }
    }


    public List<Map<Integer, String>> javachipStaffAttendanceList(Connection con) {

        Statement stmt = null;
        ResultSet rset = null;

        List<Map<Integer, String>> arrive = null;

        String query = prop.getProperty("javachipStaffAttendanceList");

        try {
            stmt = con.createStatement();
            rset = stmt.executeQuery(query);

            arrive = new ArrayList<>();

            while (rset.next()) {
                Map<Integer, String> attendanceInfo = new HashMap<>();

                arrive.add(attendanceInfo);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(rset);
            close(stmt);
        }

        return arrive;
    }
}



