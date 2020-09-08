package erp_mvc_study.mvc.model;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import erp_mvc_study.dto.Department;
import erp_mvc_study.mvc.controller.CommandHandler;
import erp_mvc_study.service.DeptService;

public class DepartmentAddHandler implements CommandHandler {
//    private static final String FORM_VIEW = "titleAdd.do";
    private DeptService service;

    public DepartmentAddHandler() {
        service = new DeptService();
    }

    @Override
    public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
        System.out.println("req.getMethod() > " + req.getMethod());
        if (req.getMethod().equalsIgnoreCase("GET")) {
            return processForm(req, res);
        } else if (req.getMethod().equalsIgnoreCase("POST")) {
            return processSubmit(req, res);
        } else {
            res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
            return null;
        }
    }

    private String processForm(HttpServletRequest req, HttpServletResponse res) throws IOException {
        System.out.println("processForm()");
        int deptNo = service.getNextDeptNo();
        req.setAttribute("deptNo", deptNo);
        PrintWriter pw = res.getWriter();
        pw.print(deptNo);
        pw.flush();
        return null;
    }

    private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws IOException {
        System.out.println("processSubmit()");
        Gson gson = new Gson();
        Department dept = gson.fromJson(new InputStreamReader(req.getInputStream(), "UTF-8"), Department.class);
        int result = service.addDepartment(dept);
        
        res.setStatus(HttpServletResponse.SC_ACCEPTED);
        PrintWriter pw = res.getWriter();
        pw.print(result);
        pw.flush();

        return null;
    }

}
