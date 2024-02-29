package develop.javachip.dao;

import develop.javachip.dto.StaffDTO;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import static develop.javachip.common.JDBCTemplate.close;


public class StaffDAO {
  private Properties prop = new Properties();

  public StaffDAO() {
    try {
      prop.loadFromXML(new FileInputStream("src/main/java/develop/javachip/mapper/staff-query.xml"));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  //로그인한 ID로 해당 직원의 JAVACHIP_MEMBER 테이블의 컬럼 정보를 모두 가져와서 StaffDTO에 set&get
  public StaffDTO selectStaff(Connection con, String id) {
      PreparedStatement pstmt = null;
      ResultSet rset = null;
      StaffDTO selectedStaff = null;

      String query = prop.getProperty("selectStaffName");

    try {
      pstmt = con.prepareStatement(query);
      pstmt.setString(1, id); //입력한 로그인 ID로 컬럼정보 조회
      rset = pstmt.executeQuery();

      if (rset.next()) {
        selectedStaff = new StaffDTO();

        selectedStaff.setStaffCode(rset.getInt("JAVACHIP_CODE"));
        selectedStaff.setStaffID(rset.getString("JAVACHIP_ID"));
        selectedStaff.setStaffPw(rset.getString("JAVACHIP_PW"));
        selectedStaff.setPosition(rset.getString("POSITION"));
        selectedStaff.setStaffName(rset.getString("JAVACHIP_NAME"));
      }

    } catch (SQLException e) {
      throw new RuntimeException(e);
    } finally {
      close(pstmt);
      close(rset);
    }
    return selectedStaff;
  }

  public void selectAttendanceInfo(Connection con, StaffDTO selectedStaff) {
    PreparedStatement pstmt = null;

    ResultSet rset = null;

    int staffCode = selectedStaff.getStaffCode();

    String query = prop.getProperty("selectAttendanceInfo");

    try {
      pstmt = con.prepareStatement(query);
      pstmt.setInt(1, staffCode);

      rset = pstmt.executeQuery();

      if (rset.next()) {

//        selectedStaff.setStaffCode(rset.getInt("JAVACHIP_CODE"));
//        selectedStaff.setStaffName(rset.getString("JAVACHIP_NAME"));
        selectedStaff.setWorkHour(rset.getInt("WORK_HOUR"));
        selectedStaff.setRemainVacation(rset.getInt("REMAIN_VACATION"));
        selectedStaff.setWorkStatus(rset.getString("WORK_STATUS"));
        selectedStaff.setArriveInfo(rset.getString("ARRIVE_INFO"));
        selectedStaff.setLeaveInfo(rset.getInt("LEAVE_INFO"));
      }
      System.out.println("사원번호 : " + selectedStaff.getStaffCode() + " | 이름 : " + selectedStaff.getStaffName());
      System.out.println("---------------------------------");
      System.out.println("직책 : " + selectedStaff.getPosition());
      System.out.println("---------------------------------");

      System.out.println("총 근무시간 : " + selectedStaff.getWorkHour() + " 시간 / 주");
      System.out.println("남은연차 : " + selectedStaff.getRemainVacation() + "일 / 15일");
      System.out.println("당일근무현황 : " + selectedStaff.getWorkStatus() );
      System.out.println("출근정보 : " + selectedStaff.getArriveInfo());
      System.out.println("퇴근정보 : " + (selectedStaff.getLeaveInfo() == 0 ? "미퇴근" : "퇴근완료"));

    } catch (SQLException e) {
      throw new RuntimeException(e);
    } finally {
      close(pstmt);
      close(rset);
    }
  }

  public int updateArriveInfo(Connection con, String arriveanswer, StaffDTO selectedDTO) {

    PreparedStatement pstmt = null;
    StaffDTO selectedStaff = null;
    int result1 = 0;

    String query = prop.getProperty("updateArriveInfo");

    try {

      pstmt = con.prepareStatement(query);
      pstmt.setString(1,arriveanswer);
      pstmt.setInt(2,selectedDTO.getStaffCode());

      result1 = pstmt.executeUpdate();
      System.out.println(result1);

    } catch (SQLException e) {
      throw new RuntimeException(e);
    } finally {
      close(pstmt);
    }

    return result1;
  }
  public int updateLeaveInfo(Connection con,Boolean arriveleave, StaffDTO selectedDTO) {
    PreparedStatement pstmt = null;
    int result2 = 0;

    String query = prop.getProperty("updateLeaveInfo");
    try {
      pstmt = con.prepareStatement(query);
      pstmt.setBoolean(1, arriveleave);
      pstmt.setInt(2, selectedDTO.getStaffCode());

      result2 = pstmt.executeUpdate();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    } finally {
      close(pstmt);
    }

    return result2;
  }
  public int updateWorkStatus(Connection con, String ws, StaffDTO selectedDTO) {
    PreparedStatement pstmt = null;

    int result = 0;

    String query = prop.getProperty("updateWorkHour");

    try {
      pstmt = con.prepareStatement(query);

      pstmt.setString(1, ws);
      pstmt.setInt(2, selectedDTO.getStaffCode());

      result = pstmt.executeUpdate();

    } catch (SQLException e) {
      throw new RuntimeException(e);
    } finally {
      close(pstmt);
    }
    return result;
  }

  //로그인한 직원의 당일 일정 조회
  public void selectTodaySchedule(Connection con, StaffDTO selectedStaff) {
    PreparedStatement pstmt = null;
    ResultSet rset = null;

    String query = prop.getProperty("selectTodaySchedule");

    try {
      pstmt = con.prepareStatement(query);
      pstmt.setInt(1, selectedStaff.getStaffCode());
      rset = pstmt.executeQuery();

      if (rset.next()) {
        System.out.println("[ 금요일 ] : " + ( rset.getString("DAY_SCHEDULE") == null ? "정상근무" : rset.getString("DAY_SCHEDULE")));
      }

    } catch (SQLException e) {
      throw new RuntimeException(e);
    } finally {
      close(pstmt);
      close(rset);
    }
  }

  //로그인한 직원의 다음주 일정 조회
  public void selectNextWeekSchedule(Connection con, StaffDTO selectedStaff) {
    PreparedStatement pstmt = null;
    ResultSet rset = null;

    String query = prop.getProperty("selectNextSchedule");

    try {
      pstmt = con.prepareStatement(query);
      pstmt.setInt(1, selectedStaff.getStaffCode());

      rset = pstmt.executeQuery();

      while (rset.next()) {
        System.out.println("[ " + rset.getString("DAYS") + " ] : " +( (rset.getString("DAY_SCHEDULE")) == null ? "정상근무" : (rset.getString("DAY_SCHEDULE"))));
        System.out.println("---------------------------------");
      }

    } catch (SQLException e) {
      throw new RuntimeException(e);
    } finally {
      close(pstmt);
      close(rset);
    }
  }

  //로그인한 직원의 일정등록
  public int updateNewSchedule(Connection con, String days, String schedule, StaffDTO selectedStaff) {
    //일정등록에 필요한 요일 정보 / 스케쥴 입력 정보
    PreparedStatement pstmt = null;
    int result = 0;

    String query = prop.getProperty("updateNewSchedule");

    try {
      pstmt = con.prepareStatement(query);
      pstmt.setString(1, schedule); // 입력한 스케쥴을 DAY_SCHADULE 컬럼 값으로 설정
      pstmt.setString(2, days); //입력한 요일값을 DAYS 컬럼 값으로 설정
      pstmt.setInt(3, selectedStaff.getStaffCode()); //로그인한 회원의 사번을 JAVACHIP_CODE 컬럼 값으로 설정

      result = pstmt.executeUpdate(); //해당 UPDATE 쿼리문 실행결과 = -1 || 1;

    } catch (SQLException e) {
      throw new RuntimeException(e);
    } finally {
      close(pstmt);
    }
    return result;
  }
}
