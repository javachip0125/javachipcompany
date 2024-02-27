package develop.javachip.dao;

import develop.javachip.dto.ManagerDTO;

import java.sql.*;
import java.util.*;

import static develop.javachip.common.JDBCTemplate.close;
import static develop.javachip.common.JDBCTemplate.getConnection;

public class ManagerDAO {

    private Properties prop = new Properties();

    public void Check_or_employee() {

        Connection con = getConnection();

        PreparedStatement pstmt = null;
        ResultSet rset = null;
        ManagerDTO managerDTO = null;



        String query = "SELECT\n" +
                "      A.JAVACHIP_CODE,\n" +
                "      A.JAVACHIP_ID,\n" +
                "      A.JAVACHIP_NAME,\n" +
                "      A.POSITION,\n" +
                "      B.WORK_HOUR,\n" +
                "      B.REMAIN_VACATION,\n" +
                "      B.WORK_STATUS,\n" +
                "      B.ARRIVE_INFO,\n" +
                "      B.LEAVE_INFO\n" +
                "      FROM JAVACHIP_MEMBER A\n" +
                "      JOIN ATTENDANCE_INFO B ON A. JAVACHIP_CODE = B.JAVACHIP_CODE";
        System.out.println(query);


        try {
            pstmt = con.prepareStatement(query);
            rset = pstmt.executeQuery();


                                                                                                                                                       사원번호,사원이름,사원아이디,직책,총근무시간,당일근무현황,남은연차,출근정보,퇴근정보
        while (rset.next()) {




            System.out.println("금일 근태 현황 조회");
            System.out.println("=====================================");
            System.out.println("사원번호" +rset.getInt("JAVACHIP_CODE") + "  : " + "사원이름"+ "  " +rset.getString("JAVACHIP_NAME") );
            System.out.println("=====================================");
            System.out.println("현재근무현황"+"   " + rset.getString("WORK_STATUS"));
            System.out.println("출근정보" +"  "+rset.getString("ARRIVE_INFO") );
            System.out.println("퇴근정보" + (rset.getBoolean("LEAVE_INFO") == true ? "   퇴근" :  "  미퇴근"));




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


}

