package develop.javachip.menu;

import develop.javachip.dao.ManagerDAO;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ManagerMenu {

  ManagerDAO managerDAO = new ManagerDAO();

  Scanner scanner = new Scanner(System.in);

  //전체 사원 당일 or 해당요일 일정 조회
  public void showSchedule() {
    System.out.println("=================================");
    System.out.println("              일정조회            ");
    System.out.println("=================================");
    System.out.println("1. 오늘 일정\n2. 다음주 일정");
    System.out.println("=================================");

    while (true) {
      System.out.print("조회할 일정을 선택해주세요 : ");

      int menuNum = 0;
      String select = "";
      char answer;
      char answer2;

      try {
        menuNum = scanner.nextInt();

        switch (menuNum) {
          case 1:
            select = "금요일";
            managerDAO.selectAllTodaySchedule(select);

            while (true) {
              System.out.print("이전메뉴로 돌아갈까요?(N/Y) : ");

              try {
                answer = scanner.next().toUpperCase().charAt(0);
                if (answer == 'Y') {
                  System.out.println("이전메뉴로 돌아갑니다.");
                  break;
                }
              } catch (InputMismatchException e) { //char 타입 아닌 리터럴 입력시
                System.out.println("잘못 선택했습니다.\n다시 선택해주세요.");
                System.out.println("---------------------------------");
                scanner.nextLine(); // Scanner에 입력되어있던 내용 버퍼 비우기
              }
            }
            break;
          case 2:
            //다음주 일정 조회 메소드 호출
            managerDAO.selectAllNextSchedule();

            while (true) {
              System.out.print("일정을 다시 조회하시겠습니까?(N/Y) : ");

              try {
                answer2 = scanner.next().toUpperCase().charAt(0);
                if (answer2 == 'N') {
                  System.out.println("이전메뉴로 돌아갑니다.");
                  break;
                } else {
                  System.out.println("일정조회를 다시 진행합니다.");
                  managerDAO.selectAllNextSchedule(); // 다음주 일정 조회 메소드 호출
                }
              } catch (InputMismatchException e) { //char 타입 아닌 리터럴 입력시
                System.out.println("잘못 선택했습니다.\n다시 선택해주세요.");
                System.out.println("---------------------------------");
                scanner.nextLine(); // Scanner에 입력되어있던 내용 버퍼 비우기
              }
            }
            break;
        }

        if (menuNum > 2 || menuNum < 0) {
          System.out.println("잘못 선택했습니다.\n다시 선택해주세요.");
          System.out.println("---------------------------------");
        } else {
          break;
        }

      } catch (InputMismatchException e) { //menuNum에 정수가 아닌 타입으로 입력한 경우
        System.out.println("잘못 선택했습니다.\n다시 선택해주세요.");
        System.out.println("---------------------------------");
        scanner.nextLine(); // Scanner에 입력되어있던 내용 버퍼 비우기
      }
    } // 일정 선택


  }


}
