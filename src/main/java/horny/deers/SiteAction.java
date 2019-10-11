package horny.deers;

import org.eclipse.jetty.server.Request;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class SiteAction {
    protected void writeResponseMessage(Request baseRequest, HttpServletResponse response, String content, int status) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(status);
        baseRequest.setHandled(true);
        response.getWriter().print(content);
    }
}
