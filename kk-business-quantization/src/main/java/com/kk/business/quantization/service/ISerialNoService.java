package com.kk.business.quantization.service;

import com.github.jeffreyning.mybatisplus.service.IMppService;
import com.kk.business.quantization.dao.entity.SerialNo;
import com.kk.common.base.model.BasePage;
import com.kk.common.base.model.PageResult;

import java.util.List;

/**
 * <p>
 * 自定义主键序号 服务类
 * </p>
 *
 * @author kk
 * @since 2021-12-18
 */
public interface ISerialNoService extends IMppService<SerialNo> {

    /**
    * 分批批量插入
    * @param list 数据列表
    * @return
    */
    void insertIgnoreBatch(List<SerialNo> list);
    /**
    * 分页获取结果集
    * @param vo 请求参数
    * @return 结果集
    */
    PageResult<SerialNo> getSerialNoPageResult(BasePage vo);

    /**
     * 获取id(事务挂起，不然会出现同步后未提交事务获取到旧数据)
     * @param prefix 前缀
     * @param format 日期格式（拼接在前缀后面）
     * @param bit 位数
     * @param size 多少个id
     * @return id列表
     */
     List<String> notsGetNextId(String prefix, String format, int bit, int size);
}
