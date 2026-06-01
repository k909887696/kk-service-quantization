package com.kk.business.quantization.utils.readdatalistenter;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.metadata.CellExtra;
import com.alibaba.excel.metadata.data.CellData;
import com.alibaba.excel.metadata.data.ReadCellData;
import com.alibaba.excel.metadata.data.WriteCellData;
import com.alibaba.excel.util.ListUtils;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.kk.common.utils.JsonUtil;
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
public class MergeExcelDataWithStyleListener extends AnalysisEventListener<Object> {
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
    public MergeExcelDataWithStyleListener(String mergeFilePath, String sheetName, List<Integer> ignoreRowNums, ExcelWriter excelWriter)
    {
        this.mergeFilePath = mergeFilePath;
        this.sheetName = sheetName;
        this.ignoreRowNums = ignoreRowNums;
        this.excelWriter =excelWriter;
    }
    @Override
    public void invoke(Object integerStringMap, AnalysisContext analysisContext) {
        //System.out.println("analysisContext:"+ JsonUtil.getJSONString(analysisContext));

        //log.info("解析到一条数据:{}", JSON.toJSONString(data));
        System.out.println("行数():"+analysisContext.readRowHolder().getRowIndex()+"|"+ JsonUtil.getJSONString(integerStringMap));
       /* if(ignoreRowNums!=null && ignoreRowNums.size()>0)
        {
            if(ignoreRowNums.contains(analysisContext.readRowHolder().getRowIndex()))
            {
                System.out.println("忽略行数:"+analysisContext.readRowHolder().getRowIndex());
            }
        }
        List<List<WriteCellData<Object>>> list = ListUtils.newArrayList();
        List<WriteCellData<Object>> row = new ArrayList<>();

        for (Integer key : integerStringMap.keySet())
        {
           // analysisContext.readRowHolder().getCellMap().get(key).
            WriteCellData<Object> t = new WriteCellData<>();
            t.setData(integerStringMap.get(key).getData());
            t.setType(integerStringMap.get(key).getType());
            row.add(t);
        }
       // integerStringMap.get(0).getType()
        list.add(row);
        if(StringUtils.isEmpty(sheetName))
        {
            WriteSheet writeSheet = EasyExcel.writerSheet().needHead(Boolean.FALSE).build();

            this.excelWriter.write(list,writeSheet);
        }else {
            WriteSheet writeSheet = EasyExcel.writerSheet(sheetName).needHead(Boolean.FALSE).build();

            this.excelWriter.write(list,writeSheet);

        }*/

    }
    @Override
   public void extra(CellExtra extra, AnalysisContext context) {
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
