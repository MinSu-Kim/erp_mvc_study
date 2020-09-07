package erp_mvc_study.mvc.model;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import erp_mvc_study.dto.Title;
import erp_mvc_study.mvc.controller.CommandHandler;
import erp_mvc_study.service.TitleService;

public class TitleAddHandler implements CommandHandler {
//    private static final String FORM_VIEW = "titleAdd.do";
    private TitleService service;

    public TitleAddHandler() {
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
        int nextNo = service.getNextTitleNo();
        req.setAttribute("nextNo", nextNo);
        PrintWriter pw = res.getWriter();
        pw.print(nextNo);
        pw.flush();
        return null;
    }

    private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws IOException {
        System.out.println("processSubmit()");
        Gson gson = new Gson();
        Title newTitle = gson.fromJson(new InputStreamReader(req.getInputStream(), "UTF-8"), Title.class);
        int result = service.addTitle(newTitle);
        
        res.setStatus(HttpServletResponse.SC_ACCEPTED);
        PrintWriter pw = res.getWriter();
        pw.print(result);
        pw.flush();

        return null;
    }

}
