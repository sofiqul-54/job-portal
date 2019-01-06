package com.jobportal.model;

import com.jobportal.util.Util;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;
import pool.FixedVar;

@ManagedBean
@SessionScoped
public class EmploymentHistory implements Serializable {

    private static final long serialVersionUID = 1L;
    private String company_name, com_cat_name, company_location, department, position_held, area_of_exp, responsibilities;
    private java.util.Date form_date = new java.util.Date();
    private java.util.Date to_date = new java.util.Date();
    boolean todate;

    @PostConstruct
    public void init() {
        findEMpHis();
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getCom_cat_name() {
        return com_cat_name;
    }

    public void setCom_cat_name(String com_cat_name) {
        this.com_cat_name = com_cat_name;
    }

    public String getCompany_location() {
        return company_location;
    }

    public void setCompany_location(String company_location) {
        this.company_location = company_location;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPosition_held() {
        return position_held;
    }

    public void setPosition_held(String position_held) {
        this.position_held = position_held;
    }

    public String getArea_of_exp() {
        return area_of_exp;
    }

    public void setArea_of_exp(String area_of_exp) {
        this.area_of_exp = area_of_exp;
    }

    public String getResponsibilities() {
        return responsibilities;
    }

    public void setResponsibilities(String responsibilities) {
        this.responsibilities = responsibilities;
    }

    public Date getTo_date() {
        return to_date;
    }

    public void setTo_date(Date to_date) {
        this.to_date = to_date;
    }

    public Date getForm_date() {
        return form_date;
    }

    public void setForm_date(Date form_date) {
        this.form_date = form_date;
    }

    public boolean isTodate() {
        return todate;
    }

    public void setTodate(boolean todate) {
        this.todate = todate;
    }

    public synchronized void saveHistoryInfo() throws SQLException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String fd = sdf.format(form_date);
        String td = "";
        if (todate) {
            td = "Continuing";
        } else {
            td = sdf.format(to_date);
        }
        Connection con = null;
        PreparedStatement ps2 = null;
        PreparedStatement ps3 = null;
        try {
            con = FixedVar.getConnection();
            HttpSession session = Util.getSession();
            String sql = "insert into employment_history(employee_id, company_name, com_cat_id, company_location, department, position_held, area_of_exp, responsibilities, form_date, to_date) values((select employee_id from employee_basic_info where user_id=(select user_id from user_info where uname=?)),?,(select com_cat_id from company_category where com_cat_name=?),?,?,?,?,?,?,?)";
            ps2 = con.prepareStatement(sql);
            ps2.setString(1, session.getAttribute("username").toString());
            ps2.setString(2, company_name);
            ps2.setString(3, com_cat_name);
            ps2.setString(4, company_location);
            ps2.setString(5, department);
            ps2.setString(6, position_held);
            ps2.setString(7, area_of_exp);
            ps2.setString(8, responsibilities);
            ps2.setString(9, fd);
            ps2.setString(10, td);
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

    }

    public List<SelectItem> getComCat() {
        List<SelectItem> jcname = new ArrayList<SelectItem>();
        Connection con = null;
        ResultSet rs = null;
        //PreparedStatement ps2 = null;
        Statement st = null;
        try {
            con = FixedVar.getConnection();
            st = con.createStatement();
            rs = null;
            String myQuery = "SELECT com_cat_name FROM company_category";

            rs = st.executeQuery(myQuery);
            while (rs.next()) {
                jcname.add(new SelectItem(rs.getString("com_cat_name")));
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

    private  ArrayList<EmploymentDetails> emphisList = new ArrayList();

    public  ArrayList<EmploymentDetails> getEmphisList() {
        return emphisList;
    }

    public void findEMpHis() {
        emphisList.remove(emphisList);
        Connection con = null;
        ResultSet rs = null;
        //PreparedStatement ps2 = null;
        Statement st = null;
        try {
            HttpSession session = Util.getSession();
            String uname = session.getAttribute("username").toString();
            con = FixedVar.getConnection();
            st = con.createStatement();
            rs = null;
            String myQuery = "SELECT employment_history.company_name,\n"
                    + " company_category.com_cat_name,\n"
                    + " employment_history.company_location,\n"
                    + " employment_history.department,\n"
                    + " employment_history.position_held,\n"
                    + " employment_history.area_of_exp,\n"
                    + " employment_history.responsibilities,\n"
                    + " employment_history.form_date,\n"
                    + " employment_history.to_date\n"
                    + " FROM    (   (   employee_basic_info\n"
                    + " INNER JOIN\n"
                    + " job_portal.user_info user_info\n"
                    + " ON (employee_basic_info.user_id = user_info.user_id))\n"
                    + " INNER JOIN\n"
                    + " employment_history\n"
                    + " ON (employment_history.employee_id =\n"
                    + " employee_basic_info.employee_id))\n"
                    + " INNER JOIN\n"
                    + " company_category\n"
                    + " ON (employment_history.com_cat_id = company_category.com_cat_id)\n"
                    + " WHERE (user_info.uname = '"+uname+"')\n"
                    + " ORDER BY employment_history.form_date DESC";
            System.out.println(myQuery);
            rs = st.executeQuery(myQuery);
            while (rs.next()) {
                emphisList.add(new EmploymentDetails(rs.getString("company_name"), rs.getString("com_cat_name"), rs.getString("company_location"), rs.getString("department"), rs.getString("position_held"), rs.getString("area_of_exp"), rs.getString("responsibilities"), rs.getString("form_date"), rs.getString("to_date")));
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
    }
}
