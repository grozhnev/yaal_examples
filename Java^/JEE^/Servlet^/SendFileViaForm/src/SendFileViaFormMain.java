import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

/**
 * Отправка файла через форму на html-странице и его сохранение в файл
 * на сервере с помощью сервлета.
 */
public class SendFileViaFormMain {
    public static void main(String[] args) throws Exception {
        ContextHandler formPageHandler = new ContextHandler("/form");
        formPageHandler.setHandler(new FormPageHandler());

        ServletHolder holder = new ServletHolder(HelloWorldServlet.class);
        ServletContextHandler uploadContext = new ServletContextHandler(ServletContextHandler.SESSIONS);
        uploadContext.addServlet(holder, "/upload");

        ContextHandlerCollection contextHandlers = new ContextHandlerCollection();
        contextHandlers.setHandlers(new Handler[]{formPageHandler, uploadContext});

        Server server = new Server(6666);
        server.setHandler(contextHandlers);
        server.start();
        server.join();
    }
}