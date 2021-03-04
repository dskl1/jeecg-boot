<template>
  <a-card :bordered="false">
    <!-- 查询区域 -->
    <div class="table-page-search-wrapper">
      <a-form layout="inline" @keyup.enter.native="searchQuery">
        <a-row :gutter="24">
          <a-col :md="6" :sm="8">
            <a-form-item label="日期">
              <a-month-picker placeholder="请输入日期" format="YYYY-MM" @change="simpleDateFormat"
                              v-model="selectMonth"></a-month-picker>
            </a-form-item>
          </a-col>
        </a-row>
      </a-form>
    </div>
    <!-- 查询区域-END -->

    <!-- 操作按钮区域 -->
    <div class="table-operator">
      <a-button type="primary" icon="download" @click="handleExportXls('历史工资')">导出</a-button>
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
        </a-menu>
        <a-button style="margin-left: 8px"> 批量操作
          <a-icon type="down"/>
        </a-button>
      </a-dropdown>
    </div>

    <!-- table区域-begin -->
    <div>
      <div class="ant-alert ant-alert-info" style="margin-bottom: 16px;">
        <i class="anticon anticon-info-circle ant-alert-icon"></i> 已选择 <a
        style="font-weight: 600">{{ selectedRowKeys.length }}</a>项
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

    <salary-statistics-copy1-modal ref="modalForm" @ok="modalFormOk"></salary-statistics-copy1-modal>
  </a-card>
</template>

<script>

import '@/assets/less/TableExpand.less'
import { mixinDevice } from '@/utils/mixin'
import { JeecgListMixin } from '@/mixins/JeecgListMixin'
import SalaryStatisticsCopy1Modal from './modules/SalaryStatisticsCopy1Modal'
import { getAction } from '@api/manage'
import { formatDate } from '@/utils/util'

export default {
  name: 'SalaryStatisticsCopy1List',
  mixins: [JeecgListMixin, mixinDevice],
  components: {
    SalaryStatisticsCopy1Modal
  },
  data() {
    return {
      date: new Date(),
      monthFormat: 'YYYY-MM',
      description: '历史工资管理页面',
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
          title: '转正日期',
          align: 'center',
          dataIndex: 'positiveDate',
          customRender: function (text) {
            return !text ? '' : (text.length > 10 ? text.substr(0, 10) : text)
          }
        },
        {
          title: '转正薪资',
          align: 'center',
          dataIndex: 'positiveSalary'
        },
        {
          title: '试用工资',
          align: 'center',
          dataIndex: 'trialSalary'
        },
        {
          title: '转正天数',
          align: 'center',
          dataIndex: 'positiveDay'
        },
        {
          title: '试用天数',
          align: 'center',
          dataIndex: 'trialDay'
        },
        {
          title: '应出勤天数',
          align: 'center',
          dataIndex: 'totalDay'
        },
        {
          title: '实际出勤天数',
          align: 'center',
          dataIndex: 'actualDay'
        },
        {
          title: '外派天数',
          align: 'center',
          dataIndex: 'dispatchDay'
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
          title: '申请时间',
          align: 'center',
          dataIndex: 'applicationDate',
          customRender: function (text) {
            return !text ? '' : (text.length > 10 ? text.substr(0, 10) : text)
          }
        },
        {
          title: '到期时间',
          align: 'center',
          dataIndex: 'expireDate',
          customRender: function (text) {
            return !text ? '' : (text.length > 10 ? text.substr(0, 10) : text)
          }
        },
        {
          title: '所得税',
          align: 'center',
          dataIndex: 'personIncome'
        },
        {
          title: '应发',
          align: 'center',
          dataIndex: 'actualSalary'
        }
      ],
      url: {
        list: '/salary/salaryStatisticsCopy1/list',
        delete: '/salary/salaryStatisticsCopy1/delete',
        queryByMonth: '/salary/salaryStatisticsCopy1/queryByMonth',
        deleteBatch: '/salary/salaryStatisticsCopy1/deleteBatch',
        exportXlsUrl: '/salary/salaryStatisticsCopy1/exportXls',
        importExcelUrl: 'salary/salaryStatisticsCopy1/importExcel',

      },
      selectMonth: '',
      dictOptions: {},
    }
  },
  created() {
  },
  mounted() {
    let _this = this
    this.timer = setInterval(function () {
      _this.date = new Date().toLocaleString()
    })
  },
  computed: {
    importExcelUrl: function () {
      return `${window._CONFIG['domianURL']}/${this.url.importExcelUrl}`
    },
  },
  methods: {
    simpleDateFormat(selectMonth, format) {
      // formatDate(selectMonth, format)
      getAction(this.url.queryByMonth, { format: format }).then((res) => {
        if (res.success) {
          this.dataSource = res.result
        } else {
          this.dataSource = null
        }
      })

    },
    getDate() {
      getAction(this.url.queryByMonth, { selectMonth: this.selectMonth }).then((res) => {
        // simpleDateFormat(this.selectMonth, format)
        alert(format)
        if (res.success) {
          this.dataSource = res.result
        } else {
          this.dataSource = null
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