package org.jeecg.modules.salary.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.jeecg.modules.salary.entity.HeadDisplay;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 首页展示
 * @Author: jeecg-boot
 * @Date:   2021-01-21
 * @Version: V1.0
 */
public interface HeadDisplayMapper extends BaseMapper<HeadDisplay> {

    @Update("update head_display\n" +
            "set no_repay = \n" +
            "(select sum(total_amount)\n" +
            "from invoice_management\n" +
            "where stage = 0)")
    public void repayUpdate();  //更新未回款

    @Update("update head_display\n" +
            "set long_no_repay = \n" +
            "(select sum(total_amount)\n" +
            "from invoice_management\n" +
            "where stage = 0 and DATEDIFF(now(),invoice_date) > 365)")
    public void longRepayUpdate(); //呆账更新

    @Update("update head_display\n" +
            "set no_invoice = \n" +
            "(select sum(forecast_invoice_amount)\n" +
            "from no_invoice\n" +
            "where stage = 0)")
    public void invoiceUpdate(); //未开票更新

}
