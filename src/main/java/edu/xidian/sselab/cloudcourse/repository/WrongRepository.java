package edu.xidian.sselab.cloudcourse.repository;

import edu.xidian.sselab.cloudcourse.domain.Record;
import edu.xidian.sselab.cloudcourse.hbase.HbaseClient;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.client.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class WrongRepository {

    private static final String TABLE_NAME = "WrongInfo";

    private static final String FAMILY_NAME = "info";

    private final HbaseClient hbaseClient;

    @Autowired
    public WrongRepository(HbaseClient hbaseClient) {
        this.hbaseClient = hbaseClient;
    }

    public List<Record> findAllByRecord(){
        List<Record> resultRecords = new ArrayList<>();
        hbaseClient.getConnection();
        Table table = hbaseClient.getTableByName(TABLE_NAME);
        /**
         * MUST_PASS_ALL (AND)
         * MUST_PASS_ONE(OR)
         * MUST_PASS_ALL  懒惰地评估：只要有一个过滤器不包含cell,评估就会停止，
         * MUST_PASS_ONE 非懒惰地评估：所有过滤器总是被评估
         * 默认是Filster.Operator.MUST_PASS_ALL
         */

        Scan scan = new Scan();
        ResultScanner rs;
        try {
            if (table != null) {
                rs = table.getScanner(scan);
                for (Result result : rs) {
                    Record tempRecord = new Record().mapFrom(result);
                    resultRecords.add(tempRecord);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("查询出错，返回一个空的列表!");
        }
        return resultRecords;
    }
    
}
