<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/main.css" />
<script src="js/jquery-3.4.1.min.js"></script>
<script>
$(function(){
    $('#addDepartment').on("click", function(){
        $.ajax({
            type:"get",
            url:"deptNextNo.do",
            success: function(data){
                $(location).attr('href', 'departmentAdd.jsp?nextNo='+data);
            }
        });
    });
});
</script>

</head>
<body>
    <h2>부서목록</h2>
    <table>
        <thead>
            <td>부서번호</td>
            <td>부서명</td>
            <td>위치</td>
        </thead>
        <tbody>
            <c:forEach var="dept" items="${list}">
                <tr>
                    <td><c:out value="${dept.deptNo }"/></td>     
                    <td><a href='getDepartment.do?deptNo=${dept.deptNo }'>${dept.deptName}</a></td>
                    <td><c:out value="${dept.floor }"/></td> 
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <button id="addDepartment">부서 추가</button>
</body>
</html>