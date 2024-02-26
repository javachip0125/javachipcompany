package master;

import master.section.model.dao.DAO;
import master.section.model.dto.DTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

import static master.common.JDBCTemplate.getConnection;

public class Arrive {
    Connection con = getConnection();
    Scanner sc = new Scanner(System.in);

    private boolean arriveleave;  // 초기값 false 상태 출근x(퇴근)

    DAO dao = new DAO();

    public String arriveCheck() {
        String arriveanswer = null;

        System.out.print("출근을 입력해주세요. : ");
        arriveanswer = sc.nextLine();
        if (arriveanswer.equals("출근")) {
            System.out.println("근태 정보가 출근으로 바뀌었습니다.");

            arriveleave = true;  // 출근 상태로 바뀐다.
            dao.insertArriveInfo(con, arriveanswer);
        } else {
            System.out.println("잘못된 입력값입니다.");
        }
        return arriveanswer;
    }

    public String leaveCheck() {
        PreparedStatement pstmt = null;
        String leavecheck = null;

        System.out.print("퇴근을 입력해주세요. : ");
        leavecheck = sc.nextLine();
        if (leavecheck.equals("퇴근")) {
            System.out.println("근태 정보가 퇴근으로 바뀌었습니다.");
            arriveleave = false;  // 퇴근 상태로 바뀐다.
            dao.insertLeaveInfo(con, arriveleave);

        } else {
            System.out.println("잘못된 입력값입니다.");
        }
        return leavecheck;
    }

    public String WorkStatusCheck() {
        String insertWorkStatus = null;
        System.out.print("당일 근무 현황을 입력해주세요 (재실/부재)");
        String ws = sc.nextLine();

        if (ws.equals("재실") || ws.equals("부재")) {
            dao.insertWorkStatus(con, ws);
            System.out.println("당일 근무 현황이 " + ws + "로 변경되었습니다.");
        }
        return ws;
    }

    public void AttendanceInfoCheck(){
        DAO dao = new DAO();
        List all = dao.selectAttendanceInfo(con);
        System.out.println("selectAttendanceInfo = " + all);
    }
}