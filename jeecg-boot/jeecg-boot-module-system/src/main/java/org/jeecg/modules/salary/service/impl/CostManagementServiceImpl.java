package org.jeecg.modules.salary.service.impl;

import org.apache.poi.hslf.record.Record;
import org.jeecg.modules.company.entity.NewestEmployee;
import org.jeecg.modules.company.service.INewestEmployeeService;
import org.jeecg.modules.salary.entity.CostManagement;
import org.jeecg.modules.salary.mapper.CostManagementMapper;
import org.jeecg.modules.salary.service.ICostManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static java.lang.StrictMath.floor;

/**
 * @Description: 成本管理
 * @Author: jeecg-boot
 * @Date: 2020-08-13
 * @Version: V1.0
 */
@Service
public class CostManagementServiceImpl extends ServiceImpl<CostManagementMapper, CostManagement> implements ICostManagementService {

    @Autowired
    private CostManagementMapper costManagementMapper;

    @Autowired
    private ICostManagementService costManagementService;

    @Override
    public void costInsert() {
        this.costManagementMapper.costInsert();
    }

    @Override
    public void costUpdate() {
        this.costManagementMapper.costUpdate();
    }

    @Override
    public void profitUpdate() {
        this.costManagementMapper.profitUpdate();
    }

    @Override
    public void intReimbursement() {
        this.costManagementMapper.intReimbursement();
    }

    @Override
    public List<CostManagement> queryBankCard(String id) {
        return costManagementService.queryBankCard(id);
    }

    /**
     * 导出
     *
     * @return CallbackBody
     */
    @Override
    public ResponseEntity<byte[]> exportLand2ndClassIndex(String ids) {
        //查询表数据
        List<CostManagement> list = this.costManagementService.listByIds(Arrays.asList(ids.split(",")));

        if (list == null || list.size() <= 0) {
            return null;
        }
        List txtContentList = new ArrayList();
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String str = sdf.format(date);
        BigDecimal count = BigDecimal.valueOf(0);
        txtContentList.add("PAYOFFSTART");
        for (CostManagement classIndex3 : list) {
            count = classIndex3.getReimbursement().add(count);
        }
        txtContentList.add("|44433|532658197462|1|CNY|" + count + "|" + list.size() + "|EV|" + str + "||");
        for (CostManagement classIndex : list) {
            List<NewestEmployee> list2 = this.costManagementMapper.queryBankCard(classIndex.getId());
            for (NewestEmployee classIndex2 : list2) {
                try {
                    txtContentList.add("32|" + classIndex.getReimbursement().stripTrailingZeros().toPlainString() + "|" + classIndex2.getBankCard() + "|" + classIndex.getName() + "|||");
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        }
        txtContentList.add("PAYOFFEND");
        //导出的文件存储目录
        String fileSavePath = "D:/avatar/file/baoxiao.txt";
//        String fileSavePath = "D:/upFiles/file/baoxiao.txt";
        //保存文本文件
        writeToTxt(txtContentList, fileSavePath);
        //获取文本文件的ResponseEntity
        try {
            ResponseEntity<byte[]> fileByte = buildResponseEntity(new File(fileSavePath));
            return fileByte;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<CostManagement> queryAllClassIndex(List<CostManagement> id) {
        return this.costManagementMapper.queryAllClassIndex(id);
    }

    /**
     * 将数据写入文本文件
     *
     * @param list
     * @param path
     */
    private void writeToTxt(List list, String path) {

        String dir = path.substring(0, path.lastIndexOf("/"));
        File parent = new File(dir);
        if (parent != null && !parent.exists()) {
            parent.mkdirs();
        }
        FileOutputStream outSTr = null;
        BufferedOutputStream Buff = null;
        String enter = "\r\n";
        StringBuffer write;
        try {
            outSTr = new FileOutputStream(new File(path));
            Buff = new BufferedOutputStream(outSTr);
            for (int i = 0; i < list.size(); i++) {
                write = new StringBuffer();
                write.append(list.get(i));
                write.append(enter);
                Buff.write(write.toString().getBytes("GBK"));
            }
            Buff.flush();
            Buff.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                assert Buff != null;
                Buff.close();
                outSTr.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //读取文件
    private ResponseEntity<byte[]> buildResponseEntity(File file) throws IOException {
        byte[] body;
        //获取文件
        InputStream is = new FileInputStream(file);
        body = new byte[is.available()];
        is.read(body);
        HttpHeaders headers = new HttpHeaders();
        //设置文件类型
        headers.add("Content-Disposition", "attchement;filename=" + file.getName());
        //设置Http状态码
        HttpStatus statusCode = HttpStatus.OK;
        //返回数据
        ResponseEntity<byte[]> entity = new ResponseEntity<>(body, headers, statusCode);
        return entity;
    }


}
