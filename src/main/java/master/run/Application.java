package master.run;

import master.Arrive;

import java.sql.Connection;
import java.util.Scanner;

import static master.common.JDBCTemplate.getConnection;

public class Application {

    public static void main(String[] args) {
    /* master */
        /* master */

        Connection con = getConnection();
        Arrive arrive = new Arrive();

        Scanner sc = new Scanner(System.in);

        System.out.println("1. 출근하기");
        System.out.println("2. 조회하기");
        System.out.println("3. 일정등록하기");
        System.out.println("4. 퇴근하기");
        System.out.print("메뉴를 골라주세요 : ");
        String answer = sc.nextLine();
        switch (answer){
            case "출근하기" : arrive.arriveCheck();
            case "조회하기" :
        }

    }
}