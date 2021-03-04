<template>
  <a-card :bordered="false">
    <!-- 查询区域 -->
    <div class="table-page-search-wrapper">
      <a-form layout="inline" @keyup.enter.native="searchQuery">
        <a-row :gutter="24">
          <a-col :md="6" :sm="8">
            <a-form-item label="训练时间">
              <a-select v-model="queryParam.trainingDate1" placeholder="请选择训练时间查询">
                <a-select-option value="1">周一</a-select-option>
                <a-select-option value="2">周二</a-select-option>
                <a-select-option value="3">周三</a-select-option>
                <a-select-option value="4">周四</a-select-option>
                <a-select-option value="5">周五</a-select-option>
                <a-select-option value="6">周六</a-select-option>
                <a-select-option value="0">周日</a-select-option>
              </a-select>
            </a-form-item>
          </a-col>
          <a-col :md="6" :sm="8">
            <a-form-item label="时间段">
              <a-select v-model="queryParam.period" placeholder="请选择时间段查询">
                <a-select-option value="上午">上午</a-select-option>
                <a-select-option value="下午">下午</a-select-option>
                <a-select-option value="晚上">晚上</a-select-option>
              </a-select>
            </a-form-item>
          </a-col>
          <a-col :md="6" :sm="8">
            <a-form-item label="训练小组">
              <a-select v-model="queryParam.trainingGroupId" placeholder="请选择训练小组查询">
                <a-select-option value="初级">初级</a-select-option>
                <a-select-option value="中级">中级</a-select-option>
                <a-select-option value="高级">高级</a-select-option>
              </a-select>
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
    <!-- 查询区域-END -->

    <!-- 操作按钮区域 -->
    <div class="table-operator">
      <a-button @click="handleAdd" type="primary" icon="plus">新增</a-button>
      <a-button type="primary" icon="download" @click="handleExportXls('一周训练内容')">导出</a-button>
      <a-upload name="file" :showUploadList="false" :multiple="false" :headers="tokenHeader" :action="importExcelUrl"
                @change="handleImportExcel">
        <a-button type="primary" icon="import">导入</a-button>
      </a-upload>
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
        class="j-table-force-nowrap"
        :columns="columns"
        :dataSource="dataSource"
        :pagination="ipagination"
        :loading="loading"
        :customRow="clickThenSelect"
        :rowSelection="{selectedRowKeys: selectedRowKeys, onChange: onSelectChange, type:'radio'}"
        @change="handleTableChange">

        <!--        :customRow="clickThenSelect"-->

        <template slot="htmlSlot" slot-scope="text">
          <div v-html="text"></div>
        </template>
        <template slot="imgSlot" slot-scope="text">
          <span v-if="!text" style="font-size: 12px;font-style: italic;">无图片</span>
          <img v-else :src="getImgView(text)" height="25px" alt=""
               style="max-width:80px;font-size: 12px;font-style: italic;"/>
        </template>
        <template slot="fileSlot" slot-scope="text">
          <span v-if="!text" style="font-size: 12px;font-style: italic;">无文件</span>
          <a-button
            v-else
            :ghost="true"
            type="primary"
            icon="download"
            size="small"
            @click="uploadFile(text)">
            下载
          </a-button>
        </template>

        <span slot="switch" slot-scope="text, record">
          <template>
            <a-switch @change="onChange" checked-children="开始" un-checked-children="关闭"/>
            <!--            <a style="margin-left: 24px" @click="onClearSelected">清空</a>-->
          </template>
        </span>

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

    <a-tabs defaultActiveKey="1">
      <a-tab-pane tab="训练人员名单" key="1">
        <WeekTrainingScoreList :mainId="selectedMainId"/>
      </a-tab-pane>
    </a-tabs>

    <weekPlan-modal ref="modalForm" @ok="modalFormOk"></weekPlan-modal>
  </a-card>
</template>

<script>

import { JeecgListMixin } from '@/mixins/JeecgListMixin'
import WeekPlanModal from './modules/WeekPlanModal'
import { getAction } from '@/api/manage'
import WeekTrainingScoreList from './WeekTrainingScoreList'
import '@/assets/less/TableExpand.less'

export default {
  name: 'WeekPlanList',
  mixins: [JeecgListMixin],
  components: {
    WeekTrainingScoreList,
    WeekPlanModal
  },
  data () {
    return {
      description: '一周训练内容管理页面',
      // 表头
      columns: [
        {
          title: '训练时间',
          align: 'center',
          dataIndex: 'trainingDate1_dictText',
          sortDirections: ['descend', 'ascend'],
          defaultSortOrder: 'ascend',
          sorter: (a, b) => a.trainingDate1 - b.trainingDate1,
        },
        {
          title: '时间段',
          align: 'center',
          dataIndex: 'period'
        },
        {
          title: '训练小组',
          align: 'center',
          dataIndex: 'trainingGroupId'
        },
        {
          title: '训练序号',
          align: 'center',
          dataIndex: 'trainingItemNo'
        },
        {
          title: '训练项目',
          align: 'center',
          dataIndex: 'trainingItem'
        },
        {
          title: '距离',
          align: 'center',
          dataIndex: 'distance'
        },
        {
          title: '组数',
          align: 'center',
          dataIndex: 'groupNo'
        },
        {
          title: '是否高原',
          align: 'center',
          dataIndex: 'isHighland'
        },
        {
          title: '教练',
          align: 'center',
          dataIndex: 'coachName'
        },
        {
          title: '包干时间',
          align: 'center',
          dataIndex: 'baoGan'
        },
        {
          title: '趟数',
          align: 'center',
          dataIndex: 'tangShu'
        },
        {
          title: '辅助道具',
          align: 'center',
          dataIndex: 'supportItem'
        },
        {
          title: '***',
          align: 'center',
          scopedSlots: { customRender: 'switch' },
        },
        {
          title: '操作',
          dataIndex: 'action',
          align: 'center',
          scopedSlots: { customRender: 'action' },
        }
      ],
      url: {
        list: '/training/weekPlan/list',
        delete: '/training/weekPlan/delete',
        deleteBatch: '/training/weekPlan/deleteBatch',
        exportXlsUrl: '/training/weekPlan/exportXls',
        importExcelUrl: 'training/weekPlan/importExcel',
      },
      dictOptions: {},
      /* 分页参数 */
      ipagination: {
        current: 1,
        pageSize: 10,
        pageSizeOptions: ['5', '10', '50'],
        showTotal: (total, range) => {
          return range[0] + '-' + range[1] + ' 共' + total + '条'
        },
        showQuickJumper: true,
        showSizeChanger: true,
        total: 0
      },
      selectedMainId: '',
      count: 0
    }
  },
  computed: {
    importExcelUrl: function () {
      return `${window._CONFIG['domianURL']}/${this.url.importExcelUrl}`
    }
  },
  methods: {
    selectRow () {
      getAction(this.url.list, { mainId: this.selectedMainId }).then((res) => {
        if (res.success) {
          this.dataSource = res.result
        } else {
          this.dataSource = null
        }
      })
    },
    changeEnabled (data, val) {
      this.$confirm('此操作将 "' + this.dict.label.user_status[val] + '" ' + data.username + ', 是否继续？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        crudUser.edit(data).then(res => {
          this.crud.notify(this.dict.label.user_status[val] + '成功', CRUD.NOTIFICATION_TYPE.SUCCESS)
        }).catch(() => {
          data.enabled = !data.enabled
        })
      }).catch(() => {
        data.enabled = !data.enabled
      })
    },
    initDictConfig () {
    },
    clickThenSelect (record) {
      return {
        on: {
          click: () => {
            this.onSelectChange(record.id.split(','), [record])
          }
        }
      }
    },
    onClearSelected () {
      this.selectedRowKeys = []
      this.selectionRows = []
      this.selectedMainId = ''
    },
    onChange (checked) {
      if (checked === false) {
        this.count = this.count - 1
        this.onClearSelected()
      } else if (checked === true) {
        this.count = this.count + 1
      }
    },
    onSelectChange (selectedRowKeys, selectionRows) {
      this.selectedRowKeys = selectedRowKeys
      this.selectionRows = selectionRows
      if (this.count !== 0) {
        this.selectedMainId = selectedRowKeys[0]
        this.selectRow()
      }
      // alert(this.selectedMainId)
    },
    loadData (arg) {
      if (!this.url.list) {
        this.$message.error('请设置url.list属性!')
        return
      }
      //加载数据 若传入参数1则加载第一页的内容
      if (arg === 1) {
        this.ipagination.current = 1
      }
      this.onClearSelected()
      var params = this.getQueryParams()//查询条件
      this.loading = true
      getAction(this.url.list, params).then((res) => {
        if (res.success) {
          this.dataSource = res.result.records
          this.ipagination.total = res.result.total
        }
        if (res.code === 510) {
          this.$message.warning(res.message)
        }
        this.loading = false
      })
    }

  }
}
</script>
<style scoped>
@import '~@assets/less/common.less';
</style>