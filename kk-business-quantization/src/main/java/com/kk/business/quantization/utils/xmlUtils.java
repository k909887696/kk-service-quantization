package com.kk.business.quantization.utils;


import api.VoucherFileInfo;
import api.VoucherFileUtil;
import com.kk.common.utils.DateUtil;
import com.kk.common.utils.JsonUtil;
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
public class xmlUtils {

   public static InputStream ofdToXml(String ofdUrl) throws Exception {
       String outFilePath = "/temp/temp"+ DateUtil.getCurrentTime(DateUtil.PATTERN_STANDARD17W)+".xml";
       VoucherFileInfo res = VoucherFileUtil.extractXBRLFromOFD(ofdUrl,outFilePath);
       System.out.println(JsonUtil.getJSONString( res));
       File tempFile = new File(res.getXbrlFilePath());
       FileInputStream fileInputStream = new FileInputStream(tempFile);

       tempFile.deleteOnExit();

       return fileInputStream;
   }

}
