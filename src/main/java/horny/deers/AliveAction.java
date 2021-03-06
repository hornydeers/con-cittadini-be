package horny.deers;

import org.eclipse.jetty.server.Request;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static javax.servlet.http.HttpServletResponse.SC_OK;

public class AliveAction extends SiteAction {
    public void process(Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException {
        writeResponseMessage(baseRequest, response, "OK", SC_OK);
    }
}
