package com.kk.business.quantization.service;

import com.kk.business.quantization.dao.entity.IndexDaily;
import com.github.jeffreyning.mybatisplus.service.IMppService;

import java.util.List;
import com.kk.business.quantization.model.vo.IndexDailyListVo;
import com.kk.business.quantization.model.dto.IndexDailyListDto;
import com.kk.business.quantization.model.vo.IndexDailyAddVo;
import com.kk.business.quantization.model.vo.IndexDailyEditVo;
import com.kk.business.quantization.model.dto.IndexDailyDto;
import com.kk.business.quantization.model.vo.IndexDailyDetailsVo;
import com.kk.business.quantization.model.vo.IndexDailyDeleteVo;
import com.kk.common.base.model.PageResult;
/**
 * <p>
 * 指数日线行情 服务类
 * </p>
 *
 * @author kk
 * @since 2023-05-19
 */
public interface IIndexDailyService extends IMppService<IndexDaily> {

    /**
    * 分批批量插入
    * @param list 数据列表
    * @return
    */
    void insertIgnoreBatch(List<IndexDaily> list);
    /**
    * 分页获取结果集
    * @param vo 请求参数
    * @return 结果集
    */
    PageResult<IndexDailyListDto>  selectPageList(IndexDailyListVo vo);
    /**
    * 单条插入
    * @param vo 请求参数
    * @return 结果集
    */
    void insert(IndexDailyAddVo vo);
    /**
    * 更新
    * @param vo 请求参数
    * @return 结果集
    */
    int update(IndexDailyEditVo vo);
    /**
    * 单条查询
    * @param vo 请求参数
    * @return 结果集
    */
    IndexDailyDto selectById(IndexDailyDetailsVo vo);
    /**
    * 删除
    * @param vo 请求参数
    * @return 结果集
    */
    int deleteById(IndexDailyDeleteVo vo);

}
