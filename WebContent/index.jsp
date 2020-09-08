<%@page import="erp_mvc_study.ds.HikariJNDI"%>
<%@page import="java.sql.Connection"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<c:set var="con" value="${HikariJNDI.getConnection()}"/>
<c:out value="${con}"/><br>
<a href="titleList.do">직책 목록</a>
<a href="deptList.do">부서 목록</a>
</body>
</html>