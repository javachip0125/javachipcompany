package develop.javachip.menu;

import develop.javachip.dao.StaffDAO;
import develop.javachip.dto.StaffDTO;

import java.sql.Connection;
import java.util.InputMismatchException;
import java.util.Scanner;

import static develop.javachip.common.JDBCTemplate.getConnection;

public class StaffMenu {

  Connection con = getConnection();
  Scanner scanner = new Scanner(System.in);
  StaffDAO staffDAO = new StaffDAO();

  //직원 메인 화면 및 시작메뉴
  public void staffMainMenu(StaffDTO selectedDTO) {
    while (true) {

      // 전체 직원 근태정보 조회 내용 출력
      System.out.println();
      System.out.println("1. 출근하기");
      System.out.println("2. 일정등록");
      System.out.println("3. 퇴근하기");
      System.out.println("4. 로그아웃");
      System.out.println();
      System.out.println("=================================");
      System.out.print("메뉴를 선택하세요 : ");
      int menuNum = 0; // 입력한 메뉴 번호 저장
      try {
        menuNum = scanner.nextInt();

        switch (menuNum) {// 올바른 메뉴를 입력한 경우 해당 메뉴 메소드 호출
          case 1 : // 출근하기
            System.out.println("출근하기 메뉴입니다.");
            break;

          case 2 : // 일정등록
            System.out.println("일정등록 메뉴입니다.");
            registerSchedule(selectedDTO); // 일정등록 메소드 호출
            char answer;
            while (true) {
              System.out.print("일정 등록을 다시 진행하시겠습니까?(N/Y) : ");

              try {
                answer = scanner.next().toUpperCase().charAt(0);
                if (answer == 'N') {
                  System.out.println("이전메뉴로 돌아갑니다.");
                  break;
                } else {
                  System.out.println("일정등록을 다시 진행합니다.");
                  registerSchedule(selectedDTO); // 일정등록 메소드 호출
                }
              } catch (InputMismatchException e) { //char 타입 아닌 리터럴 입력시
                System.out.println("잘못 선택했습니다.\n다시 선택해주세요.");
                System.out.println("---------------------------------");
                scanner.nextLine(); // Scanner에 입력되어있던 내용 버퍼 비우기
              }
            }
            break;

          case 3 : // 퇴근하기
            System.out.println("퇴근하기 메뉴입니다.");
            break;

          case 4 : // 로그아웃
            System.out.println("로그아웃을 진행합니다.");
            break;

          default:// 입력한 메뉴 번호가 올바르지 않은 경우 다시 입력하도록
            System.out.println("메뉴를 잘못 선택했습니다. 다시 선택해주세요.");
            break;
        }

        if (menuNum == 4) {
          System.out.println("로그아웃이 완료되었습니다.");
          System.out.println("프로그램을 종료합니다.");
        }
      } catch (InputMismatchException e) { //menuNum에 정수가 아닌 타입으로 입력한 경우
        System.out.println("메뉴를 잘못 선택했습니다. 다시 선택해주세요.");
        scanner.nextLine(); // Scanner에 입력되어있던 내용 버퍼 비우기
      }
    }
  }


  //일정등록 메뉴
  public void registerSchedule(StaffDTO staffDTO) {
    while (true) {
      System.out.println("=================================");
      System.out.println("              일정등록             ");
      System.out.println("=================================");
      System.out.println("        Today : 2024/02/29       ");
      System.out.println("---------------------------------");
      System.out.println("              오늘 일정            ");
      System.out.println("---------------------------------");
      staffDAO.selectTodaySchedule(con, staffDTO);
      System.out.println("---------------------------------");
      System.out.println("          등록된 다음주 일정        ");
      System.out.println("---------------------------------");
      staffDAO.selectNextWeekSchedule(con, staffDTO);//등록되어있는 다음주 일정 조회
      System.out.println();
      System.out.println("=================================");
      System.out.println("1. 월요일\n2. 화요일\n3. 수요일\n4. 목요일\n5. 금요일");
      System.out.println("=================================");

      String select = ""; //선택한 등록 요일
      while (true) {
        System.out.print("일정 등록을 할 요일을 선택하세요 : ");
        Scanner scanner = new Scanner(System.in);
        int selectDay = 0;

        try {
          selectDay = scanner.nextInt();

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
          scanner.nextLine(); // Scanner에 입력되어있던 내용 버퍼 비우기
        }
      } // 요일 선택

      System.out.println(select + "을 선택하셨습니다.");
      System.out.println("=================================");
      System.out.println("1. 휴가\n2. 외근\n3. 출장\n4. 정상근무");
      System.out.println("=================================");

      String schedule = ""; //선택한 등록 일정
      while (true) {
        System.out.print(select + "에 등록할 일정을 선택하세요 : ");
        int selectNum = 0;

        try {
          selectNum = scanner.nextInt();
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
            }
            break;
          }
        } catch (InputMismatchException e) {
          //selectDay에 정수가 아닌 타입으로 입력한 경우
          System.out.println("일정을 잘못 선택했습니다.\n다시 선택해주세요.");
          System.out.println("---------------------------------");
          scanner.nextLine(); // Scanner에 입력되어있던 내용 버퍼 비우기
        }

      } // 일정 선택


      break;
    }

  }
}
