<template>
  <a-card :bordered="false">
    <!-- 查询区域 -->
    <div class="table-page-search-wrapper">
      <a-form layout="inline" @keyup.enter.native="searchQuery">
        <a-row :gutter="24">
        </a-row>
      </a-form>
    </div>
    <!-- 查询区域-END -->

    <!-- 操作按钮区域 -->
    <div class="table-operator">
      <a-button type="primary" icon="download" @click="handleExportXls('队员表')">导出</a-button>
      <a-upload name="file" :showUploadList="false" :multiple="false" :headers="tokenHeader" :action="importExcelUrl" @change="handleImportExcel">
        <a-button type="primary" icon="import">导入</a-button>
      </a-upload>
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

    <a-tabs defaultActiveKey="1">
      <a-tab-pane tab="25" key="1" >
        <D25List :mainId="selectedMainId" />
      </a-tab-pane>
      <a-tab-pane tab="50" key="2" forceRender>
        <D50List :mainId="selectedMainId" />
      </a-tab-pane>
      <a-tab-pane tab="100" key="3" forceRender>
        <D100List :mainId="selectedMainId" />
      </a-tab-pane>
      <a-tab-pane tab="150" key="4" forceRender>
        <D150List :mainId="selectedMainId" />
      </a-tab-pane>
      <a-tab-pane tab="200" key="5" forceRender>
        <D200List :mainId="selectedMainId" />
      </a-tab-pane>
      <a-tab-pane tab="250" key="6" forceRender>
        <D250List :mainId="selectedMainId" />
      </a-tab-pane>
      <a-tab-pane tab="300" key="7" forceRender>
        <D300List :mainId="selectedMainId" />
      </a-tab-pane>
      <a-tab-pane tab="350" key="8" forceRender>
        <D350List :mainId="selectedMainId" />
      </a-tab-pane>
      <a-tab-pane tab="400" key="9" forceRender>
        <D400List :mainId="selectedMainId" />
      </a-tab-pane>
      <a-tab-pane tab="800" key="10" forceRender>
        <D800List :mainId="selectedMainId" />
      </a-tab-pane>
      <a-tab-pane tab="1000" key="11" forceRender>
        <D1000List :mainId="selectedMainId" />
      </a-tab-pane>
      <a-tab-pane tab="1500" key="12" forceRender>
        <D1500List :mainId="selectedMainId" />
      </a-tab-pane>
    </a-tabs>

    <trainingScore-modal ref="modalForm" @ok="modalFormOk"></trainingScore-modal>
  </a-card>
</template>

<script>

  import { JeecgListMixin } from '@/mixins/JeecgListMixin'
  import TrainingScoreModal from './modules/TrainingScoreModal'
  import { getAction } from '@/api/manage'
  import D25List from './D25List'
  import D50List from './D50List'
  import D100List from './D100List'
  import D150List from './D150List'
  import D200List from './D200List'
  import D250List from './D250List'
  import D300List from './D300List'
  import D350List from './D350List'
  import D400List from './D400List'
  import D800List from './D800List'
  import D1000List from './D1000List'
  import D1500List from './D1500List'
  import '@/assets/less/TableExpand.less'

  export default {
    name: "TrainingScoreList",
    mixins:[JeecgListMixin],
    components: {
      D25List,
      D50List,
      D100List,
      D150List,
      D200List,
      D250List,
      D300List,
      D350List,
      D400List,
      D800List,
      D1000List,
      D1500List,
      TrainingScoreModal
    },
    data () {
      return {
        description: 'training_score管理页面',
        // 表头
        columns: [
          {
            title:'姓名',
            align:"center",
            dataIndex: 'name'
          },
          {
            title:'训练小组',
            align:"center",
            dataIndex: 'trainingGroupId'
          },
        ],
        url: {
          list: "/training/trainingScore/list",
          delete: "/training/trainingScore/delete",
          deleteBatch: "/training/trainingScore/deleteBatch",
          exportXlsUrl: "/training/trainingScore/exportXls",
          importExcelUrl: "training/trainingScore/importExcel",
        },
        dictOptions:{
        },
        /* 分页参数 */
        ipagination:{
          current: 1,
          pageSize: 10,
          pageSizeOptions: ['5', '10', '50'],
          showTotal: (total, range) => {
            return range[0] + "-" + range[1] + " 共" + total + "条"
          },
          showQuickJumper: true,
          showSizeChanger: true,
          total: 0
        },
        selectedMainId:''

      }
    },
    computed: {
      importExcelUrl: function(){
        return `${window._CONFIG['domianURL']}/${this.url.importExcelUrl}`;
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