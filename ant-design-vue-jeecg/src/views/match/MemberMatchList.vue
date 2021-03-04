<template>
  <a-row :gutter="10">
    <a-col :md="leftColMd" :sm="24" style="margin-bottom: 20px">
      <a-card :bordered="false">
        <!-- 查询区域 -->
        <div class="table-page-search-wrapper">
          <!-- 搜索区域 -->
          <a-form layout="inline">
            <a-row :gutter="24">
              <a-col :md="12" :sm="12">
                <a-form-item label="运动员姓名" :labelCol="{span: 5}" :wrapperCol="{span: 18, offset: 1}">
                  <a-input placeholder="" v-model="queryParam.name"></a-input>
                </a-form-item>
              </a-col>
              <span style="float: left;overflow: hidden;" class="table-page-search-submitButtons">
            <a-col :md="12" :sm="24">
               <a-button type="primary" @click="searchQuery" icon="search" style="margin-left: 21px">查询</a-button>
              <a-button type="primary" @click="searchReset" icon="reload" style="margin-left: 8px">重置</a-button>
            </a-col>
          </span>
            </a-row>
          </a-form>
        </div>
        <!-- 操作按钮区域 -->
        <div class="table-operator" style="margin: -8px 0 10px 2px">
          <a-button @click="handleAdd" type="primary" icon="plus">运动员录入</a-button>
          <a-button @click="handleEdit(model1)" type="primary" icon="plus">运动员编辑</a-button>
          <a-upload name="file" :showUploadList="false" :multiple="false" :headers="tokenHeader" :action="importExcelUrl" @change="handleImportExcel">
            <a-button type="primary" icon="import">导入</a-button>
          </a-upload>
          <a-button type="primary" icon="download" @click="handleExportXls">导出</a-button>
        </div>

        <div class="ant-alert ant-alert-info" style="margin-bottom: 16px;">
          <i class="anticon anticon-info-circle ant-alert-icon">
          </i> 已选择 <a><b>{{ selectedRowKeys1.length }}</b></a>项
          <a style="margin-left: 24px" @click="onClearSelected1">清空</a>
        </div>

        <div style="margin-top: 15px">
          <a-table
            style="height:500px"
            ref="table"
            size="middle"
            bordered
            rowKey="id"
            :columns="columns"
            :dataSource="dataSource"
            :pagination="ipagination"
            :loading="loading"
            :rowSelection="{selectedRowKeys: selectedRowKeys1, onChange: onSelectChange1, type:'radio'}"
            @change="handleTableChange">
          <span slot="action" slot-scope="text, record">
          <a @click="handleOpen(record)">比赛</a>
          <a-divider type="vertical"/>
          <a-dropdown>
            <a class="ant-dropdown-link">
              更多 <a-icon type="down"/>
            </a>
            <a-menu slot="overlay">
              <a-menu-item>
                <a-popconfirm title="确定删除吗?" @confirm="() => handleDelete1(record.id)">
                  <a>删除</a>
                </a-popconfirm>
              </a-menu-item>
            </a-menu>
          </a-dropdown>
        </span>
          </a-table>
        </div>
      </a-card>
    </a-col>
    <a-col :md="rightColMd" :sm="24">
      <a-card :bordered="false">
        <!-- 查询区域 -->
        <div class="table-page-search-wrapper">
          <a-form layout="inline">
            <a-row :gutter="24">

              <a-col :md="12" :sm="12">
                <a-form-item label="比赛名称">
                  <a-input placeholder="" v-model="queryParam2.matchName"></a-input>
                </a-form-item>
              </a-col>
              <span style="float: left;overflow: hidden;" class="table-page-search-submitButtons">
            <a-col :md="9" :sm="24">
             <a-button type="primary" @click="searchQuery2" icon="search" style="margin-left: 21px">查询</a-button>
              <a-button type="primary" @click="searchReset2" icon="reload" style="margin-left: 8px">重置</a-button>

            </a-col>
          </span>
            </a-row>
          </a-form>
        </div>
        <!-- 操作按钮区域 -->
        <div class="table-operator" :md="24" :sm="24" style="margin: -25px 0px 10px 2px">
          <a-button @click="handleAdd2" type="primary" icon="plus" style="margin-top: 16px">比赛录入</a-button>
          <!--<a-button @click="handleEdit2" type="primary" icon="edit" style="margin-top: 16px">用户编辑</a-button>-->
          <a-button @click="handleAddMatchMember" type="primary" icon="plus" style="margin-top: 16px">添加已有比赛</a-button>

          <a-dropdown v-if="selectedRowKeys2.length > 0">
            <a-menu slot="overlay">
              <a-menu-item key="1" @click="batchDel2">
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
            selectedRowKeys2.length }}</a>项
            <a style="margin-left: 24px" @click="onClearSelected2">清空</a>
          </div>
          <a-table
            style="height:500px"
            ref="table2"
            bordered
            size="middle"
            rowKey="id"
            :columns="columns2"
            :dataSource="dataSource2"
            :pagination="ipagination2"
            :loading="loading2"
            :rowSelection="{selectedRowKeys: selectedRowKeys2, onChange: onSelectChange2}"
            @change="handleTableChange2">
           <span slot="action" slot-scope="text, record">
           <a @click="handleEdit2(record)">编辑</a>
          <a-divider type="vertical"/>
          <a-dropdown>
            <a class="ant-dropdown-link">
              更多 <a-icon type="down"/>
            </a>
            <a-menu slot="overlay">
              <a-menu-item>
                <a-popconfirm title="确定删除吗?" @confirm="() => handleDelete2(record.id)">
                  <a>删除</a>
                </a-popconfirm>
              </a-menu-item>
            </a-menu>
          </a-dropdown>
        </span>
          </a-table>
        </div>
        <!-- 表单区域 -->
        <matchPlan-modal ref="modalForm2" @ok="modalFormOk2"></matchPlan-modal>
        <matchMember-modal ref="modalForm" @ok="modalFormOk"></matchMember-modal>
        <Select-match-Modal ref="selectMatchModal" @selectFinished="selectOK"></Select-match-Modal>
      </a-card>
    </a-col>
  </a-row>
</template>
<script>
    import { JeecgListMixin } from '@/mixins/JeecgListMixin'
    import { deleteAction, postAction, getAction } from '@/api/add'
    import SelectMatchModal from './modules/SelectMatchModal'
    import MatchMemberModal from './modules/MatchMemberModal'
    import MatchPlanModal from './modules/MatchPlanModal'
    import { filterObj } from '@/utils/util'

    export default {
        name: 'MemberMatchList',
        mixins: [JeecgListMixin],
        components: {
            SelectMatchModal,
            MatchMemberModal,
            MatchPlanModal
        },
        data() {
            return {
                model1: {},
                model2: {},
                currentSportsmanId: '',
                queryParam1: {},
                queryParam2: {},
                dataSource1: [],
                dataSource2: [],
                ipagination1: {
                    current: 1,
                    pageSize: 10,
                    pageSizeOptions: ['10', '20', '30'],
                    showTotal: (total, range) => {
                        return range[0] + '-' + range[1] + ' 共' + total + '条'
                    },
                    showQuickJumper: true,
                    showSizeChanger: true,
                    total: 0
                },
                ipagination2: {
                    current: 1,
                    pageSize: 10,
                    pageSizeOptions: ['10', '20', '30'],
                    showTotal: (total, range) => {
                        return range[0] + '-' + range[1] + ' 共' + total + '条'
                    },
                    showQuickJumper: true,
                    showSizeChanger: true,
                    total: 0
                },
                isorter1: {
                    column: 'createTime',
                    order: 'desc'
                },
                isorter2: {
                    column: 'createTime',
                    order: 'desc'
                },
                filters1: {},
                filters2: {},
                loading1: false,
                loading2: false,
                selectedRowKeys1: [],
                selectedRowKeys2: [],
                selectionRows1: [],
                selectionRows2: [],
                columns:
                    [
                        {
                            title: '运动员姓名',
                            align: 'center',
                            dataIndex: 'name'
                        },
                        {
                            title: '操作',
                            dataIndex: 'action',
                            align: 'center',
                            scopedSlots: { customRender: 'action' }
                        }
                    ],
                columns2: [{
                    title: '比赛名称',
                    align: 'center',
                    dataIndex: 'matchName',
                    width: 120
                },
                    {
                        title: '比赛日期',
                        align:"center",
                        children:[
                            {
                                title: '开始日期',
                                align:"center",
                                dataIndex: 'matchDate',
                                width:150,
                            },
                            {
                                title: '结束日期',
                                align:"center",
                                dataIndex: 'endDate',
                                width:150,
                            },
                        ]
                    },
                    {
                        title: '参赛人员',
                        align: 'center',
                        width: 80,
                        dataIndex: 'matchItem'
                    },

                    {
                        title: '操作',
                        dataIndex: 'action',
                        scopedSlots: { customRender: 'action' },
                        align: 'center',
                        width: 120
                    }],


                url: {
                    list: '/match/matchMember/list',
                    delete: '/match/matchMember/delete',
                    list2: '/match/matchPlan/matchMemberList',
                    addMatchMember: '/match/matchPlan/addMatchMember',
                    delete2: '/match/matchPlan/deleteMatchSportsman',
                    deleteBatch2: '/match/matchPlan/deleteMatchSportsmanBatch',
                    exportXlsUrl: 'match/matchMember/exportXls',
                    importExcelUrl: 'match/matchMember/importExcel'
                }
            }
        },
        computed: {
            importExcelUrl: function() {
                return `${window._CONFIG['domianURL']}/${this.url.importExcelUrl}`
            },
            leftColMd() {
                return this.selectedRowKeys1.length === 0 ? 24 : 12
            },
            rightColMd() {
                return this.selectedRowKeys1.length === 0 ? 0 : 12
            }
        },
        methods: {
            onSelectChange2(selectedRowKeys, selectionRows) {
                this.selectedRowKeys2 = selectedRowKeys
                this.selectionRows2 = selectionRows
            },
            onClearSelected2() {
                this.selectedRowKeys2 = []
                this.selectionRows2 = []
            },
            onClearSelected1() {
                this.selectedRowKeys1 = []
                this.selectionRows1 = []
            },
            onSelectChange1(selectedRowKeys, selectionRows) {
                this.selectedRowKeys1 = selectedRowKeys
                this.selectionRows1 = selectionRows
                this.model1 = Object.assign({}, selectionRows[0])
                console.log(this.model1)
                this.currentSportsmanId = selectedRowKeys[0]
                this.loadData2()
            },
            onClearSelected() {
            },

            getQueryParams2() {
                //获取查询条件
                let sqp = {}
                if (this.superQueryParams2) {
                    sqp['superQueryParams'] = encodeURI(this.superQueryParams2)
                }
                var param = Object.assign(sqp, this.queryParam2, this.isorter2, this.filters2)
                param.field = this.getQueryField2()
                param.pageNo = this.ipagination2.current
                param.pageSize = this.ipagination2.pageSize
                return filterObj(param)
            },
            getQueryField2() {
                //TODO 字段权限控制
                var str = 'id,'
                this.columns2.forEach(function(value) {
                    str += ',' + value.dataIndex
                })
                return str
            },
            handleEdit2: function(record) {
                this.$refs.modalForm2.title = '编辑'
                this.$refs.modalForm2.sportsmanDisabled = true
                this.$refs.modalForm2.edit(record)
            },
            handleAdd2: function() {
                if (this.currentSportsmanId == '') {
                    this.$message.error('请选择一个运动员!')
                } else {
                    this.$refs.modalForm2.sportsmanDisabled = true
                    this.$refs.modalForm2.selectedSportsman = [this.currentSportsmanId]

                    this.$refs.modalForm2.add()

                    this.$refs.modalForm2.title = '新增'
                }
            },
            modalFormOk2() {
                // 新增/修改 成功时，重载列表
                this.loadData2()
            },
            loadData2(arg) {
                if (!this.url.list2) {
                    this.$message.error('请设置url.list2属性!')
                    return
                }
                //加载数据 若传入参数1则加载第一页的内容
                if (arg === 1) {
                    this.ipagination2.current = 1
                }
                if (this.currentSportsmanId === '') return
                let params = this.getQueryParams2()//查询条件
                params.sportsmanId = this.currentSportsmanId
                this.loading2 = true
                getAction(this.url.list2, params).then((res) => {
                    if (res.success) {
                        this.dataSource2 = res.result.records
                        this.ipagination2.total = res.result.total

                    }
                    this.loading2 = false
                })

            },
            handleDelete1: function(id) {
                this.handleDelete(id)
                this.dataSource2 = []
                this.currentSportsmanId = ''
            },
            handleDelete2: function(id) {
                if (!this.url.delete2) {
                    this.$message.error('请设置url.delete2属性!')
                    return
                }
                var that = this
                deleteAction(that.url.delete2, { sportsmanId: this.currentSportsmanId, matchId: id }).then((res) => {
                    if (res.success) {
                        that.$message.success(res.message)
                        that.loadData2()
                    } else {
                        that.$message.warning(res.message)
                    }
                })
            },
            batchDel2: function() {

                if (!this.url.deleteBatch2) {
                    this.$message.error('请设置url.deleteBatch2属性!')
                    return
                }
                if (this.selectedRowKeys2.length <= 0) {
                    this.$message.warning('请选择一条记录！')
                    return
                } else {
                    var ids = ''
                    for (var a = 0; a < this.selectedRowKeys2.length; a++) {
                        ids += this.selectedRowKeys2[a] + ','
                    }
                    var that = this
                    console.log(this.currentDeptId)
                    this.$confirm({
                        title: '确认删除',
                        content: '是否删除选中数据?',
                        onOk: function() {
                            deleteAction(that.url.deleteBatch2, { sportsmanId: that.currentSportsmanId, matchIds: ids }).then((res) => {
                                if (res.success) {
                                    that.$message.success(res.message)
                                    that.loadData2()
                                    that.onClearSelected()
                                } else {
                                    that.$message.warning(res.message)
                                }
                            })
                        }
                    })
                }
            },
            selectOK(data) {
                let params = {}
                params.sportsmanId = this.currentSportsmanId
                params.matchIdList = []
                for (var a = 0; a < data.length; a++) {
                    params.matchIdList.push(data[a])
                }
                console.log(params)
                postAction(this.url.addMatchMember, params).then((res) => {
                    if (res.success) {
                        this.loadData2()
                        this.$message.success(res.message)
                    } else {
                        this.$message.warning(res.message)
                    }
                })
            },

            handleAddMatchMember() {
                if (this.currentSportsmanId == '') {
                    this.$message.error('请选择一个运动员!')
                } else {
                    this.$refs.selectMatchModal.visible = true
                }
            },
            handleOpen(record) {
                this.selectedRowKeys1 = [record.id]
                this.model1 = Object.assign({}, record)
                this.currentSportsmanId = record.id
                this.onClearSelected2()
                this.loadData2()
            },

            handleEdit: function(record) {
                if (this.currentSportsmanId == '') {
                    this.$message.error('请选择一个运动员!')
                } else {
                    this.$refs.modalForm.edit(record)
                    this.$refs.modalForm.title = '编辑'
                }
            },
            searchQuery2() {
                this.loadData2(1)
            },
            searchReset2() {
                this.queryParam2 = {}
                this.loadData2(1)
            },
            handleTableChange2(pagination, filters, sorter) {
                //分页、排序、筛选变化时触发
                //TODO 筛选
                if (Object.keys(sorter).length > 0) {
                    this.isorter2.column = sorter.field
                    this.isorter2.order = 'ascend' == sorter.order ? 'asc' : 'desc'
                }
                this.ipagination2 = pagination
                this.loadData2()
            }
        }
    }
</script>
<style scoped>
  /** Button按钮间距 */
  .ant-btn {
    margin-left: 8px
  }
</style>