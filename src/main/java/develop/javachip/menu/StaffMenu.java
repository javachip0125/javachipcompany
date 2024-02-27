package develop.javachip.menu;

import develop.javachip.dao.StaffDAO;
import develop.javachip.dto.StaffDTO;

import java.sql.Connection;
import java.util.InputMismatchException;
import java.util.Scanner;

import static develop.javachip.common.JDBCTemplate.getConnection;

public class StaffMenu {

  Connection con = getConnection();
  Scanner sc = new Scanner(System.in);
  StaffDAO staffDAO = new StaffDAO();

  //일정등록 메뉴
  public void registerSchedule(StaffDTO staffDTO) {

    System.out.println("=================================");
    System.out.println("              일정등록             ");
    System.out.println("=================================");
    System.out.println("        Today : 2024/02/29       ");
    System.out.println("---------------------------------");
    System.out.println("              오늘 일정            ");
    System.out.println("---------------------------------");
    System.out.println("          등록된 다음주 일정        ");
    System.out.println("---------------------------------");

    //등록되어있는 다음주 일정 조회
    System.out.println("---------------------------------");
    System.out.println();
    System.out.println("=================================");
    System.out.println("1. 월요일\n2. 화요일\n3. 수요일\n4. 목요일\n5. 금요일");
    System.out.println("=================================");

    String select = ""; //선택한 등록 요일
    while (true) {
      System.out.print("일정 등록을 할 요일을 선택하세요 : ");
      Scanner sc = new Scanner(System.in);
      int selectDay = 0;

      try {
        selectDay = sc.nextInt();

        switch (selectDay) {
          case 1:
            select = "월요일";
            break;
          case 2:
            select = "화요일";
            break;
          case 3:
            select = "수요일";
            break;
          case 4:
            select = "목요일";
            break;
          case 5:
            select = "금요일";
            break;
        }

        if (selectDay > 5 || selectDay < 0) {
          System.out.println("요일을 잘못 선택했습니다.\n다시 선택해주세요.");
          System.out.println("---------------------------------");
        } else {
          break;
        }

      } catch (InputMismatchException e) { //selectDay에 정수가 아닌 타입으로 입력한 경우
        System.out.println("요일을 잘못 선택했습니다.\n다시 선택해주세요.");
        System.out.println("---------------------------------");
        sc.nextLine(); // Scanner에 입력되어있던 내용 버퍼 비우기
      }
    }

    System.out.println(select + "을 선택하셨습니다.");
    System.out.println("=================================");
    System.out.println("1. 휴가\n2. 외근\n3. 출장\n4. 정상근무");
    System.out.println("=================================");

    String schedule = ""; //선택한 등록 일정
    while (true) {
      System.out.print(select + "에 등록할 일정을 선택하세요 : ");
      int selectNum = 0;

      try {
        selectNum = sc.nextInt();
        switch (selectNum) {
          case 1 :
            schedule = "휴가";
            break;
          case 2 :
            schedule = "외근";
            break;
          case 3 :
            schedule = "출장";
            break;
          case 4 :
            schedule = "정상근무";
            break;

        }

        if (selectNum > 4 || selectNum < 0) {
          System.out.println("선택할수 없는 일정입니다.\n다시 일정을 선택해주세요.");
          System.out.println("---------------------------------");
        } else {
          if (staffDAO.updateNewSchedule(con, select, schedule, staffDTO) < 0) {
            System.out.println("다음주 일정 등록에 실패했습니다. 다시 시도해주세요.");
          } else {
            System.out.println("다음주 [" + select  + "]" + "[" + schedule + "]" + " 일정 등록을 완료했습니다.");
            break;
          }
        }
      } catch (InputMismatchException e) {
        //selectDay에 정수가 아닌 타입으로 입력한 경우
        System.out.println("일정을 잘못 선택했습니다.\n다시 선택해주세요.");
        System.out.println("---------------------------------");
        sc.nextLine(); // Scanner에 입력되어있던 내용 버퍼 비우기
      }

    }


  }
}
