package edu.xidian.sselab.cloudcourse.repository;

import edu.xidian.sselab.cloudcourse.domain.Record;
import edu.xidian.sselab.cloudcourse.hbase.HbaseClient;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.filter.CompareFilter;
import org.apache.hadoop.hbase.filter.FilterList;
import org.apache.hadoop.hbase.filter.RegexStringComparator;
import org.apache.hadoop.hbase.filter.RowFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class TrackRepository {

    private static final String TABLENAME = "Record";
    private static final String FAMILYNAME = "info";
    @Autowired
    private final  HbaseClient hbaseClient;

    public TrackRepository(HbaseClient hbaseClient)
    {
        this.hbaseClient = hbaseClient;
    }
    public List<Record>  findAllRecordByEid(String eid){
        List<Record> recordList = new ArrayList<Record>();
        //连接客户端
        hbaseClient.getConnection();
        //获得表
        Table table = hbaseClient.getTableByName(TABLENAME);

        FilterList filterList = new FilterList(FilterList.Operator.MUST_PASS_ALL);
        RowFilter rowFilter = new RowFilter(
                CompareFilter.CompareOp.EQUAL,
                new RegexStringComparator("##" + eid + "$"));
        filterList.addFilter(rowFilter);

        Scan scan =new Scan();
        if(filterList.getFilters().size()>0){
            scan.setFilter(filterList);
        }

        ResultScanner rs;
        if(table!=null)
        {
            try {
                rs = table.getScanner(scan);
                for (Result result : rs) {
                    Record tempRecord = new Record().mapFrom(result);
                    recordList.add(tempRecord);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return recordList;
    }

}
