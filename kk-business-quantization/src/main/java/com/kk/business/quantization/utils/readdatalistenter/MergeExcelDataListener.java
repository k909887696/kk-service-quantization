package com.kk.business.quantization.utils.readdatalistenter;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.util.ListUtils;
import com.alibaba.excel.write.metadata.WriteSheet;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author kk
 * @since 2024/9/30
 */
@Data
public class MergeExcelDataListener extends AnalysisEventListener<Map<Integer, Object>> {
    /**
     * 合并后文件临时地址（追加到最后一行）
     */
    private String mergeFilePath;
    /**
     * 合并是需要忽略的几行数据
     */
    private List<Integer> ignoreRowNums;
    /**
     * 写入哪个sheet名称
     */
    private String sheetName;

    private ExcelWriter excelWriter;
    public MergeExcelDataListener(String mergeFilePath,String sheetName,List<Integer> ignoreRowNums,ExcelWriter excelWriter)
    {
        this.mergeFilePath = mergeFilePath;
        this.sheetName = sheetName;
        this.ignoreRowNums = ignoreRowNums;
        this.excelWriter =excelWriter;
    }
    @Override
    public void invoke(Map<Integer, Object> integerStringMap, AnalysisContext analysisContext) {
        System.out.println("行数:"+analysisContext.readRowHolder().getRowIndex());
        if(ignoreRowNums!=null && ignoreRowNums.size()>0)
        {
            if(ignoreRowNums.contains(analysisContext.readRowHolder().getRowIndex()))
            {
                System.out.println("忽略行数:"+analysisContext.readRowHolder().getRowIndex());
            }
        }
        List<List<Object>> list = ListUtils.newArrayList();
        List<Object> row = new ArrayList<>();
        for (Integer key : integerStringMap.keySet())
        {
            row.add(integerStringMap.get(key));
        }
       // analysisContext.readRowHolder().
        list.add(row);
        if(StringUtils.isEmpty(sheetName))
        {
            WriteSheet writeSheet = EasyExcel.writerSheet().needHead(Boolean.FALSE).build();

            this.excelWriter.write(list,writeSheet);
        }else {
            WriteSheet writeSheet = EasyExcel.writerSheet(sheetName).needHead(Boolean.FALSE).build();

            this.excelWriter.write(list,writeSheet);

        }

    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
