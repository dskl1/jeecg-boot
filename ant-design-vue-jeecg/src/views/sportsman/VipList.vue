<template>
  <a-card :bordered="false">

    <!-- 查询区域 -->
    <div class="table-page-search-wrapper">
      <a-form layout="inline" @keyup.enter.native="searchQuery">
        <a-row :gutter="24">

          <a-col :md="6" :sm="8">
            <a-form-item label="会员姓名">
              <a-input placeholder="请输入会员姓名" v-model="queryParam.name"></a-input>
            </a-form-item>
          </a-col>
          <a-col :md="6" :sm="8">
            <a-form-item label="会员类型">
              <j-dict-select-tag v-model="queryParam.vipType" placeholder="请选择会员类型"
                                 dictCode="vip_card"/>
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
      <a-button type="primary" icon="download" @click="handleExportXls('会员管理')">导出</a-button>
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

        <template slot="date" slot-scope="text, record">
          <template v-if="record.vipType_dictText==='10次卡'">
            {{ record.two }}
          </template>
          <template v-if="record.vipType_dictText==='20次卡'">
            {{ record.three }}
          </template>
          <template v-if="record.vipType_dictText==='培训'">
            {{ record.two }}
          </template>
          <template v-if="record.vipType_dictText==='月卡'">
            {{ record.one }}
          </template>
          <template v-if="record.vipType_dictText==='年卡'">
            {{ record.twelve }}
          </template>
        </template>

        <template slot="join" slot-scope="text, record">
          <template v-if="record.participate<20">
            <span style="color: red;">{{ record.participate + '%' }}</span>
          </template>
          <span v-else>{{ record.participate + '%' }}</span>
        </template>

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
    <vip-modal ref="modalForm" @ok="modalFormOk"></vip-modal>
  </a-card>
</template>

<script>
import VipModal from './modules/VipModal'
import { JeecgListMixin } from '@/mixins/JeecgListMixin'

export default {
  name: 'VipList',
  mixins: [JeecgListMixin],
  components: {
    VipModal
  },
  data() {
    return {
      description: '会员管理管理页面',
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
          title: '会员姓名',
          align: 'center',
          dataIndex: 'name'
        },
        {
          title: '开卡日期',
          align: 'center',
          dataIndex: 'startTime'
        },
        {
          title: '到期时间',
          align: 'center',
          dataIndex: 'vipDate',
          scopedSlots: { customRender: 'date' },
        },
        {
          title: '会员次数',
          align: 'center',
          dataIndex: 'vipTime'
        },
        {
          title: '会员类型',
          align: 'center',
          dataIndex: 'vipType_dictText'
        },
        {
          title: '会员积分',
          align: 'center',
          dataIndex: 'memberPoint'
        },
        {
          title: '出勤率',
          align: 'center',
          dataIndex: 'participate',
          scopedSlots: { customRender: 'join' },
        },
        {
          title: '操作',
          dataIndex: 'action',
          align: 'center',
          scopedSlots: { customRender: 'action' },
        }
      ],
      url: {
        list: '/sportsman/vip/list',
        delete: '/sportsman/vipAdd/delete',
        deleteBatch: '/sportsman/vipAdd/deleteBatch',
        exportXlsUrl: 'sportsman/vip/exportXls',
        importExcelUrl: 'sportsman/vip/importExcel',
      },
    }
  },
  computed: {
    importExcelUrl: function () {
      return `${window._CONFIG['domianURL']}/${this.url.importExcelUrl}`
    }
  },
  methods: {}
}
</script>
<style scoped>
@import '~@assets/less/common.less'
</style>