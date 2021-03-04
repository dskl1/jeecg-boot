package org.jeecg.modules.sale.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.jeecg.modules.sale.entity.SaleSituation;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 销售情况
 * @Author: jeecg-boot
 * @Date: 2020-01-14
 * @Version: V1.0
 */
public interface SaleSituationMapper extends BaseMapper<SaleSituation> {

    @Update("update sale_situation \n" +
            "set remain_number = \n" +
            "buy_number - sell_number")
    public void remainUpdate(); //计算剩余数量

    @Update("update sale_situation \n" +
            "set profit = \n" +
            "sell_number * sell_price - buy_number * buy_price")
    public void profitUpdate(); //计算利润

}
