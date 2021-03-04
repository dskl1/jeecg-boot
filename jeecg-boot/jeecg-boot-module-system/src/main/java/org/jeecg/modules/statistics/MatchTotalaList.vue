<template>
  <a-card :bordered="false">
    <div class="page-header-index-wide">

      <a-row>
        <a-col :span="24">
          <a-card :loading="loading" activeTabKey="id" :bordered="false" title="比赛成绩报表"
                  :style="{ marginLeft:'10px',marginTop: '24px' }">
            <a-select defaultValue="36500" v-model="timeSelectOption" @change="onChange" style="width: 100px">
              <a-select-option value="30">近一月</a-select-option>
              <a-select-option value="90">近一季度</a-select-option>
              <a-select-option value="180">近半年</a-select-option>
              <a-select-option value="365">近一年</a-select-option>
              <a-select-option value="36500"></a-select-option>
            </a-select>
            <line-chart-multid :fields="trainingFields" :dataSource="trainingInfo"></line-chart-multid>
          </a-card>
        </a-col>
      </a-row>
    </div>

  </a-card>
</template>

<script>
import { JeecgListMixin } from '@/mixins/JeecgListMixin'
import { getAction } from '@/api/manage'
import ChartCard from '@/components/ChartCard'
import ACol from 'ant-design-vue/es/grid/Col'
import ATooltip from 'ant-design-vue/es/tooltip/Tooltip'
import BarMultid from '@/components/chart/BarMultid'
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
    name: '白鹭岛 ' + (i + 1) + ' 号店',
    total: 1234.56 - i * 100
  })
}

export default {
  name: 'MatchTotalaList',
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
    BarMultid,
    LineChartMultid,
    HeadInfo
  },
  data() {
    return {
      columns: [],
      url: {
        list: '/statistics/statistic/listMatchTotalaByMainId',
        chart: '/statistics/statistic/trainingInfo',
        delete: '/statistics/statistic/deleteMatchTotala',
        deleteBatch: '/statistics/statistic/deleteBatchMatchTotala',
      },
      loading: true,
      center: null,
      barData: [],
      loginfo: {},
      timeSelectOption: '3650',
      trainingFields: ['50米仰泳', '50米自由泳', '50米蝶泳', '50米蛙泳',
        '100米仰泳', '100米自由泳', '100米蝶泳', '100米蛙泳',
        '200米仰泳', '200米自由泳', '200米蝶泳', '200米蛙泳',
        '200米混合泳', '400米混合泳',
        '400米自由泳', '800米自由泳', '1500米自由泳'],
      trainingInfo: [],
      indicator: <a-icon type="loading" style="font-size: 24px" spin />
    }
  },
  created() {
    setTimeout(() => {
      this.loading = !this.loading
    }, 1000)
  },
  methods: {
    onChange(selectedValue) {
      var params = this.getQueryParams()
      if (params.mainId != null) {
        getAction(this.url.chart, { mainId: params.mainId, selectValue: selectedValue }).then((res) => {
          if (res.success) {
            this.trainingInfo = res.result
          } else {
            this.trainingInfo = null
          }
        })
      }
    },
    loadData(arg) {

      if (arg === 1) {
        this.ipagination.current = 1
      }
      var params = this.getQueryParams()
      if (params.mainId != null) {
        getAction(this.url.chart, { mainId: params.mainId, selectValue: this.timeSelectOption }).then((res) => {
          if (res.success) {
            this.trainingInfo = res.result
          } else {
            this.trainingInfo = null
          }
        })
      }

    },
    getMain(mainId) {
      this.queryParam.mainId = mainId
      this.loadData(1)
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
