package develop.javachip.dao;

import develop.javachip.dto.StaffDTO;

import java.io.Closeable;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.*;

import static develop.javachip.common.JDBCTemplate.close;

public class ManagerDAO {

    Scanner sc = new Scanner(System.in);
    boolean logout;

    private Properties prop = new Properties();

    public ManagerDAO() {// void는 리턴값을 넣고 삭제
        try {
            prop.loadFromXML(new FileInputStream("src/main/java/develop/javachip/mapper/manager-query.xml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void EMPLOYEE_INQUIRY(Connection con) {

        PreparedStatement pstmt = null;

        ResultSet rset = null;

        StaffDTO selectedEmp = null;

        Scanner sc = new Scanner(System.in);
        System.out.print("조회하실 사번을 입력해주세요 : ");
        int staffCodeAnswer = sc.nextInt();

        String query = prop.getProperty("EMPLOYEE_INQUIRY");


        try {

            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, staffCodeAnswer);
            rset = pstmt.executeQuery();

            if (rset.next()) {

                selectedEmp = new StaffDTO();

                selectedEmp.setStaffCode((rset.getInt("JAVACHIP_CODE")));
                System.out.println();
                System.out.println("---------------------------------");
                System.out.println("사원 번호 : " + rset.getString("JAVACHIP_CODE"));
                System.out.println("---------------------------------");
                selectedEmp.setWorkHour(rset.getInt("WORK_HOUR"));
                System.out.println(" - 총 근무 시간 : " + rset.getInt("WORK_HOUR"));

                selectedEmp.setRemainVacation(rset.getInt("REMAIN_VACATION"));
                System.out.println(" - 남은 연차 : " + rset.getInt("REMAIN_VACATION"));

                selectedEmp.setWorkStatus(rset.getString("WORK_STATUS"));
                System.out.println(" - 작업 현황 : " + rset.getString("WORK_STATUS"));

                selectedEmp.setArriveInfo(rset.getString("ARRIVE_INFO"));
                System.out.println(" - 출근 현황 : " + rset.getString("ARRIVE_INFO"));

                selectedEmp.setLeaveInfo(rset.getInt("LEAVE_INFO"));
                System.out.println(" - 퇴근 현황 : " + ((rset.getBoolean("LEAVE_INFO") == true) ? "퇴근함" : "퇴근안함"));

                selectedEmp.setWorkStatus(rset.getString("WORK_SCHEDULE"));
                System.out.println(" - 근무 현황 : " + ((rset.getString("WORK_SCHEDULE")) == null ? "정상출근" : "출장".equals((rset.getString("WORK_SCHEDULE"))) ? "출장" : "외근".equals((rset.getString("WORK_SCHEDULE"))) ? "외근" : "휴가"));
                System.out.println("---------------------------------");

            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(rset);
            close(pstmt);
        }
    }
    // 전체 직원 당일 근무 현황 조회
    public void selectWorkStatus(Connection con) {

        PreparedStatement pstmt = null;

        ResultSet rset = null;

        String query = prop.getProperty("selectWorkStatus");
        try {

            pstmt = con.prepareStatement(query);
            rset = pstmt.executeQuery();

            while (rset.next()) {

                System.out.println();
                System.out.println("=================================");
                System.out.println("사원 번호 : " + rset.getString("JAVACHIP_CODE"));
                System.out.println("사원 이름 : " + rset.getString("JAVACHIP_NAME"));
                System.out.println("사원 직책 : " + rset.getString("POSITION"));
                System.out.println("---------------------------------");
                System.out.print(" - 당일 근무현황 : " + rset.getString("WORK_STATUS"));

            }
            System.out.println();
            System.out.println("=================================");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(rset);
            close(pstmt);
        }
    }
}