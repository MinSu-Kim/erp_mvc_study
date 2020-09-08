<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href="css/main.css" />
<script src="js/jquery-3.4.1.min.js"></script>

<script>
$(function(){
    var status = false;
    
    $('#modify').on("click", function(){
        alert(!status);
        if (!status){
            alert("status > " + status)
            $('input#deptName').attr("readonly", false);
            $('input#floor').attr("readonly", false);
            status = true;
        }else{
            var dept = {deptNo:$('#deptNo').val(), deptName:$('#deptName').val(), floor:$('#floor').val()};     
            $.ajax({
                type:"post",
                url: "updateDept.do",
                data: JSON.stringify(dept),
                success: function(data){
                    alert(data);
                    if (data == 1){
                        alert('수정 되었습니다.');
                    }
                    window.location.href = "deptList.do";
                }
            });
        }
    });
    
    $('#list').on("click", function(){
        window.location.href = "deptList.do";
    });
    
    $('#delete').on("click", function(){
        var dept = {deptNo:$('#deptNo').val()};       
        $.ajax({
            type:"get",
            url:"removeDept.do",
            data:dept,
            success: function(data){
                alert(data);
                if (data == 1){
                    alert('삭제 되었습니다.');
                }
                window.location.href = "deptList.do";
            }
        });
    });
});
</script>

<fieldset>
    <legend>직책 정보</legend>
    <ul>
        <li>
            <label for="deptNo">직책번호</label> 
            <input id="deptNo" type="text" name="deptNo" placeholder="부서번호" value="${dept.deptNo }" readonly>
        </li>
        <li>
            <label for="deptName">직책명</label> 
            <input id="deptName" type="text" name="deptName" placeholder="숫자/문자" value="${dept.deptName }" readonly>
        </li>
        <li>
            <label for="floor">직책명</label> 
            <input id="floor" type="text" name="floor" placeholder="숫자/문자" value="${dept.floor }" readonly>
        </li>
        <li>
            <button id="modify">수정</button>
            <button id="delete">삭제</button>
            <button id="list">목록</button>
        </li>
    </ul>
</fieldset>
