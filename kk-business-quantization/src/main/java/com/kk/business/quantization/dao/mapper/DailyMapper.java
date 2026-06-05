package com.kk.business.quantization.dao.mapper;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kk.business.quantization.dao.entity.Daily;
import com.kk.business.quantization.model.vo.DailyListVo;
import com.kk.business.quantization.model.vo.SearchDailyLeaderVo;
import com.kk.business.quantization.model.vo.SearchDailyVo;
import com.kk.business.quantization.model.vo.SelectMaxMinByDateRangeVo;
import com.kk.common.dao.mapper.RootMapper;
import com.kk.business.quantization.model.vobase.res.DailyListResVo;
import com.kk.business.quantization.model.vobase.req.DailyListReqVo;
/**
 * <p>
 * 个股日线行情 Mapper 接口
 * </p>
 *
 * @author kk
 * @since 2026-06-04
 */
public interface DailyMapper extends RootMapper<Daily> {
     /**
     * 查询个股日线行情列表
     */
     Page<DailyListResVo> selectDailyPageList(Page page, DailyListReqVo dailyListReqVo);
     /**
      * 查询日线行情拓展信息（分页）
      * @param vo
      * @param page
      * @return
      */
     Page selectDailyExList(IPage page, SearchDailyVo vo);


     /**
      * 查询区间内股票最大最小收盘价
      * @param page
      * @param vo
      * @return
      */
     Page selectMaxMinByDateRange(IPage page, SelectMaxMinByDateRangeVo vo);

     /**
      * 获取区间涨幅最好的概念
      * @param vo
      * @return
      */
     Page selectStockLeader(IPage page, SearchDailyLeaderVo vo);
}
