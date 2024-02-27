package com.javachip.model.dao;

import com.javachip.model.dto.JavachipDTO;

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

    public JavachipDAO() {// void는 리턴값을 넣고 삭제
        try {
            prop.loadFromXML(new FileInputStream("src/main/java/com/javachip/mapper/menu-query.xml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void EMPLOYEE_INQUIRY(Connection con) {

        PreparedStatement pstmt = null;

        ResultSet rset = null;

        JavachipDTO selectedEmp = null;

        Scanner sc = new Scanner(System.in);
        System.out.print("조회하실 사번을 입력해주세요 : ");
        int javachipCode = sc.nextInt();

        String query = prop.getProperty("EMPLOYEE_INQUIRY");
        System.out.println(query);


        try {

            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, javachipCode);
            System.out.println(pstmt);
            rset = pstmt.executeQuery();

            if (rset.next()) {

                selectedEmp = new JavachipDTO();





                selectedEmp.setJavachipCode(rset.getString("JAVACHIP_CODE"));
                System.out.print("[ 사원 번호 : " + rset.getString("JAVACHIP_CODE") + ", ");

                selectedEmp.setWorkHour(rset.getInt("WORK_HOUR"));
                System.out.print("총 근무 시간 : " + rset.getInt("WORK_HOUR") + ", ");

                selectedEmp.setRemainVacation(rset.getInt("REMAIN_VACATION"));
                System.out.print("남은 연차 : " + rset.getInt("REMAIN_VACATION") + ", ");

                selectedEmp.setWorkStatus(rset.getString("WORK_STATUS"));
                System.out.print("작업 현황 : " + rset.getString("WORK_STATUS") + ", ");

                selectedEmp.setArriveInfo(rset.getString("ARRIVE_INFO"));
                System.out.print("출근 현황 : " + rset.getString("ARRIVE_INFO") + ", ");

                selectedEmp.setLeaveInfo(rset.getBoolean("LEAVE_INFO"));
                System.out.print("퇴근 현황 : " + ((rset.getBoolean("LEAVE_INFO") == true)?  "퇴근함, " : "퇴근안함, " ));

                selectedEmp.setWork_schedule(rset.getString("WORK_SCHEDULE"));
                System.out.print("근무 현황 : " + ((rset.getString("WORK_SCHEDULE"))==null? "정상출근" : "출장".equals((rset.getString("WORK_SCHEDULE")))? "출장" : "외근".equals((rset.getString("WORK_SCHEDULE")))? "외근" : "휴가" + " ]"));

            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(rset);
            close(pstmt);
        }


    }
}