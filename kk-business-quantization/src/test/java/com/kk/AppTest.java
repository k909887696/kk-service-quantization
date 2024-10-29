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
        String uploadUrl = "http://192.168.90.190:8080/file/apis/v1/file/uploadFile/report";

        InputStream inputStream = new FileInputStream(tempfile);
        //pdfFile2.deleteOnExit();
        tempfile.deleteOnExit();
        String res = pdfUtils.inputStreamUpload(uploadUrl, "converterToPdf.ofd", inputStream);
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
    //

    @Test
    public void testMYinterfaceWithSign() {
        Date n = new Date();
        long timeStamp = n.getTime();
        String apiKey = "AYATDI2B3XfV@Q%I7hJGSGS!SFU0Ew$7";
        String json = "{\"nodeCode\":\"P1394118\",\"nodeName\":\"林11\",\"parentCode\":\"419054\",\"typeId\":null,\"status\":null,\"saleType\":null,\"opId\":\"飞书接口同步\",\"opDate\":\"2024-09-04 09:53:56\",\"groupId\":null,\"companyId\":null} ";
        Map<String, Object> params = JSONObject.parseObject(json);
        String sendJson = JsonUtil.getJSONString(params);
        Map<String, Object> hearder = new HashMap<>();
        String res = "";
        String url = "https://testtmcsz.shinetour.com/crmjurisdiction/apiss/v1/netservice/update_node";
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
        String filePath = "F:\\log\\Query (1)(16).xlsx";
        List<Map<String,Object>> excelData1 = ExcelUtils.readExcel(filePath,"Query",0);
        List<Map<String,Object>> excelData2 = ExcelUtils.readExcel(filePath,"Sheet-4",0);
        Map<String,String> ordernoMap = new HashMap<>();

        for(int i=0;i<excelData1.size();i++)
        {
            double amount = Double.parseDouble(excelData1.get(i).get("claimed_amount").toString());
            if(excelData1.get(i).get("claimed_amount3")==null)
            {
                System.out.println(excelData1.get(i).get("order_no").toString()+":"+excelData1.get(i).get("claimed_amount").toString()+"|"+excelData1.get(i).get("claimed_amount3"));
                continue;
            }
            double amount3 = Double.parseDouble(excelData1.get(i).get("claimed_amount3").toString());
            if(amount != amount3)
            {
                System.out.println(excelData1.get(i).get("order_no").toString()+":"+excelData1.get(i).get("claimed_amount").toString()+"||"+excelData1.get(i).get("claimed_amount3"));
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
    public void testSuijuGongju() throws Exception {
        String uploadUrl = "http://192.168.90.190:8080/file/apis/v1/file/uploadFile/report";
        String fromFilePath="F:\\log\\8803430284748-24468880211003276540.ofd";

        String outFilePath="F:\\log\\A测试财政部工具转xmltt.xml";
        InputStream fileInputStream = xmlUtils.ofdToXml(fromFilePath);
        String uploadres = pdfUtils.inputStreamUpload(uploadUrl, "A测试财政部工具转xml.xml", fileInputStream);
        fileInputStream.close();
        System.out.println(uploadres);

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
    public void compreeenFile() {
        List<String> filePath = Arrays.asList("F:\\log\\商旅系统：2024年03月欠付明细表按月(20160101-20240331).xlsx"
                , "F:\\log\\bigdata.xlsx", "F:\\log\\bigdata1.xlsx","F:\\log\\server-rpc-tmcjurisdiction-1.0.0-SNAPSHOT.jar");
       // List<String> filePath = new {};

        String ZIP_FILE = "F:\\log\\bigdata1.zip";
        File zipFile = new File(ZIP_FILE);
        try (ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(zipFile));
             BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(zipOut)) {
            //开始时间
            long beginTime = System.currentTimeMillis();
            for (int i=0;i<filePath.size();i++) {
                try (BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(filePath.get(i)))) {
                    zipOut.putNextEntry(new ZipEntry("bigdata"+i+".xlsx"));
                    int temp = 0;
                    while ((temp = bufferedInputStream.read()) != -1) {
                        bufferedOutputStream.write(temp);
                    }
                }
            }

            Date date = new Date();
            System.out.println("zip耗时：" + (date.getTime() - beginTime));
        } catch (Exception e) {
            e.printStackTrace();
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
}
