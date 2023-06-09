package com.kk;

import static org.junit.Assert.assertTrue;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.kk.business.quantization.model.po.pdd.*;
import com.kk.common.utils.DateUtil;
import com.kk.common.utils.FileUtil;
import com.kk.common.utils.JsonUtil;
import com.kk.common.utils.httpUtil;
import org.junit.Test;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }
    @Test
    public  void testUtils(){
        String res =httpUtil.doPost("http://push2his.eastmoney.com/api/qt/stock/kline/get?ut=fa5fd1943c7b386f172d6893dbfba10b&fields1=f1%2Cf2%2Cf3%2Cf4%2Cf5&fields2=f51%2Cf52%2Cf53%2Cf54%2Cf55%2Cf56%2Cf57%2Cf58&klt=101&fqt=1&smplmt=1524.6&_=1590670510425&cb=jQuery112402670742210902033_1584861859279&beg=20211223&end=20211226&lmt=5000&secid=90.BK0886","");
        System.out.println(res);
    }

    @Test
    public void testPDDGoods() throws Exception {
        String basesavePath ="E:\\淘宝\\picture\\商品资料\\"+ DateUtil.date2String(new Date(),DateUtil.PATTERN_STANDARD08W) +"\\" ;
        String mainPath="主图\\";
        String skuPath="sku\\";
        String detailsPath = "详情\\";
        Map<String, Object> params = new HashMap<>();
        params.put("pre_sale_type",4);
        params.put("page",1);
        params.put("is_onsale",1);
        params.put("sold_out",0);
        params.put("size",100);
        Map<String, Object> header = new HashMap<>();
        header.put("user-agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/102.0.5005.63 Safari/537.36");
        header.put("anti-content","0apWfxUeMwVEsaKm7whqxbAbuA9VoFH56z7CqGYr_CmA25WOzj5kSjYUar06IEHY2OR9wMtPnerglcuZI5OZvxB_d-2OezRCM3hOv32e-3tDpbTL_7S3R1S3lWMXcT8qMxJD22KbQ_TXYgxnHEaOU9yXUgYX09YXATjn0XjXTWtzfKmBZV6n0ZgMgMGi_Aze-EzX-EdRRe7eWmFflImLZFKDPT00uboGjma4jgann9YOsE9415ez4cdMf-KtUVzsl_FZIuMWzWzLfODMROeMq-KW6hgRQHKtlwFeIuIhtuMeRAHtUZSs9gA1BiKEtwSKBAIEB4Ie8DOgfqE--5d4A7zK2swbsIee32hFzCVm2sZgsBesxGoE-xVm2fWrsvOM2qe32KEg25zrf6mTmpt4TIb9qVSQYyYpJR2Z6lB_y1hHSAF5DAepMfzMslfcLhSErIcr_IMaB8XUurL6ka2zR_G_M4yzhNk");
        header.put("cookie","api_uid=ChDdmmKYtI6lajVBtKEIAg==; _bee=XhVsnLxzpebiLQYKnKdVcluY93AeaWeZ; _f77=c30163ee-e259-46d1-8a60-af9519f4b460; _a42=2eed05ad-90a3-4285-ac47-e2bdfdb0f986; rckk=XhVsnLxzpebiLQYKnKdVcluY93AeaWeZ; ru1k=c30163ee-e259-46d1-8a60-af9519f4b460; ru2k=2eed05ad-90a3-4285-ac47-e2bdfdb0f986; _nano_fp=XpEyn0g8n0C8X0XynT_D3p34WsJs1cYbEQPQVcHm; jrpl=bM1xAxFzHbUdZPZVpr4gku8WliG0y0sS; njrpl=bM1xAxFzHbUdZPZVpr4gku8WliG0y0sS; dilx=A6h87keOOmjfRyzspj7bl; mms_b84d1838=3414,120,170,180,3397,3434,3254,3474,3475,3477,3479,3482,1202,1203,1204,1205,3417; x-visit-time=1654694561836; JSESSIONID=2FE358A707CA60220B3D3C5159223092; PASS_ID=1-dBeQJJHfcASf6KDbmxrzlimKfv60TBo0FCRLyHIBpF4l7r74UDt3m2YzRzVUCQCUwwwdSSha3aVL0RHg3Mwsow_881552394_50004361");
        String res = httpUtil.httpRestRequest(params,"https://mms.pinduoduo.com/vodka/v2/mms/query/display/mall/goodsList",header,String.class);
        //System.out.println(res);
        PDDResponseBase<GoodsList> responseBase = (PDDResponseBase<GoodsList>) JSON.parseObject(res, new TypeReference<PDDResponseBase<GoodsList>>() {});
        for (Goods g:responseBase.getResult().getGoods_list()){
            System.out.println("商品编号："+g.getId());
            Map<String, Object> detailparams = new HashMap<>();
            detailparams.put("goods_id",g.getId());
            String goodName = g.getGoods_name().replace("\\","_").replace("/","-").substring(0,20)+"-"+g.getId();
            String detailres = httpUtil.httpRestRequest(detailparams,"https://mms.pinduoduo.com/glide/v2/mms/query/commit/on_shop/detail",header,String.class);
            PDDResponseBase<GoodsDetails> responseBaseDetail = (PDDResponseBase<GoodsDetails>) JSON.parseObject(detailres, new TypeReference<PDDResponseBase<GoodsDetails>>() {});
            String jsonTxtPath = basesavePath + goodName +"\\"+goodName+"["+g.getId()+"].txt";
            StringBuilder jsonContent = new StringBuilder();
            jsonContent.append(JsonUtil.getJSONString( g) +"\n");
            jsonContent.append("======详情\n"+JsonUtil.getJSONString( responseBaseDetail)+"\n");
            jsonContent.append("======格式化详情\n");
            jsonContent.append("======商品名称："+g.getGoods_name()+"\n");
            jsonContent.append("======分类："+responseBaseDetail.getResult().getCats().toString()+"\n");
            jsonContent.append("======sku\n");


            int i =1;
            //主图下载
            for (Gallery gallery :responseBaseDetail.getResult().getCarousel_gallery())
            {
                String url = gallery.getUrl();
                String newFileName=mainPath+goodName+ String.format("%02d", i);
                String newFileSuffix=url.substring(url.lastIndexOf(".")+1);
                String tempPath = basesavePath + goodName +"\\" +newFileName+"."+newFileSuffix;
                FileUtil.createFile(new File(tempPath));
                httpUtil.download(url,tempPath);
                i++;
            }i=1;
            //详情下载
            for (Gallery gallery :responseBaseDetail.getResult().getDetail_gallery())
            {
                String url = gallery.getUrl();
                String newFileName=detailsPath+goodName+ String.format("%02d", i);
                String newFileSuffix=url.substring(url.lastIndexOf(".")+1);
                String tempPath = basesavePath + goodName +"\\" +newFileName+"."+newFileSuffix;
                FileUtil.createFile(new File(tempPath));
                httpUtil.download(url,tempPath);
                i++;
            }i=1;
            //sku下载
            for (SKU sku :responseBaseDetail.getResult().getSkus())
            {
                String url = sku.getThumb_url();
                String newFileName= sku.getSpec().get(0).getParent_name();
                for(SKUSpec skuSpec : sku.getSpec())
                {
                    newFileName+="_"+skuSpec.getSpec_name();
                }
                jsonContent.append("======sku名称："+newFileName+"；价格："+(sku.getPrice()/100)+"\n");
                String newFileSuffix=url.substring(url.lastIndexOf(".")+1);
                String tempPath = basesavePath + goodName+"\\"+skuPath+ newFileName+ String.format("%02d", i)+"."+newFileSuffix;
                FileUtil.createFile(new File(tempPath));
                httpUtil.download(url,tempPath);
                i++;
            }i=1;
            FileUtil.createAndWriteTxtFile(jsonTxtPath,jsonContent.toString());
            System.out.println("商品编号："+g.getId()+":finish");
           // System.out.println(JsonUtil.getJSONString( responseBaseDetail));
        }
        //System.out.println(responseBase.getResult().getGoods_list().get(0).getId());
    }

    @Test
    public void testDownLoadTaoBaoDetailImg() throws Exception {
        String htmlStr = "<p><img align=\"absmiddle\" src=\"https://img.alicdn.com/imgextra/i2/2206656335018/O1CN01sEibN21mwI9AuX3gw_!!2206656335018.jpg\" style=\"max-width: 750.0px;\" data-spm-anchor-id=\"2013.1.0.i0.58433d78RSd8Mx\"><img align=\"absmiddle\" src=\"https://img.alicdn.com/imgextra/i2/2206656335018/O1CN01Q9ULSw1mwI92DaeW8_!!2206656335018.jpg\" style=\"max-width: 750.0px;\"><img align=\"absmiddle\" src=\"https://img.alicdn.com/imgextra/i4/2206656335018/O1CN01MpaiP11mwI9CGZMuN_!!2206656335018.jpg\" style=\"max-width: 750.0px;\"><img align=\"absmiddle\" src=\"https://img.alicdn.com/imgextra/i3/2206656335018/O1CN01YT4XPa1mwI9F69coD_!!2206656335018.jpg\" style=\"max-width: 750.0px;\"><img align=\"absmiddle\" src=\"https://img.alicdn.com/imgextra/i4/2206656335018/O1CN01AMlSF31mwI9F69pHy_!!2206656335018.jpg\" class=\"\" style=\"max-width: 750.0px;\" width=\"750\" height=\"1115\"><img align=\"absmiddle\" src=\"https://img.alicdn.com/imgextra/i2/2206656335018/O1CN01GX5lmZ1mwI9BTSLkn_!!2206656335018.jpg\" class=\"\" style=\"max-width: 750.0px;\" width=\"750\" height=\"504\"><img align=\"absmiddle\" src=\"https://img.alicdn.com/imgextra/i4/2206656335018/O1CN014lhHLZ1mwI9AuXawa_!!2206656335018.jpg\" class=\"\" style=\"max-width: 750.0px;\" width=\"750\" height=\"867\"><img align=\"absmiddle\" src=\"https://img.alicdn.com/imgextra/i2/2206656335018/O1CN01T48BK51mwI96rwbho_!!2206656335018.jpg\" class=\"\" style=\"max-width: 750.0px;\" width=\"750\" height=\"950\"><img align=\"absmiddle\" src=\"https://img.alicdn.com/imgextra/i3/2206656335018/O1CN011zWVHQ1mwI9BTQbfD_!!2206656335018.jpg\" class=\"\" style=\"max-width: 750.0px;\" width=\"750\" height=\"816\"><img align=\"absmiddle\" src=\"https://img.alicdn.com/imgextra/i4/2206656335018/O1CN01lkUc001mwI9F699jK_!!2206656335018.jpg\" class=\"\" style=\"max-width: 750.0px;\" width=\"750\" height=\"639\" data-spm-anchor-id=\"2013.1.0.i2.58433d78RSd8Mx\"></p>";

        Matcher m = Pattern.compile("src=\"?(.*?)(\"|>|\\s+)").matcher(htmlStr);
        String url = "";
        int i=1;
        while(m.find())
        {
            url = m.group(1);
            String newFileName= String.format("%02d", i);
            String newFileSuffix=url.substring(url.lastIndexOf(".")+1);
            String savePath ="E:\\淘宝\\picture\\商品资料\\20220609\\适配大众迈腾朗逸速腾途观明锐水箱散热冷却-166232899454\\详情\\"
                    + DateUtil.date2String(new Date(),DateUtil.PATTERN_STANDARD08W) +"A\\" +newFileName+"."+newFileSuffix;
            FileUtil.createFile( new File(savePath));
            httpUtil.download(url,savePath);
            i++;
            System.out.println("savePath："+savePath.substring(0,20));
        }
    }

    @Test
    public  void testPattern()
    {
        String illegalCharReg = "[^～@#_*%/\\.+:=0-9a-zA-Z]+";
        String numberReg = "[0-9]+";
        String charReg = "[a-zA-Z]+";
        String legalSpecialCharReg = "[～@#_*%/\\.+:=]+";
        String repeatsCharReg = "(\\w)\\1{5}";
        String simpleCharReg ="(1234|abcd|qwer|4321)";

        String htmlStr = "qwer111111fffff1234sdfasdfsadfsadfdsaabcdsasdfaqwerqwer123443211234321fasdfe1234asdfadfqweradddabcd.>";

        Matcher m_illegalCharReg = Pattern.compile(illegalCharReg).matcher(htmlStr);
        System.out.println("m_illegalCharReg："+m_illegalCharReg.find());
        System.out.println("m_illegalCharReg_match："+m_illegalCharReg.group());
        boolean m_numberReg = Pattern.compile(numberReg).matcher(htmlStr).matches();
        System.out.println("m_numberReg："+m_numberReg);
        boolean m_charReg = Pattern.compile(charReg).matcher(htmlStr).matches();
        System.out.println("m_charReg："+m_charReg);
        boolean m_legalSpecialCharReg = Pattern.compile(legalSpecialCharReg).matcher(htmlStr).matches();
        System.out.println("m_legalSpecialCharReg："+m_legalSpecialCharReg);
        boolean m_repeatsCharReg = Pattern.compile(repeatsCharReg).matcher(htmlStr).matches();
        System.out.println("m_repeatsCharReg："+m_repeatsCharReg);
        Matcher m_simpleCharReg = Pattern.compile(simpleCharReg).matcher(htmlStr);

        System.out.println("m_simpleCharReg："+m_simpleCharReg.find());
        System.out.println("m_simpleCharReg："+m_simpleCharReg.group());
        System.out.println("m_simpleCharReg："+m_simpleCharReg.matches());
    }

    @Test
    public void testMobileAndEmail()
    {
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
    public  void testKdjExcuetor()
    {

    }

}
