<template>
  <a-card :bordered="false">

    <!-- 查询区域 -->
    <div class="table-page-search-wrapper">
      <a-form layout="inline">
        <a-row :gutter="24">
          <a-col :md="6" :sm="24">
            <a-form-item label="姓名">
              <a-input placeholder="请输入姓名" v-model="queryParam.name"></a-input>
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

    <!-- table区域-begin -->
    <div>
      <div class="ant-alert ant-alert-info" style="margin-bottom: 16px;">
        <i class="anticon anticon-info-circle ant-alert-icon"></i>
        <span>已选择</span>
        <a style="font-weight: 600">
          {{ selectedRowKeys.length }}
        </a>
        <span>项</span>
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

      </a-table>
    </div>
    <!-- table区域-end -->

    <a-tabs defaultActiveKey="1">
      <a-tab-pane tab="25" :key="refKeys[0]" :forceRender="true">
        <d25a-List ref="D25aList"></d25a-List>
      </a-tab-pane>
      <a-tab-pane tab="50" :key="refKeys[1]" :forceRender="true">
        <d50a-List ref="D50aList"></d50a-List>
      </a-tab-pane>
      <a-tab-pane tab="100" :key="refKeys[2]" :forceRender="true">
        <d100a-List ref="D100aList"></d100a-List>
      </a-tab-pane>
      <a-tab-pane tab="150" :key="refKeys[3]" :forceRender="true">
        <d150a-List ref="D150aList"></d150a-List>
      </a-tab-pane>
      <a-tab-pane tab="200" :key="refKeys[4]" :forceRender="true">
        <d200a-List ref="D200aList"></d200a-List>
      </a-tab-pane>
      <a-tab-pane tab="250" :key="refKeys[5]" :forceRender="true">
        <d250a-List ref="D250aList"></d250a-List>
      </a-tab-pane>
      <a-tab-pane tab="300" :key="refKeys[6]" :forceRender="true">
        <d300a-List ref="D300aList"></d300a-List>
      </a-tab-pane>
      <a-tab-pane tab="350" :key="refKeys[7]" :forceRender="true">
        <d350a-List ref="D350aList"></d350a-List>
      </a-tab-pane>
      <a-tab-pane tab="400" :key="refKeys[8]" :forceRender="true">
        <d400a-List ref="D400aList"></d400a-List>
      </a-tab-pane>
      <a-tab-pane tab="800" :key="refKeys[9]" :forceRender="true">
        <d800a-List ref="D800aList"></d800a-List>
      </a-tab-pane>
      <a-tab-pane tab="1000" :key="refKeys[10]" :forceRender="true">
        <d1000a-List ref="D1000aList"></d1000a-List>
      </a-tab-pane>
      <a-tab-pane tab="1500" :key="refKeys[11]" :forceRender="true">
        <d1500a-List ref="D1500aList"></d1500a-List>
      </a-tab-pane>
    </a-tabs>

    <!-- 表单区域 -->
    <statistics-modal ref="modalForm" @ok="modalFormOk"/>

  </a-card>
</template>

<script>
  import { JeecgListMixin } from '@/mixins/JeecgListMixin'
  import StatisticsModal from './modules/StatisticsModal'
  import D25aList from './D25aList'
  import D25aModal from './modules/D25aModal'
  import D50aList from './D50aList'
  import D50aModal from './modules/D50aModal'
  import D100aList from './D100aList'
  import D100aModal from './modules/D100aModal'
  import D150aList from './D150aList'
  import D150aModal from './modules/D150aModal'
  import D200aList from './D200aList'
  import D200aModal from './modules/D200aModal'
  import D250aList from './D250aList'
  import D250aModal from './modules/D250aModal'
  import D300aList from './D300aList'
  import D300aModal from './modules/D300aModal'
  import D350aList from './D350aList'
  import D350aModal from './modules/D350aModal'
  import D400aList from './D400aList'
  import D400aModal from './modules/D400aModal'
  import D800aList from './D800aList'
  import D800aModal from './modules/D800aModal'
  import D1000aList from './D1000aList'
  import D1000aModal from './modules/D1000aModal'
  import D1500aList from './D1500aList'
  import D1500aModal from './modules/D1500aModal'

  export default {
    name: "StatisticsList",
    mixins: [JeecgListMixin],
    components: {
      StatisticsModal,
      D25aModal,
      D25aList,
      D50aModal,
      D50aList,
      D100aModal,
      D100aList,
      D150aModal,
      D150aList,
      D200aModal,
      D200aList,
      D250aModal,
      D250aList,
      D300aModal,
      D300aList,
      D350aModal,
      D350aList,
      D400aModal,
      D400aList,
      D800aModal,
      D800aList,
      D1000aModal,
      D1000aList,
      D1500aModal,
      D1500aList,
    },
    data () {
      return {
        refKeys: ['d25a', 'd50a', 'd100a', 'd150a', 'd200a', 'd250a', 'd300a', 'd350a', 'd400a', 'd800a', 'd1000a', 'd1500a', ],
        description: '训练报表管理页面',
        // 表头
        columns: [
          {
            title: '#',
            dataIndex: '',
            key: 'rowIndex',
            width: 60,
            align: "center",
            customRender:function (t, r, index) {
              return parseInt(index)+1;
            }
          },
          {
            title: '姓名',
            align:"center",
            dataIndex: 'name'
          },
        ],

        //操作url
        type: "radio",
        url: {
          list: "/statistics/statistics/list",
         },
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
        this.$refs.D25aList.getMain(this.selectedRowKeys[0]);
        this.$refs.D50aList.getMain(this.selectedRowKeys[0]);
        this.$refs.D100aList.getMain(this.selectedRowKeys[0]);
        this.$refs.D150aList.getMain(this.selectedRowKeys[0]);
        this.$refs.D200aList.getMain(this.selectedRowKeys[0]);
        this.$refs.D250aList.getMain(this.selectedRowKeys[0]);
        this.$refs.D300aList.getMain(this.selectedRowKeys[0]);
        this.$refs.D350aList.getMain(this.selectedRowKeys[0]);
        this.$refs.D400aList.getMain(this.selectedRowKeys[0]);
        this.$refs.D800aList.getMain(this.selectedRowKeys[0]);
        this.$refs.D1000aList.getMain(this.selectedRowKeys[0]);
        this.$refs.D1500aList.getMain(this.selectedRowKeys[0]);
      },
      onClearSelected() {
        this.selectedRowKeys = [];
        this.selectionRows = [];
        this.$refs.D25aList.queryParam.mainId = null;
        this.$refs.D25aList.loadData();
        this.$refs.D25aList.selectedRowKeys = [];
        this.$refs.D25aList.selectionRows = [];
        this.$refs.D50aList.queryParam.mainId = null;
        this.$refs.D50aList.loadData();
        this.$refs.D50aList.selectedRowKeys = [];
        this.$refs.D50aList.selectionRows = [];
        this.$refs.D100aList.queryParam.mainId = null;
        this.$refs.D100aList.loadData();
        this.$refs.D100aList.selectedRowKeys = [];
        this.$refs.D100aList.selectionRows = [];
        this.$refs.D150aList.queryParam.mainId = null;
        this.$refs.D150aList.loadData();
        this.$refs.D150aList.selectedRowKeys = [];
        this.$refs.D150aList.selectionRows = [];
        this.$refs.D200aList.queryParam.mainId = null;
        this.$refs.D200aList.loadData();
        this.$refs.D200aList.selectedRowKeys = [];
        this.$refs.D200aList.selectionRows = [];
        this.$refs.D250aList.queryParam.mainId = null;
        this.$refs.D250aList.loadData();
        this.$refs.D250aList.selectedRowKeys = [];
        this.$refs.D250aList.selectionRows = [];
        this.$refs.D300aList.queryParam.mainId = null;
        this.$refs.D300aList.loadData();
        this.$refs.D300aList.selectedRowKeys = [];
        this.$refs.D300aList.selectionRows = [];
        this.$refs.D350aList.queryParam.mainId = null;
        this.$refs.D350aList.loadData();
        this.$refs.D350aList.selectedRowKeys = [];
        this.$refs.D350aList.selectionRows = [];
        this.$refs.D400aList.queryParam.mainId = null;
        this.$refs.D400aList.loadData();
        this.$refs.D400aList.selectedRowKeys = [];
        this.$refs.D400aList.selectionRows = [];
        this.$refs.D800aList.queryParam.mainId = null;
        this.$refs.D800aList.loadData();
        this.$refs.D800aList.selectedRowKeys = [];
        this.$refs.D800aList.selectionRows = [];
        this.$refs.D1000aList.queryParam.mainId = null;
        this.$refs.D1000aList.loadData();
        this.$refs.D1000aList.selectedRowKeys = [];
        this.$refs.D1000aList.selectionRows = [];
        this.$refs.D1500aList.queryParam.mainId = null;
        this.$refs.D1500aList.loadData();
        this.$refs.D1500aList.selectedRowKeys = [];
        this.$refs.D1500aList.selectionRows = [];
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
</style>