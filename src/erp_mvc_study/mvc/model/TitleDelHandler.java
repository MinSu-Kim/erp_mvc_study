package erp_mvc_study.mvc.model;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import erp_mvc_study.dto.Title;
import erp_mvc_study.mvc.controller.CommandHandler;
import erp_mvc_study.service.TitleService;

public class TitleDelHandler implements CommandHandler {
//    private static final String FORM_VIEW = "titleList.do";
    private TitleService service;

    public TitleDelHandler() {
        service = new TitleService();
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
        int titleNo = Integer.parseInt(req.getParameter("titleNo").trim());
        
        int nextNo = service.delTitle(new Title(titleNo));
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
