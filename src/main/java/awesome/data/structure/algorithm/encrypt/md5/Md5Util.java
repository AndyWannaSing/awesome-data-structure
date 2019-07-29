package awesome.data.structure.algorithm.encrypt.md5;


import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Md5 工具类
 *
 * @author: Andy
 * @time: 2019/5/10 17:27
 * @since
 */
public class Md5Util {
    private static final Logger LOGGER = LoggerFactory.getLogger(Md5Util.class);

    /**
     * 简单 MD5 加密
     *
     * @param str
     * @return: {@link String }
     * @author: Andy
     * @time: 2019/4/29 15:28
     * @since
     */
    public static String encrypt(String str) {
        try {
            return DigestUtils.md5Hex(str).toUpperCase();
        } catch (Exception e) {
            LOGGER.error("MD5加密失败！", e);
        }
        return null;
    }
}
