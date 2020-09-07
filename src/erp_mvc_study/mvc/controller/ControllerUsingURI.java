package erp_mvc_study.mvc.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(
        name = "ControllerUsingURI", 
        urlPatterns = { "*.do" }, 
        loadOnStartup = 1, 
        initParams = { 
                @WebInitParam(
                        name = "configFile", 
                        value = "/WEB-INF/commandHandler.properties") 
                }
        )
public class ControllerUsingURI extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // <command, ActionInstance>
    private Map<String, CommandHandler> actionHandlerMap = new HashMap<>();

    @Override
    public void init(ServletConfig config) throws ServletException {
        String configFile = config.getInitParameter("configFile");

        try (InputStream is = config.getServletContext().getResourceAsStream(configFile)) {
            Properties props = new Properties();
            props.load(is);

            Iterator<Object> keyIter = props.keySet().iterator();
            while (keyIter.hasNext()) {
                String command = (String) keyIter.next();
                String handlerClassName = props.getProperty(command);
                Class<?> handlerClass = Class.forName(handlerClassName);
                CommandHandler handlerInstance = (CommandHandler) handlerClass.newInstance();
                actionHandlerMap.put(command, handlerInstance);
            }
            for(Entry<String, CommandHandler> e : actionHandlerMap.entrySet()) {
                System.out.printf("%s -> %s%n", e.getKey(), e.getValue());
            }

        } catch (IOException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        process(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        process(request, response);
    }

    private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String command = request.getRequestURI();
        System.out.println("command : " + command);
        
        if (command.indexOf(request.getContextPath()) == 0) {
            command = command.substring(request.getContextPath().length());
        }
        
        
        CommandHandler handler = actionHandlerMap.get(command);
        System.out.println("handler : " + handler);
        
        if (handler == null) {
            handler = new NullHandler();
        }
        
        String viewPage = null;
        try {
            viewPage = handler.process(request, response);
        } catch (Throwable e) {
            throw new ServletException(e);
        }
        
        System.out.println("viewPage > " + viewPage);
        
        if (viewPage != null) {
            request.getRequestDispatcher(viewPage).forward(request, response);
        }
    }

}
