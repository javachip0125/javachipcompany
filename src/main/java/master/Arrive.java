package master;

import java.sql.Connection;
import java.util.Properties;
import java.util.Scanner;

public class Arrive {

    private boolean arriveleave;  // 초기값 false 상태 출근x(퇴근)

    Scanner sc = new Scanner(System.in);

    public void arriveCheck() {
        String arrivecheck = null;

        System.out.print("출근을 입력해주세요. : ");
        arrivecheck = sc.nextLine();
        if (arrivecheck.equals("출근")) {
            System.out.println("근태 정보가 출근으로 바뀌었습니다.");
            arriveleave = true;  // 출근 상태로 바뀐다.
        } else {
            System.out.println("잘못된 입력값입니다.");
        }
    }

    public void leaveCheck() {
        String leavecheck = null;

        System.out.print("퇴근을 입력해주세요. : ");
        leavecheck = sc.nextLine();
        if (leavecheck.equals("퇴근")) {
            System.out.println("근태 정보가 퇴근으로 바뀌었습니다.");
            arriveleave = false;  // 퇴근 상태로 바뀐다.
        } else {
            System.out.println("잘못된 입력값입니다.");
        }
    }
}