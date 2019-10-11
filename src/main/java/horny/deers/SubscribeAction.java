package horny.deers;

import org.eclipse.jetty.server.Request;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class SubscribeAction {
    private List<String> _userIds;

    public SubscribeAction(List<String> userIds) {
        _userIds = userIds;
    }

    public void process(Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String userId = request.getParameter("userId");
        if (!_userIds.contains(userId)) {
            _userIds.add(userId);
            writeResponseMessage(baseRequest, response, "Subscribed: " + userId);
        }
        else {
            writeResponseMessage(baseRequest, response, "User already exists");
        }
    }

    private void writeResponseMessage(Request baseRequest, HttpServletResponse response, String content) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        baseRequest.setHandled(true);
        response.getWriter().print(content);
    }
}
