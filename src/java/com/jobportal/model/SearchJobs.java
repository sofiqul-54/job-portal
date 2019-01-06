package com.jobportal.model;

import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;
import pool.FixedVar;

@ManagedBean
@SessionScoped
public class SearchJobs implements Serializable {

    private String job_title, job_location;

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

    private static final ArrayList<AllJobs> jobList = new ArrayList<AllJobs>();

    public ArrayList<AllJobs> getJobList() {
        return jobList;
    }
    public String searchAction() {
        try {
            jobList.removeAll(jobList);
            Connection con = FixedVar.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = null;
            PreparedStatement ps2 = null;
            String myQuery = "SELECT * FROM job_post where job_title like ? and job_location like ? order by end_date desc";
            try {
                ps2 = con.prepareStatement(myQuery);
                ps2.setString(1, "%"+job_title.toLowerCase()+"%");
                ps2.setString(2, "%"+job_location.toLowerCase()+"%");
                System.out.println(ps2);
                rs = ps2.executeQuery();
                while (rs.next()) {
                    jobList.add(new AllJobs(rs.getInt("job_id"),rs.getString("job_title"), rs.getString("job_location"), rs.getString("skills_req"), rs.getString("edu_req"), rs.getString("basic_req"),rs.getString("salary_given"), rs.getInt("no_vacancy"), rs.getString("start_date"), rs.getString("end_date"), rs.getString("expr_date")));
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
//            job_title="";
//            job_location="";
        }

        return "searchresults?faces-redirect=true";
    }
    
    public List<String> completeWhat(String query) {
        List<String> results = new ArrayList<String>();
        Connection con=null;
        ResultSet rs=null;
        PreparedStatement ps2 = null;
        Statement st = null;
        try {
            con = FixedVar.getConnection();
            st = con.createStatement();
            rs = null;
            ps2 = null;
            String myQuery = "SELECT distinct job_title FROM job_post";
            try {
                ps2 = con.prepareStatement(myQuery);
                
                rs = ps2.executeQuery();
                while (rs.next()) {
                    results.add(rs.getString("job_title"));
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
            } catch (Exception e) {
                e.printStackTrace();
            }
//            job_title="";
//            job_location="";
        }
         
        return results;
    }
    public List<String> completeWhere(String query) {
        List<String> results = new ArrayList<String>();
        Connection con=null;
        ResultSet rs=null;
        PreparedStatement ps2 = null;
        Statement st = null;
        try {
            con = FixedVar.getConnection();
            st = con.createStatement();
            rs = null;
            ps2 = null;
            String myQuery = "SELECT distinct job_location FROM job_post";
            try {
                ps2 = con.prepareStatement(myQuery);
                
                rs = ps2.executeQuery();
                while (rs.next()) {
                    results.add(rs.getString("job_location"));
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
            } catch (Exception e) {
                e.printStackTrace();
            }
//            job_title="";
//            job_location="";
        }
         
        return results;
    }
}
