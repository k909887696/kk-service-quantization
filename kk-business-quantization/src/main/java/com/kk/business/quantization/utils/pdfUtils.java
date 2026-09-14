package com.kk.business.quantization.utils;


import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HTTP;
import org.apache.pdfbox.pdmodel.PDDocument;



import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;


/**
 * @author kk
 * @since 2024/7/4
 */
public class pdfUtils {

    public static void ofdToPdf(InputStream ofdInputStream) throws IOException {
        PDDocument document = PDDocument.load(ofdInputStream);


    }

    public final static String FILE_NAME = "fileName";
    public final static String FILEMD5CODE = "fileMd5Code";
    public final static String SHARDINDEX = "shardIndex";
    public final static String SHARDTOTAL = "shardTotal";

    public static InputStream getInputStreamFromRemoteUrl(String path) throws Exception {
        //http文件服务器文件处理
        URL url = new URL(path);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        // 创建输入流，读取Excel
        return conn.getInputStream();

    }

    public static String inputStreamUpload(String url, String fileName, InputStream inputStream) {
        //创建HttpClient对象
        CloseableHttpClient client = HttpClients.createDefault();
        //构建POST请求   请求地址请更换为自己的。
        HttpPost post = new HttpPost(url);
        try {
            ContentType contentType = ContentType.create(HTTP.PLAIN_TEXT_TYPE, HTTP.UTF_8);
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.setCharset(Charset.forName(HTTP.UTF_8));//设置请求的编码格式
            builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);   //设置浏览器兼容模式
            //第一个参数为 相当于 Form表单提交的file框的name值
            //第二个参数就是我们要发送的InputStream对象了
            //第三个参数是文件名
            builder.addBinaryBody("uploadFile", inputStream, ContentType.create("multipart/form-data"), fileName);
            //4)构建请求参数 普通表单项
            StringBody stringBody = new StringBody(fileName, contentType);
            builder.addPart(FILE_NAME, stringBody);
            HttpEntity entity = builder.build();
            post.setEntity(entity);
            //发送请求
            HttpResponse response = client.execute(post);
            entity = response.getEntity();
            if (entity != null) {
                inputStream = entity.getContent();
                //转换为字节输入流
                BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, Consts.UTF_8));
                StringBuilder stringBuilder = new StringBuilder();
                String body = null;
                while ((body = br.readLine()) != null) {
                    stringBuilder.append(body);
                }
                br.close();
                return stringBuilder.toString();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            if(inputStream != null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }

    public static String inputStreamUpload4Slice(String url, String fileName,String fileMd5Code,int shardIndex,int shardTotal, InputStream inputStream) {
        //创建HttpClient对象
        CloseableHttpClient client = HttpClients.createDefault();
        //构建POST请求   请求地址请更换为自己的。
        HttpPost post = new HttpPost(url);
        try {
            ContentType contentType = ContentType.create(HTTP.PLAIN_TEXT_TYPE, HTTP.UTF_8);
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.setCharset(Charset.forName(HTTP.UTF_8));//设置请求的编码格式
            builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);   //设置浏览器兼容模式
            //第一个参数为 相当于 Form表单提交的file框的name值
            //第二个参数就是我们要发送的InputStream对象了
            //第三个参数是文件名
            builder.addBinaryBody("uploadFile", inputStream, ContentType.create("multipart/form-data"), fileName);
            //4)构建请求参数 普通表单项
            StringBody stringBody = new StringBody(fileName, contentType);
            builder.addPart(FILE_NAME, stringBody);
            builder.addPart(FILEMD5CODE, new StringBody(fileMd5Code, contentType));
            builder.addPart(SHARDINDEX, new StringBody(shardIndex+"", contentType));
            builder.addPart(SHARDTOTAL, new StringBody(shardTotal+"", contentType));
            HttpEntity entity = builder.build();
            post.setEntity(entity);
            //发送请求
            HttpResponse response = client.execute(post);
            entity = response.getEntity();
            if (entity != null) {
                inputStream = entity.getContent();
                //转换为字节输入流
                BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, Consts.UTF_8));
                StringBuilder stringBuilder = new StringBuilder();
                String body = null;
                while ((body = br.readLine()) != null) {
                    stringBuilder.append(body);
                }
                br.close();
                return stringBuilder.toString();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            if(inputStream != null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }

    public static void generatePdfFromHtml(String htmlContent, String outputPath) throws Exception {
        try (OutputStream os = new FileOutputStream(outputPath)) {
            PdfRendererBuilder builder = new PdfRendererBuilder();

            // 设置 HTML 内容，第二个参数是 base URI（用于解析相对路径的资源），没有可传 null
            builder.withHtmlContent(htmlContent, null);

            // 注册中文字体（防止中文变方框）
            // Windows 系统字体路径示例：
            // 从 classpath 读取字体，复制到临时文件
            InputStream fontStream = pdfUtils.class.getClassLoader()
                    .getResourceAsStream("fonts/AlibabaPuHuiTi-3-55-Regular.ttf");
            if (fontStream != null) {
                // 创建临时文件
                File tempFontFile = File.createTempFile("AlibabaPuHuiTi", ".ttf");
                tempFontFile.deleteOnExit(); // JVM 退出时自动删除

                // 将 classpath 中的字体写入临时文件
                try (FileOutputStream fos = new FileOutputStream(tempFontFile)) {
                    fontStream.transferTo(fos);
                }

                // 用 File 模式注册字体
                builder.useFont(tempFontFile, "Alibaba PuHuiTi 3");
            }

            // 输出到流
            builder.toStream(os);

            // 执行生成
            builder.run();
        }
    }}
