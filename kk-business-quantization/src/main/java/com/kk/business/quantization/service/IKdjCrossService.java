package com.kk.business.quantization.service;

import com.kk.business.quantization.dao.entity.KdjCross;
import com.github.jeffreyning.mybatisplus.service.IMppService;

import java.util.List;
import com.kk.business.quantization.model.vo.KdjCrossListVo;
import com.kk.business.quantization.model.dto.KdjCrossListDto;
import com.kk.business.quantization.model.vo.KdjCrossAddVo;
import com.kk.business.quantization.model.vo.KdjCrossEditVo;
import com.kk.business.quantization.model.dto.KdjCrossDto;
import com.kk.business.quantization.model.vo.KdjCrossDetailsVo;
import com.kk.business.quantization.model.vo.KdjCrossDeleteVo;
import com.kk.common.base.model.PageResult;
/**
 * <p>
 * kdj交叉点 服务类
 * </p>
 *
 * @author kk
 * @since 2023-05-18
 */
public interface IKdjCrossService extends IMppService<KdjCross> {

    /**
     * 分批批量插入
     * @param list 数据列表
     * @return
     */
    void insertIgnoreBatch(List<KdjCross> list);
    /**
     * 分页获取结果集
     * @param vo 请求参数
     * @return 结果集
     */
    PageResult<KdjCrossListDto>  selectPageList(KdjCrossListVo vo);
    /**
     * 单条插入
     * @param vo 请求参数
     * @return 结果集
     */
    void insert(KdjCrossAddVo vo);
    /**
     * 更新
     * @param vo 请求参数
     * @return 结果集
     */
    int update(KdjCrossEditVo vo);
    /**
     * 单条查询
     * @param vo 请求参数
     * @return 结果集
     */
    KdjCrossDto selectById(KdjCrossDetailsVo vo);
    /**
     * 删除
     * @param vo 请求参数
     * @return 结果集
     */
    int deleteById(KdjCrossDeleteVo vo);

}
