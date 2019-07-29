package awesome.data.structure.date;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author: Andy
 * @time: 2019/7/11 11:35
 * @since
 */
public class DateUtils {

    public static String getDate(String format, Date date) {
        SimpleDateFormat df = new SimpleDateFormat(format);
        return df.format(date);
    }

    /**
     * 时间格式 ： 20190710
     * @param searchEndTime
     * @return
     */
    private static String getRequiredFormatDate(Date searchEndTime){
        return getDate("yyyyMMdd", searchEndTime);
    }

    /**
     * 时间格式：20190710_171010
     * @param searchEndTime
     * @return
     */
    private static String getRequiredFormatDateTime(Date searchEndTime){
        return getDate("yyyyMMdd_HHmmss", searchEndTime);
    }

    public static void main(String[] args) {
        System.out.println(getRequiredFormatDate(new Date()));
        System.out.println(getRequiredFormatDateTime(new Date()));
    }
}
