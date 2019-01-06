package com.jobportal.model;

import java.io.Serializable;

public class AllJobs implements Serializable {

    private static final long serialVersionUID = 1L;
    private String job_cat, job_title, job_location, skills_req, edu_req, basic_req, salary_given;
    private String job_nature, experience_required, other_benefit, age_limit;
    private int no_vacancy;
    private String start_date;
    private String end_date;
    private String expr_date;
    private int job_id;

    public AllJobs(int job_id, String job_title, String job_location, String skills_req, String edu_req, String basic_req, String salary_given, int no_vacancy, String start_date, String end_date, String expr_date) {
        this.job_cat = job_cat;
        this.job_title = job_title;
        this.job_location = job_location;
        this.skills_req = skills_req;
        this.edu_req = edu_req;
        this.basic_req = basic_req;
        this.salary_given = salary_given;
        this.no_vacancy = no_vacancy;
        this.start_date = start_date;
        this.end_date = end_date;
        this.expr_date = expr_date;
        this.job_id = job_id;
    }

    public AllJobs(int job_id, String job_title, String job_location, String skills_req, String edu_req, String basic_req, String salary_given, int no_vacancy, String start_date, String end_date, String expr_date, String job_nature, String experience_required, String other_benefit, String age_limit) {
        this.job_cat = job_cat;
        this.job_title = job_title;
        this.job_location = job_location;
        this.skills_req = skills_req;
        this.edu_req = edu_req;
        this.basic_req = basic_req;
        this.salary_given = salary_given;
        this.no_vacancy = no_vacancy;
        this.start_date = start_date;
        this.end_date = end_date;
        this.expr_date = expr_date;
        this.job_id = job_id;
        this.job_nature = job_nature;
        this.experience_required = experience_required;
        this.other_benefit = other_benefit;
        this.age_limit = age_limit;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getJob_cat() {
        return job_cat;
    }

    public String getJob_title() {
        return job_title;
    }

    public String getJob_location() {
        return job_location;
    }

    public String getSkills_req() {
        return skills_req;
    }

    public String getEdu_req() {
        return edu_req;
    }

    public String getBasic_req() {
        return basic_req;
    }

    public String getSalary_given() {
        return salary_given;
    }

    public int getNo_vacancy() {
        return no_vacancy;
    }

    public String getStart_date() {
        return start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public String getExpr_date() {
        return expr_date;
    }

    public int getJob_id() {
        return job_id;
    }

    public String getJob_nature() {
        return job_nature;
    }

    public String getExperience_required() {
        return experience_required;
    }

    public String getOther_benefit() {
        return other_benefit;
    }

    public String getAge_limit() {
        return age_limit;
    }

}
