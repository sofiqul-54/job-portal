package com.jobportal.model;

import java.io.Serializable;
import java.sql.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import org.primefaces.event.RowEditEvent;
import pool.FixedVar;

@ManagedBean
@SessionScoped
public class ComCat implements Serializable {

    private static final long serialVersionUID = 1L;
    private String com_cat_name;    
    private boolean com_cat_status;

    public String getCom_cat_name() {
        return com_cat_name;
    }

    public void setCom_cat_name(String com_cat_name) {
        this.com_cat_name = com_cat_name;
    }

    public boolean isCom_cat_status() {
        return com_cat_status;
    }

    public void setCom_cat_status(boolean com_cat_status) {
        this.com_cat_status = com_cat_status;
    }
    
    

    public synchronized void saveComCat() throws SQLException {
        Connection con = null;
        PreparedStatement ps2 = null;
        if (!chkDupCat()) {
            try {
                con = FixedVar.getConnection();
                // con = Database.getConnection();
                String sql = "insert into company_category(com_cat_name) values(?)";
                ps2 = con.prepareStatement(sql);
                ps2.setString(1, com_cat_name);
                int x = ps2.executeUpdate();
                if (x == 1) {

                    FacesContext contextm = FacesContext.getCurrentInstance();

                    contextm.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Entry Successful", "Have A Nice Day"));
                init();
                } else {
                    FacesContext.getCurrentInstance().addMessage(
                            "a",
                            new FacesMessage(FacesMessage.SEVERITY_WARN,
                                    "Category Entry Failed!",
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
                            "Category Name Already Exists!",
                            "Please Try Again!"));
        }

    }

    public boolean chkDupCat() {
        boolean found = false;
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement ps2 = null;
        //Statement st = null;
        try {
            con = FixedVar.getConnection();
            //st = con.createStatement();
            ps2 = con.prepareStatement("select com_cat_name from company_category where com_cat_name = ?");
            ps2.setString(1, com_cat_name);
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
    
     public void removelist() {
        comcatlList.removeAll(comcatlList);
    }
    public static ArrayList<ComCatUpdate> comcatlList = new ArrayList();

    public ArrayList<ComCatUpdate> getComCatName() {
        return comcatlList;
    }

   @PostConstruct
    public void init() {
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            con = FixedVar.getConnection();
            st = con.createStatement();
            String myQuery = "SELECT * FROM company_category";
            removelist();
            rs = st.executeQuery(myQuery);
            while (rs.next()) {
                comcatlList.add(new ComCatUpdate(rs.getInt("com_cat_id"), rs.getString("com_cat_name"), rs.getBoolean("com_cat_status")));
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
    
    
    public void onEdit(RowEditEvent event) {
        com_cat_name = ((ComCatUpdate) event.getObject()).getCom_cat_name();
        com_cat_status = ((ComCatUpdate) event.getObject()).isCom_cat_status();
        if(!chkDupCat()){
        
        }
        else{
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Category Name Already Exists ", null);
        FacesContext.getCurrentInstance().addMessage(null, message);
        }
//
//        //System.out.println(uid);
//        //System.out.println(fname);
//
//        if (!sname.isEmpty()) {
//
//
//            try {
//                Connection con = FixedVar.getConnection();
//                Statement stmt = con.createStatement();
//                int count = 0;
//                ResultSet query = stmt.executeQuery("Select * from acc_slevel where s_name='" + sname + "'");
//                while (query.next()) {
//                    count = query.getRow();
//                }
//                if (count >= 1) {
//                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Second Level Already Exists ", null);
//                    FacesContext.getCurrentInstance().addMessage(null, message);
//                } else {
//                    //System.out.println("jahangir 2");
//                    //System.out.println(count);
//
//                    user_name = Util.getSession().getAttribute("username").toString();
//
//                    stmt.executeUpdate("UPDATE acc_slevel set s_name = '" + sname + "', u_id = (Select admin_id from acc_admin where uname ='" + user_name + "'),edate= now() where s_id = '" + uid + "'");
//                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Updated Successfully ", null);
//                    FacesContext.getCurrentInstance().addMessage(null, message);
//
//                    init();
//                    //  clear();
//                }
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//
//        } else {
//            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Please insert Second Level", null);
//            FacesContext.getCurrentInstance().addMessage(null, message);
//
//        }


    }

    public void onCancel(RowEditEvent event) {
//        FacesMessage msg = new FacesMessage("Item Cancelled");
//        FacesContext.getCurrentInstance().addMessage(null, msg);
//        //orderList.remove((OrderBean) event.getObject());

//        if (RoleCheck.checkRole()) {
//            itemSlevelList.remove((Slevel_update) event.getObject());
//
//
//            sname = ((Slevel_update) event.getObject()).getSname();
//
//            uid = ((Slevel_update) event.getObject()).getUid();
//
//
//            try {
//                Connection con = FixedVar.getConnection();
//                Statement stmt = con.createStatement();
//                int count = 0;
//                ResultSet query = stmt.executeQuery("Select * from acc_plevel where s_id = (select s_id from acc_slevel where s_name='" + sname + "')");
//                while (query.next()) {
//                    count = query.getRow();
//                }
//                if (count > 0) {
//                    init();
//                    clear();
//                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Information Already In USe ", "Can't Be Delete");
//                    FacesContext.getCurrentInstance().addMessage(null, message);
//                } else {
//
//                    stmt.executeUpdate("Delete FROM acc_slevel where s_id =" + uid);
//                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Successfully Deleted", "Successfully Deleted");
//                    FacesContext.getCurrentInstance().addMessage(null, message);
//
//
//
//
//                }
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//        } else {
//            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Access Denied!", "Successfully Deleted");
//            FacesContext.getCurrentInstance().addMessage(null, message);
//        }

    }

}
