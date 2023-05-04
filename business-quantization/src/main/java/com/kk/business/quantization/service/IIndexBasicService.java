package com.kk.business.quantization.service;

import com.github.jeffreyning.mybatisplus.service.IMppService;
import com.kk.business.quantization.dao.entity.IndexBasic;
import com.kk.common.base.model.BasePage;
import com.kk.common.base.model.PageResult;

import java.util.List;

/**
 * <p>
 * 指数基本信息 服务类
 * </p>
 *
 * @author kk
 * @since 2021-12-18
 */
public interface IIndexBasicService extends IMppService<IndexBasic> {

    /**
    * 分批批量插入
    * @param list 数据列表
    * @return
    */
    void insertIgnoreBatch(List<IndexBasic> list);
    /**
    * 分页获取结果集
    * @param vo 请求参数
    * @return 结果集
    */
    PageResult<IndexBasic> getPageResult(BasePage vo);

}
