package com.kk.business.quantization.utils;

import org.apache.commons.collections.map.LinkedMap;
import org.apache.commons.lang3.StringUtils;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author kk
 * @since 2023/8/23
 */
public class htmlUtil {

    public static String inner_style = "#innerTableClass{1px solid lightblue;}#innerTableClass tr{1px solid lightblue;}#innerTableClass td{1px solid lightblue};#innerTableClass th{1px solid lightblue;}";
    public static String table_style = "border:1px solid lightblue;";

    public static String th_style = "border:1px solid lightblue;";

    public static String td_style = "border:1px solid lightblue;";

    public static StringBuilder genHtmlTable(LinkedHashMap<String,String> headMap, List<LinkedHashMap<String, Object>> data,String style)
    {
        StringBuilder table = new StringBuilder();
        StringBuilder thead = new StringBuilder();
        StringBuilder th= new StringBuilder();
        StringBuilder tbody = new StringBuilder();
        StringBuilder trList = new StringBuilder();
        for(String h : headMap.keySet())
        {
            th.append(String.format("<th style=\"%s\">%s</th>",th_style,headMap.get(h)));
        }
        for (Map m : data)
        {
            StringBuilder td = new StringBuilder();
            m.forEach((k,v)->{
                td.append(String.format("<td style=\"%s\">%s</td>",td_style,v==null ?"":v.toString()));
            });
            trList.append(String.format("<tr>%s</tr>",td.toString()));
        }
        thead.append(String.format("<thead >%s</thead>",th));
        tbody.append(String.format("<tbody >%s</tbody>",trList.toString()));
        //table.append(String.format("<style>%s</style>",inner_style));
        table.append(String.format("<table id='innerTableClass' style=\"%s\">%s%s</table>", StringUtils.isBlank(style)?table_style:style,thead.toString(),tbody.toString()));
        return table;
    }
}
