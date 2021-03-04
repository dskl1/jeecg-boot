<template>
  <a-card :bordered="false" :class="'cust-erp-sub-tab'">
    <!-- 操作按钮区域 -->
    <div class="table-operator" v-if="mainId">
      <a-button @click="handleAdd" type="primary" icon="plus">新增</a-button>
      <a-button type="primary" icon="download" @click="handleExportXls('成绩')">导出</a-button>
      <a-upload
        name="file"
        :showUploadList="false"
        :multiple="false"
        :headers="tokenHeader"
        :action="importExcelUrl"
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
            @click="uploadFile(text)">
            下载
          </a-button>
        </template>

        <span slot="action" slot-scope="text, record">
          <a @click="handleEdit(record)">编辑</a>
          <a-divider type="vertical"/>
          <a-popconfirm title="确定删除吗?" @confirm="() => handleDelete(record.id)">
            <a>删除</a>
          </a-popconfirm>
        </span>

      </a-table>
    </div>

    <trainingScoree-modal ref="modalForm" @ok="modalFormOk" :mainId="mainId"></trainingScoree-modal>
  </a-card>
</template>

<script>

import { JeecgListMixin } from '@/mixins/JeecgListMixin'
import TrainingScoreeModal from './modules/TrainingScoreeModal'
import { getAction } from '@/api/manage'

export default {
  name: 'TrainingScoreeList',
  mixins: [JeecgListMixin],
  components: { TrainingScoreeModal },
  props: {
    mainId: {
      type: String,
      default: '',
      required: false
    }
  },
  watch: {
    mainId: {
      immediate: true,
      handler(val) {
        if (!this.mainId) {
          this.clearList()
        } else {
          this.queryParam['sportsmanId'] = val
          this.loadData(1)
          this.selectRow()
        }
      }
    }
  },
  data() {
    return {
      description: '25管理页面',
      disableMixinCreated: true,
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
          title: '成绩',
          align: 'center',
          dataIndex: 'trainingScore',
          customRender: function (t, r, index) {
            // return String(parseInt(r.trainingScore / 1000 % 10)) + parseInt(r.trainingScore / 100 % 10) + ':' + parseInt(r.trainingScore / 10 % 10) + parseInt(r.trainingScore % 10)
            return String(parseInt(r.trainingScore / 10000 % 10)) + ':' + parseInt(r.trainingScore / 1000 % 10) + parseInt(r.trainingScore / 100 % 10) + ':' + parseInt(r.trainingScore / 10 % 10) + parseInt(r.trainingScore % 10)

          }
        },
        {
          title: '操作',
          dataIndex: 'action',
          align: 'center',
          scopedSlots: { customRender: 'action' },
        }
      ],
      url: {
        list: '/training/d25/listTrainingScoreeByMainId',
        delete: '/training/d25/deleteTrainingScoree',
        deleteBatch: '/training/d25/deleteBatchTrainingScoree',
        exportXlsUrl: '/training/d25/exportTrainingScoree',
        importUrl: '/training/d25/importTrainingScoree',
      },
      dictOptions: {},

    }
  },
  computed: {
    importExcelUrl() {
      return `${window._CONFIG['domianURL']}/${this.url.importUrl}/${this.mainId}`
    }
  },
  methods: {
    clearList() {
      this.dataSource = []
      this.selectedRowKeys = []
      this.ipagination.current = 1

    },
    selectRow() {
      getAction(this.url.list, { mainId: this.mainId }).then((res) => {
        if (res.success) {
          this.dataSource = res.result
        } else {
          this.dataSource = null
        }
      })
    },
  }
}
</script>
<style scoped>
@import '~@assets/less/common.less'
</style>
