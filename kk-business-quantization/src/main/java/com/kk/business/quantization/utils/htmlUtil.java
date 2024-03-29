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

    public static String table_style = "border";

    public static StringBuilder genHtmlTable(LinkedHashMap<String,String> headMap, List<LinkedHashMap<String, Object>> data,String style)
    {
        StringBuilder table = new StringBuilder();
        StringBuilder thead = new StringBuilder();
        StringBuilder th= new StringBuilder();
        StringBuilder tbody = new StringBuilder();
        StringBuilder trList = new StringBuilder();
        for(String h : headMap.keySet())
        {
            th.append(String.format("<th>%s</th>",headMap.get(h)));
        }
        for (Map m : data)
        {
            StringBuilder td = new StringBuilder();
            m.forEach((k,v)->{
                td.append(String.format("<td>%s</td>",v.toString()));
            });
            trList.append(String.format("<tr>%s</tr>",td.toString()));
        }
        thead.append(String.format("<thead>%s</thead>",th));
        tbody.append(String.format("<tbody>%s</tbody>",trList.toString()));
        table.append(String.format("<table style=\"%s\">%s%s</table>", StringUtils.isBlank(style)?table_style:style,thead.toString(),tbody.toString()));
        return table;
    }
}
