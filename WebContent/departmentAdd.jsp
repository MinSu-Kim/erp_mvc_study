<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>직책 추가</title>
<script type="text/javascript" src="js/jquery-3.4.1.min.js"></script>
<script>
$(function() {
 
    $('#reg').on("click", function(){
        var department = {
                deptNo:   $('#deptNo').val(), 
                deptName: $('#deptName').val(),
                floor: $('#floor').val()
                };
        
        $.ajax({
            type : "POST",
            url : "deptAdd.do",
            cache:false,
            data : JSON.stringify(department),
            complete : function(data){
                    alert("추가하였습니다.");
                    window.location.href = "deptList.do";
            }
        });

    });
    
});

</script>
</head>
<body>
    <div>
        <label for="deptNo">부서번호</label> 
        <input type="number" name='deptNo' id="deptNo" value="${param.nextNo }">
    </div>
    <div>
        <label for="deptName">부서명</label> 
        <input type="text" name='deptName' id="deptName" />
    </div>
    <div>
        <label for="floor">위치</label> 
        <input type="number" name='floor' id="floor" />
    </div>
    <div>
        <button id="reg">부서 추가</button>
        <button type="reset">취소</button>
    </div>
</body>
</html>