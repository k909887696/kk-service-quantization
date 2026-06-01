package com.kk.business.quantization.utils;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.style.column.LongestMatchColumnWidthStyleStrategy;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * Excel工具类 - 提供Excel写入和TXT文件读取功能
 * @author kk
 * @since 2024/9/29
 */
@Slf4j
public class ExcelUtil {

    // ==================== Excel写入相关方法 ====================

    /**
     * 将数据写入Excel文件（单Sheet）
     *
     * @param filePath 文件路径
     * @param sheetName Sheet名称
     * @param data 数据列表
     * @param head 表头类
     * @param <T> 数据类型
     */
    public static <T> void writeExcel(String filePath, String sheetName, List<T> data, Class<T> head) {
        if (StringUtils.isBlank(filePath)) {
            throw new IllegalArgumentException("文件路径不能为空");
        }
        if (data == null) {
            data = new ArrayList<>();
        }
        try {
            sheetName = StringUtils.isBlank(sheetName) ? "Sheet1" : sheetName;
            EasyExcel.write(filePath, head)
                    .sheet(sheetName)
                    .doWrite(data);
            log.info("Excel文件写入成功: {}", filePath);
        } catch (Exception e) {
            log.error("Excel文件写入失败: {}", filePath, e);
            throw new RuntimeException("Excel文件写入失败: " + e.getMessage(), e);
        }
    }

    /**
     * 将数据写入Excel文件（使用默认Sheet名称）
     *
     * @param filePath 文件路径
     * @param data 数据列表
     * @param head 表头类
     * @param <T> 数据类型
     */
    public static <T> void writeExcel(String filePath, List<T> data, Class<T> head) {
        writeExcel(filePath, "Sheet1", data, head);
    }

    /**
     * 将数据写入Excel文件（无表头，纯数据）
     *
     * @param filePath 文件路径
     * @param sheetName Sheet名称
     * @param data 数据列表（List<List<Object>>格式）
     */
    public static void writeExcelWithoutHead(String filePath, String sheetName, List<List<Object>> data) {
        if (StringUtils.isBlank(filePath)) {
            throw new IllegalArgumentException("文件路径不能为空");
        }
        if (data == null) {
            data = new ArrayList<>();
        }
        try {
            sheetName = StringUtils.isBlank(sheetName) ? "Sheet1" : sheetName;
            EasyExcel.write(filePath)
                    .sheet(sheetName)
                    .doWrite(data);
            log.info("Excel文件写入成功: {}", filePath);
        } catch (Exception e) {
            log.error("Excel文件写入失败: {}", filePath, e);
            throw new RuntimeException("Excel文件写入失败: " + e.getMessage(), e);
        }
    }

    /**
     * 将数据写入Excel文件（自动列宽）
     *
     * @param filePath 文件路径
     * @param sheetName Sheet名称
     * @param data 数据列表
     * @param head 表头类
     * @param <T> 数据类型
     */
    public static <T> void writeExcelWithAutoWidth(String filePath, String sheetName, List<T> data, Class<T> head) {
        if (StringUtils.isBlank(filePath)) {
            throw new IllegalArgumentException("文件路径不能为空");
        }
        if (data == null) {
            data = new ArrayList<>();
        }
        try {
            sheetName = StringUtils.isBlank(sheetName) ? "Sheet1" : sheetName;
            EasyExcel.write(filePath, head)
                    .registerWriteHandler(new LongestMatchColumnWidthStyleStrategy())
                    .sheet(sheetName)
                    .doWrite(data);
            log.info("Excel文件写入成功: {}", filePath);
        } catch (Exception e) {
            log.error("Excel文件写入失败: {}", filePath, e);
            throw new RuntimeException("Excel文件写入失败: " + e.getMessage(), e);
        }
    }

    /**
     * 将数据写入Excel输出流
     *
     * @param outputStream 输出流
     * @param sheetName Sheet名称
     * @param data 数据列表
     * @param head 表头类
     * @param <T> 数据类型
     */
    public static <T> void writeExcelToStream(OutputStream outputStream, String sheetName, List<T> data, Class<T> head) {
        if (outputStream == null) {
            throw new IllegalArgumentException("输出流不能为空");
        }
        if (data == null) {
            data = new ArrayList<>();
        }
        try {
            sheetName = StringUtils.isBlank(sheetName) ? "Sheet1" : sheetName;
            EasyExcel.write(outputStream, head)
                    .sheet(sheetName)
                    .doWrite(data);
            log.info("Excel数据写入流成功");
        } catch (Exception e) {
            log.error("Excel数据写入流失败", e);
            throw new RuntimeException("Excel数据写入流失败: " + e.getMessage(), e);
        }
    }

    /**
     * 多Sheet写入Excel
     *
     * @param filePath 文件路径
     * @param sheetDataList Sheet数据列表
     */
    public static void writeMultiSheetExcel(String filePath, List<SheetData<?>> sheetDataList) {
        if (StringUtils.isBlank(filePath)) {
            throw new IllegalArgumentException("文件路径不能为空");
        }
        if (sheetDataList == null || sheetDataList.isEmpty()) {
            throw new IllegalArgumentException("Sheet数据不能为空");
        }
        try (ExcelWriter excelWriter = EasyExcel.write(filePath).build()) {
            for (int i = 0; i < sheetDataList.size(); i++) {
                SheetData<?> sheetData = sheetDataList.get(i);
                String sheetName = StringUtils.isBlank(sheetData.getSheetName()) 
                        ? "Sheet" + (i + 1) : sheetData.getSheetName();
                WriteSheet writeSheet = EasyExcel.writerSheet(i, sheetName)
                        .head(sheetData.getHeadClass())
                        .build();
                excelWriter.write(sheetData.getData(), writeSheet);
            }
            log.info("多Sheet Excel文件写入成功: {}", filePath);
        } catch (Exception e) {
            log.error("多Sheet Excel文件写入失败: {}", filePath, e);
            throw new RuntimeException("多Sheet Excel文件写入失败: " + e.getMessage(), e);
        }
    }

    // ==================== TXT文件读取相关方法 ====================

    /**
     * 读取TXT文件内容（按行读取）
     *
     * @param filePath 文件路径
     * @return 每行内容的列表
     */
    public static List<String> readTxtFile(String filePath) {
        if (StringUtils.isBlank(filePath)) {
            throw new IllegalArgumentException("文件路径不能为空");
        }
        List<String> lines = new ArrayList<>();
        File file = new File(filePath);
        if (!file.exists()) {
            throw new RuntimeException("文件不存在: " + filePath);
        }
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
            log.info("TXT文件读取成功: {}, 共{}行", filePath, lines.size());
        } catch (IOException e) {
            log.error("TXT文件读取失败: {}", filePath, e);
            throw new RuntimeException("TXT文件读取失败: " + e.getMessage(), e);
        }
        return lines;
    }

    /**
     * 读取TXT文件内容（指定编码）
     *
     * @param filePath 文件路径
     * @param charset 编码格式
     * @return 每行内容的列表
     */
    public static List<String> readTxtFile(String filePath, String charset) {
        if (StringUtils.isBlank(filePath)) {
            throw new IllegalArgumentException("文件路径不能为空");
        }
        if (StringUtils.isBlank(charset)) {
            charset = "UTF-8";
        }
        List<String> lines = new ArrayList<>();
        File file = new File(filePath);
        if (!file.exists()) {
            throw new RuntimeException("文件不存在: " + filePath);
        }
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(file), charset))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
            log.info("TXT文件读取成功: {}, 编码: {}, 共{}行", filePath, charset, lines.size());
        } catch (IOException e) {
            log.error("TXT文件读取失败: {}", filePath, e);
            throw new RuntimeException("TXT文件读取失败: " + e.getMessage(), e);
        }
        return lines;
    }

    /**
     * 读取TXT文件内容并返回单个字符串
     *
     * @param filePath 文件路径
     * @return 文件内容字符串
     */
    public static String readTxtFileAsString(String filePath) {
        List<String> lines = readTxtFile(filePath);
        return String.join(System.lineSeparator(), lines);
    }

    /**
     * 读取TXT文件内容并返回单个字符串（指定编码）
     *
     * @param filePath 文件路径
     * @param charset 编码格式
     * @return 文件内容字符串
     */
    public static String readTxtFileAsString(String filePath, String charset) {
        List<String> lines = readTxtFile(filePath, charset);
        return String.join(System.lineSeparator(), lines);
    }

    /**
     * 从输入流读取TXT内容（按行读取）
     *
     * @param inputStream 输入流
     * @return 每行内容的列表
     */
    public static List<String> readTxtFromStream(InputStream inputStream) {
        if (inputStream == null) {
            throw new IllegalArgumentException("输入流不能为空");
        }
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
            log.info("从流读取TXT成功, 共{}行", lines.size());
        } catch (IOException e) {
            log.error("从流读取TXT失败", e);
            throw new RuntimeException("从流读取TXT失败: " + e.getMessage(), e);
        }
        return lines;
    }

    /**
     * 读取TXT文件并解析为指定分隔符的数据
     *
     * @param filePath 文件路径
     * @param delimiter 分隔符
     * @return 解析后的数据列表
     */
    public static List<String[]> readTxtFileWithDelimiter(String filePath, String delimiter) {
        List<String> lines = readTxtFile(filePath);
        List<String[]> result = new ArrayList<>();
        for (String line : lines) {
            if (StringUtils.isNotBlank(line)) {
                result.add(line.split(delimiter));
            }
        }
        return result;
    }

    /**
     * 读取TXT文件并解析为CSV格式（逗号分隔）
     *
     * @param filePath 文件路径
     * @return 解析后的数据列表
     */
    public static List<String[]> readTxtFileAsCsv(String filePath) {
        return readTxtFileWithDelimiter(filePath, ",");
    }

    // ==================== 内部类 ====================

    /**
     * Sheet数据封装类（用于多Sheet写入）
     */
    public static class SheetData<T> {
        private String sheetName;
        private List<T> data;
        private Class<T> headClass;

        public SheetData(String sheetName, List<T> data, Class<T> headClass) {
            this.sheetName = sheetName;
            this.data = data;
            this.headClass = headClass;
        }

        public String getSheetName() {
            return sheetName;
        }

        public void setSheetName(String sheetName) {
            this.sheetName = sheetName;
        }

        public List<T> getData() {
            return data;
        }

        public void setData(List<T> data) {
            this.data = data;
        }

        public Class<T> getHeadClass() {
            return headClass;
        }

        public void setHeadClass(Class<T> headClass) {
            this.headClass = headClass;
        }
    }
}
