package com.jobportal.model;

import java.io.Serializable;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import pool.FixedVar;

@ManagedBean
@SessionScoped
public class Registration implements Serializable {

    private String uname, pass, ques, sec_answer, role_name;

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getQues() {
        return ques;
    }

    public void setQues(String ques) {
        this.ques = ques;
    }

    public String getSec_answer() {
        return sec_answer;
    }

    public void setSec_answer(String sec_answer) {
        this.sec_answer = sec_answer;
    }

    public List<SelectItem> getSecurityQuestions() {
        List<SelectItem> jcname = new ArrayList<SelectItem>();
        Connection con = null;
        ResultSet rs = null;
        //PreparedStatement ps2 = null;
        Statement st = null;
        try {
            con = FixedVar.getConnection();
            st = con.createStatement();
            rs = null;
            String myQuery = "SELECT question FROM seq_questions";

            rs = st.executeQuery(myQuery);
            while (rs.next()) {
                jcname.add(new SelectItem(rs.getString("question")));
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                con.close();
                rs.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return jcname;
    }

    public List<SelectItem> getRoleName() {
        List<SelectItem> jcname = new ArrayList<SelectItem>();
        Connection con = null;
        ResultSet rs = null;
        //PreparedStatement ps2 = null;
        Statement st = null;
        try {
            con = FixedVar.getConnection();
            st = con.createStatement();
            rs = null;
            String myQuery = "SELECT role_name FROM user_role where role_name!='admin'";

            rs = st.executeQuery(myQuery);
            while (rs.next()) {
                jcname.add(new SelectItem(rs.getString("role_name")));
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                con.close();
                rs.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return jcname;
    }

    public boolean chkDupUser() {
        boolean found = false;
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement ps2 = null;
        //Statement st = null;
        try {
            con = FixedVar.getConnection();
            //st = con.createStatement();
            ps2 = con.prepareStatement("select uname from user_info where uname = ?");
            ps2.setString(1, uname);
            rs = ps2.executeQuery();
            if (rs.next()) // found
            {
                //System.out.println(rs.getString("uname"));
                found = true;
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                con.close();
                rs.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return found;
    }

    public synchronized void saveRegData() throws SQLException {
        Connection con = null;
        PreparedStatement ps2 = null;
        if (!chkDupUser()) {
            try {
                con = FixedVar.getConnection();
                // con = Database.getConnection();
                String sql = "insert into user_info(role_id, uname, pass, que_id, sec_answer, reg_date) values((select role_id from user_role where role_name=?),?,?,(select que_id from seq_questions where question=?),?, curdate())";
                ps2 = con.prepareStatement(sql);
                ps2.setString(1, role_name);
                ps2.setString(2, uname);
                ps2.setString(3, pass);
                ps2.setString(4, ques);
                ps2.setString(5, sec_answer);
                int x = ps2.executeUpdate();
                if (x == 1) {

                    FacesContext contextm = FacesContext.getCurrentInstance();

                    contextm.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Entry Successful", "Have A Nice Day"));
                } else {
                    FacesContext.getCurrentInstance().addMessage(
                            "a",
                            new FacesMessage(FacesMessage.SEVERITY_WARN,
                                    "Registration Failed!",
                                    "Please Try Again!"));
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                con.close();
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(
                    "a",
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Please Change User Name!",
                            "Please Try Again!"));
        }
    }
}
