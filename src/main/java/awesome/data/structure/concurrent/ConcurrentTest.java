package awesome.data.structure.concurrent;


import awesome.data.structure.http.HttpHelper;
import awesome.data.structure.http.HttpRequestResult;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CyclicBarrier;

/**
 * 并发测试类
 *
 * @author: Andy
 * @time: 2019/5/17 17:29
 * @since
 */
public class ConcurrentTest {
    /**
     * 初始化屏障，以拦截 10 个线程
     */
    private CyclicBarrier cyclicBarrier = new CyclicBarrier(10);


    public static void main(String[] args) {
        List<Record> details = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            Record detail = new Record(1000000 + 1, i, "备注" + i);
            details.add(detail);
        }
        System.out.println(JSONObject.toJSONString(details));
        //
    }

    static class Record {
        private int id;
        private int status;
        private String remark;

        public Record() {

        }

        public Record(int id, int status, String remark) {
            this.id = id;
            this.status = status;
            this.remark = remark;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }
    }

}
