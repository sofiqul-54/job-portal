package com.jobportal.model;

import java.io.Serializable;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import org.apache.commons.lang.StringEscapeUtils;
import pool.FixedVar;

@ManagedBean
@SessionScoped
public class Jobpost implements Serializable {

    private String job_cat, job_title, job_location, skills_req, edu_req, basic_req, salary_given, job_nature, expr_req, benifit, age_limit;
    private int no_vacancy;
    private java.util.Date start_date = new java.util.Date();
    private java.util.Date end_date = new java.util.Date();
    private java.util.Date expr_date = new java.util.Date();

    public String getJob_cat() {
        return job_cat;
    }

    public void setJob_cat(String job_cat) {
        this.job_cat = job_cat;
    }

    public String getJob_title() {
        return job_title;
    }

    public void setJob_title(String job_title) {
        this.job_title = job_title;
    }

    public String getJob_location() {
        return job_location;
    }

    public void setJob_location(String job_location) {
        this.job_location = job_location;
    }

    public String getSkills_req() {
        return skills_req;
    }

    public void setSkills_req(String skills_req) {
        this.skills_req = skills_req;
    }

    public String getEdu_req() {
        return edu_req;
    }

    public void setEdu_req(String edu_req) {
        this.edu_req = edu_req;
    }

    public String getBasic_req() {
        return basic_req;
    }

    public void setBasic_req(String basic_req) {
        this.basic_req = basic_req;
    }

    public int getNo_vacancy() {
        return no_vacancy;
    }

    public void setNo_vacancy(int no_vacancy) {
        this.no_vacancy = no_vacancy;
    }

    public java.util.Date getStart_date() {
        return start_date;
    }

    public void setStart_date(java.util.Date start_date) {
        this.start_date = start_date;
    }

    public java.util.Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(java.util.Date end_date) {
        this.end_date = end_date;
    }

    public java.util.Date getExpr_date() {
        return expr_date;
    }

    public void setExpr_date(java.util.Date expr_date) {
        this.expr_date = expr_date;
    }

    public String getSalary_given() {
        return salary_given;
    }

    public void setSalary_given(String salary_given) {
        this.salary_given = salary_given;
    } 

    public String getJob_nature() {
        return job_nature;
    }

    public void setJob_nature(String job_nature) {
        this.job_nature = job_nature;
    }

    public String getExpr_req() {
        return expr_req;
    }

    public void setExpr_req(String expr_req) {
        this.expr_req = expr_req;
    }

    public String getBenifit() {
        return benifit;
    }

    public void setBenifit(String benifit) {
        this.benifit = benifit;
    }

    public String getAge_limit() {
        return age_limit;
    }

    public void setAge_limit(String age_limit) {
        this.age_limit = age_limit;
    }   

    public List<SelectItem> getJobCatName() {
        List<SelectItem> jcname = new ArrayList<SelectItem>();
        Connection con=null;
        ResultSet rs=null;
        //PreparedStatement ps2 = null;
        Statement st = null;
        try {
            con = FixedVar.getConnection();
            st = con.createStatement();
            rs = null;
            String myQuery = "SELECT job_cat_name FROM job_category";

            rs = st.executeQuery(myQuery);
            while (rs.next()) {
                jcname.add(new SelectItem(rs.getString("job_cat_name")));
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        finally{
            try {
                con.close();
                rs.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return jcname;
    }
    
    public synchronized void saveJobPost() throws SQLException {
//        if (orderList.size() > 0) {
//            if (transtotal == 0) {

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                String strDate = sdf.format(start_date);
                String endDate = sdf.format(end_date);
                String exprDate = sdf.format(expr_date);
                
                Connection con = null;
                PreparedStatement ps2 = null;
                PreparedStatement ps3 = null;
                try {
                    con = FixedVar.getConnection();
                    // con = Database.getConnection();
                    String sql = "insert into job_post(employer_id, job_cat_id, job_title, job_location, no_vacancy, start_date, end_date, expr_date, skills_req, edu_req, basic_req, salary_given, job_nature, experience_required, other_benefit, age_limit) values(?,(select job_cat_id from job_category where job_cat_name=?),?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                    ps2 = con.prepareStatement(sql);
                    ps2.setInt(1, 1);
                    ps2.setString(2, job_cat);
                    ps2.setString(3, job_title);
                    ps2.setString(4, job_location);
                    ps2.setInt(5, no_vacancy);
                    ps2.setString(6, strDate);
                    ps2.setString(7, endDate);
                    ps2.setString(8, exprDate);
                    ps2.setString(9, skills_req);
                    ps2.setString(10, edu_req);
                    ps2.setString(11, basic_req);
                    ps2.setString(12, salary_given);
                    ps2.setString(13, job_nature);
                    ps2.setString(14, expr_req);
                    ps2.setString(15, benifit);
                    ps2.setString(16, age_limit);
                    //ps2.setString(8, Util.getSession().getAttribute("username").toString());
                    //ps2.setInt(9, 0);
                    //System.out.println(sql);
                    int x = ps2.executeUpdate();
                    if (x == 1) {
                        
                        FacesContext contextm = FacesContext.getCurrentInstance();

                        contextm.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Entry Successful", "Have A Nice Day"));
                    } else {
                        FacesContext.getCurrentInstance().addMessage(
                                "a",
                                new FacesMessage(FacesMessage.SEVERITY_WARN,
                                "Job Posting Could not saved!",
                                "Please Try Again!"));
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                } finally {
                    con.close();
                }
//                    }
//                }.start();

//            } else {
//                FacesContext.getCurrentInstance().addMessage(
//                        null,
//                        new FacesMessage(FacesMessage.SEVERITY_WARN,
//                        "Please Add transaction properly!",
//                        "Please Try Again!"));
//            }
//        } else {
//            FacesContext.getCurrentInstance().addMessage(
//                    null,
//                    new FacesMessage(FacesMessage.SEVERITY_WARN,
//                    "Please Add transaction!",
//                    "Please Try Again!"));
//        }

    }
}
