package develop.javachip.dao;

import develop.javachip.dto.ManagerDTO;
import develop.javachip.dto.StaffDTO;

import java.io.Closeable;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.*;

import static develop.javachip.common.JDBCTemplate.close;
import static develop.javachip.common.JDBCTemplate.getConnection;

public class ManagerDAO {
    private boolean arriveleave;
    Scanner sc = new Scanner(System.in);
    ManagerDTO managerDTO = new ManagerDTO();
    boolean logout;

    private Properties prop = new Properties();

    public ManagerDAO() {// void는 리턴값을 넣고 삭제
        try {
            prop.loadFromXML(new FileInputStream("src/main/java/develop/javachip/mapper/manager-query.xml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // 사번기준으로 정보 조회
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
                System.out.println("―――――――――――――――――――――");
                System.out.println("사원 번호 : " + rset.getString("JAVACHIP_CODE"));
                System.out.println("---------------------------------");
                selectedEmp.setWorkHour(rset.getInt("WORK_HOUR"));
                System.out.println(" - 총 근무 시간 : " + rset.getInt("WORK_HOUR") + "시간");

                selectedEmp.setRemainVacation(rset.getInt("REMAIN_VACATION"));
                System.out.println(" - 남은 연차 : " + rset.getInt("REMAIN_VACATION"));

                selectedEmp.setWorkStatus(rset.getString("WORK_STATUS"));
                System.out.println(" - 당일 근무현황 : " + rset.getString("WORK_STATUS"));

                selectedEmp.setArriveInfo(rset.getString("ARRIVE_INFO"));
                System.out.println(" - 출근정보 : " + rset.getString("ARRIVE_INFO"));

                selectedEmp.setLeaveInfo(rset.getInt("LEAVE_INFO"));
                System.out.println(" - 퇴근정보 : " + ((rset.getBoolean("LEAVE_INFO") == true) ? "퇴근함" : "퇴근안함"));

                selectedEmp.setWorkStatus(rset.getString("WORK_SCHEDULE"));
                System.out.println(" - 일정정보 : " + ((rset.getString("WORK_SCHEDULE")) == null ? "정상출근" : "출장".equals((rset.getString("WORK_SCHEDULE"))) ? "출장" : "외근".equals((rset.getString("WORK_SCHEDULE"))) ? "외근" : "휴가"));
                System.out.println("―――――――――――――――――――――");

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

    // 출근 정보 조회
    public void selectArriveInfo(Connection con) {
        PreparedStatement pstmt = null;

        ResultSet rset = null;

        String query = prop.getProperty("selectArriveInfo");
        try {
            pstmt = con.prepareStatement(query);
            rset = pstmt.executeQuery();

            System.out.println("―――――――――――――――――――――――――――――");
            System.out.println("│  사원번호  |  사원이름  |   직책   |   출근정보   │");
            System.out.println("―――――――――――――――――――――――――――――");

            while (rset.next()) {

                System.out.println("│     " + (rset.getInt("JAVACHIP_CODE") >= 10 ? rset.getInt("JAVACHIP_CODE") + "    " : rset.getInt("JAVACHIP_CODE") + "     ") + "|"
                        + "   " + (rset.getString("JAVACHIP_NAME").length() <= 2 ? rset.getString("JAVACHIP_NAME") + "    " : rset.getString("JAVACHIP_NAME") + "  ") + "|"
                        + "   " + (rset.getString("POSITION").length() > 2 ? rset.getString("POSITION") + "  " : rset.getString("POSITION") + "   ") + "|"
                        + "     " + ("출근".equals(rset.getString("ARRIVE_INFO")) ? "출근    " : "결근".equals(rset.getString("ARRIVE_INFO")) ? "결근    " : "미입력  ") + "|");

                System.out.println("―――――――――――――――――――――――――――――");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void Check_or_employee() {

        Connection con = getConnection();

        PreparedStatement pstmt = null;
        ResultSet rset = null;
        ManagerDTO managerDTO = null;

        String query = prop.getProperty("EMPLOYEE_INQUIRY");
        try {
            pstmt = con.prepareStatement(query);
            rset = pstmt.executeQuery();

            while (rset.next()) {

                System.out.println("금일 근태 현황 조회");
                System.out.println("=====================================");
                System.out.println("사원번호" + rset.getInt("JAVACHIP_CODE") + "  : " + "사원이름" + "  " + rset.getString("JAVACHIP_NAME"));
                System.out.println("=====================================");
                System.out.println("현재근무현황" + "   " + rset.getString("WORK_STATUS"));
                System.out.println("출근정보" + "  " + rset.getString("ARRIVE_INFO"));
                System.out.println("퇴근정보" + (rset.getBoolean("LEAVE_INFO") == true ? "   퇴근" : "  미퇴근"));

            }
            System.out.println("조회내용  :  " + managerDTO);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(con);
            close(rset);
            close(pstmt);
        }
    }

    public void selectLeaveInfo() {

        Connection con = getConnection();

        PreparedStatement pstmt = null;
        ResultSet rset = null;
        ManagerDTO managerDTO = null;

        String query = prop.getProperty("selectLeaveInfo");

        try {
            pstmt = con.prepareStatement(query);
            rset = pstmt.executeQuery();
            System.out.println("―――――――――――――――――――――――――――");
            System.out.println("│  사원번호  |  사원이름  |   직책   |   일정   │");
            System.out.println("―――――――――――――――――――――――――――");
            while (rset.next()) {

                System.out.println("│     " + (rset.getInt("JAVACHIP_CODE") >= 10 ? rset.getInt("JAVACHIP_CODE") + "    " : rset.getInt("JAVACHIP_CODE") + "     ") + "|"
                        + "   " + (rset.getString("JAVACHIP_NAME").length() <= 2 ? rset.getString("JAVACHIP_NAME") + "    " : rset.getString("JAVACHIP_NAME") + "  ") + "|"
                        + "   " + (rset.getString("POSITION").length() > 2 ? rset.getString("POSITION") + "  " : rset.getString("POSITION") + "   ") + "|"
                        + (rset.getBoolean("LEAVE_INFO") == true ? "   퇴근   |" : "  미퇴근  |"));
                System.out.println("―――――――――――――――――――――――――――");


            }
            System.out.println("---------------------------------");
        } catch (SQLException e) {
            throw new RuntimeException(e);


        } finally {
            close(con);
            close(pstmt);
            close(rset);
        }
    }

    public void deleteMember() {
        Connection con = getConnection();
        System.out.println("―――――――― 퇴사자 명단 ――――――――");
        System.out.println("|  사원번호  |  사원이름  |    직  책    |");
        System.out.println("|    10    |   유재석   |     부장     |");
        System.out.println("|    13    |   김종국   |     인턴     |");
        System.out.println("|    15    |   송지효   |     사원     |");
        System.out.println("―――――――――――――――――――――――");

        System.out.print("삭제할 직원의 사원번호을 입력해주세요. :");
        int memberNum = sc.nextInt();

        PreparedStatement pstmt = null;
        PreparedStatement pstmt1 = null;

        int result = 0;
        int result1 = 0;

        String query = prop.getProperty("deleteMember");
        String query1 = prop.getProperty("deleteJavachipMember");

        System.out.println("사원번호 " + memberNum + "번의 정보가 삭제되었습니다.");
        System.out.println("―――――――――――――――――――――――");

        try {
            pstmt = con.prepareStatement(query);
            pstmt1 = con.prepareStatement(query1);

            pstmt.setInt(1, memberNum);
            pstmt1.setInt(1, memberNum);

            result = pstmt.executeUpdate();
            result1 = pstmt1.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void remainVacation() {

        Connection con = getConnection();
        PreparedStatement pstmt = null;
        ResultSet rset = null;

        String query = prop.getProperty("remainVacation");

        try {
            pstmt = con.prepareStatement(query);
            rset = pstmt.executeQuery();

            System.out.println("―――――――――――――――――――――――――――――");
            System.out.println("│  사원번호  |  사원이름  |   직책   |  남은 연차  │");
            System.out.println("―――――――――――――――――――――――――――――");

            while (rset.next()) {
                System.out.println("│     " + (rset.getInt("JAVACHIP_CODE") >= 10 ? rset.getInt("JAVACHIP_CODE") + "    " : rset.getInt("JAVACHIP_CODE") + "     ") + "|"
                        + "   " + (rset.getString("JAVACHIP_NAME").length() <= 2 ? rset.getString("JAVACHIP_NAME") + "    " : rset.getString("JAVACHIP_NAME") + "  ") + "|"
                        + "   " + (rset.getString("POSITION").length() > 2 ? rset.getString("POSITION") + " " : rset.getString("POSITION") + "   ") + "|"
                        + "     " + (rset.getInt("REMAIN_VACATION") >= 10 ? rset.getInt("REMAIN_VACATION") + "    " : rset.getInt("REMAIN_VACATION") + "     ") + "│");
                System.out.println("―――――――――――――――――――――――――――――");

            }

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            close(con);
            close(pstmt);
            close(rset);
        }
    }

    //관리자 > 모든 직원 오늘 일정 확인
    public void selectAllTodaySchedule(Connection con, String dayName) {
        //해당 요일 매개변수로 모든 직원의 해당 요일 일정 확인
        PreparedStatement pstmt = null;
        ResultSet rset = null;

        String query = prop.getProperty("selectTodaySchedule");

        try {
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, dayName);
            pstmt.setString(2, dayName);
            rset = pstmt.executeQuery();
            System.out.println();
            System.out.println("[전체 사원의 " + dayName + " 일정 목록]");
            System.out.println("―――――――――――――――――――――――――――");
            System.out.println("│  사원번호  |  사원이름  |   직책   |   일정   │");
            System.out.println("―――――――――――――――――――――――――――");

            while (rset.next()) {

                System.out.println("│     " + (rset.getInt("JAVACHIP_CODE") >= 10 ? rset.getInt("JAVACHIP_CODE") + "    " : rset.getInt("JAVACHIP_CODE") + "     ") + "|"
                        + "   " + (rset.getString("JAVACHIP_NAME").length() <= 2 ? rset.getString("JAVACHIP_NAME") + "    " : rset.getString("JAVACHIP_NAME") + "  ") + "|"
                        + "   " + (rset.getString("POSITION").length() > 2 ? rset.getString("POSITION") + " " : rset.getString("POSITION") + "   ") + "|"
                        + (rset.getString(dayName) == null ? ("  정상근무 ") : "   " + rset.getString(dayName) + "   ") + "│"
                );
                System.out.println("--------------------------------------------");

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(pstmt);
            close(rset);
        }

    }

    //관리자 > 모든 직원 다음주 일정 확인
    public void selectAllNextSchedule(Connection con) {
        //해당 요일 매개변수로 모든 직원의 해당 요일 일정 확인
        PreparedStatement pstmt = null;
        ResultSet rset = null;

        String query1 = prop.getProperty("selectEmployeeInfo");

        try {
            pstmt = con.prepareStatement(query1);
            rset = pstmt.executeQuery();
            System.out.println();
            System.out.println("[전체 사원의 다음주 일정 목록]");
            System.out.println("―――――――――――――――――――――――――――――――――――――――――――――――――――――――――");
            System.out.println("│  사원번호  |  사원이름  |   직책   |   월요일   |   화요일   |   수요일   |   목요일   |   금요일   │");
            System.out.println("―――――――――――――――――――――――――――――――――――――――――――――――――――――――――");

            while (rset.next()) {
                System.out.println("│     " + (rset.getInt("JAVACHIP_CODE") >= 10 ? rset.getInt("JAVACHIP_CODE") + "    " : rset.getInt("JAVACHIP_CODE") + "     ") + "|"
                        + "   " + (rset.getString("JAVACHIP_NAME").length() <= 2 ? rset.getString("JAVACHIP_NAME") + "    " : rset.getString("JAVACHIP_NAME") + "  ") + "|"
                        + "   " + (rset.getString("POSITION").length() > 2 ? rset.getString("POSITION") + " " : rset.getString("POSITION") + "   ") + "|"
                        + (rset.getString("DAY_SCHEDULE") == null ? ("  정상근무  ") : "    " + rset.getString("DAY_SCHEDULE") + "    ") + "|"
                        + (rset.getString("DAY_SCHEDULE") == null ? ("  정상근무  ") : "    " + rset.getString("DAY_SCHEDULE") + "    ") + "|"
                        + (rset.getString("DAY_SCHEDULE") == null ? ("  정상근무  ") : "    " + rset.getString("DAY_SCHEDULE") + "    ") + "|"
                        + (rset.getString("DAY_SCHEDULE") == null ? ("  정상근무  ") : "    " + rset.getString("DAY_SCHEDULE") + "    ") + "|"
                        + (rset.getString("DAY_SCHEDULE") == null ? ("  정상근무  ") : "    " + rset.getString("DAY_SCHEDULE") + "    ") + "│"
                );

                System.out.println("-------------------------------------------------------------------------------------------");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(pstmt);
            close(rset);
        }

    }

    // 직원의 출퇴근 정보 변경
    public int updateArriveInfo(Connection con) {

        System.out.println(" 출근을 입력해주세요 :");
        String arrive = sc.nextLine();
        int result1 = 0;
        if (arrive.equals("출근")) {
            arriveleave = false;  // 출근 상태로 바뀐다.
            System.out.println("근태 정보가 출근으로 바뀌었습니다.");
            PreparedStatement pstmt = null;

            String query = prop.getProperty("updateArriveInfo");

            try {

                pstmt = con.prepareStatement(query);
                pstmt.setString(1, arrive);

                result1 = pstmt.executeUpdate();
                System.out.println(result1);

            } catch (SQLException e) {
                throw new RuntimeException(e);
            } finally {
                close(pstmt);
            }
        } else {
            System.out.println("잘못된 입력값입니다.");
        }

        return result1;
    }

    public int updateLeaveInfo(Connection con) {

        System.out.println(" 퇴근을 입력해주세요 :");
        String leave = sc.nextLine();
        int result2 = 0;
        if (leave.equals("퇴근")) {
            System.out.println("근태 정보가 퇴근으로 바뀌었습니다.");
            arriveleave = true;
            PreparedStatement pstmt = null;

            String query = prop.getProperty("updateLeaveInfo");
            try {
                pstmt = con.prepareStatement(query);
                pstmt.setBoolean(1, arriveleave);

                result2 = pstmt.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } finally {
                close(pstmt);
            }

        }else {
            System.out.println("잘못된 입력값입니다.");
        }
        return result2;
    }
}