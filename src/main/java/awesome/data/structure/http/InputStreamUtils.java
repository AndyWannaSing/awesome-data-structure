package awesome.data.structure.http;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author: Andy
 * @time: 2019/7/10 16:12
 * @since
 */
public class InputStreamUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(InputStreamUtils.class);

    public static String read(InputStream in){
        StringBuilder resultBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(in))){
            String line;
            while ((line = reader.readLine()) != null){
                resultBuilder.append(line);
            }
        }catch (Exception e){
            LOGGER.error("InputStreamUtils.read 读取异常！", e);
            return null;
        }
        return resultBuilder.toString();
    }
}
