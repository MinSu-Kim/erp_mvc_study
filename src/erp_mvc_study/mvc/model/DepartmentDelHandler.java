package erp_mvc_study.mvc.model;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import erp_mvc_study.dto.Department;
import erp_mvc_study.mvc.controller.CommandHandler;
import erp_mvc_study.service.DeptService;

public class DepartmentDelHandler implements CommandHandler {
    private DeptService service;

    public DepartmentDelHandler() {
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
        int deptNo = Integer.parseInt(req.getParameter("deptNo").trim());
        
        int nextNo = service.delDepartment(new Department(deptNo));
        System.out.println("delete : " + nextNo);
        res.setStatus(HttpServletResponse.SC_ACCEPTED);
        PrintWriter pw = res.getWriter();
        pw.print(nextNo);
        pw.flush();
        return null;
    }

    private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws IOException {
        System.out.println("processSubmit()");
        
        return null;
    }

}
