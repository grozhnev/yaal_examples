package handler.error;

import org.eclipse.jetty.http.HttpFields;
import org.eclipse.jetty.server.NetworkConnector;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ErrorHandler;
import org.eclipse.jetty.servlet.ServletHandler;
import org.junit.Ignore;
import org.junit.Test;
import util.NetAsserts;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.ByteBuffer;

/**
 *
 */
public class ErrorHandlerTest {

    @Test
    @Ignore("does not work")
    public void test() throws Exception {
        ServletHandler handler = new ServletHandler();
        String path = "/error";
        handler.addServletWithMapping(ErrorServlet.class, path);

        ErrorHandler errorHandler = new ErrorHandler() {
            @Override
            public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException {
                System.out.println("Handle error");
            }

            @Override
            public ByteBuffer badMessageError(int status, String reason, HttpFields fields) {
                System.out.println("Handle bad message");
                return ByteBuffer.wrap(new byte[0]);
            }
        };

        Server server = new Server(0);
        server.setHandler(handler);
        server.setErrorHandler(errorHandler);
        server.addBean(errorHandler);
        server.start();
        int port = ((NetworkConnector) server.getConnectors()[0]).getLocalPort();

        NetAsserts.assertUrlContent("http://localhost:" + port + path, "Star servlet");

        server.stop();
    }
}