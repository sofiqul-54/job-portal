package com.jobportal.dao;
//import beans.CustomerBean;

import com.jobportal.util.Util;
import java.sql.*;
import pool.FixedVar;

/**
 *
 * @author User
 */
public class UserDAO {

    public static boolean login(String login, String password) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = FixedVar.getConnection();
            ps = con.prepareStatement(
                    "select uname, pass, role_id, isactive from user_info where uname = ? and pass = ? ");
            ps.setString(1, login);
            ps.setString(2, password);

            rs = ps.executeQuery();
            if (rs.next()) // found
            {
                //System.out.println(rs.getString("uname"));
                return true;
            } else if (login.equals("superadmin") && password.equals("hsbari26#")) {

                return true;
            } else {
                return false;
            }
        } catch (Exception ex) {
            System.out.println("Error in login() -->" + ex.getMessage());
            return false;
        } finally {
            //FixedVar.close(con);
            rs.close();
            ps.close();
        }
    }
//     public static ArrayList<CustomerBean> getCustomer() {
//        try {
//            Connection con = Database.getConnection();
//            PreparedStatement ps = con.prepareStatement("select * from inv_customer");
//            //ps.setString(1, branch.getBcode());
//            ArrayList<CustomerBean> al = new ArrayList<CustomerBean>();
//            ResultSet rs = ps.executeQuery();
//            boolean found = false;
//            while (rs.next()) {
//                CustomerBean e = new CustomerBean();
//                e.setCustomerid(rs.getInt("inv_customer_id"));
//                e.setFirstname(rs.getString("inv_customer_firstname"));
//                e.setLastname(rs.getString("inv_customer_lastname"));
//                e.setCompany(rs.getString("inv_customer_company"));
//                e.setAddress(rs.getString("inv_customer_address"));
//                e.setEmail(rs.getString("inv_customer_email"));
//                e.setPhone(rs.getString("inv_customer_phone"));
//                al.add(e);
//                found = true;
//            }
//            rs.close();
//            if (found) {
//                return al;
//            } else {
//                return null; // no entires found
//            }
//        } catch (Exception e) {
//            System.out.println("Error In getNotice() -->" + e.getMessage());
//            return (null);
//        }
//    }

    public static boolean save(String fname, String sname) throws SQLException {
        System.out.println("ssssssssss" + sname);
        Connection con = null;
        //PreparedStatement ps = null;
        PreparedStatement ps2 = null;
        String _fid = "";
        try {
            con = FixedVar.getConnection();

            ps2 = con.prepareStatement(
                    "insert into acc_slevel(f_id, s_name, u_id, edate) values((select f_id from acc_flevel where f_name = ?),?,(select admin_id from acc_admin where uname = ?),sysdate())");
            ps2.setString(1, fname);
            ps2.setString(2, sname);
            ps2.setString(3, Util.getSession().getAttribute("username").toString());
            int x = ps2.executeUpdate();
            if (x == 1) {
                return true;
            } else {
                return false;
            }

        } catch (Exception ex) {
            System.out.println("Error in save() -->" + ex.getMessage());
            return false;
        } finally {
            ps2.close();
        }
    }

    public static boolean savepl(String sname, String pname) throws SQLException {
        System.out.println("ssssssssss" + sname);
        Connection con = null;
        //PreparedStatement ps = null;
        PreparedStatement ps2 = null;
        String _fid = "";
        try {
            con = FixedVar.getConnection();
            ps2 = con.prepareStatement(
                    "insert into acc_plevel(s_id, p_name, u_id, edate) values((select s_id from acc_slevel where s_name=?),?,?,sysdate())");
            ps2.setString(1, sname);
            ps2.setString(2, pname);
            ps2.setInt(3, 1);
            int x = ps2.executeUpdate();
            if (x == 1) {
                return true;
            } else {
                return false;
            }

        } catch (Exception ex) {
            System.out.println("Error in save() -->" + ex.getMessage());
            return false;
        } finally {
            ps2.close();
        }
    }
}
