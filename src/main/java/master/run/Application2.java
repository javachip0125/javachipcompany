package master.run;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import static develop.javachip.common.JDBCTemplate.close;
import static develop.javachip.common.JDBCTemplate.getConnection;

public class Application2 {
    public static void main(String[] args) {
        Connection con = getConnection();

        Properties prop = new Properties();
        PreparedStatement pstmt = null;
        int result = 0;

        try {
            prop.loadFromXML(new FileInputStream("src/main/java/master/mapper/menu-query.xml"));
            String query = prop.getProperty("updateWorkHour");
            pstmt = con.prepareStatement(query);
            System.out.println("query = " + query);
            System.out.println("pstmt = " + pstmt);

            pstmt.setString(1, "지각");
            System.out.println("pstmt.executeUpdate() = " + pstmt.executeUpdate());

            result = pstmt.executeUpdate();


        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(pstmt);
            close(con);
        }

        if (result > 0) {
            System.out.println("출근정보 업데이트 성공");
        } else {
            System.out.println("출근정보 업데이트 실패");
        }
    }
}
