<%-- 
    Document   : JSPage
    Created on : Aug 10, 2017, 2:11:58 PM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import = "java.io.*,java.util.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello JSP World!</h1>
        <%-- 
        The path follow by JSP are:
        Compilation (Parsing the JSP, turn into Servlet, compile servlet)
        Initialization public void jspInit()
        Execution - void _jspService(HttpServletRequest request, HttpServletResponse response) 
        Cleanup - public void jspDestroy()
        --%>
        <%
         out.println("Your IP address is " + request.getRemoteAddr());
        %>
        
        <%-- JSP Declarations --%>
        <%! int i = 0; %>
        
        <%-- JSP Expressions --%>
        <p>Today's date: <%= (new java.util.Date()).toLocaleString()%></p>
        
        <%-- JSP Directives --%>
        <%--
            <%@ directive attribute="value" %>
            <%@ page ... %>
                The page directive is used to provide instructions to the container. 
                These instructions pertain to the current JSP page
            <%@ include ... %>
                The include directive is used to include a file during the translation phase
                <%@ include file = "relative url" >
            <%@ taglib ... %>
                The JavaServer Pages API allow you to define custom JSP tags that look like HTML or XML 
                tags and a tag library is a set of user-defined tags that implement custom behavior.
                <%@ taglib uri="uri" prefix = "prefixOfTag" >
        --%>
        
        <%-- JSP Actions --%>
        <%--
            <jsp:action_name attribute="value" />
            jsp:include - Includes a file at the time the page is requested.
            jsp:useBean - Finds or instantiates a JavaBean.
            jsp:setProperty - Sets the property of a JavaBean.
            jsp:getProperty - Inserts the property of a JavaBean into the output.
            jsp:forward - Forwards the requester to a new page.
                <jsp:forward page = "Relative URL" />
            jsp:plugin - Generates browser-specific code that makes an OBJECT or EMBED tag for the Java plugin. 
                <jsp:plugin type = "applet" codebase = "dirname" code = "MyApplet.class" width = "60" height = "80">
                <jsp:param name = "fontcolor" value = "red" />
                <jsp:param name = "background" value = "black" />
                <jsp:fallback>
                    Unable to initialize Java Plugin
                </jsp:fallback>
                </jsp:plugin>
            jsp:element - Defines XML elements dynamically.
            jsp:attribute - Defines dynamically-defined XML element's attribute.
            jsp:body - Defines dynamically-defined XML element's body.
            jsp:text - Used to write template text in JSP pages and documents.
        
            There are two attributes that are common to all Action elements: 
            the id attribute and the scope attribute defines the lifespan it may have
            for attributes (a) page, (b)request, (c)session, and (d) application
            
        
        --%>
        
        <%-- JSP Implicit Objects --%>
        <%--
            request - This is the HttpServletRequest object associated with the request.
            response - This is the HttpServletResponse object associated with the response to the client.
            out - This is the PrintWriter object used to send output to the client.
            session - This is the HttpSession object associated with the request.
            application - This is the ServletContext object associated with the application context.
            config - This is the ServletConfig object associated with the page.
            pageContext - This encapsulates use of server-specific features like higher performance JspWriters.
            page - This is simply a synonym for this, and is used to call the methods defined by the translated servlet class.
            Exception - The Exception object allows the exception data to be accessed by designated JSP.
        --%>
        
        <%-- JSP Control Flow statements and Operators --%>
        <%-- They are the same as in Java --%>
        <%  int fontSize = 0;
            while ( fontSize <= 3){ %>
                <font color = "green" size = "<%= fontSize %>">
                    JSP Tutorial
                </font><br />
                <%fontSize++;%>
            <%}%>
      <center>
         <h2>Using JavaBeans in JSP</h2>
         <jsp:useBean id = "jspbean" class = "com.jsptutorial.TestBean" />
         <jsp:setProperty name = "jspbean"  property = "message" 
            value = "Hello JSP..." />          
         <p>Got message....</p>
         <jsp:getProperty name = "jspbean" property = "message" />
      </center>
    <jsp:text>Template data</jsp:text>
    
    <%-- Request Methods
    Cookie[] getCookies()
    Enumeration getAttributeNames()
    Enumeration getHeaderNames()
    Enumeration getParameterNames()
    HttpSession getSession()
    HttpSession getSession(boolean create)
    Locale getLocale()
    Object getAttribute(String name)
    ServletInputStream getInputStream()
    String getAuthType()
    String getCharacterEncoding()
    String getContentType()
    String getContextPath()
    String getHeader(String name)
    String getMethod()
    String getParameter(String name)
    String getPathInfo()
    String getProtocol()
    String getQueryString()
    String getRemoteAddr()
    String getRemoteHost()
    String getRemoteUser()
    String getRequestURI()
    String getRequestedSessionId()
    String getServletPath()
    String[] getParameterValues(String name)
    boolean isSecure()
    int getContentLength()
    int getIntHeader(String name)
    int getServerPort()  
    --%>
    
    <%-- Response method
    String encodeRedirectURL(String url)
    String encodeURL(String url)
    boolean containsHeader(String name)
    boolean isCommitted()
    void addCookie(Cookie cookie)
    void addDateHeader(String name, long date)
    void addHeader(String name, String value)
    void addIntHeader(String name, int value)
    void flushBuffer()
    void resetBuffer()
    void sendError(int sc)
    void sendError(int sc, String msg)
    void sendRedirect(String location)
    void setBufferSize(int size)
    void setCharacterEncoding(String charset)
    void setContentLength(int len)
    void setContentType(String type)
    void setDateHeader(String name, long date)
    void setHeader(String name, String value)
    void setIntHeader(String name, int value)
    void setLocale(Locale loc)
    void setStatus(int sc)
    --%>
<center>
         <h2>HTTP Header Request Example</h2>
         
         <table width = "100%" border = "1" align = "center">
            <tr bgcolor = "#949494">
               <th>Header Name</th>
               <th>Header Value(s)</th>
            </tr>
            <%
               Enumeration headerNames = request.getHeaderNames();
               while(headerNames.hasMoreElements()) {
                  String paramName = (String)headerNames.nextElement();
                  out.print("<tr><td>" + paramName + "</td>\n");
                  String paramValue = request.getHeader(paramName);
                  out.println("<td> " + paramValue + "</td></tr>\n");
               }
            %>
         </table>
      </center>  
    <%-- Response method
    String encodeRedirectURL(String url)
    String encodeURL(String url)
    boolean containsHeader(String name)
    boolean isCommitted()
    void addCookie(Cookie cookie)
    void addDateHeader(String name, long date)
    void addHeader(String name, String value)
    void addIntHeader(String name, int value)
    void flushBuffer()
    void resetBuffer()
    void sendError(int sc)
    void sendError(int sc, String msg)
    void sendRedirect(String location)
    void setBufferSize(int size)
    void setCharacterEncoding(String charset)
    void setContentLength(int len)
    void setContentType(String type)
    void setDateHeader(String name, long date)
    void setHeader(String name, String value)
    void setIntHeader(String name, int value)
    void setLocale(Locale loc)
    void setStatus(int sc)
    --%>         
      <center>
         <h2>Auto Refresh Header Example</h2>
         <%
            // Set refresh, autoload time as 5 seconds
            response.setIntHeader("Refresh", 5);
            
            // Get current time
            Calendar calendar = new GregorianCalendar();
            
            String am_pm;
            int hour = calendar.get(Calendar.HOUR);
            int minute = calendar.get(Calendar.MINUTE);
            int second = calendar.get(Calendar.SECOND);
            
            if(calendar.get(Calendar.AM_PM) == 0) 
               am_pm = "AM";
            else
               am_pm = "PM";
               String CT = hour+":"+ minute +":"+ second +" "+ am_pm;
               out.println("Current Time is: " + CT + "\n");
         %>
      </center>
        <%-- HTTP Status Codes
        It consists of :
        An initial status line + CRLF (Carriage Return + Line Feed ie. New Line)
        Zero or more header lines + CRLF
        A blank line ie. a CRLF
        An optional message body like file, query data or query output.
        
        public void setStatus ( int statusCode )
        public void sendRedirect(String url)
        public void sendError(int code, String message)      
        --%>
        <%--
         // Set error code and reason.
         response.sendError(407, "Need authentication!!!" );
        --%>  
        
    </body>
</html>
