<template>
  <a-card :bordered="false">

    <!-- 查询区域 begin -->
    <div class="table-page-search-wrapper">
      <a-form layout="inline">
        <a-row :gutter="24">
          <a-col :md="6" :sm="12">
            <a-form-item label="账号">
              <!--<a-input placeholder="请输入账号查询" v-model="queryParam.username"></a-input>-->
              <j-input placeholder="请输入账号模糊查询" v-model="queryParam.username"></j-input>
            </a-form-item>
          </a-col>
          <!--          <a-col :md="6" :sm="8">-->
          <!--            <a-form-item label="开票编号">-->
          <!--              <a-input placeholder="请输入开票编号" v-model="queryParam.invoiceId"></a-input>-->
          <!--            </a-form-item>-->
          <!--          </a-col>-->
          <!--          <a-col :md="6" :sm="8">-->
          <!--            <a-form-item label="开票编号(分包公司)">-->
          <!--              <a-input placeholder="请输入开票编号(分包公司)" v-model="queryParam.billingId"></a-input>-->
          <!--            </a-form-item>-->
          <!--          </a-col>-->
        </a-row>
      </a-form>
    </div>
    <!-- 查询区域 end -->

    <!-- 操作按钮区域 -->
    <div class="table-operator" :md="24" :sm="24" style="margin: 0px 0px 20px 0px">
      <a-button @click="handleAdd" type="primary" icon="plus">新增</a-button>

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
        <i class="anticon anticon-info-circle ant-alert-icon"></i> 已选择 <a style="font-weight: 600">{{
          selectedRowKeys.length
        }}</a>项
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
        :rowSelection="{selectedRowKeys: selectedRowKeys, onChange: onSelectChange}"
        @change="handleTableChange">

        <span slot="action" slot-scope="text, record">
          <a @click="handleEdit(record)">编辑</a>
          <a-divider type="vertical"/>
          <a-dropdown>
            <a class="ant-dropdown-link">
              更多 <a-icon type="down"/>
            </a>
            <a-menu slot="overlay">
              <a-menu-item>
                <a href="javascript:;" @click="handleDetail(record)">详情</a>
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
    <!-- table区域-end -->

    <!-- 表单区域 -->
    <NewestBillingReport-modal ref="modalForm" @ok="modalFormOk"/>
  </a-card>
</template>

<script>
import NewestBillingReportModal from './modules/NewestBillingReportModal'
import { JeecgListMixin } from '@/mixins/JeecgListMixin'
import { getAction } from '@/api/manage'

export default {
  name: 'NewestBillingReportList',
  mixins: [JeecgListMixin],
  components: {
    NewestBillingReportModal,
  },
  data() {
    return {
      description: '信息',
      // 表头
      columns: [
        {
          title: '开票编号',
          align: 'center',
          dataIndex: 'invoiceId'
        },
        {
          title: '开票时间',
          align: 'center',
          dataIndex: 'invoiceTime'
        },
        {
          title: '(客户方)结算月份',
          align: 'center',
          dataIndex: 'billingMonth'
        },
        {
          title: '开票金额',
          align: 'center',
          dataIndex: 'billingPrice'
        },
        {
          title: '回款日期',
          align: 'center',
          dataIndex: 'paymentMonth'
        },
        {
          title: '是否分包',
          align: 'center',
          dataIndex: 'isSubcontract'
        },
        {
          title: '分包单价',
          align: 'center',
          dataIndex: 'subcontractPrice'
        },
        {
          title: '分包公司',
          align: 'center',
          dataIndex: 'subcontractingCompany'
        },
        {
          title: '(分包公司)开票编号',
          align: 'center',
          dataIndex: 'billingId'
        },
        {
          title: '开票日期(分包公司)',
          align: 'center',
          dataIndex: 'invoicingDay'
        },
        {
          title: '结算月份(分包公司)',
          align: 'center',
          dataIndex: 'isInvoicing'
        },
        {
          title: '开票金额(分包公司)',
          align: 'center',
          dataIndex: 'payment'
        },
        {
          title: '回款日期(分包公司)',
          align: 'center',
          dataIndex: 'billingDate'
        },
        {
          title: '备注',
          align: 'center',
          dataIndex: 'note'
        },
        {
          title: '操作',
          key: 'operation',
          align: 'center',
          width: 130,
          scopedSlots: { customRender: 'action' },
        }],
      url: {
        list: '/company/newestEmployee/listNewestBillingReportByMainId',
        delete: '/company/newestEmployee/deleteNewestBillingReport',
        deleteBatch: '/company/newestEmployee/deleteBatchNewestBillingReport',
      }
    }
  },
  methods: {
    loadData(arg) {
      if (arg === 1) {
        this.ipagination.current = 1
      }
      var params = this.getQueryParams()
      getAction(this.url.list, {
        sportsmanId: params.mainId
        , pageNo: this.ipagination.current, pageSize: this.ipagination.pageSize
      }).then((res) => {
        if (res.success) {
          this.dataSource = res.result.records
          this.ipagination.total = res.result.total
        } else {
          this.dataSource = null
        }
      })
    },
    getMain(mainId) {
      this.queryParam.mainId = mainId
      this.loadData(1)
    },
    handleAdd: function () {
      this.$refs.modalForm.add(this.queryParam.mainId)
      this.$refs.modalForm.title = '添加账单'
    },
  }
}
</script>
<style scoped>
@import '~@assets/less/common.less'
</style>
