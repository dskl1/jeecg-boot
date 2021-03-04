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

        <template slot="avatarslot" slot-scope="text, record, index">
          <div class="anty-img-wrap">
            <img :src="getAvatarView(record.avatar)"/>
          </div>
        </template>

        <div slot="age" slot-scope="text,record" style="margin: 0">
          <template v-if="record.cardNumber">{{
              record.grade =
                parseInt(date[0] + date[1] + date[2] + date[3]) - parseInt(record.cardNumber[6] + record.cardNumber[7] + record.cardNumber[8] + record.cardNumber[9]) + 1
            }}
          </template>
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
      <a-tab-pane tab="比赛报表" :key="refKeys[0]" :forceRender="true">
        <matchTotala-List ref="MatchTotalaList"></matchTotala-List>
      </a-tab-pane>
    </a-tabs>

    <!-- 表单区域 -->
    <statistic-modal ref="modalForm" @ok="modalFormOk"/>

  </a-card>
</template>

<script>
import { JeecgListMixin } from '@/mixins/JeecgListMixin'
import StatisticModal from './modules/StatisticModal'
import { deleteAction } from '@/api/manage'
import MatchTotalaList from './MatchTotalaList'
import MatchTotalaModal from './modules/MatchTotalaModal'

export default {
  name: 'StatisticList',
  mixins: [JeecgListMixin],
  components: {
    StatisticModal,
    MatchTotalaModal,
    MatchTotalaList,
  },
  data() {
    return {
      date: new Date(),
      refKeys: ['matchTotala',],
      description: '比赛报表管理页面',
      // 表头
      columns: [
        {
          title: '#',
          dataIndex: '',
          key: 'rowIndex',
          width: 60,
          align: 'center',
          customRender: function (t, r, index) {
            return parseInt(index) + 1
          }
        },
        {
          title: '照片',
          align: 'center',
          width: 50,
          scopedSlots: { customRender: 'avatarslot' }
        },
        {
          title: '姓名',
          align: 'center',
          dataIndex: 'name',
        },
        {
          title: '年龄',
          align: 'center',
          scopedSlots: { customRender: 'age' }
        },
        {
          title: '操作',
          dataIndex: 'action',
          align: "center",
          scopedSlots: {customRender: 'action'},
        }
      ],

      //操作url
      type: 'radio',
      url: {
        imgerver: window._CONFIG['domianURL'] + '/sys/common/view',
        list: '/statistics/statistic/list',
        delete: '/statistics/statistic/delete',
        deleteBatch: '/statistics/statistic/deleteBatch',
        exportXlsUrl: 'statistics/statistic/exportXls',
        importExcelUrl: 'statistics/statistic/importExcel',
      },
    }
  },
  mounted() {
    let _this = this
    this.timer = setInterval(function () {
      _this.date = new Date().toLocaleString()
    })
  },
  computed: {
    importExcelUrl: function () {
      return `${window._CONFIG['domianURL']}/${this.url.importExcelUrl}`
    }
  },
  methods: {
    getAvatarView: function (avatar) {
      return this.url.imgerver + '/' + avatar
    },
    clickThenCheck(record) {
      return {
        on: {
          click: () => {
            this.onSelectChange(record.id.split(','), [record])
          }
        }
      }
    },
    onSelectChange(selectedRowKeys, selectionRows) {
      this.selectedRowKeys = selectedRowKeys
      this.selectionRows = selectionRows
      this.$refs.MatchTotalaList.getMain(this.selectedRowKeys[0])
    },
    onClearSelected() {
      this.selectedRowKeys = []
      this.selectionRows = []
      this.$refs.MatchTotalaList.queryParam.mainId = null
      this.$refs.MatchTotalaList.loadData()
      this.$refs.MatchTotalaList.selectedRowKeys = []
      this.$refs.MatchTotalaList.selectionRows = []
    },

    handleDelete: function (id) {
      var that = this
      deleteAction(that.url.delete, { id: id }).then((res) => {
        if (res.success) {
          that.$message.success(res.message)
          that.loadData()
          this.$refs.MatchTotalaList.loadData()
        } else {
          that.$message.warning(res.message)
        }
      })
    },
    searchQuery: function () {
      this.selectedRowKeys = []
      this.selectionRows = []
      this.$refs.MatchTotalaList.queryParam.mainId = null
      this.$refs.MatchTotalaList.loadData()
      this.$refs.MatchTotalaList.selectedRowKeys = []
      this.$refs.MatchTotalaList.selectionRows = []
      this.loadData()
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

.anty-img-wrap {
  height: 50px;
  position: relative;
  width: 50px;
  text-align: center;
  border-radius: 50%;
  overflow: hidden;

img {
  height: 100%;
  width: 100%;
}

}

.anty-img-wrap > img {
  max-height: 100%;
}
</style>