<template>
  <a-card :bordered="false">

    <!-- 操作按钮区域 -->
    <div class="table-operator" :md="24" :sm="24" style="margin: 0px 0px 20px 0px">
      <a-button @click="handleAdd" type="primary" icon="plus">新增</a-button>

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

        <span slot="action" slot-scope="text, record">
          <a @click="handleEdit(record)">编辑</a>
          <a-divider type="vertical"/>
          <a-dropdown>
            <a class="ant-dropdown-link">
              更多 <a-icon type="down"/>
            </a>
            <a-menu slot="overlay">
              <a-menu-item>
                <a href="javascript:;" @click="handleDetail(record)">详情</a>
              </a-menu-item>
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
    <DevelopSituation-modal ref="modalForm" @ok="modalFormOk"/>
  </a-card>
</template>

<script>
  import DevelopSituationModal from './modules/DevelopSituationModal'
  import { JeecgListMixin } from '@/mixins/JeecgListMixin'
  import { getAction } from '@/api/manage'

  export default {
    name: 'DevelopSituationList',
    mixins: [JeecgListMixin],
    components: {
      DevelopSituationModal,
    },
    data () {
      return {
        description: '信息',
        // 表头
        columns: [
          {
            title: '日期',
            align: 'center',
            dataIndex: 'testDate'
          },
          {
            title: '晨脉',
            align: 'center',
            dataIndex: 'chenMai'
          },
          {
            title: '尿蛋白',
            align: 'center',
            dataIndex: 'niaoDanBai'
          },
          {
            title: '血压',
            align: 'center',
            dataIndex: 'xueYa'
          },
          {
            title: '血色素',
            align: 'center',
            dataIndex: 'xueSeSu'
          },
          {
            title: '肺活量',
            align: 'center',
            dataIndex: 'feiHuoLiang'
          },
          {
            title: '背力',
            align: 'center',
            dataIndex: 'beiLi'
          },
          {
            title: '台阶实验',
            align: 'center',
            dataIndex: 'taiJieShiYan'
          },
          {
            title: '反应时',
            align: 'center',
            dataIndex: 'fanYingShi'
          },
          {
            title: '骨龄',
            align: 'center',
            dataIndex: 'guLing'
          },
          {
            title: '预测身高',
            align: 'center',
            dataIndex: 'forecastHeight'
          },
          {
            title: '心理',
            align: 'center',
            children: [
              {
                title: '',
                align: 'center',
                width: 30,
                dataIndex: 'psychology1'
              },
              {
                title: '',
                align: 'center',
                width: 30,
                dataIndex: 'psychology2'
              },
              {
                title: '',
                align: 'center',
                width: 30,
                dataIndex: 'psychology3'
              },
              {
                title: '',
                align: 'center',
                width: 30,
                dataIndex: 'psychology4'
              },]
          },
          {
            title: '发育类型',
            align: 'center',
            dataIndex: 'faYuType'
          },
          {
            title: '月经初潮',
            align: 'center',
            dataIndex: 'yueJingChuChao'
          },
          {
            title: '乳房',
            align: 'center',
            dataIndex: 'ruFang'
          },
          {
            title: '阴毛',
            align: 'center',
            dataIndex: 'yinMao'
          },
          {
            title: '睾丸',
            align: 'center',
            dataIndex: 'gaoWan'
          },
          {
            title: '视力',
            align: 'center',
            dataIndex: 'shiLi'
          },
          {
            title: '操作',
            key: 'operation',
            align: 'center',
            width: 100,
            scopedSlots: { customRender: 'action' },
          }],
        url: {
          list: '/sportsman/sportsmanPersonalArchives/listDevelopSituationByMainId',
          delete: '/sportsman/sportsmanPersonalArchives/deleteDevelopSituation',
          deleteBatch: '/sportsman/sportsmanPersonalArchives/deleteBatchDevelopSituation',
        }
      }
    },
    methods: {
      loadData (arg) {
        if (arg === 1) {
          this.ipagination.current = 1
        }
        var params = this.getQueryParams()
        getAction(this.url.list, { mainId: params.mainId }).then((res) => {
          if (res.success) {
            this.dataSource = res.result
          } else {
            this.dataSource = null
          }
        })
      },
      getMain (mainId) {
        this.queryParam.mainId = mainId
        this.loadData(1)
      },
      handleAdd: function () {
        this.$refs.modalForm.add(this.queryParam.mainId)
        this.$refs.modalForm.title = '添加历年机能发育情况'
      },
    }
  }
</script>
<style scoped>
  .ant-card {
    margin-left: -30px;
    margin-right: -30px;
  }
</style>
