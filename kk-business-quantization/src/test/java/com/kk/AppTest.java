package com.kk;

import static org.junit.Assert.assertTrue;


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
import com.kk.business.quantization.utils.MD5Common;
import com.kk.business.quantization.utils.ThridDataUtils;
import com.kk.business.quantization.utils.pdfUtils;
import com.kk.common.utils.DateUtil;
import com.kk.common.utils.FileUtil;
import com.kk.common.utils.JsonUtil;
import com.kk.common.utils.httpUtil;
import jdk.internal.util.xml.impl.Input;
import org.apache.commons.lang3.StringUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.printing.PDFPageable;
import org.apache.pdfbox.rendering.PDFRenderer;
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

import org.ofdrw.layout.element.Img;

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
        String res = httpUtil.doPost("http://push2his.eastmoney.com/api/qt/stock/kline/get?ut=fa5fd1943c7b386f172d6893dbfba10b&fields1=f1%2Cf2%2Cf3%2Cf4%2Cf5&fields2=f51%2Cf52%2Cf53%2Cf54%2Cf55%2Cf56%2Cf57%2Cf58&klt=101&fqt=1&smplmt=1524.6&_=1590670510425&cb=jQuery112402670742210902033_1584861859279&beg=20240717&end=20240718&lmt=5000&secid=90.BK1081", "");
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
        File tempfiledir = new File("/temp");
        File tempfile = new File("/temp/converterToPdf.ofd");
        if (!tempfiledir.exists()) {
            tempfiledir.mkdirs();
        }
        tempfile.createNewFile();
        System.out.print(tempfile.getAbsolutePath());


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
        String sid = "NET:1220329420273038902270_0EFB89734F0358CC98AE2C847498E0A1";
        String json = "{\"moneyRmb\":90000,\"payOfflineVoList\":[{\"businessOrders\":[{\"orderId\":\"IO2400040618\",\"moneyRmb\":15000,\"moneyOriginal\":15000}],\"currency\":\"CNY\",\"currencyOriginal\":\"CNY\",\"description\":\"\",\"payerType\":\"1\",\"money\":15000,\"moneyRmb\":15000,\"dealMoneyRmb\":15000,\"rateDifferenceFee\":0,\"exchangeRate\":1,\"orderEntry\":4,\"outsideFlowId\":\"TZW666\",\"payApplicationId\":\"\",\"payeeAccountId\":\"退转资金流水账号\",\"payeeAccountName\":\"商旅退转资金流水业务\",\"payeeFinancialInstitution\":\"中转账户\",\"payeeId\":\"PTSH000000\",\"payeeName\":\"广东美亚商旅科技有限公司\",\"payerAccountId\":\"退转资金流水账号\",\"payerAccountName\":\"商旅退转资金流水业务\",\"payerFinancialInstitution\":\"中转账户\",\"payerId\":\"S125477\",\"payerName\":\"杭州高品自动化设备有限公司\",\"remark\":\"\",\"records\":[{\"cardId\":\"\",\"companyId\":\"S125477\",\"companyName\":\"杭州高品自动化设备有限公司\",\"details\":[{\"businessOrderId\":\"IO2400040618\",\"payAmount\":90000,\"receivableAmount\":\"\",\"recordId\":\"\"}],\"order\":{\"kefuCode\":\"\",\"kefuName\":\"\",\"lv1CodeKf\":\"\",\"lv1NameKf\":\"\",\"lv2CodeKf\":\"\",\"lv2NameKf\":\"\",\"lv3CodeKf\":\"\",\"lv3NameKf\":\"\",\"lv4CodeKf\":\"\",\"lv4NameKf\":\"\",\"lv5CodeKf\":\"\",\"lv5NameKf\":\"\",\"orderOpTime\":\"\"},\"entry\":4,\"objectId\":\"IO2400040618\",\"payAmount\":90000,\"payChannel\":\"MeiYa\",\"payMethod\":\"10\",\"payMode\":\"4\",\"payType\":1,\"productName\":\"众安个人航空意外险-方案四\",\"receivableAmount\":\"\",\"tradingType\":\"1\"}],\"transactionTime\":\"2024-07-04 17:09:59\",\"writeOff\":0,\"createType\":1,\"claimMoneyFlowId\":\"KX240000108121\",\"files\":[]},{\"businessOrders\":[{\"orderId\":\"IO2400040618\",\"moneyRmb\":25000,\"moneyOriginal\":25000}],\"currency\":\"CNY\",\"currencyOriginal\":\"CNY\",\"description\":\"\",\"payerType\":\"1\",\"money\":25000,\"moneyRmb\":25000,\"dealMoneyRmb\":25000,\"rateDifferenceFee\":0,\"exchangeRate\":1,\"orderEntry\":4,\"outsideFlowId\":\"TZW666\",\"payApplicationId\":\"\",\"payeeAccountId\":\"退转资金流水账号\",\"payeeAccountName\":\"商旅退转资金流水业务\",\"payeeFinancialInstitution\":\"中转账户\",\"payeeId\":\"PTSH000000\",\"payeeName\":\"广东美亚商旅科技有限公司\",\"payerAccountId\":\"退转资金流水账号\",\"payerAccountName\":\"商旅退转资金流水业务\",\"payerFinancialInstitution\":\"中转账户\",\"payerId\":\"S125477\",\"payerName\":\"杭州高品自动化设备有限公司\",\"remark\":\"\",\"records\":[{\"cardId\":\"\",\"companyId\":\"S125477\",\"companyName\":\"杭州高品自动化设备有限公司\",\"details\":[{\"businessOrderId\":\"IO2400040618\",\"payAmount\":90000,\"receivableAmount\":\"\",\"recordId\":\"\"}],\"order\":{\"kefuCode\":\"\",\"kefuName\":\"\",\"lv1CodeKf\":\"\",\"lv1NameKf\":\"\",\"lv2CodeKf\":\"\",\"lv2NameKf\":\"\",\"lv3CodeKf\":\"\",\"lv3NameKf\":\"\",\"lv4CodeKf\":\"\",\"lv4NameKf\":\"\",\"lv5CodeKf\":\"\",\"lv5NameKf\":\"\",\"orderOpTime\":\"\"},\"entry\":4,\"objectId\":\"IO2400040618\",\"payAmount\":90000,\"payChannel\":\"MeiYa\",\"payMethod\":\"10\",\"payMode\":\"4\",\"payType\":1,\"productName\":\"众安个人航空意外险-方案四\",\"receivableAmount\":\"\",\"tradingType\":\"1\"}],\"transactionTime\":\"2024-07-04 17:08:31\",\"writeOff\":0,\"createType\":1,\"claimMoneyFlowId\":\"KX240000108118\",\"files\":[]},{\"businessOrders\":[{\"orderId\":\"IO2400040618\",\"moneyRmb\":25000,\"moneyOriginal\":25000}],\"currency\":\"CNY\",\"currencyOriginal\":\"CNY\",\"description\":\"\",\"payerType\":\"1\",\"money\":25000,\"moneyRmb\":25000,\"dealMoneyRmb\":25000,\"rateDifferenceFee\":0,\"exchangeRate\":1,\"orderEntry\":4,\"outsideFlowId\":\"TZW666\",\"payApplicationId\":\"\",\"payeeAccountId\":\"退转资金流水账号\",\"payeeAccountName\":\"商旅退转资金流水业务\",\"payeeFinancialInstitution\":\"中转账户\",\"payeeId\":\"PTSH000000\",\"payeeName\":\"广东美亚商旅科技有限公司\",\"payerAccountId\":\"退转资金流水账号\",\"payerAccountName\":\"商旅退转资金流水业务\",\"payerFinancialInstitution\":\"中转账户\",\"payerId\":\"S125477\",\"payerName\":\"杭州高品自动化设备有限公司\",\"remark\":\"\",\"records\":[{\"cardId\":\"\",\"companyId\":\"S125477\",\"companyName\":\"杭州高品自动化设备有限公司\",\"details\":[{\"businessOrderId\":\"IO2400040618\",\"payAmount\":90000,\"receivableAmount\":\"\",\"recordId\":\"\"}],\"order\":{\"kefuCode\":\"\",\"kefuName\":\"\",\"lv1CodeKf\":\"\",\"lv1NameKf\":\"\",\"lv2CodeKf\":\"\",\"lv2NameKf\":\"\",\"lv3CodeKf\":\"\",\"lv3NameKf\":\"\",\"lv4CodeKf\":\"\",\"lv4NameKf\":\"\",\"lv5CodeKf\":\"\",\"lv5NameKf\":\"\",\"orderOpTime\":\"\"},\"entry\":4,\"objectId\":\"IO2400040618\",\"payAmount\":90000,\"payChannel\":\"MeiYa\",\"payMethod\":\"10\",\"payMode\":\"4\",\"payType\":1,\"productName\":\"众安个人航空意外险-方案四\",\"receivableAmount\":\"\",\"tradingType\":\"1\"}],\"transactionTime\":\"2024-07-04 09:50:42\",\"writeOff\":0,\"createType\":1,\"claimMoneyFlowId\":\"KX240000107818\",\"files\":[]},{\"businessOrders\":[{\"orderId\":\"IO2400040618\",\"moneyRmb\":25000,\"moneyOriginal\":25000}],\"currency\":\"CNY\",\"currencyOriginal\":\"CNY\",\"description\":\"\",\"payerType\":\"1\",\"money\":25000,\"moneyRmb\":25000,\"dealMoneyRmb\":25000,\"rateDifferenceFee\":0,\"exchangeRate\":1,\"orderEntry\":4,\"outsideFlowId\":\"TZW666\",\"payApplicationId\":\"\",\"payeeAccountId\":\"退转资金流水账号\",\"payeeAccountName\":\"商旅退转资金流水业务\",\"payeeFinancialInstitution\":\"中转账户\",\"payeeId\":\"PTSH000000\",\"payeeName\":\"广东美亚商旅科技有限公司\",\"payerAccountId\":\"退转资金流水账号\",\"payerAccountName\":\"商旅退转资金流水业务\",\"payerFinancialInstitution\":\"中转账户\",\"payerId\":\"S125477\",\"payerName\":\"杭州高品自动化设备有限公司\",\"remark\":\"\",\"records\":[{\"cardId\":\"\",\"companyId\":\"S125477\",\"companyName\":\"杭州高品自动化设备有限公司\",\"details\":[{\"businessOrderId\":\"IO2400040618\",\"payAmount\":90000,\"receivableAmount\":\"\",\"recordId\":\"\"}],\"order\":{\"kefuCode\":\"\",\"kefuName\":\"\",\"lv1CodeKf\":\"\",\"lv1NameKf\":\"\",\"lv2CodeKf\":\"\",\"lv2NameKf\":\"\",\"lv3CodeKf\":\"\",\"lv3NameKf\":\"\",\"lv4CodeKf\":\"\",\"lv4NameKf\":\"\",\"lv5CodeKf\":\"\",\"lv5NameKf\":\"\",\"orderOpTime\":\"\"},\"entry\":4,\"objectId\":\"IO2400040618\",\"payAmount\":90000,\"payChannel\":\"MeiYa\",\"payMethod\":\"10\",\"payMode\":\"4\",\"payType\":1,\"productName\":\"众安个人航空意外险-方案四\",\"receivableAmount\":\"\",\"tradingType\":\"1\"}],\"transactionTime\":\"2024-07-04 09:50:35\",\"writeOff\":0,\"createType\":1,\"claimMoneyFlowId\":\"KX240000107817\",\"files\":[]}]}";
        Map<String, Object> params = JSONObject.parseObject(json);
        Map<String, Object> hearder = new HashMap<>();
        String res = "";
        String url = "https://pay.shinetour.com/paycenter//apis/v1/paymentorders/batchPayOffline";
        hearder.put("sid", sid);
        hearder.put("source", "tmc");
        res = httpUtil.httpRestRequest(params, url, hearder, String.class);
        System.out.print(res);
    }

    @Test
    public void testsendInvoiceEmail() {
        String sid = "NET:4250626490584025715640_651851121AA8C6588F7CC93C626C9ED0";
        String json = "{\"moneyRmb\":90000,\"payOfflineVoList\":[{\"businessOrders\":[{\"orderId\":\"IO2400040618\",\"moneyRmb\":15000,\"moneyOriginal\":15000}],\"currency\":\"CNY\",\"currencyOriginal\":\"CNY\",\"description\":\"\",\"payerType\":\"1\",\"money\":15000,\"moneyRmb\":15000,\"dealMoneyRmb\":15000,\"rateDifferenceFee\":0,\"exchangeRate\":1,\"orderEntry\":4,\"outsideFlowId\":\"TZW666\",\"payApplicationId\":\"\",\"payeeAccountId\":\"退转资金流水账号\",\"payeeAccountName\":\"商旅退转资金流水业务\",\"payeeFinancialInstitution\":\"中转账户\",\"payeeId\":\"PTSH000000\",\"payeeName\":\"广东美亚商旅科技有限公司\",\"payerAccountId\":\"退转资金流水账号\",\"payerAccountName\":\"商旅退转资金流水业务\",\"payerFinancialInstitution\":\"中转账户\",\"payerId\":\"S125477\",\"payerName\":\"杭州高品自动化设备有限公司\",\"remark\":\"\",\"records\":[{\"cardId\":\"\",\"companyId\":\"S125477\",\"companyName\":\"杭州高品自动化设备有限公司\",\"details\":[{\"businessOrderId\":\"IO2400040618\",\"payAmount\":90000,\"receivableAmount\":\"\",\"recordId\":\"\"}],\"order\":{\"kefuCode\":\"\",\"kefuName\":\"\",\"lv1CodeKf\":\"\",\"lv1NameKf\":\"\",\"lv2CodeKf\":\"\",\"lv2NameKf\":\"\",\"lv3CodeKf\":\"\",\"lv3NameKf\":\"\",\"lv4CodeKf\":\"\",\"lv4NameKf\":\"\",\"lv5CodeKf\":\"\",\"lv5NameKf\":\"\",\"orderOpTime\":\"\"},\"entry\":4,\"objectId\":\"IO2400040618\",\"payAmount\":90000,\"payChannel\":\"MeiYa\",\"payMethod\":\"10\",\"payMode\":\"4\",\"payType\":1,\"productName\":\"众安个人航空意外险-方案四\",\"receivableAmount\":\"\",\"tradingType\":\"1\"}],\"transactionTime\":\"2024-07-04 17:09:59\",\"writeOff\":0,\"createType\":1,\"claimMoneyFlowId\":\"KX240000108121\",\"files\":[]},{\"businessOrders\":[{\"orderId\":\"IO2400040618\",\"moneyRmb\":25000,\"moneyOriginal\":25000}],\"currency\":\"CNY\",\"currencyOriginal\":\"CNY\",\"description\":\"\",\"payerType\":\"1\",\"money\":25000,\"moneyRmb\":25000,\"dealMoneyRmb\":25000,\"rateDifferenceFee\":0,\"exchangeRate\":1,\"orderEntry\":4,\"outsideFlowId\":\"TZW666\",\"payApplicationId\":\"\",\"payeeAccountId\":\"退转资金流水账号\",\"payeeAccountName\":\"商旅退转资金流水业务\",\"payeeFinancialInstitution\":\"中转账户\",\"payeeId\":\"PTSH000000\",\"payeeName\":\"广东美亚商旅科技有限公司\",\"payerAccountId\":\"退转资金流水账号\",\"payerAccountName\":\"商旅退转资金流水业务\",\"payerFinancialInstitution\":\"中转账户\",\"payerId\":\"S125477\",\"payerName\":\"杭州高品自动化设备有限公司\",\"remark\":\"\",\"records\":[{\"cardId\":\"\",\"companyId\":\"S125477\",\"companyName\":\"杭州高品自动化设备有限公司\",\"details\":[{\"businessOrderId\":\"IO2400040618\",\"payAmount\":90000,\"receivableAmount\":\"\",\"recordId\":\"\"}],\"order\":{\"kefuCode\":\"\",\"kefuName\":\"\",\"lv1CodeKf\":\"\",\"lv1NameKf\":\"\",\"lv2CodeKf\":\"\",\"lv2NameKf\":\"\",\"lv3CodeKf\":\"\",\"lv3NameKf\":\"\",\"lv4CodeKf\":\"\",\"lv4NameKf\":\"\",\"lv5CodeKf\":\"\",\"lv5NameKf\":\"\",\"orderOpTime\":\"\"},\"entry\":4,\"objectId\":\"IO2400040618\",\"payAmount\":90000,\"payChannel\":\"MeiYa\",\"payMethod\":\"10\",\"payMode\":\"4\",\"payType\":1,\"productName\":\"众安个人航空意外险-方案四\",\"receivableAmount\":\"\",\"tradingType\":\"1\"}],\"transactionTime\":\"2024-07-04 17:08:31\",\"writeOff\":0,\"createType\":1,\"claimMoneyFlowId\":\"KX240000108118\",\"files\":[]},{\"businessOrders\":[{\"orderId\":\"IO2400040618\",\"moneyRmb\":25000,\"moneyOriginal\":25000}],\"currency\":\"CNY\",\"currencyOriginal\":\"CNY\",\"description\":\"\",\"payerType\":\"1\",\"money\":25000,\"moneyRmb\":25000,\"dealMoneyRmb\":25000,\"rateDifferenceFee\":0,\"exchangeRate\":1,\"orderEntry\":4,\"outsideFlowId\":\"TZW666\",\"payApplicationId\":\"\",\"payeeAccountId\":\"退转资金流水账号\",\"payeeAccountName\":\"商旅退转资金流水业务\",\"payeeFinancialInstitution\":\"中转账户\",\"payeeId\":\"PTSH000000\",\"payeeName\":\"广东美亚商旅科技有限公司\",\"payerAccountId\":\"退转资金流水账号\",\"payerAccountName\":\"商旅退转资金流水业务\",\"payerFinancialInstitution\":\"中转账户\",\"payerId\":\"S125477\",\"payerName\":\"杭州高品自动化设备有限公司\",\"remark\":\"\",\"records\":[{\"cardId\":\"\",\"companyId\":\"S125477\",\"companyName\":\"杭州高品自动化设备有限公司\",\"details\":[{\"businessOrderId\":\"IO2400040618\",\"payAmount\":90000,\"receivableAmount\":\"\",\"recordId\":\"\"}],\"order\":{\"kefuCode\":\"\",\"kefuName\":\"\",\"lv1CodeKf\":\"\",\"lv1NameKf\":\"\",\"lv2CodeKf\":\"\",\"lv2NameKf\":\"\",\"lv3CodeKf\":\"\",\"lv3NameKf\":\"\",\"lv4CodeKf\":\"\",\"lv4NameKf\":\"\",\"lv5CodeKf\":\"\",\"lv5NameKf\":\"\",\"orderOpTime\":\"\"},\"entry\":4,\"objectId\":\"IO2400040618\",\"payAmount\":90000,\"payChannel\":\"MeiYa\",\"payMethod\":\"10\",\"payMode\":\"4\",\"payType\":1,\"productName\":\"众安个人航空意外险-方案四\",\"receivableAmount\":\"\",\"tradingType\":\"1\"}],\"transactionTime\":\"2024-07-04 09:50:42\",\"writeOff\":0,\"createType\":1,\"claimMoneyFlowId\":\"KX240000107818\",\"files\":[]},{\"businessOrders\":[{\"orderId\":\"IO2400040618\",\"moneyRmb\":25000,\"moneyOriginal\":25000}],\"currency\":\"CNY\",\"currencyOriginal\":\"CNY\",\"description\":\"\",\"payerType\":\"1\",\"money\":25000,\"moneyRmb\":25000,\"dealMoneyRmb\":25000,\"rateDifferenceFee\":0,\"exchangeRate\":1,\"orderEntry\":4,\"outsideFlowId\":\"TZW666\",\"payApplicationId\":\"\",\"payeeAccountId\":\"退转资金流水账号\",\"payeeAccountName\":\"商旅退转资金流水业务\",\"payeeFinancialInstitution\":\"中转账户\",\"payeeId\":\"PTSH000000\",\"payeeName\":\"广东美亚商旅科技有限公司\",\"payerAccountId\":\"退转资金流水账号\",\"payerAccountName\":\"商旅退转资金流水业务\",\"payerFinancialInstitution\":\"中转账户\",\"payerId\":\"S125477\",\"payerName\":\"杭州高品自动化设备有限公司\",\"remark\":\"\",\"records\":[{\"cardId\":\"\",\"companyId\":\"S125477\",\"companyName\":\"杭州高品自动化设备有限公司\",\"details\":[{\"businessOrderId\":\"IO2400040618\",\"payAmount\":90000,\"receivableAmount\":\"\",\"recordId\":\"\"}],\"order\":{\"kefuCode\":\"\",\"kefuName\":\"\",\"lv1CodeKf\":\"\",\"lv1NameKf\":\"\",\"lv2CodeKf\":\"\",\"lv2NameKf\":\"\",\"lv3CodeKf\":\"\",\"lv3NameKf\":\"\",\"lv4CodeKf\":\"\",\"lv4NameKf\":\"\",\"lv5CodeKf\":\"\",\"lv5NameKf\":\"\",\"orderOpTime\":\"\"},\"entry\":4,\"objectId\":\"IO2400040618\",\"payAmount\":90000,\"payChannel\":\"MeiYa\",\"payMethod\":\"10\",\"payMode\":\"4\",\"payType\":1,\"productName\":\"众安个人航空意外险-方案四\",\"receivableAmount\":\"\",\"tradingType\":\"1\"}],\"transactionTime\":\"2024-07-04 09:50:35\",\"writeOff\":0,\"createType\":1,\"claimMoneyFlowId\":\"KX240000107817\",\"files\":[]}]}";
        List<String>  iaCodes=new ArrayList<String>();
        iaCodes.add("IA0000377247");

        Map<String, Object> params = new HashMap<>();
        params.put("codes",iaCodes);
        Map<String, Object> hearder = new HashMap<>();
        String res = "";
        String url = "https://tmc.shinetour.com/invoice//apis/v1/invoiceapply/send_invoiceapply_email";
        hearder.put("sid", sid);
        hearder.put("source", "tmc");



        res = httpUtil.httpRestRequest(params, url, hearder, String.class);
        System.out.print(res);
    }
}
