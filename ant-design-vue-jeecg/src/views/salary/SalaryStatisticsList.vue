<template>
  <a-card :bordered="false">
    <!-- 查询区域 -->
    <div class="table-page-search-wrapper">
      <a-form layout="inline" @keyup.enter.native="searchQuery">
        <a-row :gutter="24">
          <a-col :md="6" :sm="8">
            <a-form-item label="姓名">
              <a-input placeholder="请输入姓名" v-model="queryParam.name"></a-input>
            </a-form-item>
          </a-col>
          <a-col :md="6" :sm="24">
            <span style="float: left;overflow: hidden;" class="table-page-search-submitButtons">
              <a-button type="primary" @click="searchQuery" icon="search">查询</a-button>
              <a-button type="primary" @click="searchReset" icon="reload" style="margin-left: 8px">重置</a-button>
            </span>
          </a-col>
        </a-row>
      </a-form>
    </div>
    <!-- 查询区域-END -->

    <!-- 操作按钮区域 -->
    <div class="table-operator">
      <a-button @click="handleAdd" type="primary" icon="plus">新增</a-button>
      <a-button type="primary" icon="download" @click="handleExportXls('工资统计')">导出</a-button>
      <a-upload name="file" :showUploadList="false" :multiple="false" :headers="tokenHeader" :action="importExcelUrl"
                @change="handleImportExcel">
        <a-button type="primary" icon="import">导入</a-button>
      </a-upload>
      <a-dropdown v-if="selectedRowKeys.length > 0">
        <a-menu slot="overlay">
          <a-menu-item key="1" @click="batchDel">
            <a-icon type="delete"/>
            删除
          </a-menu-item>
          <a-menu-item key="2" @click="sendMail">
            <a-icon type="mail"/>
            发送工资(正式)
          </a-menu-item>
          <a-menu-item key="3" @click="sendMails">
            <a-icon type="mail"/>
            发送通知
          </a-menu-item>
          <a-menu-item key="3" @click="holiday">
            <a-icon type="calculator"/>
            计算年假
          </a-menu-item>
          <a-menu-item key="4" @click="downloadTxt">
            <a-icon type="download"/>
            下载txt
          </a-menu-item>
        </a-menu>
        <a-button style="margin-left: 8px"> 批量操作
          <a-icon type="down"/>
        </a-button>
      </a-dropdown>
    </div>

    <!-- table区域-begin -->
    <div>
      <div class="ant-alert ant-alert-info" style="margin-bottom: 16px;">
        <i class="anticon anticon-info-circle ant-alert-icon"></i> 已选择 <a style="font-weight: 600">{{
          selectedRowKeys.length
        }}</a>项
        <a style="margin-left: 24px" @click="onClearSelected">清空</a>

      </div>

      <a-table
        ref="table"
        size="middle"
        :scroll="{x:true}"
        bordered
        rowKey="id"
        :columns="columns"
        :dataSource="dataSource"
        :pagination="ipagination"
        :loading="loading"
        :rowSelection="{selectedRowKeys: selectedRowKeys, onChange: onSelectChange}"
        class="j-table-force-nowrap"
        @change="handleTableChange">

        <template slot="htmlSlot" slot-scope="text">
          <div v-html="text"></div>
        </template>
        <template slot="imgSlot" slot-scope="text">
          <span v-if="!text" style="font-size: 12px;font-style: italic;">无图片</span>
          <img v-else :src="getImgView(text)" height="25px" alt=""
               style="max-width:80px;font-size: 12px;font-style: italic;"/>
        </template>
        <template slot="fileSlot" slot-scope="text">
          <span v-if="!text" style="font-size: 12px;font-style: italic;">无文件</span>
          <a-button
            v-else
            :ghost="true"
            type="primary"
            icon="download"
            size="small"
            @click="downloadFile(text)">
            下载
          </a-button>
        </template>

        <span slot="holiday" slot-scope="text, record">
          <a @click="minis(record)">{{ text }}</a>
        </span>

        <span slot="action" slot-scope="text, record">
          <a @click="handleEdit(record)">编辑</a>

          <a-divider type="vertical"/>
          <a-dropdown>
            <a class="ant-dropdown-link">更多 <a-icon type="down"/></a>
            <a-menu slot="overlay">
              <a-menu-item>
                <a @click="handleDetail(record)">详情</a>
              </a-menu-item>
              <a-menu-item>
                <a-popconfirm title="确定删除吗?" @confirm="() => handleDelete(record.id)">
                  <a>删除</a>
                </a-popconfirm>
              </a-menu-item>
            </a-menu>
          </a-dropdown>
        </span>

      </a-table>
    </div>

    <salary-statistics-modal ref="modalForm" @ok="modalFormOk"></salary-statistics-modal>
  </a-card>
</template>

<script>

import '@/assets/less/TableExpand.less'
import { mixinDevice } from '@/utils/mixin'
import { JeecgListMixin } from '@/mixins/JeecgListMixin'
import SalaryStatisticsModal from './modules/SalaryStatisticsModal__Style#Drawer'
import Vue from 'vue'
import { deleteAction, getAction } from '@api/manage'
import { postAction, putAction } from '@api/add'

export default {
  name: 'SalaryStatisticsList',
  mixins: [JeecgListMixin, mixinDevice],
  components: {
    SalaryStatisticsModal
  },
  data() {
    return {
      description: '工资统计管理页面',
      // 表头
      columns: [
        {
          title: '#',
          dataIndex: '',
          key: 'rowIndex',
          width: 60,
          align: 'center',
          customRender: function (t, r, index) {
            return parseInt(index) + 1
          }
        },
        {
          title: '姓名',
          align: 'center',
          dataIndex: 'name'
        },
        {
          title: '电子邮件',
          align: 'center',
          dataIndex: 'email'
        },
        {
          title: '工资标准',
          align: 'center',
          dataIndex: 'basicWageStandard'
        },
        {
          title: '外派补贴标准',
          align: 'center',
          dataIndex: 'dispatchSubsidyStandard'
        },
        {
          title: '应出勤天数',
          align: 'center',
          dataIndex: 'totalDay'
        },
        {
          title: '转正天数',
          align: 'center',
          dataIndex: 'positiveDay'
        },
        {
          title: '转正外派天数',
          align: 'center',
          dataIndex: 'positiveDispatchDay',
        },
        {
          title: '试用天数',
          align: 'center',
          dataIndex: 'trialDay',
        },
        {
          title: '试用外派天数',
          align: 'center',
          dataIndex: 'trialDispatchDay',
        },
        {
          title: '年假',
          align: 'center',
          dataIndex: 'yearHoliday',
          scopedSlots: { customRender: 'holiday' },
        },
        {
          title: '加班时间',
          align: 'center',
          dataIndex: 'overtimeHours'
        },
        {
          title: '工资明细',
          align: 'center',
          children: [
            {
              title: '基本工资',
              align: 'center',
              dataIndex: 'basicWage'
            },
            {
              title: '外派补贴',
              align: 'center',
              dataIndex: 'dispatchSubsidy'
            },
            {
              title: '加班工资',
              align: 'center',
              dataIndex: 'overtimeSalary'
            },
            {
              title: '绩效',
              align: 'center',
              dataIndex: 'performance'
            },
            {
              title: '餐补',
              align: 'center',
              dataIndex: 'mealSupplement'
            },
            {
              title: '其他',
              align: 'center',
              dataIndex: 'otherThing'
            },
            {
              title: '电脑补贴',
              align: 'center',
              dataIndex: 'computerSubsidy'
            },]
        },
        {
          title: '扣除项',
          align: 'center',
          children: [
            {
              title: '预支工资',
              align: 'center',
              dataIndex: 'advanceSalary'
            },
            {
              title: '公积金',
              align: 'center',
              dataIndex: 'providentFund'
            },
            {
              title: '社保(个人)',
              align: 'center',
              dataIndex: 'socialSecurityPerson'
            },
            {
              title: '迟到',
              align: 'center',
              dataIndex: 'late'
            },
          ]
        },
        {
          title: '工资总计',
          align: 'center',
          dataIndex: 'totalIncome'
        },
        {
          title: '税前总计',
          align: 'center',
          dataIndex: 'preTaxIncome'
        },
        {
          title: '专项抵扣项目',
          align: 'center',
          dataIndex: 'specialDeductionItems'
        },
        {
          title: '专项抵扣金额',
          align: 'center',
          dataIndex: 'specialDeductionAmount'
        },
        {
          title: '所得税',
          align: 'center',
          dataIndex: 'personIncome'
        },
        {
          title: '应发',
          align: 'center',
          dataIndex: 'totalSalary'
        },
        {
          title: '操作',
          dataIndex: 'action',
          align: 'center',
          fixed: 'right',
          scopedSlots: {
            filterDropdown: 'filterDropdown',
            filterIcon: 'filterIcon',
            customRender: 'action'
          },
        }
      ],
      url: {
        list: '/salary/salaryStatistics/list',
        mail: '/salary/salaryStatistics/mail',
        mails: '/salary/salaryStatistics/mails',
        holiday: '/salary/salaryStatistics/holiday',
        minis: '/salary/salaryStatistics/minis',
        txt: '/salary/salaryStatistics/txt',
        delete: '/salary/salaryStatistics/delete',
        deleteBatch: '/salary/salaryStatistics/deleteBatch',
        exportXlsUrl: '/salary/salaryStatistics/exportXls',
        importExcelUrl: 'salary/salaryStatistics/importExcel',

      },
      dictOptions: {},
    }
  },
  created() {

  },
  computed: {
    importExcelUrl: function () {
      return `${window._CONFIG['domianURL']}/${this.url.importExcelUrl}`
    },
  },
  methods: {
    downloadTxt() {
      var ids = ''
      for (var a = 0; a < this.selectedRowKeys.length; a++) {
        ids += this.selectedRowKeys[a] + ','
      }
      putAction(this.url.txt, { ids: ids }).then((res) => {
        if (res.success) {
          // window.open(window.location.host + '/jeecg-boot/sys/common/static/file/gongzi.txt', '_blank')
          window.open('http://192.168.31.133:8080/jeecg-boot/sys/common/static/file/gongzi.txt', '_blank')
        } else {
          this.$message.warning('下载失败')
        }
      })
    },
    minis(record) {
      putAction(this.url.minis, { ids: record.id }).then((res) => {
        if (res.success) {
          this.$message.success(res.message)
          this.loadData()
        } else {
          this.$message.warning('失败')
        }
      })
    },
    holiday() {
      var ids = ''
      for (var a = 0; a < this.selectedRowKeys.length; a++) {
        ids += this.selectedRowKeys[a] + ','
      }
      putAction(this.url.holiday, { ids: ids }).then((res) => {
        if (res.success) {
          this.$message.success(res.message)
          this.loadData()
        } else {
          this.$message.warning('计算失败')
        }
      })
    },
    sendMail() {
      var ids = ''
      for (var a = 0; a < this.selectedRowKeys.length; a++) {
        ids += this.selectedRowKeys[a] + ','
      }
      putAction(this.url.mail, { ids: ids }).then((res) => {
        if (res.success) {
          this.$message.success(res.message)
        } else {
          this.$message.warning('发送失败')
        }
      })
    },
    sendMails() {
      var ids = ''
      for (var a = 0; a < this.selectedRowKeys.length; a++) {
        ids += this.selectedRowKeys[a] + ','
      }
      putAction(this.url.mails, { ids: ids }).then((res) => {
        if (res.success) {
          this.$message.success(res.message)
        } else {
          this.$message.warning('发送失败')
        }
      })
    },
    initDictConfig() {

    }
  }
}
</script>
<style scoped>
@import '~@assets/less/common.less';
</style>