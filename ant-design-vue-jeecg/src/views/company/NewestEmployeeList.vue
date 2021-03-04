<template>
  <a-card :bordered="false">

    <!-- 查询区域 -->
    <div class="table-page-search-wrapper">
      <a-form layout="inline" @keyup.enter.native="searchQuery">
        <a-row :gutter="24">
          <a-col :xl="6" :lg="7" :md="8" :sm="24">
            <a-form-item label="姓名">
              <a-input placeholder="请输入姓名" v-model="queryParam.name"></a-input>
            </a-form-item>
          </a-col>
          <a-col :xl="6" :lg="7" :md="8" :sm="24">
            <a-form-item label="客户方">
              <a-input placeholder="请输入客户方" v-model="queryParam.client"></a-input>
            </a-form-item>
          </a-col>
          <a-col :xl="6" :lg="7" :md="8" :sm="24">
            <span style="float: left;overflow: hidden;" class="table-page-search-submitButtons">
              <a-button type="primary" @click="searchQuery" icon="search">查询</a-button>
              <a-button type="primary" @click="searchReset" icon="reload" style="margin-left: 8px">重置</a-button>
            </span>
          </a-col>

        </a-row>
      </a-form>
    </div>

    <!-- 操作按钮区域 -->
    <div class="table-operator">
      <a-button @click="handleAdd" type="primary" icon="plus">新增</a-button>
      <a-button type="primary" icon="download" @click="handleExportXls('清单')">导出</a-button>
      <a-upload name="file" :showUploadList="false" :multiple="false" :headers="tokenHeader" :action="importExcelUrl"
                @change="handleImportExcel">
        <a-button type="primary" icon="import">导入</a-button>
      </a-upload>
      <a-button @click="deleteAll" type="primary" icon="delete">全部删除</a-button>
      <a-dropdown v-if="selectedRowKeys.length > 0">
        <a-menu slot="overlay">
          <a-menu-item key="1" @click="batchDel">
            <a-icon type="delete"/>
            删除
          </a-menu-item>
        </a-menu>
        <!--        <a-button style="margin-left: 8px"> 批量操作-->
        <!--          <a-icon type="down"/>-->
        <!--        </a-button>-->
      </a-dropdown>
    </div>

    <!-- table区域-begin -->
    <div>
      <div class="ant-alert ant-alert-info" style="margin-bottom: 16px;">
        <i class="anticon anticon-info-circle ant-alert-icon"></i>
        <span>已选择</span>
        <a style="font-weight: 600">
          {{ selectedRowKeys.length }}
        </a>
        <span>项</span>
        <a style="margin-left: 24px" @click="onClearSelected">清空</a>
      </div>

      <a-table
        ref="table"
        size="middle"
        bordered
        rowKey="id"
        :columns="columns"
        :dataSource="dataSource"
        :pagination="ipagination"
        :loading="loading"
        :rowSelection="{selectedRowKeys: selectedRowKeys, onChange: onSelectChange,type:type}"
        @change="handleTableChange"
        :customRow="clickThenCheck">

        <span slot="action" slot-scope="text, record">
          <a @click="handleEdit(record)">编辑</a>

          <a-divider type="vertical"/>
          <a-popconfirm title="确定删除吗?" @confirm="() =>handleStatus(record.id)">
            <a>删除</a>
          </a-popconfirm>
        </span>

      </a-table>
    </div>
    <!-- table区域-end -->


    <a-tabs defaultActiveKey="1">
      <a-tab-pane tab="账单" :key="refKeys[0]" :forceRender="true">
        <newestBillingReport-List ref="NewestBillingReportList"></newestBillingReport-List>
      </a-tab-pane>
    </a-tabs>

    <!-- 表单区域 -->
    <newestEmployee-modal ref="modalForm" @ok="modalFormOk"/>

  </a-card>
</template>

<script>
import { JeecgListMixin } from '@/mixins/JeecgListMixin'
import NewestEmployeeModal from './modules/NewestEmployeeModal'
import { deleteAction } from '@/api/manage'
import NewestBillingReportList from './NewestBillingReportList'
import NewestBillingReportModal from './modules/NewestBillingReportModal'
import JMultiSelectTag from '@/components/dict/JMultiSelectTag'
import { getAction } from '@api/add'

export default {
  name: 'NewestEmployeeList',
  mixins: [JeecgListMixin],
  components: {
    NewestEmployeeModal,
    NewestBillingReportModal,
    NewestBillingReportList,
    JMultiSelectTag
  },
  data() {
    return {
      refKeys: ['newestBillingReport',],
      description: '清单管理页面',
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
          title: '岗位',
          align: 'center',
          dataIndex: 'job'
        },
        {
          title: '级别',
          align: 'center',
          dataIndex: 'level'
        },
        {
          title: '薪资',
          align: 'center',
          dataIndex: 'salary'
        },
        {
          title: '单价',
          align: 'center',
          dataIndex: 'price'
        },
        {
          title: '调薪',
          align: 'center',
          dataIndex: 'raiseSalary',
          customRender: function (t, r, index) {
            return (t === 0) ? '' : t
          }
        },
        {
          title: '调薪日',
          align: 'center',
          dataIndex: 'raiseDate'
        },
        {
          title: '入职时间',
          align: 'center',
          dataIndex: 'entryTime'
        },
        {
          title: '入场时间',
          align: 'center',
          dataIndex: 'enterTime'
        },
        {
          title: '离场时间',
          align: 'center',
          dataIndex: 'awayTime'
        },
        {
          title: '客户方',
          align: 'center',
          dataIndex: 'client'
        },
        {
          title: '是否租用电脑',
          align: 'center',
          dataIndex: 'isRentalComputer'
        },
        {
          title: '租用日期',
          align: 'center',
          dataIndex: 'rentalDate'
        },
        {
          title: '订单号',
          align: 'center',
          dataIndex: 'orderNumber'
        },
        {
          title: '费用',
          align: 'center',
          dataIndex: 'cost'
        },
        {
          title: '银行名称',
          align: 'center',
          dataIndex: 'bankName'
        },
        {
          title: '银行卡号',
          align: 'center',
          dataIndex: 'bankCard'
        },
        {
          title: '操作',
          dataIndex: 'action',
          align: 'center',
          scopedSlots: { customRender: 'action' },
        }
      ],

      //操作url
      type: 'radio',
      url: {
        list: '/company/newestEmployee/list',
        delete: '/company/employee/delete',
        status: '/company/newestEmployee/status',
        deleteAll: '/company/employee/deleteAll',
        deleteBatch: '/company/employee/deleteBatch',
        exportXlsUrl: 'company/newestEmployee/exportXls',
        importExcelUrl: 'company/newestEmployee/importExcel',
      },
    }
  },
  computed: {
    importExcelUrl: function () {
      return `${window._CONFIG['domianURL']}/${this.url.importExcelUrl}`
    }
  },
  methods: {
    handleStatus: function (id) {
      var that = this
      getAction(that.url.status, { id: id }).then((res) => {
        if (res.success) {
          that.$message.success(res.message)
          that.loadData()
        } else {
          that.$message.warning(res.message)
        }
      })
    },
    deleteAll() {
      var that = this
      that.$confirm({
        title: '确认删除',
        content: '是否删除全部数据?',
        onOk: function () {
          deleteAction(that.url.deleteAll).then((res) => {
            if (res.success) {
              that.$message.success(res.message)
              that.loadData()
              that.onClearSelected()
            } else {
              that.$message.warning('全部删除失败')
            }
          }).finally(() => {
            that.loading = false
          })
        }
      })
    },
    clickThenCheck(record) {
      return {
        on: {
          click: () => {
            this.onSelectChange(record.id.split(','), [record])
          }
        }
      }
    },
    onSelectChange(selectedRowKeys, selectionRows) {
      this.selectedRowKeys = selectedRowKeys
      this.selectionRows = selectionRows
      this.$refs.NewestBillingReportList.getMain(this.selectedRowKeys[0])
    },
    onClearSelected() {
      this.selectedRowKeys = []
      this.selectionRows = []
      this.$refs.NewestBillingReportList.queryParam.mainId = null
      this.$refs.NewestBillingReportList.loadData()
      this.$refs.NewestBillingReportList.selectedRowKeys = []
      this.$refs.NewestBillingReportList.selectionRows = []
    },

    handleDelete: function (id) {
      var that = this
      deleteAction(that.url.delete, { id: id }).then((res) => {
        if (res.success) {
          that.$message.success(res.message)
          that.loadData()
          this.$refs.NewestBillingReportList.loadData()
        } else {
          that.$message.warning(res.message)
        }
      })
    },
    searchQuery: function () {
      this.selectedRowKeys = []
      this.selectionRows = []
      this.$refs.NewestBillingReportList.queryParam.mainId = null
      this.$refs.NewestBillingReportList.loadData()
      this.$refs.NewestBillingReportList.selectedRowKeys = []
      this.$refs.NewestBillingReportList.selectionRows = []
      this.loadData()
    }
  }
}
</script>
<style scoped>
@import '~@assets/less/common.less'
</style>