package com.kk.business.quantization.serviceview;


import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import java.util.List;
import com.kk.business.quantization.dao.entity.CollectionTaskHistory;
import com.kk.business.quantization.service.ICollectionTaskHistoryService;
import com.kk.business.quantization.serviceapi.CollectionTaskHistoryServiceApi;
import com.kk.business.quantization.model.vobase.req.CollectionTaskHistoryListReqVo;
import com.kk.business.quantization.model.vobase.res.CollectionTaskHistoryListResVo;
import com.kk.business.quantization.model.vobase.req.CollectionTaskHistoryAddReqVo;
import com.kk.business.quantization.model.vobase.req.CollectionTaskHistoryEditReqVo;
import com.kk.business.quantization.model.vobase.res.CollectionTaskHistoryResVo;
import com.kk.business.quantization.model.vobase.req.CollectionTaskHistoryDetailsReqVo;
import com.kk.business.quantization.model.vobase.req.CollectionTaskHistoryDeleteReqVo;
import com.kk.common.base.model.PageResult;
/**
 * <p>
 * 系统设置-数据任务-历史 View服务实现类
 * </p>
 *
 * @author kk
 * @since 2026-06-05
 */
@Service
public class CollectionTaskHistoryServiceView   {

    @Resource
    public CollectionTaskHistoryServiceApi collectionTaskHistoryServiceApi;



}
