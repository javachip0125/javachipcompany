package master;

import com.javachip.common.JDBCTemplate;
import com.javachip.model.dao.JavachipDAO;

import java.sql.Connection;

import static com.javachip.common.JDBCTemplate.getConnection;

public class Application {

    public static void main(String[] args) {
    /* master */
        /* master */

        Connection con = getConnection();

            JavachipDAO javachipDAO = new JavachipDAO();
//            javachipDAO.Check_all_employees(con);




    }
}
