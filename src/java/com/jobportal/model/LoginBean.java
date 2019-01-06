/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobportal.model;

import com.jobportal.dao.UserDAO;
import com.jobportal.util.Util;
import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;
import java.sql.*;
import javax.servlet.ServletContext;
import pool.FixedVar;

@ManagedBean(name = "loginBean")
@SessionScoped
/**
 *
 * @author User
 */
public class LoginBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private String login;
    private String password;
    private String message, uname, email;
    boolean remember;
    String remember1 = "hi";
//private static final long serialVersionUID = 1L;

    public LoginBean() {
        checkCookie();
    }

    public void setRemember(boolean remember) {
        this.remember = remember;
    }

    public boolean getRemember() {
        return remember;
    }

    public String getRemember1() {
        return remember1;
    }

    public void setRemember1(String remember1) {
        this.remember1 = remember1;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPassword() {
        if (remember == false) {
            password = "";
            return password;
        } else {
            System.out.println("cccccccc" + password);
            return password;
        }
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUname() {
        if (remember == false) {
            uname = "";
            return uname;
        } else {
            return uname;
        }
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String loginProject() throws SQLException {
        boolean result = UserDAO.login(uname, password);
        if (result) {
            // get Http Session and store username
            HttpSession session = Util.getSession();
            //session.setAttribute("userid", loginuserid);
            session.setAttribute("username", uname);
            FacesContext facesContext = FacesContext.getCurrentInstance();
// Save the uname and password in a cookie
            Cookie btuser = new Cookie("btuser", uname);
            Cookie btpasswd = new Cookie("btpasswd", password);
            if (remember == false) {
                remember1 = "false";
            } else {
                remember1 = "true";
            }
            Cookie btremember = new Cookie("btremember", remember1);
            btuser.setMaxAge(3600);
            btpasswd.setMaxAge(3600);
            ((HttpServletResponse) facesContext.getExternalContext().getResponse()).addCookie(btuser);

            ((HttpServletResponse) facesContext.getExternalContext().getResponse()).addCookie(btpasswd);

            ((HttpServletResponse) facesContext.getExternalContext().getResponse()).addCookie(btremember);
            return session.getAttribute("originalURL") + "faces-redirect=true";
        } else {

            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Invalid Login!",
                            "Please Try Again!"));

            // invalidate session, and redirect to other pages
            //message = "Invalid Login. Please Try Again!";
            return "login";
        }
    }

    public String doLogin() {
        String returnPage = "index";
        String query = "SELECT u.uname, r.role_name, u.isactive "
                + "FROM user_info u "
                + "JOIN user_role r "
                + "WHERE uname=? AND pass=? AND u.role_id = r.role_id;";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = FixedVar.getConnection().prepareStatement(query);
            ps.setString(1, uname);
            ps.setString(2, password);
            rs = ps.executeQuery();
            if (rs.next()) {
                boolean isActive = rs.getBoolean("isactive");
                String userType = rs.getString("role_name");
                //int userid = rs.getInt("user_id");
                if (isActive) {
                    //session
                    HttpSession session = Util.getSession();
                    session.setAttribute("username", uname);
                    session.setAttribute("roleName", userType);
                    //cookie
                    FacesContext facesContext = FacesContext.getCurrentInstance();
                    // Save the uname and password in a cookie
                    Cookie btuser = new Cookie("btuser", uname);
                    Cookie btpasswd = new Cookie("btpasswd", password);
                    if (remember == false) {
                        remember1 = "false";
                    } else {
                        remember1 = "true";
                    }
                    Cookie btremember = new Cookie("btremember", remember1);
                    btuser.setMaxAge(3600);
                    btpasswd.setMaxAge(3600);
                    ((HttpServletResponse) facesContext.getExternalContext().getResponse()).addCookie(btuser);

                    ((HttpServletResponse) facesContext.getExternalContext().getResponse()).addCookie(btpasswd);

                    ((HttpServletResponse) facesContext.getExternalContext().getResponse()).addCookie(btremember);
                    //-------end cookie
                    if (userType.equalsIgnoreCase("admin")) {
                        returnPage = "admin/index?faces-redirect=true";
                    }
                    if (userType.equalsIgnoreCase("employer")) {
                        returnPage = "employer/index?faces-redirect=true";
                    }
                    if (userType.equalsIgnoreCase("employee")) {
                        returnPage = "employee/index?faces-redirect=true";
                    }
                } else {
                    FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Information", "User is not active");
                    FacesContext.getCurrentInstance().addMessage("warn", msg);
                }
            } else {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Invalid Username or password");
                FacesContext.getCurrentInstance().addMessage("warn", msg);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                ps.close();
                rs.close();
                clear();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return returnPage;
    }

    public String logout() throws IOException {
        HttpSession session = Util.getSession();
        session.invalidate();
        ServletContext servletContext = (ServletContext) FacesContext
                .getCurrentInstance().getExternalContext().getContext();
        FacesContext.getCurrentInstance().getExternalContext().redirect(servletContext+"index.xhtml");
        return "login";
    }

    void clear() {
        setUname(null);
        setPassword(null);
    }

    public void checkCookie() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        String cookieName = null;
        Cookie cookie[] = ((HttpServletRequest) facesContext.getExternalContext().getRequest()).getCookies();
        if (cookie != null && cookie.length > 0) {
            for (int i = 0; i < cookie.length; i++) {
                cookieName = cookie[i].getName();
                if (cookieName.equals("btuser")) {
                    uname = cookie[i].getValue();
                    //System.out.println("cccccccc"+uname);
                } else if (cookieName.equals("btpasswd")) {
                    password = cookie[i].getValue();
                } else if (cookieName.equals("btremember")) {
                    remember1 = cookie[i].getValue();
                    if (remember1.equals("false")) {
                        remember = false;
                    } else if (remember1.equals("true")) {
                        remember = true;
                    }
                }
            }
        } else {
            System.out.println("Cannot find any cookie");
        }
    }
}
