package auction.filters;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;

public final class AuthFilter implements Filter 

{
   private FilterConfig filterConfig = null;
   
   
   public void init(FilterConfig filterConfig) 
      throws ServletException 
   {
      this.filterConfig = filterConfig;
      
   }
   
   public void destroy() 
   {
      this.filterConfig = null;
   }
   
   public void doFilter(ServletRequest request,
      ServletResponse response, FilterChain chain) 
      throws IOException, ServletException 
   {
      if (filterConfig == null) return;
         
      HttpServletRequest req = (HttpServletRequest)request;
      HttpServletResponse resp = (HttpServletResponse)response;
      HttpSession session = req.getSession(true);
               
     /* Do something */
//      if(session!=null && session.getAttribute("u_id")==null){
//          //resp.sendRedirect(resp.encodeRedirectURL("Login.jsp"));
//      }
       
    	chain.doFilter(request, response);          
   }      
}
