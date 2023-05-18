package com.kk.business.quantization.service;

import com.kk.business.quantization.dao.entity.IndexBasic;
import com.github.jeffreyning.mybatisplus.service.IMppService;

import java.util.List;
import com.kk.business.quantization.model.vo.IndexBasicListVo;
import com.kk.business.quantization.model.dto.IndexBasicListDto;
import com.kk.business.quantization.model.vo.IndexBasicAddVo;
import com.kk.business.quantization.model.vo.IndexBasicEditVo;
import com.kk.business.quantization.model.dto.IndexBasicDto;
import com.kk.business.quantization.model.vo.IndexBasicDetailsVo;
import com.kk.business.quantization.model.vo.IndexBasicDeleteVo;
import com.kk.common.base.model.PageResult;
/**
 * <p>
 * 指数基本信息 服务类
 * </p>
 *
 * @author kk
 * @since 2023-05-18
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
    PageResult<IndexBasicListDto>  selectPageList(IndexBasicListVo vo);
    /**
    * 单条插入
    * @param vo 请求参数
    * @return 结果集
    */
    void insert(IndexBasicAddVo vo);
    /**
    * 更新
    * @param vo 请求参数
    * @return 结果集
    */
    int update(IndexBasicEditVo vo);
    /**
    * 单条查询
    * @param vo 请求参数
    * @return 结果集
    */
    IndexBasicDto selectById(IndexBasicDetailsVo vo);
    /**
    * 删除
    * @param vo 请求参数
    * @return 结果集
    */
    int deleteById(IndexBasicDeleteVo vo);

}
