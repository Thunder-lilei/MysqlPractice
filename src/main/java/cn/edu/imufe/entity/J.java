package cn.edu.imufe.entity;

public class J {
    private String jno;

    private String jname;

    private String city;

    public String getJno() {
        return jno;
    }

    public void setJno(String jno) {
        this.jno = jno == null ? null : jno.trim();
    }

    public String getJname() {
        return jname;
    }

    public void setJname(String jname) {
        this.jname = jname == null ? null : jname.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }
}