<template>
  <a-card :bordered="false">

    <!-- 查询区域 -->
    <div class="table-page-search-wrapper">
      <a-form layout="inline">
        <a-row :gutter="24">
          <a-col :span="6">
            <a-form-item label="姓名">
              <a-input placeholder="请输入姓名" v-model="queryParam.name"></a-input>
            </a-form-item>
          </a-col>
          <a-col :span="6">
            <a-form-item label="性别">
              <a-input placeholder="请输入性别" v-model="queryParam.sex"></a-input>
            </a-form-item>
          </a-col>
          <a-col :span="8" >
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
          <a-menu-item key="1" @click="batchDel"><a-icon type="delete"/>删除</a-menu-item>
        </a-menu>
        <a-button style="margin-left: 8px"> 批量操作 <a-icon type="down" /></a-button>
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
    <studyScore-modal ref="modalForm" @ok="modalFormOk"/>

  </a-card>
</template>

<script>

  import { JeecgListMixin } from '@/mixins/JeecgListMixin'
  import StudyScoreModal from './modules/StudyScoreModal'

  export default {
    name: "StudyScoreList",
    mixins: [JeecgListMixin],
    components: {
      StudyScoreModal
    },
    data () {
      return {
        description: '学习成绩管理页面',
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
          {
            title: '性别',
            align:"center",
            dataIndex: 'sex'
          },
          {
            title: '照片',
            align:"center",
            dataIndex: 'avatar'
          },
          {
            title: '出生日期',
            align:"center",
            dataIndex: 'birthday'
          },
          {
            title: '运动项目',
            align:"center",
            dataIndex: 'sportItem'
          },
          {
            title: '父亲身高(CM)',
            align:"center",
            dataIndex: 'fatherHeight'
          },
          {
            title: '母亲身高(CM)',
            align:"center",
            dataIndex: 'motherHeight'
          },
          {
            title: '直系亲属中有无突出身高者',
            align:"center",
            dataIndex: 'linealRelativeHeightProminent'
          },
          {
            title: '直系亲属中有无遗传疾病',
            align:"center",
            dataIndex: 'linealRelativeGeneticDiseases'
          },
          {
            title: '年级',
            align:"center",
            dataIndex: 'grade'
          },
          {
            title: '学期',
            align:"center",
            dataIndex: 'term'
          },
          {
            title: '语文',
            align:"center",
            dataIndex: 'language'
          },
          {
            title: '数学',
            align:"center",
            dataIndex: 'math'
          },
          {
            title: '英语',
            align:"center",
            dataIndex: 'english'
          },
          {
            title: '生物',
            align:"center",
            dataIndex: 'biology'
          },
          {
            title: '地理',
            align:"center",
            dataIndex: 'geography'
          },
          {
            title: '化学',
            align:"center",
            dataIndex: 'chemistry'
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
            title: '历史',
            align:"center",
            dataIndex: 'history'
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
            title: '出生地',
            align:"center",
            dataIndex: 'birthplace'
          },
          {
            title: '文化程度',
            align:"center",
            dataIndex: 'educationalLevel'
          },
          {
            title: '政治面貌',
            align:"center",
            dataIndex: 'politicsStatus'
          },
          {
            title: '原学校',
            align:"center",
            dataIndex: 'formerSchool'
          },
          {
            title: '原教练',
            align:"center",
            dataIndex: 'formerCoach'
          },
          {
            title: '入队(校)时间',
            align:"center",
            dataIndex: 'entryTeamDate'
          },
          {
            title: '开始训练时间',
            align:"center",
            dataIndex: 'attendTrainTime'
          },
          {
            title: '入队前专项',
            align:"center",
            dataIndex: 'formerSportItem'
          },
          {
            title: '入队后拟选专项',
            align:"center",
            dataIndex: 'afterSportItem'
          },
          {
            title: '有何伤病史',
            align:"center",
            dataIndex: 'injuryHistory'
          },
          {
            title: '身份证号码',
            align:"center",
            dataIndex: 'cardNumber'
          },
          {
            title: '状态',
            align:"center",
            dataIndex: 'retire'
          },
          {
            title: '阶段',
            align:"center",
            dataIndex: 'stage'
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
        // 请求参数
    	url: {
              list: "/sportsman/studyScore/list",
              delete: "/sportsman/studyScore/delete",
              deleteBatch: "/sportsman/studyScore/deleteBatch",
              exportXlsUrl: "sportsman/studyScore/exportXls",
              importExcelUrl: "sportsman/studyScore/importExcel",
           },
        }
      },
      computed: {
        importExcelUrl: function(){
          return `${window._CONFIG['domianURL']}/${this.url.importExcelUrl}`;
        }
      },


    methods: {

      initDictConfig() {
      }

    }
  }
</script>
<style lang="less" scoped>
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
