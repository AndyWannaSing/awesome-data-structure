package awesome.data.structure.http;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * HttpClient的包装类，支持http/https的普通访问和basic Auth认证访问
 *
 * @author needle
 */
public class HttpHelper {

    private static final int DEFAULT_TIMEOUT = 6000;

    /**
     * 设置超时时间
     *
     * @param httpRequestBase
     * @author: Andy
     * @time: 2019/5/17 17:49
     */
    private static void setTimeout(HttpRequestBase httpRequestBase) {
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(DEFAULT_TIMEOUT).setConnectionRequestTimeout(DEFAULT_TIMEOUT)
                .setSocketTimeout(DEFAULT_TIMEOUT).build();

        httpRequestBase.setConfig(requestConfig);
    }

    /**
     * 获取客户端
     *
     * @param httpClientBuilder
     * @return
     */
    private static CloseableHttpClient getHttpClient(HttpClientBuilder httpClientBuilder) {
        RegistryBuilder<ConnectionSocketFactory> registryBuilder = RegistryBuilder.create();
        ConnectionSocketFactory socketFactory = new PlainConnectionSocketFactory();
        registryBuilder.register("http", socketFactory);
        //指定信任密钥存储对象和连接套接字工厂
        try {
            KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
            //信任任何链接
            TrustStrategy anyTrustStrategy = new TrustStrategy() {
                @Override
                public boolean isTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
                    return true;
                }
            };
            SSLContext sslContext = SSLContexts.custom().useTLS().loadTrustMaterial(trustStore, anyTrustStrategy).build();
            LayeredConnectionSocketFactory sslSF = new SSLConnectionSocketFactory(sslContext, SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
            registryBuilder.register("https", sslSF);
        } catch (KeyStoreException e) {
            throw new RuntimeException(e);
        } catch (KeyManagementException e) {
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        Registry<ConnectionSocketFactory> registry = registryBuilder.build();
        //设置连接管理器
        PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager(registry);

        //构建客户端
        return httpClientBuilder.setConnectionManager(connManager).build();
    }

    /**
     * 获取客户端
     */
    private static CloseableHttpClient getHttpClient() {
        return getHttpClient(HttpClientBuilder.create());
    }

    /**
     * 获取支持basic Auth认证的HttpClient
     *
     * @param username
     * @param password
     * @return
     */
    private static CloseableHttpClient getHttpClientWithBasicAuth(String username, String password) {
        return getHttpClient(credential(username, password));
    }

    /**
     * 配置basic Auth 认证
     *
     * @param username
     * @param password
     * @return
     */
    private static HttpClientBuilder credential(String username, String password) {
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        CredentialsProvider provider = new BasicCredentialsProvider();
        AuthScope scope = new AuthScope(AuthScope.ANY_HOST, AuthScope.ANY_PORT, AuthScope.ANY_REALM);
        UsernamePasswordCredentials credentials = new UsernamePasswordCredentials(username, password);
        provider.setCredentials(scope, credentials);
        httpClientBuilder.setDefaultCredentialsProvider(provider);
        return httpClientBuilder;
    }

    /**
     * 设置头信息
     *
     * @param req
     * @param headers
     */
    private static void setHeaders(HttpRequestBase req, Map<String, String> headers) {
        if (headers == null) return;

        for (Entry<String, String> header : headers.entrySet()) {
            req.setHeader(header.getKey(), header.getValue());
        }
    }

    /**
     * get基础类，支持普通访问和Basic Auth认证
     *
     * @param uri
     * @param headers
     * @param client    不同的方式不同的HttpClient
     * @param isTimeout 是否超时
     * @return
     */
    private static HttpRequestResult get(String uri, Map<String, String> headers, CloseableHttpClient client, boolean isTimeout) {
        try (CloseableHttpClient httpClient = client) {

            HttpGet get = new HttpGet(uri);
            setHeaders(get, headers);

            if (isTimeout) {
                setTimeout(get);
            }

            return getRequestResult(httpClient.execute(get));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * HTTP GET 请求
     *
     * @param uri
     * @param headers
     * @param isTimeout
     * @return
     */
    public static HttpRequestResult get(String uri, Map<String, String> headers, boolean isTimeout) {
        return get(uri, headers, getHttpClient(), isTimeout);
    }

    /**
     * 带有用户名密码认证的 HTTP GET 请求
     *
     * @param uri
     * @param headers
     * @param isTimeout
     * @return
     */
    public static HttpRequestResult getWithBaiscAuth(String uri, Map<String, String> headers, String username, String password, boolean isTimeout) {
        return get(uri, headers, getHttpClientWithBasicAuth(username, password), isTimeout);
    }

    /**
     * 获取 UrlEncodedFormEntity
     *
     * @param params
     * @return
     */
    public static HttpEntity createUrlEncodedFormEntity(Map<String, String> params) {
        List<NameValuePair> data = new ArrayList<>();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            data.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }
        try {
            return new UrlEncodedFormEntity(data, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取 StringEntity
     *
     * @param body
     * @return
     */
    public static HttpEntity createStringEntity(String body) {
        try {
            return new StringEntity(body);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * HTTP POST 请求
     *
     * @param client
     * @param uri
     * @param headers
     * @param entity
     * @param isTimeout
     * @return
     */
    private static HttpRequestResult post(CloseableHttpClient client, String uri, Map<String, String> headers, HttpEntity entity, boolean isTimeout) {
        try (CloseableHttpClient httpClient = client) {
            HttpPost post = new HttpPost(uri);
            setHeaders(post, headers);
            if (isTimeout) {
                setTimeout(post);
            }
            post.setEntity(entity);
            return getRequestResult(httpClient.execute(post));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取请求结果
     *
     * @param response
     * @return
     * @throws IOException
     */
    private static HttpRequestResult getRequestResult(CloseableHttpResponse response) throws IOException {
        try (CloseableHttpResponse httpResponse = response) {
            int status = httpResponse.getStatusLine().getStatusCode();

            if (status != HttpStatus.SC_NOT_FOUND) {
                return new HttpRequestResult(status, IOUtils.toString(httpResponse.getEntity().getContent(), "utf-8"));
            } else {
                return new HttpRequestResult(status, "404");
            }
        }
    }

    /**
     * HTTP POST 请求
     *
     * @param uri
     * @param headers
     * @param entity
     * @param isTimeout
     * @return
     */
    public static HttpRequestResult post(String uri, Map<String, String> headers, HttpEntity entity, boolean isTimeout) {
        return post(getHttpClient(), uri, headers, entity, isTimeout);
    }

    /**
     * 带有用户名密码认证的 HTTP POST 请求
     *
     * @param uri
     * @param headers
     * @param username
     * @param password
     * @param entity
     * @param isTimeout
     * @return
     */
    public static HttpRequestResult postWithBasicAuth(String uri, Map<String, String> headers, String username, String password, HttpEntity entity, boolean isTimeout) {
        return post(getHttpClientWithBasicAuth(username, password), uri, headers, entity, isTimeout);
    }


    public static void main(String[] args) throws UnsupportedEncodingException {
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("isClient", "true");
        headers.put("content-type", "application/xml");
        headers.put("content-encoding", "UTF-8");

        String body = "<action><status></status><fault><reason></reason><detail></detail></fault></action>";

        //测试操作Ovirt 虚拟机
        HttpRequestResult result = postWithBasicAuth("https://192.168.104.71/api/vms/41feaa71-4cb9-4c22-9380-ee530143eb0d/stop", headers, "sysadmin@internal", "admin==1", new StringEntity(body), false);

        System.out.println(result.getStatus());
        System.out.println(result.getContent());
    }
}