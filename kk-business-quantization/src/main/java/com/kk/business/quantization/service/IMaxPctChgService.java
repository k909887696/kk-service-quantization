package com.kk.business.quantization.service;

import com.kk.business.quantization.dao.entity.MaxPctChg;
import java.util.List;
import com.kk.business.quantization.model.vobase.req.MaxPctChgListReqVo;
import com.kk.business.quantization.model.vobase.res.MaxPctChgListResVo;
import com.kk.business.quantization.model.vobase.req.MaxPctChgAddReqVo;
import com.kk.business.quantization.model.vobase.req.MaxPctChgEditReqVo;
import com.kk.business.quantization.model.vobase.res.MaxPctChgResVo;
import com.kk.business.quantization.model.vobase.req.MaxPctChgDetailsReqVo;
import com.kk.business.quantization.model.vobase.req.MaxPctChgDeleteReqVo;
import com.kk.common.base.model.PageResult;
/**
 * <p>
 * 各个市场涨跌幅限制 服务类
 * </p>
 *
 * @author kk
 * @since 2026-06-04
 */
public interface IMaxPctChgService  {

    /**
    * 分批批量插入各个市场涨跌幅限制
    * @param list 数据列表
    * @return
    */
    void insertMaxPctChgBatchSomeColumn(List<MaxPctChg> list);
    /**
    * 单条插入各个市场涨跌幅限制
    * @param vo 请求参数
    * @return 结果集
    */
    void insertMaxPctChg(MaxPctChgAddReqVo vo);
    /**
    * 更新各个市场涨跌幅限制
    * @param vo 请求参数
    * @return 结果集
    */
    int updateMaxPctChg(MaxPctChgEditReqVo vo);
    /**
    * 单条查询各个市场涨跌幅限制
    * @param vo 请求参数
    * @return 结果集
    */
    MaxPctChgResVo selectMaxPctChgById(MaxPctChgDetailsReqVo vo);
    /**
    * 删除各个市场涨跌幅限制
    * @param vo 请求参数
    * @return 结果集
    */
    int deleteMaxPctChgById(MaxPctChgDeleteReqVo vo);
    /**
    * 分页获取各个市场涨跌幅限制结果集
    * @param vo 请求参数
    * @return 结果集
    */
    PageResult<MaxPctChgListResVo>  selectMaxPctChgPageList(MaxPctChgListReqVo vo);
}

