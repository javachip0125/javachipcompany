package master.section.model.dto;
public class DTO implements java.io.Serializable {

    private int javachipCode;
    private int workHour;
    private int remainVacation;
    private String workStatus;
    private String arriveInfo;
    private boolean leaveInfo;
    private String workSchedule;

    public DTO() {
    }

    public DTO(int javachipCode, int workHour, int remainVacation, String workStatus, String arriveInfo, boolean leaveInfo, String workSchedule) {
        this.javachipCode = javachipCode;
        this.workHour = workHour;
        this.remainVacation = remainVacation;
        this.workStatus = workStatus;
        this.arriveInfo = arriveInfo;
        this.leaveInfo = leaveInfo;
        this.workSchedule = workSchedule;
    }

    public int getJavachipCode() {
        return javachipCode;
    }

    public void setJavachipCode(int javachipCode) {
        this.javachipCode = javachipCode;
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

    public boolean isLeaveInfo() {
        return leaveInfo;
    }

    public void setLeaveInfo(boolean leaveInfo) {
        this.leaveInfo = leaveInfo;
    }

    public String getWorkSchedule() {
        return workSchedule;
    }

    public void setWorkSchedule(String workSchedule) {
        this.workSchedule = workSchedule;
    }

    @Override
    public String toString() {
        return "DTO{" +
                "javachipCode=" + javachipCode +
                ", workHour=" + workHour +
                ", remainVacation=" + remainVacation +
                ", workStatus='" + workStatus + '\'' +
                ", arriveInfo='" + arriveInfo + '\'' +
                ", leaveInfo=" + leaveInfo +
                ", workSchedule='" + workSchedule + '\'' +
                '}';
    }
}