package com.kk.business.quantization.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.jeffreyning.mybatisplus.service.MppServiceImpl;
import com.kk.business.quantization.dao.entity.SerialNo;
import com.kk.business.quantization.dao.mapper.SerialNoMapper;
import com.kk.business.quantization.model.dto.SerialNoDto;
import com.kk.business.quantization.model.dto.SerialNoListDto;
import com.kk.business.quantization.model.vo.*;
import com.kk.business.quantization.service.ISerialNoService;
import com.kk.common.base.model.BasePage;
import com.kk.common.base.model.PageResult;
import com.kk.common.exception.BusinessException;
import com.kk.common.utils.DateUtil;
import com.kk.common.utils.MapperUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 自定义主键序号 服务实现类
 * </p>
 *
 * @author kk
 * @since 2021-12-18
 */
@Service
@Slf4j
public class SerialNoServiceImpl extends MppServiceImpl<SerialNoMapper, SerialNo> implements ISerialNoService {

    @Resource
    public MapperUtils mapperUtils;
    /**
     * 分批批量插入
     * @param list 数据列表
     * @return
     */
    public void insertIgnoreBatch(List<SerialNo> list)
    {

        if(list ==null || list.size()<=0) return ;
        int totalCount = list.size();
        int size = 1000;
        int index = 1;
        int totalPage = totalCount / size;
        if( totalPage * size < totalCount ) totalPage += 1;

        for(;index<=totalPage;index++)
        {
            List<SerialNo> tempList = list.stream().skip((index-1)*size).limit(size).collect(Collectors.toList());
            this.baseMapper.insertIgnoreBatchSomeColumn(tempList);
        }
    }
    /**
     * 单条插入
     * @param vo 请求参数
     * @return 结果集
     */
    public void insert(SerialNoAddVo vo)
    {
        SerialNo model = mapperUtils.map(vo,SerialNo.class);
        this.baseMapper.insert(model);
    }
    /**
     * 更新
     * @param vo 请求参数
     * @return 结果集
     */
    public int update(SerialNoEditVo vo)
    {
        SerialNo model = mapperUtils.map(vo,SerialNo.class);
        int r = this.baseMapper.updateById(model);
        if(r != 1)
        {
            throw new BusinessException("自定义主键序号更新失败!");
        }
        return r;
    }
    /**
     * 单条查询
     * @param vo 请求参数
     * @return 结果集
     */
    public SerialNoDto selectById(SerialNoDetailsVo vo)
    {
        SerialNo model = mapperUtils.map(vo,SerialNo.class);
        SerialNo res = this.baseMapper.selectByMultiId(model);
        SerialNoDto dto = mapperUtils.map(res,SerialNoDto.class);
        return dto;
    }
    /**
     * 删除
     * @param vo 请求参数
     * @return 结果集
     */
    public int deleteById(SerialNoDeleteVo vo)
    {
        SerialNo model = mapperUtils.map(vo,SerialNo.class);
        int r = this.baseMapper.deleteByMultiId(model);
        if(r != 1)
        {
            throw new BusinessException("自定义主键序号删除失败!");
        }
        return r;
    }
    /**
     * 分页获取结果集
     * @param vo 请求参数
     * @return 结果集
     */
    public PageResult<SerialNoListDto>  selectPageList(SerialNoListVo vo){

        IPage<SerialNoListDto> page = new Page<>(vo.getPageIndex(),vo.getPageSize());
        page = this.baseMapper.selectPageList(page,vo);
        PageResult<SerialNoListDto>  pageResult = new PageResult<>();

        pageResult.setResult(page.getRecords());
        pageResult.setTotalCount(page.getTotal());
        pageResult.setPageIndex(vo.getPageIndex());
        pageResult.setPageSize(vo.getPageSize());

        return pageResult;
    }

    /**
     * 获取id
     * @param prefix 前缀
     * @param format 日期格式（拼接在前缀后面）
     * @param bit 位数
     * @param size 多少个id
     * @return id列表
     */
    public synchronized List<String> notsGetNextId(String prefix,String format, int bit,int size)
    {
        StringBuilder nextPrefix = new StringBuilder();
        if(prefix != null)
            nextPrefix.append(prefix);
        if(StringUtils.isNotBlank(format))
        {
            nextPrefix.append(DateUtil.date2String(new Date(),format));
        }
        prefix =nextPrefix.toString();
        SerialNo serialNo =  baseMapper.selectById(prefix);
        long start = 1;
        if(serialNo ==null)
        {
            serialNo = new SerialNo();
            serialNo.setSerialName(prefix);
            serialNo.setNextValue((long)(size+1));
            baseMapper.insert(serialNo);
        }else {
            start = serialNo.getNextValue();
            baseMapper.updateNext(size,prefix);
            serialNo.setNextValue(serialNo.getNextValue()+(long)size);
        }
        List<String> ids = new ArrayList<>();
        log.info("serialNo.getNextValue:"+serialNo.getNextValue());
        for(long i=start;i<serialNo.getNextValue();i++)
        {
            String tempId =   String.format("%s%s",prefix,  String.format("%0"+bit+"d", i) );
            ids.add(tempId);
        }
        return ids;
    }


}
