<template>
  <a-card :bordered="false">
    <div class="page-header-index-wide">

      <a-row>
        <a-col :span="24">
          <a-card :loading="loading" activeTabKey="id" :bordered="false" title="训练成绩报表" :style="{ marginLeft:'10px',marginTop: '24px' }">
            <a-select defaultValue="7" v-model="timeSelectOption"  @change="onChange" style="width: 100px" >
              <a-select-option value="7">近一周</a-select-option>
              <a-select-option value="30">近一月</a-select-option>
              <a-select-option value="90">近一季度</a-select-option>
              <a-select-option value="180">近半年</a-select-option>
              <a-select-option value="365">近一年</a-select-option>
            </a-select>
            <line-chart-multid :fields="trainingFields" :dataSource="trainingInfo1000"></line-chart-multid>
          </a-card>
        </a-col>
      </a-row>
    </div>

  </a-card>
</template>

<script>
    import {JeecgListMixin} from '@/mixins/JeecgListMixin'
    import {getAction} from '@/api/manage'
    import ChartCard from '@/components/ChartCard'
    import ACol from "ant-design-vue/es/grid/Col"
    import ATooltip from "ant-design-vue/es/tooltip/Tooltip"
    import MiniArea from '@/components/chart/MiniArea'
    import MiniBar from '@/components/chart/MiniBar'
    import MiniProgress from '@/components/chart/MiniProgress'
    import RankList from '@/components/chart/RankList'
    import Bar from '@/components/chart/Bar'
    import LineChartMultid from '@/components/chart/LineChartMultid'
    import HeadInfo from '@/components/tools/HeadInfo.vue'
    import Trend from '@/components/Trend'

    const rankList = []
    for (let i = 0; i < 7; i++) {
        rankList.push({
            name: '白鹭岛 ' + (i+1) + ' 号店',
            total: 1234.56 - i * 100
        })
    }

    export default {
        name: "D25aList",
        mixins: [JeecgListMixin],
        components: {
            ATooltip,
            ACol,
            ChartCard,
            MiniArea,
            MiniBar,
            MiniProgress,
            RankList,
            Bar,
            Trend,
            LineChartMultid,
            HeadInfo
        },
        data() {
            return {
                columns: [],
                url: {
                    list: "/statistics/statistics/listD1000aByMainId",
                    chart:"/statistics/statistics/trainingInfo1000",
                    delete: "/statistics/statistics/deleteD1000a",
                    deleteBatch: "/statistics/statistics/deleteBatchD1000a",
                },
                loading: true,
                center: null,
                barData:[],
                loginfo:{},
                timeSelectOption:"7",
                trainingFields:['1000米仰泳','1000米自由泳','1000米蝶泳','1000米蛙泳','1000米自测'],
                trainingInfo1000:[],
                indicator: <a-icon type="loading" style="font-size: 24px" spin />
        }
        },
        created() {
            setTimeout(() => {
                this.loading = !this.loading
            }, 1000)
        },
        methods: {
            onChange (selectedValue) {
                var params = this.getQueryParams();
                if(params.mainId!=null) {
                    getAction(this.url.chart, {mainId: params.mainId, selectValue: selectedValue}).then((res) => {
                        if (res.success) {
                            this.trainingInfo1000 = res.result;
                        } else {
                            this.trainingInfo1000 = null;
                        }
                    })
                }
            },
            loadData(arg) {
                if (arg === 1) {
                    this.ipagination.current = 1;
                }
                var params = this.getQueryParams();
                if(params.mainId!=null){
                    getAction(this.url.chart, {mainId: params.mainId, selectValue:this.timeSelectOption}).then((res) => {
                        if (res.success) {
                            this.trainingInfo1000 = res.result;
                        } else {
                            this.trainingInfo1000 = null;
                        }
                    })
                }

            },
            getMain(mainId) {
                this.queryParam.mainId = mainId;
                this.loadData(1);
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
