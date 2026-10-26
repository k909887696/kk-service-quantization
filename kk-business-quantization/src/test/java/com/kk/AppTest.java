package com.kk;

import static org.junit.Assert.assertTrue;



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
import com.kk.business.quantization.utils.RegUtil;
import com.kk.business.quantization.utils.meiya.SHAUtil;
import com.kk.business.quantization.utils.readdatalistenter.MergeExcelDataListener;
import com.kk.business.quantization.utils.readdatalistenter.MergeExcelDataWithStyleListener;
import com.kk.common.utils.*;
import com.kk.common.utils.DateUtil;
import com.kk.common.utils.ExcelUtils;

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
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
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
    public void testRandomPassword()
    {
        for(int i=0;i<10;i++) {
           String password =  PasswordGeneratorUtils.generatePassword(10);
           System.out.println(password);
        }
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
    public void testrepeat_push_bkcapitalflow_2_orderprocess() {
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
    public void testmodify_blpayapplication_status() {
        String sid = "NET:90AC1CA90DF646E39F31C4CD44B5C3AETMC_69A8EE52A647F3B34C6E77141FA97864";
        String json = "{\"status\":5,\"payApplicationId\":\"PR2500968718\",\"approvalResult\":\"重复核销取消pr单\"}";
        Map<String, Object> params = JSONObject.parseObject(json);
        Map<String, Object> hearder = new HashMap<>();
        String res = "";
        String url = "https://pay.shinetour.com/paycenter/apis/v1/payApplication/modify_blpayapplication_status";
        hearder.put("sid", sid);
        hearder.put("source", "tmc");
        res = httpUtil.httpRestRequest(params, url, hearder, String.class);
        System.out.print(res);
    }

    @Test
    public void testMYinterfaceGetPassenger() {
        String sid = "NET:AD1C2BE8E0BB45D8B0232E0A5CC200ACTMC_E49AC29D90E160A8F2408457A940F611";
        String json = "{\"pageSize\":20,\"pjob_state\":\"0\",\"accountStatus\":-1,\"accountType\":\"MobileAcount\",\"psngrId\":\"${code}\",\"pageNum\":1,\"pageIndex\":1}";
        Map<String, Object> params = JSONObject.parseObject(json);
        Map<String, Object> hearder = new HashMap<>();
        String res = "";
        String url = "http://tmc.shinetour.com/crm//apixs/v1/PassengerWebService/GetPsngrLoginByCondition";
        hearder.put("sid", sid);
        hearder.put("source", "tmc");
        List<String> codes = Arrays.asList(
                 "P2731269"
                ,"P3706688"
                ,"P3753799"
                ,"P3823058"
                ,"P3957356"
                ,"P4003994"
                ,"P4040823"
                ,"P4100222"
                ,"P4101470"
                ,"P4108213"
                ,"P4115098"
                ,"P4169983"
                ,"P4189619"
                ,"P4236613"
                ,"P4386117"
                ,"P4399062"
                ,"P4472782"
                ,"P4511120"
                ,"P4614097"
                ,"P4648313"
                ,"P4660349"
                ,"P4760143"
                ,"P4760144"
                ,"P4769787"
                ,"P4796508"
                ,"P4796509"
                ,"P4831632"
                ,"P4964836"
                ,"P4983062"
                ,"P4983431"
                ,"P4984208"
                ,"P4997749"
                ,"P5012430"
                ,"P5082255"
                ,"P5571062"
                ,"P5577511"
                ,"P5578384"
                ,"P5604596"
                ,"P5639691"
                ,"P5645971"
                ,"P5677181"
                ,"P5726897"
                ,"P5740418"
                ,"P5745024"
                ,"P5745815"
                ,"P5759925"
                ,"P5772029"
                ,"P5784083"
                ,"P5790317"
                ,"P5808462"
                ,"P5854314"
                ,"P5854340"
                ,"P5860356"
                ,"P5869024"
                ,"P5871328"
                ,"P5877335"
                ,"P5929433"
                ,"P6030023"
                ,"P6030406"
                ,"P6113934"
                ,"P6137882"
                ,"P6189333"
                ,"P6228772"
                ,"P6274918"
                ,"P6291730"
                ,"P6309938"
                ,"P6345395"
                ,"P6374357"
                ,"P6385067"
                ,"P8040548"
                ,"P8056279"
                ,"P8068709"
                ,"P8075940"
                ,"P8086065"
                ,"P8107047"
                ,"P8176725"
                ,"P8211604"
                ,"P8213527"
                ,"P8224620"
                ,"P8255212"
                ,"P8260654"
                ,"P8279808"
                ,"P8288058"
                ,"P8293085"
                ,"P8299628"
                ,"P8305458"
                ,"P8306353"
                ,"P8308534"
                ,"P8317347"
                ,"P8320174"
                ,"P8325604"
                ,"P8334801"
                ,"P8338736"
                ,"P8360648"
                ,"P8361541"
                ,"P8362946"
                ,"P8384616"
                ,"P8408765"
                ,"P8411802"
                ,"P8419189"
                ,"P8436869"
                ,"P8447947"
                ,"P8457706"
                ,"P8461148"
                ,"P8472700"
                ,"P8499446"
                ,"P8502865"
                ,"P8504118"



        );
        for (String code : codes)
        {
            params.put("psngrId", code);
            res = httpUtil.httpRestRequest(params, url, hearder, String.class);
            System.out.print(res);
        }

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
    public void testcreditUnFreeze() {
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
        String json = "{\"objectId\":\"RB2600264359\",\"payStatus\":4,\"closeType\":\"3\",\"remark\":{\"remark\":\"订单取消\"}} ";
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

        BigDecimal serviceAmount = new BigDecimal(10036900).divide(new BigDecimal(100));
        System.out.println(serviceAmount.toString());
    }
    @Test
    public void testRsaUtil()
    {

        String str="DzRnn/cSau7yZ8IAyB2sA04yh2nZL5TPRv6v0RTQMLXUi9O3IGBX1JCGMZg3UGjjzSse1I4suIUac+cyNbs1QrnCeOlbOFKBDont58xCQzlTo6PU84A5KYFqzegkIJVX9PG6bmTha8XVqZ2rdxyo5DvOhogmAWWGOS33U/cg398=";
        String publicKey="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCdjBsU4rYO9XNSYbnDRomLnevbW8hO7AGlbgJOOvNDHwxJgKvpAXeu3yeJSOpRcVhZTtRjWazi2O9NrDdcvc++bDDViM8VM4lbtwNXN9KbuDjaJ6Hu4bG0HHxL9243DS8kBjs8h2dIUE9PNG3QmidfzskAHCF0OUq7HyF8/ed5RwIDAQAB";
        String privateKey="MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAJ2MGxTitg71c1JhucNGiYud69tbyE7sAaVuAk4680MfDEmAq+kBd67fJ4lI6lFxWFlO1GNZrOLY702sN1y9z75sMNWIzxUziVu3A1c30pu4ONonoe7hsbQcfEv3bjcNLyQGOzyHZ0hQT080bdCaJ1/OyQAcIXQ5SrsfIXz953lHAgMBAAECgYEAh0x9LXCNXUdZZFRczeDT2r0igl3OJkj0GPfirwBdTTVqvezstcv21oz6TSH7aTnn37qdFz06Bg67ZH8ThGfV8Z/JrziSy0yWFqtCohqG10vBK/sQxOkNlg1YfLPficn35EY3HURpw3Aeme81npnrIdKNUxlv/JFcFcmIM+7560ECQQDL0iYOihQlCtX95g2fJ6YTUEWi4gIxwx7pTyEwWUloANIDH5E+9HW/Dw+DQ7xE1O89ySj+cmxYcgPB/BfFQYcxAkEAxeFQgNZpcrNzzgzFKAF0f/HxNRC02bG6RnWwRB6b7b/hkCRzOFU9VweZ6twgsG4filpyzvZHYPBoqsMarw5Z9wJBAIjX+kcuu6G3Kb+DEsg1lZJIBcj7Vx/T8lAQmabde6SBUH9aVWlgNiHl2bN26dOI52ujQIu9hTLIse8u80Kz4TECQFIYtsOdMUaLOUKFfc6pO+JRSJeRhNbEq9nLvgOlB2BOOZEJ7luy0a4n0y3jghRwu9k0yWkgOGD5nRMruwhh1eUCQDnhH4uKOmw2hkciBDagp5TutEDnHEwwWkE5mv8r7i4+W/BI4Mo1wrnMZP19xZauhcJQUw0zf63/dkKdshhdWvI=";
        String str2 = RSAUtils.decrypt(str, privateKey);
        System.out.println(str2);
        String res = RSAUtils.encrypted(str2, publicKey);
        System.out.println(res);

    }
    @Test
    public void testCheckReport()
    {
        String sid ="";
        List<String> clientIds = new ArrayList<>();
        String startDate ="2025-01-01";
        String endDate ="2025-12-01";
        //核对客户欠收余额
        //获取利润明细表 本期应收
        String profitDetailURL="https://tmc.shinetour.com/report//apis/v1/profit_detail/search";

    }
    @Test
    public void testGroupToken()
    {
        Map<String, String> headers = new HashMap<>();
        String secretKey = "TCSKlICFRWAdHAAPlcxfJ68NndTEkMVNmbi2it6";//"TCSKlICFRWAdHAAPlcxfJ68NndTEkMVNmbi2it6";//金蝶测试秘钥 RvZNIZMzsc3M9zfUD6OPjCCvG2i9jEcO6WScUBp
        long timestamp = System.currentTimeMillis();
        String token = GroupTokenUtils.groupTokenGet(secretKey, timestamp);
        headers.put("WebApiBaseGroupApiTimestamp", String.valueOf(timestamp));
        headers.put("WebApiBaseGroupApiToken", token);

        System.out.println(headers);
    }

    @Test
    public void validExcelFileData() throws Exception {
        String filePath = "D:\\20260317之后开的火车票订单.xlsx";
        String filePath2 = "D:\\20260317之后开的火车票error.txt";
        List<Map<String,Object>> excelData1 = ExcelUtils.readExcel(filePath,"Query",0);
        List<List<Object>> data = new ArrayList<>();
        data.add(Arrays.asList("order_invoice_id", "order_no", "electronic_order_no","passenger_name","invoice_amount","invoice_title_name","invoice_title_tax_no","local_ofd_file_url","local_file_url","result")); // 自定义表头

        String preData = ExcelUtil.readTxtFileAsString(filePath2);
// 写入无表头Excel

        Map<String,Double> ordernoSum = new HashMap<>();
        Map<String,Double> ordernoReceivableFee = new HashMap<>();
        //try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath2))) {
            for(int i=0;i<excelData1.size();i++)
            {
                String order_invoice_id = excelData1.get(i).get("order_invoice_id").toString();
                String OrderNo = excelData1.get(i).get("order_no").toString();
                String electronic_order_no= excelData1.get(i).get("electronic_order_no").toString();

                String local_ofd_file_url = excelData1.get(i).get("local_ofd_file_url").toString();
                String local_file_url = excelData1.get(i).get("local_file_url").toString();
                String passenger_name =excelData1.get(i).get("passenger_name")==null?"": excelData1.get(i).get("passenger_name").toString();
                String invoice_amount =excelData1.get(i).get("invoice_amount")==null?"": excelData1.get(i).get("invoice_amount").toString();
                String invoice_title_tax_no = excelData1.get(i).get("invoice_title_tax_no").toString();
                String invoice_title_name = excelData1.get(i).get("invoice_title_name").toString();



                String mapKey=order_invoice_id+"-"+ OrderNo+"-"+electronic_order_no;
                if(!preData.contains(OrderNo)){
                    continue;
                }
                String validRespdf = validUrl(local_file_url);
                String validResofd = validUrl(local_ofd_file_url);
                if(StringUtils.isNotBlank(validRespdf)|| StringUtils.isNotBlank(validResofd))
                {
                    String result =mapKey+":pdf文件-"+validRespdf+"|ofd文件-"+validResofd;
                    data.add(Arrays.asList(order_invoice_id, OrderNo, electronic_order_no,passenger_name,invoice_amount,invoice_title_name,invoice_title_tax_no,local_ofd_file_url,local_file_url,result));
                    System.out.println(result);
                }
            }
            ExcelUtil.writeExcelWithoutHead("D:/raw_data.xlsx", "数据", data);


    }

    @Test
    public void testRemoteFileSize()   {
        String pdfFileUrl = "//tmc.shinetour.com/file/apis/v1/file/downloadFile?path=/invoicePzg/20260317/20260317135048389-26359134141000073877.ofd&fileName=26359134141000073877.ofd";
        String size = readNetworkFile(pdfFileUrl);
        //System.out.println(size);
        System.out.println(size.length());
        String ofdurl ="//tmc.shinetour.com/file/apis/v1/file/downloadFile?path=/invoicePzg/20260317/20260317111331799-26369132687000201322.ofd&fileName=26369132687000201322.ofd";
        String size2 = readNetworkFile(ofdurl);
        System.out.println(size2);
        System.out.println(size2.length());
        System.out.println(getRemoteFileSize(pdfFileUrl));
        System.out.println(getRemoteFileSize(ofdurl));
    }


    public String validUrl(String pdfFileUrl) {
        String readText = readNetworkFile(pdfFileUrl);
        if (!StringUtils.isEmpty(readText) && (readText.contains("系统忙") || readText.contains("请先登录系统"))) {

            return ("文件内容：：" + readText + ",文件地址："+pdfFileUrl+";  属于异常文件，需要重新推送");
        }
        return "";
    }

    public long getRemoteFileSize(String fileUrl) {
        if (!fileUrl.contains("http")) {
            fileUrl = "http:" + fileUrl;
        }
        long totalBytes = 0;
        try (InputStream inputStream = new URL(fileUrl).openStream();
             ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            // 先复制到内存流，同时计算大小
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                //baos.write(buffer, 0, bytesRead);
                totalBytes += bytesRead;
            }
            //byte[] contentBytes = baos.toByteArray();
            System.out.println("文件流大小: " + totalBytes + " bytes");
        }  catch (Exception e) {
            e.printStackTrace();
        }
        return totalBytes;
    }

    /**
     * 读取网络文件内容（支持HTTP/HTTPS）
     */
    public String readNetworkFile(String url) {
        if (StringUtils.isEmpty(url)) {
            return null;
        }
        if (!url.contains("http")) {
            url = "http:" + url;
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new URL(url).openStream()))) {
            StringBuilder content = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }

            return content.toString();
        } catch (Exception e) {
            //log.info("pdf");
        }
        return url;
    }

    @Test
    public void testFileName() {

        BigDecimal  tmpTotalAmount=new BigDecimal(70924.60);
        BigDecimal subTotalAmount = tmpTotalAmount.subtract(new BigDecimal(70924.6));
        int t= subTotalAmount.compareTo(BigDecimal.ZERO);
        System.out.println(subTotalAmount.doubleValue());
        System.out.println(t);
    }

    @Test
    public void testHtmlToPdf() throws Exception {
        String html = """
            <html>
                <head>
        
                    <style>
                        body { font-family: SimSun, sans-serif; }
                        h1 { color: #2c3e50; }
                        table { border-collapse: collapse; width: 100%; }
                        td, th { border: 1px solid #ddd; padding: 8px; }
                    </style>
                </head>
                <body>
                    <h1>PDFBox + OpenHTML2PDF 生成 PDF</h1>
                    <table>
                        <tr><th>姓名</th><th>部门</th></tr>
                        <tr><td>张三</td><td>技术部</td></tr>
                        <tr><td>李四</td><td>产品部</td></tr>
                    </table>
                </body>
            </html>
            """;

        pdfUtils.generatePdfFromHtml(html, "D:\\log\\output1.pdf");
    }

    @Test
    public void testDataSdf() throws FileNotFoundException {
        String base64 = "";
                //"JVBERi0xLjcKJeLjz9MKMSAwIG9iago8PC9NZXRhZGF0YSAzIDAgUi9OYW1lcyAzMCAwIFIvUGFnZXMgNCAwIFIvVHlwZSAvQ2F0YWxvZz4+CmVuZG9iagoyIDAgb2JqCjw8L0F1dGhvciAo/v8AQwBIAEUATikvQ3JlYXRpb25EYXRlIChEOjIwMjYwMzEwMTYxMjU1KS9Nb2REYXRlIChEOjIwMjYwMzEwMTYxMjU1KS9PRkQyUERGTGliIChvZmQycGRmbGliLzIuMS4yMy4wODE3LjE0MjcpL1Byb2R1Y2VyICj+/wBTAHUAdwBlAGwAbAAgAE8ARgBEACAAYwBvAG4AdgBlAHIAdABvAHIpPj4KZW5kb2JqCjMgMCBvYmoKPDwvTGVuZ3RoIDYzMy9TdWJ0eXBlIC9YTUwvVHlwZSAvTWV0YWRhdGE+PgpzdHJlYW0NCjw/eHBhY2tldCBiZWdpbj0i77u/IiBpZD0iVzVNME1wQ2VoaUh6cmVTek5UY3prYzlkIj8+PHg6eG1wbWV0YSB4bWxuczp4PSJhZG9iZTpuczptZXRhLyIgeDp4bXB0az0iQWRvYmUgWE1QIENvcmUgNS40LWMwMDUgNzguMTQ3MzI2LCAyMDEyLzA4LzIzLTEzOjAzOjAzIj4KICA8cmRmOlJERiB4bWxuczpyZGY9Imh0dHA6Ly93d3cudzMub3JnLzE5OTkvMDIvMjItcmRmLXN5bnRheC1ucyMiPgogICAgPHJkZjpEZXNjcmlwdGlvbiB4bWxuczpkYz0iaHR0cDovL3B1cmwub3JnL2RjL2VsZW1lbnRzLzEuMS8iIHhtbG5zOnhtcD0iaHR0cDovL25zLmFkb2JlLmNvbS94YXAvMS4wLyIgeG1sbnM6cGRmPSJodHRwOi8vbnMuYWRvYmUuY29tL3BkZi8xLjMvIiB4bWxuczp4bXBNTT0iaHR0cDovL25zLmFkb2JlLmNvbS94YXAvMS4wL21tLyIgeG1sbnM6cGRmeD0iaHR0cDovL25zLmFkb2JlLmNvbS9wZGZ4LzEuMy8iIHJkZjphYm91dD0iIj4KICAgICAgPGRjOmZvcm1hdD5hcHBsaWNhdGlvbi9wZGY8L2RjOmZvcm1hdD4KICAgICAgPHBkZng6U1dDb252ZXJJbmZvPjMzPC9wZGZ4OlNXQ29u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b3IgMTEgMCBSL1N1YnR5cGUgL0NJREZvbnRUeXBlMi9UeXBlIC9Gb250L1cgWzE1MTQgNDY2NSAxMDAwXT4+CmVuZG9iagoxMyAwIG9iago8PC9CYXNlRm9udCAvU2ltU3VuL0Rlc2NlbmRhbnRGb250cyBbMTUgMCBSXS9FbmNvZGluZyAvVW5pR0ItVUNTMi1IL1N1YnR5cGUgL1R5cGUwL1R5cGUgL0ZvbnQ+PgplbmRvYmoKMTQgMCBvYmoKPDwvQXNjZW50IDg1OS9DYXBIZWlnaHQgODAwL0Rlc2NlbnQgLTE0MC9GbGFncyAzMi9Gb250QkJveCBbLTcgLTE0MCAxMDAwIDg1OV0vRm9udEZhbWlseSAoU2ltU3VuKS9Gb250TmFtZSAvU2ltU3VuL0ZvbnRXZWlnaHQgNDAwL0l0YWxpY0FuZ2xlIDAvU3RlbVYgMC9UeXBlIC9Gb250RGVzY3JpcHRvcj4+CmVuZG9iagoxNSAwIG9iago8PC9CYXNlRm9udCAvU2ltU3VuL0NJRFN5c3RlbUluZm8gPDwvT3JkZXJpbmcgKEdCMSkvUmVnaXN0cnkgKEFkb2JlKS9TdXBwbGVtZW50IDQ+Pi9Gb250RGVzY3JpcHRvciAxNCAwIFIvU3VidHlwZSAvQ0lERm9udFR5cGUyL1R5cGUgL0ZvbnQvVyBbMTI0MiA2MDMxIDEwMDBdPj4KZW5kb2JqCjE2IDAgb2JqCjw8L0Jhc2VGb250IC9TaW1TdW4vRW5jb2RpbmcgL1dpbkFuc2lFbmNvZGluZy9GaXJzdENoYXIgMzIvRm9udERlc2NyaXB0b3IgMTcgMCBSL0xhc3RDaGFyIDgwL1N1YnR5cGUgL1RydWVUeXBlL1R5cGUgL0ZvbnQvV2lkdGhzIFs1MDAgMCAwIDAgMCAwIDAgMCAwIDAgNTAwIDAgMCAwIDUwMCAwIDUwMCA1MDAgNTAwIDUwMCA1MDAgNTAwIDUwMCA1MDAgNTAwIDUwMCA1MDAgMCAwIDAgMCAwIDAgMCAwIDAgMCAwIDUwMCAwIDAgMCAwIDAgMCAwIDAgMCA1MDBdPj4KZW5kb2JqCjE3IDAgb2JqCjw8L0FzY2VudCA4NTkvQ2FwSGVpZ2h0IDgwMC9EZXNjZW50IC0xNDAvRmxhZ3MgMzIvRm9udEJCb3ggWy03IC0xNDAgMTAwMCA4NTldL0ZvbnRGYW1pbHkgKFNpbVN1bikvRm9udE5hbWUgLy9Gb250V2VpZ2h0IDQwMC9JdGFsaWNBbmdsZSAwL1N0ZW1WIDAvVHlwZSAvRm9udERlc2NyaXB0b3I+PgplbmRvYmoKMTggMCBvYmoKPDwvQmFzZUZvbnQgL1NpbUhlaS9FbmNvZGluZyAvV2luQW5zaUVuY29kaW5nL0ZpcnN0Q2hhciA0OS9Gb250RGVzY3JpcHRvciAxOSAwIFIvTGFzdENoYXIgMTIyL1N1YnR5cGUgL1RydWVUeXBlL1R5cGUgL0ZvbnQvV2lkdGhzIFs1MDAgMCAwIDAgNTAwIDAgNTAwIDAgNTAwIDAgMCAwIDAgMCAwIDAgMCAwIDAgNTAwIDAgNTAwIDUwMCAwIDAgMCAwIDAgMCAwIDAgMCAwIDAgMCAwIDAgMCAwIDAgMCAwIDAgMCAwIDAgMCAwIDUwMCAwIDAgNTAwIDAgMCA1MDAgNTAwIDUwMCAwIDAgMCAwIDUwMCA1MDAgMCAwIDAgMCA1MDAgNTAwIDAgMCAwIDAgNTAwXT4+CmVuZG9iagoxOSAwIG9iago8PC9Bc2NlbnQgODU5L0NhcEhlaWdodCA4MDAvRGVzY2VudCAtMTQwL0ZsYWdzIDMyL0ZvbnRCQm94IFstMTEgLTE1NiA5OTYgODU5XS9Gb250RmFtaWx5IChTaW1IZWkpL0ZvbnROYW1lIC8vRm9udFdlaWdodCA0MDAvSXRhbGljQW5nbGUgMC9TdGVtViAwL1R5cGUgL0ZvbnREZXNjcmlwdG9yPj4KZW5kb2JqCjIwIDAgb2JqCjw8L0Jhc2VGb250IC9TaW1IZWkvRGVzY2VuZGFudEZvbnRzIFsyMiAwIFJdL0VuY29kaW5nIC9VbmlHQi1VQ1MyLUgvU3VidHlwZSAvVHlwZTAvVHlwZSAvRm9udD4+CmVuZG9iagoyMSAwIG9iago8PC9Bc2NlbnQgODU5L0NhcEhlaWdodCA4MDAvRGVzY2VudCAtMTQwL0ZsYWdzIDMyL0ZvbnRCQm94IFstMTEgLTE1NiA5OTYgODU5XS9Gb250RmFtaWx5IChTaW1IZWkpL0ZvbnROYW1lIC9TaW1IZWkvRm9udFdlaWdodCA0MDAvSXRhbGljQW5nbGUgMC9TdGVtViAwL1R5cGUgL0ZvbnREZXNjcmlwdG9yPj4KZW5kb2JqCjIyIDAgb2JqCjw8L0Jhc2VGb250IC9TaW1IZWkvQ0lEU3lzdGVtSW5mbyA8PC9PcmRlcmluZyAoR0IxKS9SZWdpc3RyeSAoQWRvYmUpL1N1cHBsZW1lbnQgND4+L0ZvbnREZXNjcmlwdG9yIDIxIDAgUi9TdWJ0eXBlIC9DSURGb250VHlwZTIvVHlwZSAvRm9udC9XIFsyNjkgNDY1NiAxMDAwXT4+CmVuZG9iagoyMyAwIG9iago8PC9CYXNlRm9udCAvRGVuZ1hpYW4vRGVzY2VuZGFudEZvbnRzIFsyNSAwIFJdL0VuY29kaW5nIC9VbmlHQi1VQ1MyLUgvU3VidHlwZSAvVHlwZTAvVHlwZSAvRm9udD4+CmVuZG9iagoyNCAwIG9iago8PC9Bc2NlbnQgODEwL0NhcEhlaWdodCA4MDAvRGVzY2VudCAtMjMxL0ZsYWdzIDMyL0ZvbnRCQm94IFstMTQxIC0yNTMgMTE1MSA5NTNdL0ZvbnRGYW1pbHkgKERlbmdYaWFuKS9Gb250TmFtZSAvRGVuZ1hpYW4vRm9udFdlaWdodCA0MDAvSXRhbGljQW5nbGUgMC9TdGVtViAwL1R5cGUgL0ZvbnREZXNjcmlwdG9yPj4KZW5kb2JqCjI1IDAgb2JqCjw8L0Jhc2VGb250IC9EZW5nWGlhbi9DSURTeXN0ZW1JbmZvIDw8L09yZGVyaW5nIChHQjEpL1JlZ2lzdHJ5IChBZG9iZSkvU3VwcGxlbWVudCA0Pj4vRm9udERlc2NyaXB0b3IgMjQgMCBSL1N1YnR5cGUgL0NJREZvbnRUeXBlMi9UeXBlIC9Gb250L1cgWzI2NSAyNjUgMTAwMF0+PgplbmRvYmoKMjYgMCBvYmoKPDwvQml0c1BlckNvbXBvbmVudCA4L0NvbG9yU3BhY2UgL0RldmljZVJHQi9GaWx0ZXIgL0ZsYXRlRGVjb2RlL0hlaWdodCAyNjAvTGVuZ3RoIDEwNDUzL1N1YnR5cGUgL0ltYWdlL1R5cGUgL1hPYmplY3QvV2lkdGggMjYwPj4Kc3RyZWFtDQp4nO1dCXcTR7N9//+HvHO+LycQAg6rMQYMGMwW1gAJO3jR7Pv09rp7JFk2UuhRTdWMztM9E59gV/e9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b3JnLzIwMDMvaW5zdGFuY2UiIHhtbG5zOm51bT0iaHR0cDovL3d3dy54YnJsLm9yZy9kdHIvdHlwZS9udW1lcmljIiB4bWxuczpsaW5rPSJodHRwOi8vd3d3Lnhicmwub3JnLzIwMDMvbGlua2Jhc2UiIHhtbG5zOmlzbzQyMTc9Imh0dHA6Ly93d3cueGJybC5vcmcvMjAwMy9pc280MjE3IiB4bWxuczp4YnJsZHQ9Imh0dHA6Ly94YnJsLm9yZy8yMDA1L3hicmxkdCIgeG1sbnM6bGFiZWw9Imh0dHA6Ly94YnJsLm9yZy8yMDA4L2xhYmVsIiB4bWxuczp4YnJsaT0iaHR0cDovL3d3dy54YnJsLm9yZy8yMDAzL2luc3RhbmNlIiB4bWxuczp4c2k9Imh0dHA6Ly93d3cudzMub3JnLzIwMDEvWE1MU2NoZW1hLWluc3RhbmNlIiB4bWxuczpnZW49Imh0dHA6Ly94YnJsLm9yZy8yMDA4L2dlbmVyaWMiIHhtbG5zOnhsPSJodHRwOi8vd3d3Lnhicmwub3JnLzIwMDMvWExpbmsiIHhtbG5zOnhicmxkaT0iaHR0cDovL3hicmwub3JnLzIwMDYveGJybGRpIiB4bWxuczpyYWk9Imh0dHA6Ly94YnJsLm1vZi5nb3YuY24vdGF4b25vbXkvMjAyMS0xMS0zMC9yYWkiIHhtbG5zOm5vbm51bT0iaHR0cDovL3d3dy54YnJsLm9yZy9kdHIvdHlwZS9ub24tbnVtZXJpYyIgeG1sbnM6eGxpbms9Imh0dHA6Ly93d3cudzMub3JnLzE5OTkveGxpbmsiIHhzaTpzY2hlbWFMb2NhdGlvbj0iaHR0cDovL3d3dy54YnJsLm9yZy8yMDAzL2xpbmtiYXNlIGh0dHA6Ly93d3cueGJybC5vcmcvMjAwMy94YnJsLWxpbmtiYXNlLTIwMDMtMTItMzEueHNkIj48bGluazpzY2hlbWFSZWYgeGxpbms6dHlwZT0ic2ltcGxlIiB4bGluazpocmVmPSJyYWlfaXNzdWVyX2VudHJ5X3BvaW50XzIwMjEtMTEtMzAueHNkIi8+PGNvbnRleHQgaWQ9IkFzX09mXzIwMjZfMDNfMTAiPjxlbnRpdHk+PGlkZW50aWZpZXIgc2NoZW1lPSJodHRwOi8veGJybC5tb2YuZ292LmNuIj4yNjQ0OTEyMzcxMTAwMDM3NzgzODwvaWRlbnRpZmllcj48L2VudGl0eT48cGVyaW9kPjxpbnN0YW50PjIwMjYtMDMtMTA8L2luc3RhbnQ+PC9wZXJpb2Q+PC9jb250ZXh0Pjx1bml0IGlkPSJDTlkiPjxtZWFzdXJlPmlzbzQyMTc6Q05ZPC9tZWFzdXJlPjwvdW5pdD48cmFpOlR5cGVPZlZvdWNoZXIgY29udGV4dFJlZj0iQXNfT2ZfMjAyNl8wM18xMCI+55S15a2Q5Y+R56Wo77yI6ZOB6Lev55S15a2Q5a6i56Wo77yJPC9yYWk6VHlwZU9mVm91Y2hlcj48cmFpOkVsZWN0cm9uaWNJbnZvaWNlUmFpbHdheUVUaWNrZXROdW1iZXIgY29udGV4dFJlZj0iQXNfT2ZfMjAyNl8wM18xMCI+MjY0NDkxMjM3MTEwMDAzNzc4Mzg8L3JhaTpFbGVjdHJvbmljSW52b2ljZVJhaWx3YXlFVGlja2V0TnVtYmVyPjxyYWk6RGF0ZU9mSXNzdWUgY29udGV4dFJlZj0iQXNfT2ZfMjAyNl8wM18xMCI+MjAyNi0wMy0xMDwvcmFpOkRhdGVPZklzc3VlPjxyYWk6SXNzdWVQYXJ0eSBjb250ZXh0UmVmPSJBc19PZl8yMDI2XzAzXzEwIiB4c2k6bmlsPSJ0cnVlIi8+PHJhaTpJc3N1ZVBhcnR5Q29kZSBjb250ZXh0UmVmPSJBc19PZl8yMDI2XzAzXzEwIiB4c2k6bmlsPSJ0cnVlIi8+PHJhaTpUeXBlT2ZCdXNpbmVzcyBjb250ZXh0UmVmPSJBc19PZl8yMDI2XzAzXzEwIj7llK48L3JhaTpUeXBlT2ZCdXNpbmVzcz48cmFpOkRlcGFydHVyZVN0YXRpb24gY29udGV4dFJlZj0iQXNfT2ZfMjAyNl8wM18xMCI+5bm/5bee5LicPC9yYWk6RGVwYXJ0dXJlU3RhdGlvbj48cmFpOlBob25pY3NPZkRlcGFydHVyZVN0YXRpb24gY29udGV4dFJlZj0iQXNfT2ZfMjAyNl8wM18xMCI+R3Vhbmd6aG91ZG9uZzwvcmFpOlBob25pY3NPZkRlcGFydHVyZVN0YXRpb24+PHJhaTpEZXN0aW5hdGlvblN0YXRpb24gY29udGV4dFJlZj0iQXNfT2ZfMjAyNl8wM18xMCI+56aP55SwPC9yYWk6RGVzdGluYXRpb25TdGF0aW9uPjxyYWk6UGhvbmljc09mRGVzdGluYXRpb25TdGF0aW9uIGNvbnRleHRSZWY9IkFzX09mXzIwMjZfMDNfMTAiPkZ1dGlhbjwvcmFpOlBob25pY3NPZkRlc3RpbmF0aW9uU3RhdGlvbj48cmFpOlRyYWluTnVtYmVyIGNvbnRleHRSZWY9IkFzX09mXzIwMjZfMDNfMTAiPkQ3NTkxPC9yYWk6VHJhaW5OdW1iZXI+PHJhaTpUcmF2ZWxEYXRlIGNvbnRleHRSZWY9IkFzX09mXzIwMjZfMDNfMTAiPjIwMjYtMDMtMDk8L3JhaTpUcmF2ZWxEYXRlPjxyYWk6RGVwYXJ0dXJlVGltZSBjb250ZXh0UmVmPSJBc19PZl8yMDI2XzAzXzEwIj4wNzoyMDwvcmFpOkRlcGFydHVyZVRpbWU+PHJhaTpUeXBlT2ZSYWlsd2F5VGlja2V0IGNvbnRleHRSZWY9IkFzX09mXzIwMjZfMDNfMTAiIHhzaTpuaWw9InRydWUiLz48cmFpOkFpckNvbmRpdGlvbmluZ0NoYXJhY3RlcmlzdGljcyBjb250ZXh0UmVmPSJBc19PZl8yMDI2XzAzXzEwIiB4c2k6bmlsPSJ0cnVlIi8+PHJhaTpTZWF0TGV2ZWwgY29udGV4dFJlZj0iQXNfT2ZfMjAyNl8wM18xMCI+5LqM562J5bqnPC9yYWk6U2VhdExldmVsPjxyYWk6Q2FycmlhZ2UgY29udGV4dFJlZj0iQXNfT2ZfMjAyNl8wM18xMCI+MDTovaY8L3JhaTpDYXJyaWFnZT48cmFpOlNlYXQgY29udGV4dFJlZj0iQXNfT2ZfMjAyNl8wM18xMCI+MDNG5Y+3PC9yYWk6U2VhdD48cmFpOkRpc2NvdW50TWFyayBjb250ZXh0UmVmPSJBc19PZl8yMDI2XzAzXzEwIiB4c2k6bmlsPSJ0cnVlIi8+PHJhaTpGYXJlIGNvbnRleHRSZWY9IkFzX09mXzIwMjZfMDNfMTAiIHVuaXRSZWY9IkNOWSIgZGVjaW1hbHM9IjIiPjgzLjAwPC9yYWk6RmFyZT48cmFpOkFtb3VudFJlZnVuZGVkIGNvbnRleHRSZWY9IkFzX09mXzIwMjZfMDNfMTAiIHVuaXRSZWY9IkNOWSIgeHNpOm5pbD0idHJ1ZSIvPjxyYWk6RmFyZU9mT3JpZ2luYWxSYWlsd2F5VGlja2V0IGNvbnRleHRSZWY9IkFzX09mXzIwMjZfMDNfMTAiIHVuaXRSZWY9IkNOWSIgeHNpOm5pbD0idHJ1ZSIvPjxyYWk6RGVwYXJ0dXJlU3RhdGlvbk9mT3JpZ2luYWxSYWlsd2F5VGlja2V0IGNvbnRleHRSZWY9IkFzX09mXzIwMjZfMDNfMTAiIHhzaTpuaWw9InRydWUiLz48cmFpOkRlc3RpbmF0aW9uU3RhdGlvbk9mT3JpZ2luYWxSYWlsd2F5VGlja2V0IGNvbnRleHRSZWY9IkFzX09mXzIwMjZfMDNfMTAiIHhzaTpuaWw9InRydWUiLz48cmFpOkVUaWNrZXROdW1iZXIgY29udGV4dFJlZj0iQXNfT2ZfMjAyNl8wM18xMCI+MjM3MTE2MjA4NjAzMDc5MjYyOTg0MjAyNjwvcmFpOkVUaWNrZXROdW1iZXI+PHJhaTpJZE51bWJlciBjb250ZXh0UmVmPSJBc19PZl8yMDI2XzAzXzEwIj40NDA4MjMxOTk3KioqKjM2Mjk8L3JhaTpJZE51bWJlcj48cmFpOk5hbWUgY29udGV4dFJlZj0iQXNfT2ZfMjAyNl8wM18xMCI+5rip6Z2Z5ai0PC9yYWk6TmFtZT48cmFpOlRvdGFsQW1vdW50RXhjbHVkaW5nVGF4IGNvbnRleHRSZWY9IkFzX09mXzIwMjZfMDNfMTAiIHVuaXRSZWY9IkNOWSIgZGVjaW1hbHM9IjIiPjc2LjE1PC9yYWk6VG90YWxBbW91bnRFeGNsdWRpbmdUYXg+PHJhaTpUYXhSYXRlIGNvbnRleHRSZWY9IkFzX09mXzIwMjZfMDNfMTAiIHVuaXRSZWY9IkNOWSIgZGVjaW1hbHM9IjIiPjAuMDk8L3JhaTpUYXhSYXRlPjxyYWk6VGF4QW1vdW50IGNvbnRleHRSZWY9IkFzX09mXzIwMjZfMDNfMTAiIHVuaXRSZWY9IkNOWSIgZGVjaW1hbHM9IjIiPjYuODU8L3JhaTpUYXhBbW91bnQ+PHJhaTpOYW1lT2ZQdXJjaGFzZXIgY29udGV4dFJlZj0iQXNfT2ZfMjAyNl8wM18xMCI+5bm/5Lic576O5Lqa5ZWG5peF56eR5oqA5pyJ6ZmQ5YWs5Y+4PC9yYWk6TmFtZU9mUHVyY2hhc2VyPjxyYWk6VW5pZmllZFNvY2lhbENyZWRpdENvZGVPZlB1cmNoYXNlciBjb250ZXh0UmVmPSJBc19PZl8yMDI2XzAzXzEwIj45MTQ0MDExNjU5MTUyNDY3M1A8L3JhaTpVbmlmaWVkU29jaWFsQ3JlZGl0Q29kZU9mUHVyY2hhc2VyPjxyYWk6QWRkcmVzc1Bob25lTnVtYmVyT2ZQdXJjaGFzZXIgY29udGV4dFJlZj0iQXNfT2ZfMjAyNl8wM18xMCIgeHNpOm5pbD0idHJ1ZSIvPjxyYWk6RGVwb3NpdEJhbmtBbmRBY2NvdW50TnVtYmVyT2ZQdXJjaGFzZXIgY29udGV4dFJlZj0iQXNfT2ZfMjAyNl8wM18xMCIgeHNpOm5pbD0idHJ1ZSIvPjxyYWk6TnVtYmVyT2ZPcmlnaW5hbEludm9pY2UgY29udGV4dFJlZj0iQXNfT2ZfMjAyNl8wM18xMCIgeHNpOm5pbD0idHJ1ZSIvPjxyYWk6UmVtYXJrcyBjb250ZXh0UmVmPSJBc19PZl8yMDI2XzAzXzEwIiB4c2k6bmlsPSJ0cnVlIi8+PC94YnJsPg0KZW5kc3RyZWFtCmVuZG9iagozMCAwIG9iago8PC9FbWJlZGRlZEZpbGVzIDMxIDAgUj4+CmVuZG9iagozMSAwIG9iago8PC9OYW1lcyBbKP7/ADApIDMyIDAgUl0+PgplbmRvYmoKMzIgMCBvYmoKPDwvRUYgPDwvRiAyOSAwIFI+Pi9GICj+/wByAGEAaQBfAGkAcwBzAHUAZQByAF8AMgAwADIANgAwADMAMQAwAF8AMgA2ADQANAA5ADEAMgAzADcAMQAxADAAMAAwADMANwA3ADgAMwA4AC4AeABtAGwpL1R5cGUgL0ZpbGVzcGVjL1VGIDxGRUZGMDA3MjAwNjEwMDY5MDA1RjAwNjkwMDczMDA3MzAwNzUwMDY1MDA3MjAwNUYwMDMyMDAzMDAwMzIwMDM2MDAzMDAwMzMwMDMxMDAzMDAwNUYwMDMyMDAzNjAwMzQwMDM0MDAzOTAwMzEwMDMyMDAzMzAwMzcwMDMxMDAzMTAwMzAwMDMwMDAzMDAwMzMwMDM3MDAzNzAwMzgwMDMzMDAzODAwMkUwMDc4MDA2RDAwNkM+Pj4KZW5kb2JqCjMzIDAgb2JqCjw8L0ZpbHRlciAvRmxhdGVEZWNvZGUvTGVuZ3RoIDc4Pj4Kc3RyZWFtDQp4nLOxr8jNUShLLSrOzM+zVTLUM1BSSM1Lzk/JzEu3VSotSdO1ULK347JxTEnJLAEqScxxSSxJtLNxyU8uzU3NK9G3s9FHk+MCAJujHCENCmVuZHN0cmVhbQplbmRvYmoKeHJlZgowIDM0CjAwMDAwMDAwMDAgNjU1MzUgZg0KMDAwMDAwMDAxNSAwMDAwMCBuDQowMDAwMDAwMDg5IDAwMDAwIG4NCjAwMDAwMDAyODMgMDAwMDAgbg0KMDAwMDAwMDk5NSAwMDAwMCBuDQowMDAwMDAxMDQ4IDAwMDAwIG4NCjAwMDAwMDU1NzQgMDAwMDAgbg0KMDAwMDAwNTcyMCAwMDAwMCBuDQowMDAwMDA1NzYzIDAwMDAwIG4NCjAwMDAwMDU4NTQgMDAwMDAgbg0KMDAwMDAwNTg5NSAwMDAwMCBuDQowMDAwMDA2MDA2IDAwMDAwIG4NCjAwMDAwMDYxOTYgMDAwMDAgbg0KMDAwMDAwNjM3MSAwMDAwMCBuDQowMDAwMDA2NDgzIDAwMDAwIG4NCjAwMDAwMDY2NzQgMDAwMDAgbg0KMDAwMDAwNjg1MCAwMDAwMCBuDQowMDAwMDA3MTI5IDAwMDAwIG4NCjAwMDAwMDczMTQgMDAwMDAgbg0KMDAwMDAwNzY0NiAwMDAwMCBuDQowMDAwMDA3ODMxIDAwMDAwIG4NCjAwMDAwMDc5NDMgMDAwMDAgbg0KMDAwMDAwODEzNCAwMDAwMCBuDQowMDAwMDA4MzA5IDAwMDAwIG4NCjAwMDAwMDg0MjMgMDAwMDAgbg0KMDAwMDAwODYyMCAwMDAwMCBuDQowMDAwMDA4Nzk2IDAwMDAwIG4NCjAwMDAwMTk0MTQgMDAwMDAgbg0KMDAwMDAyMDI2NSAwMDAwMCBuDQowMDAwMDIyMzE4IDAwMDAwIG4NCjAwMDAwMjY2MjcgMDAwMDAgbg0KMDAwMDAyNjY2OSAwMDAwMCBuDQowMDAwMDI2NzEyIDAwMDAwIG4NCjAwMDAwMjcwNDYgMDAwMDAgbg0KdHJhaWxlcgo8PC9JbmZvIDIgMCBSL1Jvb3QgMSAwIFIvU2l6ZSAzND4+CnN0YXJ0eHJlZgoyNzE5NQolJUVPRg==";
        byte[] bytes = Base64.getDecoder().decode(base64);
        if (isPdfBytes(bytes))
            {
System.out.println("pdf");
            }else {System.out.println("img");}
        String fileName = UUID.randomUUID().toString();
        try (ByteArrayInputStream jpgBis = new ByteArrayInputStream(bytes);){

             System.out.println("ddddd");
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private  boolean isPdfBytes(byte[] bytes) {
        return bytes != null && bytes.length >= 4
                && bytes[0] == 0x25 && bytes[1] == 0x50 && bytes[2] == 0x44 && bytes[3] == 0x46;
    }
}
