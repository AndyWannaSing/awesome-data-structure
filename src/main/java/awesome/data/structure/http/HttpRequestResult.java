package awesome.data.structure.http;

/**
 * @author: Andy
 * @time: 2019/5/17 18:13
 * @since
 */
public class HttpRequestResult {
    private int status;
    private String content;

    public HttpRequestResult(int status, String content) {
        this.status = status;
        this.content = content;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
