package com.jobportal.model;

import java.io.Serializable;

public class EmploymentDetails implements Serializable {

    private static final long serialVersionUID = 1L;
    private String company_name, com_cat_name, company_location, department, position_held, area_of_exp, responsibilities;
    private String form_date, to_date;

    public EmploymentDetails(String company_name, String com_cat_name, String company_location, String department, String position_held, String area_of_exp, String responsibilities, String form_date, String to_date) {
        this.company_name = company_name;
        this.com_cat_name = com_cat_name;
        this.company_location = company_location;
        this.department = department;
        this.position_held = position_held;
        this.area_of_exp = area_of_exp;
        this.responsibilities = responsibilities;
        this.form_date = form_date;
        this.to_date = to_date;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getCompany_name() {
        return company_name;
    }

    public String getCom_cat_name() {
        return com_cat_name;
    }

    public String getCompany_location() {
        return company_location;
    }

    public String getDepartment() {
        return department;
    }

    public String getPosition_held() {
        return position_held;
    }

    public String getArea_of_exp() {
        return area_of_exp;
    }

    public String getResponsibilities() {
        return responsibilities;
    }

    public String getForm_date() {
        return form_date;
    }

    public String getTo_date() {
        return to_date;
    }

    
    
    

}
