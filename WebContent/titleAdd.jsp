<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="nextNo" value="${request.get }"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>직책 추가</title>
<script type="text/javascript" src="js/jquery-3.4.1.min.js"></script>
<script>
$(function() {
 
    $('#reg').on("click", function(){
        var newTitle = {
                titleNo:   $('#titleNo').val(), 
                titleName: $('#titleName').val() };
        
        $.ajax({
            type : "POST",
            url : "titleAdd.do",
            cache:false,
            data : JSON.stringify(newTitle),
            complete : function(data){
                    alert("추가하였습니다.");
                    window.location.href = "titleList.do";
            }
        });

    });
    
});

</script>
</head>
<body>
    <div>
        <label for="titleNo">직책번호</label> 
        <input name='titleNo' id="titleNo" value="${param.nextNo }">
    </div>
    <div>
        <label for="titleName">직책명</label> 
        <input name='titleName' id="titleName" />
    </div>
    <div>
        <button id="reg">직책 추가</button>
        <button type="reset">취소</button>
    </div>
</body>
</html>