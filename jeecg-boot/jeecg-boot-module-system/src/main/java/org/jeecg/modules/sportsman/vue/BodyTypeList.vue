<template>
  <a-card :bordered="false">

    <!-- 操作按钮区域 -->
    <div class="table-operator" :md="24" :sm="24" style="margin: 0px 0px 20px 0px">
      <a-button @click="handleAdd" type="primary" icon="plus">新增</a-button>

      <a-dropdown v-if="selectedRowKeys.length > 0">
        <a-menu slot="overlay">
          <a-menu-item key="1" @click="batchDel">
            <a-icon type="delete"/>删除
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
        <i class="anticon anticon-info-circle ant-alert-icon"></i> 已选择 <a style="font-weight: 600">{{ selectedRowKeys.length }}</a>项
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
    <BodyType-modal ref="modalForm" @ok="modalFormOk"/>
  </a-card>
</template>

<script>
  import BodyTypeModal from './modules/BodyTypeModal'
  import {JeecgListMixin} from '@/mixins/JeecgListMixin'
  import {getAction} from '@/api/manage'

  export default {
    name: "BodyTypeList",
    mixins: [JeecgListMixin],
    components: {
      BodyTypeModal,
    },
    data() {
      return {
        description: '信息',
        // 表头
        columns: [
          {
            title: '臂',
            align:"center",
            dataIndex: 'arm'
          },
          {
            title: '背',
            align:"center",
            dataIndex: 'back'
          },
          {
            title: '腹',
            align:"center",
            dataIndex: 'belly'
          },
          {
            title: '大腿围(cm)',
            align:"center",
            dataIndex: 'bigLeg'
          },
          {
            title: '体重(kg)',
            align:"center",
            dataIndex: 'bodyWeight'
          },
          {
            title: '腰围(cm)',
            align:"center",
            dataIndex: 'butt'
          },
          {
            title: '骨盆宽(cm)',
            align:"center",
            dataIndex: 'guPenKuan'
          },
          {
            title: '身高(cm)',
            align:"center",
            dataIndex: 'height'
          },
          {
            title: '踝围(cm)',
            align:"center",
            dataIndex: 'huaiWei'
          },
          {
            title: '肩宽(cm)',
            align:"center",
            dataIndex: 'jianKuan'
          },
          {
            title: '上肢长(cm)',
            align:"center",
            dataIndex: 'shangZhiChang'
          },
          {
            title: '坐高(cm)',
            align:"center",
            dataIndex: 'sitHeight'
          },
          {
            title: '小腿围(cm)',
            align:"center",
            dataIndex: 'smallLeg'
          },
          {
            title: '测试日期',
            align:"center",
            dataIndex: 'testDate'
          },
          {
            title: '腿腱长(cm)',
            align:"center",
            dataIndex: 'tuiJianChang'
          },
          {
            title: '下肢长(cm)',
            align:"center",
            dataIndex: 'xiaZhiChang'
          },
          {
            title: '小腿长(cm)',
            align:"center",
            dataIndex: 'xiaoTuiChang'
          },
          {
            title: '胸围(cm)',
            align:"center",
            dataIndex: 'xiongWei'
          },
          {
            title: '指距(cm)',
            align:"center",
            dataIndex: 'zhiJu'
          },
         {
          title: '操作',
          key: 'operation',
          align: "center",
          width: 130,
          scopedSlots: {customRender: 'action'},
        }],
        url: {
          list: "/sportsman/sportsmanPersonalArchives/listBodyTypeByMainId",
          delete: "/sportsman/sportsmanPersonalArchives/deleteBodyType",
          deleteBatch: "/sportsman/sportsmanPersonalArchives/deleteBatchBodyType",
        }
      }
    },
    methods: {
      loadData(arg) {
        if (arg === 1) {
          this.ipagination.current = 1;
        }
        var params = this.getQueryParams();
        getAction(this.url.list, {mainId: params.mainId}).then((res) => {
          if (res.success) {
            this.dataSource = res.result;
          } else {
            this.dataSource = null;
          }
        })
      },
      getMain(mainId) {
        this.queryParam.mainId = mainId;
        this.loadData(1);
      },
      handleAdd: function () {
        this.$refs.modalForm.add(this.queryParam.mainId);
        this.$refs.modalForm.title = "添加身体形态指标";
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
