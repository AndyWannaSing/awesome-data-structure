package awesome.data.structure.http;

import org.junit.Test;

import java.io.IOException;

/**
 * @author: Andy
 * @time: 2019/7/10 15:48
 * @since
 */
public class HttpUtilsTest {

    @Test
    public void postTextTest() throws IOException {
        String url = "http://localhost:8080/postText";
        String path = "D:\\workspace\\example.txt";
        HttpResponse response = HttpUtils.postText(url, path);
        System.out.println(response.toString());
    }
}
