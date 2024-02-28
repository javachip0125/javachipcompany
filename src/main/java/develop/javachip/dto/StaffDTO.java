package develop.javachip.dto;

public class StaffDTO {

  // 근태정보 관련 필드
  private int staffCode; //사원번호
  private String staffID; //사원ID
  private String staffPw; //사원PW
  private String staffName;//사원이름
  private String position;//직책
  private int workHour;//총근무시간
  private int remainVacation;//남은연차
  private String workStatus; //당일근무현황
  private String arriveInfo; //출근정보
  private int leaveInfo; //퇴근정보

  // 일정정보 관련 필드
  private String dayName;//요일 - 월/화/수/목/금
  private String scheduleType;//일정타입 - 출장/외근/휴가


  public StaffDTO(){}

  public StaffDTO(int staffCode, String staffID, String staffPw, String staffName, String position, int workHour, int remainVacation, String workStatus, String arriveInfo, int leaveInfo, String dayName, String scheduleType) {
    this.staffCode = staffCode;
    this.staffID = staffID;
    this.staffPw = staffPw;
    this.staffName = staffName;
    this.position = position;
    this.workHour = workHour;
    this.remainVacation = remainVacation;
    this.workStatus = workStatus;
    this.arriveInfo = arriveInfo;
    this.leaveInfo = leaveInfo;
    this.dayName = dayName;
    this.scheduleType = scheduleType;
  }

  public int getStaffCode() {
    return staffCode;
  }

  public void setStaffCode(int staffCode) {
    this.staffCode = staffCode;
  }

  public String getStaffID() {
    return staffID;
  }

  public void setStaffID(String staffID) {
    this.staffID = staffID;
  }

  public String getStaffPw() {
    return staffPw;
  }

  public void setStaffPw(String staffPw) {
    this.staffPw = staffPw;
  }

  public String getStaffName() {
    return staffName;
  }

  public void setStaffName(String staffName) {
    this.staffName = staffName;
  }

  public String getPosition() {
    return position;
  }

  public void setPosition(String position) {
    this.position = position;
  }

  public int getWorkHour() {
    return workHour;
  }

  public void setWorkHour(int workHour) {
    this.workHour = workHour;
  }

  public int getRemainVacation() {
    return remainVacation;
  }

  public void setRemainVacation(int remainVacation) {
    this.remainVacation = remainVacation;
  }

  public String getWorkStatus() {
    return workStatus;
  }

  public void setWorkStatus(String workStatus) {
    this.workStatus = workStatus;
  }

  public String getArriveInfo() {
    return arriveInfo;
  }

  public void setArriveInfo(String arriveInfo) {
    this.arriveInfo = arriveInfo;
  }

  public int getLeaveInfo() {
    return leaveInfo;
  }

  public void setLeaveInfo(int leaveInfo) {
    this.leaveInfo = leaveInfo;
  }

  public String getDayName() {
    return dayName;
  }

  public void setDayName(String dayName) {
    this.dayName = dayName;
  }

  public String getScheduleType() {
    return scheduleType;
  }

  public void setScheduleType(String scheduleType) {
    this.scheduleType = scheduleType;
  }

  @Override
  public String toString() {
    return "StaffDTO{" +
            "staffCode=" + staffCode +
            ", staffID='" + staffID + '\'' +
            ", staffPw='" + staffPw + '\'' +
            ", staffName='" + staffName + '\'' +
            ", position='" + position + '\'' +
            ", workHour=" + workHour +
            ", remainVacation=" + remainVacation +
            ", workStatus='" + workStatus + '\'' +
            ", arriveInfo='" + arriveInfo + '\'' +
            ", leaveInfo='" + leaveInfo + '\'' +
            ", dayName='" + dayName + '\'' +
            ", scheduleType='" + scheduleType + '\'' +
            '}';
  }
}
