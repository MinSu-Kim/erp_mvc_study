-- 접속자 확인
SELECT USER FROM DUAL;

-- 접속한 계정의 테이블 목록 조회
SELECT TABLE_NAME 
  FROM USER_TABLES;
 
-- 접속한 계정의 테이블별 컬럼 조회
SELECT TABLE_NAME, COLUMN_NAME, DATA_TYPE, DATA_PRECISION, DATA_LENGTH, NULLABLE, DATA_DEFAULT
  FROM USER_TAB_COLUMNS
 WHERE TABLE_NAME IN ('EMPLOYEE', 'DEPARTMENT', 'TITLE');

-- 접속한 계정의 테이블별 제약조건 조회
SELECT TABLE_NAME, CONSTRAINT_NAME, CONSTRAINT_TYPE, SEARCH_CONDITION, DELETE_RULE
  FROM USER_CONSTRAINTS 
 WHERE TABLE_NAME IN ('EMPLOYEE', 'DEPARTMENT', 'TITLE');
 
-- 데이터 확인
SELECT * FROM TITLE;
SELECT DEPT_NO, DEPT_NAME, FLOOR FROM DEPARTMENT;
SELECT * FROM EMPLOYEE;

-- employee
SELECT e.EMP_NO, e.EMP_NAME, e.TNO, e.MANAGER, e.SALARY, e.DNO, e.REGDATE, e.EMAIL , e.PASSWD , e.PIC_URL, 
       t.TITLE_NAME, d.DEPT_NAME, m.EMP_NAME MANAGER_NAME
  FROM EMPLOYEE e JOIN TITLE t ON e.TNO = t.TITLE_NO 
       LEFT JOIN EMPLOYEE m ON e.MANAGER = m.EMP_NO 
       JOIN DEPARTMENT d ON e.DNO = d.DEPT_NO;

CREATE OR REPLACE VIEW VW_EMPLOYEEJOIN AS 
SELECT e.EMP_NO, e.EMP_NAME, e.TNO, e.MANAGER AS MANAGER_NO, e.SALARY, e.DNO, e.REGDATE, e.EMAIL , e.PASSWD , e.PIC_URL, 
       t.TITLE_NAME, d.DEPT_NAME, m.EMP_NAME MANAGER_NAME
  FROM EMPLOYEE e JOIN TITLE t ON e.TNO = t.TITLE_NO 
       LEFT JOIN EMPLOYEE m ON e.MANAGER = m.EMP_NO 
       JOIN DEPARTMENT d ON e.DNO = d.DEPT_NO;   

SELECT *
  FROM VW_EMPLOYEE_JOIN;
  
SELECT EMP_NO, EMP_NAME, TNO, MANAGER, SALARY, DNO, REGDATE, EMAIL, TEL, PIC_URL, TITLE_NAME, DEPT_NAME, MANAGER_NAME FROM VW_EMPLOYEE_JOIN;
   
SELECT EMP_NO , EMP_NAME , TNO , MANAGER , SALARY , DNO , HIREDATE 
  FROM EMPLOYEE
 WHERE EMP_NO = 4377;
 
INSERT INTO EMPLOYEE(emp_no, emp_name, tno, manager, salary, dno, hiredate)
VALUES(1004, '박규영', 5, 3426, 1500000, 1, sysdate);

DELETE FROM EMPLOYEE WHERE emp_no = 1004;

-- department
SELECT DEPT_NO, DEPT_NAME, FLOOR FROM DEPARTMENT;

-- title
SELECT TITLE_NO, TITLE_NAME FROM TITLE;


SELECT 1
  FROM EMPLOYEE
 WHERE EMP_NO = 4377 and pwd = '1254';
 
SELECT 1
  FROM EMPLOYEE
 WHERE EMP_NO = 4377 and pwd = '1234';
 
SELECT EMP_NO, EMP_NAME, TNO, MANAGER, SALARY, DNO, EMAIL, REGDATE, TEL, PASSWD
  FROM EMPLOYEE 
WHERE EMAIL = 'test@test.co.kr' AND PASSWD = '1234';

UPDATE EMPLOYEE SET TNO=?, MANAGER=?, SALARY=?, DNO=?, PASSWD=?, TEL=? WHERE EMAIL=?;
