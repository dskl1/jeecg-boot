<template>
  <a-card :bordered="false">

    <!-- 查询区域 -->
    <div class="table-page-search-wrapper">
      <a-form layout="inline">
        <a-row :gutter="24">
          <a-col :md="6" :sm="24">
            <a-form-item label="比赛名称">
              <a-input placeholder="请输入比赛名称" v-model="queryParam.matchName"></a-input>
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

    <!-- 操作按钮区域 -->
    <div class="table-operator">
      <a-button @click="handleAdd" type="primary" icon="plus">新增</a-button>
      <a-button type="primary" icon="download" @click="handleExportXls('比赛计划')">导出</a-button>
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

        <template slot="avatarslot" slot-scope="text, record, index">
          <div class="anty-img-wrap">
            <img :src="getAvatarView(record.avatar)"/>
          </div>
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

    <a-tabs defaultActiveKey="1">
      <a-tab-pane tab="比赛队员" :key="refKeys[0]" :forceRender="true">
        <match-memberp-List ref="MatchMemberpList"></match-memberp-List>
      </a-tab-pane>
    </a-tabs>

    <!-- 表单区域 -->
    <matchPlan-modal ref="modalForm" @ok="modalFormOk"></matchPlan-modal>
  </a-card>
</template>

<script>
  import MatchPlanModal from './modules/MatchPlanModal'
  import {JeecgListMixin} from '@/mixins/JeecgListMixin'
  import MatchMemberpList from "./MatchMemberpList";
  import MatchMemberpModal from "./modules/MatchMemberpModal";

  export default {
    name: "MatchPlanList",
    mixins: [JeecgListMixin],
    components: {
      MatchMemberpList,
      MatchPlanModal,
      MatchMemberpModal
    },
    data() {
      return {
        refKeys: ['matchMember',],
        description: '比赛计划管理页面',
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
            title: '比赛名称',
            align: "center",
            dataIndex: 'matchName',
            width: 150,
          },
          {
            title: '比赛日期',
            align: "center",
            children: [
              {
                title: '开始日期',
                align: "center",
                dataIndex: 'matchDate',
                width: 150,
                sorter: (a, b) => a.matchDate - b.matchDate,
              },
              {
                title: '结束日期',
                align: "center",
                dataIndex: 'endDate',
                width: 150,
              },
            ]
          },
          {
            title: '参赛人员',
            align: "center",
            dataIndex: 'matchItem'
          },
          {
            title: '操作',
            dataIndex: 'action',
            align: "center",
            width: 150,
            scopedSlots: {customRender: 'action'},
          }
        ],
        url: {
          imgerver: window._CONFIG['domianURL'] + "/sys/common/view",
          list: "/match/matchPlan/list",
          delete: "/match/matchPlan/delete",
          deleteBatch: "/match/matchPlan/deleteBatch",
          exportXlsUrl: "match/matchPlan/exportXls",
          importExcelUrl: "match/matchPlan/importExcel",
        },
      }
    },
    computed: {
      importExcelUrl: function () {
        return `${window._CONFIG['domianURL']}/${this.url.importExcelUrl}`;
      }
    },
    methods: {
      onSelectChange(selectedRowKeys, selectionRows) {
        this.selectedRowKeys = selectedRowKeys;
        this.selectionRows = selectionRows;
        // alert(this.selectedRowKeys)
      },
      getAvatarView: function (avatar) {
        return this.url.imgerver + "/" + avatar;
      },
    }
  }
</script>
<style scoped>
  /** Button按钮间距 */
  .ant-btn {
    margin-left: 3px
  }

  .ant-card-body .table-operator {
    margin-bottom: 18px;
  }

  .ant-table-tbody .ant-table-row td {
    padding-top: 15px;
    padding-bottom: 15px;
  }

  .anty-row-operator button {
    margin: 0 5px
  }

  .ant-btn-danger {
    background-color: #ffffff
  }

  .ant-modal-cust-warp {
    height: 100%
  }

  .ant-modal-cust-warp .ant-modal-body {
    height: calc(100% - 110px) !important;
    overflow-y: auto
  }

  .ant-modal-cust-warp .ant-modal-content {
    height: 90% !important;
    overflow-y: hidden
  }

  .anty-img-wrap {
    height: 50px;
    position: relative;
    width: 50px;
    text-align: center;
    border-radius: 50%;
    overflow: hidden;

  img {
    height: 100%;
    width: 100%;
  }

  }

  .anty-img-wrap > img {
    max-height: 100%;
  }
</style>