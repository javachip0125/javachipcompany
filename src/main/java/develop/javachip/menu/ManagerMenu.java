package develop.javachip.menu;

import develop.javachip.dao.ManagerDAO;

import java.sql.Connection;
import java.util.InputMismatchException;
import java.util.Scanner;

import static develop.javachip.common.JDBCTemplate.getConnection;

public class ManagerMenu {
    Connection con = getConnection();
    Scanner scanner = new Scanner(System.in);
    ManagerDAO managerDAO = new ManagerDAO();

    //관리자 메뉴
    public void managerMenu() {

        while (true) {
            // 전체 직원 근태정보 조회 내용 출력
            System.out.println("        |    메뉴 목록    |         ");
            System.out.println();
            System.out.println("1. 개별 직원 근태 현황");
            System.out.println("2. 일정정보");
            System.out.println("3. 출근정보");
            System.out.println("4. 당일 근무현황");
            System.out.println("5. 연차정보");
            System.out.println("6. 퇴근정보");
            System.out.println("7. 퇴사자 삭제");
            System.out.println("8. 로그아웃");
            System.out.println();
            System.out.println("=================================");
            System.out.print("메뉴를 선택하세요 : ");
            int menuNum = 0; // 입력한 메뉴 번호 저장
            try {
                menuNum = scanner.nextInt();

                switch (menuNum) {// 올바른 메뉴를 입력한 경우 해당 메뉴 메소드 호출
                    case 1: // 개별 직원 근태 현황
                        System.out.println("개별 직원 근태 현황 조회 메뉴입니다.");
                        managerDAO.EMPLOYEE_INQUIRY(con);
                        break;

                    case 2: // 일정정보
                        System.out.println("일정정보 메뉴입니다.");
                        showSchedule();
                        break;

                    case 3: // 출근정보
                        System.out.println("출근정보 메뉴입니다.");
                        managerDAO.selectArriveInfo(con);
                        break;

                    case 4: // 당일근무현황
                        System.out.println("당일근무현황 메뉴입니다.");
                        managerDAO.selectWorkStatus(con);
                        break;

                    case 5: // 연차정보
                        System.out.println("연차정보 메뉴입니다.");
                        managerDAO.remainVacation();
                        break;
                        
                    case 6: // 퇴근정보
                        System.out.println("퇴근정보 메뉴입니다.");
                        managerDAO.selectLeaveInfo();
                        break;

                    case 7: // 퇴사자 삭제
                        System.out.println("퇴사자 삭제 메뉴입니다..");
                        managerDAO.deleteMember();
                        break;

                    case 8: // 로그아웃
                        System.out.println("로그아웃을 진행합니다.");
                        break;

                    default:// 입력한 메뉴 번호가 올바르지 않은 경우 다시 입력하도록
                        System.out.println("메뉴를 잘못 선택했습니다. 다시 선택해주세요.");
                        break;
                }
                if (menuNum == 8){
                    System.out.println("로그아웃이 완료되었습니다.");
                }
            } catch (InputMismatchException e) { //menuNum에 정수가 아닌 타입으로 입력한 경우
                System.out.println("메뉴를 잘못 선택했습니다. 다시 선택해주세요.");
                scanner.nextLine(); // Scanner에 입력되어있던 내용 버퍼 비우기
            }
        }
    }
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
                        managerDAO.selectAllTodaySchedule(con, select);

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
                        managerDAO.selectAllNextSchedule(con);

                        while (true) {
                            System.out.print("일정을 다시 조회하시겠습니까?(N/Y) : ");

                            try {
                                answer2 = scanner.next().toUpperCase().charAt(0);
                                if (answer2 == 'N') {
                                    System.out.println("이전메뉴로 돌아갑니다.");
                                    break;
                                } else {
                                    System.out.println("일정조회를 다시 진행합니다.");
                                    managerDAO.selectAllNextSchedule(con); // 다음주 일정 조회 메소드 호출
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
        }
    }
}