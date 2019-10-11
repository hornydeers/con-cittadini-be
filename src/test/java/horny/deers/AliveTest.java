package horny.deers;

import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.api.ContentResponse;
import org.junit.Test;

import static org.eclipse.jetty.http.HttpStatus.OK_200;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class AliveTest
{
    @Test
    public void shouldTellOk() throws Exception {
        try (Backend backend = new Backend().start()) {
            HttpClient httpClient = new HttpClient();
            httpClient.start();

            ContentResponse response = httpClient.GET("http://localhost:9090");

            assertThat(response.getStatus(), is(OK_200));
            assertThat(response.getContentAsString(), is("OK"));
        }
    }
}
