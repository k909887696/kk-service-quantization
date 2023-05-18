package com.kk.business.quantization.service;

import com.github.jeffreyning.mybatisplus.service.IMppService;
import com.kk.business.quantization.dao.entity.DailyBasic;
import com.kk.business.quantization.model.dto.DailyBasicDto;
import com.kk.business.quantization.model.dto.DailyBasicListDto;
import com.kk.business.quantization.model.vo.*;
import com.kk.common.base.model.BasePage;
import com.kk.common.base.model.PageResult;

import java.util.List;

/**
 * <p>
 * 个股每日指标 服务类
 * </p>
 *
 * @author kk
 * @since 2021-12-18
 */
public interface IDailyBasicService extends IMppService<DailyBasic> {

    /**
     * 分批批量插入
     * @param list 数据列表
     * @return
     */
    void insertIgnoreBatch(List<DailyBasic> list);
    /**
     * 分页获取结果集
     * @param vo 请求参数
     * @return 结果集
     */
    PageResult<DailyBasicListDto>  selectPageList(DailyBasicListVo vo);
    /**
     * 单条插入
     * @param vo 请求参数
     * @return 结果集
     */
    void insert(DailyBasicAddVo vo);
    /**
     * 更新
     * @param vo 请求参数
     * @return 结果集
     */
    int update(DailyBasicEditVo vo);
    /**
     * 单条查询
     * @param vo 请求参数
     * @return 结果集
     */
    DailyBasicDto selectById(DailyBasicDetailsVo vo);
    /**
     * 删除
     * @param vo 请求参数
     * @return 结果集
     */
    int deleteById(DailyBasicDeleteVo vo);

}
