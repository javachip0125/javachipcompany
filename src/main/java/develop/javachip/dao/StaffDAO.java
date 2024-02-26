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
        selectedStaff = new StaffDTO();

        selectedStaff.setStaffCode(rset.getInt("JAVACHIP_CODE"));
        selectedStaff.setStaffName(rset.getString("JAVACHIP_NAME"));
        selectedStaff.setWorkHour(rset.getInt("WORK_HOUR"));
        selectedStaff.setRemainVacation(rset.getInt("REMAIN_VACATION"));
        selectedStaff.setWorkStatus(rset.getString("WORK_STATUS"));
        selectedStaff.setArriveInfo(rset.getString("ARRIVE_INFO"));
        selectedStaff.setLeaveInfo(rset.getInt("LEAVE_INFO"));
      }
      System.out.println("사원번호 : " + selectedStaff.getStaffCode() + " | 이름 : " + selectedStaff.getStaffName() + " | 직책 : " + selectedStaff.getPosition());
      System.out.println("직책 : " + selectedStaff.getPosition());

      System.out.println("---------------------------------");

      System.out.println("남은연차 : " + selectedStaff.getRemainVacation());
      System.out.println("당일근무현황 : " + selectedStaff.getWorkStatus());
      System.out.println("출근정보 : " + selectedStaff.getArriveInfo());
      System.out.println("퇴근정보 : " + (selectedStaff.getLeaveInfo() == 0 ? "미퇴근" : "퇴근완료"));

    } catch (SQLException e) {
      throw new RuntimeException(e);
    } finally {
      close(con);
      close(pstmt);
      close(rset);
    }
  }
}
