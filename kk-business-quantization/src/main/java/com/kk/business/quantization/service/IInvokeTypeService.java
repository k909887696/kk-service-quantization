package com.kk.business.quantization.service;

import com.github.jeffreyning.mybatisplus.service.IMppService;
import com.kk.business.quantization.dao.entity.InvokeType;
import com.kk.business.quantization.model.dto.InvokeTypeDto;
import com.kk.business.quantization.model.dto.InvokeTypeListDto;
import com.kk.business.quantization.model.vo.*;
import com.kk.common.base.model.BasePage;
import com.kk.common.base.model.PageResult;

import java.util.List;

/**
 * <p>
 * 任务执行器 服务类
 * </p>
 *
 * @author kk
 * @since 2021-12-18
 */
public interface IInvokeTypeService extends IMppService<InvokeType> {

    /**
     * 分批批量插入
     * @param list 数据列表
     * @return
     */
    void insertIgnoreBatch(List<InvokeType> list);
    /**
     * 分页获取结果集
     * @param vo 请求参数
     * @return 结果集
     */
    PageResult<InvokeTypeListDto>  selectPageList(InvokeTypeListVo vo);
    /**
     * 单条插入
     * @param vo 请求参数
     * @return 结果集
     */
    void insert(InvokeTypeAddVo vo);
    /**
     * 更新
     * @param vo 请求参数
     * @return 结果集
     */
    int update(InvokeTypeEditVo vo);
    /**
     * 单条查询
     * @param vo 请求参数
     * @return 结果集
     */
    InvokeTypeDto selectById(InvokeTypeDetailsVo vo);
    /**
     * 删除
     * @param vo 请求参数
     * @return 结果集
     */
    int deleteById(InvokeTypeDeleteVo vo);

}
