package horny.deers;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Handler extends AbstractHandler
{
    private static List<String> _userIds = new ArrayList<>();

    public void handle(String target,
                       Request baseRequest,
                       HttpServletRequest request,
                       HttpServletResponse response)
            throws IOException, ServletException
    {
        if ("/subscribe".equals(target)) {
            new SubscribeAction(_userIds).process(baseRequest, request, response);
            return;
        }
        new AliveAction().process(baseRequest, request, response);
    }
}
