package master;

import com.javachip.common.JDBCTemplate;
import com.javachip.model.dao.JavachipDAO;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import static com.javachip.common.JDBCTemplate.getConnection;

public class Application {

    public static void main(String[] args) {


        Connection con = getConnection();

            JavachipDAO javachipDAO = new JavachipDAO();

        List<Map<Integer, String>> arrive = javachipDAO.javachipStaffAttendanceList(con);

        for (Map<Integer, String> attendanceInfo : arrive) {
            System.out.println(attendanceInfo);
        }
//        List<Map<Integer, String>> categoryList = registDAO.selectAllCategory(con);
//
//        for (Map<Integer, String> category : categoryList) {
//            System.out.println(category);
//        }


    }
}
