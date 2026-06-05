package com.kk.business.quantization.service;

import com.kk.business.quantization.dao.entity.KdjCross;
import java.util.List;
import com.kk.business.quantization.model.vobase.req.KdjCrossListReqVo;
import com.kk.business.quantization.model.vobase.res.KdjCrossListResVo;
import com.kk.business.quantization.model.vobase.req.KdjCrossAddReqVo;
import com.kk.business.quantization.model.vobase.req.KdjCrossEditReqVo;
import com.kk.business.quantization.model.vobase.res.KdjCrossResVo;
import com.kk.business.quantization.model.vobase.req.KdjCrossDetailsReqVo;
import com.kk.business.quantization.model.vobase.req.KdjCrossDeleteReqVo;
import com.kk.common.base.model.PageResult;
/**
 * <p>
 * kdj交叉点 服务类
 * </p>
 *
 * @author kk
 * @since 2026-06-04
 */
public interface IKdjCrossService  {

    /**
    * 分批批量插入kdj交叉点
    * @param list 数据列表
    * @return
    */
    void insertKdjCrossBatchSomeColumn(List<KdjCross> list);
    /**
    * 单条插入kdj交叉点
    * @param vo 请求参数
    * @return 结果集
    */
    void insertKdjCross(KdjCrossAddReqVo vo);
    /**
    * 更新kdj交叉点
    * @param vo 请求参数
    * @return 结果集
    */
    int updateKdjCross(KdjCrossEditReqVo vo);
    /**
    * 单条查询kdj交叉点
    * @param vo 请求参数
    * @return 结果集
    */
    KdjCrossResVo selectKdjCrossById(KdjCrossDetailsReqVo vo);
    /**
    * 删除kdj交叉点
    * @param vo 请求参数
    * @return 结果集
    */
    int deleteKdjCrossById(KdjCrossDeleteReqVo vo);
    /**
    * 分页获取kdj交叉点结果集
    * @param vo 请求参数
    * @return 结果集
    */
    PageResult<KdjCrossListResVo>  selectKdjCrossPageList(KdjCrossListReqVo vo);
}

