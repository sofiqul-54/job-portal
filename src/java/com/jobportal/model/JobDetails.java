/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobportal.model;

import com.jobportal.util.Util;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.HttpSession;
import pool.FixedVar;

/**
 *
 * @author User
 */
@ManagedBean
@SessionScoped
public class JobDetails implements Serializable {

    private static final long serialVersionUID = 1L;
    private String job_cat, job_title, job_location, skills_req, edu_req, basic_req, salary_given;
    private int no_vacancy;
    private String start_date;
    private String end_date;
    private String expr_date;
    private int job_id;
    private String originalURL;

    public String getOriginalURL() {
        return originalURL;
    }

    public void setOriginalURL(String originalURL) {
        this.originalURL = originalURL;
    }

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

    public String getSalary_given() {
        return salary_given;
    }

    public void setSalary_given(String salary_given) {
        this.salary_given = salary_given;
    }

    public int getNo_vacancy() {
        return no_vacancy;
    }

    public void setNo_vacancy(int no_vacancy) {
        this.no_vacancy = no_vacancy;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public String getExpr_date() {
        return expr_date;
    }

    public void setExpr_date(String expr_date) {
        this.expr_date = expr_date;
    }

    public int getJob_id() {
        return job_id;
    }

    public void setJob_id(int job_id) {
        this.job_id = job_id;
    }
    private static final ArrayList<AllJobs> jobList = new ArrayList<AllJobs>();

    public ArrayList<AllJobs> getJobList() {
        return jobList;
    }

    public String detailAction() {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement ps2 = null;
        Statement st = null;
        try {
            jobList.removeAll(jobList);
            con = FixedVar.getConnection();
            st = con.createStatement();
            String myQuery = "SELECT * FROM job_post where job_id= ?";
            try {
                ps2 = con.prepareStatement(myQuery);
                ps2.setInt(1, job_id);
                System.out.println(ps2);
                rs = ps2.executeQuery();
                while (rs.next()) {
                    jobList.add(new AllJobs(rs.getInt("job_id"), rs.getString("job_title"), rs.getString("job_location"), rs.getString("skills_req"), rs.getString("edu_req"), rs.getString("basic_req"), rs.getString("salary_given"), rs.getInt("no_vacancy"), rs.getString("start_date"), rs.getString("end_date"), rs.getString("expr_date"), rs.getString("job_nature"), rs.getString("experience_required"), rs.getString("other_benefit"), rs.getString("age_limit")));
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

        return "jobdetails?faces-redirect=true";
    }

    public String applyAction() {
        //System.out.println(originalURL);
        String pageName = originalURL.substring(originalURL.lastIndexOf("/")+1);
        // get Http Session and store username
        HttpSession session = Util.getSession();
        session.setAttribute("originalURL", pageName);
        //session.setAttribute("userid", loginuserid);
        if (session.getAttribute("username") == null) {
            return "login?faces-redirect=true";
        } else {
            Connection con = null;
            ResultSet rs = null;
            PreparedStatement ps2 = null;
            Statement st = null;
            try {
                jobList.removeAll(jobList);
                con = FixedVar.getConnection();
                st = con.createStatement();
                String myQuery = "SELECT * FROM job_post where job_id= ?";
                try {
                    ps2 = con.prepareStatement(myQuery);
                    ps2.setInt(1, job_id);
                    System.out.println(ps2);
                    rs = ps2.executeQuery();
                    while (rs.next()) {
                        jobList.add(new AllJobs(rs.getInt("job_id"), rs.getString("job_title"), rs.getString("job_location"), rs.getString("skills_req"), rs.getString("edu_req"), rs.getString("basic_req"), rs.getString("salary_given"), rs.getInt("no_vacancy"), rs.getString("start_date"), rs.getString("end_date"), rs.getString("expr_date"), rs.getString("job_nature"), rs.getString("experience_required"), rs.getString("other_benefit"), rs.getString("age_limit")));
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
            return "jobdetails?faces-redirect=true";
        }

    }
}
