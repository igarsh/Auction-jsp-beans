package auction.filters;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;

public final class NoCacheFilter implements Filter 

{
   private FilterConfig filterConfig = null;
  // private Logger logger;
   
   public void init(FilterConfig filterConfig) 
      throws ServletException 
   {
      this.filterConfig = filterConfig;
      //this.logger = Logger.getRootLogger();
   }
   
   public void destroy() 
   {
      this.filterConfig = null;
      //this.logger = null;
   }
   
   public void doFilter(ServletRequest request,
      ServletResponse response, FilterChain chain) 
      throws IOException, ServletException 
   {
      if (filterConfig == null) return;
         
      HttpServletRequest req = (HttpServletRequest)request;
      HttpServletResponse resp = (HttpServletResponse)response;
      HttpSession session = req.getSession(false);
               
     /* Do something */

     	resp.setHeader("Cache-Control","no-cache");
			resp.setHeader("Pragma","no-cache");
			resp.setDateHeader ("Expires", 0);
       
    	chain.doFilter(request, response);
     
     
   }
      
}
