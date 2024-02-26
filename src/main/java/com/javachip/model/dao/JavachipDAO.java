package com.javachip.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

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


    public String userCheck(){
        String userId = null;
        String userPw = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rset = null;

        System.out.print("ID : ");
         userId = sc.nextLine();
        System.out.print("비밀번호 : ");
        userPw = sc.nextLine();

        if(userId.equals(useriid) && userPw.equals(userpwd)){
            System.out.println("로그인 성공!");
            logout = false;

        }else {
            System.out.println("아이디와 비밀번호가 틀렸습니다.");
        }
        return "ID";
    }





    public void systemLogout() {

        String yesOrNo = null ;
        System.out.print("로그아웃");

        yesOrNo = sc.nextLine();
        if (yesOrNo.equals("Y")) {
            System.out.println("로그아웃 성공");
            logout = true;

            if (yesOrNo.equals("N")){

                System.out.println("다시 돌아가겠습니다.");
                logout = false;
            }
        } else {
            System.out.println("잘못된 입력값입니다.");
        }
    }
    public class DeleteUser {

        // delete 함수

        public int Delete(String id) {

            String sql = "DELETE FROM USERDTO "
                    + "WHERE ID = '"+ id + "'";

            // 기본 셋팅
            Connection con = Connection.getConnection();
            PreparedStatement psmt = null;
            int count = 0;

            // sql문 확인
            System.out.println("sql = " + sql);
            // 데이터 삭제하기 SQL실행
            try {
                psmt = conn.prepareStatement(sql);

                count = psmt.executeUpdate();

            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } finally{
                try {

                    DBClose.close(psmt, conn, null);

                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }
            System.out.println("퇴사자 "+ + id+ "를 삭제했습니다.");
            return count;
        }




    }

}
