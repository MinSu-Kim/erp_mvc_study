package erp_mvc_study.mvc.model;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import erp_mvc_study.dto.Department;
import erp_mvc_study.mvc.controller.CommandHandler;
import erp_mvc_study.service.DeptService;

public class DepartmentListHandler implements CommandHandler {
    private static final String FORM_VIEW = "deptList.jsp";
    private DeptService service;
    
    public DepartmentListHandler() {
        service = new DeptService();
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

    private String processForm(HttpServletRequest req, HttpServletResponse res) {
        System.out.println("get processForm()");
        List<Department> list = service.getDepartmentList();
        list.stream().forEach(System.out::println);
        req.setAttribute("list", list);
        return FORM_VIEW;
    }

    private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws IOException {
        /*        System.out.println("post processSubmit()");
        List<Title> list = service.getTitleList();
        Gson gson = new Gson(); 
        String result = gson.toJson(list, new TypeToken<List<Title>>(){}.getType());
        System.out.println(result);
                
        res.setContentType("application/json");
        res.setCharacterEncoding("UTF-8");
        
        PrintWriter pw = res.getWriter();
        pw.print(result);
        pw.flush(); */
        return null;
    }

}
