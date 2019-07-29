package awesome.data.structure;

import awesome.data.structure.http.InputStreamUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.PrintWriter;

@SpringBootApplication
@RestController
public class AwesomeDataStructureApplication {
    private static final Logger LOGGER = LoggerFactory.getLogger(AwesomeDataStructureApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(AwesomeDataStructureApplication.class, args);
    }

    @PostMapping("/postText")
    public void receivePostText_thenOK(HttpServletRequest request, HttpServletResponse response) {
        boolean success = true;
        try (InputStream in = request.getInputStream()) {
            String requestData = InputStreamUtils.read(in);
            LOGGER.info("requestData:" + requestData);
        } catch (Exception e) {
            success = false;
            LOGGER.info("处理 postText 请求异常", e);
        }

        try (PrintWriter writer = response.getWriter()) {
            if (success) {
                writer.write("OK");
            } else {
                writer.write("Not OK");
            }
        } catch (Exception e) {
            LOGGER.info("处理 postText 响应异常", e);
        }
    }
}
