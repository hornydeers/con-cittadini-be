package horny.deers;

import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.api.ContentResponse;
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

            ContentResponse response1 = httpClient.GET("http://localhost:9090/subscribe?userId=cittadino");

            assertThat(response1.getStatus(), is(OK_200));
            assertThat(response1.getContentAsString(), is("Subscribed: cittadino"));

            ContentResponse response2 = httpClient.GET("http://localhost:9090/subscribe?userId=cittadino");

            assertThat(response2.getStatus(), is(OK_200));
            assertThat(response2.getContentAsString(), is("User already exists"));
        }
    }
}
