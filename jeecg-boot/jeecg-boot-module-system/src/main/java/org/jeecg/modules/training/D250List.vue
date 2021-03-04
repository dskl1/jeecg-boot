<template>
  <a-card :bordered="false" :class="'cust-erp-sub-tab'">
    <!-- 操作按钮区域 -->
    <div class="table-operator" v-if="mainId">
      <a-button @click="handleAdd" type="primary" icon="plus">新增</a-button>
      <a-button type="primary" icon="download" @click="handleExportXls('250')">导出</a-button>
      <a-upload
        name="file"
        :showUploadList="false"
        :multiple="false"
        :headers="tokenHeader"
        :action="importExcelUrl"
        @change="handleImportExcel">
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
        <i class="anticon anticon-info-circle ant-alert-icon"></i> 已选择 <a style="font-weight: 600">{{ selectedRowKeys.length }}</a>项
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
        :rowSelection="{selectedRowKeys: selectedRowKeys, onChange: onSelectChange, type:'radio'}"
        :customRow="clickThenSelect"
        @change="handleTableChange">

        <template slot="htmlSlot" slot-scope="text">
          <div v-html="text"></div>
        </template>
        <template slot="imgSlot" slot-scope="text">
          <span v-if="!text" style="font-size: 12px;font-style: italic;">无图片</span>
          <img v-else :src="getImgView(text)" height="25px" alt="" style="max-width:80px;font-size: 12px;font-style: italic;"/>
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
      <a-tab-pane tab="成绩" key="1">
        <TrainingScoreeList :mainId="selectedMainId"/>
      </a-tab-pane>
    </a-tabs>

    <d250-modal ref="modalForm" @ok="modalFormOk" :mainId="mainId"></d250-modal>
  </a-card>
</template>

<script>

  import { JeecgListMixin } from '@/mixins/JeecgListMixin'
  import D250Modal from './modules/D250Modal'
  import { getAction } from '@/api/manage'
  import TrainingScoreeList from './TrainingScoreeList'
  import '@/assets/less/TableExpand.less'
  import D25Modal from './modules/D25Modal'


  export default {
    name: "D250List",
    mixins:[JeecgListMixin],
    components: {
      TrainingScoreeList,
      D250Modal
    },
    props:{
      mainId:{
        type:String,
        default:'',
        required:false
      }
    },
    watch:{
      mainId:{
        immediate: true,
        handler(val) {
          if(!this.mainId){
            this.clearList()
          }else{
            this.queryParam['sportsmanId'] = val
            this.loadData(1);
          }
        }
      }
    },
    data () {
      return {
        description: 'training_score管理页面',
        disableMixinCreated:true,
        // 表头
        columns: [
          {
            title: '#',
            dataIndex: '',
            key:'rowIndex',
            width:60,
            align:"center",
            customRender:function (t,r,index) {
              return parseInt(index)+1;
            }
          },
          {
            title:'训练日期',
            align:"center",
            dataIndex: 'trainingDate',
            customRender:function (text) {
              return !text?"":(text.length>10?text.substr(0,10):text)
            },
            sortDirections: ['descend', 'ascend'],
            defaultSortOrder: 'descend',
            sorter: (a, b) => a.trainingDate - b.trainingDate,
          },
          {
            title:'时间段',
            align:"center",
            dataIndex: 'period'
          },
          {
            title:'训练小组',
            align:"center",
            dataIndex: 'trainingGroupId'
          },
          {
            title:'训练序号',
            align:"center",
            dataIndex: 'trainingItemNo'
          },
          {
            title:'训练项目',
            align:"center",
            dataIndex: 'trainingItem'
          },
          {
            title:'组数',
            align:"center",
            dataIndex: 'groupNo'
          },
          {
            title:'教练',
            align:"center",
            dataIndex: 'coachName'
          },
          {
            title:'是否高原',
            align:"center",
            dataIndex: 'isHighland'
          },
          {
            title:'包干时间',
            align:"center",
            dataIndex: 'baoGan'
          },
          {
            title:'趟数',
            align:"center",
            dataIndex: 'tangShu'
          },
          {
            title:'辅助道具',
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
        url: {
          list: "/training/trainingScore/listD250ByMainId",
          delete: "/training/trainingScore/deleteD250",
          deleteBatch: "/training/trainingScore/deleteBatchD250",
          exportXlsUrl: "/training/trainingScore/exportD250",
          importUrl: "/training/trainingScore/importD250",
        },
        dictOptions:{
        },
        selectedMainId:''
      }
    },
    computed: {
      importExcelUrl(){
        return `${window._CONFIG['domianURL']}/${this.url.importUrl}/${this.mainId}`;
      }
    },
    methods: {
      initDictConfig(){
      },
      clickThenSelect(record) {
        return {
          on: {
            click: () => {
              this.onSelectChange(record.id.split(","), [record]);
            }
          }
        }
      },
      onClearSelected() {
        this.selectedRowKeys = [];
        this.selectionRows = [];
        this.selectedMainId=''
      },
      onSelectChange(selectedRowKeys, selectionRows) {
        this.selectedMainId=selectedRowKeys[0]
        this.selectedRowKeys = selectedRowKeys;
        this.selectionRows = selectionRows;
      },
      loadData(arg) {
        if(!this.url.list){
          this.$message.error("请设置url.list属性!")
          return
        }
        //加载数据 若传入参数1则加载第一页的内容
        if (arg === 1) {
          this.ipagination.current = 1;
        }
        this.onClearSelected()
        var params = this.getQueryParams();//查询条件
        this.loading = true;
        getAction(this.url.list, params).then((res) => {
          if (res.success) {
            this.dataSource = res.result.records;
            this.ipagination.total = res.result.total;
          }
          if(res.code===510){
            this.$message.warning(res.message)
          }
          this.loading = false;
        })
      }

    }
  }
</script>
<style scoped>
  @import '~@assets/less/common.less'
</style>
