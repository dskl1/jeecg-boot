<template>
  <a-card :bordered="false">

    <!-- 查询区域 -->
    <div class="table-page-search-wrapper">
      <a-form layout="inline" @keyup.enter.native="searchQuery">
        <a-row :gutter="24">

          <a-col :md="6" :sm="8">
            <a-form-item label="deviceid">
              <a-input placeholder="请输入deviceid" v-model="queryParam.deviceId"></a-input>
            </a-form-item>
          </a-col>
          <a-col :md="6" :sm="8">
            <a-form-item label="status">
              <a-input placeholder="请输入status" v-model="queryParam.status"></a-input>
            </a-form-item>
          </a-col>
          <a-col :md="6" :sm="8">
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
        :columns="columns"
        :dataSource="dataSource"
        :pagination="ipagination"
        :loading="loading"
        :rowSelection="{selectedRowKeys: selectedRowKeys, onChange: onSelectChange}"
        @change="handleTableChange">

        <span slot="time" slot-scope="text, record">
          <template v-if="record.status===0">
            <template v-if="record.createTime"></template>
          </template>
        </span>

        <span slot="action" slot-scope="text, record">
          <a @click="handleEdit(record)">编辑</a>

          <a-divider type="vertical"/>
          <a-dropdown>
            <a class="ant-dropdown-link">更多 <a-icon type="down"/></a>
            <a-menu slot="overlay">
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
    <restApi-modal ref="modalForm" @ok="modalFormOk"></restApi-modal>
  </a-card>
</template>

<script>
  import RestApiModal from './modules/RestApiModal'
  import {JeecgListMixin} from '@/mixins/JeecgListMixin'

  export default {
    name: "RestApiList",
    mixins: [JeecgListMixin],
    components: {
      RestApiModal
    },
    data() {
      return {
        description: 'rest管理页面',
        props: {

        },
        // 表头
        columns: [
          {
            title: '#',
            dataIndex: '',
            key: 'rowIndex',
            width: 60,
            align: "center",
            customRender: function (t, r, index) {
              return parseInt(index) + 1;
            }
          },
          {
            title: '服务',
            align: "center",
            dataIndex: 'deviceId',
            // scopedSlots: {customRender: 'info'},
          },
          {
            title: '设备',
            align: "center",
            dataIndex: 'nfcId',
            // scopedSlots: {customRender: 'info'},
          },
          {
            title: '状态',
            align: "center",
            dataIndex: 'status',
            // scopedSlots: {customRender: 'info'},
          },
          {
            title: '用时',
            align: "center",
            dataIndex: 'subSecond',
          },
          {
            title: '创建时间',
            align: "center",
            dataIndex: 'createTime',
            sortDirections: ['descend', 'ascend'],
            defaultSortOrder: 'descend',
            sorter: (a, b) => a.createTime - b.createTime,
          },
        ],
        url: {
          list: "/restapi/restApiCopy1/list",
          delete: "/restapi/restApiCopy1/delete",
          deleteBatch: "/restapi/restApiCopy1/deleteBatch",
          exportXlsUrl: "restapi/restApiCopy1/exportXls",
          importExcelUrl: "restapi/restApiCopy1/importExcel",
        },
      }
    },
    computed: {
      importExcelUrl: function () {
        return `${window._CONFIG['domianURL']}/${this.url.importExcelUrl}`;
      }
    },
    methods: {}
  }
</script>
<style scoped>
  @import '~@assets/less/common.less'
</style>