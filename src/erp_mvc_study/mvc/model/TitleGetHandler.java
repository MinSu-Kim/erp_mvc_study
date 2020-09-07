package erp_mvc_study.mvc.model;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import erp_mvc_study.dto.Title;
import erp_mvc_study.mvc.controller.CommandHandler;
import erp_mvc_study.service.TitleService;

public class TitleGetHandler implements CommandHandler {
    private static final String FORM_VIEW = "title.jsp";
    private TitleService service;

    public TitleGetHandler() {
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
        Title title = service.getTitle(new Title(titleNo));

        req.setAttribute("title", title);
        return FORM_VIEW;
    }

    private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws IOException {
        /*System.out.println("processSubmit()");
        Gson gson = new Gson();
        Title newTitle = gson.fromJson(new InputStreamReader(req.getInputStream(), "UTF-8"), Title.class);
        int result = service.addTitle(newTitle);
        
        PrintWriter pw = res.getWriter();
        pw.print(result);
        pw.flush();*/

        return null;
    }

}
