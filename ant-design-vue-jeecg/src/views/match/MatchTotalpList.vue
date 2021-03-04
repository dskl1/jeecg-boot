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
      <a-tab-pane tab="项目" :key="refKeys[0]" :forceRender="true">
        <matchScorep-List ref="MatchScorepList"></matchScorep-List>
      </a-tab-pane>
    </a-tabs>

    <!-- 表单区域 -->
    <MatchTotalp-modal ref="modalForm" @ok="modalFormOk"/>
  </a-card>
</template>

<script>
    import MatchTotalpModal from './modules/MatchTotalpModal'
    import {JeecgListMixin} from '@/mixins/JeecgListMixin'
    import {getAction} from '@/api/manage'
    import MatchScorepList from './MatchScorepList'
    import MatchScorepModal from './modules/MatchScorepModal'
    import {deleteAction} from "../../api/manage";

    export default {
        name: "MatchTotalpList",
        mixins: [JeecgListMixin],
        components: {
            MatchTotalpModal,
            MatchScorepModal,
            MatchScorepList,
        },
        data() {
            return {
                refKeys: ['matchScorep',],
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
                    {
                        title: '操作',
                        key: 'operation',
                        align: "center",
                        width: 130,
                        scopedSlots: {customRender: 'action'},
                    }],
                type: "radio",
                url: {
                    list: "/match/matchMemberp/listMatchTotalpByMainId",
                    delete: "/match/matchMemberp/deleteMatchTotalp",
                    deleteBatch: "/match/matchMemberp/deleteBatchMatchTotalp",
                }
            }
        },
        methods: {
            clickThenCheck(record) {
                return {
                    on: {
                        click: () => {
                            this.onSelectChange(record.id.split(","), [record]);
                        }
                    }
                };
            },
            onSelectChange(selectedRowKeys, selectionRows) {
                this.selectedRowKeys = selectedRowKeys;
                this.selectionRows = selectionRows;
                this.$refs.MatchScorepList.getMain(this.selectedRowKeys[0]);
            },
            onClearSelected() {
                this.selectedRowKeys = [];
                this.selectionRows = [];
                this.$refs.MatchScorepList.queryParam.mainId = null;
                this.$refs.MatchScorepList.loadData();
                this.$refs.MatchScorepList.selectedRowKeys = [];
                this.$refs.MatchScorepList.selectionRows = [];
            },

            handleDelete: function (id) {
                var that = this;
                deleteAction(that.url.delete, {id: id}).then((res) => {
                    if (res.success) {
                        that.$message.success(res.message);
                        that.loadData();
                        this.$refs.MatchScorepList.loadData();
                    } else {
                        that.$message.warning(res.message);
                    }
                });
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
                this.$refs.modalForm.title = "添加比赛";
            },
        }
    }
</script>
<style scoped>
  @import '~@assets/less/common.less'
</style>
