package com.kk.business.quantization.service;

import com.kk.business.quantization.dao.entity.Monthly;
import com.github.jeffreyning.mybatisplus.service.IMppService;

import java.util.List;
import com.kk.business.quantization.model.vo.MonthlyListVo;
import com.kk.business.quantization.model.dto.MonthlyListDto;
import com.kk.business.quantization.model.vo.MonthlyAddVo;
import com.kk.business.quantization.model.vo.MonthlyEditVo;
import com.kk.business.quantization.model.dto.MonthlyDto;
import com.kk.business.quantization.model.vo.MonthlyDetailsVo;
import com.kk.business.quantization.model.vo.MonthlyDeleteVo;
import com.kk.common.base.model.PageResult;
/**
 * <p>
 * 个股月线行情 服务类
 * </p>
 *
 * @author kk
 * @since 2023-05-18
 */
public interface IMonthlyService extends IMppService<Monthly> {

    /**
    * 分批批量插入
    * @param list 数据列表
    * @return
    */
    void insertIgnoreBatch(List<Monthly> list);
    /**
    * 分页获取结果集
    * @param vo 请求参数
    * @return 结果集
    */
    PageResult<MonthlyListDto>  selectPageList(MonthlyListVo vo);
    /**
    * 单条插入
    * @param vo 请求参数
    * @return 结果集
    */
    void insert(MonthlyAddVo vo);
    /**
    * 更新
    * @param vo 请求参数
    * @return 结果集
    */
    int update(MonthlyEditVo vo);
    /**
    * 单条查询
    * @param vo 请求参数
    * @return 结果集
    */
    MonthlyDto selectById(MonthlyDetailsVo vo);
    /**
    * 删除
    * @param vo 请求参数
    * @return 结果集
    */
    int deleteById(MonthlyDeleteVo vo);

}
