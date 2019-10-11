package horny.deers;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.apache.commons.io.IOUtils;
import org.eclipse.jetty.server.Request;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static javax.servlet.http.HttpServletResponse.SC_OK;

public class SubscribeAction extends SiteAction {
    private Map<String, UserInfo> _users;

    public SubscribeAction(Map<String, UserInfo> users) {
        _users = users;
    }

    public void process(Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String body = IOUtils.toString(request.getReader());
        final JsonObject values = new Gson().fromJson(body, JsonObject.class);
        final String userId = stringValue(values, "userId");
            _users.put(userId, new UserInfo(
                    stringValue(values, "buttonId"),
                    stringValue(values, "latitude"),
                    stringValue(values, "longitude")));

        writeResponseMessage(baseRequest, response, "", SC_OK);
    }

    private String stringValue(JsonObject values, String key) {
        return values.get(key).getAsString();
    }

}
