package develop.javachip.menu;

import develop.javachip.dao.ManagerDAO;
import develop.javachip.dao.StaffDAO;
import develop.javachip.dto.ManagerDTO;
import develop.javachip.dto.StaffDTO;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.InputMismatchException;
import java.util.Scanner;

import static develop.javachip.common.JDBCTemplate.getConnection;

public class SystemMenu {

  Connection con = getConnection();

  private Scanner scanner = new Scanner(System.in);

  StaffDAO staffDAO = new StaffDAO();
  ManagerMenu managerMenu = new ManagerMenu();
  StaffMenu staffMenu = new StaffMenu();
  ManagerDTO managerDTO = new ManagerDTO();

  public void loginMenu() {
    //scanner = new Scanner(System.in);

    String inputId = ""; // ID 저장

    //시스템 처음 접속시 로그인 실행
    System.out.println("=================================");
    System.out.println("     [JAVACHIP 근태관리 시스템]     ");
    System.out.println("=================================");
    while (true) {
      System.out.print("ID : ");
      inputId = scanner.nextLine(); // 입력한 ID 저장
      System.out.print("PW : ");
      //비밀번호 입력시 console에 입력하는 내용이 노출됨 - *로 변경가능한 방법 있는지 검색필요
      String inputPw = scanner.nextLine();
      System.out.println("=================================");

      if (staffDAO.selectStaff(con, inputId) != null && staffDAO.selectStaff(con, inputId).getStaffPw().equals(inputPw)) { //입력한 ID/PW가 DB와 일치한다면
        //관리자 인지 아닌지 여부 확인
        // 로그인한 아이디에 따라 관리자 메뉴 와 일반직원 메뉴 관련 메소드 호출
          if (inputId.equals("USER07")) {// 관리자가 접속한 경우
            System.out.println(inputId + "회원님 로그인하셨습니다.");
            System.out.println("JAVACHIP 근태관리 관리자 메뉴로 이동합니다.");
            managerMenu();
          } else { //일반 직원이 접속한 경우
            System.out.println(inputId + "회원님 로그인하셨습니다.");
            System.out.println("직원 메뉴로 이동합니다.");
            staffMenu(staffDAO.selectStaff(con, inputId));
          }
        break;
      } else { // 입력한 ID가 DB에 존재하지 않는다면
        System.out.println("아이디와 비밀번호가 일치하지 않거나 올바르지 않습니다.");
        System.out.println("다시 입력해주세요.");
      }
    }
  }
  //출근하기


  //퇴근하기


  //관리자 메뉴
  public void managerMenu() {
    System.out.println("=================================");
    System.out.println("     [JAVACHIP 근태관리 시스템]     ");
    System.out.println("=================================");
    System.out.println("            [관리자 모드]          ");
    System.out.println("---------------------------------");
    System.out.println("        Today : 2024/02/29       ");
    System.out.println("---------------------------------");
    System.out.println("       오늘의 임직원 근태 현황       ");
    System.out.println("---------------------------------");

    managerMenu.managerMenu(managerDTO);
  }


  //직원 메뉴
  public void staffMenu(StaffDTO selectedDTO) {

    System.out.println("=================================");
    System.out.println("     [JAVACHIP 근태관리 시스템]     ");
    System.out.println("=================================");
    System.out.println("        Today : 2024/02/29       ");
    System.out.println("---------------------------------");
    System.out.println(selectedDTO.getStaffName() + "님 환영합니다.");
    System.out.println("---------------------------------");
    System.out.println("        Today : 2024/02/29       ");
    System.out.println("---------------------------------");
    System.out.println("          오늘의 근태 현황          ");
    System.out.println("=================================");
    staffDAO.selectAttendanceInfo(con, selectedDTO);

    //직원 메인 메뉴 호출
    staffMenu.staffMainMenu(selectedDTO);

  } //직원메뉴 END


}
