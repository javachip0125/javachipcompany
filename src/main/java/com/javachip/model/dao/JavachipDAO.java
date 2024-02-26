package com.javachip.model.dao;

import java.io.Closeable;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.*;

import static com.javachip.common.JDBCTemplate.getConnection;
import static jdk.internal.net.http.common.Utils.close;


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

        Connection con = getConnection();

        /* 쿼리문을 저장하고 실행하는 기능을 하는 용도의 인터페이스 */
        Statement stmt = null;

        /* select 결과집합을 받아 올 용도의 인터페이스 */
        ResultSet rset = null;

        try {
            /* connection을 이용하여 statement 인스턴스 생성 */
            stmt = con.createStatement();

            rset = stmt.executeQuery("SELECT JAVACHIP_CODE FROM JAVACHIP_MEMBER");

            while (rset.next()) {
                /* next() : ResultSet의 커서 위치를 하나 내리면서 행이 존재하면 true, 존재하지 않으면 false를 반환 */
                System.out.println(rset.getString("JAVACHIP_CODE") + ", " + rset.getString("JAVACHIP_MEMBER"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(rset);
            close(stmt);
            close(con);
        }
    }
}


