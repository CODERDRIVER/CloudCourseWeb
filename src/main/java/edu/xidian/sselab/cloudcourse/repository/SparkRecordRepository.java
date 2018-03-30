package edu.xidian.sselab.cloudcourse.repository;


import edu.xidian.sselab.cloudcourse.domain.SparkRecord;
import edu.xidian.sselab.cloudcourse.hbase.HbaseClient;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.filter.FilterList;
import org.apache.hadoop.hbase.filter.RegexStringComparator;
import org.apache.hadoop.hbase.filter.RowFilter;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class SparkRecordRepository {
    private static final String TABLE_NAME = "MeetCount";  //表名
    private static final String FAMILY_NAME = "info";   //列族+

    private final  HbaseClient hbaseClient;     //hbase客户端
    public SparkRecordRepository(HbaseClient hbaseClient) {
        this.hbaseClient = hbaseClient;
    }
    public List findAllRecordById(long eid)
    {
        List<SparkRecord> lists = new ArrayList<SparkRecord>();
        //初始化
        List sparkRecords = new ArrayList();
        //获得连接
        hbaseClient.getConnection();
        //获得表
        Table table = hbaseClient.getTableByName(TABLE_NAME);
        FilterList filterList = new FilterList(FilterList.Operator.MUST_PASS_ALL);

        RowFilter rowFilter =new RowFilter(RowFilter.CompareOp.EQUAL,
                new RegexStringComparator(eid+""));
        filterList.addFilter(rowFilter);
        Scan scan = new Scan();
        scan.setFilter(filterList);
        scan.addFamily(Bytes.toBytes(FAMILY_NAME));
        ResultScanner rs;   //结果扫描
        if(table!=null){
            try {
                rs = table.getScanner(scan);
                for(Result result:rs)
                {
                    lists =new SparkRecord().mapForm(result);
                    sparkRecords.add(lists);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return lists;
    }
    public List<SparkRecord> getAll(Long eid)
    {
        List<SparkRecord> sparkRecords = new ArrayList<SparkRecord>();
        hbaseClient.getConnection();
        //获得表
        Table table = hbaseClient.getTableByName(TABLE_NAME);
        Get get = new Get(Bytes.toBytes(eid));
        try {
            table.get(get);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
