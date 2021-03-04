<template>
  <a-card :bordered="false">

    <!-- table区域-begin -->
    <div>

      <a-table
        ref="table"
        size="default"
        :scroll="{x:true}"
        rowKey="id"
        :columns="columns"
        :dataSource="dataSource"
        :loading="loading"
        class="j-table-force-nowrap">

        <template slot="big" slot-scope="record,text,index">
          {{record}}
        </template>

      </a-table>
    </div>
  </a-card>
</template>

<script>

import '@/assets/less/TableExpand.less'
import { mixinDevice } from '@/utils/mixin'
import { JeecgListMixin } from '@/mixins/JeecgListMixin'
import HeadDisplayModal from './modules/HeadDisplayModal'

export default {
  name: 'HeadDisplayList',
  mixins:[JeecgListMixin, mixinDevice],
  components: {
    HeadDisplayModal
  },
  data () {
    return {
      description: '首页展示管理页面',
      // 表头
      columns: [
        {
          title:'未回款',
          align:"center",
          dataIndex: 'noRepay',
          scopedSlots: { customRender: 'big' },
        },
        {
          title:'未开票',
          align:"center",
          dataIndex: 'noInvoice',
          scopedSlots: { customRender: 'big' },
        },
        {
          title:'呆账',
          align:"center",
          dataIndex: 'longNoRepay',
          scopedSlots: { customRender: 'big' },
        },
      ],
      url: {
        list: "/salary/headDisplay/list",
        delete: "/salary/headDisplay/delete",
        deleteBatch: "/salary/headDisplay/deleteBatch",
        exportXlsUrl: "/salary/headDisplay/exportXls",
        importExcelUrl: "salary/headDisplay/importExcel",

      },
      dictOptions:{},
    }
  },
  created() {
  },
  computed: {
    importExcelUrl: function(){
      return `${window._CONFIG['domianURL']}/${this.url.importExcelUrl}`;
    },
  },
  methods: {
    initDictConfig(){
    }
  }
}
</script>
<style scoped>
@import '~@assets/less/common.less';
</style>