package horny.deers;

import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.api.ContentResponse;
import org.eclipse.jetty.client.api.Request;
import org.eclipse.jetty.client.util.StringContentProvider;
import org.eclipse.jetty.http.HttpHeader;
import org.junit.Test;

import static org.eclipse.jetty.http.HttpStatus.OK_200;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SubscribeTest
{
    @Test
    public void shouldTellOk() throws Exception {
        try (Backend backend = new Backend().start()) {
            HttpClient httpClient = new HttpClient();
            httpClient.start();

            final Request request = httpClient.POST("http://localhost:9090/subscribe");
            request.header(HttpHeader.CONTENT_TYPE, "application/json");
            request.content(new StringContentProvider("{\"userId\":\"cittadino\"}","utf-8"));
            ContentResponse response = request.send();

            assertThat(response.getStatus(), is(OK_200));
            assertThat(response.getContentAsString(), is("Subscribed: cittadino"));
        }
    }
}
