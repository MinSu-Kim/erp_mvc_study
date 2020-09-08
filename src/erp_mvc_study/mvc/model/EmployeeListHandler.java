package erp_mvc_study.mvc.model;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import erp_mvc_study.dto.Employee;
import erp_mvc_study.mvc.controller.CommandHandler;
import erp_mvc_study.service.EmpService;

public class EmployeeListHandler implements CommandHandler {
    private static final String FORM_VIEW = "empList.jsp";
    private EmpService service;
    
    public EmployeeListHandler() {
        service = new EmpService();
    }

    @Override
    public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
        if (req.getMethod().equalsIgnoreCase("GET")) {
            return processForm(req, res);
        }else if (req.getMethod().equalsIgnoreCase("POST")) {
            return processSubmit(req, res);
        }else {
            res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
            return null;
        }
    }

    private String processForm(HttpServletRequest req, HttpServletResponse res) throws IOException {
        System.out.println("get processForm()");
        List<Employee> list = service.getEmpList();
        Gson gson = new Gson(); 
        String result = gson.toJson(list, new TypeToken<List<Employee>>(){}.getType());
        System.out.println(result);
        req.setAttribute("list", list);
        res.setStatus(HttpServletResponse.SC_ACCEPTED);
        
        return FORM_VIEW;
    }

    private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws IOException {
        System.out.println("post processSubmit()");
        List<Employee> list = service.getEmpList();
        Gson gson = new Gson(); 
        String result = gson.toJson(list, new TypeToken<List<Employee>>(){}.getType());
        System.out.println(result);
                
        res.setContentType("application/json");
        res.setStatus(HttpServletResponse.SC_ACCEPTED);
        
        PrintWriter pw = res.getWriter();
        pw.print(result);
        pw.flush();
        return null;
    }

}
