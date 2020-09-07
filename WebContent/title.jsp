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
            $('input#titleName').attr("readonly", false);
            status = true;
        }else{
            var newTitle = {titleNo:$('#titleNo').val(), titleName:$('#titleName').val()};     
            $.ajax({
                type:"post",
                url: "updateTitle.do",
                data: JSON.stringify(newTitle),
                success: function(data){
                    alert(data);
                    if (data == 1){
                        alert('수정 되었습니다.');
                    }
                    window.location.href = "titleList.do";
                }
            });
        }
    });
    
    $('#list').on("click", function(){
        window.location.href = "titleList.do";
    });
    
    $('#delete').on("click", function(){
        var newTitle = {titleNo:$('#titleNo').val()};       
        $.ajax({
            type:"get",
            url:"removeTitle.do",
            data:newTitle,
            success: function(data){
                alert(data);
                if (data == 1){
                    alert('삭제 되었습니다.');
                }
                window.location.href = "titleList.do";
            }
        });
    });
});
</script>

<fieldset>
    <legend>직책 정보</legend>
    <ul>
        <li>
            <label for="titleNo">직책번호</label> 
            <input id="titleNo" type="text" name="titleNo" placeholder="직책 번호" value="${title.titleNo }" readonly>
        </li>
        <li>
            <label for="titleName">직책명</label> 
            <input id="titleName" type="text" name="titleName" placeholder="숫자/문자" value="${title.titleName }" readonly>
        </li>
        <li>
            <button id="modify">수정</button>
            <button id="delete">삭제</button>
            <button id="list">목록</button>
        </li>
    </ul>
</fieldset>
