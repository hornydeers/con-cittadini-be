package horny.deers;

import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.api.ContentResponse;
import org.eclipse.jetty.client.api.Request;
import org.eclipse.jetty.client.util.StringContentProvider;
import org.eclipse.jetty.http.HttpHeader;
import org.junit.Test;

import static org.eclipse.jetty.http.HttpStatus.NOT_FOUND_404;
import static org.eclipse.jetty.http.HttpStatus.OK_200;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class UserTest
{
    @Test
    public void shouldGiveNotFoundOnUnknownUser() throws Exception {
        try (Backend backend = new Backend().start()) {
            HttpClient httpClient = new HttpClient();
            httpClient.start();

            final ContentResponse response = httpClient.GET("http://localhost:9090/user?userId=unknown");

            assertThat(response.getStatus(), is(NOT_FOUND_404));
        }
    }

    @Test
    public void shouldGiveKnownUser() throws Exception {
        try (Backend backend = new Backend().start()) {
            HttpClient httpClient = new HttpClient();
            httpClient.start();

            final Request request1 = httpClient.POST("http://localhost:9090/subscribe");
            request1.header(HttpHeader.CONTENT_TYPE, "application/json");
            request1.content(new StringContentProvider("{\"userId\":\"cittadino\",\"buttonId\":\"il-bottone-magico\",\"latitude\":\"3.456\",\"longitude\":\"-10.23\"}","utf-8"));
            ContentResponse response1 = request1.send();

            final ContentResponse response = httpClient.GET("http://localhost:9090/user?userId=cittadino");

            assertThat(response.getStatus(), is(OK_200));
            assertThat(response.getContentAsString(), is("{\"buttonId\":\"il-bottone-magico\",\"latitude\":\"3.456\",\"longitude\":\"-10.23\"}"));
        }
    }
}
