<template>
  <a-card :bordered="false" :class="'cust-erp-sub-tab'">
    <!-- 操作按钮区域 -->
    <div class="table-operator">
      <a-button v-if="mainId" @click="handleAdd" type="primary" icon="plus">新增</a-button>
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
        selectedRowKeys.length }}</a>项
        <a style="margin-left: 24px" @click="onClearSelected">清空</a>
      </div>

      <a-table
        ref="table"
        size="middle"
        bordered
        rowKey="id"
        rowName="name"
        :columns="columns"
        :dataSource="dataSource"
        :pagination="ipagination"
        :loading="loading"
        :rowSelection="{selectedRowKeys: selectedRowKeys,selectedRowNames: selectedRowNames, onChange: onSelectChange}"
        @change="handleTableChange">

        <template slot="htmlSlot" slot-scope="text">
          <div v-html="text"></div>
        </template>
        <template slot="imgSlot" slot-scope="text">
          <span v-if="!text" style="font-size: 12px;font-style: italic;">无此图片</span>
          <img v-else :src="getImgView(text)" height="25px" alt="图片不存在"
               style="max-width:80px;font-size: 12px;font-style: italic;"/>
        </template>
        <template slot="fileSlot" slot-scope="text">
          <span v-if="!text" style="font-size: 12px;font-style: italic;">无此文件</span>
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

    <testWord-modal ref="modalForm" @ok="modalFormOk" :mainId="mainId"></testWord-modal>
  </a-card>
</template>

<script>

  import { JeecgListMixin } from '@/mixins/JeecgListMixin'
  import TestWordModal from './modules/TestWordModal'

  export default {
    name: 'TestWordList',
    mixins: [JeecgListMixin],
    components: { TestWordModal },
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
        handler (val) {
          if (!this.mainId) {
            this.clearList()
          } else {
            this.queryParam['sportsmanId'] = val
            this.loadData(1)
          }
        }
      },
    },
    data () {
      return {
        description: '简历管理页面',
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
            title: '运动员id',
            align: 'center',
            dataIndex: 'sportsmanId'
          },
          {
            title: '操作',
            dataIndex: 'action',
            align: 'center',
            scopedSlots: { customRender: 'action' },
          }
        ],
        url: {
          list: '/company/basicSituation/listTestWordByMainId',
          delete: '/company/basicSituation/deleteTestWord',
          deleteBatch: '/company/basicSituation/deleteBatchTestWord'
        },
        dictOptions: {
          sex: [],
        },
        name:'',
      }
    },
    methods: {
      clearList () {
        this.dataSource = []
        this.selectedRowKeys = []
        this.selectedRowNames = []
        this.ipagination.current = 1
      },
    }
  }
</script>
<style scoped>
  @import '~@assets/less/common.less'
</style>
