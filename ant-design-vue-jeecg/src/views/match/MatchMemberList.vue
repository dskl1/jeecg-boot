<template>
  <a-card :bordered="false" :class="'cust-erp-sub-tab'">

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
            <a-form-item label="出生日期">
              <j-input placeholder="请输入出生日期" v-model="queryParam.cardNumber"></j-input>
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

        <template slot="avatarslot" slot-scope="text, record, index">
          <div class="anty-img-wrap">
            <a-avatar :src="getAvatarView(record.avatar)"/>
          </div>
        </template>

        <div slot="age" slot-scope="text,record" style="margin: 0">
          <template v-if="record.cardNumber">{{
              parseInt(date[0] + date[1] + date[2] + date[3]) - parseInt(record.cardNumber[6] + record.cardNumber[7] + record.cardNumber[8] + record.cardNumber[9]) + 1
            }}
          </template>

        </div>

      </a-table>
    </div>
    <!-- table区域-end -->

    <a-tabs defaultActiveKey="1">
      <a-tab-pane tab="比赛名称" :key="refKeys[0]" :forceRender="true">
        <match-total-List ref="MatchTotalList"></match-total-List>
      </a-tab-pane>
    </a-tabs>

    <!-- 表单区域 -->
    <matchMember-modal ref="modalForm" @ok="modalFormOk"/>

  </a-card>
</template>

<script>
import { JeecgListMixin } from '@/mixins/JeecgListMixin'
import MatchMemberModal from './modules/MatchMemberModal'
import { deleteAction } from '@/api/manage'
import MatchTotalList from './MatchTotalList'
import MatchTotalModal from './modules/MatchTotalModal'
import JInput from '@/components/jeecg/JInput'
import { putAction, getFileAccessHttpUrl } from '@/api/manage'

//高级查询modal需要参数
const superQueryFieldList = [{
  type: 'year',
  value: 'cardNumber[16]',
  text: '出生日期'
}, {
  type: 'string',
  value: 'name',
  text: '姓名'
}, {
  type: 'int',
  value: 'age',
  text: '年龄'
}]

const key = 'updatable'


export default {
  name: 'MatchMemberList',
  mixins: [JeecgListMixin],
  components: {
    MatchMemberModal,
    MatchTotalModal,
    MatchTotalList,
    JInput
  },
  data() {
    return {
      date: new Date(),
      refKeys: ['matchTotal',],
      description: '运动员管理页面',
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
      ],

      //操作url
      type: 'radio',
      url: {
        imgerver: window._CONFIG['staticDomainURL'],
        list: '/match/matchMember/list',
        delete: '/match/matchMember/delete',
        deleteBatch: '/match/matchMember/deleteBatch',
        exportXlsUrl: 'match/matchMember/exportXls',
        importExcelUrl: 'match/matchMember/importExcel',
      },
      fieldList: superQueryFieldList
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
      return getFileAccessHttpUrl(avatar, this.url.imgerver, 'http')
    },
    clickThenCheck(record) {
      return {
        on: {
          click: () => {
            this.onSelectChange(record.id.split(','), [record])
            this.$notification.open({
              key,
              message: record.name,
              style: {
                width: '250px',
                marginTop: `34px`,
                marginLeft: `130px`,

              },
              duration: 0,
              icon: <a-avatar src = { window._CONFIG['staticDomainURL'] + '/' + record.avatar }
            />,
          })

          }
        }
      }
    },
    onSelectChange(selectedRowKeys, selectionRows) {
      this.selectedRowKeys = selectedRowKeys
      this.selectionRows = selectionRows
      this.$refs.MatchTotalList.getMain(this.selectedRowKeys[0])
    },
    onClearSelected() {
      this.selectedRowKeys = []
      this.selectionRows = []
      this.$refs.MatchTotalList.queryParam.mainId = null
      this.$refs.MatchTotalList.loadData()
      this.$refs.MatchTotalList.selectedRowKeys = []
      this.$refs.MatchTotalList.selectionRows = []
    },

    handleDelete: function (id) {
      var that = this
      deleteAction(that.url.delete, { id: id }).then((res) => {
        if (res.success) {
          that.$message.success(res.message)
          that.loadData()
          this.$refs.MatchTotalList.loadData()
        } else {
          that.$message.warning(res.message)
        }
      })
    },
    searchQuery: function () {
      this.selectedRowKeys = []
      this.selectionRows = []
      this.$refs.MatchTotalList.queryParam.mainId = null
      this.$refs.MatchTotalList.loadData()
      this.$refs.MatchTotalList.selectedRowKeys = []
      this.$refs.MatchTotalList.selectionRows = []
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

/** Button按钮间距 */
.ant-btn {
  margin-left: 3px
}

.ant-table {
  position: fixed;
  top: 0px;
  margin-left: 100px;
  width: 1000px;
  z-index: 1000;
}
</style>