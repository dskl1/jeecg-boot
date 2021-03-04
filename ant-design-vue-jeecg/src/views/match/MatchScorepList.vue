<template>
  <a-card :bordered="false" :class="'cust-erp-sub-tab'">

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

        <span slot="date" slot-scope="text,record">
          <template v-if="record.changCi===1||record.changCi===2">{{record.matchDate}}</template>
          <template v-if="record.changCi===3||record.changCi===4">{{record.matchDate+1}}</template>
          <template v-if="record.changCi===5||record.changCi===6">{{record.matchDate}}2</template>
        </span>

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
    <MatchScorep-modal ref="modalForm" @ok="modalFormOk"/>
  </a-card>
</template>

<script>
  import MatchScorepModal from './modules/MatchScorepModal'
  import {JeecgListMixin} from '@/mixins/JeecgListMixin'
  import {getAction} from '@/api/manage'

  export default {
    name: "MatchScorepList",
    mixins: [JeecgListMixin],
    components: {
      MatchScorepModal,
    },
    data() {
      return {
        description: '信息',
        // 表头
        columns: [
          {
            title: '比赛项目',
            align: "center",
            dataIndex: 'matchItem'
          },
          {
            title: '比赛日期',
            align: "center",
            width: 100,
            dataIndex: 'matchDate',
            // scopedSlots: {customRender: 'date'},
          },
          {
            title: '比赛阶段',
            align: "center",
            width: 100,
            dataIndex: 'matchName'
          },
          {
            title: '场次',
            align: "center",
            dataIndex: 'changCi',
          },
          {
            title: '项次',
            align: "center",
            dataIndex: 'xiangCi'
          },
          {
            title: '道次',
            align: "center",
            dataIndex: 'daoCi'
          }],
        url: {
          list: "/match/matchTotal/listMatchScoreByMainId",
          delete: "/match/matchTotal/deleteMatchScore",
          deleteBatch: "/match/matchTotal/deleteBatchMatchScore",
        }
      }
    },
    methods: {
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
        this.$refs.modalForm.title = "添加项目";
      },
    }
  }
</script>
<style scoped>
  @import '~@assets/less/common.less'
</style>
