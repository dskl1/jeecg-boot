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
      <a-button type="primary" icon="download" @click="handleExportXls('开票统计')">导出</a-button>
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

    <invoicing-statistics-modal ref="modalForm" @ok="modalFormOk"></invoicing-statistics-modal>
  </a-card>
</template>

<script>

import '@/assets/less/TableExpand.less'
import { mixinDevice } from '@/utils/mixin'
import { JeecgListMixin } from '@/mixins/JeecgListMixin'
import InvoicingStatisticsModal from './modules/InvoicingStatisticsModal'
import { getAction } from '@api/manage'

export default {
  name: 'InvoicingStatisticsList',
  mixins: [JeecgListMixin, mixinDevice],
  components: {
    InvoicingStatisticsModal
  },
  data() {
    return {
      monthFormat: 'YYYY-MM',
      description: '开票统计管理页面',
      // 表头
      columns: [
        {
          title: '#',
          width: '180px',
          align: 'center',
          dataIndex: 'name',
          customRender: function (text, r, index) {
            return (text !== '合计') ? (parseInt(index) + 1) : text
          }
        },
        {
          title: '姓名',
          align: 'center',
          dataIndex: 'name',
          customRender: function (text, r, index) {
            return (text === '合计') ? '-' : text
          },

        },
        {
          title: '出勤天数',
          align: 'center',
          dataIndex: 'totalDay',
          customRender: function (text, r, index) {
            return (r.name === '合计') ? '-' : text
          }
        },
        {
          title: '单价',
          align: 'center',
          dataIndex: 'price',
          statistics: ['sum', 'average']
        },
      ],
      /* 分页参数 */
      ipagination:{
        current: 1,
        pageSize: 20,
        pageSizeOptions: ['10', '20', '30'],
        showTotal: (total, range) => {
          return range[0] + "-" + range[1] + " 共" + total + "条"
        },
        showQuickJumper: true,
        showSizeChanger: true,
        total: 0
      },
      url: {
        list: '/salary/invoicingStatistics/list',
        delete: '/salary/invoicingStatistics/delete',
        queryByMonth: '/salary/invoicingStatistics/queryByMonth',
        deleteBatch: '/salary/invoicingStatistics/deleteBatch',
        exportXlsUrl: '/salary/invoicingStatistics/exportXls',
        importExcelUrl: 'salary/invoicingStatistics/importExcel',

      },
      selectMonth: '',
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
    simpleDateFormat(selectMonth, format) {
      getAction(this.url.queryByMonth, { format: format }).then((res) => {
        if (res.success) {
          this.dataSource = res.result
        } else {
          this.dataSource = null
        }
      })

    },
    getFormFieldValue(field) {
      return this.form.getFieldValue(field)
    },
    popupCallback(row) {
      this.form.setFieldsValue(row)
    },
    initDictConfig() {
    }
  }
}
</script>
<style scoped>
@import '~@assets/less/common.less';
</style>