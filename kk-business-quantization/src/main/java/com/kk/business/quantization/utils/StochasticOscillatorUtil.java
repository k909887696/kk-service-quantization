package com.kk.business.quantization.utils;

import com.kk.business.quantization.dao.entity.Daily;
import com.kk.business.quantization.dao.entity.StockDayKdj;
import com.kk.business.quantization.model.DailyKdj;
import org.apache.commons.lang3.StringUtils;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 技术指标计算工具类
 */
public class StochasticOscillatorUtil {

    /**
     * kdj 算法
     * @param N 9
     * @param M1 3
     * @param M2 3
     * @param dailyKdjList 日线行情
     * @return
     */
    public static List<StockDayKdj> ComputationKDJ(int N, int M1, int M2, List<DailyKdj> dailyKdjList)
    {
        List<StockDayKdj> list = new ArrayList<>();
        for (int i = 0; i < dailyKdjList.size(); i++)
        {
            StockDayKdj kdj = new StockDayKdj();
            double RSV = 0;
            double a = 0;
            double b = 0;
            double e = 0;
            double close = 0;

            GetMinMaxPirce(i , N, dailyKdjList);
            close = (double)dailyKdjList.get(i).getClose();
            double diff = dailyKdjList.get(i).getMaxPrice() - dailyKdjList.get(i).getMinPrice();
            if (i == 0)
            {
                RSV  = diff == 0 ? 0 : (close - dailyKdjList.get(i).getMinPrice()) / diff * 100;
                a = (1 * RSV + (M1 - 1) * 0) / 1;
                b = (1 * a + (M2 - 1) * 0) / 1;
                e = 3 * a - 2 * b;

            }
            else
            {
                RSV = diff == 0 ? 0 : (close - dailyKdjList.get(i).getMinPrice()) / diff * 100;
                a = (1 * RSV + (M1 - 1) * dailyKdjList.get(i-1).getKValue()) / M1;
                b = (1 * a + (M2 - 1) * dailyKdjList.get(i-1).getDValue()) / M2;
                e = 3 * a - 2 * b;
            }

            dailyKdjList.get(i).setRsv(StringUtils.isBlank(dailyKdjList.get(i).getKdjType()) ? RSV : dailyKdjList.get(i).getRsv());
            dailyKdjList.get(i).setKValue(StringUtils.isBlank(dailyKdjList.get(i).getKdjType()) ? a : dailyKdjList.get(i).getKValue());
            dailyKdjList.get(i).setDValue(StringUtils.isBlank(dailyKdjList.get(i).getKdjType()) ? b : dailyKdjList.get(i).getDValue());
            dailyKdjList.get(i).setJValue(StringUtils.isBlank(dailyKdjList.get(i).getKdjType()) ? e : dailyKdjList.get(i).getJValue());


            kdj.setRsv(new BigDecimal(RSV).setScale(2,RoundingMode.HALF_UP).doubleValue());
            kdj.setKValue(new BigDecimal(a).setScale(2,RoundingMode.HALF_UP).doubleValue());
            kdj.setDValue(new BigDecimal(b).setScale(2, RoundingMode.HALF_UP).doubleValue());
            kdj.setJValue(new BigDecimal(e).setScale(2,RoundingMode.HALF_UP).doubleValue());
            kdj.setKdjType( String.format("%s_%s_%s", N, M1, M2));
            kdj.setTsCode(dailyKdjList.get(i).getTsCode());
            kdj.setTradeDate(dailyKdjList.get(i).getTradeDate());


            //if (a < 0) KLStocklist[i].k_value = 0;
            //if (a > 100) KLStocklist[i].k_value = 100;
            //if (b < 0) KLStocklist[i].d_value = 0;
            //if (b > 100) KLStocklist[i].d_value = 100;
            //if (e < 0) KLStocklist[i].j_value = 0;
            //if (e > 100) KLStocklist[i].j_value = 100;

            list.add(kdj);
        }



        return list;

    }

    /**
     * 获取区间最小值
     * @param index
     * @param N
     * @param dailyKdjList
     */
    public static void GetMinMaxPirce(int index, int N, List<DailyKdj> dailyKdjList)
    {
        int start = (index +1- N ) < 0 ? 0 : (index+1 - N);

        List<DailyKdj>  MinPirce = dailyKdjList.subList(start, index+1);
        if (MinPirce.size() != 0)
        {
            dailyKdjList.get(index).setMinPrice(MinPirce.stream().mapToDouble(DailyKdj::getLow).min().getAsDouble());
            dailyKdjList.get(index).setMaxPrice(MinPirce.stream().mapToDouble(DailyKdj::getHigh).max().getAsDouble());
        }
    }

    /**
     * 空：非交点，UP:向上交点 DOWN：向下交点
     * @param dailyKdjList
     * @return
     */
    public static String IsKDJCrossAndType(List<DailyKdj> dailyKdjList)
    {
        if (dailyKdjList==null ||dailyKdjList.size() < 2) return "";
        for (int i = 1; i < dailyKdjList.size(); i++)
        {
            double low1 = (dailyKdjList.get(i).getKValue() - dailyKdjList.get(i).getDValue());
            double low2 = (dailyKdjList.get(i-1).getKValue() - dailyKdjList.get(i-1).getDValue());
            if (low1 * low2 <= 0){

                if (low2 <= 0 && low1 >= 0 && dailyKdjList.get(i).getKValue() < 50 && dailyKdjList.get(i).getDValue() < 50 && dailyKdjList.get(i).getJValue() < 50)
                {
                    return "UP";
                }
                else {
                    return "DOWN";
                }
            }
        }

        return "";
    }

}
