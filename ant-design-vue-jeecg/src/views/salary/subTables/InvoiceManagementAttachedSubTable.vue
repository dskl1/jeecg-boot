<template>
  <!-- 操作按钮区域 begin -->
  <div class="table-operator">
    <a-dropdown v-if="selectedRowKeys.length > 0">
      <a-menu slot="overlay">
        <a-menu-item key="1" @click="confirm">
          <a-icon type="check-square"/>
          确认回款
        </a-menu-item>
      </a-menu>
      <a-button style="margin-left: 8px"> 批量操作
        <a-icon type="down"/>
      </a-button>
    </a-dropdown>
    <!-- 操作按钮区域 end -->

    <a-table
      rowKey="id"
      size="middle"
      bordered
      :loading="loading"
      :columns="columns"
      :rowSelection="{selectedRowKeys, onChange: onSelectChange}"
      :dataSource="dataSource"
      :pagination="false"
    >

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

    </a-table>
  </div>
</template>

<script>
import { deleteAction, getAction } from '@api/manage'
import { JeecgListMixin } from '@/mixins/JeecgListMixin'
import { cloneObject } from '@/utils/util'

export default {
  name: 'InvoiceManagementAttachedSubTable',
  mixins: [JeecgListMixin],
  props: {
    record: {
      type: Object,
      default: null,
    }
  },
  data() {
    return {
      description: '附票内嵌列表',
      disableMixinCreated: true,
      loading: false,
      dataSource: [],
      columns: [
        {
          title: '开票编号',
          align: 'center',
          dataIndex: 'invoiceId',
          scopedSlots: { customRender: 'invoiceId' },
        },
        {
          title: '开票日期',
          align: 'center',
          dataIndex: 'invoiceDate',
        },
        {
          title: '开票额',
          align: 'center',
          dataIndex: 'invoiceAmount',
        },
        {
          title: '客户方',
          align: 'center',
          dataIndex: 'client',
        },
      ],
      url: {
        listByMainId: '/salary/invoiceManagement/queryInvoiceManagementAttachedByMainId',
        confirms: '/salary/invoiceManagement/confirms',
      },
    }
  },
  watch: {
    record: {
      immediate: true,
      handler() {
        if (this.record != null) {
          this.loadData(this.record)
        }
      }
    }
  },
  methods: {
    confirm() {
      var ids = ''
      for (var a = 0; a < this.selectedRowKeys.length; a++) {
        ids += this.selectedRowKeys[a] + ','
      }
      getAction(this.url.confirms, { ids: ids }).then((res) => {
        if (res.success) {
          this.$message.success(res.message)
          this.loadData(this.record)
        } else {
          this.$message.warning('发送失败')
        }
      })
    },

    loadData(record) {
      this.loading = true
      this.dataSource = []
      getAction(this.url.listByMainId, {
        id: record.id
      }).then((res) => {
        if (res.success) {
          this.dataSource = res.result.records
        }
      }).finally(() => {
        this.loading = false
      })
    },

  },
}
</script>

<style scoped>

</style>
