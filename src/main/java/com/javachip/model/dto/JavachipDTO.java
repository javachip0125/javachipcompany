package com.javachip.model.dto;

public class JavachipDTO implements java.io.Serializable {

    private String javachipId;
    private String javachipPwd;

    public JavachipDTO () {}
    public JavachipDTO(String javachipId, String javachipPwd) {
        this.javachipId = javachipId;
        this.javachipPwd = javachipPwd;
    }

    public String getJavachipId() {
        return javachipId;
    }

    public void setJavachipId(String javachipId) {
        this.javachipId = javachipId;
    }

    public String getJavachipPwd() {
        return javachipPwd;
    }

    public void setJavachipPwd(String javachipPwd) {
        this.javachipPwd = javachipPwd;
    }

    @Override
    public String toString() {
        return "JavachipDTO{" +
                "javachipId='" + javachipId + '\'' +
                ", javachipPwd='" + javachipPwd + '\'' +
                '}';
    }
}
