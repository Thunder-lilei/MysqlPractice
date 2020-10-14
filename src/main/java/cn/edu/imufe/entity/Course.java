package cn.edu.imufe.entity;

public class Course {
    private Integer cno;

    private String cname;

    private Integer cpno;

    private Integer ccredit;

    public Integer getCno() {
        return cno;
    }

    public void setCno(Integer cno) {
        this.cno = cno;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname == null ? null : cname.trim();
    }

    public Integer getCpno() {
        return cpno;
    }

    public void setCpno(Integer cpno) {
        this.cpno = cpno;
    }

    public Integer getCcredit() {
        return ccredit;
    }

    public void setCcredit(Integer ccredit) {
        this.ccredit = ccredit;
    }
}