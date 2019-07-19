<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	
	<table>
		
 
	</table>
	
	<h1>Shiro Success Page</h1>
	
	Welcome: <shiro:principal/>
	<br><br>
	
	<shiro:hasAnyRoles name="user,admin">
		<a href="shiro-users.ftl">User Page</a>
	</shiro:hasAnyRoles>
	
	<shiro:hasRole name="a,b">

	</shiro:hasRole>

	<shiro:hasPermission name="admin.jsp">
		<a >aaaaa</a>
	</shiro:hasPermission>
	
		
	<br><br>
	
	<shiro:hasAnyRoles name="admin">
		<a href="admin.jsp">Admin Page</a>
	</shiro:hasAnyRoles>
	
	<br><br>
	<a href="shiro-logout">Logout</a>
	
</body>
</html>