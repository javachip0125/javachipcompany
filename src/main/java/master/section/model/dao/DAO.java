package master.section.model.dao;

import master.section.model.dto.DTO;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

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

    public int insertArriveInfo(Connection con, DTO newDto) {
        PreparedStatement pstmt = null;
        int result = 0;

        String query = prop.getProperty("insertArriveInfo");

        try {
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, newDto.getArriveInfo());

            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(pstmt);
        }

        return result;
    }

    public int insertLeaveInfo(Connection con, DTO newDto) {
        PreparedStatement pstmt = null;
        int result = 0;

        String query = prop.getProperty("insertLeaveInfo");

        try {
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, newDto.getLeaveInfo());

            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(pstmt);
        }

        return result;
    }
    public int selectAttendanceInfo(Connection con, DTO newdto){
        PreparedStatement pstmt = null;
        int result = 0;
        String query = prop.getProperty("selectAttendanceInfo");




    }
}