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

    public static String table_style = "border:1px solid lightblue;font-weight: 700;font-size: 14px;";
    public static String th_style = "border:1px solid lightblue;padding:5px;";
    public static String thead_style = "border:1px solid lightblue;padding:5px;background-color: lightblue;color: rebeccapurple;";
    public static String td_style = "border:1px solid lightblue;padding:5px;font-size: 11px;";
    public static String tbody_style = "color: red;font-weight: 700;";
    public static String title_style = "color: red;font-weight: 700;font-size: 15px;";
    public static StringBuilder genHtmlTable(String title, LinkedHashMap<String,String> headMap, List<LinkedHashMap<String, Object>> data)
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
                if(!headMap.keySet().contains(k)) {
                    return;
                }
                td.append(String.format("<td style=\"%s\">%s</td>",td_style,v==null ?"":v.toString()));
            });
            trList.append(String.format("<tr style=\"%s\">%s</tr>", m.get("style")!=null?m.get("style"):"",td.toString()));
        }
        thead.append(String.format("<thead style=\"%s\">%s</thead>",thead_style,th));
        tbody.append(String.format("<tbody style=\"%s\">%s</tbody>",tbody_style,trList.toString()));
        table.append(String.format("<span style=\"%s\">%s</span>",title_style,title));
        table.append(String.format("<table id='innerTableClass' style=\"%s\">%s%s</table>", table_style,thead.toString(),tbody.toString()));
        return table;
    }
}
