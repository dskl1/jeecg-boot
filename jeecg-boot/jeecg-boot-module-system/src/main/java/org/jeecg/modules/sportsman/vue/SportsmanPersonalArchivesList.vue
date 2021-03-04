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
      <a-tab-pane tab="运动员等级" :key="refKeys[0]" :forceRender="true">
        <sportsmanRank-List ref="SportsmanRankList"></sportsmanRank-List>
      </a-tab-pane>
      <a-tab-pane tab="运动员训练简历" :key="refKeys[1]" :forceRender="true">
        <sportsmanResume-List ref="SportsmanResumeList"></sportsmanResume-List>
      </a-tab-pane>
      <a-tab-pane tab="运动员家庭成员情况" :key="refKeys[2]" :forceRender="true">
        <sportsmanFamilySituation-List ref="SportsmanFamilySituationList"></sportsmanFamilySituation-List>
      </a-tab-pane>
      <a-tab-pane tab="个人资料" :key="refKeys[3]" :forceRender="true">
        <sportsmanData-List ref="SportsmanDataList"></sportsmanData-List>
      </a-tab-pane>
      <a-tab-pane tab="历年机能发育情况" :key="refKeys[4]" :forceRender="true">
        <developSituation-List ref="DevelopSituationList"></developSituation-List>
      </a-tab-pane>
      <a-tab-pane tab="身体形态指标" :key="refKeys[5]" :forceRender="true">
        <bodyType-List ref="BodyTypeList"></bodyType-List>
      </a-tab-pane>
      <a-tab-pane tab="身体素质" :key="refKeys[6]" :forceRender="true">
        <bodyAbility-List ref="BodyAbilityList"></bodyAbility-List>
      </a-tab-pane>
    </a-tabs>

    <!-- 表单区域 -->
    <sportsmanPersonalArchives-modal ref="modalForm" @ok="modalFormOk"/>

  </a-card>
</template>

<script>
  import { JeecgListMixin } from '@/mixins/JeecgListMixin'
  import SportsmanPersonalArchivesModal from './modules/SportsmanPersonalArchivesModal'
  import {deleteAction} from '@/api/manage'
  import SportsmanRankList from './SportsmanRankList'
  import SportsmanRankModal from './modules/SportsmanRankModal'
  import SportsmanResumeList from './SportsmanResumeList'
  import SportsmanResumeModal from './modules/SportsmanResumeModal'
  import SportsmanFamilySituationList from './SportsmanFamilySituationList'
  import SportsmanFamilySituationModal from './modules/SportsmanFamilySituationModal'
  import SportsmanDataList from './SportsmanDataList'
  import SportsmanDataModal from './modules/SportsmanDataModal'
  import DevelopSituationList from './DevelopSituationList'
  import DevelopSituationModal from './modules/DevelopSituationModal'
  import BodyTypeList from './BodyTypeList'
  import BodyTypeModal from './modules/BodyTypeModal'
  import BodyAbilityList from './BodyAbilityList'
  import BodyAbilityModal from './modules/BodyAbilityModal'

  export default {
    name: "SportsmanPersonalArchivesList",
    mixins: [JeecgListMixin],
    components: {
      SportsmanPersonalArchivesModal,
      SportsmanRankModal,
      SportsmanRankList,
      SportsmanResumeModal,
      SportsmanResumeList,
      SportsmanFamilySituationModal,
      SportsmanFamilySituationList,
      SportsmanDataModal,
      SportsmanDataList,
      DevelopSituationModal,
      DevelopSituationList,
      BodyTypeModal,
      BodyTypeList,
      BodyAbilityModal,
      BodyAbilityList,
    },
    data () {
      return {
        refKeys: ['sportsmanRank', 'sportsmanResume', 'sportsmanFamilySituation', 'sportsmanData', 'developSituation', 'bodyType', 'bodyAbility', ],
        description: '档案管理管理页面',
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
          list: "/sportsman/sportsmanPersonalArchives/list",
          delete: "/sportsman/sportsmanPersonalArchives/delete",
          deleteBatch: "/sportsman/sportsmanPersonalArchives/deleteBatch",
          exportXlsUrl: "sportsman/sportsmanPersonalArchives/exportXls",
          importExcelUrl: "sportsman/sportsmanPersonalArchives/importExcel",
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
        this.$refs.SportsmanRankList.getMain(this.selectedRowKeys[0]);
        this.$refs.SportsmanResumeList.getMain(this.selectedRowKeys[0]);
        this.$refs.SportsmanFamilySituationList.getMain(this.selectedRowKeys[0]);
        this.$refs.SportsmanDataList.getMain(this.selectedRowKeys[0]);
        this.$refs.DevelopSituationList.getMain(this.selectedRowKeys[0]);
        this.$refs.BodyTypeList.getMain(this.selectedRowKeys[0]);
        this.$refs.BodyAbilityList.getMain(this.selectedRowKeys[0]);
      },
      onClearSelected() {
        this.selectedRowKeys = [];
        this.selectionRows = [];
        this.$refs.SportsmanRankList.queryParam.mainId = null;
        this.$refs.SportsmanRankList.loadData();
        this.$refs.SportsmanRankList.selectedRowKeys = [];
        this.$refs.SportsmanRankList.selectionRows = [];
        this.$refs.SportsmanResumeList.queryParam.mainId = null;
        this.$refs.SportsmanResumeList.loadData();
        this.$refs.SportsmanResumeList.selectedRowKeys = [];
        this.$refs.SportsmanResumeList.selectionRows = [];
        this.$refs.SportsmanFamilySituationList.queryParam.mainId = null;
        this.$refs.SportsmanFamilySituationList.loadData();
        this.$refs.SportsmanFamilySituationList.selectedRowKeys = [];
        this.$refs.SportsmanFamilySituationList.selectionRows = [];
        this.$refs.SportsmanDataList.queryParam.mainId = null;
        this.$refs.SportsmanDataList.loadData();
        this.$refs.SportsmanDataList.selectedRowKeys = [];
        this.$refs.SportsmanDataList.selectionRows = [];
        this.$refs.DevelopSituationList.queryParam.mainId = null;
        this.$refs.DevelopSituationList.loadData();
        this.$refs.DevelopSituationList.selectedRowKeys = [];
        this.$refs.DevelopSituationList.selectionRows = [];
        this.$refs.BodyTypeList.queryParam.mainId = null;
        this.$refs.BodyTypeList.loadData();
        this.$refs.BodyTypeList.selectedRowKeys = [];
        this.$refs.BodyTypeList.selectionRows = [];
        this.$refs.BodyAbilityList.queryParam.mainId = null;
        this.$refs.BodyAbilityList.loadData();
        this.$refs.BodyAbilityList.selectedRowKeys = [];
        this.$refs.BodyAbilityList.selectionRows = [];
      },

      handleDelete: function (id) {
        var that = this;
        deleteAction(that.url.delete, {id: id}).then((res) => {
          if (res.success) {
            that.$message.success(res.message);
            that.loadData();
            this.$refs.SportsmanRankList.loadData();
            this.$refs.SportsmanResumeList.loadData();
            this.$refs.SportsmanFamilySituationList.loadData();
            this.$refs.SportsmanDataList.loadData();
            this.$refs.DevelopSituationList.loadData();
            this.$refs.BodyTypeList.loadData();
            this.$refs.BodyAbilityList.loadData();
          } else {
            that.$message.warning(res.message);
          }
        });
      },
      searchQuery:function(){
        this.selectedRowKeys = [];
        this.selectionRows = [];
        this.$refs.SportsmanRankList.queryParam.mainId = null;
        this.$refs.SportsmanRankList.loadData();
        this.$refs.SportsmanRankList.selectedRowKeys = [];
        this.$refs.SportsmanRankList.selectionRows = [];
        this.$refs.SportsmanResumeList.queryParam.mainId = null;
        this.$refs.SportsmanResumeList.loadData();
        this.$refs.SportsmanResumeList.selectedRowKeys = [];
        this.$refs.SportsmanResumeList.selectionRows = [];
        this.$refs.SportsmanFamilySituationList.queryParam.mainId = null;
        this.$refs.SportsmanFamilySituationList.loadData();
        this.$refs.SportsmanFamilySituationList.selectedRowKeys = [];
        this.$refs.SportsmanFamilySituationList.selectionRows = [];
        this.$refs.SportsmanDataList.queryParam.mainId = null;
        this.$refs.SportsmanDataList.loadData();
        this.$refs.SportsmanDataList.selectedRowKeys = [];
        this.$refs.SportsmanDataList.selectionRows = [];
        this.$refs.DevelopSituationList.queryParam.mainId = null;
        this.$refs.DevelopSituationList.loadData();
        this.$refs.DevelopSituationList.selectedRowKeys = [];
        this.$refs.DevelopSituationList.selectionRows = [];
        this.$refs.BodyTypeList.queryParam.mainId = null;
        this.$refs.BodyTypeList.loadData();
        this.$refs.BodyTypeList.selectedRowKeys = [];
        this.$refs.BodyTypeList.selectionRows = [];
        this.$refs.BodyAbilityList.queryParam.mainId = null;
        this.$refs.BodyAbilityList.loadData();
        this.$refs.BodyAbilityList.selectedRowKeys = [];
        this.$refs.BodyAbilityList.selectionRows = [];
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
