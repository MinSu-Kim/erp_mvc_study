package erp_mvc_study.mvc.model;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import erp_mvc_study.dto.Department;
import erp_mvc_study.mvc.controller.CommandHandler;
import erp_mvc_study.service.DeptService;

public class DepartmentGetHandler implements CommandHandler {
    private static final String FORM_VIEW = "department.jsp";
    private DeptService service;

    public DepartmentGetHandler() {
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
        Department dept = service.getDepartment(new Department(deptNo));

        req.setAttribute("dept", dept);
        return FORM_VIEW;
    }

    private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws IOException {

        return null;
    }

}
