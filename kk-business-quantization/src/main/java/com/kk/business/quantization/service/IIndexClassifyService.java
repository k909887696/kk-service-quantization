package com.kk.business.quantization.service;

import com.kk.business.quantization.dao.entity.IndexClassify;
import com.github.jeffreyning.mybatisplus.service.IMppService;

import java.util.List;
import com.kk.business.quantization.model.vo.IndexClassifyListVo;
import com.kk.business.quantization.model.dto.IndexClassifyListDto;
import com.kk.business.quantization.model.vo.IndexClassifyAddVo;
import com.kk.business.quantization.model.vo.IndexClassifyEditVo;
import com.kk.business.quantization.model.dto.IndexClassifyDto;
import com.kk.business.quantization.model.vo.IndexClassifyDetailsVo;
import com.kk.business.quantization.model.vo.IndexClassifyDeleteVo;
import com.kk.common.base.model.PageResult;
/**
 * <p>
 * 申万行业分类 服务类
 * </p>
 *
 * @author kk
 * @since 2023-05-18
 */
public interface IIndexClassifyService extends IMppService<IndexClassify> {

    /**
    * 分批批量插入
    * @param list 数据列表
    * @return
    */
    void insertIgnoreBatch(List<IndexClassify> list);
    /**
    * 分页获取结果集
    * @param vo 请求参数
    * @return 结果集
    */
    PageResult<IndexClassifyListDto>  selectPageList(IndexClassifyListVo vo);
    /**
    * 单条插入
    * @param vo 请求参数
    * @return 结果集
    */
    void insert(IndexClassifyAddVo vo);
    /**
    * 更新
    * @param vo 请求参数
    * @return 结果集
    */
    int update(IndexClassifyEditVo vo);
    /**
    * 单条查询
    * @param vo 请求参数
    * @return 结果集
    */
    IndexClassifyDto selectById(IndexClassifyDetailsVo vo);
    /**
    * 删除
    * @param vo 请求参数
    * @return 结果集
    */
    int deleteById(IndexClassifyDeleteVo vo);

}
