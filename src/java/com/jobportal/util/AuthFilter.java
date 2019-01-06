package com.jobportal.util;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(filterName = "AuthFilter", urlPatterns = {"*.xhtml"})
public class AuthFilter implements Filter {

  private static final boolean debug = false;
  // The filter configuration object we are associated with.  If
  // this value is null, this filter instance is not currently
  // configured. 
  private FilterConfig filterConfig = null;

  public AuthFilter() {
  }

  /**
   *
   * @param request The servlet request we are processing
   * @param response The servlet response we are creating
   * @param chain The filter chain we are processing
   *
   * @exception IOException if an input/output error occurs
   * @exception ServletException if a servlet error occurs
   */
  public void doFilter(ServletRequest request, ServletResponse response,
          FilterChain chain)
          throws IOException, ServletException {

    try {

      // check whether session variable is set
      HttpServletRequest req = (HttpServletRequest) request;
      HttpServletResponse res = (HttpServletResponse) response;
      res.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
      res.setHeader("Pragma", "no-cache"); // HTTP 1.0.
      res.setDateHeader("Expires", 0); // Proxies.
      HttpSession ses = req.getSession(false);
      //  allow user to proccede if url is login.xhtml or user logged in or user is accessing any page in //public folder
      String reqURI = req.getRequestURI();
      if (reqURI.indexOf("/index.xhtml") >= 0 || reqURI.indexOf("/login.xhtml") >= 0 || reqURI.indexOf("/searchresults.xhtml") >= 0 || reqURI.indexOf("/jobdetails.xhtml") >= 0
              || (ses != null && ses.getAttribute("username") != null && ses.getAttribute("roleName").equals("admin") && reqURI.indexOf("/admin/") >= 0)
              || reqURI.indexOf("/public/") >= 0 || reqURI.contains("javax.faces.resource")) {
        chain.doFilter(request, response);
      } else if (reqURI.indexOf("/index.xhtml") >= 0 || reqURI.indexOf("/login.xhtml") >= 0
              || (ses != null && ses.getAttribute("username") != null && ses.getAttribute("roleName").equals("employer") && reqURI.indexOf("/employer/") >= 0)
              || reqURI.indexOf("/public/") >= 0 || reqURI.contains("javax.faces.resource")) {
        chain.doFilter(request, response);
      } else if (reqURI.indexOf("/index.xhtml") >= 0 || reqURI.indexOf("/login.xhtml") >= 0
              || (ses != null && ses.getAttribute("username") != null && ses.getAttribute("roleName").equals("employee") && reqURI.indexOf("/employee/") >= 0)
              || reqURI.indexOf("/public/") >= 0 || reqURI.contains("javax.faces.resource")) {
        chain.doFilter(request, response);
      } else // user didn't log in but asking for a page that is not allowed so take user to login page
      {
        res.sendRedirect(req.getContextPath() + "/index.xhtml");  // Anonymous user. Redirect to login page
      }
    } catch (Throwable t) {
      System.out.println(t.getMessage());
    }
  }

  /**
   * Destroy method for this filter 
   */
  public void destroy() {
  }

  /**
   * Init method for this filter 
   */
  public void init(FilterConfig filterConfig) {
    
  }

}
