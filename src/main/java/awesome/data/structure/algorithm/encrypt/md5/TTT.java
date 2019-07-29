package awesome.data.structure.algorithm.encrypt.md5;

import java.util.*;

/**
 * @author: Andy
 * @time: 2019/5/13 17:23
 * @since
 */

public class TTT {
    public static void main(String[] args) {

        List<String> str = new ArrayList<>();
        int i= 0;
        while (true){
            str.add("SSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS"+i);
        }

    }

    /**
     * 获取 sql 中的 in 的查询字符串内容
     *
     *
     * @param values
     * @return: {@link ? }
     * @author: Andy
     * @time: 2019/5/16 11:20
     * @since
     */
    public static String toStringForSql(List<?> values){

        StringBuilder inConditionSql = new StringBuilder("(");
        boolean first = true;
        for(Object value : values){
            if(first){
                first = false;
            }else {
                inConditionSql.append(",");
            }

            if(value instanceof Byte
                    || value instanceof Short
                    || value instanceof Integer
                    || value instanceof Long
                    || value instanceof Float
                    || value instanceof Double){
                inConditionSql.append(value.toString());
                continue;
            }

            inConditionSql.append("'").append(value).append("'");
        }
        inConditionSql.append(")");

        return inConditionSql.toString();
    }



}
