<template>
  <a-card :bordered="false">
    <!-- 查询区域 -->
    <div class="table-page-search-wrapper">
      <a-form layout="inline" @keyup.enter.native="searchQuery">
        <a-row :gutter="24">
          <a-col :md="6" :sm="8">
            <a-form-item label="姓名">
              <a-input placeholder="请输入姓名" v-model="queryParam.invoiceMember"></a-input>
            </a-form-item>
          </a-col>
          <a-col :md="6" :sm="8">
            <a-form-item label="日期">
              <j-date placeholder="请输入日期" v-model="queryParam.invoiceDate" style="width: 100%"></j-date>
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
      <a-button type="primary" icon="download" @click="handleExportXls('未开票')">导出</a-button>
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
          <a-menu-item key="2" @click="confirm">
            <a-icon type="check-square"/>
            确认开票
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

        <template slot="invoiceIdd" slot-scope="record,text,index">
          <div style="color: #00DB00" v-if="text.stage === '1'">
            {{ record }}(已开票)
          </div>
          <div v-else>
            {{ record }}
          </div>
        </template>

        <template slot="invoiceId" slot-scope="record,text,index">
          <div style="color: #00DB00" v-if="text.stage === '1'">
            {{ record }}
          </div>
          <div v-else>
            {{ record }}
          </div>
        </template>

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

    <no-invoice-modal ref="modalForm" @ok="modalFormOk"></no-invoice-modal>
  </a-card>
</template>

<script>

import '@/assets/less/TableExpand.less'
import { mixinDevice } from '@/utils/mixin'
import { JeecgListMixin } from '@/mixins/JeecgListMixin'
import NoInvoiceModal from './modules/NoInvoiceModal'
import { getAction } from '../../api/add'
import JDate from '@/components/jeecg/JDate'

export default {
  name: 'NoInvoiceList',
  mixins: [JeecgListMixin, mixinDevice],
  components: {
    NoInvoiceModal,
    JDate
  },
  data() {
    return {
      description: '未开票管理页面',
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
          title: '人员',
          align: 'center',
          dataIndex: 'invoiceMember',
          scopedSlots: { customRender: 'invoiceIdd' },
          sorter: (a, b) => b.stage - a.stage,
        },
        {
          title: '开票日期',
          align: 'center',
          dataIndex: 'invoiceDate',
          // customRender: function (text) {
          //   return !text ? '' : (text.length > 10 ? text.substr(0, 10) : text)
          // },
          scopedSlots: { customRender: 'invoiceId' }
        },
        {
          title: '预计开票额',
          align: 'center',
          dataIndex: 'forecastInvoiceAmount',
          scopedSlots: { customRender: 'invoiceId' }
        },
        {
          title: '开票编号',
          align: 'center',
          dataIndex: 'invoiceId',
          scopedSlots: { customRender: 'invoiceId' }
        },
        {
          title: '实际开票额',
          align: 'center',
          dataIndex: 'realInvoiceAmount',
          scopedSlots: { customRender: 'invoiceId' }
        },
        {
          title: '操作',
          dataIndex: 'action',
          align: 'center',
          fixed: 'right',
          width: 147,
          scopedSlots: { customRender: 'action' }
        }
      ],
      url: {
        list: '/salary/noInvoice/list',
        delete: '/salary/noInvoice/delete',
        deleteBatch: '/salary/noInvoice/deleteBatch',
        confirm: '/salary/noInvoice/confirm',
        exportXlsUrl: '/salary/noInvoice/exportXls',
        importExcelUrl: 'salary/noInvoice/importExcel',

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
    initDictConfig() {
    },
    confirm() {
      var ids = ''
      for (var a = 0; a < this.selectedRowKeys.length; a++) {
        ids += this.selectedRowKeys[a] + ','
      }
      getAction(this.url.confirm, { ids: ids }).then((res) => {
        if (res.success) {
          this.$message.success(res.message)
          this.loadData(this.record)
        } else {
          this.$message.warning('发送失败')
        }
      })
    },
  }
}
</script>
<style scoped>
@import '~@assets/less/common.less';
</style>