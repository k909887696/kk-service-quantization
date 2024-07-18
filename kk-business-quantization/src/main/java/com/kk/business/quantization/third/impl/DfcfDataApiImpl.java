package com.kk.business.quantization.third.impl;

import com.kk.business.quantization.config.ThirdDataConfig;
import com.kk.business.quantization.constant.DfcfFieldsMap;
import com.kk.business.quantization.dao.entity.*;
import com.kk.business.quantization.model.po.dfcf.*;
import com.kk.business.quantization.model.po.tushare.ConceptDetailVo;
import com.kk.business.quantization.model.po.tushare.ConceptVo;
import com.kk.business.quantization.third.IDfcfDataApi;
import com.kk.business.quantization.utils.ThridDataUtils;
import com.kk.common.utils.JsonUtil;
import com.kk.common.utils.MapperUtils;
import com.kk.common.utils.httpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import springfox.documentation.spring.web.json.Json;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: kk
 * @Date: 2021/12/11 11:26
 * 东方财富接口
 */
@Service
@Slf4j
public class DfcfDataApiImpl implements IDfcfDataApi {

    @Resource
    public ThirdDataConfig thirdDataConfig;
    @Resource
    public MapperUtils mapperUtils;

    private String dfcfResutlHandler(String result,String cb)
    {

        result = result.replace(cb,"");
        result = result.replace("(","");
        result = result.replace(");","");
        return result;
    }

    /**
     * 获取概念信息
     * @param vo
     * @return
     */
    @Override
    public DfcfData<Concept> concept(ConceptVo vo)
    {
        Map<String, Object> params = new HashMap<>();

        String reqUrl = thirdDataConfig.getDfcfApiUrl()
                +"api/qt/clist/get?po=1&np=1&ut=bd1d9ddb04089700cf9c27f6f7426281&fltt=2&invt=2&fid=f3&fs=m:90+t:3+f:!50&=1584861859282&fields=f1,f2,f3,f4,f5,f6,f7,f8,f9,f10,f12,f13,f14,f15,f16,f17,f18,f20,f21,f23,f24,f25,f26,f22,f33,f11,f62,f128,f136,f115,f152,f124,f107,f104,f105,f140,f141,f207,f222"
                +String.format("&cb=%s&pn=1&pz=%s",thirdDataConfig.getDfcfCb(),5000);
        String resStr = httpUtil.httpRestRequest(params,reqUrl,String.class);
        resStr = dfcfResutlHandler(resStr,thirdDataConfig.getDfcfCb());
        DfcfBaseRes resObj= (DfcfBaseRes) JsonUtil.parseObject(resStr,DfcfBaseRes.class);
        List<Map<String,Object>> diff = ThridDataUtils.dfcfMapTranferHandler(resObj.getData().getDiff(),DfcfFieldsMap.CODE_VALUE_MAP.get(DfcfFieldsMap.DFCF_CONCEPT),null);
        List<Concept> list = mapperUtils.map(diff,Concept.class);
        DfcfData<Concept> res = new DfcfData<>();
        res.setData(list);
        res.setTotal(resObj.getData().getTotal());
        return res;
    }


    /**
     * 获取概念明细信息
     * @param vo
     * @return
     */
    public DfcfData<ConceptDetail> conceptDetail(ConceptDetailVo vo)
    {
        Map<String, Object> params = new HashMap<>();

        String reqUrl = thirdDataConfig.getDfcfApiUrl()
                +"api/qt/clist/get?po=1&np=1&ut=b2884a393a59ad64002292a3e90d46a5&fltt=2&invt=2&fid=f62&stat=1&fields=f12,f14,f2,f3,f62,f184,f66,f69,f72,f75,f78,f81,f84,f87,f204,f205,f124&rt=53019593&_=1590587801744"
                +String.format("&cb=%s&pn=%s&pz=%s&fs=b:%s",thirdDataConfig.getDfcfCb(),1,5000,vo.getId());
        String resStr = httpUtil.httpRestRequest(params,reqUrl,String.class);
        resStr = dfcfResutlHandler(resStr,thirdDataConfig.getDfcfCb());
        DfcfBaseRes resObj= (DfcfBaseRes) JsonUtil.parseObject(resStr,DfcfBaseRes.class);
        Map<String, Object> defaultFields = new HashMap<>();
        defaultFields.put("conceptId",vo.getId());
        if(resObj.getData()!=null && resObj.getData().getDiff() != null) {
            List<Map<String, Object>> diff = ThridDataUtils.dfcfMapTranferHandler(resObj.getData().getDiff(), DfcfFieldsMap.CODE_VALUE_MAP.get(DfcfFieldsMap.DFCF_CONCEPT_DETAIL), defaultFields);
            List<ConceptDetail> list = mapperUtils.map(diff, ConceptDetail.class);
            DfcfData<ConceptDetail> res = new DfcfData<>();
            res.setData(list);
            res.setTotal(resObj.getData().getTotal());
            return res;
        }else {
            return null;
        }
    }

    /**
     * 获取东方财富概念日线行情
     * @param vo
     * @return
     */
    public DfcfData<ConceptDaily> conceptDaily(DfcfConceptDailyVo vo)
    {
        Map<String, Object> params = new HashMap<>();

        String reqUrl = thirdDataConfig.getDfcfHisApiUrl()
                +"api/qt/stock/kline/get?ut=fa5fd1943c7b386f172d6893dbfba10b&fields1=f1%2Cf2%2Cf3%2Cf4%2Cf5&fields2=f51%2Cf52%2Cf53%2Cf54%2Cf55%2Cf56%2Cf57%2Cf58&klt=101&fqt=1&smplmt=1524.6&_=1590670510425"
                +String.format("&cb=%s&beg=%s&end=%s&lmt=%s&secid=%s",
                thirdDataConfig.getDfcfCb(),vo.getStartDate(),vo.getEndDate(),vo.getLimit(),"90."+vo.getConceptId());
        //&cb=jQuery112402670742210902033_1584861859279&beg=20211024&end=20211224&lmt=1000000&secid=90.BK0615
        log.info("{}|{}",reqUrl,"conceptDaily");
        String resStr = httpUtil.doPost(reqUrl,"");
        resStr = dfcfResutlHandler(resStr,thirdDataConfig.getDfcfCb());
        DfcfHisBaseRes resObj= (DfcfHisBaseRes) JsonUtil.parseObject(resStr,DfcfHisBaseRes.class);
        log.info("{}|{}", JsonUtil.getJSONString(resObj),"conceptDaily");
        if(resObj.getData()!=null && resObj.getData().getKlines() != null) {
            List<Map<String, Object>> diff = ThridDataUtils.dfcfKlinesHandler(resObj.getData().getKlines(), DfcfFieldsMap.CODE_VALUE_MAP.get(DfcfFieldsMap.DFCF_CONCEPT_DAILY));
            List<ConceptDaily> list = mapperUtils.map(diff, ConceptDaily.class);
            if (list != null && list.size() > 0) {
                for (int i = 1; i < list.size(); i++) {
                    list.get(i).setTradeDate(list.get(i).getTradeDate().replace("-", ""));
                    list.get(i).setChange(list.get(i).getClose() - list.get(i - 1).getClose());
                    list.get(i).setPreClose(list.get(i - 1).getClose());
                    list.get(i).setPctChg(list.get(i).getChange() / list.get(i).getOpen() * 100);
                    list.get(i).setConceptId(resObj.getData().getCode());
                }
                list.remove(0);
            }
            DfcfData<ConceptDaily> res = new DfcfData<>();
            res.setData(list);
            res.setTotal(resObj.getData().getDktotal());

            return res;
        }else {
            return null;
        }
    }

    /**
     * 获取东方财富概念资金流向
     * @param vo
     * @return
     */
    public DfcfData<ConceptMoneyFlow> conceptMoneyFlow(DfcfConceptMoneyFlowVo vo)
    {
        Map<String, Object> params = new HashMap<>();

        String reqUrl = thirdDataConfig.getDfcfHisApiUrl()
                +"api/qt/stock/fflow/daykline/get?klt=101&fields1=f1,f2,f3,f7&fields2=f51,f52,f53,f54,f55,f56,f57,f58,f59,f60,f61,f62,f63,f64,f65&ut=b2884a393a59ad64002292a3e90d46a5&_=1605620437221"
                +String.format("&cb=%s&lmt=%s&secid=%s",
                thirdDataConfig.getDfcfCb(),vo.getLimit(),"90."+vo.getConceptId());
        //&cb=jQuery112402670742210902033_1584861859279&secid=90.BK0615&lmt=10000
        String resStr = httpUtil.doPost(reqUrl,"");
        resStr = dfcfResutlHandler(resStr,thirdDataConfig.getDfcfCb());
        DfcfHisBaseRes resObj= (DfcfHisBaseRes) JsonUtil.parseObject(resStr,DfcfHisBaseRes.class);
        if(resObj.getData()!=null && resObj.getData().getKlines() != null) {
            List<Map<String, Object>> diff = ThridDataUtils.dfcfKlinesHandler(resObj.getData().getKlines(), DfcfFieldsMap.CODE_VALUE_MAP.get(DfcfFieldsMap.DFCF_CONCEPT_MONEY_FLOW));
            List<ConceptMoneyFlow> list = mapperUtils.map(diff, ConceptMoneyFlow.class);
            if (list != null && list.size() > 0) {
                for (int i = 0; i < list.size(); i++) {
                    list.get(i).setTradeDate(list.get(i).getTradeDate().replace("-", ""));
                    list.get(i).setCode(resObj.getData().getCode());
                    list.get(i).setName(resObj.getData().getName());
                    list.get(i).setNetMfAmount(list.get(i).getBuyElgAmount() + list.get(i).getBuyLgAmount() + list.get(i).getBuyMdAmount() + list.get(i).getBuySmAmount());
                }
            }
            DfcfData<ConceptMoneyFlow> res = new DfcfData<>();
            res.setData(list);
            res.setTotal(resObj.getData().getDktotal());

            return res;
        }else{
            return null;
        }
    }
}
