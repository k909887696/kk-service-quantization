package com.kk;

import static org.junit.Assert.assertTrue;


import api.VoucherFileInfo;
import api.VoucherFileUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.kk.business.quantization.constant.DfcfFieldsMap;
import com.kk.business.quantization.model.po.dfcf.DfcfBaseRes;
import com.kk.business.quantization.model.po.dfcf.DfcfHisBaseRes;
import com.kk.business.quantization.model.po.pdd.*;
import com.kk.business.quantization.service.executor.impl.StrongPoolTaskExecutor;
import com.kk.business.quantization.utils.*;
import com.kk.business.quantization.utils.meiya.SHAUtil;
import com.kk.business.quantization.utils.readdatalistenter.MergeExcelDataListener;
import com.kk.business.quantization.utils.readdatalistenter.MergeExcelDataWithStyleListener;
import com.kk.common.utils.*;
import com.kk.common.utils.DateUtil;
import com.kk.common.utils.ExcelUtils;
import jdk.internal.util.xml.impl.Input;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.pdfbox.io.MemoryUsageSetting;
import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.printing.PDFPageable;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.junit.Test;
import org.junit.jupiter.api.TestTemplate;
import org.ofdrw.converter.ConvertHelper;
import org.ofdrw.converter.ImageMaker;
import org.ofdrw.converter.ofdconverter.DocConverter;
import org.ofdrw.converter.ofdconverter.ImageConverter;
import org.ofdrw.converter.ofdconverter.PDFConverter;
import org.ofdrw.layout.OFDDoc;
import org.ofdrw.layout.element.Paragraph;
import org.ofdrw.reader.OFDReader;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.ofdrw.layout.element.Img;
import org.slf4j.Logger;
import org.springframework.http.HttpMethod;

/**
 * Unit test for simple App.
 */
public class AppTest {
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() {
        assertTrue(true);
    }

    public static String MD5(String s) {
        char hexDigits[]={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
        try {
            byte[] btInput = s.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * net 旅客权限系统密码加密算法
     * @param s
     * @return
     */
    public static String NetPasswordMD5(String s)
    {
        String res =MD5(s);
        res =MD5(res.substring(1,31));
        res = res.substring(0,31);
        return res;
    }
    @Test
    public void testNetMd5() throws Exception {
        MD5Common mdt = new MD5Common();

        System.out.println(NetPasswordMD5("random2024"));
        System.out.println(mdt.Md5Encrypt("random2024"));
    }
    @Test
    public void testUtils() {
        String res = httpUtil.doPost("http://push2his.eastmoney.com/api/qt/stock/kline/get?ut=fa5fd1943c7b386f172d6893dbfba10b&fields1=f1%2Cf2%2Cf3%2Cf4%2Cf5&fields2=f51%2Cf52%2Cf53%2Cf54%2Cf55%2Cf56%2Cf57%2Cf58&klt=101&fqt=1&smplmt=1524.6&_=1590670510425&cb=jQuery112402670742210902033_1584861859279&beg=20240717&end=20240718&lmt=5000&secid=90.BK1081", "",null);
        String result = res.replace("jQuery112402670742210902033_1584861859279","");
        result = result.replace("(","");
        result = result.replace(");","");
        DfcfHisBaseRes resObj= (DfcfHisBaseRes) JsonUtil.parseObject(result, DfcfHisBaseRes.class);
        List<Map<String,Object>> diff = ThridDataUtils.dfcfKlinesHandler(resObj.getData().getKlines(), DfcfFieldsMap.CODE_VALUE_MAP.get(DfcfFieldsMap.DFCF_CONCEPT_DAILY));
        System.out.println(result);
    }

    @Test
    public void testPDDGoods() throws Exception {
        String basesavePath = "E:\\淘宝\\picture\\商品资料\\" + DateUtil.date2String(new Date(), DateUtil.PATTERN_STANDARD08W) + "\\";
        String mainPath = "主图\\";
        String skuPath = "sku\\";
        String detailsPath = "详情\\";
        Map<String, Object> params = new HashMap<>();
        params.put("pre_sale_type", 4);
        params.put("page", 1);
        params.put("is_onsale", 1);
        params.put("sold_out", 0);
        params.put("size", 100);
        Map<String, Object> header = new HashMap<>();
        header.put("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/102.0.5005.63 Safari/537.36");
        header.put("anti-content", "0apWfxUeMwVEsaKm7whqxbAbuA9VoFH56z7CqGYr_CmA25WOzj5kSjYUar06IEHY2OR9wMtPnerglcuZI5OZvxB_d-2OezRCM3hOv32e-3tDpbTL_7S3R1S3lWMXcT8qMxJD22KbQ_TXYgxnHEaOU9yXUgYX09YXATjn0XjXTWtzfKmBZV6n0ZgMgMGi_Aze-EzX-EdRRe7eWmFflImLZFKDPT00uboGjma4jgann9YOsE9415ez4cdMf-KtUVzsl_FZIuMWzWzLfODMROeMq-KW6hgRQHKtlwFeIuIhtuMeRAHtUZSs9gA1BiKEtwSKBAIEB4Ie8DOgfqE--5d4A7zK2swbsIee32hFzCVm2sZgsBesxGoE-xVm2fWrsvOM2qe32KEg25zrf6mTmpt4TIb9qVSQYyYpJR2Z6lB_y1hHSAF5DAepMfzMslfcLhSErIcr_IMaB8XUurL6ka2zR_G_M4yzhNk");
        header.put("cookie", "api_uid=ChDdmmKYtI6lajVBtKEIAg==; _bee=XhVsnLxzpebiLQYKnKdVcluY93AeaWeZ; _f77=c30163ee-e259-46d1-8a60-af9519f4b460; _a42=2eed05ad-90a3-4285-ac47-e2bdfdb0f986; rckk=XhVsnLxzpebiLQYKnKdVcluY93AeaWeZ; ru1k=c30163ee-e259-46d1-8a60-af9519f4b460; ru2k=2eed05ad-90a3-4285-ac47-e2bdfdb0f986; _nano_fp=XpEyn0g8n0C8X0XynT_D3p34WsJs1cYbEQPQVcHm; jrpl=bM1xAxFzHbUdZPZVpr4gku8WliG0y0sS; njrpl=bM1xAxFzHbUdZPZVpr4gku8WliG0y0sS; dilx=A6h87keOOmjfRyzspj7bl; mms_b84d1838=3414,120,170,180,3397,3434,3254,3474,3475,3477,3479,3482,1202,1203,1204,1205,3417; x-visit-time=1654694561836; JSESSIONID=2FE358A707CA60220B3D3C5159223092; PASS_ID=1-dBeQJJHfcASf6KDbmxrzlimKfv60TBo0FCRLyHIBpF4l7r74UDt3m2YzRzVUCQCUwwwdSSha3aVL0RHg3Mwsow_881552394_50004361");
        String res = httpUtil.httpRestRequest(params, "https://mms.pinduoduo.com/vodka/v2/mms/query/display/mall/goodsList", header, String.class);
        //System.out.println(res);
        PDDResponseBase<GoodsList> responseBase = (PDDResponseBase<GoodsList>) JSON.parseObject(res, new TypeReference<PDDResponseBase<GoodsList>>() {
        });
        for (Goods g : responseBase.getResult().getGoods_list()) {
            System.out.println("商品编号：" + g.getId());
            Map<String, Object> detailparams = new HashMap<>();
            detailparams.put("goods_id", g.getId());
            String goodName = g.getGoods_name().replace("\\", "_").replace("/", "-").substring(0, 20) + "-" + g.getId();
            String detailres = httpUtil.httpRestRequest(detailparams, "https://mms.pinduoduo.com/glide/v2/mms/query/commit/on_shop/detail", header, String.class);
            PDDResponseBase<GoodsDetails> responseBaseDetail = (PDDResponseBase<GoodsDetails>) JSON.parseObject(detailres, new TypeReference<PDDResponseBase<GoodsDetails>>() {
            });
            String jsonTxtPath = basesavePath + goodName + "\\" + goodName + "[" + g.getId() + "].txt";
            StringBuilder jsonContent = new StringBuilder();
            jsonContent.append(JsonUtil.getJSONString(g) + "\n");
            jsonContent.append("======详情\n" + JsonUtil.getJSONString(responseBaseDetail) + "\n");
            jsonContent.append("======格式化详情\n");
            jsonContent.append("======商品名称：" + g.getGoods_name() + "\n");
            jsonContent.append("======分类：" + responseBaseDetail.getResult().getCats().toString() + "\n");
            jsonContent.append("======sku\n");


            int i = 1;
            //主图下载
            for (Gallery gallery : responseBaseDetail.getResult().getCarousel_gallery()) {
                String url = gallery.getUrl();
                String newFileName = mainPath + goodName + String.format("%02d", i);
                String newFileSuffix = url.substring(url.lastIndexOf(".") + 1);
                String tempPath = basesavePath + goodName + "\\" + newFileName + "." + newFileSuffix;
                FileUtil.createFile(new File(tempPath));
                httpUtil.download(url, tempPath);
                i++;
            }
            i = 1;
            //详情下载
            for (Gallery gallery : responseBaseDetail.getResult().getDetail_gallery()) {
                String url = gallery.getUrl();
                String newFileName = detailsPath + goodName + String.format("%02d", i);
                String newFileSuffix = url.substring(url.lastIndexOf(".") + 1);
                String tempPath = basesavePath + goodName + "\\" + newFileName + "." + newFileSuffix;
                FileUtil.createFile(new File(tempPath));
                httpUtil.download(url, tempPath);
                i++;
            }
            i = 1;
            //sku下载
            for (SKU sku : responseBaseDetail.getResult().getSkus()) {
                String url = sku.getThumb_url();
                String newFileName = sku.getSpec().get(0).getParent_name();
                for (SKUSpec skuSpec : sku.getSpec()) {
                    newFileName += "_" + skuSpec.getSpec_name();
                }
                jsonContent.append("======sku名称：" + newFileName + "；价格：" + (sku.getPrice() / 100) + "\n");
                String newFileSuffix = url.substring(url.lastIndexOf(".") + 1);
                String tempPath = basesavePath + goodName + "\\" + skuPath + newFileName + String.format("%02d", i) + "." + newFileSuffix;
                FileUtil.createFile(new File(tempPath));
                httpUtil.download(url, tempPath);
                i++;
            }
            i = 1;
            FileUtil.createAndWriteTxtFile(jsonTxtPath, jsonContent.toString());
            System.out.println("商品编号：" + g.getId() + ":finish");
            // System.out.println(JsonUtil.getJSONString( responseBaseDetail));
        }
        //System.out.println(responseBase.getResult().getGoods_list().get(0).getId());
    }

    @Test
    public void testDownLoadTaoBaoDetailImg() throws Exception {
        String htmlStr = "<p><img align=\"absmiddle\" src=\"https://img.alicdn.com/imgextra/i2/2206656335018/O1CN01sEibN21mwI9AuX3gw_!!2206656335018.jpg\" style=\"max-width: 750.0px;\" data-spm-anchor-id=\"2013.1.0.i0.58433d78RSd8Mx\"><img align=\"absmiddle\" src=\"https://img.alicdn.com/imgextra/i2/2206656335018/O1CN01Q9ULSw1mwI92DaeW8_!!2206656335018.jpg\" style=\"max-width: 750.0px;\"><img align=\"absmiddle\" src=\"https://img.alicdn.com/imgextra/i4/2206656335018/O1CN01MpaiP11mwI9CGZMuN_!!2206656335018.jpg\" style=\"max-width: 750.0px;\"><img align=\"absmiddle\" src=\"https://img.alicdn.com/imgextra/i3/2206656335018/O1CN01YT4XPa1mwI9F69coD_!!2206656335018.jpg\" style=\"max-width: 750.0px;\"><img align=\"absmiddle\" src=\"https://img.alicdn.com/imgextra/i4/2206656335018/O1CN01AMlSF31mwI9F69pHy_!!2206656335018.jpg\" class=\"\" style=\"max-width: 750.0px;\" width=\"750\" height=\"1115\"><img align=\"absmiddle\" src=\"https://img.alicdn.com/imgextra/i2/2206656335018/O1CN01GX5lmZ1mwI9BTSLkn_!!2206656335018.jpg\" class=\"\" style=\"max-width: 750.0px;\" width=\"750\" height=\"504\"><img align=\"absmiddle\" src=\"https://img.alicdn.com/imgextra/i4/2206656335018/O1CN014lhHLZ1mwI9AuXawa_!!2206656335018.jpg\" class=\"\" style=\"max-width: 750.0px;\" width=\"750\" height=\"867\"><img align=\"absmiddle\" src=\"https://img.alicdn.com/imgextra/i2/2206656335018/O1CN01T48BK51mwI96rwbho_!!2206656335018.jpg\" class=\"\" style=\"max-width: 750.0px;\" width=\"750\" height=\"950\"><img align=\"absmiddle\" src=\"https://img.alicdn.com/imgextra/i3/2206656335018/O1CN011zWVHQ1mwI9BTQbfD_!!2206656335018.jpg\" class=\"\" style=\"max-width: 750.0px;\" width=\"750\" height=\"816\"><img align=\"absmiddle\" src=\"https://img.alicdn.com/imgextra/i4/2206656335018/O1CN01lkUc001mwI9F699jK_!!2206656335018.jpg\" class=\"\" style=\"max-width: 750.0px;\" width=\"750\" height=\"639\" data-spm-anchor-id=\"2013.1.0.i2.58433d78RSd8Mx\"></p>";

        Matcher m = Pattern.compile("src=\"?(.*?)(\"|>|\\s+)").matcher(htmlStr);
        String url = "";
        int i = 1;
        while (m.find()) {
            url = m.group(1);
            String newFileName = String.format("%02d", i);
            String newFileSuffix = url.substring(url.lastIndexOf(".") + 1);
            String savePath = "E:\\淘宝\\picture\\商品资料\\20220609\\适配大众迈腾朗逸速腾途观明锐水箱散热冷却-166232899454\\详情\\"
                    + DateUtil.date2String(new Date(), DateUtil.PATTERN_STANDARD08W) + "A\\" + newFileName + "." + newFileSuffix;
            FileUtil.createFile(new File(savePath));
            httpUtil.download(url, savePath);
            i++;
            System.out.println("savePath：" + savePath.substring(0, 20));
        }
    }

    @Test
    public void testPattern() {
        String illegalCharReg = "[^～@#_*%/\\.+:=0-9a-zA-Z]+";
        String numberReg = "[0-9]+";
        String charReg = "[a-zA-Z]+";
        String legalSpecialCharReg = "[～@#_*%/\\.+:=]+";
        String repeatsCharReg = "(\\w)\\1{5}";
        String simpleCharReg = "(1234|abcd|qwer|4321)";

        String htmlStr = "qwer111111fffff1234sdfasdfsadfsadfdsaabcdsasdfaqwerqwer123443211234321fasdfe1234asdfadfqweradddabcd.>";

        Matcher m_illegalCharReg = Pattern.compile(illegalCharReg).matcher(htmlStr);
        System.out.println("m_illegalCharReg：" + m_illegalCharReg.find());
        System.out.println("m_illegalCharReg_match：" + m_illegalCharReg.group());
        boolean m_numberReg = Pattern.compile(numberReg).matcher(htmlStr).matches();
        System.out.println("m_numberReg：" + m_numberReg);
        boolean m_charReg = Pattern.compile(charReg).matcher(htmlStr).matches();
        System.out.println("m_charReg：" + m_charReg);
        boolean m_legalSpecialCharReg = Pattern.compile(legalSpecialCharReg).matcher(htmlStr).matches();
        System.out.println("m_legalSpecialCharReg：" + m_legalSpecialCharReg);
        boolean m_repeatsCharReg = Pattern.compile(repeatsCharReg).matcher(htmlStr).matches();
        System.out.println("m_repeatsCharReg：" + m_repeatsCharReg);
        Matcher m_simpleCharReg = Pattern.compile(simpleCharReg).matcher(htmlStr);

        System.out.println("m_simpleCharReg：" + m_simpleCharReg.find());
        System.out.println("m_simpleCharReg：" + m_simpleCharReg.group());
        System.out.println("m_simpleCharReg：" + m_simpleCharReg.matches());
    }

    @Test
    public void testMobileAndEmail() {
        String email = "418699890@qq.com";
        System.out.println("原邮箱： " + email);

        email = email.replaceAll("(^\\w)[^@]*(.@.*$)", "$1****$2");

        System.out.println("脱敏后： " + email);

        System.out.println("---------------------------");

        String phone = "13488889999";
        System.out.println("原电话： " + phone);

        phone = phone.replaceAll("([1][3-9]\\d{1})\\d{4}(\\d{4})", "$1****$2");

        System.out.println("脱敏后： " + phone);
    }

    @Test
    public void testKdjExcuetor() throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd\'T\'HH:mm:ss");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String nowTime = simpleDateFormat.format(new Date());
        Date payChannelShoppingDate = simpleDateFormat.parse("2023-08-31T12:00:36+08:00");
        System.out.print(nowTime);
        System.out.print(payChannelShoppingDate);
    }

    @Test
    public void testmap() {
        Map<String, String> BYTETOSTR = new HashMap<String, String>();


        Integer t = 1;
        BYTETOSTR.put("1", "周一");
        BYTETOSTR.put("2", "周二");
        BYTETOSTR.put("3", "周三");
        BYTETOSTR.put("4", "周四");
        BYTETOSTR.put("5", "周五");
        BYTETOSTR.put("6", "周六");
        BYTETOSTR.put("7", "周日");

        System.out.print(BYTETOSTR.get("1"));
        Calendar cal = Calendar.getInstance();
        cal.setTime(DateUtil.string2Date("2024-07-12 00:00:00",DateUtil.PATTERN_STANDARD19H));
        for (int i = 1; i < 1; i++) {
            cal.setFirstDayOfWeek(Calendar.MONDAY);
            int day = cal.get(Calendar.DAY_OF_WEEK);
            if(day==1){
                cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);
            }else{
                cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day+7);
            }
        }




    }

    @Test
    public void ofdToPdf() throws Exception {
        String uploadUrl = "http://192.168.90.190:8080/file/apis/v1/file/uploadFile/report";
        String downLoadUrl = "http://192.168.90.190:8080/file/apis/v1/file/downloadFile";
        String pdfPath = "F:\\kk\\1.pdf";
        File file = new File("F:\\kk\\0185444270932-24318018211000463811.ofd");
        /* File pdffile = new File(pdfPath);*/
        InputStream fileIs = pdfUtils.getInputStreamFromRemoteUrl("http://testtmcsz.shinetour.com/file/apis/v1/file/downloadFile?path=/invoicePzg/20240805/20240805175933113-2220611093871-90202406291449091273.ofd&fileName=2220611093871-90202406291449091273.ofd");


        InputStream ofdInputStream = fileIs;

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        ConvertHelper.usePDFBox();
        ConvertHelper.toPdf(ofdInputStream, byteArrayOutputStream);
        byte[] buffer = byteArrayOutputStream.toByteArray();
        InputStream byteArrayInputStream = new ByteArrayInputStream(buffer);
        String res = pdfUtils.inputStreamUpload(uploadUrl, "test.pdf", byteArrayInputStream);
        System.out.print(res);
    }

    @Test
    public void testRandom() {
      System.out.println(Math.random()*1000000);
    }

    @Test
    public void pdfConverter() throws IOException {
        File tempfile = new File("/temp/pdfConverter" + (new Date()).getTime() + "-" + String.format("%06d", (int)(Math.random()*1000000))+ ".ofd");
        PDFConverter pdfConverter = new PDFConverter(Paths.get(tempfile.getPath()));
        File pdfFile = new File("F:\\kk\\1.pdf");
        InputStream pdfSteam = new FileInputStream(pdfFile);
        File pdfFile2 = new File("/temp/pdfConverter" + (new Date()).getTime() + "-" + String.format("%06d", (int)(Math.random()*1000000)) + ".pdf");
        try (OutputStream outputStream = new FileOutputStream(pdfFile2)) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = pdfSteam.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
        }
        pdfConverter.convert(Paths.get(pdfFile2.getPath()));
        pdfConverter.close();
        String uploadUrl = "http://192.168.90.190:8080/file/apis/v1/file/uploadFile4Slice/testSlice";

        InputStream inputStream = new FileInputStream(tempfile);
        //pdfFile2.deleteOnExit();
        tempfile.deleteOnExit();
        String res = pdfUtils.inputStreamUpload4Slice(uploadUrl, "converterToPdf.ofd",MD5("converterToPdf.ofd"+DateUtil.getCurrentTime(DateUtil.PATTERN_STANDARD17W)) ,1,1,inputStream);
        System.out.print(res);
        inputStream.close();

    }

    @Test
    public void testTempfile() throws IOException {
      /*  File tempfiledir = new File("/temp");
        File tempfile = new File("/temp/converterToPdf.ofd");
        if (!tempfiledir.exists()) {
            tempfiledir.mkdirs();
        }
        tempfile.createNewFile();
        System.out.print(tempfile.getAbsolutePath());*/
        long lt =new Long( "1727083200000");
        Date t = new Date(lt);
        System.out.print(DateUtil.date2String(t,DateUtil.PATTERN_STANDARD19H));
    }

    @Test
    public void imageConverter() throws IOException {

        ImageConverter pdfConverter = new ImageConverter(Paths.get("F:\\kk\\imagetoofd.ofd"));
        pdfConverter.convert(Paths.get("F:\\kk\\temp1.png"));
        pdfConverter.close();

    }

    @Test
    public void ofdToImage() throws Exception {
        String uploadUrl = "http://192.168.90.190:8080/file/apis/v1/file/uploadFile/report";
        String downLoadUrl = "http://192.168.90.190:8080/file/apis/v1/file/downloadFile";
        String pdfPath = "F:\\kk\\1.pdf";
        File file = new File("F:\\kk\\2220611093871-90202406291449091273.ofd");

        InputStream fileIs = pdfUtils.getInputStreamFromRemoteUrl("http://testtmcsz.shinetour.com/file/apis/v1/file/downloadFile?path=/invoicePzg/20240805/20240805175933113-2220611093871-90202406291449091273.ofd&fileName=2220611093871-90202406291449091273.ofd");


        //OFDDoc ofdDoc = new OFDDoc(Paths.get("F:\\kk\\pdftoofd.ofd"));
        try (OFDReader reader = new OFDReader(fileIs);) {
            ImageMaker imageMaker = new ImageMaker(reader, 15);
            for (int i = 0; i < imageMaker.pageSize(); i++) {
                // 4. 指定页码转换图片
                BufferedImage image = imageMaker.makePage(i);

                ByteArrayOutputStream ofdOutput = new ByteArrayOutputStream();
                // 5. 存储为指定格式图片
                ImageIO.write(image, "JPG", ofdOutput);
                InputStream byteArrayInputStream = new ByteArrayInputStream(ofdOutput.toByteArray());
                String res = pdfUtils.inputStreamUpload(uploadUrl, "ofd2Img" + i + ".jpg", byteArrayInputStream);
                byteArrayInputStream.close();
                ofdOutput.close();
                System.out.print(res);
            }

        }


    }

    @Test
    public void testMYinterface() {
        String sid = "NET:0200928430294150321760_98F3DFA34DBA86B1D645CAEABADCFCFC";
        String json = "{\"image\":{\"code\":\"II24000189970\",\"invoiceType\":2,\"invoiceSubType\":7,\"invoiceClass\":2,\"invoiceStatus\":1,\"imageUrl\":\"//tmc.shinetour.com/file/apis/v1/file/downloadFile?path=/invoicePzg/20240812/20240812155614869-8365901041419-24138836211001486315.ofd&fileName=8365901041419-24138836211001486315.ofd\",\"imageUrlName\":\"凌莲静_24138836211001486315_8365901041419_null_TC2400053396_豪雅（上海）光学有限公司.ofd\",\"status\":3,\"clientCode\":\"S124869\",\"clientName\":\"豪雅（上海）光学有限公司\",\"groupCode\":\"G103647\",\"groupName\":\"豪雅集团\",\"creatorCode\":\"sys\",\"creator\":\"系统\",\"createTime\":\"2024-08-12 15:56:15\",\"invoiceNumber\":\"8365901041419\",\"invoiceCode\":\"24138836211001486315\",\"buyerCode\":null,\"buyerName\":\"豪雅（上海）光学有限公司\",\"buyerTaxNumber\":\"91310115752464377J\",\"buyerAddressTel\":null,\"buyerBankAccount\":null,\"saleCode\":null,\"saleName\":\"广东美亚商旅科技有限公司\",\"saleTaxNumber\":null,\"saleAddressTel\":null,\"saleBankAccount\":null,\"fillUnit\":null,\"checkCode\":null,\"sjhjAmount\":\"957.80\",\"totalTax\":\"86.2\",\"deductionRatio\":\"9\",\"totalAmount\":\"878.72\",\"billingDate\":\"2024-09-12\",\"billingTime\":\"17\",\"amountTax\":\"1044.00\",\"amountTaxCn\":\"壹仟零肆拾肆元\",\"administrativeDivisionName\":null,\"machineCode\":null,\"remark\":null,\"passenger\":{\"code\":\"II24000189970\",\"imageCode\":null,\"passengerCode\":\"P2981381\",\"passengerName\":\"凌莲静\",\"certificateId\":\"450621197702070020\",\"ticketNo\":\"8365901041419\",\"travelCode\":\"24138836211001486315\"},\"order\":{\"code\":\"II24000189970\",\"imageCode\":null,\"taxationFee\":\"0.00\",\"fuelSurtax\":\"0.00\",\"civilAviationDevelopmentFund\":\"0.00\",\"airTicketType\":\"国内机票\",\"insuranceFee\":\"0.00\",\"faceValue\":\"1044.00\",\"businessOrderId\":null},\"records\":[{\"code\":\"IE24001083995\",\"imageCode\":\"II24000189970\",\"businessOrderId\":\"TC2400053396\",\"passengerCode\":\"P2981381\",\"businessOrderType\":2,\"claimant\":\"系统\",\"claimantCode\":\"sys\",\"claimTime\":\"2024-08-12 15:56:15\",\"supplierCode\":null,\"supplierName\":null,\"sellingCost\":null,\"clientName\":null,\"clientCode\":null,\"airTicketType\":null,\"claimedAmount\":null,\"invoiceRecoveryStatus\":null,\"remarkReason\":null,\"segmentNo\":null,\"electronicOrderNumber\":null}],\"segments\":[{\"code\":\"IS24000118285\",\"imageCode\":\"II24000189970\",\"trainFlightNumber\":\"NS8558\",\"departureTime\":\"2024-07-24 14:10:00\",\"arrivalTime\":null,\"originCityCode\":null,\"originCityName\":\"上海\",\"destinationCityCode\":null,\"destinationCityName\":\"厦门\",\"seatType\":\"经济舱\",\"carrierCode\":null,\"carrierName\":\"厦门航空\"}],\"hasClaimRecord\":false,\"details\":[],\"fileKey\":null,\"signTaskId\":null,\"signedFileKey\":null,\"claimedAmount\":null,\"claimAmount\":null,\"deductionTaxAmount\":\"86.2\"}}";
        Map<String, Object> params = JSONObject.parseObject(json);
        Map<String, Object> hearder = new HashMap<>();
        String res = "";
        String url = "https://tmc.shinetour.com/invoice//apis/v1/imageInvoice/update_image_invoice";
        hearder.put("sid", sid);
        hearder.put("source", "tmc");
        res = httpUtil.httpRestRequest(params, url, hearder, String.class);
        System.out.print(res);
    }

    @Test
    public void testsendInvoiceEmail() {
        String sid = "NET:1210021490187300902037_7D702C595F3BA18D74FF9F4A03F3579A";
        String json = "{\"billDetails\":[],\"payType\":4,\"billId\":\"ZD240004952302\",\"currency\":\"CNY\",\"money\":4000,\"moneyRmb\":4000,\"dealMoneyRmb\":4000,\"rateDifferenceFee\":0,\"exchangeRate\":1,\"orderEntry\":3,\"payApplicationId\":\"PR2400167877\",\"outsideFlowId\":\"PA0043212408290058\",\"payeeAccountId\":\"6212253602071000691\",\"payeeAccountName\":\"KIM CALVIN JUNGMIN\",\"payeeFinancialInstitution\":\"工商银行\",\"payeeId\":\"S120762\",\"payeeName\":\"US GZ Consulate\",\"payerAccountId\":\"120911249810301\",\"payerAccountName\":\"招商银行广州远洋支行（0301）\",\"payerFinancialInstitution\":\"招商银行广州远洋支行\",\"payerId\":\"PTSH000000\",\"payerName\":\"广东美亚商旅科技有限公司\",\"transactionTime\":\"2024-08-30 18:06\",\"files\":[]}";


        Map<String, Object> params = new HashMap<>();
        params = JSONObject.parseObject(json);
        Map<String, Object> hearder = new HashMap<>();
        String res = "";
        String url = "https://pay.shinetour.com/paycenter//apis/v1/paymentorders/billRefundOffline";
        hearder.put("sid", sid);
        hearder.put("source", "tmc");



        res = httpUtil.httpRestRequest(params, url, hearder, String.class);
        System.out.print(res);
    }
    @Test
    public void testMYinterface2() {
        String sid = "NET:4220427491516135615265_80A9EA7EDA315A41AE66B3ADDE5D609A";
        String json = "{\"flowId\":\"LS240329948\"}";
        Map<String, Object> params = JSONObject.parseObject(json);
        Map<String, Object> hearder = new HashMap<>();
        String res = "";
        String url = "https://pay.shinetour.com/paycenter/apis/v1/capitalflow/repeat_push_bkcapitalflow_2_orderprocess";
        hearder.put("sid", sid);
        hearder.put("source", "tmc");
        res = httpUtil.httpRestRequest(params, url, hearder, String.class);
        System.out.print(res);
    }

    @Test
    public void testMYinterface3() {
        Date n = new Date();
        long timeStamp = n.getTime();
        String apiKey = "AYATDI2B3XfV@Q%I7hJGSGS!SFU0Ew$7";
        String json = "{\"flowId\":\"LS240329948\"}";
        Map<String, Object> params = JSONObject.parseObject(json);
        String sendJson = JsonUtil.getJSONString(params);
        Map<String, Object> hearder = new HashMap<>();
        String res = "";
        String url = "https://pay.shinetour.com/paycenter/apis/v1/capitalflow/repeat_push_bkcapitalflow_2_orderprocess";
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(sendJson);
        stringBuffer.append("," + apiKey);
        stringBuffer.append("," + timeStamp);
        hearder.put("singature", SHAUtil.getSHA1(stringBuffer.toString()));
        hearder.put("timestamp", timeStamp);
        hearder.put("source", "tmc");
        System.out.print(stringBuffer.toString());
        System.out.print("|"+hearder.get("singature"));
        res = httpUtil.httpRestRequest(JSONObject.parseObject(sendJson), url, hearder, String.class);
        System.out.print(res);
    }
    @Test
    public void testMYinterfaceWithSignjiedong() {
        Date n = new Date();
        long timeStamp = n.getTime();
        String apiKey = "AYATDI2B3XfV@Q%I7hJGSGS!SFU0Ew$7";
        String json = "{\"businessOrderId\":\"IO2500014412\",\"buyerId\":\"S119027\",\"opId\":\"系统\",\"opName\":\"系统\",\"remark\":\"解冻\"} ";
        Map<String, Object> params = JSONObject.parseObject(json);
        String sendJson = JsonUtil.getJSONString(params);
        Map<String, Object> hearder = new HashMap<>();
        String res = "";
        String url = "https://pay.shinetour.com/paycenter/apiss/v1/paymentordersforinn/creditUnFreeze";
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(sendJson);
        stringBuffer.append("," + apiKey);
        stringBuffer.append("," + timeStamp);
        hearder.put("singature", SHAUtil.getSHA1(stringBuffer.toString()));
        hearder.put("timestamp", timeStamp);
        hearder.put("source", "tmc");
        System.out.print(stringBuffer.toString());
        System.out.print("|"+hearder.get("singature"));
        res = httpUtil.httpRestRequest(JSONObject.parseObject(sendJson), url, hearder, String.class);
        System.out.print(res);
    }
    @Test
    public void testclaimmoneynotify() {
        Date n = new Date();
        long timeStamp = n.getTime();
        String apiKey = "AYATDI2B3XfV@Q%I7hJGSGS!SFU0Ew$7";
        String json = "{\"flowDetailList\":[{\"bankAccountCode\":\"3602001009200267463\",\"bankAccountName\":\"广东美亚商旅科技有限公司\",\"createName\":\"招行CBS\",\"incomeFee\":107200,\"outSerialNumber\":\"2025041001399577\",\"paymentBankAccount\":\"9558854000009011946\",\"paymentBankName\":\"中广核（内蒙古）新能源投资有限公司包头分公司\",\"paymentFinOrg\":\"中国工商银行深圳市分行营业部\",\"procedureFee\":0,\"remitter\":\"202504100289443BU250220SW000005\",\"tradeDate\":\"2025-04-10\",\"tradeTime\":\"11:41:00\",\"transactionAmount\":107200,\"transactionCurrency\":\"人民币\",\"purpose\":\"ZH43BU250220SW000005\",\"postscript\":null,\"remark\":null}]} ";
        Map<String, Object> params = JSONObject.parseObject(json);
        String sendJson = JsonUtil.getJSONString(params);
        Map<String, Object> hearder = new HashMap<>();
        String res = "";
        String url = "https://pay.shinetour.com/paycenter/apiss/v1/claimmoneynotify/inserts";
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(sendJson);
        stringBuffer.append("," + apiKey);
        stringBuffer.append("," + timeStamp);
        hearder.put("singature", SHAUtil.getSHA1(stringBuffer.toString()));
        hearder.put("timestamp", timeStamp);
        hearder.put("source", "tmc");
        System.out.print(stringBuffer.toString());
        System.out.print("|"+hearder.get("singature"));
        res = httpUtil.httpRestRequest(JSONObject.parseObject(sendJson), url, hearder, String.class);
        System.out.print(res);
    }

    @Test
    public void testsign() {
        Date n = new Date();
        long timeStamp = new Date().getTime();
        String apiKey = "AYATDI2B3XfV@Q%I7hJGSGS!SFU0Ew$7";
        String json = "{\"flowDetailList\":[{\"bankAccountCode\":\"st011@meiya.com\",\"bankAccountName\":\"广东美亚商旅科技有限公司\",\"createName\":\"系统\",\"incomeFee\":4800,\"outSerialNumber\":\"2025041022001442111427785646\",\"paymentBankAccount\":\"135******75\",\"paymentBankName\":\"**丰\",\"paymentFinOrg\":\"支付宝\",\"procedureFee\":0,\"remitter\":\"1470649061112642111\",\"tradeDate\":\"2025-04-10\",\"tradeTime\":\"07:17:15\",\"transactionAmount\":4800,\"transactionCurrency\":\"CNY\",\"purpose\":null,\"postscript\":null,\"remark\":null},{\"bankAccountCode\":\"st013@meiya.com\",\"bankAccountName\":\"广东美亚商旅科技有限公司\",\"createName\":\"系统\",\"incomeFee\":42700,\"outSerialNumber\":\"2025041022001452021410624551\",\"paymentBankAccount\":\"p28***@163.com\",\"paymentBankName\":\"*剑\",\"paymentFinOrg\":\"支付宝\",\"procedureFee\":0,\"remitter\":\"1470650195183652021\",\"tradeDate\":\"2025-04-10\",\"tradeTime\":\"07:50:03\",\"transactionAmount\":42700,\"transactionCurrency\":\"CNY\",\"purpose\":null,\"postscript\":null,\"remark\":null},{\"bankAccountCode\":\"st011@meiya.com\",\"bankAccountName\":\"广东美亚商旅科技有限公司\",\"createName\":\"系统\",\"incomeFee\":1700,\"outSerialNumber\":\"2025041022001467451458162170\",\"paymentBankAccount\":\"186******07\",\"paymentBankName\":\"**欢\",\"paymentFinOrg\":\"支付宝\",\"procedureFee\":0,\"remitter\":\"1470654984027867451\",\"tradeDate\":\"2025-04-10\",\"tradeTime\":\"08:09:24\",\"transactionAmount\":1700,\"transactionCurrency\":\"CNY\",\"purpose\":null,\"postscript\":null,\"remark\":null},{\"bankAccountCode\":\"st013@meiya.com\",\"bankAccountName\":\"广东美亚商旅科技有限公司\",\"createName\":\"系统\",\"incomeFee\":1700,\"outSerialNumber\":\"2025041022001461501444050812\",\"paymentBankAccount\":\"159******69\",\"paymentBankName\":\"**奇\",\"paymentFinOrg\":\"支付宝\",\"procedureFee\":0,\"remitter\":\"1470659162440461501\",\"tradeDate\":\"2025-04-10\",\"tradeTime\":\"08:36:03\",\"transactionAmount\":1700,\"transactionCurrency\":\"CNY\",\"purpose\":null,\"postscript\":null,\"remark\":null},{\"bankAccountCode\":\"st008@meiya.com\",\"bankAccountName\":\"广东美亚商旅科技有限公司\",\"createName\":\"系统\",\"incomeFee\":3600,\"outSerialNumber\":\"2025041022001491191426196693\",\"paymentBankAccount\":\"186******55\",\"paymentBankName\":\"**军\",\"paymentFinOrg\":\"支付宝\",\"procedureFee\":0,\"remitter\":\"1470662234086691191\",\"tradeDate\":\"2025-04-10\",\"tradeTime\":\"08:51:34\",\"transactionAmount\":3600,\"transactionCurrency\":\"CNY\",\"purpose\":null,\"postscript\":null,\"remark\":null},{\"bankAccountCode\":\"st008@meiya.com\",\"bankAccountName\":\"广东美亚商旅科技有限公司\",\"createName\":\"系统\",\"incomeFee\":20500,\"outSerialNumber\":\"2025041022001407371453907163\",\"paymentBankAccount\":\"xuq***@hotmail.com\",\"paymentBankName\":\"*强\",\"paymentFinOrg\":\"支付宝\",\"procedureFee\":0,\"remitter\":\"1470680073147907371\",\"tradeDate\":\"2025-04-10\",\"tradeTime\":\"08:56:37\",\"transactionAmount\":20500,\"transactionCurrency\":\"CNY\",\"purpose\":null,\"postscript\":null,\"remark\":null},{\"bankAccountCode\":\"st011@meiya.com\",\"bankAccountName\":\"广东美亚商旅科技有限公司\",\"createName\":\"系统\",\"incomeFee\":1000,\"outSerialNumber\":\"2025041022001402571422156425\",\"paymentBankAccount\":\"188******16\",\"paymentBankName\":\"**豪\",\"paymentFinOrg\":\"支付宝\",\"procedureFee\":0,\"remitter\":\"1470623955261702571\",\"tradeDate\":\"2025-04-10\",\"tradeTime\":\"09:43:35\",\"transactionAmount\":1000,\"transactionCurrency\":\"CNY\",\"purpose\":null,\"postscript\":null,\"remark\":null},{\"bankAccountCode\":\"st007@meiya.com\",\"bankAccountName\":\"广东美亚商旅科技有限公司\",\"createName\":\"系统\",\"incomeFee\":131000,\"outSerialNumber\":\"2025041022001459301448597484\",\"paymentBankAccount\":\"xuy***@sinopec.com\",\"paymentBankName\":\"*悦\",\"paymentFinOrg\":\"支付宝\",\"procedureFee\":0,\"remitter\":\"1470668809815959301\",\"tradeDate\":\"2025-04-10\",\"tradeTime\":\"10:00:07\",\"transactionAmount\":131000,\"transactionCurrency\":\"CNY\",\"purpose\":null,\"postscript\":null,\"remark\":null},{\"bankAccountCode\":\"st013@meiya.com\",\"bankAccountName\":\"广东美亚商旅科技有限公司\",\"createName\":\"系统\",\"incomeFee\":5400,\"outSerialNumber\":\"2025041022001447841404134676\",\"paymentBankAccount\":\"138******97\",\"paymentBankName\":\"*伟\",\"paymentFinOrg\":\"支付宝\",\"procedureFee\":0,\"remitter\":\"1470637951749547841\",\"tradeDate\":\"2025-04-10\",\"tradeTime\":\"10:28:30\",\"transactionAmount\":5400,\"transactionCurrency\":\"CNY\",\"purpose\":null,\"postscript\":null,\"remark\":null},{\"bankAccountCode\":\"myshinetour@meiya.com\",\"bankAccountName\":\"广东美亚商旅科技有限公司\",\"createName\":\"系统\",\"incomeFee\":384700,\"outSerialNumber\":\"20250410200040011100980015001364\",\"paymentBankAccount\":\"183******64\",\"paymentBankName\":\"**婷\",\"paymentFinOrg\":\"支付宝\",\"procedureFee\":0,\"remitter\":\"1470633510341335981\",\"tradeDate\":\"2025-04-10\",\"tradeTime\":\"11:22:02\",\"transactionAmount\":384700,\"transactionCurrency\":\"CNY\",\"purpose\":\"LEE SECHEUN机票\",\"postscript\":null,\"remark\":null},{\"bankAccountCode\":\"st007@meiya.com\",\"bankAccountName\":\"广东美亚商旅科技有限公司\",\"createName\":\"系统\",\"incomeFee\":52000,\"outSerialNumber\":\"2025041022001439171435785407\",\"paymentBankAccount\":\"189******58\",\"paymentBankName\":\"**辉\",\"paymentFinOrg\":\"支付宝\",\"procedureFee\":0,\"remitter\":\"1470648192170139171\",\"tradeDate\":\"2025-04-10\",\"tradeTime\":\"11:43:06\",\"transactionAmount\":52000,\"transactionCurrency\":\"CNY\",\"purpose\":null,\"postscript\":null,\"remark\":null},{\"bankAccountCode\":\"st008@meiya.com\",\"bankAccountName\":\"广东美亚商旅科技有限公司\",\"createName\":\"系统\",\"incomeFee\":16100,\"outSerialNumber\":\"2025041022001492931437598166\",\"paymentBankAccount\":\"134******28\",\"paymentBankName\":\"*超\",\"paymentFinOrg\":\"支付宝\",\"procedureFee\":0,\"remitter\":\"1470666147210892931\",\"tradeDate\":\"2025-04-10\",\"tradeTime\":\"11:50:31\",\"transactionAmount\":16100,\"transactionCurrency\":\"CNY\",\"purpose\":null,\"postscript\":null,\"remark\":null},{\"bankAccountCode\":\"st007@meiya.com\",\"bankAccountName\":\"广东美亚商旅科技有限公司\",\"createName\":\"系统\",\"incomeFee\":131000,\"outSerialNumber\":\"2025041022001442051456877958\",\"paymentBankAccount\":\"182******43\",\"paymentBankName\":\"**林\",\"paymentFinOrg\":\"支付宝\",\"procedureFee\":0,\"remitter\":\"1470665263218242051\",\"tradeDate\":\"2025-04-10\",\"tradeTime\":\"11:49:44\",\"transactionAmount\":131000,\"transactionCurrency\":\"CNY\",\"purpose\":null,\"postscript\":null,\"remark\":null},{\"bankAccountCode\":\"st013@meiya.com\",\"bankAccountName\":\"广东美亚商旅科技有限公司\",\"createName\":\"系统\",\"incomeFee\":200,\"outSerialNumber\":\"2025041022001437791446998019\",\"paymentBankAccount\":\"151******15\",\"paymentBankName\":\"*祥\",\"paymentFinOrg\":\"支付宝\",\"procedureFee\":0,\"remitter\":\"1470657388400237791\",\"tradeDate\":\"2025-04-10\",\"tradeTime\":\"11:59:57\",\"transactionAmount\":200,\"transactionCurrency\":\"CNY\",\"purpose\":null,\"postscript\":null,\"remark\":null},{\"bankAccountCode\":\"st008@meiya.com\",\"bankAccountName\":\"广东美亚商旅科技有限公司\",\"createName\":\"系统\",\"incomeFee\":60600,\"outSerialNumber\":\"2025041022001471531448223060\",\"paymentBankAccount\":\"176******88\",\"paymentBankName\":\"**亮\",\"paymentFinOrg\":\"支付宝\",\"procedureFee\":0,\"remitter\":\"1470669469925171531\",\"tradeDate\":\"2025-04-10\",\"tradeTime\":\"12:11:56\",\"transactionAmount\":60600,\"transactionCurrency\":\"CNY\",\"purpose\":null,\"postscript\":null,\"remark\":null},{\"bankAccountCode\":\"st008@meiya.com\",\"bankAccountName\":\"广东美亚商旅科技有限公司\",\"createName\":\"系统\",\"incomeFee\":16700,\"outSerialNumber\":\"2025041022001492931437623049\",\"paymentBankAccount\":\"134******28\",\"paymentBankName\":\"*超\",\"paymentFinOrg\":\"支付宝\",\"procedureFee\":0,\"remitter\":\"1470666169022592931\",\"tradeDate\":\"2025-04-10\",\"tradeTime\":\"12:20:50\",\"transactionAmount\":16700,\"transactionCurrency\":\"CNY\",\"purpose\":null,\"postscript\":null,\"remark\":null},{\"bankAccountCode\":\"myshinetour@meiya.com\",\"bankAccountName\":\"广东美亚商旅科技有限公司\",\"createName\":\"系统\",\"incomeFee\":284600,\"outSerialNumber\":\"2025041022001426211411963361\",\"paymentBankAccount\":\"186******48\",\"paymentBankName\":\"*胜\",\"paymentFinOrg\":\"支付宝\",\"procedureFee\":0,\"remitter\":\"1470658285593526211\",\"tradeDate\":\"2025-04-10\",\"tradeTime\":\"12:33:35\",\"transactionAmount\":284600,\"transactionCurrency\":\"CNY\",\"purpose\":null,\"postscript\":null,\"remark\":null},{\"bankAccountCode\":\"st008@meiya.com\",\"bankAccountName\":\"广东美亚商旅科技有限公司\",\"createName\":\"系统\",\"incomeFee\":1900,\"outSerialNumber\":\"2025041022001459241411646167\",\"paymentBankAccount\":\"138******02\",\"paymentBankName\":\"**娟\",\"paymentFinOrg\":\"支付宝\",\"procedureFee\":0,\"remitter\":\"1470647542358359241\",\"tradeDate\":\"2025-04-10\",\"tradeTime\":\"12:43:00\",\"transactionAmount\":1900,\"transactionCurrency\":\"CNY\",\"purpose\":null,\"postscript\":null,\"remark\":null},{\"bankAccountCode\":\"st008@meiya.com\",\"bankAccountName\":\"广东美亚商旅科技有限公司\",\"createName\":\"系统\",\"incomeFee\":9700,\"outSerialNumber\":\"2025041022001492931435847395\",\"paymentBankAccount\":\"134******28\",\"paymentBankName\":\"*超\",\"paymentFinOrg\":\"支付宝\",\"procedureFee\":0,\"remitter\":\"1470666324054092931\",\"tradeDate\":\"2025-04-10\",\"tradeTime\":\"12:53:54\",\"transactionAmount\":9700,\"transactionCurrency\":\"CNY\",\"purpose\":null,\"postscript\":null,\"remark\":null},{\"bankAccountCode\":\"st008@meiya.com\",\"bankAccountName\":\"广东美亚商旅科技有限公司\",\"createName\":\"系统\",\"incomeFee\":1000,\"outSerialNumber\":\"2025041022001442051457678114\",\"paymentBankAccount\":\"134******97\",\"paymentBankName\":\"**磊\",\"paymentFinOrg\":\"支付宝\",\"procedureFee\":0,\"remitter\":\"1470665165759042051\",\"tradeDate\":\"2025-04-10\",\"tradeTime\":\"13:06:46\",\"transactionAmount\":1000,\"transactionCurrency\":\"CNY\",\"purpose\":null,\"postscript\":null,\"remark\":null},{\"bankAccountCode\":\"st004@meiya.com\",\"bankAccountName\":\"广东美亚商旅科技有限公司\",\"createName\":\"系统\",\"incomeFee\":42400,\"outSerialNumber\":\"2025041022001475911430502134\",\"paymentBankAccount\":\"gzs***@163.com\",\"paymentBankName\":\"**胜\",\"paymentFinOrg\":\"支付宝\",\"procedureFee\":0,\"remitter\":\"1470631778152675911\",\"tradeDate\":\"2025-04-10\",\"tradeTime\":\"13:12:36\",\"transactionAmount\":42400,\"transactionCurrency\":\"CNY\",\"purpose\":null,\"postscript\":null,\"remark\":null},{\"bankAccountCode\":\"st013@meiya.com\",\"bankAccountName\":\"广东美亚商旅科技有限公司\",\"createName\":\"系统\",\"incomeFee\":700,\"outSerialNumber\":\"2025041022001483701406770410\",\"paymentBankAccount\":\"139******02\",\"paymentBankName\":\"**原\",\"paymentFinOrg\":\"支付宝\",\"procedureFee\":0,\"remitter\":\"1470628114299583701\",\"tradeDate\":\"2025-04-10\",\"tradeTime\":\"13:18:00\",\"transactionAmount\":700,\"transactionCurrency\":\"CNY\",\"purpose\":null,\"postscript\":null,\"remark\":null},{\"bankAccountCode\":\"myshinetour@meiya.com\",\"bankAccountName\":\"广东美亚商旅科技有限公司\",\"createName\":\"系统\",\"incomeFee\":110000,\"outSerialNumber\":\"20250410200040011100910016737140\",\"paymentBankAccount\":\"132******04\",\"paymentBankName\":\"**阳\",\"paymentFinOrg\":\"支付宝\",\"procedureFee\":0,\"remitter\":\"1470633600211039911\",\"tradeDate\":\"2025-04-10\",\"tradeTime\":\"13:23:09\",\"transactionAmount\":110000,\"transactionCurrency\":\"CNY\",\"purpose\":\"赵晨阳 东航退回1100元\",\"postscript\":null,\"remark\":null},{\"bankAccountCode\":\"st008@meiya.com\",\"bankAccountName\":\"广东美亚商旅科技有限公司\",\"createName\":\"系统\",\"incomeFee\":1500,\"outSerialNumber\":\"2025041022001420251424043325\",\"paymentBankAccount\":\"778***@qq.com\",\"paymentBankName\":\"*军\",\"paymentFinOrg\":\"支付宝\",\"procedureFee\":0,\"remitter\":\"1470649695274320251\",\"tradeDate\":\"2025-04-10\",\"tradeTime\":\"13:35:01\",\"transactionAmount\":1500,\"transactionCurrency\":\"CNY\",\"purpose\":null,\"postscript\":null,\"remark\":null},{\"bankAccountCode\":\"st008@meiya.com\",\"bankAccountName\":\"广东美亚商旅科技有限公司\",\"createName\":\"系统\",\"incomeFee\":2300,\"outSerialNumber\":\"2025041022001451181428532273\",\"paymentBankAccount\":\"liu***@163.com\",\"paymentBankName\":\"**旭\",\"paymentFinOrg\":\"支付宝\",\"procedureFee\":0,\"remitter\":\"1470644813166551181\",\"tradeDate\":\"2025-04-10\",\"tradeTime\":\"13:43:01\",\"transactionAmount\":2300,\"transactionCurrency\":\"CNY\",\"purpose\":null,\"postscript\":null,\"remark\":null},{\"bankAccountCode\":\"myshinetour@meiya.com\",\"bankAccountName\":\"广东美亚商旅科技有限公司\",\"createName\":\"系统\",\"incomeFee\":28000,\"outSerialNumber\":\"2025041022001432721401760242\",\"paymentBankAccount\":\"166******52\",\"paymentBankName\":\"* EWAN JOHN\",\"paymentFinOrg\":\"支付宝\",\"procedureFee\":0,\"remitter\":\"1470621509294032721\",\"tradeDate\":\"2025-04-10\",\"tradeTime\":\"14:09:59\",\"transactionAmount\":28000,\"transactionCurrency\":\"CNY\",\"purpose\":null,\"postscript\":null,\"remark\":null},{\"bankAccountCode\":\"st013@meiya.com\",\"bankAccountName\":\"广东美亚商旅科技有限公司\",\"createName\":\"系统\",\"incomeFee\":69200,\"outSerialNumber\":\"2025041022001400741448389089\",\"paymentBankAccount\":\"997***@qq.com\",\"paymentBankName\":\"**皞\",\"paymentFinOrg\":\"支付宝\",\"procedureFee\":0,\"remitter\":\"1470998663054100741\",\"tradeDate\":\"2025-04-10\",\"tradeTime\":\"14:16:26\",\"transactionAmount\":69200,\"transactionCurrency\":\"CNY\",\"purpose\":null,\"postscript\":null,\"remark\":null},{\"bankAccountCode\":\"st007@meiya.com\",\"bankAccountName\":\"广东美亚商旅科技有限公司\",\"createName\":\"系统\",\"incomeFee\":149000,\"outSerialNumber\":\"2025041022001477501444283936\",\"paymentBankAccount\":\"553***@qq.com\",\"paymentBankName\":\"**辰\",\"paymentFinOrg\":\"支付宝\",\"procedureFee\":0,\"remitter\":\"1470657944583177501\",\"tradeDate\":\"2025-04-10\",\"tradeTime\":\"14:16:11\",\"transactionAmount\":149000,\"transactionCurrency\":\"CNY\",\"purpose\":null,\"postscript\":null,\"remark\":null},{\"bankAccountCode\":\"st004@meiya.com\",\"bankAccountName\":\"广东美亚商旅科技有限公司\",\"createName\":\"系统\",\"incomeFee\":2600,\"outSerialNumber\":\"2025041022001429981421826073\",\"paymentBankAccount\":\"139******73\",\"paymentBankName\":\"**城\",\"paymentFinOrg\":\"支付宝\",\"procedureFee\":0,\"remitter\":\"1470633961205329981\",\"tradeDate\":\"2025-04-10\",\"tradeTime\":\"14:46:33\",\"transactionAmount\":2600,\"transactionCurrency\":\"CNY\",\"purpose\":null,\"postscript\":null,\"remark\":null},{\"bankAccountCode\":\"nicle@meiya.com\",\"bankAccountName\":\"广东美亚商旅科技有限公司\",\"createName\":\"系统\",\"incomeFee\":10000000,\"outSerialNumber\":\"20250410020070011550290081159524\",\"paymentBankAccount\":\"nicle@meiya.com\",\"paymentBankName\":\"广东美亚商旅科技有限公司\",\"paymentFinOrg\":\"支付宝\",\"procedureFee\":0,\"remitter\":\"364958777573291\",\"tradeDate\":\"2025-04-10\",\"tradeTime\":\"15:01:01\",\"transactionAmount\":10000000,\"transactionCurrency\":\"CNY\",\"purpose\":\"ZJDB2025041000548\",\"postscript\":null,\"remark\":null},{\"bankAccountCode\":\"st008@meiya.com\",\"bankAccountName\":\"广东美亚商旅科技有限公司\",\"createName\":\"系统\",\"incomeFee\":4700,\"outSerialNumber\":\"2025041022001421571416401864\",\"paymentBankAccount\":\"158******85\",\"paymentBankName\":\"*波\",\"paymentFinOrg\":\"支付宝\",\"procedureFee\":0,\"remitter\":\"1470622653876821571\",\"tradeDate\":\"2025-04-10\",\"tradeTime\":\"15:06:45\",\"transactionAmount\":4700,\"transactionCurrency\":\"CNY\",\"purpose\":null,\"postscript\":null,\"remark\":null},{\"bankAccountCode\":\"st008@meiya.com\",\"bankAccountName\":\"广东美亚商旅科技有限公司\",\"createName\":\"系统\",\"incomeFee\":4700,\"outSerialNumber\":\"2025041022001421571415603372\",\"paymentBankAccount\":\"158******85\",\"paymentBankName\":\"*波\",\"paymentFinOrg\":\"支付宝\",\"procedureFee\":0,\"remitter\":\"1470622803802421571\",\"tradeDate\":\"2025-04-10\",\"tradeTime\":\"15:12:05\",\"transactionAmount\":4700,\"transactionCurrency\":\"CNY\",\"purpose\":null,\"postscript\":null,\"remark\":null},{\"bankAccountCode\":\"st008@meiya.com\",\"bankAccountName\":\"广东美亚商旅科技有限公司\",\"createName\":\"系统\",\"incomeFee\":15100,\"outSerialNumber\":\"2025041022001428291404480461\",\"paymentBankAccount\":\"151******64\",\"paymentBankName\":\"**志\",\"paymentFinOrg\":\"支付宝\",\"procedureFee\":0,\"remitter\":\"1470633216106728291\",\"tradeDate\":\"2025-04-10\",\"tradeTime\":\"15:29:34\",\"transactionAmount\":15100,\"transactionCurrency\":\"CNY\",\"purpose\":null,\"postscript\":null,\"remark\":null},{\"bankAccountCode\":\"st008@meiya.com\",\"bankAccountName\":\"广东美亚商旅科技有限公司\",\"createName\":\"系统\",\"incomeFee\":2500,\"outSerialNumber\":\"2025041022001498591418961221\",\"paymentBankAccount\":\"115***@163.com\",\"paymentBankName\":\"**伟\",\"paymentFinOrg\":\"支付宝\",\"procedureFee\":0,\"remitter\":\"1470640227372198591\",\"tradeDate\":\"2025-04-10\",\"tradeTime\":\"15:47:18\",\"transactionAmount\":2500,\"transactionCurrency\":\"CNY\",\"purpose\":null,\"postscript\":null,\"remark\":null},{\"bankAccountCode\":\"st011@meiya.com\",\"bankAccountName\":\"广东美亚商旅科技有限公司\",\"createName\":\"系统\",\"incomeFee\":1700,\"outSerialNumber\":\"2025041022001488511444340911\",\"paymentBankAccount\":\"150******09\",\"paymentBankName\":\"*俊\",\"paymentFinOrg\":\"支付宝\",\"procedureFee\":0,\"remitter\":\"1470662879220988511\",\"tradeDate\":\"2025-04-10\",\"tradeTime\":\"16:08:02\",\"transactionAmount\":1700,\"transactionCurrency\":\"CNY\",\"purpose\":null,\"postscript\":null,\"remark\":null},{\"bankAccountCode\":\"st011@meiya.com\",\"bankAccountName\":\"广东美亚商旅科技有限公司\",\"createName\":\"系统\",\"incomeFee\":14000,\"outSerialNumber\":\"2025041022001444431449279734\",\"paymentBankAccount\":\"102***@qq.com\",\"paymentBankName\":\"**宇\",\"paymentFinOrg\":\"支付宝\",\"procedureFee\":0,\"remitter\":\"1470664112752744431\",\"tradeDate\":\"2025-04-10\",\"tradeTime\":\"16:15:29\",\"transactionAmount\":14000,\"transactionCurrency\":\"CNY\",\"purpose\":null,\"postscript\":null,\"remark\":null},{\"bankAccountCode\":\"st011@meiya.com\",\"bankAccountName\":\"广东美亚商旅科技有限公司\",\"createName\":\"系统\",\"incomeFee\":3700,\"outSerialNumber\":\"2025041022001428491448993283\",\"paymentBankAccount\":\"jxb***@gmail.com\",\"paymentBankName\":\"**昂\",\"paymentFinOrg\":\"支付宝\",\"procedureFee\":0,\"remitter\":\"1470666682947328491\",\"tradeDate\":\"2025-04-10\",\"tradeTime\":\"16:17:29\",\"transactionAmount\":3700,\"transactionCurrency\":\"CNY\",\"purpose\":null,\"postscript\":null,\"remark\":null},{\"bankAccountCode\":\"st013@meiya.com\",\"bankAccountName\":\"广东美亚商旅科技有限公司\",\"createName\":\"系统\",\"incomeFee\":19900,\"outSerialNumber\":\"2025041022001407121430454829\",\"paymentBankAccount\":\"186******57\",\"paymentBankName\":\"**峰\",\"paymentFinOrg\":\"支付宝\",\"procedureFee\":0,\"remitter\":\"1470732839414107121\",\"tradeDate\":\"2025-04-10\",\"tradeTime\":\"16:33:44\",\"transactionAmount\":19900,\"transactionCurrency\":\"CNY\",\"purpose\":null,\"postscript\":null,\"remark\":null},{\"bankAccountCode\":\"st011@meiya.com\",\"bankAccountName\":\"广东美亚商旅科技有限公司\",\"createName\":\"系统\",\"incomeFee\":35000,\"outSerialNumber\":\"2025041022001417991447490734\",\"paymentBankAccount\":\"zha***@lenovo.com\",\"paymentBankName\":\"**娟\",\"paymentFinOrg\":\"支付宝\",\"procedureFee\":0,\"remitter\":\"1470637133676517991\",\"tradeDate\":\"2025-04-10\",\"tradeTime\":\"16:55:53\",\"transactionAmount\":35000,\"transactionCurrency\":\"CNY\",\"purpose\":null,\"postscript\":null,\"remark\":null},{\"bankAccountCode\":\"st013@meiya.com\",\"bankAccountName\":\"广东美亚商旅科技有限公司\",\"createName\":\"系统\",\"incomeFee\":4500,\"outSerialNumber\":\"2025041022001470291411539928\",\"paymentBankAccount\":\"175******30\",\"paymentBankName\":\"*彬\",\"paymentFinOrg\":\"支付宝\",\"procedureFee\":0,\"remitter\":\"1470632638103170291\",\"tradeDate\":\"2025-04-10\",\"tradeTime\":\"17:39:01\",\"transactionAmount\":4500,\"transactionCurrency\":\"CNY\",\"purpose\":null,\"postscript\":null,\"remark\":null},{\"bankAccountCode\":\"st013@meiya.com\",\"bankAccountName\":\"广东美亚商旅科技有限公司\",\"createName\":\"系统\",\"incomeFee\":500,\"outSerialNumber\":\"2025041022001471751442871294\",\"paymentBankAccount\":\"493***@qq.com\",\"paymentBankName\":\"**涵\",\"paymentFinOrg\":\"支付宝\",\"procedureFee\":0,\"remitter\":\"1470609659951071751\",\"tradeDate\":\"2025-04-10\",\"tradeTime\":\"17:43:04\",\"transactionAmount\":500,\"transactionCurrency\":\"CNY\",\"purpose\":null,\"postscript\":null,\"remark\":null},{\"bankAccountCode\":\"st013@meiya.com\",\"bankAccountName\":\"广东美亚商旅科技有限公司\",\"createName\":\"系统\",\"incomeFee\":59400,\"outSerialNumber\":\"2025041022001469201410726869\",\"paymentBankAccount\":\"smi***@126.com\",\"paymentBankName\":\"**利\",\"paymentFinOrg\":\"支付宝\",\"procedureFee\":0,\"remitter\":\"1470646065982469201\",\"tradeDate\":\"2025-04-10\",\"tradeTime\":\"17:49:03\",\"transactionAmount\":59400,\"transactionCurrency\":\"CNY\",\"purpose\":null,\"postscript\":null,\"remark\":null}]}";
        Map<String, Object> params = JSONObject.parseObject(json);
        String sendJson = JSONObject.toJSONString(params);
        Map<String, Object> hearder = new HashMap<>();
        String res = "";
        String url = "https://pay.shinetour.com/paycenter/apiss/v1/claimmoneynotify/inserts";
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(json);
        stringBuffer.append("," + apiKey);
        stringBuffer.append("," + timeStamp);
        hearder.put("singature", SHAUtil.getSHA1(stringBuffer.toString()));
        hearder.put("timestamp", timeStamp);
        hearder.put("source", "pc");
        System.out.print(stringBuffer.toString());
        System.out.print("|"+hearder.get("singature"));
        res = httpUtil.doPost( url,json, hearder);
        System.out.print(res);
    }
    @Test
    public void testMYinterfaceWithSignAdvanceCardJieDong() {
        Date n = new Date();
        long timeStamp = n.getTime();
        String apiKey = "AYATDI2B3XfV@Q%I7hJGSGS!SFU0Ew$7";
        String json = "{\"objectId\":\"RB2400252299\",\"payStatus\":4,\"closeType\":\"3\",\"remark\":{\"remark\":\"订单取消\"}} ";
        Map<String, Object> params = JSONObject.parseObject(json);
        String sendJson = JsonUtil.getJSONString(params);
        Map<String, Object> hearder = new HashMap<>();
        String res = "";
        String url = "https://pay.shinetour.com/paycenter/apiss/v1/innBkTradingRecord/updateBkTradingRecordPayStatusClose";
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(sendJson);
        stringBuffer.append("," + apiKey);
        stringBuffer.append("," + timeStamp);
        hearder.put("singature", SHAUtil.getSHA1(stringBuffer.toString()));
        hearder.put("timestamp", timeStamp);
        hearder.put("source", "tmc");
        System.out.print(stringBuffer.toString());
        System.out.print("|"+hearder.get("singature"));
        res = httpUtil.httpRestRequest(JSONObject.parseObject(sendJson), url, hearder, String.class);
        System.out.print(res);
    }
    @Test
    public void testModuleLogger()
    {
        System.out.println(DateUtil.date2String(new Date(),DateUtil.PATTERN_STANDARD19H));
        for(int i=0;i<1000;i++) {

            Logger log =  ModuleLogFactory.createLogger("kklog"+(i%3));
            log.info("bu zhidao xie xie shen me ?gao kuai dian ba ."+i);
            log.error("bu zhidao xie xie shen me ?gao kuai dian ba ."+i);
            log.debug("bu zhidao xie xie shen me ?gao kuai dian ba ."+i);
            log.warn("bu zhidao xie xie shen me ?gao kuai dian ba ."+i);

            //ModuleLogFactory.stop("kklog"+(i%3));
        }
    }


    @Test
    public void testEs() {
        String sid = "NET:0200928430294150321760_98F3DFA34DBA86B1D645CAEABADCFCFC";
        String json =
                "  {\"template\": {\n" +
                "    \"settings\": {\n" +
                "      \"number_of_shards\": \"5\",\n" +
                "      \"number_of_replicas\": \"1\",\n" +
                "\t  \"max_result_window\": 100000000\n" +
                "    }\n" +
                "    \n" +
                "  }\n" +
                "}";
        Map<String, Object> params = JSONObject.parseObject(json);
        Map<String, Object> hearder = new HashMap<>();
        String res = "";
        String url = "http://192.168.90.212:9200/";
        String esOp ="_component_template/component_setting_base";
        hearder.put("sid", sid);
        hearder.put("source", "tmc");
        res = httpUtil.httpRestRequest(params, url+esOp, hearder, String.class,HttpMethod.PUT);
        System.out.print(res);
    }

    @Test
    public void testReadExcel() throws Exception {
        String filePath = "F:\\log\\增值产品消息配置新增公司规则.xlsx";
        String sid = "NET:0230321410393201611076_7E99284246C77AD53F513B252D70640C";

        Map<String, Object> hearder = new HashMap<>();
        String res = "";
        String url = "https://tmc.shinetour.com/infoservice//apis/v1/inforulecustomer/rules_save";
        hearder.put("sid", sid);
        hearder.put("source", "tmc");

        List<Map<String,Object>> excelData = ExcelUtils.readExcel(filePath,null,0);

        for(Map<String,Object> map : excelData)
        {
            System.out.println(map);
            String json = "{\n" +
                    "\t\"rules\": [\n" +
                    "\t\t{\n" +
                    "\n" +
                    "\t\t\t\"infoTypeCode\": \"Market_Order_Granted\",\n" +
                    "\t\t\t\"infoTypeName\": \"增值产品订单_发放通知\",\n" +
                    "\t\t\t\"merchantCode\": \"PTSH000000\",\n" +
                    "\t\t\t\"companyCode\": \"#company_code\",\n" +
                    "\t\t\t\"source\": 28,\n" +
                    "\t\t\t\"sourceName\": \"增值产品\",\n" +
                    "\t\t\t\"section1\": \"增值产品订单\",\n" +
                    "\t\t\t\"section2\": \"已发放\",\n" +
                    "\t\t\t\"sendCondition\": \"状态变为已发放\",\n" +
                    "\t\t\t\"sendConditionValue\": [],\n" +
                    "\t\t\t\"receiveObjects\": [\n" +
                    "\t\t\t\t{\n" +
                    "\t\t\t\t\t\"name\": \"领取人\",\n" +
                    "\t\t\t\t\t\"code\": \"45\",\n" +
                    "\t\t\t\t\t\"additionalInfo\": null,\n" +
                    "\t\t\t\t\t\"selected\": true\n" +
                    "\t\t\t\t}\n" +
                    "\t\t\t],\n" +
                    "\t\t\t\"sendType\": [\n" +
                    "\t\t\t\t{\n" +
                    "\t\t\t\t\t\"name\": \"邮件\",\n" +
                    "\t\t\t\t\t\"code\": \"3\",\n" +
                    "\t\t\t\t\t\"additionalInfo\": null,\n" +
                    "\t\t\t\t\t\"selected\": false\n" +
                    "\t\t\t\t},\n" +
                    "\t\t\t\t{\n" +
                    "\t\t\t\t\t\"name\": \"短信\",\n" +
                    "\t\t\t\t\t\"code\": \"1\",\n" +
                    "\t\t\t\t\t\"additionalInfo\": null,\n" +
                    "\t\t\t\t\t\"selected\": true\n" +
                    "\t\t\t\t}\n" +
                    "\t\t\t],\n" +
                    "\t\t\t\"tags\": [\n" +
                    "\t\t\t\t{\n" +
                    "\t\t\t\t\t\"name\": \"国内机票\",\n" +
                    "\t\t\t\t\t\"code\": \"0000000001\",\n" +
                    "\t\t\t\t\t\"additionalInfo\": null,\n" +
                    "\t\t\t\t\t\"selected\": false\n" +
                    "\t\t\t\t},\n" +
                    "\t\t\t\t{\n" +
                    "\t\t\t\t\t\"name\": \"技术支持申报平台\",\n" +
                    "\t\t\t\t\t\"code\": \"0000000002\",\n" +
                    "\t\t\t\t\t\"additionalInfo\": null,\n" +
                    "\t\t\t\t\t\"selected\": false\n" +
                    "\t\t\t\t},\n" +
                    "\t\t\t\t{\n" +
                    "\t\t\t\t\t\"name\": \"回电通知\",\n" +
                    "\t\t\t\t\t\"code\": \"0000000003\",\n" +
                    "\t\t\t\t\t\"additionalInfo\": null,\n" +
                    "\t\t\t\t\t\"selected\": false\n" +
                    "\t\t\t\t},\n" +
                    "\t\t\t\t{\n" +
                    "\t\t\t\t\t\"name\": \"Q消息\",\n" +
                    "\t\t\t\t\t\"code\": \"0000000004\",\n" +
                    "\t\t\t\t\t\"additionalInfo\": null,\n" +
                    "\t\t\t\t\t\"selected\": false\n" +
                    "\t\t\t\t},\n" +
                    "\t\t\t\t{\n" +
                    "\t\t\t\t\t\"name\": \"酒店订单同步异常\",\n" +
                    "\t\t\t\t\t\"code\": \"0000001001\",\n" +
                    "\t\t\t\t\t\"additionalInfo\": null,\n" +
                    "\t\t\t\t\t\"selected\": false\n" +
                    "\t\t\t\t}\n" +
                    "\t\t\t],\n" +
                    "\t\t\t\"sendtimeRule\": null,\n" +
                    "\t\t\t\"ranges\": [],\n" +
                    "\t\t\t\"tmpTags\": []\n" +
                    "\t\t}\n" +
                    "\t],\n" +
                    "\t\"companyCode\": \"#company_code\",\n" +
                    "\t\"source\": \"28\",\n" +
                    "\t\"infoTypeCate\": 2,\n" +
                    "\t\"ruleType\": 3,\n" +
                    "\t\"rangeValue\": 5\n" +
                    "}";
            json = json.replace("#company_code",map.get("company_code").toString());
            Map<String, Object> params = new HashMap<>();
            params = JSONObject.parseObject(json);
            res = httpUtil.httpRestRequest(params, url, hearder, String.class);
            System.out.print(res);
        }
    }

    @Test
    public void testvalidExcel() throws Exception {
        String filePath = "F:\\log\\利润明细表2024-09-29_(2023-01-01_2024-09-29).xlsx";
        List<Map<String,Object>> excelData1 = ExcelUtils.readExcel(filePath,"Sheet-2",0);
        List<Map<String,Object>> excelData2 = ExcelUtils.readExcel(filePath,"Sheet-4",0);
        Map<String,String> ordernoMap = new HashMap<>();
        for(int i=0;i<excelData2.size();i++)
        {
            ordernoMap.put(excelData2.get(i).get("order_no").toString(),"");
        }
        System.out.println(excelData2.size());
        for(int i=0;i<excelData1.size();i++)
        {
           if(!ordernoMap.containsKey(excelData1.get(i).get("order_no").toString()))
           {
               System.out.println(excelData1.get(i).get("order_no").toString());
           }
        }
    }
    @Test
    public void testvalidExcelAmount() throws Exception {
        String filePath = "F:\\log\\利润明细表2024-11-22_(2023-01-01_2024-11-22).xlsx";
        String filePath2 = "F:\\log\\Query(97).xlsx";
        List<Map<String,Object>> excelData1 = ExcelUtils.readExcel(filePath,"sheet-1",0);
        List<Map<String,Object>> excelData2 = ExcelUtils.readExcel(filePath2,"sheet-1",0);
        Map<String,String> ordernoMap = new HashMap<>();
        for(int i=0;i<excelData2.size();i++)
        {

            ordernoMap.put(excelData2.get(i).get("order_no").toString(),excelData2.get(i).get("cny_tmc_total_cost").toString());
        }
        for(int i=0;i<excelData1.size();i++)
        {
            String orderno=excelData1.get(i).get("订单编号").toString();
            if(excelData1.get(i).get("应付")==null || StringUtils.isBlank(excelData1.get(i).get("应付").toString()))
            {
                System.out.println(orderno+":应付为空");
                continue;
            }
            double amount = Double.parseDouble(excelData1.get(i).get("应付").toString());
            if(!ordernoMap.containsKey(orderno)){
                System.out.println(orderno+":发票系统不存在");
                continue;
            }
            double amount1 = Double.parseDouble(ordernoMap.get(orderno));

            if(amount != amount1)
            {
                System.out.println(orderno+":应付="+amount+"||cny_tmc_total_cost="+amount1);
            }
        }
    }
    @Test
    public void mergeExcel() throws Exception {
        String filePath = "F:\\log\\利润明细表2024-09-30_(2024-09-29_2024-09-29).xlsx";
        String mergefilePath = "F:\\log\\利润明细表(mergefilePath).xlsx";
        List<String> filePaths = Arrays.asList(
                //"F:\\log\\利润明细表2024-05-141.xlsx"
                "F:\\log\\利润明细表2024-09-30_(2024-09-29_2024-09-29).xlsx"
                //,"F:\log\欠付明细表按月(20160101-20240331)-(20240301-20240307).xlsx"
                //,"F:\log\欠付明细表按月(20160101-20240331)-(20240308-20240319).xlsx"
        );
        ExcelWriter excelWriter = EasyExcel.write(mergefilePath).build();

        for(String path : filePaths) {
            List<Integer> ignoreRows = new ArrayList<>();
            ignoreRows.add(1);
            FileInputStream fileInputStream = new FileInputStream(new File(path));
            EasyExcel.read(fileInputStream, new MergeExcelDataWithStyleListener(mergefilePath, "sheet-1", ignoreRows, excelWriter)).sheet().doRead();
            fileInputStream.close();

        }
        excelWriter.finish();
    }

    @Test
    public void readExcel() throws Exception {
        String filePath = "F:\\log\\利润明细表2024-11-22.xlsx";
        String mergefilePath = "F:\\log\\利润明细表2024-11-22tttt.xlsx";
        List<String> filePaths = Arrays.asList(
                //"F:\\log\\利润明细表2024-05-141.xlsx"
                "F:\\log\\利润明细表2024-11-22.xlsx"
                //,"F:\log\欠付明细表按月(20160101-20240331)-(20240301-20240307).xlsx"
                //,"F:\log\欠付明细表按月(20160101-20240331)-(20240308-20240319).xlsx"
        );
        ExcelWriter excelWriter = EasyExcel.write(mergefilePath).build();

        for(String path : filePaths) {
            List<Integer> ignoreRows = new ArrayList<>();
            ignoreRows.add(1);
            FileInputStream fileInputStream = new FileInputStream(new File(path));
            EasyExcel.read(fileInputStream, new MergeExcelDataWithStyleListener(mergefilePath, "sheet-1", ignoreRows, excelWriter)).sheet().doRead();
            fileInputStream.close();

        }
        excelWriter.finish();
    }
    @Test
    public void testSuijuGongju() throws Exception {
        String uploadUrl = "http://192.168.90.190:8080/file/apis/v1/file/uploadFile/report";
        String fromFilePath="F:\\log\\7843024343571-票价+燃油费.pdf";

        String outFilePath="F:\\log\\7843024343571-票价+燃油费.xml";
        VoucherFileInfo res =VoucherFileUtil.extractXBRLFromPDF(fromFilePath,outFilePath);
        /*InputStream fileInputStream = xmlUtils.ofdToXml(fromFilePath);
        String uploadres = pdfUtils.inputStreamUpload(uploadUrl, "A测试财政部工具转xml.xml", fileInputStream);*/
       // fileInputStream.close();
        System.out.println(res);


    }

    @Test
    public void testBigDataExcelExport() throws Exception {
        System.setProperty("java.io.tmpdir","F:\\log");
        //SXSSFWorkbook wb1 = new SXSSFWorkbook(1000);
        SXSSFWorkbook wb = new SXSSFWorkbook(-1);
        Font font = wb.createFont();
        font.setColor(Font.COLOR_NORMAL);
        font.setFontName(" 黑体  ");
        font.setBold(true);
        CellStyle cellStyle = wb.createCellStyle();
        cellStyle.setFont(font);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        SXSSFSheet currentSheet = wb.createSheet("Sheet1");
        for(int i=0;i<200000;i++)
        {
            Row row = currentSheet.createRow(i);
            for(int j =0;j<100;j++)
            {
                Cell cell= row.createCell((short) j);
                cell.setCellStyle(cellStyle);
                cell.setCellValue("我国第四批预备航天员已于今年8月入队参加训练，不仅要执行空间站任务，未来也将执行载人登月任务。针对执行空间站任务和未来执行载人登月任务的新特点，在训练内容设置上，既注重失重状态下生活工作与健康维护等基本技能，以及掌握出舱活动、设备维护维修、空间科学实/试验等技能，更面向未来载人登月任务，进一步培塑航天员从操控飞行器到驾驶月球车、从天体辨识到地质科考、从太空失重漂浮到月面负重行走的能力。:"+i+"-"+j);
            }
            if(i%100==0)
            {
                currentSheet.flushRows();
            }
        }
        String tempexcelFilePath = "F:\\log\\bigdata1.xlsx";
        FileOutputStream fos = new FileOutputStream(tempexcelFilePath);
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        wb.write(bos);

        wb.dispose();
        bos.close();
        fos.close();

        String ZIP_FILE = "F:\\log\\bigdata1.zip";
        File zipFile = new File(ZIP_FILE);
        try (ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(zipFile));
             BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(zipOut)) {
            //开始时间
            long beginTime = System.currentTimeMillis();

                try (BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(tempexcelFilePath))) {
                    zipOut.putNextEntry(new ZipEntry("bigdata1.xlsx"));
                    int temp = 0;
                    while ((temp = bufferedInputStream.read()) != -1) {
                        bufferedOutputStream.write(temp);
                    }
                }


            Date date = new Date();
            System.out.println("zip耗时：" + (date.getTime() - beginTime));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void compreeenFile() throws IOException {
        List<String> filePath = Arrays.asList("F:\\log\\20241104152335152-878748-利润明细表2024-11-04.xlsx","F:\\log\\行程单火车票统计 (1).xlsx");
       // List<String> filePath = new {};

        String ZIP_FILE = "F:\\log\\bigdata1-1104.zip";
        File zipFile = new File(ZIP_FILE);
       ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(zipFile));
             BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(zipOut);
            //开始时间
            long beginTime = System.currentTimeMillis();
            for (int i=0;i<filePath.size();i++) {
                try (BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(filePath.get(i)))) {
                    zipOut.putNextEntry(new ZipEntry("bigdata" + i + ".xlsx"));
                    int temp = 0;
                    while ((temp = bufferedInputStream.read()) != -1) {
                        bufferedOutputStream.write(temp);
                    }
                    // bufferedOutputStream.close();
                }
                zipOut.closeEntry();
            }


        //zipOut.close();
            Date date = new Date();
            System.out.println("zip耗时：" + (date.getTime() - beginTime));
        zipOut.finish();

    }

    //https://tmc.shinetour.com/file/apis/v1/file/downloadFile?path=/invoicePzg/20241114/20241114073127062-9992098013745-24118999211006411679.ofd&fileName=9992098013745-24118999211006411679.ofd
    @Test
    public void testCompressFileByRemoteUrl() throws Exception {
        Map<String,String> files = new HashMap<>();
        for(int i=0;i<10;i++)
        {
            files.put(""+i+"/test-ima-"+i+".ofd", "https://tmc.shinetour.com/file/apis/v1/file/downloadFile?path=/invoicePzg/20241114/20241114073127062-9992098013745-24118999211006411679.ofd&fileName=9992098013745-24118999211006411679.ofd");

            // List<String> filePath = new {};
        }
        String ZIP_FILE = "F:\\log\\TestBigFileCompress-20241114.zip";
        File zipFile = new File(ZIP_FILE);
        try(ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(zipFile));
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(zipOut)) {

            //开始时间
            long beginTime = System.currentTimeMillis();
            for (String key : files.keySet()) {
                InputStream inputStream = pdfUtils.getInputStreamFromRemoteUrl(files.get(key));
                zipOut.putNextEntry(new ZipEntry(key));
                byte[] buff = InputStreamtoByteArray(inputStream);
                bufferedOutputStream.write(buff);

                inputStream.close();
            }


            //zipOut.close();
            Date date = new Date();
            System.out.println("zip耗时：" + (date.getTime() - beginTime));
            //zipOut.finish();
        }
    }

    public  byte[] InputStreamtoByteArray(InputStream input) throws IOException {
        try (InputStream in = input) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            ByteArrayOutputStream output = new ByteArrayOutputStream();

            while ((bytesRead = in.read(buffer)) != -1) {
                output.write(buffer, 0, bytesRead);
            }

            return output.toByteArray();
        }
    }

    @Test
    public void compreeenFileByChannel() {
        List<String> filePath = Arrays.asList("F:\\log\\商旅系统：2024年03月欠付明细表按月(20160101-20240331).xlsx"
                , "F:\\log\\bigdata.xlsx", "F:\\log\\bigdata1.xlsx","F:\\log\\server-rpc-tmcjurisdiction-1.0.0-SNAPSHOT.jar");
        // List<String> filePath = new {};

        String ZIP_FILE = "F:\\log\\bigdataChannel.zip";
        File zipFile = new File(ZIP_FILE);
        try (ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(zipFile));
             WritableByteChannel writableByteChannel = Channels.newChannel(zipOut)) {
            //开始时间
            long beginTime = System.currentTimeMillis();
            for (int i=0;i<filePath.size();i++) {
                try (FileChannel fileChannel = new FileInputStream(filePath.get(i)).getChannel()) {
                    zipOut.putNextEntry(new ZipEntry("bigdata"+i+".xlsx"));
                    fileChannel.transferTo(0, fileChannel.size(), writableByteChannel);
                }
            }

            Date date = new Date();
            System.out.println("zip耗时：" + (date.getTime() - beginTime));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGzipCompressor() throws Exception {
        String sourceFilePath = "F:\\log\\bigdata1.xlsx";
        String compressedFilePath = "F:\\log\\bigdata1.gz";
        try (FileInputStream fis = new FileInputStream(sourceFilePath);
             FileOutputStream fos = new FileOutputStream(compressedFilePath);
             GZIPOutputStream gzos = new GZIPOutputStream(fos)) {

            byte[] buffer = new byte[1024 * 1024]; // 1MB 缓冲区
            int len;

            while ((len = fis.read(buffer)) != -1) {
                gzos.write(buffer, 0, len);
            }

            System.out.println("文件压缩完成");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void uploadFile4Slice() throws IOException {
        File tempfile = new File("F:\\log\\bigdataChannel.zip");

        String uploadUrl = "http://192.168.90.190:8080/file/apis/v1/file/uploadFile4Slice/testSlice";
        long fileSize = tempfile.length();
        System.out.println(fileSize);
        InputStream inputStream = new FileInputStream(tempfile);

        int maxUploadByteSize = 1024 * 1024 * 50;//50M
        int bufferSize = 2048; // or any other size
        int readCount =0;
        int shardIndex =0;
        int totalShardSize = (int) (fileSize /  maxUploadByteSize);
        if(maxUploadByteSize*totalShardSize<=fileSize)
        {
            totalShardSize++;
        }
        byte[] buffer = new byte[bufferSize];
        int bytesRead = 0;
        String fileName ="bigdataChannel.zip-"+DateUtil.getCurrentTime(DateUtil.PATTERN_STANDARD17W);
        BufferedInputStream bis = new BufferedInputStream(inputStream);

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        while ((bytesRead = bis.read(buffer)) != -1) {
            // bytesRead contains the number of bytes read into the buffer
            // Do something with the buffer
            bos.write(buffer);
            readCount ++;
            if(readCount>=maxUploadByteSize/bufferSize)
            {
                InputStream uploadInputStream = new ByteArrayInputStream(bos.toByteArray()) ;
                String res = pdfUtils.inputStreamUpload4Slice(uploadUrl, "bigdataChannel.zip",MD5(fileName) ,shardIndex,totalShardSize,uploadInputStream);
                System.out.print(res);
                bos.flush();
                bos.close();
                bos = new ByteArrayOutputStream();
                readCount =0;
                shardIndex++;
            }
        }
        if( totalShardSize>shardIndex) {
            InputStream uploadInputStream = new ByteArrayInputStream(bos.toByteArray());
            String res = pdfUtils.inputStreamUpload4Slice(uploadUrl, "bigdataChannel.zip", MD5(fileName), shardIndex, totalShardSize, uploadInputStream);
            System.out.print(res);
            bos.flush();
            bos.close();
        }

        inputStream.close();

    }

    @Test
    public void testMergePdf() throws Exception {
        String oriFIle = "https://tmc.shinetour.com/file/apis/v1/file/downloadFile?path=/invoicePzg/20241114/20241114114055706-%E5%B9%BF%E5%B7%9E%E5%B8%82%E7%99%BE%E6%9E%9C%E5%9B%AD%E4%BF%A1%E6%81%AF%E6%8A%80%E6%9C%AF%E6%9C%89%E9%99%90%E5%85%AC%E5%8F%B8_226855.49_24442000000546279953_%E5%B9%BF%E5%B7%9E%E5%B8%82%E7%99%BE%E6%9E%9C%E5%9B%AD%E4%BF%A1%E6%81%AF%E6%8A%80%E6%9C%AF%E6%9C%89%E9%99%90%E5%85%AC%E5%8F%B8.pdf&fileName=%E5%B9%BF%E5%B7%9E%E5%B8%82%E7%99%BE%E6%9E%9C%E5%9B%AD%E4%BF%A1%E6%81%AF%E6%8A%80%E6%9C%AF%E6%9C%89%E9%99%90%E5%85%AC%E5%8F%B8_226855.49_24442000000546279953_%E5%B9%BF%E5%B7%9E%E5%B8%82%E7%99%BE%E6%9E%9C%E5%9B%AD%E4%BF%A1%E6%81%AF%E6%8A%80%E6%9C%AF%E6%9C%89%E9%99%90%E5%85%AC%E5%8F%B8.pdf";
        String mergeFile = "F:\\log\\testMergeFile.pdf";

Date  beginTime = new Date();

        PDFMergerUtility mergePdf = new PDFMergerUtility();
        PDDocument sourceDoc = PDDocument.load(new File(mergeFile));
       // mergePdf.setDestinationFileName(mergeFile);

            for(int i=0;i<100;i++)
            {

                InputStream inputStream = pdfUtils.getInputStreamFromRemoteUrl(oriFIle);
                PDDocument targetDoc = PDDocument.load(new ByteArrayInputStream( InputStreamtoByteArray(inputStream)));
               // mergePdf.addSource(new ByteArrayInputStream( InputStreamtoByteArray(inputStream)));
                inputStream.close();
                mergePdf.appendDocument(sourceDoc,targetDoc);
               // if(i%100==0 && i>0)
                {
                    System.out.println(DateUtil.getCurrentTime(DateUtil.PATTERN_STANDARD19H)+"写入磁盘："+i);

                }

            }
        Date  date = new Date();
        System.out.println("zip耗时：" + (date.getTime() - beginTime.getTime()));
        System.out.println("文件添加完成");
        //mergePdf.mergeDocuments(MemoryUsageSetting.setupTempFileOnly());
        System.out.println("文件合并完成");
    }

    @Test
    public void testGetFileIputStreamByRemoteUrl() throws Exception
    {
        String url ="https://dppt.guangdong.chinatax.gov.cn:8443/kpfw/fpjfzz/v1/exportDzfpwjEwm?Wjgs=pdf&Jym=A076&Fphm=24442000000552501573&Kprq=20241117210715&Czsj=1731848835900";

        for(int i=0;i<100;i++)
        {

            try {
                InputStream inputStream = pdfUtils.getInputStreamFromRemoteUrl(url);

                File file = new File("F:\\log\\" + i + ".pdf");
                FileOutputStream fos = new FileOutputStream(file);
                fos.write(InputStreamtoByteArray(inputStream));
                System.out.println(DateUtil.getCurrentTime(DateUtil.PATTERN_STANDARD19H) + "写入磁盘：" + i);
            }catch (Exception e){
                System.out.println(DateUtil.getCurrentTime(DateUtil.PATTERN_STANDARD19H) + "写入磁盘异常：" + e.getMessage());
            }



        }
        Date  date = new Date();
       // System.out.println("zip耗时：" + (date.getTime() - beginTime.getTime()));
        System.out.println("文件添加完成");
    }

    @Test
    public void testPostMeiyaInterface4Sign()
    {

    }

    @Test
    public void generateSql() throws Exception {
        String filePath = "F:\\log\\Query (1)(31).xlsx";
        String filePath2 = "F:\\log\\vo旅客编号更新.txt";
        List<Map<String,Object>> excelData1 = ExcelUtils.readExcel(filePath,"Query",0);
        List<Map<String,Object>> excelData2 = ExcelUtils.readExcel(filePath,"Query1",0);

        Map<String,String> ordernoMap = new HashMap<>();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath2))) {
            for(int i=0;i<excelData1.size();i++)
            {
                String OrderNo = excelData1.get(i).get("orderno")==null ?"": excelData1.get(i).get("orderno").toString();
                String passengerName = excelData1.get(i).get("passengerName")==null ?"": excelData1.get(i).get("passengerName").toString();
                String newPsngrId = excelData1.get(i).get("PassengerNo")==null ?"": excelData1.get(i).get("PassengerNo").toString();

                String line = "update st_payment.bl_purchase_pasenger  set psngr_id = '"+newPsngrId+"'  where business_order_id= '"+OrderNo+"'"+" and psngr_name= '"+passengerName+"';";
                writer.write(line);
                writer.newLine(); // 写入换行符
                String line2 = "update st_payment.bl_purchase_record set version_number =now() where business_order_id = '"+OrderNo+"';";
                writer.write(line2);
                writer.newLine(); // 写入换行符
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void validExcelData() throws Exception {
        String filePath = "F:\\log\\Query (1)(29).xlsx";
        String filePath2 = "F:\\log\\商旅订单的城市数据.txt";
        List<Map<String,Object>> excelData1 = ExcelUtils.readExcel(filePath,"Query",0);

        Map<String,Double> ordernoSum = new HashMap<>();
        Map<String,Double> ordernoReceivableFee = new HashMap<>();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath2))) {
            for(int i=0;i<excelData1.size();i++)
            {
                String OrderNo = excelData1.get(i).get("OrderNo").toString();
                String PsngrId= excelData1.get(i).get("PsngrId").toString();
                if(StringUtils.isBlank(OrderNo))
                    continue;
                double ServiceCharge = Double.parseDouble(excelData1.get(i).get("ServiceCharge").toString());
                double CancellationFee = Double.parseDouble(excelData1.get(i).get("CancellationFee").toString());
                double EndorsementFee = Double.parseDouble(excelData1.get(i).get("EndorsementFee").toString());
                double FaceValuePrice = Double.parseDouble(excelData1.get(i).get("FaceValuePrice").toString());
                double AirportConstructionFee = Double.parseDouble(excelData1.get(i).get("AirportConstructionFee").toString());
                double FuelChargeFee = Double.parseDouble(excelData1.get(i).get("FuelChargeFee").toString());
                double UpgradeFee = Double.parseDouble(excelData1.get(i).get("UpgradeFee").toString());
                double ReceivableFee = Double.parseDouble(excelData1.get(i).get("ReceivableFee").toString());
                double tempsum = ServiceCharge+CancellationFee+EndorsementFee+FaceValuePrice+AirportConstructionFee+FuelChargeFee+UpgradeFee;
                String mapKey= OrderNo+"-"+PsngrId;
                if(ordernoSum.keySet().contains(mapKey))
                {
                    double sum =ordernoSum.get(mapKey);
                    tempsum += sum;
                }
                ordernoSum.put(mapKey ,tempsum);
                ordernoReceivableFee.put(mapKey ,ReceivableFee);
            }
            for (String key : ordernoSum.keySet())
            {
                if(ordernoSum.get(key)-ordernoReceivableFee.get(key) != 0)
                {
                    System.out.println(key+":"+ordernoSum.get(key).toString()+"|"+ordernoReceivableFee.get(key).toString());
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testRemoteUrl() throws Exception {
        String uploadUrl = "http://192.168.90.190:8080/file/apis/v1/file/uploadFile/report";
        String downLoadUrl = "http://192.168.90.190:8080/file/apis/v1/file/downloadFile";
        InputStream fileIs = pdfUtils.getInputStreamFromRemoteUrl("http://tmc.shinetour.com/file/apis/v1/file/downloadFile?path=/invoice/20250313/20250313153137914-%E4%B8%8A%E6%B5%B7%E5%87%BD%E5%A4%8F%E7%A7%91%E6%8A%80%E6%9C%89%E9%99%90%E5%85%AC%E5%8F%B8_25442000000139259092_%E4%BB%A3%E8%AE%A2%E6%9C%BA%E7%A5%A8%E6%AC%BE_26177.00.pdf&fileName=%E4%B8%8A%E6%B5%B7%E5%87%BD%E5%A4%8F%E7%A7%91%E6%8A%80%E6%9C%89%E9%99%90%E5%85%AC%E5%8F%B8_25442000000139259092_%E4%BB%A3%E8%AE%A2%E6%9C%BA%E7%A5%A8%E6%AC%BE_26177.00.pdf");
        ByteArrayOutputStream buffer = inputStreamToByteArrayOutputStream(fileIs);
        if(buffer.size()>0) {
            InputStream byteArrayInputStream = new ByteArrayInputStream(buffer.toByteArray());
            String res = pdfUtils.inputStreamUpload(uploadUrl, "test.pdf", byteArrayInputStream);
            System.out.print(res);
        }
        //fileIs.in
        InputStream fileIs2 = pdfUtils.getInputStreamFromRemoteUrl("http://testtmcsz.shinetour.com/file/apis/v1/file/downloadFile?path=/invoicePzg/20240805/20240805175933113-2220611093871-90202406291449091273.ofd&fileName=2220611093871-90202406291449091273.ofd");
        ByteArrayOutputStream buffer2 = inputStreamToByteArrayOutputStream(fileIs2);
        if(buffer2.size()>0) {
            InputStream byteArrayInputStream = new ByteArrayInputStream(buffer2.toByteArray());
            String res = pdfUtils.inputStreamUpload(uploadUrl, "test2.pdf", byteArrayInputStream);
            System.out.print(res);
        }
    }

    public static ByteArrayOutputStream inputStreamToByteArrayOutputStream(InputStream inputStream) throws IOException {
        if (inputStream == null) {
            return null; // 如果流为 null，视为空流
        }

        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        byte[] data = new byte[1024];
        int bytesRead;
        while ((bytesRead = inputStream.read(data, 0, data.length)) != -1) {
            buffer.write(data, 0, bytesRead);
        }

         buffer.toByteArray();
        return buffer;
    }
    @Test
    public void testSubString()
    {
        String str ="//tmc.shinetour.com/file/apis/v1/file/downloadFile?path=/invoice/20250307/20250307092105653-%E4%B8%AD%E7%B2%AE%E7%A6%8F%E4%B8%B4%E9%97%A8%E9%A3%9F%E5%93%81%E8%90%A5%E9%94%80%E6%9C%89%E9%99%90%E5%85%AC%E5%8F%B8%E6%88%90%E9%83%BD%E5%88%86%E5%85%AC%E5%8F%B8_25442000000125860831_%E6%9C%8D%E5%8A%A1%E8%B4%B9_342.00.pdf&fileName=%E4%B8%AD%E7%B2%AE%E7%A6%8F%E4%B8%B4%E9%97%A8%E9%A3%9F%E5%93%81%E8%90%A5%E9%94%80%E6%9C%89%E9%99%90%E5%85%AC%E5%8F%B8%E6%88%90%E9%83%BD%E5%88%86%E5%85%AC%E5%8F%B8_342.0_25442000000125860831_ZD250001423739_%E4%B8%AD%E7%B2%AE%E7%A6%8F%E4%B8%B4%E9%97%A8%E9%A3%9F%E5%93%81%E8%90%A5%E9%94%80%E6%9C%89%E9%99%90%E5%85%AC%E5%8F%B8%E6%88%90%E9%83%BD%E5%88%86%E5%85%AC%E5%8F%B8.pdf";
        String subStr = str.substring(0,str.lastIndexOf("&fileName="))+"&fileName=ddddddd.pdf" ;
        System.out.print(DateFormatUtils.format(new Date(), "yyyy-MM-dd"));

        Date d = new Date(Long.parseLong("1742296717913"));
       System.out.println(d);
    }
}
