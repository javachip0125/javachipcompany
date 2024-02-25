package master.section.model.dto;
public class DTO implements java.io.Serializable {

    private String arriveInfo;
    private String leaveInfo;
    private String remainVacation;
    private String workStatus;
public DTO() {
}

public DTO(String arriveInfo, String leaveInfo, String remainVacation, String workStatus) {
    this.arriveInfo = arriveInfo;
    this.leaveInfo = leaveInfo;
    this.remainVacation = remainVacation;
    this.workStatus = workStatus;
}

public String getArriveInfo() {
    return arriveInfo;
}

public void setArriveInfo(String arriveInfo) {
    this.arriveInfo = arriveInfo;
}

public String getLeaveInfo() {
    return leaveInfo;
}

public void setLeaveInfo(String leaveInfo) {
    this.leaveInfo = leaveInfo;
}

public String getRemainVacation() {
    return remainVacation;
}

public void setRemainVacation(String remainVacation) {
    this.remainVacation = remainVacation;
}

public String getWorkStatus() {
    return workStatus;
}

public void setWorkStatus(String workStatus) {
    this.workStatus = workStatus;
}

@Override
public String toString() {
    return "DTO{" +
            "arriveInfo='" + arriveInfo + '\'' +
            ", leaveInfo='" + leaveInfo + '\'' +
            ", remainVacation='" + remainVacation + '\'' +
            ", workStatus='" + workStatus + '\'' +
            '}';
}
}