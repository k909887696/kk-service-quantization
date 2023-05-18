package com.kk.business.quantization.service;

import com.kk.business.quantization.dao.entity.DailyTime;
import com.github.jeffreyning.mybatisplus.service.IMppService;

import java.util.List;
import com.kk.business.quantization.model.vo.DailyTimeListVo;
import com.kk.business.quantization.model.dto.DailyTimeListDto;
import com.kk.business.quantization.model.vo.DailyTimeAddVo;
import com.kk.business.quantization.model.vo.DailyTimeEditVo;
import com.kk.business.quantization.model.dto.DailyTimeDto;
import com.kk.business.quantization.model.vo.DailyTimeDetailsVo;
import com.kk.business.quantization.model.vo.DailyTimeDeleteVo;
import com.kk.common.base.model.PageResult;
/**
 * <p>
 * 个股分钟行情 服务类
 * </p>
 *
 * @author kk
 * @since 2023-05-18
 */
public interface IDailyTimeService extends IMppService<DailyTime> {

    /**
     * 分批批量插入
     * @param list 数据列表
     * @return
     */
    void insertIgnoreBatch(List<DailyTime> list);
    /**
     * 分页获取结果集
     * @param vo 请求参数
     * @return 结果集
     */
    PageResult<DailyTimeListDto>  selectPageList(DailyTimeListVo vo);
    /**
     * 单条插入
     * @param vo 请求参数
     * @return 结果集
     */
    void insert(DailyTimeAddVo vo);
    /**
     * 更新
     * @param vo 请求参数
     * @return 结果集
     */
    int update(DailyTimeEditVo vo);
    /**
     * 单条查询
     * @param vo 请求参数
     * @return 结果集
     */
    DailyTimeDto selectById(DailyTimeDetailsVo vo);
    /**
     * 删除
     * @param vo 请求参数
     * @return 结果集
     */
    int deleteById(DailyTimeDeleteVo vo);

}
