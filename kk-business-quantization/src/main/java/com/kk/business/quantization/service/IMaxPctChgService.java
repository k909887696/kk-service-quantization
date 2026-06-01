package com.kk.business.quantization.service;

import com.kk.business.quantization.dao.entity.MaxPctChg;
import com.github.jeffreyning.mybatisplus.service.IMppService;

import java.util.List;
import com.kk.business.quantization.model.vo.MaxPctChgListVo;
import com.kk.business.quantization.model.dto.MaxPctChgListDto;
import com.kk.business.quantization.model.vo.MaxPctChgAddVo;
import com.kk.business.quantization.model.vo.MaxPctChgEditVo;
import com.kk.business.quantization.model.dto.MaxPctChgDto;
import com.kk.business.quantization.model.vo.MaxPctChgDetailsVo;
import com.kk.business.quantization.model.vo.MaxPctChgDeleteVo;
import com.kk.common.base.model.PageResult;
/**
 * <p>
 * 各个市场涨跌幅限制 服务类
 * </p>
 *
 * @author kk
 * @since 2023-05-18
 */
public interface IMaxPctChgService extends IMppService<MaxPctChg> {

    /**
    * 分批批量插入
    * @param list 数据列表
    * @return
    */
    void insertIgnoreBatch(List<MaxPctChg> list);
    /**
    * 分页获取结果集
    * @param vo 请求参数
    * @return 结果集
    */
    PageResult<MaxPctChgListDto>  selectPageList(MaxPctChgListVo vo);
    /**
    * 单条插入
    * @param vo 请求参数
    * @return 结果集
    */
    void insert(MaxPctChgAddVo vo);
    /**
    * 更新
    * @param vo 请求参数
    * @return 结果集
    */
    int update(MaxPctChgEditVo vo);
    /**
    * 单条查询
    * @param vo 请求参数
    * @return 结果集
    */
    MaxPctChgDto selectById(MaxPctChgDetailsVo vo);
    /**
    * 删除
    * @param vo 请求参数
    * @return 结果集
    */
    int deleteById(MaxPctChgDeleteVo vo);

}
