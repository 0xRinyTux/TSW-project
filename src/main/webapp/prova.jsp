<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%String username = (String) session.getAttribute("username");%>
<% if(username != null) %>
<%= username%>
<% Cookie[] cookies = request.getCookies();%>
<%!int i;%>
<% for(i = 0; i < cookies.length; i++){%>
<%= cookies[i].getValue()%>
<%}%>

</body>
</html>
