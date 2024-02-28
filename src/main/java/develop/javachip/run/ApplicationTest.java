package develop.javachip.run;

import develop.javachip.dao.ManagerDAO;
import develop.javachip.menu.SystemMenu;

import java.sql.Connection;
import java.util.Collection;

import static develop.javachip.common.JDBCTemplate.getConnection;

public class ApplicationTest {
  public static void main(String[] args) {

    SystemMenu systemMenu = new SystemMenu();

  //  systemMenu.loginMenu();
    Connection con = getConnection();
    ManagerDAO managerDAO = new ManagerDAO();
    managerDAO.remainVacation();


  }
}
