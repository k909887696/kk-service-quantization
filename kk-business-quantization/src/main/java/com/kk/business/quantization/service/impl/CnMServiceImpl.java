package com.kk.business.quantization.service.impl;


import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kk.business.quantization.dao.entity.CnM;
import com.kk.business.quantization.dao.mapper.CnMMapper;
import com.kk.business.quantization.service.ICnMService;
import com.kk.business.quantization.model.vobase.req.CnMListReqVo;
import com.kk.business.quantization.model.vobase.res.CnMListResVo;
import com.kk.business.quantization.model.vobase.req.CnMAddReqVo;
import com.kk.business.quantization.model.vobase.req.CnMEditReqVo;
import com.kk.business.quantization.model.vobase.res.CnMResVo;
import com.kk.business.quantization.model.vobase.req.CnMDetailsReqVo;
import com.kk.business.quantization.model.vobase.req.CnMDeleteReqVo;
import com.kk.common.base.model.PageResult;
import com.kk.common.utils.BeanUtil;
import com.kk.common.exception.BusinessException;
/**
 * <p>
 * 人民币货币总量对象 服务实现类
 * </p>
 *
 * @author kk
 * @since 2026-05-30
 */
@Service
public class CnMServiceImpl extends ServiceImpl<CnMMapper, CnM> implements ICnMService {


    /**
    * 分批批量插入人民币货币总量对象
    * @param list 数据列表
    * @return
    */
    @Override
    public void insertCnMBatchSomeColumn(List<CnM> list)
    {

        if(list ==null || list.size()<=0) return ;
        int totalCount = list.size();
        int size = 1000;
        int index = 1;
        int totalPage = totalCount / size;
        if( totalPage * size < totalCount ) totalPage += 1;

        for(;index<=totalPage;index++)
        {
            List<CnM> tempList = list.stream().skip((index-1)*size).limit(size).collect(Collectors.toList());
            this.baseMapper.insertBatchSomeColumn(tempList);
        }
    }
    /**
    * 单条插入人民币货币总量对象
    * @param vo 请求参数
    * @return 结果集
    */
    @Override
    public void insertCnM(CnMAddReqVo vo)
    {
        CnM model = new CnM();
        BeanUtil.copyProperties(vo,model);
        this.baseMapper.insert(model);
    }
    /**
    * 更新人民币货币总量对象
    * @param vo 请求参数
    * @return 结果集
    */
    @Override
    public int updateCnM(CnMEditReqVo vo)
    {
        CnM model = new CnM();
        BeanUtil.copyProperties(vo,model);
        int r = this.baseMapper.updateById(model);
        if(r != 1)
        {
            throw new BusinessException("人民币货币总量对象更新失败!");
        }
        return r;
    }
    /**
    * 单条查询人民币货币总量对象
    * @param vo 请求参数
    * @return 结果集
    */
    @Override
    public CnMResVo selectCnMById(CnMDetailsReqVo vo)
    {
        CnM model = new CnM();
        BeanUtil.copyProperties(vo,model);
        CnM res = this.baseMapper.selectById(model);
        CnMResVo resVo = new CnMResVo();
        BeanUtil.copyProperties(res,resVo);
        return resVo;
    }
    /**
    * 删除人民币货币总量对象
    * @param vo 请求参数
    * @return 结果集
    */
    @Override
    public int deleteCnMById(CnMDeleteReqVo vo)
    {
        CnM model = new CnM();
        BeanUtil.copyProperties(vo,model);
        int r = this.baseMapper.deleteById(model);
        if(r != 1)
        {
            throw new BusinessException("人民币货币总量对象删除失败!");
        }
        return r;
    }
    /**
    * 分页获取人民币货币总量对象结果集
    * @param vo 请求参数
    * @return 结果集
    */
    @Override
    public PageResult<CnMListResVo>  selectCnMPageList(CnMListReqVo vo){

        Page<CnMListResVo> page = new Page<>(vo.getPageIndex(),vo.getPageSize());
        Page<CnMListResVo> results = this.baseMapper.selectCnMPageList(page,vo);
        PageResult<CnMListResVo>  pageResult = new PageResult<>();

        return pageResult.convertPage(results);
    }



}
