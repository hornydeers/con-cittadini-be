package horny.deers;

import org.apache.log4j.Logger;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Handler extends AbstractHandler
{
    private static Map<String, UserInfo> _users = new HashMap<>();

    public void handle(String target,
                       Request baseRequest,
                       HttpServletRequest request,
                       HttpServletResponse response)
            throws IOException, ServletException
    {
        final Logger logger = Logger.getLogger(this.getClass());

        logger.info("Received request: " + baseRequest.getOriginalURI());

        if ("/subscribe".equals(target)) {
            new SubscribeAction(_users).process(baseRequest, request, response);
            return;
        }
        new AliveAction().process(baseRequest, request, response);
    }
}
