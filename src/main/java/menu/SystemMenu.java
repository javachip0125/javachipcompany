package menu;

import java.util.Scanner;

public class SystemMenu {
  public void loginMenu() {
    Scanner scanner = new Scanner(System.in);

    //시스템 처음 접속시 로그인 실행
    while (true) {

      System.out.println("[JAVACHIP 근태관리 시스템]");
      System.out.println("=======================");
      System.out.print("ID : ");
      String inputId = scanner.nextLine();
      System.out.print("PW : ");
      //비밀번호 입력시 console에 입력하는 내용이 노출됨 - *로 변경가능한 방법 있는지 검색필요
      String inputPw = scanner.nextLine();
      System.out.println("=======================");

      if (inputId.equals("abc") && inputPw.equals("1234")) {
        System.out.println(inputId + "회원님 로그인하셨습니다.");
        break;
      } else {
        System.out.println("inputId = " + inputId);
        System.out.println("inputPw = " + inputPw);
        System.out.println("아이디와 비밀번호가 일치하지 않거나 올바르지 않습니다.");
        System.out.println("다시 입력해주세요.");
      }
    }
  }
}
