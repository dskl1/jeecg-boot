<template>
  <a-card :bordered="false">

    <!-- 查询区域 -->
    <div class="table-page-search-wrapper">
      <a-form layout="inline">
        <a-row :gutter="24">
          <a-col :md="6" :sm="24">
            <a-form-item label="入队后拟选专项">
              <a-input placeholder="请输入入队后拟选专项" v-model="queryParam.afterSportItem"></a-input>
            </a-form-item>
          </a-col>
          <a-col :md="6" :sm="24">
            <a-form-item label="开始训练时间">
              <a-input placeholder="请输入开始训练时间" v-model="queryParam.attendTrainTime"></a-input>
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
      <a-button type="primary" icon="download" @click="handleExportXls">导出</a-button>
      <a-upload name="file" :showUploadList="false" :multiple="false" :action="importExcelUrl" @change="handleImportExcel">
        <a-button type="primary" icon="import">导入</a-button>
      </a-upload>
      <a-dropdown v-if="selectedRowKeys.length > 0">
        <a-menu slot="overlay">
          <a-menu-item key="1" @click="batchDel">
          <a-icon type="delete"/>删除
          </a-menu-item>
        </a-menu>
        <a-button style="margin-left: 8px"> 批量操作
          <a-icon type="down" />
        </a-button>
      </a-dropdown>
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

        <span slot="action" slot-scope="text, record">
          <a @click="handleEdit(record)">编辑</a>

          <a-divider type="vertical" />
          <a-popconfirm title="确定删除吗?" @confirm="() => handleDelete(record.id)">
            <a>删除</a>
          </a-popconfirm>
        </span>

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
  import {deleteAction} from '@/api/manage'
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
            title: '入队后拟选专项',
            align:"center",
            dataIndex: 'afterSportItem'
          },
          {
            title: '开始训练时间',
            align:"center",
            dataIndex: 'attendTrainTime'
          },
          {
            title: '照片',
            align:"center",
            dataIndex: 'avatar'
          },
          {
            title: '生物',
            align:"center",
            dataIndex: 'biology'
          },
          {
            title: '出生日期',
            align:"center",
            dataIndex: 'birthday'
          },
          {
            title: '出生地',
            align:"center",
            dataIndex: 'birthplace'
          },
          {
            title: '身份证号码',
            align:"center",
            dataIndex: 'cardNumber'
          },
          {
            title: '化学',
            align:"center",
            dataIndex: 'chemistry'
          },
          {
            title: '教练',
            align:"center",
            dataIndex: 'coachName'
          },
          {
            title: '距离',
            align:"center",
            dataIndex: 'distance'
          },
          {
            title: '文化程度',
            align:"center",
            dataIndex: 'educationalLevel'
          },
          {
            title: '英语',
            align:"center",
            dataIndex: 'english'
          },
          {
            title: '入队(校)时间',
            align:"center",
            dataIndex: 'entryTeamDate'
          },
          {
            title: '父亲身高(CM)',
            align:"center",
            dataIndex: 'fatherHeight'
          },
          {
            title: '原教练',
            align:"center",
            dataIndex: 'formerCoach'
          },
          {
            title: '原学校',
            align:"center",
            dataIndex: 'formerSchool'
          },
          {
            title: '入队前专项',
            align:"center",
            dataIndex: 'formerSportItem'
          },
          {
            title: '地理',
            align:"center",
            dataIndex: 'geography'
          },
          {
            title: '年级',
            align:"center",
            dataIndex: 'grade'
          },
          {
            title: '组数',
            align:"center",
            dataIndex: 'groupNo'
          },
          {
            title: '历史',
            align:"center",
            dataIndex: 'history'
          },
          {
            title: '有何伤病史',
            align:"center",
            dataIndex: 'injuryHistory'
          },
          {
            title: '语文',
            align:"center",
            dataIndex: 'language'
          },
          {
            title: '直系亲属中有无遗传疾病',
            align:"center",
            dataIndex: 'linealRelativeGeneticDiseases'
          },
          {
            title: '直系亲属中有无突出身高者',
            align:"center",
            dataIndex: 'linealRelativeHeightProminent'
          },
          {
            title: '数学',
            align:"center",
            dataIndex: 'math'
          },
          {
            title: '母亲身高(CM)',
            align:"center",
            dataIndex: 'motherHeight'
          },
          {
            title: '姓名',
            align:"center",
            dataIndex: 'name'
          },
          {
            title: '民族',
            align:"center",
            dataIndex: 'nation'
          },
          {
            title: '籍贯',
            align:"center",
            dataIndex: 'nativePlace'
          },
          {
            title: '系统时间',
            align:"center",
            dataIndex: 'newTime'
          },
          {
            title: '时间段',
            align:"center",
            dataIndex: 'period'
          },
          {
            title: '物理',
            align:"center",
            dataIndex: 'physics'
          },
          {
            title: '政治',
            align:"center",
            dataIndex: 'politics'
          },
          {
            title: '政治面貌',
            align:"center",
            dataIndex: 'politicsStatus'
          },
          {
            title: '状态',
            align:"center",
            dataIndex: 'retire'
          },
          {
            title: '性别',
            align:"center",
            dataIndex: 'sex'
          },
          {
            title: '运动项目',
            align:"center",
            dataIndex: 'sportItem'
          },
          {
            title: '运动员id',
            align:"center",
            dataIndex: 'sportsmanId'
          },
          {
            title: '阶段',
            align:"center",
            dataIndex: 'stage'
          },
          {
            title: '辅助道具',
            align:"center",
            dataIndex: 'supportItem'
          },
          {
            title: '学期',
            align:"center",
            dataIndex: 'term'
          },
          {
            title: '训练日期',
            align:"center",
            dataIndex: 'trainingDate'
          },
          {
            title: '训练时间',
            align:"center",
            dataIndex: 'trainingDate1'
          },
          {
            title: '训练小组',
            align:"center",
            dataIndex: 'trainingGroupId'
          },
          {
            title: '训练项目',
            align:"center",
            dataIndex: 'trainingItem'
          },
          {
            title: '训练序号',
            align:"center",
            dataIndex: 'trainingItemNo'
          },
          {
            title: '成绩',
            align:"center",
            dataIndex: 'trainingScore'
          },
          {
            title: '操作',
            dataIndex: 'action',
            align:"center",
            scopedSlots: { customRender: 'action' },
          }
        ],

        //操作url
        type: "radio",
        url: {
          list: "/statistics/statistics/list",
          delete: "/statistics/statistics/delete",
          deleteBatch: "/statistics/statistics/deleteBatch",
          exportXlsUrl: "statistics/statistics/exportXls",
          importExcelUrl: "statistics/statistics/importExcel",
         },
      }
    },
    computed: {
      importExcelUrl: function(){
        return `${window._CONFIG['domianURL']}/${this.url.importExcelUrl}`;
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

      handleDelete: function (id) {
        var that = this;
        deleteAction(that.url.delete, {id: id}).then((res) => {
          if (res.success) {
            that.$message.success(res.message);
            that.loadData();
            this.$refs.D25aList.loadData();
            this.$refs.D50aList.loadData();
            this.$refs.D100aList.loadData();
            this.$refs.D150aList.loadData();
            this.$refs.D200aList.loadData();
            this.$refs.D250aList.loadData();
            this.$refs.D300aList.loadData();
            this.$refs.D350aList.loadData();
            this.$refs.D400aList.loadData();
            this.$refs.D800aList.loadData();
            this.$refs.D1000aList.loadData();
            this.$refs.D1500aList.loadData();
          } else {
            that.$message.warning(res.message);
          }
        });
      },
      searchQuery:function(){
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
        this.loadData();
      }
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
