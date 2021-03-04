<template>
  <a-card :bordered="false">

    <!-- 查询区域 -->
    <div class="table-page-search-wrapper">
      <a-form layout="inline">
        <a-row :gutter="24">
          <a-col :md="6" :sm="8">
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

    <!-- 操作按钮区域 -->
    <div class="table-operator">
      <a-button type="primary" icon="download" @click="handleExportXls">导出</a-button>
      <a-upload name="file" :showUploadList="false" :multiple="false" :action="importExcelUrl"
                @change="handleImportExcel">
        <a-button type="primary" icon="import">导入</a-button>
      </a-upload>
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

        <div slot="birthday" slot-scope="text,record" style="margin: 0">
          <a v-if="record.cardNumber" v-show="false">{{record.grade =
            parseInt(date[0]+date[1]+date[2]+date[3])-parseInt(record.cardNumber[6]+record.cardNumber[7]+record.cardNumber[8]+record.cardNumber[9])}}</a>
          <template v-if="record.grade===3&&parseInt(record.cardNumber[10]+record.cardNumber[11])<9">小班</template>
          <template v-else-if="record.grade===4&&parseInt(record.cardNumber[10]+record.cardNumber[11])>=9">小班</template>
          <template v-else-if="record.grade===4&&parseInt(record.cardNumber[10]+record.cardNumber[11])<9">中班</template>
          <template v-else-if="record.grade===5&&parseInt(record.cardNumber[10]+record.cardNumber[11])>=9">中班</template>
          <template v-else-if="record.grade===5&&parseInt(record.cardNumber[10]+record.cardNumber[11])<9">大班</template>
          <template v-else-if="record.grade===6&&parseInt(record.cardNumber[10]+record.cardNumber[11])>=9">大班</template>
          <template v-else-if="record.grade===6&&parseInt(record.cardNumber[10]+record.cardNumber[11])<9">一年级</template>
          <template v-else-if="record.grade===7&&parseInt(record.cardNumber[10]+record.cardNumber[11])>=9">一年级
          </template>
          <template v-else-if="record.grade===7&&parseInt(record.cardNumber[10]+record.cardNumber[11])<9">二年级</template>
          <template v-else-if="record.grade===8&&parseInt(record.cardNumber[10]+record.cardNumber[11])>=9">二年级
          </template>
          <template v-else-if="record.grade===8&&parseInt(record.cardNumber[10]+record.cardNumber[11])<9">三年级</template>
          <template v-else-if="record.grade===9&&parseInt(record.cardNumber[10]+record.cardNumber[11])>=9">三年级
          </template>
          <template v-else-if="record.grade===9&&parseInt(record.cardNumber[10]+record.cardNumber[11])<9">四年级</template>
          <template v-else-if="record.grade===10&&parseInt(record.cardNumber[10]+record.cardNumber[11])>=9">四年级
          </template>
          <template v-else-if="record.grade===10&&parseInt(record.cardNumber[10]+record.cardNumber[11])<9">五年级
          </template>
          <template v-else-if="record.grade===11&&parseInt(record.cardNumber[10]+record.cardNumber[11])>=9">五年级
          </template>
          <template v-else-if="record.grade===11&&parseInt(record.cardNumber[10]+record.cardNumber[11])<9">六年级
          </template>
          <template v-else-if="record.grade===12&&parseInt(record.cardNumber[10]+record.cardNumber[11])>=9">六年级
          </template>
          <template v-else-if="record.grade===12&&parseInt(record.cardNumber[10]+record.cardNumber[11])<9">初一</template>
          <template v-else-if="record.grade===13&&parseInt(record.cardNumber[10]+record.cardNumber[11])>=9">初一
          </template>
          <template v-else-if="record.grade===13&&parseInt(record.cardNumber[10]+record.cardNumber[11])<9">初二</template>
          <template v-else-if="record.grade===14&&parseInt(record.cardNumber[10]+record.cardNumber[11])>=9">初二
          </template>
          <template v-else-if="record.grade===14&&parseInt(record.cardNumber[10]+record.cardNumber[11])<9">初三</template>
          <template v-else-if="record.grade===15&&parseInt(record.cardNumber[10]+record.cardNumber[11])>=9">初三
          </template>
          <template v-else-if="record.grade===15&&parseInt(record.cardNumber[10]+record.cardNumber[11])<9">高一</template>
          <template v-else-if="record.grade===16&&parseInt(record.cardNumber[10]+record.cardNumber[11])>=9">高一
          </template>
          <template v-else-if="record.grade===16&&parseInt(record.cardNumber[10]+record.cardNumber[11])<9">高二</template>
          <template v-else-if="record.grade===17&&parseInt(record.cardNumber[10]+record.cardNumber[11])>=9">高二
          </template>
          <template v-else-if="record.grade===17&&parseInt(record.cardNumber[10]+record.cardNumber[11])<9">高三</template>
          <template v-else-if="record.grade>=18">退役</template>
          <template v-else>-</template>
        </div>


        <span slot="action" slot-scope="text, record">
          <a @click="handleEdit(record)">编辑</a>

          <a-divider type="vertical"/>
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
    import {JeecgListMixin} from '@/mixins/JeecgListMixin'
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
    import AFormItem from "ant-design-vue/es/form/FormItem"
    import {initDictOptions} from '@/components/dict/JDictSelectUtil'
    import AInputSearch from "ant-design-vue/es/input/Search";

    export default {
        name: "SportsmanPersonalArchivesList",
        mixins: [JeecgListMixin],
        components: {
            AInputSearch,
            AFormItem,
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
        data() {
            return {
                date: new Date(),
                refKeys: ['sportsmanRank', 'sportsmanResume', 'sportsmanFamilySituation', 'sportsmanData', 'developSituation', 'bodyType', 'bodyAbility',],
                description: '档案管理管理页面',
                // 表头
                columns: [
                    {
                        title: '#',
                        dataIndex: '',
                        key: 'rowIndex',
                        width: 60,
                        align: "center",
                        customRender: function (t, r, index) {
                            return parseInt(index) + 1;
                        }
                    },
                    {
                        title: '姓名',
                        align: "center",
                        dataIndex: 'name',
                    },
                    {
                        title: '性别',
                        align: "center",
                        dataIndex: 'cardNumber',
                        customRender: function (t) {
                          return t=== null ? '-':parseInt(t[16]) % 2 == 0 ? "女" : "男";
                        },
                    },
                    {
                        title: '出生日期',
                        align: "center",
                        dataIndex: 'cardNumber',
                        customRender: function (t, r) {
                          return t=== null ? '-':r.cardNumber[6] + r.cardNumber[7] + r.cardNumber[8] + r.cardNumber[9] + '-' + r.cardNumber[10] + r.cardNumber[11] + '-' + r.cardNumber[12] + r.cardNumber[13];
                        }
                    },
                    {
                        title: '年级',
                        align: "center",
                        scopedSlots: {customRender: 'birthday'},
                    },
                    {
                        title: '操作',
                        dataIndex: 'action',
                        align: "center",
                        scopedSlots: {customRender: 'action'},
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
            importExcelUrl: function () {
                return `${window._CONFIG['domianURL']}/${this.url.importExcelUrl}`;
            },
        },
        created() {
            this.initDictConfig();
        },
        mounted() {
            let _this = this;
            this.timer = setInterval(function () {
                _this.date = new Date().toLocaleString();
            });
        },
        beforeDestroy: function () {
            if (this.timer) {
                clearInterval(this.timer);
            }
        },
        methods: {
            initDictConfig() {
                //初始化字典 - 性别
                initDictOptions('grade').then((res) => {
                    if (res.success) {
                        this.grade = res.result;
                    }
                });
            },
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
            searchQuery: function () {
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