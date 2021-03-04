<template>
  <a-card :bordered="false">

    <!-- 查询区域 -->
    <div class="table-page-search-wrapper">
      <a-form layout="inline">
        <a-row :gutter="24">

          <a-col :md="6" :sm="8">
            <a-form-item label="运动员id">
              <a-input placeholder="请输入运动员id" v-model="queryParam.sportsmanId"></a-input>
            </a-form-item>
          </a-col>
          <a-col :md="6" :sm="8">
            <a-form-item label="姓名">
              <a-input placeholder="请输入姓名" v-model="queryParam.name"></a-input>
            </a-form-item>
          </a-col>
        <template v-if="toggleSearchStatus">
        <a-col :md="6" :sm="8">
            <a-form-item label="训练日期">
              <a-input placeholder="请输入训练日期" v-model="queryParam.trainingDate"></a-input>
            </a-form-item>
          </a-col>
          <a-col :md="6" :sm="8">
            <a-form-item label="时间段">
              <a-input placeholder="请输入时间段" v-model="queryParam.period"></a-input>
            </a-form-item>
          </a-col>
          <a-col :md="6" :sm="8">
            <a-form-item label="训练小组">
              <a-input placeholder="请输入训练小组" v-model="queryParam.trainingGroupId"></a-input>
            </a-form-item>
          </a-col>
          </template>
          <a-col :md="6" :sm="8" >
            <span style="float: left;overflow: hidden;" class="table-page-search-submitButtons">
              <a-button type="primary" @click="searchQuery" icon="search">查询</a-button>
              <a-button type="primary" @click="searchReset" icon="reload" style="margin-left: 8px">重置</a-button>
              <a @click="handleToggleSearch" style="margin-left: 8px">
                {{ toggleSearchStatus ? '收起' : '展开' }}
                <a-icon :type="toggleSearchStatus ? 'up' : 'down'"/>
              </a>
            </span>
          </a-col>

        </a-row>
      </a-form>
    </div>

    <!-- 操作按钮区域 -->
    <div class="table-operator">
      <a-button @click="handleAdd" type="primary" icon="plus">新增</a-button>
      <a-button type="primary" icon="download" @click="handleExportXls('ma')">导出</a-button>
      <a-upload name="file" :showUploadList="false" :multiple="false" :headers="tokenHeader" :action="importExcelUrl" @change="handleImportExcel">
        <a-button type="primary" icon="import">导入</a-button>
      </a-upload>
      <a-dropdown v-if="selectedRowKeys.length > 0">
        <a-menu slot="overlay">
          <a-menu-item key="1" @click="batchDel"><a-icon type="delete"/>删除</a-menu-item>
        </a-menu>
        <a-button style="margin-left: 8px"> 批量操作 <a-icon type="down" /></a-button>
      </a-dropdown>
    </div>

    <!-- table区域-begin -->
    <div>
      <div class="ant-alert ant-alert-info" style="margin-bottom: 16px;">
        <i class="anticon anticon-info-circle ant-alert-icon"></i> 已选择 <a style="font-weight: 600">{{ selectedRowKeys.length }}</a>项
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

          <a-divider type="vertical" />
          <a-dropdown>
            <a class="ant-dropdown-link">更多 <a-icon type="down" /></a>
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
    <ma-modal ref="modalForm" @ok="modalFormOk"></ma-modal>
  </a-card>
</template>

<script>
  import MaModal from './modules/MaModal'
  import { JeecgListMixin } from '@/mixins/JeecgListMixin'

  export default {
    name: "MaList",
    mixins:[JeecgListMixin],
    components: {
      MaModal
    },
    data () {
      return {
        description: 'ma管理页面',
        // 表头
        columns: [
          {
            title: '#',
            dataIndex: '',
            key:'rowIndex',
            width:60,
            align:"center",
            customRender:function (t,r,index) {
              return parseInt(index)+1;
            }
           },
		   {
            title: '运动员id',
            align:"center",
            dataIndex: 'sportsmanId'
           },
		   {
            title: '姓名',
            align:"center",
            dataIndex: 'name'
           },
		   {
            title: '训练日期',
            align:"center",
            dataIndex: 'trainingDate'
           },
		   {
            title: '时间段',
            align:"center",
            dataIndex: 'period'
           },
		   {
            title: '训练小组',
            align:"center",
            dataIndex: 'trainingGroupId'
           },
		   {
            title: '训练序号',
            align:"center",
            dataIndex: 'trainingItemNo'
           },
		   {
            title: '训练项目',
            align:"center",
            dataIndex: 'trainingItem'
           },
		   {
            title: '距离',
            align:"center",
            dataIndex: 'distance'
           },
		   {
            title: '组数',
            align:"center",
            dataIndex: 'groupNo'
           },
		   {
            title: '教练',
            align:"center",
            dataIndex: 'coachName'
           },
		   {
            title: '成绩',
            align:"center",
            dataIndex: 'trainingScore'
           },
		   {
            title: '分',
            align:"center",
            dataIndex: 'minute'
           },
		   {
            title: '秒',
            align:"center",
            dataIndex: 'second'
           },
		   {
            title: '毫秒',
            align:"center",
            dataIndex: 'miniSecond'
           },
		   {
            title: '辅助道具',
            align:"center",
            dataIndex: 'supportItem'
           },
          {
            title: '操作',
            dataIndex: 'action',
            align:"center",
            scopedSlots: { customRender: 'action' },
          }
        ],
		url: {
          list: "/mayanfan/ma/list",
          delete: "/mayanfan/ma/delete",
          deleteBatch: "/mayanfan/ma/deleteBatch",
          exportXlsUrl: "mayanfan/ma/exportXls",
          importExcelUrl: "mayanfan/ma/importExcel",
       },
    }
  },
  computed: {
    importExcelUrl: function(){
      return `${window._CONFIG['domianURL']}/${this.url.importExcelUrl}`;
    }
  },
    methods: {

    }
  }
</script>
<style scoped>
  @import '~@assets/less/common.less'
</style>
