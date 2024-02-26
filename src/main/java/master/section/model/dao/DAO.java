package master.section.model.dao;

import com.mysql.cj.log.NullLogger;
import master.Arrive;
import master.section.model.dto.DTO;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.*;

import static master.common.JDBCTemplate.close;

public class DAO {

    private Properties prop = new Properties();


    public DAO() {
        try {
            prop.loadFromXML(new FileInputStream("src/main/java/master/mapper/menu-query.xml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public int insertArriveInfo(Connection con, String arriveanswer) {
        PreparedStatement pstmt = null;
        int result1 = 0;

        String query = prop.getProperty("attendanceInfo");

        try {
            pstmt = con.prepareStatement(query);
            pstmt.setString(1,arriveanswer);

            result1 = pstmt.executeUpdate();
            System.out.println(result1);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(pstmt);
            close(con);
        }

        return result1;
    }

    public int insertLeaveInfo(Connection con,Boolean arriveleave) {
        PreparedStatement pstmt = null;
        int result2 = 0;

        String query = prop.getProperty("insertLeaveInfo");

        try {
            pstmt = con.prepareStatement(query);
            pstmt.setBoolean(1, arriveleave);

            result2 = pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(pstmt);
            close(con);
        }

        return result2;
    }

    public int insertWorkStatus(Connection con, String ws) {
        PreparedStatement pstmt = null;

        int result = 0;

        String query = prop.getProperty("insertWorkStatus");

        try {
            pstmt = con.prepareStatement(query);

            pstmt.setString(1, "재실");

            result = pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(pstmt);
            close(con);
        }
        return result;
    }


    public List<DTO> selectAttendanceInfo(Connection con) {
        Statement stmt = null;
        ResultSet rset = null;
        DTO dto = null;

        List<DTO> attendanceInfo = null;
        String query = prop.getProperty("selectAttendanceInfo");

        try {
            stmt = con.createStatement();
            rset = stmt.executeQuery(query);
            attendanceInfo = new ArrayList<>();

            while (rset.next()){
               dto = new DTO();

               dto.setJavachipCode(rset.getInt("JAVACHIP_CODE"));
               dto.setWorkHour(rset.getInt("WORK_HOUR"));
               dto.setRemainVacation(rset.getInt("REMAIN_VACATION"));
               dto.setWorkStatus(rset.getNString("WORK_STATUS"));
               dto.setArriveInfo(rset.getNString("ARRIVE_INFO"));
               dto.setLeaveInfo(rset.getBoolean("LEAVE_INFO"));
               dto.setWorkSchedule(rset.getNString("WORK_SCHEDULE"));


               attendanceInfo.add(dto);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(rset);
            close(stmt);
            close(con);
        }
        return attendanceInfo;
    }
}
