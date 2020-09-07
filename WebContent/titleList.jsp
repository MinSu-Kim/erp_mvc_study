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
    $('#addTitle').on("click", function(){
        $.ajax({
            type:"get",
            url:"titleNextNo.do",
            success: function(data){
                $(location).attr('href', 'titleAdd.jsp?nextNo='+data);
                /* window.location.href = "titleAdd.jsp?nextNo="+data; */
            }
        });
    });
});
</script>

</head>
<body>
    <h2>사원목록</h2>
    <table border=1>
        <thead>
            <td>직책번호</td>
            <td>직책명</td>
        </thead>
        <tbody>
            <c:forEach var="title" items="${list}">
                <tr>
                    <td><c:out value="${title.titleNo }"/></td>
                    <td><a href='getTitle.do?titleNo=${title.titleNo}'>${title.titleName}</a></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <button id="addTitle">직책 추가</button>
</body>
</html>