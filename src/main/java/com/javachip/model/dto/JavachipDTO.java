package com.javachip.model.dto;

public class JavachipDTO implements java.io.Serializable {

    private String javachipCode;
    private int workHour;
    private int remainVacation;
    private String workStatus;
    private String arriveInfo;
    private boolean leaveInfo;
    private String workSchedule;

    public JavachipDTO() {
    }

    public JavachipDTO(String javachipCode, int workHour, int remainVacation, String workStatus, String arriveInfo, boolean leaveInfo, String work_schedule) {
        this.javachipCode = javachipCode;
        this.workHour = workHour;
        this.remainVacation = remainVacation;
        this.workStatus = workStatus;
        this.arriveInfo = arriveInfo;
        this.leaveInfo = leaveInfo;
        this.workSchedule = work_schedule;
    }

    public String getJavachipCode() {
        return javachipCode;
    }

    public void setJavachipCode(String javachipCode) {
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

    public String getWork_schedule() {
        return workSchedule;
    }

    public void setWork_schedule(String work_schedule) {
        this.workSchedule = work_schedule;
    }

    @Override
    public String toString() {
        return "JavachipDTO{" +
                "javachipCode='" + javachipCode + '\'' +
                ", workHour=" + workHour +
                ", remainVacation=" + remainVacation +
                ", workStatus='" + workStatus + '\'' +
                ", arriveInfo='" + arriveInfo + '\'' +
                ", leaveInfo=" + leaveInfo +
                ", work_schedule='" + workSchedule + '\'' +
                '}';
    }
}



