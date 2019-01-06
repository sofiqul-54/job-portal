package com.jobportal.model;

import com.jobportal.util.Util;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import pool.FixedVar;

@ManagedBean
@SessionScoped
public class EmpBasicInfo implements Serializable {

    private static final long serialVersionUID = 1L;
    private String first_name, middle_name, last_name, gender, contact_no, email, address, father_name, mother_name, marital_status, nationality;
    private java.util.Date dob = new java.util.Date();

    @PostConstruct
    public void init() {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement ps2 = null;
        Statement st = null;
        try {
            con = FixedVar.getConnection();
            st = con.createStatement();
            HttpSession session = Util.getSession();
            String myQuery = "SELECT * FROM employee_basic_info where user_id= (select user_id from user_info where uname=?)";
            try {
                ps2 = con.prepareStatement(myQuery);
                ps2.setString(1, session.getAttribute("username").toString());
                //System.out.println(ps2);
                rs = ps2.executeQuery();
                while (rs.next()) {
                    first_name = rs.getString("first_name");
                    middle_name = rs.getString("middle_name");
                    last_name= rs.getString("last_name");
                    gender= rs.getString("gender");
                    contact_no= rs.getString("contact_no");
                    email= rs.getString("email");
                    father_name= rs.getString("father_name");
                    mother_name= rs.getString("mother_name");
                    nationality= rs.getString("nationality");
                    dob= rs.getDate("dob");
                    marital_status= rs.getString("marital_status");
                    address= rs.getString("address");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                con.close();
                ps2.close();
                rs.close();
//            job_title="";
//            job_location="";
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getMiddle_name() {
        return middle_name;
    }

    public void setMiddle_name(String middle_name) {
        this.middle_name = middle_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getContact_no() {
        return contact_no;
    }

    public void setContact_no(String contact_no) {
        this.contact_no = contact_no;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getFather_name() {
        return father_name;
    }

    public void setFather_name(String father_name) {
        this.father_name = father_name;
    }

    public String getMother_name() {
        return mother_name;
    }

    public void setMother_name(String mother_name) {
        this.mother_name = mother_name;
    }

    public String getMarital_status() {
        return marital_status;
    }

    public void setMarital_status(String marital_status) {
        this.marital_status = marital_status;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public synchronized void saveBasicInfo() throws SQLException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String birthdate = sdf.format(dob);
        Connection con = null;
        PreparedStatement ps2 = null;
        PreparedStatement ps3 = null;
        if (!chkDupInfo()) {
            try {
                con = FixedVar.getConnection();
                HttpSession session = Util.getSession();
                String sql = "insert into employee_basic_info(user_id, first_name, middle_name, last_name, gender, dob, contact_no, profile_date, email, address, father_name, mother_name, marital_status, nationality) values((select user_id from user_info where uname=?),?,?,?,?,?,?,curdate(),?,?,?,?,?,?)";
                ps2 = con.prepareStatement(sql);
                ps2.setString(1, session.getAttribute("username").toString());
                ps2.setString(2, first_name);
                ps2.setString(3, middle_name);
                ps2.setString(4, last_name);
                ps2.setString(5, gender);
                ps2.setString(6, birthdate);
                ps2.setString(7, contact_no);
                ps2.setString(8, email);
                ps2.setString(9, address);
                ps2.setString(10, father_name);
                ps2.setString(11, mother_name);
                ps2.setString(12, marital_status);
                ps2.setString(13, nationality);
                int x = ps2.executeUpdate();
                if (x == 1) {
                    FacesContext contextm = FacesContext.getCurrentInstance();
                    contextm.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Entry Successful", "Have A Nice Day"));
                } else {
                    FacesContext.getCurrentInstance().addMessage(
                            "a",
                            new FacesMessage(FacesMessage.SEVERITY_WARN,
                                    "Could not saved data!",
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
                            "Information Already Saved!",
                            "Please Try Again!"));
        }
    }

    public boolean chkDupInfo() {
        boolean found = false;
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement ps2 = null;
        //Statement st = null;
        try {
            HttpSession session = Util.getSession();
            con = FixedVar.getConnection();
            //st = con.createStatement();
            ps2 = con.prepareStatement("select user_id from employee_basic_info where user_id = (select user_id from user_info where uname=?)");
            ps2.setString(1, session.getAttribute("username").toString());
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

}
