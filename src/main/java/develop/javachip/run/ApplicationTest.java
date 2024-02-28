package develop.javachip.run;

import develop.javachip.menu.SystemMenu;

public class ApplicationTest {
  public static void main(String[] args) {

    SystemMenu systemMenu = new SystemMenu();

    systemMenu.loginMenu();
  }
}

//Caused by: java.sql.SQLSyntaxErrorException: You have an error in your SQL syntax; check the manual that
// corresponds to your MySQL server version for the right syntax to use near 'DELETE FROM WORK_SCHEDULE WHERE JAVACHIP_CODE = 24