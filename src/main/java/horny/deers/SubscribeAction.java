package horny.deers;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.apache.commons.io.IOUtils;
import org.eclipse.jetty.server.Request;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static javax.servlet.http.HttpServletResponse.SC_OK;

public class SubscribeAction extends SiteAction {
    private List<String> _userIds;

    public SubscribeAction(List<String> userIds) {
        _userIds = userIds;
    }

    public void process(Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String body = IOUtils.toString(request.getReader());
        final JsonObject values = new Gson().fromJson(body, JsonObject.class);
        String userId = values.get("userId").getAsString();
        if (!_userIds.contains(userId)) {
            _userIds.add(userId);
            writeResponseMessage(baseRequest, response, "Subscribed: " + userId, SC_OK);
        }
        else {
            writeResponseMessage(baseRequest, response, "User already exists", SC_OK);
        }
    }
}
