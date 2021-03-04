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
          <a-col :md="6" :sm="8">
            <a-form-item label="运动项目">
              <a-input placeholder="请输入运动项目" v-model="queryParam.sportItem"></a-input>
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

    <!-- 操作按钮区域 -->
    <div class="table-operator">
      <a-button @click="handleAdd" type="primary" icon="plus">新增</a-button>
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
        <i class="anticon anticon-info-circle ant-alert-icon"></i> 已选择 <a style="font-weight: 600">{{
        selectedRowKeys.length }}</a>项
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

        <template slot="avatarslot" slot-scope="text, record, index">
          <div class="anty-img-wrap">
            <a-avatar :src="getAvatarView(record.avatar)"/>
          </div>
        </template>

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

        <template slot="fileSlot" slot-scope="text">
          <span v-if="!text" style="font-size: 12px;font-style: italic;">无此文件</span>
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

      </a-table>
    </div>
    <!-- table区域-end -->

    <!-- 表单区域 -->
    <sportsmanRegistrationEntry-modal ref="modalForm" @ok="modalFormOk"></sportsmanRegistrationEntry-modal>
  </a-card>
</template>

<script>
  import SportsmanRegistrationEntryModal from './modules/SportsmanRegistrationEntryModal'
  import { JeecgListMixin } from '@/mixins/JeecgListMixin'
  import { colAuthFilter } from '@/utils/authFilter'
  import { putAction, getFileAccessHttpUrl } from '@/api/manage'

  export default {
    name: 'SportsmanRegistrationEntryList',
    mixins: [JeecgListMixin],
    components: {
      SportsmanRegistrationEntryModal
    },
    data () {
      return {
        date: new Date(),
        description: '队员登记管理页面',
        queryParam: {},
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
            dataIndex: 'avatar',
            width: 50,
            scopedSlots: { customRender: 'avatarslot' }
            // scopedSlots: { customRender: 'fileSlot' },
          },
          {
            title: '姓名',
            align: 'center',
            dataIndex: 'name'
          },
          {
            title: '性别',
            align: 'center',
            dataIndex: 'cardNumber',
            customRender: function (t, r, index) {
              return t === null ? '-' : parseInt(t[16]) % 2 == 0 ? '女' : '男'
            },
          },
          {
            title: '出生日期',
            align: 'center',
            dataIndex: 'cardNumber',
            customRender: function (t, r) {
              return t === null ? '-' : r.cardNumber[6] + r.cardNumber[7] + r.cardNumber[8] + r.cardNumber[9] + '-' + r.cardNumber[10] + r.cardNumber[11] + '-' + r.cardNumber[12] + r.cardNumber[13]
            }
          },
          {
            title: '年级',
            align: 'center',
            scopedSlots: { customRender: 'birthday' },
          },
          // {
          //   title: '操作',
          //   key: 'operation',
          //   align: 'center',
          //   width: 130,
          //   scopedSlots: { customRender: 'action' },
          // }
        ],
        url: {
          imgerver: window._CONFIG['staticDomainURL'],
          list: '/sportsman/sportsmanRegistrationEntry/list',
          delete: '/sportsman/sportsmanRegistrationEntry/delete',
          deleteBatch: '/sportsman/sportsmanRegistrationEntry/deleteBatch',
          exportXlsUrl: 'sportsman/sportsmanRegistrationEntry/exportXls',
          importExcelUrl: 'sportsman/sportsmanRegistrationEntry/importExcel',
        },
      }
    },
    computed: {
      importExcelUrl: function () {
        return `${window._CONFIG['domianURL']}/${this.url.importExcelUrl}`
      }
    },
    created () {
      this.initDictConfig()
      this.columns = colAuthFilter(this.columns, 'perform:')
      this.loadData()
    },
    mounted () {
      let _this = this
      this.timer = setInterval(function () {
        _this.date = new Date().toLocaleString()
      })
    },
    beforeDestroy: function () {
      if (this.timer) {
        clearInterval(this.timer)
      }
    },
    methods: {
      getAvatarView: function (avatar) {
        return getFileAccessHttpUrl(avatar, this.url.imgerver, 'http')
      },
      initDictOptions () {

      },
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