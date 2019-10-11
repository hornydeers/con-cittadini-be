package horny.deers;

import com.google.gson.Gson;
import org.eclipse.jetty.server.Request;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class UserAction extends SiteAction {
    private Map<String, UserInfo> _users;

    public UserAction(Map<String, UserInfo> users) {
        _users = users;
    }

    public void process(Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException {
        final String userId = request.getParameter("userId");
        if (_users.containsKey(userId)) {
            writeResponseMessage(baseRequest, response, new Gson().toJson(_users.get(userId)), 200);
            return;
        }
        response.setStatus(404);
    }
}
