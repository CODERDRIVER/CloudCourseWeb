package edu.xidian.sselab.cloudcourse.domain;

import lombok.Data;
import lombok.ToString;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;

import java.util.ArrayList;
import java.util.List;

/**
 * 用来封装spark处理后，查询它相遇的所有车辆编号和相遇次数
 */
@Data
@ToString
public class SparkRecord {
    private String eid;    //车辆id
    private int meetCount;  //相遇次数

    public List<SparkRecord> mapForm(Result result)
    {
        List<SparkRecord> sparkRecords = new ArrayList<SparkRecord>();

        List<Cell> listCell = result.listCells();
        for(Cell cell:listCell)
        {
            SparkRecord sparkRecord = new SparkRecord();
            String qualifier = Bytes.toString(CellUtil.cloneQualifier(cell));
            int value = Bytes.toInt(CellUtil.cloneValue(cell));
            sparkRecord.setEid(qualifier);
            sparkRecord.setMeetCount(value);
            sparkRecords.add(sparkRecord);
        }
        return sparkRecords;
    }
}
