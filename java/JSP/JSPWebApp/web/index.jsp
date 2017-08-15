<%-- 
    Document   : JSPage
    Created on : Aug 10, 2017, 2:11:58 PM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        Compilation (Prasing the JSP, turn into Servlet, compile servlet)
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
            jsp:plugin - Generates browser-specific code that makes an OBJECT or EMBED tag for the Java plugin.
            jsp:element - Defines XML elements dynamically.
            jsp:attribute - Defines dynamically-defined XML element's attribute.
            jsp:body - Defines dynamically-defined XML element's body.
            jsp:text - Used to write template text in JSP pages and documents.
        
            There are two attributes that are common to all Action elements: 
            the id attribute and the scope attribute  (a) page, (b)request, (c)session, and (d) application
            
        
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
    </body>
</html>
