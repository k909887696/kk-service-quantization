package com.kk.business.quantization.service;

import com.kk.business.quantization.dao.entity.MoneyFlow;
import com.github.jeffreyning.mybatisplus.service.IMppService;

import java.util.List;
import com.kk.business.quantization.model.vo.MoneyFlowListVo;
import com.kk.business.quantization.model.dto.MoneyFlowListDto;
import com.kk.business.quantization.model.vo.MoneyFlowAddVo;
import com.kk.business.quantization.model.vo.MoneyFlowEditVo;
import com.kk.business.quantization.model.dto.MoneyFlowDto;
import com.kk.business.quantization.model.vo.MoneyFlowDetailsVo;
import com.kk.business.quantization.model.vo.MoneyFlowDeleteVo;
import com.kk.common.base.model.PageResult;
/**
 * <p>
 * 个股资金流向 服务类
 * </p>
 *
 * @author kk
 * @since 2023-05-18
 */
public interface IMoneyFlowService extends IMppService<MoneyFlow> {

    /**
    * 分批批量插入
    * @param list 数据列表
    * @return
    */
    void insertIgnoreBatch(List<MoneyFlow> list);
    /**
    * 分页获取结果集
    * @param vo 请求参数
    * @return 结果集
    */
    PageResult<MoneyFlowListDto>  selectPageList(MoneyFlowListVo vo);
    /**
    * 单条插入
    * @param vo 请求参数
    * @return 结果集
    */
    void insert(MoneyFlowAddVo vo);
    /**
    * 更新
    * @param vo 请求参数
    * @return 结果集
    */
    int update(MoneyFlowEditVo vo);
    /**
    * 单条查询
    * @param vo 请求参数
    * @return 结果集
    */
    MoneyFlowDto selectById(MoneyFlowDetailsVo vo);
    /**
    * 删除
    * @param vo 请求参数
    * @return 结果集
    */
    int deleteById(MoneyFlowDeleteVo vo);

}
