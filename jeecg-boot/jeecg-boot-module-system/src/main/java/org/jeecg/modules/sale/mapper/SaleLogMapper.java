package org.jeecg.modules.sale.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.sale.entity.SaleLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 销售记录
 * @Author: jeecg-boot
 * @Date:   2020-01-15
 * @Version: V1.0
 */
public interface SaleLogMapper extends BaseMapper<SaleLog> {

    @Insert("insert ignore into sale_log(id,commercial_month,commodity,sell_number,sell_price,buy_number,buy_price,remain_number,profit)\n" +
            "select UUID(),DATE_FORMAT(create_time,'%Y-%m') crea,commodity,sum(sell_number),\n" +
            "sum(sell_price*sell_number),sum(buy_number),sum(buy_price*buy_number),sum(sell_number)-sum(buy_number),\n" +
            "sum(sell_price*sell_number)-sum(buy_price*buy_number)\n" +
            "from sale_situation\n" +
            "group by commodity,crea;")
    public void saleInsert(); //插入销售记录

}
