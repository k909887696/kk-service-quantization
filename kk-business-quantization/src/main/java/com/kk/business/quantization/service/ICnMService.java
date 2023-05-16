package com.kk.business.quantization.service;

import com.github.jeffreyning.mybatisplus.service.IMppService;
import com.kk.business.quantization.dao.entity.CnM;
import com.kk.business.quantization.model.dto.CnMDto;
import com.kk.business.quantization.model.dto.CnMListDto;
import com.kk.business.quantization.model.vo.*;
import com.kk.common.base.model.BasePage;
import com.kk.common.base.model.PageResult;

import java.util.List;

/**
 * <p>
 * 人民币货币总量 服务类
 * </p>
 *
 * @author kk
 * @since 2021-12-18
 */
public interface ICnMService extends IMppService<CnM> {

    /**
     * 分批批量插入
     * @param list 数据列表
     * @return
     */
    void insertIgnoreBatch(List<CnM> list);
    /**
     * 分页获取结果集
     * @param vo 请求参数
     * @return 结果集
     */
    PageResult<CnMListDto>  selectPageList(CnMListVo vo);
    /**
     * 单条插入
     * @param vo 请求参数
     * @return 结果集
     */
    void insert(CnMAddVo vo);
    /**
     * 更新
     * @param vo 请求参数
     * @return 结果集
     */
    int update(CnMEditVo vo);
    /**
     * 单条查询
     * @param vo 请求参数
     * @return 结果集
     */
    CnMDto selectById(CnMDetailsVo vo);
    /**
     * 删除
     * @param vo 请求参数
     * @return 结果集
     */
    int deleteById(CnMDeleteVo vo);



}
