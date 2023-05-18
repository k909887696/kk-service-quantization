package com.kk.business.quantization.service;

import com.kk.business.quantization.dao.entity.Weekly;
import com.github.jeffreyning.mybatisplus.service.IMppService;

import java.util.List;
import com.kk.business.quantization.model.vo.WeeklyListVo;
import com.kk.business.quantization.model.dto.WeeklyListDto;
import com.kk.business.quantization.model.vo.WeeklyAddVo;
import com.kk.business.quantization.model.vo.WeeklyEditVo;
import com.kk.business.quantization.model.dto.WeeklyDto;
import com.kk.business.quantization.model.vo.WeeklyDetailsVo;
import com.kk.business.quantization.model.vo.WeeklyDeleteVo;
import com.kk.common.base.model.PageResult;
/**
 * <p>
 * 个股周线行情 服务类
 * </p>
 *
 * @author kk
 * @since 2023-05-18
 */
public interface IWeeklyService extends IMppService<Weekly> {

    /**
    * 分批批量插入
    * @param list 数据列表
    * @return
    */
    void insertIgnoreBatch(List<Weekly> list);
    /**
    * 分页获取结果集
    * @param vo 请求参数
    * @return 结果集
    */
    PageResult<WeeklyListDto>  selectPageList(WeeklyListVo vo);
    /**
    * 单条插入
    * @param vo 请求参数
    * @return 结果集
    */
    void insert(WeeklyAddVo vo);
    /**
    * 更新
    * @param vo 请求参数
    * @return 结果集
    */
    int update(WeeklyEditVo vo);
    /**
    * 单条查询
    * @param vo 请求参数
    * @return 结果集
    */
    WeeklyDto selectById(WeeklyDetailsVo vo);
    /**
    * 删除
    * @param vo 请求参数
    * @return 结果集
    */
    int deleteById(WeeklyDeleteVo vo);

}
