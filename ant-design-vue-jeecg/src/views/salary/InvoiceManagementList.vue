<template>
  <a-card class="j-inner-table-wrapper" :bordered="false">

    <!-- 查询区域 begin -->
    <div class="table-page-search-wrapper">
      <a-form layout="inline">
        <a-row :gutter="24">
          <a-col :md="6" :sm="8">
            <a-form-item label="开票编号">
              <a-input placeholder="请输入开票编号" v-model="queryParam.invoiceId"></a-input>
            </a-form-item>
          </a-col>
          <a-col :md="6" :sm="8">
            <a-form-item label="客户方">
              <a-input placeholder="请输入客户方" v-model="queryParam.client"></a-input>
            </a-form-item>
          </a-col>
          <a-col :md="6" :sm="8">
            <a-form-item label="分包公司">
              <a-input placeholder="请输入分包公司" v-model="queryParam.subcontractingCompany"></a-input>
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
    <!-- 查询区域 end -->

    <!-- 操作按钮区域 begin -->
    <div class="table-operator">
      <a-button type="primary" icon="plus" @click="handleAdd">新增</a-button>
      <a-button type="primary" icon="download" @click="handleExportXls('开票管理')">导出</a-button>
      <a-upload name="file" :showUploadList="false" :multiple="false" :headers="tokenHeader" :action="importExcelUrl"
                @change="handleImportExcel">
        <a-button type="primary" icon="import">导入</a-button>
      </a-upload>

      <a-dropdown v-if="selectedRowKeys.length > 0">
        <a-menu slot="overlay">
          <a-menu-item key="1" @click="batchDel">
            <a-icon type="delete"/>
            <span>删除</span>
          </a-menu-item>
          <a-menu-item key="2" @click="confirm">
            <a-icon type="check-square"/>
            <span>确认回款</span>
          </a-menu-item>
        </a-menu>
        <a-button>
          <span>批量操作</span>
          <a-icon type="down"/>
        </a-button>
      </a-dropdown>
    </div>
    <!-- 操作按钮区域 end -->

    <!-- table区域 begin -->
    <div>

      <a-alert type="info" showIcon style="margin-bottom: 16px;">
        <template slot="message">
          <span>已选择</span>
          <a style="font-weight: 600;padding: 0 4px;">{{ selectedRowKeys.length }}</a>
          <span>项</span>
          <a style="margin-left: 24px" @click="onClearSelected">清空</a>
        </template>
      </a-alert>

      <a-table
        ref="table"
        size="middle"
        bordered
        rowKey="id"
        class="j-table-force-nowrap"
        :scroll="{x:true}"
        :loading="loading"
        :columns="columns"
        :dataSource="dataSource"
        :pagination="ipagination"
        :expandedRowKeys="expandedRowKeys"
        :rowSelection="{selectedRowKeys, onChange: onSelectChange}"
        @expand="handleExpand"
        @change="handleTableChange"
      >

        <template slot="invoiceId" slot-scope="record,text,index">
          <div style="color: #00DB00" v-if="text.stage === '1'">
            {{ record }}(已开票)
          </div>
          <div v-else>
            {{ record }}
          </div>
        </template>

        <!-- 内嵌table区域 begin -->
        <template slot="expandedRowRender" slot-scope="record">
          <a-tabs tabPosition="top">
            <!--            <a-tab-pane tab="附票" key="invoiceManagementAttached" forceRender>-->
            <!--              <invoice-management-attached-sub-table :record="record"/>-->
            <!--            </a-tab-pane>-->
            <a-tab-pane tab="对应人员" key="invoiceManagementMember" forceRender>
              <invoice-management-member-sub-table :record="record"/>
            </a-tab-pane>
          </a-tabs>
        </template>
        <!-- 内嵌table区域 end -->

        <template slot="htmlSlot" slot-scope="text">
          <div v-html="text"></div>
        </template>

        <template slot="imgSlot" slot-scope="text">
          <div style="font-size: 12px;font-style: italic;">
            <span v-if="!text">无图片</span>
            <img v-else :src="getImgView(text)" alt="" style="max-width:80px;height:25px;"/>
          </div>
        </template>

        <template slot="fileSlot" slot-scope="text">
          <span v-if="!text" style="font-size: 12px;font-style: italic;">无文件</span>
          <a-button
            v-else
            ghost
            type="primary"
            icon="download"
            size="small"
            @click="downloadFile(text)"
          >
            <span>下载</span>
          </a-button>
        </template>

        <template slot="action" slot-scope="text, record">
          <a @click="handleEdit(record)">编辑</a>
          <a-divider type="vertical"/>
          <a-dropdown>
            <a class="ant-dropdown-link">
              <span>更多 <a-icon type="down"/></span>
            </a>
            <a-menu slot="overlay">
              <a-menu-item>
                <a-popconfirm title="确定删除吗?" @confirm="handleDelete(record.id)">
                  <a>删除</a>
                </a-popconfirm>
              </a-menu-item>
            </a-menu>
          </a-dropdown>

        </template>

      </a-table>
    </div>
    <!-- table区域 end -->

    <!-- 表单区域 -->
    <invoice-management-modal ref="modalForm" @ok="modalFormOk"/>

  </a-card>
</template>

<script>

import { JeecgListMixin } from '@/mixins/JeecgListMixin'
import InvoiceManagementModal from './modules/InvoiceManagementModal'
import InvoiceManagementAttachedSubTable from './subTables/InvoiceManagementAttachedSubTable'
import InvoiceManagementMemberSubTable from './subTables/InvoiceManagementMemberSubTable'
import '@/assets/less/TableExpand.less'
import { getAction, putAction } from '@api/add'

export default {
  name: 'InvoiceManagementList',
  mixins: [JeecgListMixin],
  components: {
    InvoiceManagementModal,
    InvoiceManagementAttachedSubTable,
    InvoiceManagementMemberSubTable
  },
  data() {
    return {
      description: '开票管理列表管理页面',
      // 表头
      columns: [
        {
          title: '#',
          key: 'rowIndex',
          width: 60,
          align: 'center',
          customRender: (t, r, index) => parseInt(index) + 1
        },
        {
          title: '开票编号',
          align: 'center',
          dataIndex: 'invoiceId',
          scopedSlots: { customRender: 'invoiceId' },
          defaultSortOrder: 'descend',
          sorter: (a, b) => b.stage - a.stage,
        },
        {
          title: '开票日期',
          align: 'center',
          dataIndex: 'invoiceDate',
        },
        {
          title: '总金额',
          align: 'center',
          dataIndex: 'totalAmount',
        },
        {
          title: '客户方',
          align: 'center',
          dataIndex: 'client',
        },
        {
          title: '分包公司',
          align: 'center',
          dataIndex: 'subcontractingCompany',
        },
        {
          title: '操作',
          dataIndex: 'action',
          align: 'center',
          fixed: 'right',
          width: 147,
          scopedSlots: { customRender: 'action' },
        },
      ],
      // 字典选项
      dictOptions: {},
      // 展开的行test
      expandedRowKeys: [],
      url: {
        list: '/salary/invoiceManagement/list',
        delete: '/salary/invoiceManagement/delete',
        confirm: '/salary/invoiceManagement/confirm',
        deleteBatch: '/salary/invoiceManagement/deleteBatch',
        exportXlsUrl: '/salary/invoiceManagement/exportXls',
        importExcelUrl: '/salary/invoiceManagement/importExcel',
      },
    }
  },
  computed: {
    importExcelUrl() {
      return window._CONFIG['domianURL'] + this.url.importExcelUrl
    }
  },
  methods: {
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

    initDictConfig() {
    },

    handleExpand(expanded, record) {
      this.expandedRowKeys = []
      if (expanded === true) {
        this.expandedRowKeys.push(record.id)
      }
    },
  }
}
</script>
<style lang="less" scoped>
@import '~@assets/less/common.less';
</style>