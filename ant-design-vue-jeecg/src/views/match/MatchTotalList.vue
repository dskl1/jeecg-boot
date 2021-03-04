<template>
  <a-card :bordered="false" :class="'cust-erp-sub-tab'">

    <!-- 操作按钮区域 -->
    <div class="table-operator" :md="24" :sm="24" style="margin: 0px 0px 20px 0px">

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
        :rowSelection="{selectedRowKeys: selectedRowKeys, onChange: onSelectChange,type:type}"
        @change="handleTableChange"
        :customRow="clickThenCheck">

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

    <a-tabs defaultActiveKey="1">
      <a-tab-pane tab="比赛成绩" :key="refKeys[0]" :forceRender="true">
        <matchScore-List ref="MatchScoreList"></matchScore-List>
      </a-tab-pane>
    </a-tabs>

    <!-- 表单区域 -->
    <MatchTotal-modal ref="modalForm" @ok="modalFormOk"/>
  </a-card>
</template>

<script>
  import MatchTotalModal from './modules/MatchTotalModal'
  import {JeecgListMixin} from '@/mixins/JeecgListMixin'
  import {getAction} from '@/api/manage'
  import MatchScoreList from './MatchScoreList'
  import MatchScoreModal from './modules/MatchScoreModal'

  export default {
    name: "MatchTotalList",
    mixins: [JeecgListMixin],
    components: {
      MatchTotalModal,
      MatchScoreModal,
      MatchScoreList,
    },
    data() {
      return {
        refKeys: ['matchScore',],
        description: '信息',
        // 表头
        columns: [
          {
            title: '比赛名称',
            align: "center",
            dataIndex: 'matchName'
          },
          {
            title: '比赛日期',
            align: "center",
            dataIndex: 'matchDate'
          },
        ],
        type: "radio",
        url: {
          list: "/match/matchMember/listMatchTotalByMainId",
          delete: "/match/matchMember/deleteMatchTotal",
          deleteBatch: "/match/matchMember/deleteBatchMatchTotal",
        }
      }
    },
    methods: {
      clickThenCheck(record) {
        return {
          on: {
            click: () => {
              // var params = this.getQueryParams();

              this.onSelectChange(record.id.split(","), [record]);
            }
          }
        };
      },
      onSelectChange(selectedRowKeys, selectionRows) {
        this.selectedRowKeys = selectedRowKeys;
        this.selectionRows = selectionRows;
        this.$refs.MatchScoreList.getMain(this.selectedRowKeys[0]);
      },
      onClearSelected() {
        this.selectedRowKeys = [];
        this.selectionRows = [];
        this.$refs.MatchScoreList.queryParam.mainId = null;
        this.$refs.MatchScoreList.loadData();
        this.$refs.MatchScoreList.selectedRowKeys = [];
        this.$refs.MatchScoreList.selectionRows = [];
      },

      loadData(arg) {
        if (arg === 1) {
          this.ipagination.current = 1;
        }
        var params = this.getQueryParams();
        getAction(this.url.list, {mainId: params.mainId}).then((res) => {
          if (res.success) {
            this.dataSource = res.result;
          } else {
            this.dataSource = null;
          }
        })
      },
      getMain(mainId) {
        this.queryParam.mainId = mainId;
        this.loadData(1);
      },
      handleAdd: function () {
        this.$refs.modalForm.add(this.queryParam.mainId);
        this.$refs.modalForm.title = "添加比赛名称";
      },
    }
  }
</script>
<style scoped>
  @import '~@assets/less/common.less'
</style>
