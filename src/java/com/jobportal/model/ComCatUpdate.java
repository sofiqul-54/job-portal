package com.jobportal.model;

public class ComCatUpdate {

    private int com_cat_id;
    private String com_cat_name;
    private boolean com_cat_status;

    public ComCatUpdate(int com_cat_id, String com_cat_name, boolean com_cat_status) {
        this.com_cat_id = com_cat_id;
        this.com_cat_name = com_cat_name;
        this.com_cat_status = com_cat_status;
    }

    public int getCom_cat_id() {
        return com_cat_id;
    }

    public String getCom_cat_name() {
        return com_cat_name;
    }

    public boolean isCom_cat_status() {
        return com_cat_status;
    }

    public void setCom_cat_id(int com_cat_id) {
        this.com_cat_id = com_cat_id;
    }

    public void setCom_cat_name(String com_cat_name) {
        this.com_cat_name = com_cat_name;
    }

    public void setCom_cat_status(boolean com_cat_status) {
        this.com_cat_status = com_cat_status;
    }
    
    
}
