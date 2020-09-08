<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사원 목록</title>
<link rel="stylesheet" href="css/main.css" />
<script type="text/javascript" src="js/jquery-3.4.1.min.js"></script>

<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.24.0/moment.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.24.0/locale/ko.js"></script>

<script>
$(function(){
    
    $('#addEmp').on("click", function(){
        self.location = "empAdd.jsp";
    });
     

     
     $.post("empList.do", function(json){
         var dataLength = json.length;
         if ( dataLength >=1 ){
             var sCont = "";
             for ( i=0 ; i < dataLength ; i++){
                sCont += "<tr>";
                sCont += "<td>" + json[i].empNo + "</td>";
                sCont += "<td>" + json[i].empName + "</td>";
                sCont += "<td>" + json[i].title.titleName + "("+ json[i].title.titleNo + ")</td>";
                if (json[i].manager.empNo != 0){
                    sCont += "<td>" + json[i].manager.empName + "("+ json[i].manager.empNo + ")</td>";
                }else{
                    sCont += "<td></td>"; 
                }
                sCont += "<td>" + json[i].salary + "</td>";
                sCont += "<td>" + json[i].dept.deptName + "("+ json[i].dept.deptNo + ")</td>";
                sCont += "<td>" + moment(json[i].regDate).format('LL') + "</td>";
                sCont += "<td>" + json[i].email + "</td>";
                sCont += "</tr>";
            }
            $("table > tbody:last-child").append(sCont);   
        } 
     });
});
</script>

</head>
<body>
    <h2>사원목록</h2>
    <table>
        <thead>
            <td>사원번호</td><td>사원명</td><td>직책</td><td>직속상사</td><td>급여</td><td>부서</td> <td>입사일</td> <td>이메일</td>
        </thead>
        <tbody>

        </tbody>
    </table>
    <button id="addEmp">사원 추가</button>
</body>
</html>