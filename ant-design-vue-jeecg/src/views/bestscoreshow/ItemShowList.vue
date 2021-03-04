<template>
  <a-card :bordered="false">

    <!-- 操作按钮区域 -->
    <div class="table-operator" :md="24" :sm="24" style="margin: 0px 0px 20px 0px">

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

        <div slot="level" slot-scope="text,record" style="margin: 0">
          <template v-if="record.cardNumber[16]%2===1">
            <template
              v-if="record.matchItem==='50米自由泳'&&record.minScore<=3350&&record.minScore>2650&&record.distance==='25'">
              当前三级,距二级差{{parseInt((record.minScore-2650) / 10000) + ":" + parseInt((record.minScore-2650) / 1000 % 10) +
              parseInt((record.minScore-2650) / 100 % 10) + "." + parseInt((record.minScore-2650) / 10 % 10) +
              parseInt((record.minScore-2650) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='50米自由泳'&&record.minScore<=3450&&record.minScore>2750&&record.distance==='50'">
              当前三级,距二级差{{parseInt((record.minScore-2750) / 10000) + ":" + parseInt((record.minScore-2750) / 1000 % 10) +
              parseInt((record.minScore-2750) / 100 % 10) + "." + parseInt((record.minScore-2750) / 10 % 10) +
              parseInt((record.minScore-2750) % 10)}}
            </template>
            <template
              v-if="record.matchItem==='50米自由泳'&&record.minScore>3350&&record.distance==='25'">
              距三级差{{parseInt((record.minScore-3350) / 10000) + ":" + parseInt((record.minScore-3350) / 1000 % 10) +
              parseInt((record.minScore-3350) / 100 % 10) + "." + parseInt((record.minScore-3350) / 10 % 10) +
              parseInt((record.minScore-3350) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='50米自由泳'&&record.minScore>3450&&record.distance==='50'">
              距三级差{{parseInt((record.minScore-3450) / 10000) + ":" + parseInt((record.minScore-3450) / 1000 % 10) +
              parseInt((record.minScore-3450) / 100 % 10) + "." + parseInt((record.minScore-3450) / 10 % 10) +
              parseInt((record.minScore-3450) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='50米自由泳'&&record.minScore<=2650&&record.minScore>2350&&record.distance==='25'">
              当前二级,距一级差{{parseInt((record.minScore-2350) / 10000) + ":" + parseInt((record.minScore-2350) / 1000 % 10) +
              parseInt((record.minScore-2350) / 100 % 10) + "." + parseInt((record.minScore-2350) / 10 % 10) +
              parseInt((record.minScore-2350) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='50米自由泳'&&record.minScore<=2750&&record.minScore>2450&&record.distance==='50'">
              当前二级,距一级差{{parseInt((record.minScore-2450) / 10000) + ":" + parseInt((record.minScore-2450) / 1000 % 10) +
              parseInt((record.minScore-2450) / 100 % 10) + "." + parseInt((record.minScore-2450) / 10 % 10) +
              parseInt((record.minScore-2450) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='50米自由泳'&&record.minScore<=2350&&record.minScore>2228&&record.distance==='25'">
              当前一级,距运动健将差{{parseInt((record.minScore-2228) / 10000) + ":" + parseInt((record.minScore-2228) / 1000 % 10)
              + parseInt((record.minScore-2228) / 100 % 10) + "." + parseInt((record.minScore-2228) / 10 % 10) +
              parseInt((record.minScore-2228) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='50米自由泳'&&record.minScore<=2450&&record.minScore>2328&&record.distance==='50'">
              当前一级,距运动健将差{{parseInt((record.minScore-2328) / 10000) + ":" + parseInt((record.minScore-2328) / 1000 % 10)
              + parseInt((record.minScore-2328) / 100 % 10) + "." + parseInt((record.minScore-2328) / 10 % 10) +
              parseInt((record.minScore-2328) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='50米自由泳'&&record.minScore<=2228&&record.minScore>2095&&record.distance==='25'">
              当前运动健将,距国际级健将差{{parseInt((record.minScore-2095) / 10000) + ":" + parseInt((record.minScore-2095) / 1000 %
              10) + parseInt((record.minScore-2095) / 100 % 10) + "." + parseInt((record.minScore-2095) / 10 % 10) +
              parseInt((record.minScore-2095) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='50米自由泳'&&record.minScore<=2328&&record.minScore>2195&&record.distance==='50'">
              当前运动健将,距国际级健将差{{parseInt((record.minScore-2195) / 10000) + ":" + parseInt((record.minScore-2195) / 1000 %
              10) + parseInt((record.minScore-2195) / 100 % 10) + "." + parseInt((record.minScore-2195) / 10 % 10) +
              parseInt((record.minScore-2195) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='50米自由泳'&&record.minScore<=2095&&record.minScore>0&&record.distance==='25'">
              当前国际级健将
            </template>
            <template
              v-else-if="record.matchItem==='50米自由泳'&&record.minScore<=2195&&record.minScore>0&&record.distance==='50'">
              当前国际级健将
            </template>
            <template
              v-else-if="record.matchItem==='100米自由泳'&&record.distance==='25'&&record.minScore<=4689&&record.minScore>0">
              当前国际级健将
            </template>
            <template
              v-else-if="record.matchItem==='100米自由泳'&&record.distance==='50'&&record.minScore<=4839&&record.minScore>0">
              当前国际级健将
            </template>
            <template
              v-else-if="record.matchItem==='100米自由泳'&&record.distance==='25'&&record.minScore<=5000&&record.minScore>4689">
              当前运动健将,距国际级健将差{{parseInt((record.minScore-4689) / 10000) + ":" + parseInt((record.minScore-4689) / 1000 %
              10) + parseInt((record.minScore-4689) / 100 % 10) + "." + parseInt((record.minScore-4689) / 10 % 10) +
              parseInt((record.minScore-4689) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='100米自由泳'&&record.distance==='50'&&record.minScore<=5150&&record.minScore>4839">
              当前运动健将,距国际级健将差{{parseInt((record.minScore-4839) / 10000) + ":" + parseInt((record.minScore-4839) / 1000 %
              10) + parseInt((record.minScore-4839) / 100 % 10) + "." + parseInt((record.minScore-4839) / 10 % 10) +
              parseInt((record.minScore-4839) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='100米自由泳'&&record.distance==='25'&&record.minScore<=5400&&record.minScore>5000">
              当前一级,距运动健将差{{parseInt((record.minScore-5000) / 10000) + ":" + parseInt((record.minScore-5000) / 1000 % 10)
              + parseInt((record.minScore-5000) / 100 % 10) + "." + parseInt((record.minScore-5000) / 10 % 10) +
              parseInt((record.minScore-5000) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='100米自由泳'&&record.distance==='50'&&record.minScore<=5550&&record.minScore>5150">
              当前一级,距运动健将差{{parseInt((record.minScore-5150) / 10000) + ":" + parseInt((record.minScore-5150) / 1000 % 10)
              + parseInt((record.minScore-5150) / 100 % 10) + "." + parseInt((record.minScore-5150) / 10 % 10) +
              parseInt((record.minScore-5150) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='100米自由泳'&&record.distance==='25'&&record.minScore<=10350&&record.minScore>5400">
              当前二级,距一级差{{parseInt((record.minScore-5400) / 10000) + ":" + parseInt((record.minScore-5400) / 1000 % 10) +
              parseInt((record.minScore-5400) / 100 % 10) + "." + parseInt((record.minScore-5400) / 10 % 10) +
              parseInt((record.minScore-5400) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='100米自由泳'&&record.distance==='50'&&record.minScore<=10500&&record.minScore>5550">
              当前二级,距一级差{{parseInt((record.minScore-5550) / 10000) + ":" + parseInt((record.minScore-5550) / 1000 % 10) +
              parseInt((record.minScore-5550) / 100 % 10) + "." + parseInt((record.minScore-5550) / 10 % 10) +
              parseInt((record.minScore-5550) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='100米自由泳'&&record.distance==='25'&&record.minScore<=12050&&record.minScore>10350">
              当前三级,距二级差{{parseInt((record.minScore-10350) / 10000) + ":" + parseInt((record.minScore-10350) / 1000 % 10)
              + parseInt((record.minScore-10350) / 100 % 10) + "." + parseInt((record.minScore-10350) / 10 % 10) +
              parseInt((record.minScore-10350) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='100米自由泳'&&record.distance==='50'&&record.minScore<=12200&&record.minScore>10500">
              当前三级,距二级差{{parseInt((record.minScore-10500) / 10000) + ":" + parseInt((record.minScore-10500) / 1000 % 10)
              + parseInt((record.minScore-10500) / 100 % 10) + "." + parseInt((record.minScore-10500) / 10 % 10) +
              parseInt((record.minScore-10500) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='100米自由泳'&&record.distance==='25'&&record.minScore>12050">
              距三级差{{parseInt((record.minScore-12050) / 10000) + ":" + parseInt((record.minScore-12050) / 1000 % 10)
              + parseInt((record.minScore-12050) / 100 % 10) + "." + parseInt((record.minScore-12050) / 10 % 10) +
              parseInt((record.minScore-12050) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='100米自由泳'&&record.distance==='50'&&record.minScore>12200">
              距三级差{{parseInt((record.minScore-12200) / 10000) + ":" + parseInt((record.minScore-12200) / 1000 % 10)
              + parseInt((record.minScore-12200) / 100 % 10) + "." + parseInt((record.minScore-12200) / 10 % 10) +
              parseInt((record.minScore-12200) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='200米自由泳'&&record.distance==='25'&&record.minScore<=14340&&record.minScore>0">
              当前国际级健将
            </template>
            <template
              v-else-if="record.matchItem==='200米自由泳'&&record.distance==='50'&&record.minScore<=14690&&record.minScore>0">
              当前国际级健将
            </template>
            <template
              v-else-if="record.matchItem==='200米自由泳'&&record.distance==='25'&&record.minScore<=14810&&record.minScore>14340">
              当前运动健将,距国际级健将差{{parseInt((record.minScore-14340) / 10000) + ":" + parseInt((record.minScore-14340) / 1000
              % 10)
              + parseInt((record.minScore-14340) / 100 % 10) + "." + parseInt((record.minScore-14340) / 10 % 10) +
              parseInt((record.minScore-14340) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='200米自由泳'&&record.distance==='50'&&record.minScore<=15150&&record.minScore>14690">
              当前运动健将,距国际级健将差{{parseInt((record.minScore-14690) / 10000) + ":" + parseInt((record.minScore-14690) / 1000
              % 10)
              + parseInt((record.minScore-14690) / 100 % 10) + "." + parseInt((record.minScore-14690) / 10 % 10) +
              parseInt((record.minScore-14690) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='200米自由泳'&&record.distance==='25'&&record.minScore<=15950&&record.minScore>14810">
              当前一级,距运动健将差{{parseInt((record.minScore-14810) / 10000) + ":" + parseInt((record.minScore-14810) / 1000 %
              10)
              + parseInt((record.minScore-14810) / 100 % 10) + "." + parseInt((record.minScore-14810) / 10 % 10) +
              parseInt((record.minScore-14810) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='200米自由泳'&&record.distance==='50'&&record.minScore<=20300&&record.minScore>15150">
              当前一级,距运动健将差{{parseInt((record.minScore-15150) / 10000) + ":" + parseInt((record.minScore-15150) / 1000 %
              10)
              + parseInt((record.minScore-15150) / 100 % 10) + "." + parseInt((record.minScore-15150) / 10 % 10) +
              parseInt((record.minScore-15150) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='200米自由泳'&&record.distance==='25'&&record.minScore<=21950&&record.minScore>15950">
              当前二级,距一级差{{parseInt((record.minScore-15950) / 10000) + ":" + parseInt((record.minScore-15950) / 1000 % 10)
              + parseInt((record.minScore-15950) / 100 % 10) + "." + parseInt((record.minScore-15950) / 10 % 10) +
              parseInt((record.minScore-15950) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='200米自由泳'&&record.distance==='50'&&record.minScore<=22300&&record.minScore>20300">
              当前二级,距一级差{{parseInt((record.minScore-20300) / 10000) + ":" + parseInt((record.minScore-20300) / 1000 % 10)
              + parseInt((record.minScore-20300) / 100 % 10) + "." + parseInt((record.minScore-20300) / 10 % 10) +
              parseInt((record.minScore-20300) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='200米自由泳'&&record.distance==='25'&&record.minScore<=25250&&record.minScore>21950">
              当前三级,距二级差{{parseInt((record.minScore-21950) / 10000) + ":" + parseInt((record.minScore-21950) / 1000 % 10)
              + parseInt((record.minScore-21950) / 100 % 10) + "." + parseInt((record.minScore-21950) / 10 % 10) +
              parseInt((record.minScore-21950) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='200米自由泳'&&record.distance==='50'&&record.minScore<=25600&&record.minScore>22300">
              当前三级,距二级差{{parseInt((record.minScore-22300) / 10000) + ":" + parseInt((record.minScore-22300) / 1000 % 10)
              + parseInt((record.minScore-22300) / 100 % 10) + "." + parseInt((record.minScore-22300) / 10 % 10) +
              parseInt((record.minScore-22300) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='200米自由泳'&&record.distance==='25'&&record.minScore>25250">
              距三级差{{parseInt((record.minScore-25250) / 10000) + ":" + parseInt((record.minScore-25250) / 1000 % 10)
              + parseInt((record.minScore-25250) / 100 % 10) + "." + parseInt((record.minScore-25250) / 10 % 10) +
              parseInt((record.minScore-25250) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='200米自由泳'&&record.distance==='50'&&record.minScore>25600">
              距三级差{{parseInt((record.minScore-25600) / 10000) + ":" + parseInt((record.minScore-25600) / 1000 % 10)
              + parseInt((record.minScore-25600) / 100 % 10) + "." + parseInt((record.minScore-25600) / 10 % 10) +
              parseInt((record.minScore-25600) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='400米自由泳'&&record.distance==='25'&&record.minScore<=34080&&record.minScore>0">
              当前国际级健将
            </template>
            <template
              v-else-if="record.matchItem==='400米自由泳'&&record.distance==='50'&&record.minScore<=34780&&record.minScore>0">
              当前国际级健将
            </template>
            <template
              v-else-if="record.matchItem==='400米自由泳'&&record.distance==='25'&&record.minScore<=35160&&record.minScore>34080">
              当前运动健将,距国际级健将差{{parseInt((record.minScore-34080) / 10000) + ":" + parseInt((record.minScore-34080) / 1000
              % 10)
              + parseInt((record.minScore-34080) / 100 % 10) + "." + parseInt((record.minScore-34080) / 10 % 10) +
              parseInt((record.minScore-34080) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='400米自由泳'&&record.distance==='50'&&record.minScore<=35860&&record.minScore>34780">
              当前运动健将,距国际级健将差{{parseInt((record.minScore-34780) / 10000) + ":" + parseInt((record.minScore-34780) / 1000
              % 10)
              + parseInt((record.minScore-34780) / 100 % 10) + "." + parseInt((record.minScore-34780) / 10 % 10) +
              parseInt((record.minScore-34780) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='400米自由泳'&&record.distance==='25'&&record.minScore<=41400&&record.minScore>35160">
              当前一级,距运动健将差{{parseInt((record.minScore-35160) / 10000) + ":" + parseInt((record.minScore-35160) / 1000 %
              10)
              + parseInt((record.minScore-35160) / 100 % 10) + "." + parseInt((record.minScore-35160) / 10 % 10) +
              parseInt((record.minScore-35160) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='400米自由泳'&&record.distance==='50'&&record.minScore<=42100&&record.minScore>35860">
              当前一级,距运动健将差{{parseInt((record.minScore-35860) / 10000) + ":" + parseInt((record.minScore-35860) / 1000 %
              10)
              + parseInt((record.minScore-35860) / 100 % 10) + "." + parseInt((record.minScore-35860) / 10 % 10) +
              parseInt((record.minScore-35860) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='400米自由泳'&&record.distance==='25'&&record.minScore<=45900&&record.minScore>41400">
              当前二级,距一级差{{parseInt((record.minScore-41400) / 10000) + ":" + parseInt((record.minScore-41400) / 1000 % 10)
              + parseInt((record.minScore-41400) / 100 % 10) + "." + parseInt((record.minScore-41400) / 10 % 10) +
              parseInt((record.minScore-41400) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='400米自由泳'&&record.distance==='50'&&record.minScore<=50600&&record.minScore>42100">
              当前二级,距一级差{{parseInt((record.minScore-42100) / 10000) + ":" + parseInt((record.minScore-42100) / 1000 % 10)
              + parseInt((record.minScore-42100) / 100 % 10) + "." + parseInt((record.minScore-42100) / 10 % 10) +
              parseInt((record.minScore-42100) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='400米自由泳'&&record.distance==='25'&&record.minScore<=61150&&record.minScore>45900">
              当前三级,距二级差{{parseInt((record.minScore-45900) / 10000) + ":" + parseInt((record.minScore-45900) / 1000 % 10)
              + parseInt((record.minScore-45900) / 100 % 10) + "." + parseInt((record.minScore-45900) / 10 % 10) +
              parseInt((record.minScore-45900) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='400米自由泳'&&record.distance==='50'&&record.minScore<=61600&&record.minScore>50600">
              当前三级,距二级差{{parseInt((record.minScore-50600) / 10000) + ":" + parseInt((record.minScore-50600) / 1000 % 10)
              + parseInt((record.minScore-50600) / 100 % 10) + "." + parseInt((record.minScore-50600) / 10 % 10) +
              parseInt((record.minScore-50600) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='400米自由泳'&&record.distance==='25'&&record.minScore>61150">
              距三级差{{parseInt((record.minScore-61150) / 10000) + ":" + parseInt((record.minScore-61150) / 1000 % 10)
              + parseInt((record.minScore-61150) / 100 % 10) + "." + parseInt((record.minScore-61150) / 10 % 10) +
              parseInt((record.minScore-61150) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='400米自由泳'&&record.distance==='50'&&record.minScore>61600">
              距三级差{{parseInt((record.minScore-61600) / 10000) + ":" + parseInt((record.minScore-61600) / 1000 % 10)
              + parseInt((record.minScore-61600) / 100 % 10) + "." + parseInt((record.minScore-61600) / 10 % 10) +
              parseInt((record.minScore-61600) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='800米自由泳'&&record.distance==='25'&&record.minScore<=74610&&record.minScore>0">
              当前国际级健将
            </template>
            <template
              v-else-if="record.matchItem==='800米自由泳'&&record.distance==='50'&&record.minScore<=80010&&record.minScore>0">
              当前国际级健将
            </template>
            <template
              v-else-if="record.matchItem==='800米自由泳'&&record.distance==='25'&&record.minScore<=81000&&record.minScore>74610">
              当前运动健将,距国际级健将差{{parseInt((record.minScore-74610) / 10000) + ":" + parseInt((record.minScore-74610) / 1000
              % 10)
              + parseInt((record.minScore-74610) / 100 % 10) + "." + parseInt((record.minScore-74610) / 10 % 10) +
              parseInt((record.minScore-74610) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='800米自由泳'&&record.distance==='50'&&record.minScore<=82400&&record.minScore>80010">
              当前运动健将,距国际级健将差{{parseInt((record.minScore-80010) / 10000) + ":" + parseInt((record.minScore-80010) / 1000
              % 10)
              + parseInt((record.minScore-80010) / 100 % 10) + "." + parseInt((record.minScore-80010) / 10 % 10) +
              parseInt((record.minScore-80010) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='800米自由泳'&&record.distance==='25'&&record.minScore<=84800&&record.minScore>81000">
              当前一级,距运动健将差{{parseInt((record.minScore-81000) / 10000) + ":" + parseInt((record.minScore-81000) / 1000 %
              10)
              + parseInt((record.minScore-81000) / 100 % 10) + "." + parseInt((record.minScore-81000) / 10 % 10) +
              parseInt((record.minScore-81000) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='800米自由泳'&&record.distance==='50'&&record.minScore<=90200&&record.minScore>82400">
              当前一级,距运动健将差{{parseInt((record.minScore-82400) / 10000) + ":" + parseInt((record.minScore-82400) / 1000 %
              10)
              + parseInt((record.minScore-82400) / 100 % 10) + "." + parseInt((record.minScore-82400) / 10 % 10) +
              parseInt((record.minScore-82400) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='800米自由泳'&&record.distance==='25'&&record.minScore<=101800&&record.minScore>84800">
              当前二级,距一级差{{parseInt((record.minScore-84800) / 10000) + ":" + parseInt((record.minScore-84800) / 1000 % 10)
              + parseInt((record.minScore-84800) / 100 % 10) + "." + parseInt((record.minScore-84800) / 10 % 10) +
              parseInt((record.minScore-84800) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='800米自由泳'&&record.distance==='50'&&record.minScore<=103200&&record.minScore>90200">
              当前二级,距一级差{{parseInt((record.minScore-90200) / 10000) + ":" + parseInt((record.minScore-90200) / 1000 % 10)
              + parseInt((record.minScore-90200) / 100 % 10) + "." + parseInt((record.minScore-90200) / 10 % 10) +
              parseInt((record.minScore-90200) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='800米自由泳'&&record.distance==='25'&&record.minScore<=125800&&record.minScore>101800">
              当前三级,距二级差{{parseInt((record.minScore-101800) / 10000) + ":" + parseInt((record.minScore-101800) / 1000 %
              10)
              + parseInt((record.minScore-101800) / 100 % 10) + "." + parseInt((record.minScore-101800) / 10 % 10) +
              parseInt((record.minScore-101800) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='800米自由泳'&&record.distance==='50'&&record.minScore<=131200&&record.minScore>103200">
              当前三级,距二级差{{parseInt((record.minScore-103200) / 10000) + ":" + parseInt((record.minScore-103200) / 1000 %
              10)
              + parseInt((record.minScore-103200) / 100 % 10) + "." + parseInt((record.minScore-103200) / 10 % 10) +
              parseInt((record.minScore-103200) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='800米自由泳'&&record.distance==='25'&&record.minScore>125800">
              距三级差{{parseInt((record.minScore-125800) / 10000) + ":" + parseInt((record.minScore-125800) / 1000 %
              10)
              + parseInt((record.minScore-125800) / 100 % 10) + "." + parseInt((record.minScore-125800) / 10 % 10) +
              parseInt((record.minScore-125800) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='800米自由泳'&&record.distance==='50'&&record.minScore>131200">
              距三级差{{parseInt((record.minScore-131200) / 10000) + ":" + parseInt((record.minScore-131200) / 1000 %
              10)
              + parseInt((record.minScore-131200) / 100 % 10) + "." + parseInt((record.minScore-131200) / 10 % 10) +
              parseInt((record.minScore-131200) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='1500米自由泳'&&record.distance==='25'&&record.minScore<=144710&&record.minScore>0">
              当前国际级健将
            </template>
            <template
              v-else-if="record.matchItem==='1500米自由泳'&&record.distance==='50'&&record.minScore<=151210&&record.minScore>0">
              当前国际级健将
            </template>
            <template
              v-else-if="record.matchItem==='1500米自由泳'&&record.distance==='25'&&record.minScore<=153530&&record.minScore>144710">
              当前运动健将,距国际级健将差{{parseInt((record.minScore-144710) / 10000) + ":" + parseInt((record.minScore-144710) /
              1000 % 10)
              + parseInt((record.minScore-144710) / 100 % 10) + "." + parseInt((record.minScore-144710) / 10 % 10) +
              parseInt((record.minScore-144710) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='1500米自由泳'&&record.distance==='50'&&record.minScore<=160030&&record.minScore>151210">
              当前运动健将,距国际级健将差{{parseInt((record.minScore-151210) / 10000) + ":" + parseInt((record.minScore-151210) /
              1000 % 10)
              + parseInt((record.minScore-151210) / 100 % 10) + "." + parseInt((record.minScore-151210) / 10 % 10) +
              parseInt((record.minScore-151210) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='1500米自由泳'&&record.distance==='25'&&record.minScore<=165400&&record.minScore>153530">
              当前一级,距运动健将差{{parseInt((record.minScore-153530) / 10000) + ":" + parseInt((record.minScore-153530) / 1000 %
              10)
              + parseInt((record.minScore-153530) / 100 % 10) + "." + parseInt((record.minScore-153530) / 10 % 10) +
              parseInt((record.minScore-153530) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='1500米自由泳'&&record.distance==='50'&&record.minScore<=172000&&record.minScore>160030">
              当前一级,距运动健将差{{parseInt((record.minScore-160030) / 10000) + ":" + parseInt((record.minScore-160030) / 1000 %
              10)
              + parseInt((record.minScore-160030) / 100 % 10) + "." + parseInt((record.minScore-160030) / 10 % 10) +
              parseInt((record.minScore-160030) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='1500米自由泳'&&record.distance==='25'&&record.minScore<=195000&&record.minScore>165400">
              当前二级,距一级差{{parseInt((record.minScore-165400) / 10000) + ":" + parseInt((record.minScore-165400) / 1000 %
              10)
              + parseInt((record.minScore-165400) / 100 % 10) + "." + parseInt((record.minScore-165400) / 10 % 10) +
              parseInt((record.minScore-165400) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='1500米自由泳'&&record.distance==='50'&&record.minScore<=201500&&record.minScore>172000">
              当前二级,距一级差{{parseInt((record.minScore-172000) / 10000) + ":" + parseInt((record.minScore-172000) / 1000 %
              10)
              + parseInt((record.minScore-172000) / 100 % 10) + "." + parseInt((record.minScore-172000) / 10 % 10) +
              parseInt((record.minScore-172000) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='1500米自由泳'&&record.distance==='25'&&record.minScore<=242000&&record.minScore>195000">
              当前三级,距二级差{{parseInt((record.minScore-195000) / 10000) + ":" + parseInt((record.minScore-195000) / 1000 %
              10)
              + parseInt((record.minScore-195000) / 100 % 10) + "." + parseInt((record.minScore-195000) / 10 % 10) +
              parseInt((record.minScore-195000) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='1500米自由泳'&&record.distance==='50'&&record.minScore<=244500&&record.minScore>201500">
              当前三级,距二级差{{parseInt((record.minScore-201500) / 10000) + ":" + parseInt((record.minScore-201500) / 1000 %
              10)
              + parseInt((record.minScore-201500) / 100 % 10) + "." + parseInt((record.minScore-201500) / 10 % 10) +
              parseInt((record.minScore-201500) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='1500米自由泳'&&record.distance==='25'&&record.minScore>242000">
              距三级差{{parseInt((record.minScore-242000) / 10000) + ":" + parseInt((record.minScore-242000) / 1000 %
              10)
              + parseInt((record.minScore-242000) / 100 % 10) + "." + parseInt((record.minScore-242000) / 10 % 10) +
              parseInt((record.minScore-242000) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='1500米自由泳'&&record.distance==='50'&&record.minScore>244500">
              距三级差{{parseInt((record.minScore-244500) / 10000) + ":" + parseInt((record.minScore-244500) / 1000 %
              10)
              + parseInt((record.minScore-244500) / 100 % 10) + "." + parseInt((record.minScore-244500) / 10 % 10) +
              parseInt((record.minScore-244500) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='50米仰泳'&&record.distance==='25'&&record.minScore<=2424&&record.minScore>0">
              当前国际级健将
            </template>
            <template
              v-else-if="record.matchItem==='50米仰泳'&&record.distance==='50'&&record.minScore<=2524&&record.minScore>0">
              当前国际级健将
            </template>
            <template
              v-else-if="record.matchItem==='50米仰泳'&&record.distance==='25'&&record.minScore<=2643&&record.minScore>2424">
              当前运动健将,距国际级健将差{{parseInt((record.minScore-2424) / 10000) + ":" + parseInt((record.minScore-2424) / 1000 %
              10)
              + parseInt((record.minScore-2424) / 100 % 10) + "." + parseInt((record.minScore-2424) / 10 % 10) +
              parseInt((record.minScore-2424) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='50米仰泳'&&record.distance==='50'&&record.minScore<=2743&&record.minScore>2524">
              当前运动健将,距国际级健将差{{parseInt((record.minScore-2524) / 10000) + ":" + parseInt((record.minScore-2524) / 1000 %
              10)
              + parseInt((record.minScore-2524) / 100 % 10) + "." + parseInt((record.minScore-2524) / 10 % 10) +
              parseInt((record.minScore-2524) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='50米仰泳'&&record.distance==='25'&&record.minScore<=2950&&record.minScore>2643">
              当前一级,距运动健将差{{parseInt((record.minScore-2643) / 10000) + ":" + parseInt((record.minScore-2643) / 1000 % 10)
              + parseInt((record.minScore-2643) / 100 % 10) + "." + parseInt((record.minScore-2643) / 10 % 10) +
              parseInt((record.minScore-2643) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='50米仰泳'&&record.distance==='50'&&record.minScore<=3050&&record.minScore>2743">
              当前一级,距运动健将差{{parseInt((record.minScore-2743) / 10000) + ":" + parseInt((record.minScore-2743) / 1000 % 10)
              + parseInt((record.minScore-2743) / 100 % 10) + "." + parseInt((record.minScore-2743) / 10 % 10) +
              parseInt((record.minScore-2743) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='50米仰泳'&&record.distance==='25'&&record.minScore<=3450&&record.minScore>2950">
              当前二级,距一级差{{parseInt((record.minScore-2950) / 10000) + ":" + parseInt((record.minScore-2950) / 1000 % 10)
              + parseInt((record.minScore-2950) / 100 % 10) + "." + parseInt((record.minScore-2950) / 10 % 10) +
              parseInt((record.minScore-2950) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='50米仰泳'&&record.distance==='50'&&record.minScore<=3550&&record.minScore>3050">
              当前二级,距一级差{{parseInt((record.minScore-3050) / 10000) + ":" + parseInt((record.minScore-3050) / 1000 % 10)
              + parseInt((record.minScore-3050) / 100 % 10) + "." + parseInt((record.minScore-3050) / 10 % 10) +
              parseInt((record.minScore-3050) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='50米仰泳'&&record.distance==='25'&&record.minScore<=4200&&record.minScore>3450">
              当前三级,距二级差{{parseInt((record.minScore-3450) / 10000) + ":" + parseInt((record.minScore-3450) / 1000 % 10)
              + parseInt((record.minScore-3450) / 100 % 10) + "." + parseInt((record.minScore-3450) / 10 % 10) +
              parseInt((record.minScore-3450) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='50米仰泳'&&record.distance==='50'&&record.minScore<=4300&&record.minScore>3550">
              当前三级,距二级差{{parseInt((record.minScore-3550) / 10000) + ":" + parseInt((record.minScore-3550) / 1000 % 10)
              + parseInt((record.minScore-3550) / 100 % 10) + "." + parseInt((record.minScore-3550) / 10 % 10) +
              parseInt((record.minScore-3550) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='50米仰泳'&&record.distance==='25'&&record.minScore>4200">
              距三级差{{parseInt((record.minScore-4200) / 10000) + ":" + parseInt((record.minScore-4200) / 1000 % 10)
              + parseInt((record.minScore-4200) / 100 % 10) + "." + parseInt((record.minScore-4200) / 10 % 10) +
              parseInt((record.minScore-4200) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='50米仰泳'&&record.distance==='50'&&record.minScore>4300">
              距三级差{{parseInt((record.minScore-4300) / 10000) + ":" + parseInt((record.minScore-4300) / 1000 % 10)
              + parseInt((record.minScore-4300) / 100 % 10) + "." + parseInt((record.minScore-4300) / 10 % 10) +
              parseInt((record.minScore-4300) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='100米仰泳'&&record.distance==='25'&&record.minScore<=5292&&record.minScore>0">
              当前国际级健将
            </template>
            <template
              v-else-if="record.matchItem==='100米仰泳'&&record.distance==='50'&&record.minScore<=5392&&record.minScore>0">
              当前国际级健将
            </template>
            <template
              v-else-if="record.matchItem==='100米仰泳'&&record.distance==='25'&&record.minScore<=5745&&record.minScore>5292">
              当前运动健将,距国际级健将差{{parseInt((record.minScore-5292) / 10000) + ":" + parseInt((record.minScore-5292) / 1000 %
              10)
              + parseInt((record.minScore-5292) / 100 % 10) + "." + parseInt((record.minScore-5292) / 10 % 10) +
              parseInt((record.minScore-5292) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='100米仰泳'&&record.distance==='50'&&record.minScore<=5845&&record.minScore>5392">
              当前运动健将,距国际级健将差{{parseInt((record.minScore-5392) / 10000) + ":" + parseInt((record.minScore-5392) / 1000 %
              10)
              + parseInt((record.minScore-5392) / 100 % 10) + "." + parseInt((record.minScore-5392) / 10 % 10) +
              parseInt((record.minScore-5392) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='100米仰泳'&&record.distance==='25'&&record.minScore<=10300&&record.minScore>5745">
              当前一级,距运动健将差{{parseInt((record.minScore-5745) / 10000) + ":" + parseInt((record.minScore-5745) / 1000 % 10)
              + parseInt((record.minScore-5745) / 100 % 10) + "." + parseInt((record.minScore-5745) / 10 % 10) +
              parseInt((record.minScore-5745) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='100米仰泳'&&record.distance==='50'&&record.minScore<=10400&&record.minScore>5845">
              当前一级,距运动健将差{{parseInt((record.minScore-5845) / 10000) + ":" + parseInt((record.minScore-5845) / 1000 % 10)
              + parseInt((record.minScore-5845) / 100 % 10) + "." + parseInt((record.minScore-5845) / 10 % 10) +
              parseInt((record.minScore-5845) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='100米仰泳'&&record.distance==='25'&&record.minScore<=11300&&record.minScore>10300">
              当前二级,距一级差{{parseInt((record.minScore-10300) / 10000) + ":" + parseInt((record.minScore-10300) / 1000 % 10)
              + parseInt((record.minScore-10300) / 100 % 10) + "." + parseInt((record.minScore-10300) / 10 % 10) +
              parseInt((record.minScore-10300) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='100米仰泳'&&record.distance==='50'&&record.minScore<=11400&&record.minScore>10400">
              当前二级,距一级差{{parseInt((record.minScore-10400) / 10000) + ":" + parseInt((record.minScore-10400) / 1000 % 10)
              + parseInt((record.minScore-10400) / 100 % 10) + "." + parseInt((record.minScore-10400) / 10 % 10) +
              parseInt((record.minScore-10400) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='100米仰泳'&&record.distance==='25'&&record.minScore<=12900&&record.minScore>11300">
              当前三级,距二级差{{parseInt((record.minScore-11300) / 10000) + ":" + parseInt((record.minScore-11300) / 1000 % 10)
              + parseInt((record.minScore-11300) / 100 % 10) + "." + parseInt((record.minScore-11300) / 10 % 10) +
              parseInt((record.minScore-11300) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='100米仰泳'&&record.distance==='50'&&record.minScore<=13000&&record.minScore>11400">
              当前三级,距二级差{{parseInt((record.minScore-11400) / 10000) + ":" + parseInt((record.minScore-11400) / 1000 % 10)
              + parseInt((record.minScore-11400) / 100 % 10) + "." + parseInt((record.minScore-11400) / 10 % 10) +
              parseInt((record.minScore-11400) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='100米仰泳'&&record.distance==='25'&&record.minScore>12900">
              距三级差{{parseInt((record.minScore-12900) / 10000) + ":" + parseInt((record.minScore-12900) / 1000 % 10)
              + parseInt((record.minScore-12900) / 100 % 10) + "." + parseInt((record.minScore-12900) / 10 % 10) +
              parseInt((record.minScore-12900) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='100米仰泳'&&record.distance==='50'&&record.minScore>13000">
              距三级差{{parseInt((record.minScore-13000) / 10000) + ":" + parseInt((record.minScore-13000) / 1000 % 10)
              + parseInt((record.minScore-13000) / 100 % 10) + "." + parseInt((record.minScore-13000) / 10 % 10) +
              parseInt((record.minScore-13000) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='200米仰泳'&&record.distance==='25'&&record.minScore<=15590&&record.minScore>0">
              当前国际级健将
            </template>
            <template
              v-else-if="record.matchItem==='200米仰泳'&&record.distance==='50'&&record.minScore<=15790&&record.minScore>0">
              当前国际级健将
            </template>
            <template
              v-else-if="record.matchItem==='200米仰泳'&&record.distance==='25'&&record.minScore<=20450&&record.minScore>15590">
              当前运动健将,距国际级健将差{{parseInt((record.minScore-15590) / 10000) + ":" + parseInt((record.minScore-15590) / 1000
              % 10)
              + parseInt((record.minScore-15590) / 100 % 10) + "." + parseInt((record.minScore-15590) / 10 % 10) +
              parseInt((record.minScore-15590) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='200米仰泳'&&record.distance==='50'&&record.minScore<=20650&&record.minScore>15790">
              当前运动健将,距国际级健将差{{parseInt((record.minScore-15790) / 10000) + ":" + parseInt((record.minScore-15790) / 1000
              % 10)
              + parseInt((record.minScore-15790) / 100 % 10) + "." + parseInt((record.minScore-15790) / 10 % 10) +
              parseInt((record.minScore-15790) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='200米仰泳'&&record.distance==='25'&&record.minScore<=21600&&record.minScore>20450">
              当前一级,距运动健将差{{parseInt((record.minScore-20450) / 10000) + ":" + parseInt((record.minScore-20450) / 1000 %
              10)
              + parseInt((record.minScore-20450) / 100 % 10) + "." + parseInt((record.minScore-20450) / 10 % 10) +
              parseInt((record.minScore-20450) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='200米仰泳'&&record.distance==='50'&&record.minScore<=21800&&record.minScore>20650">
              当前一级,距运动健将差{{parseInt((record.minScore-20650) / 10000) + ":" + parseInt((record.minScore-20650) / 1000 %
              10)
              + parseInt((record.minScore-20650) / 100 % 10) + "." + parseInt((record.minScore-20650) / 10 % 10) +
              parseInt((record.minScore-20650) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='200米仰泳'&&record.distance==='25'&&record.minScore<=23900&&record.minScore>21600">
              当前二级,距一级差{{parseInt((record.minScore-21600) / 10000) + ":" + parseInt((record.minScore-21600) / 1000 % 10)
              + parseInt((record.minScore-21600) / 100 % 10) + "." + parseInt((record.minScore-21600) / 10 % 10) +
              parseInt((record.minScore-21600) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='200米仰泳'&&record.distance==='50'&&record.minScore<=24100&&record.minScore>21800">
              当前二级,距一级差{{parseInt((record.minScore-21800) / 10000) + ":" + parseInt((record.minScore-21800) / 1000 % 10)
              + parseInt((record.minScore-21800) / 100 % 10) + "." + parseInt((record.minScore-21800) / 10 % 10) +
              parseInt((record.minScore-21800) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='200米仰泳'&&record.distance==='25'&&record.minScore<=31300&&record.minScore>23900">
              当前三级,距二级差{{parseInt((record.minScore-23900) / 10000) + ":" + parseInt((record.minScore-23900) / 1000 % 10)
              + parseInt((record.minScore-23900) / 100 % 10) + "." + parseInt((record.minScore-23900) / 10 % 10) +
              parseInt((record.minScore-23900) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='200米仰泳'&&record.distance==='50'&&record.minScore<=31600&&record.minScore>24100">
              当前三级,距二级差{{parseInt((record.minScore-24100) / 10000) + ":" + parseInt((record.minScore-24100) / 1000 % 10)
              + parseInt((record.minScore-24100) / 100 % 10) + "." + parseInt((record.minScore-24100) / 10 % 10) +
              parseInt((record.minScore-24100) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='200米仰泳'&&record.distance==='25'&&record.minScore>31300">
              距三级差{{parseInt((record.minScore-31300) / 10000) + ":" + parseInt((record.minScore-31300) / 1000 % 10)
              + parseInt((record.minScore-31300) / 100 % 10) + "." + parseInt((record.minScore-31300) / 10 % 10) +
              parseInt((record.minScore-31300) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='200米仰泳'&&record.distance==='50'&&record.minScore>31600">
              距三级差{{parseInt((record.minScore-31600) / 10000) + ":" + parseInt((record.minScore-31600) / 1000 % 10)
              + parseInt((record.minScore-31600) / 100 % 10) + "." + parseInt((record.minScore-31600) / 10 % 10) +
              parseInt((record.minScore-31600) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='50米蛙泳'&&record.distance==='25'&&record.minScore<=2647&&record.minScore>0">
              当前国际级健将
            </template>
            <template
              v-else-if="record.matchItem==='50米蛙泳'&&record.distance==='50'&&record.minScore<=2747&&record.minScore>0">
              当前国际级健将
            </template>
            <template
              v-else-if="record.matchItem==='50米蛙泳'&&record.distance==='25'&&record.minScore<=2775&&record.minScore>2647">
              当前运动健将,距国际级健将差{{parseInt((record.minScore-2647) / 10000) + ":" + parseInt((record.minScore-2647) / 1000 %
              10)
              + parseInt((record.minScore-2647) / 100 % 10) + "." + parseInt((record.minScore-2647) / 10 % 10) +
              parseInt((record.minScore-2647) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='50米蛙泳'&&record.distance==='50'&&record.minScore<=2875&&record.minScore>2747">
              当前运动健将,距国际级健将差{{parseInt((record.minScore-2747) / 10000) + ":" + parseInt((record.minScore-2747) / 1000 %
              10)
              + parseInt((record.minScore-2747) / 100 % 10) + "." + parseInt((record.minScore-2747) / 10 % 10) +
              parseInt((record.minScore-2747) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='50米蛙泳'&&record.distance==='25'&&record.minScore<=3150&&record.minScore>2775">
              当前一级,距运动健将差{{parseInt((record.minScore-2775) / 10000) + ":" + parseInt((record.minScore-2775) / 1000 % 10)
              + parseInt((record.minScore-2775) / 100 % 10) + "." + parseInt((record.minScore-2775) / 10 % 10) +
              parseInt((record.minScore-2775) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='50米蛙泳'&&record.distance==='50'&&record.minScore<=3250&&record.minScore>2875">
              当前一级,距运动健将差{{parseInt((record.minScore-2875) / 10000) + ":" + parseInt((record.minScore-2875) / 1000 % 10)
              + parseInt((record.minScore-2875) / 100 % 10) + "." + parseInt((record.minScore-2875) / 10 % 10) +
              parseInt((record.minScore-2875) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='50米蛙泳'&&record.distance==='25'&&record.minScore<=3600&&record.minScore>3150">
              当前二级,距一级差{{parseInt((record.minScore-3150) / 10000) + ":" + parseInt((record.minScore-3150) / 1000 % 10)
              + parseInt((record.minScore-3150) / 100 % 10) + "." + parseInt((record.minScore-3150) / 10 % 10) +
              parseInt((record.minScore-3150) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='50米蛙泳'&&record.distance==='50'&&record.minScore<=3700&&record.minScore>3250">
              当前二级,距一级差{{parseInt((record.minScore-3250) / 10000) + ":" + parseInt((record.minScore-3250) / 1000 % 10)
              + parseInt((record.minScore-3250) / 100 % 10) + "." + parseInt((record.minScore-3250) / 10 % 10) +
              parseInt((record.minScore-3250) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='50米蛙泳'&&record.distance==='25'&&record.minScore<=4300&&record.minScore>3600">
              当前三级,距二级差{{parseInt((record.minScore-3600) / 10000) + ":" + parseInt((record.minScore-3600) / 1000 % 10)
              + parseInt((record.minScore-3600) / 100 % 10) + "." + parseInt((record.minScore-3600) / 10 % 10) +
              parseInt((record.minScore-3600) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='50米蛙泳'&&record.distance==='50'&&record.minScore<=4400&&record.minScore>3700">
              当前三级,距二级差{{parseInt((record.minScore-3700) / 10000) + ":" + parseInt((record.minScore-3700) / 1000 % 10)
              + parseInt((record.minScore-3700) / 100 % 10) + "." + parseInt((record.minScore-3700) / 10 % 10) +
              parseInt((record.minScore-3700) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='50米蛙泳'&&record.distance==='25'&&record.minScore>4300">
              距三级差{{parseInt((record.minScore-4300) / 10000) + ":" + parseInt((record.minScore-4300) / 1000 % 10)
              + parseInt((record.minScore-4300) / 100 % 10) + "." + parseInt((record.minScore-4300) / 10 % 10) +
              parseInt((record.minScore-4300) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='50米蛙泳'&&record.distance==='50'&&record.minScore>4400">
              距三级差{{parseInt((record.minScore-4400) / 10000) + ":" + parseInt((record.minScore-4400) / 1000 % 10)
              + parseInt((record.minScore-4400) / 100 % 10) + "." + parseInt((record.minScore-4400) / 10 % 10) +
              parseInt((record.minScore-4400) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='100米蛙泳'&&record.distance==='25'&&record.minScore<=5823&&record.minScore>0">
              当前国际级健将
            </template>
            <template
              v-else-if="record.matchItem==='100米蛙泳'&&record.distance==='50'&&record.minScore<=10020&&record.minScore>0">
              当前国际级健将
            </template>
            <template
              v-else-if="record.matchItem==='100米蛙泳'&&record.distance==='25'&&record.minScore<=10180&&record.minScore>5823">
              当前运动健将,距国际级健将差{{parseInt((record.minScore-5823) / 10000) + ":" + parseInt((record.minScore-5823) / 1000 %
              10)
              + parseInt((record.minScore-5823) / 100 % 10) + "." + parseInt((record.minScore-5823) / 10 % 10) +
              parseInt((record.minScore-5823) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='100米蛙泳'&&record.distance==='50'&&record.minScore<=10380&&record.minScore>10020">
              当前运动健将,距国际级健将差{{parseInt((record.minScore-10020) / 10000) + ":" + parseInt((record.minScore-10020) / 1000
              % 10)
              + parseInt((record.minScore-10020) / 100 % 10) + "." + parseInt((record.minScore-10020) / 10 % 10) +
              parseInt((record.minScore-10020) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='100米蛙泳'&&record.distance==='25'&&record.minScore<=10900&&record.minScore>10180">
              当前一级,距运动健将差{{parseInt((record.minScore-10180) / 10000) + ":" + parseInt((record.minScore-10180) / 1000 %
              10)
              + parseInt((record.minScore-10180) / 100 % 10) + "." + parseInt((record.minScore-10180) / 10 % 10) +
              parseInt((record.minScore-10180) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='100米蛙泳'&&record.distance==='50'&&record.minScore<=11100&&record.minScore>10380">
              当前一级,距运动健将差{{parseInt((record.minScore-10380) / 10000) + ":" + parseInt((record.minScore-10380) / 1000 %
              10)
              + parseInt((record.minScore-10380) / 100 % 10) + "." + parseInt((record.minScore-10380) / 10 % 10) +
              parseInt((record.minScore-10380) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='100米蛙泳'&&record.distance==='25'&&record.minScore<=11800&&record.minScore>10900">
              当前二级,距一级差{{parseInt((record.minScore-10900) / 10000) + ":" + parseInt((record.minScore-10900) / 1000 % 10)
              + parseInt((record.minScore-10900) / 100 % 10) + "." + parseInt((record.minScore-10900) / 10 % 10) +
              parseInt((record.minScore-10900) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='100米蛙泳'&&record.distance==='50'&&record.minScore<=12000&&record.minScore>11100">
              当前二级,距一级差{{parseInt((record.minScore-11100) / 10000) + ":" + parseInt((record.minScore-11100) / 1000 % 10)
              + parseInt((record.minScore-11100) / 100 % 10) + "." + parseInt((record.minScore-11100) / 10 % 10) +
              parseInt((record.minScore-11100) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='100米蛙泳'&&record.distance==='25'&&record.minScore<=13200&&record.minScore>11800">
              当前三级,距二级差{{parseInt((record.minScore-11800) / 10000) + ":" + parseInt((record.minScore-11800) / 1000 % 10)
              + parseInt((record.minScore-11800) / 100 % 10) + "." + parseInt((record.minScore-11800) / 10 % 10) +
              parseInt((record.minScore-11800) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='100米蛙泳'&&record.distance==='50'&&record.minScore<=13400&&record.minScore>12000">
              当前三级,距二级差{{parseInt((record.minScore-12000) / 10000) + ":" + parseInt((record.minScore-12000) / 1000 % 10)
              + parseInt((record.minScore-12000) / 100 % 10) + "." + parseInt((record.minScore-12000) / 10 % 10) +
              parseInt((record.minScore-12000) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='100米蛙泳'&&record.distance==='25'&&record.minScore<=13200">
              距三级差{{parseInt((record.minScore-13200) / 10000) + ":" + parseInt((record.minScore-13200) / 1000 % 10)
              + parseInt((record.minScore-13200) / 100 % 10) + "." + parseInt((record.minScore-13200) / 10 % 10) +
              parseInt((record.minScore-13200) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='100米蛙泳'&&record.distance==='50'&&record.minScore<=13400">
              距三级差{{parseInt((record.minScore-13400) / 10000) + ":" + parseInt((record.minScore-13400) / 1000 % 10)
              + parseInt((record.minScore-13400) / 100 % 10) + "." + parseInt((record.minScore-13400) / 10 % 10) +
              parseInt((record.minScore-13400) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='200米蛙泳'&&record.distance==='25'&&record.minScore<=20780&&record.minScore>0">
              当前国际级健将
            </template>
            <template
              v-else-if="record.matchItem==='200米蛙泳'&&record.distance==='50'&&record.minScore<=21170&&record.minScore>0">
              当前国际级健将
            </template>
            <template
              v-else-if="record.matchItem==='200米蛙泳'&&record.distance==='25'&&record.minScore<=21790&&record.minScore>20780">
              当前运动健将,距国际级健将差{{parseInt((record.minScore-20780) / 10000) + ":" + parseInt((record.minScore-20780) / 1000
              % 10)
              + parseInt((record.minScore-20780) / 100 % 10) + "." + parseInt((record.minScore-20780) / 10 % 10) +
              parseInt((record.minScore-20780) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='200米蛙泳'&&record.distance==='50'&&record.minScore<=22190&&record.minScore>21170">
              当前运动健将,距国际级健将差{{parseInt((record.minScore-21170) / 10000) + ":" + parseInt((record.minScore-21170) / 1000
              % 10)
              + parseInt((record.minScore-21170) / 100 % 10) + "." + parseInt((record.minScore-21170) / 10 % 10) +
              parseInt((record.minScore-21170) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='200米蛙泳'&&record.distance==='25'&&record.minScore<=23100&&record.minScore>21790">
              当前一级,距运动健将差{{parseInt((record.minScore-21790) / 10000) + ":" + parseInt((record.minScore-21790) / 1000 %
              10)
              + parseInt((record.minScore-21790) / 100 % 10) + "." + parseInt((record.minScore-21790) / 10 % 10) +
              parseInt((record.minScore-21790) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='200米蛙泳'&&record.distance==='50'&&record.minScore<=23500&&record.minScore>22190">
              当前一级,距运动健将差{{parseInt((record.minScore-22190) / 10000) + ":" + parseInt((record.minScore-22190) / 1000 %
              10)
              + parseInt((record.minScore-22190) / 100 % 10) + "." + parseInt((record.minScore-22190) / 10 % 10) +
              parseInt((record.minScore-22190) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='200米蛙泳'&&record.distance==='25'&&record.minScore<=25000&&record.minScore>23100">
              当前二级,距一级差{{parseInt((record.minScore-23100) / 10000) + ":" + parseInt((record.minScore-23100) / 1000 %
              10)
              + parseInt((record.minScore-23100) / 100 % 10) + "." + parseInt((record.minScore-23100) / 10 % 10) +
              parseInt((record.minScore-23100) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='200米蛙泳'&&record.distance==='50'&&record.minScore<=25400&&record.minScore>23500">
              当前二级,距一级差{{parseInt((record.minScore-23500) / 10000) + ":" + parseInt((record.minScore-23500) / 1000 %
              10)
              + parseInt((record.minScore-23500) / 100 % 10) + "." + parseInt((record.minScore-23500) / 10 % 10) +
              parseInt((record.minScore-23500) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='200米蛙泳'&&record.distance==='25'&&record.minScore<=31900&&record.minScore>25000">
              当前三级,距二级差{{parseInt((record.minScore-25000) / 10000) + ":" + parseInt((record.minScore-25000) / 1000 %
              10)
              + parseInt((record.minScore-25000) / 100 % 10) + "." + parseInt((record.minScore-25000) / 10 % 10) +
              parseInt((record.minScore-25000) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='200米蛙泳'&&record.distance==='50'&&record.minScore<=32300&&record.minScore>25400">
              当前三级,距二级差{{parseInt((record.minScore-25400) / 10000) + ":" + parseInt((record.minScore-25400) / 1000 %
              10)
              + parseInt((record.minScore-25400) / 100 % 10) + "." + parseInt((record.minScore-25400) / 10 % 10) +
              parseInt((record.minScore-25400) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='200米蛙泳'&&record.distance==='25'&&record.minScore>31900">
              距三级差{{parseInt((record.minScore-31900) / 10000) + ":" + parseInt((record.minScore-31900) / 1000 %
              10)
              + parseInt((record.minScore-31900) / 100 % 10) + "." + parseInt((record.minScore-31900) / 10 % 10) +
              parseInt((record.minScore-31900) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='200米蛙泳'&&record.distance==='50'&&record.minScore>32300">
              距三级差{{parseInt((record.minScore-32300) / 10000) + ":" + parseInt((record.minScore-32300) / 1000 %
              10)
              + parseInt((record.minScore-32300) / 100 % 10) + "." + parseInt((record.minScore-32300) / 10 % 10) +
              parseInt((record.minScore-32300) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='50米蝶泳'&&record.distance==='25'&&record.minScore<=2251&&record.minScore>0">
              当前国际级健将
            </template>
            <template
              v-else-if="record.matchItem==='50米蝶泳'&&record.distance==='50'&&record.minScore<=2351&&record.minScore>0">
              当前国际级健将
            </template>
            <template
              v-else-if="record.matchItem==='50米蝶泳'&&record.distance==='25'&&record.minScore<=2389&&record.minScore>2251">
              当前运动健将,距国际级健将差{{parseInt((record.minScore-2251) / 10000) + ":" + parseInt((record.minScore-2251) / 1000 %
              10)
              + parseInt((record.minScore-2251) / 100 % 10) + "." + parseInt((record.minScore-2251) / 10 % 10) +
              parseInt((record.minScore-2251) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='50米蝶泳'&&record.distance==='50'&&record.minScore<=2489&&record.minScore>2351">
              当前运动健将,距国际级健将差{{parseInt((record.minScore-2351) / 10000) + ":" + parseInt((record.minScore-2351) / 1000 %
              10)
              + parseInt((record.minScore-2351) / 100 % 10) + "." + parseInt((record.minScore-2351) / 10 % 10) +
              parseInt((record.minScore-2351) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='50米蝶泳'&&record.distance==='25'&&record.minScore<=2600&&record.minScore>2389">
              当前一级,距运动健将差{{parseInt((record.minScore-2389) / 10000) + ":" + parseInt((record.minScore-2389) / 1000 %
              10)
              + parseInt((record.minScore-2389) / 100 % 10) + "." + parseInt((record.minScore-2389) / 10 % 10) +
              parseInt((record.minScore-2389) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='50米蝶泳'&&record.distance==='50'&&record.minScore<=2700&&record.minScore>2489">
              当前一级,距运动健将差{{parseInt((record.minScore-2489) / 10000) + ":" + parseInt((record.minScore-2489) / 1000 %
              10)
              + parseInt((record.minScore-2489) / 100 % 10) + "." + parseInt((record.minScore-2489) / 10 % 10) +
              parseInt((record.minScore-2489) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='50米蝶泳'&&record.distance==='25'&&record.minScore<=3150&&record.minScore>2600">
              当前二级,距一级差{{parseInt((record.minScore-2600) / 10000) + ":" + parseInt((record.minScore-2600) / 1000 %
              10)
              + parseInt((record.minScore-2600) / 100 % 10) + "." + parseInt((record.minScore-2600) / 10 % 10) +
              parseInt((record.minScore-2600) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='50米蝶泳'&&record.distance==='50'&&record.minScore<=3250&&record.minScore>2700">
              当前二级,距一级差{{parseInt((record.minScore-2700) / 10000) + ":" + parseInt((record.minScore-2700) / 1000 %
              10)
              + parseInt((record.minScore-2700) / 100 % 10) + "." + parseInt((record.minScore-2700) / 10 % 10) +
              parseInt((record.minScore-2700) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='50米蝶泳'&&record.distance==='25'&&record.minScore<=4050&&record.minScore>3150">
              当前三级,距二级差{{parseInt((record.minScore-3150) / 10000) + ":" + parseInt((record.minScore-3150) / 1000 %
              10)
              + parseInt((record.minScore-3150) / 100 % 10) + "." + parseInt((record.minScore-3150) / 10 % 10) +
              parseInt((record.minScore-3150) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='50米蝶泳'&&record.distance==='50'&&record.minScore<=4150&&record.minScore>3250">
              当前三级,距二级差{{parseInt((record.minScore-3250) / 10000) + ":" + parseInt((record.minScore-3250) / 1000 %
              10)
              + parseInt((record.minScore-3250) / 100 % 10) + "." + parseInt((record.minScore-3250) / 10 % 10) +
              parseInt((record.minScore-3250) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='50米蝶泳'&&record.distance==='25'&&record.minScore>4050">
              距三级差{{parseInt((record.minScore-4050) / 10000) + ":" + parseInt((record.minScore-4050) / 1000 %
              10)
              + parseInt((record.minScore-4050) / 100 % 10) + "." + parseInt((record.minScore-4050) / 10 % 10) +
              parseInt((record.minScore-4050) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='50米蝶泳'&&record.distance==='50'&&record.minScore>4150">
              距三级差{{parseInt((record.minScore-4150) / 10000) + ":" + parseInt((record.minScore-4150) / 1000 %
              10)
              + parseInt((record.minScore-4150) / 100 % 10) + "." + parseInt((record.minScore-4150) / 10 % 10) +
              parseInt((record.minScore-4150) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='100米蝶泳'&&record.distance==='25'&&record.minScore<=5042&&record.minScore>0">
              当前国际级健将
            </template>
            <template
              v-else-if="record.matchItem==='100米蝶泳'&&record.distance==='50'&&record.minScore<=5192&&record.minScore>0">
              当前国际级健将
            </template>
            <template
              v-else-if="record.matchItem==='100米蝶泳'&&record.distance==='25'&&record.minScore<=5395&&record.minScore>5042">
              当前运动健将,距国际级健将差{{parseInt((record.minScore-5042) / 10000) + ":" + parseInt((record.minScore-5042) / 1000 %
              10)
              + parseInt((record.minScore-5042) / 100 % 10) + "." + parseInt((record.minScore-5042) / 10 % 10) +
              parseInt((record.minScore-5042) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='100米蝶泳'&&record.distance==='50'&&record.minScore<=5545&&record.minScore>5192">
              当前运动健将,距国际级健将差{{parseInt((record.minScore-5192) / 10000) + ":" + parseInt((record.minScore-5192) / 1000 %
              10)
              + parseInt((record.minScore-5192) / 100 % 10) + "." + parseInt((record.minScore-5192) / 10 % 10) +
              parseInt((record.minScore-5192) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='100米蝶泳'&&record.distance==='25'&&record.minScore<=5850&&record.minScore>5395">
              当前一级,距运动健将差{{parseInt((record.minScore-5395) / 10000) + ":" + parseInt((record.minScore-5395) / 1000 %
              10)
              + parseInt((record.minScore-5395) / 100 % 10) + "." + parseInt((record.minScore-5395) / 10 % 10) +
              parseInt((record.minScore-5395) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='100米蝶泳'&&record.distance==='50'&&record.minScore<=10000&&record.minScore>5545">
              当前一级,距运动健将差{{parseInt((record.minScore-5545) / 10000) + ":" + parseInt((record.minScore-5545) / 1000 %
              10)
              + parseInt((record.minScore-5545) / 100 % 10) + "." + parseInt((record.minScore-5545) / 10 % 10) +
              parseInt((record.minScore-5545) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='100米蝶泳'&&record.distance==='25'&&record.minScore<=10950&&record.minScore>5850">
              当前二级,距一级差{{parseInt((record.minScore-5850) / 10000) + ":" + parseInt((record.minScore-5850) / 1000 %
              10)
              + parseInt((record.minScore-5850) / 100 % 10) + "." + parseInt((record.minScore-5850) / 10 % 10) +
              parseInt((record.minScore-5850) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='100米蝶泳'&&record.distance==='50'&&record.minScore<=11100&&record.minScore>10000">
              当前二级,距一级差{{parseInt((record.minScore-10000) / 10000) + ":" + parseInt((record.minScore-10000) / 1000 %
              10)
              + parseInt((record.minScore-10000) / 100 % 10) + "." + parseInt((record.minScore-10000) / 10 % 10) +
              parseInt((record.minScore-10000) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='100米蝶泳'&&record.distance==='25'&&record.minScore<=12750&&record.minScore>10950">
              当前三级,距二级差{{parseInt((record.minScore-10950) / 10000) + ":" + parseInt((record.minScore-10950) / 1000 %
              10)
              + parseInt((record.minScore-10950) / 100 % 10) + "." + parseInt((record.minScore-10950) / 10 % 10) +
              parseInt((record.minScore-10950) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='100米蝶泳'&&record.distance==='50'&&record.minScore<=12900&&record.minScore>11100">
              当前三级,距二级差{{parseInt((record.minScore-11100) / 10000) + ":" + parseInt((record.minScore-11100) / 1000 %
              10)
              + parseInt((record.minScore-11100) / 100 % 10) + "." + parseInt((record.minScore-11100) / 10 % 10) +
              parseInt((record.minScore-11100) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='100米蝶泳'&&record.distance==='25'&&record.minScore<=12750">
              距三级差{{parseInt((record.minScore-12750) / 10000) + ":" + parseInt((record.minScore-12750) / 1000 %
              10)
              + parseInt((record.minScore-12750) / 100 % 10) + "." + parseInt((record.minScore-12750) / 10 % 10) +
              parseInt((record.minScore-12750) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='100米蝶泳'&&record.distance==='50'&&record.minScore<=12900">
              距三级差{{parseInt((record.minScore-12900) / 10000) + ":" + parseInt((record.minScore-12900) / 1000 %
              10)
              + parseInt((record.minScore-12900) / 100 % 10) + "." + parseInt((record.minScore-12900) / 10 % 10) +
              parseInt((record.minScore-12900) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='200米蝶泳'&&record.distance==='25'&&record.minScore<=15360&&record.minScore>0">
              当前国际级健将
            </template>
            <template
              v-else-if="record.matchItem==='200米蝶泳'&&record.distance==='50'&&record.minScore<=15660&&record.minScore>0">
              当前国际级健将
            </template>
            <template
              v-else-if="record.matchItem==='200米蝶泳'&&record.distance==='25'&&record.minScore<=15970&&record.minScore>15360">
              当前运动健将,距国际级健将差{{parseInt((record.minScore-15360) / 10000) + ":" + parseInt((record.minScore-15360) / 1000
              %
              10)
              + parseInt((record.minScore-15360) / 100 % 10) + "." + parseInt((record.minScore-15360) / 10 % 10) +
              parseInt((record.minScore-15360) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='200米蝶泳'&&record.distance==='50'&&record.minScore<=20270&&record.minScore>15660">
              当前运动健将,距国际级健将差{{parseInt((record.minScore-15660) / 10000) + ":" + parseInt((record.minScore-15660) / 1000
              %
              10)
              + parseInt((record.minScore-15660) / 100 % 10) + "." + parseInt((record.minScore-15660) / 10 % 10) +
              parseInt((record.minScore-15660) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='200米蝶泳'&&record.distance==='25'&&record.minScore<=21100&&record.minScore>15970">
              当前一级,距运动健将差{{parseInt((record.minScore-15970) / 10000) + ":" + parseInt((record.minScore-15970) / 1000 %
              10)
              + parseInt((record.minScore-15970) / 100 % 10) + "." + parseInt((record.minScore-15970) / 10 % 10) +
              parseInt((record.minScore-15970) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='200米蝶泳'&&record.distance==='50'&&record.minScore<=21400&&record.minScore>20270">
              当前一级,距运动健将差{{parseInt((record.minScore-20270) / 10000) + ":" + parseInt((record.minScore-20270) / 1000 %
              10)
              + parseInt((record.minScore-20270) / 100 % 10) + "." + parseInt((record.minScore-20270) / 10 % 10) +
              parseInt((record.minScore-20270) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='200米蝶泳'&&record.distance==='25'&&record.minScore<=23500&&record.minScore>21100">
              当前二级,距一级差{{parseInt((record.minScore-21100) / 10000) + ":" + parseInt((record.minScore-21100) / 1000 %
              10)
              + parseInt((record.minScore-21100) / 100 % 10) + "." + parseInt((record.minScore-21100) / 10 % 10) +
              parseInt((record.minScore-21100) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='200米蝶泳'&&record.distance==='50'&&record.minScore<=23800&&record.minScore>21400">
              当前二级,距一级差{{parseInt((record.minScore-21400) / 10000) + ":" + parseInt((record.minScore-21400) / 1000 %
              10)
              + parseInt((record.minScore-21400) / 100 % 10) + "." + parseInt((record.minScore-21400) / 10 % 10) +
              parseInt((record.minScore-21400) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='200米蝶泳'&&record.distance==='25'&&record.minScore<=31500&&record.minScore>23500">
              当前三级,距二级差{{parseInt((record.minScore-23500) / 10000) + ":" + parseInt((record.minScore-23500) / 1000 %
              10)
              + parseInt((record.minScore-23500) / 100 % 10) + "." + parseInt((record.minScore-23500) / 10 % 10) +
              parseInt((record.minScore-23500) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='200米蝶泳'&&record.distance==='50'&&record.minScore<=31800&&record.minScore>23800">
              当前三级,距二级差{{parseInt((record.minScore-23800) / 10000) + ":" + parseInt((record.minScore-23800) / 1000 %
              10)
              + parseInt((record.minScore-23800) / 100 % 10) + "." + parseInt((record.minScore-23800) / 10 % 10) +
              parseInt((record.minScore-23800) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='200米蝶泳'&&record.distance==='25'&&record.minScore>31500">
              距三级差{{parseInt((record.minScore-31500) / 10000) + ":" + parseInt((record.minScore-31500) / 1000 %
              10)
              + parseInt((record.minScore-31500) / 100 % 10) + "." + parseInt((record.minScore-31500) / 10 % 10) +
              parseInt((record.minScore-31500) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='200米蝶泳'&&record.distance==='50'&&record.minScore>31800">
              距三级差{{parseInt((record.minScore-31800) / 10000) + ":" + parseInt((record.minScore-31800) / 1000 %
              10)
              + parseInt((record.minScore-31800) / 100 % 10) + "." + parseInt((record.minScore-31800) / 10 % 10) +
              parseInt((record.minScore-31800) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='200米混合泳'&&record.distance==='25'&&record.minScore<=15680&&record.minScore>0">
              当前国际级健将
            </template>
            <template
              v-else-if="record.matchItem==='200米混合泳'&&record.distance==='50'&&record.minScore<=15980&&record.minScore>0">
              当前国际级健将
            </template>
            <template
              v-else-if="record.matchItem==='200米混合泳'&&record.distance==='25'&&record.minScore<=20520&&record.minScore>15680">
              当前运动健将,距国际级健将差{{parseInt((record.minScore-15680) / 10000) + ":" + parseInt((record.minScore-15680) / 1000
              %
              10)
              + parseInt((record.minScore-15680) / 100 % 10) + "." + parseInt((record.minScore-15680) / 10 % 10) +
              parseInt((record.minScore-15680) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='200米混合泳'&&record.distance==='50'&&record.minScore<=20820&&record.minScore>15980">
              当前运动健将,距国际级健将差{{parseInt((record.minScore-15980) / 10000) + ":" + parseInt((record.minScore-15980) / 1000
              %
              10)
              + parseInt((record.minScore-15980) / 100 % 10) + "." + parseInt((record.minScore-15980) / 10 % 10) +
              parseInt((record.minScore-15980) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='200米混合泳'&&record.distance==='25'&&record.minScore<=21600&&record.minScore>20520">
              当前一级,距运动健将差{{parseInt((record.minScore-20520) / 10000) + ":" + parseInt((record.minScore-20520) / 1000 %
              10)
              + parseInt((record.minScore-20520) / 100 % 10) + "." + parseInt((record.minScore-20520) / 10 % 10) +
              parseInt((record.minScore-20520) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='200米混合泳'&&record.distance==='50'&&record.minScore<=21900&&record.minScore>20820">
              当前一级,距运动健将差{{parseInt((record.minScore-20820) / 10000) + ":" + parseInt((record.minScore-20820) / 1000 %
              10)
              + parseInt((record.minScore-20820) / 100 % 10) + "." + parseInt((record.minScore-20820) / 10 % 10) +
              parseInt((record.minScore-20820) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='200米混合泳'&&record.distance==='25'&&record.minScore<=23700&&record.minScore>21600">
              当前二级,距一级差{{parseInt((record.minScore-21600) / 10000) + ":" + parseInt((record.minScore-21600) / 1000 %
              10)
              + parseInt((record.minScore-21600) / 100 % 10) + "." + parseInt((record.minScore-21600) / 10 % 10) +
              parseInt((record.minScore-21600) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='200米混合泳'&&record.distance==='50'&&record.minScore<=24000&&record.minScore>21900">
              当前二级,距一级差{{parseInt((record.minScore-21900) / 10000) + ":" + parseInt((record.minScore-21900) / 1000 %
              10)
              + parseInt((record.minScore-21900) / 100 % 10) + "." + parseInt((record.minScore-21900) / 10 % 10) +
              parseInt((record.minScore-21900) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='200米混合泳'&&record.distance==='25'&&record.minScore<=31200&&record.minScore>23700">
              当前三级,距二级差{{parseInt((record.minScore-23700) / 10000) + ":" + parseInt((record.minScore-23700) / 1000 %
              10)
              + parseInt((record.minScore-23700) / 100 % 10) + "." + parseInt((record.minScore-23700) / 10 % 10) +
              parseInt((record.minScore-23700) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='200米混合泳'&&record.distance==='50'&&record.minScore<=31500&&record.minScore>24000">
              当前三级,距二级差{{parseInt((record.minScore-24000) / 10000) + ":" + parseInt((record.minScore-24000) / 1000 %
              10)
              + parseInt((record.minScore-24000) / 100 % 10) + "." + parseInt((record.minScore-24000) / 10 % 10) +
              parseInt((record.minScore-24000) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='200米混合泳'&&record.distance==='25'&&record.minScore>31200">
              距三级差{{parseInt((record.minScore-31200) / 10000) + ":" + parseInt((record.minScore-31200) / 1000 %
              10)
              + parseInt((record.minScore-31200) / 100 % 10) + "." + parseInt((record.minScore-31200) / 10 % 10) +
              parseInt((record.minScore-31200) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='200米混合泳'&&record.distance==='50'&&record.minScore>31500">
              距三级差{{parseInt((record.minScore-31500) / 10000) + ":" + parseInt((record.minScore-31500) / 1000 %
              10)
              + parseInt((record.minScore-31500) / 100 % 10) + "." + parseInt((record.minScore-31500) / 10 % 10) +
              parseInt((record.minScore-31500) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='400米混合泳'&&record.distance==='25'&&record.minScore<=41200&&record.minScore>0">
              当前国际级健将
            </template>
            <template
              v-else-if="record.matchItem==='400米混合泳'&&record.distance==='50'&&record.minScore<=41810&&record.minScore>0">
              当前国际级健将
            </template>
            <template
              v-else-if="record.matchItem==='400米混合泳'&&record.distance==='25'&&record.minScore<=42520&&record.minScore>41200">
              当前运动健将,距国际级健将差{{parseInt((record.minScore-41200) / 10000) + ":" + parseInt((record.minScore-41200) / 1000
              %
              10)
              + parseInt((record.minScore-41200) / 100 % 10) + "." + parseInt((record.minScore-41200) / 10 % 10) +
              parseInt((record.minScore-41200) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='400米混合泳'&&record.distance==='50'&&record.minScore<=43120&&record.minScore>41810">
              当前运动健将,距国际级健将差{{parseInt((record.minScore-41810) / 10000) + ":" + parseInt((record.minScore-41810) / 1000
              %
              10)
              + parseInt((record.minScore-41810) / 100 % 10) + "." + parseInt((record.minScore-41810) / 10 % 10) +
              parseInt((record.minScore-41810) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='400米混合泳'&&record.distance==='25'&&record.minScore<=45200&&record.minScore>42520">
              当前一级,距运动健将差{{parseInt((record.minScore-42520) / 10000) + ":" + parseInt((record.minScore-42520) / 1000 %
              10)
              + parseInt((record.minScore-42520) / 100 % 10) + "." + parseInt((record.minScore-42520) / 10 % 10) +
              parseInt((record.minScore-42520) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='400米混合泳'&&record.distance==='50'&&record.minScore<=45830&&record.minScore>43120">
              当前一级,距运动健将差{{parseInt((record.minScore-43120) / 10000) + ":" + parseInt((record.minScore-43120) / 1000 %
              10)
              + parseInt((record.minScore-43120) / 100 % 10) + "." + parseInt((record.minScore-43120) / 10 % 10) +
              parseInt((record.minScore-43120) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='400米混合泳'&&record.distance==='25'&&record.minScore<=52500&&record.minScore>45200">
              当前二级,距一级差{{parseInt((record.minScore-45200) / 10000) + ":" + parseInt((record.minScore-45200) / 1000 %
              10)
              + parseInt((record.minScore-45200) / 100 % 10) + "." + parseInt((record.minScore-45200) / 10 % 10) +
              parseInt((record.minScore-45200) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='400米混合泳'&&record.distance==='50'&&record.minScore<=53100&&record.minScore>45830">
              当前二级,距一级差{{parseInt((record.minScore-45830) / 10000) + ":" + parseInt((record.minScore-45830) / 1000 %
              10)
              + parseInt((record.minScore-45830) / 100 % 10) + "." + parseInt((record.minScore-45830) / 10 % 10) +
              parseInt((record.minScore-45830) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='400米混合泳'&&record.distance==='25'&&record.minScore<=65000&&record.minScore>52500">
              当前三级,距二级差{{parseInt((record.minScore-52500) / 10000) + ":" + parseInt((record.minScore-52500) / 1000 %
              10)
              + parseInt((record.minScore-52500) / 100 % 10) + "." + parseInt((record.minScore-52500) / 10 % 10) +
              parseInt((record.minScore-52500) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='400米混合泳'&&record.distance==='50'&&record.minScore<=65600&&record.minScore>53100">
              当前三级,距二级差{{parseInt((record.minScore-53100) / 10000) + ":" + parseInt((record.minScore-53100) / 1000 %
              10)
              + parseInt((record.minScore-53100) / 100 % 10) + "." + parseInt((record.minScore-53100) / 10 % 10) +
              parseInt((record.minScore-53100) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='400米混合泳'&&record.distance==='25'&&record.minScore>65000">
              距三级差{{parseInt((record.minScore-65000) / 10000) + ":" + parseInt((record.minScore-65000) / 1000 %
              10)
              + parseInt((record.minScore-65000) / 100 % 10) + "." + parseInt((record.minScore-65000) / 10 % 10) +
              parseInt((record.minScore-65000) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='400米混合泳'&&record.distance==='50'&&record.minScore>65600">
              距三级差{{parseInt((record.minScore-65600) / 10000) + ":" + parseInt((record.minScore-65600) / 1000 %
              10)
              + parseInt((record.minScore-65600) / 100 % 10) + "." + parseInt((record.minScore-65600) / 10 % 10) +
              parseInt((record.minScore-65600) % 10)}}
            </template>
            <template v-else>-</template>
          </template>
          <template v-else-if="record.cardNumber[16]%2===0">
            <template
              v-if="record.matchItem==='50米自由泳'&&record.minScore<=3750&&record.minScore>3050&&record.distance==='25'">
              当前三级,距二级差{{parseInt((record.minScore-3050) / 10000) + ":" + parseInt((record.minScore-3050) / 1000 % 10) +
              parseInt((record.minScore-3050) / 100 % 10) + "." + parseInt((record.minScore-3050) / 10 % 10) +
              parseInt((record.minScore-3050) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='50米自由泳'&&record.minScore<=3850&&record.minScore>3150&&record.distance==='50'">
              当前三级,距二级差{{parseInt((record.minScore-3150) / 10000) + ":" + parseInt((record.minScore-3150) / 1000 % 10) +
              parseInt((record.minScore-3150) / 100 % 10) + "." + parseInt((record.minScore-3150) / 10 % 10) +
              parseInt((record.minScore-3150) % 10)}}
            </template>
            <template
              v-if="record.matchItem==='50米自由泳'&&record.minScore>3750&&record.distance==='25'">
              距三级差{{parseInt((record.minScore-3750) / 10000) + ":" + parseInt((record.minScore-3750) / 1000 % 10) +
              parseInt((record.minScore-3750) / 100 % 10) + "." + parseInt((record.minScore-3750) / 10 % 10) +
              parseInt((record.minScore-3750) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='50米自由泳'&&record.minScore>3850&&record.distance==='50'">
              距三级差{{parseInt((record.minScore-3850) / 10000) + ":" + parseInt((record.minScore-3850) / 1000 % 10) +
              parseInt((record.minScore-3850) / 100 % 10) + "." + parseInt((record.minScore-3850) / 10 % 10) +
              parseInt((record.minScore-3850) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='50米自由泳'&&record.minScore<=3050&&record.minScore>2620&&record.distance==='25'">
              当前二级,距一级差{{parseInt((record.minScore-2620) / 10000) + ":" + parseInt((record.minScore-2620) / 1000 % 10) +
              parseInt((record.minScore-2620) / 100 % 10) + "." + parseInt((record.minScore-2620) / 10 % 10) +
              parseInt((record.minScore-2620) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='50米自由泳'&&record.minScore<=3150&&record.minScore>2720&&record.distance==='50'">
              当前二级,距一级差{{parseInt((record.minScore-2720) / 10000) + ":" + parseInt((record.minScore-2720) / 1000 % 10) +
              parseInt((record.minScore-2720) / 100 % 10) + "." + parseInt((record.minScore-2720) / 10 % 10) +
              parseInt((record.minScore-2720) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='50米自由泳'&&record.minScore<=2620&&record.minScore>2485&&record.distance==='25'">
              当前一级,距运动健将差{{parseInt((record.minScore-2485) / 10000) + ":" + parseInt((record.minScore-2485) / 1000 % 10)
              +
              parseInt((record.minScore-2485) / 100 % 10) + "." + parseInt((record.minScore-2485) / 10 % 10) +
              parseInt((record.minScore-2485) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='50米自由泳'&&record.minScore<=2720&&record.minScore>2585&&record.distance==='50'">
              当前一级,距运动健将差{{parseInt((record.minScore-2585) / 10000) + ":" + parseInt((record.minScore-2585) / 1000 % 10)
              +
              parseInt((record.minScore-2585) / 100 % 10) + "." + parseInt((record.minScore-2585) / 10 % 10) +
              parseInt((record.minScore-2585) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='50米自由泳'&&record.minScore<=2485&&record.minScore>2390&&record.distance==='25'">
              当前运动健将,距国际级健将差{{parseInt((record.minScore-2390) / 10000) + ":" + parseInt((record.minScore-2390) / 1000 %
              10) +
              parseInt((record.minScore-2390) / 100 % 10) + "." + parseInt((record.minScore-2390) / 10 % 10) +
              parseInt((record.minScore-2390) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='50米自由泳'&&record.minScore<=2585&&record.minScore>2490&&record.distance==='50'">
              当前运动健将,距国际级健将差{{parseInt((record.minScore-2490) / 10000) + ":" + parseInt((record.minScore-2490) / 1000 %
              10) +
              parseInt((record.minScore-2490) / 100 % 10) + "." + parseInt((record.minScore-2490) / 10 % 10) +
              parseInt((record.minScore-2490) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='50米自由泳'&&record.minScore<=2390&&record.minScore>0&&record.distance==='25'">
              当前国际级健将
            </template>
            <template
              v-else-if="record.matchItem==='50米自由泳'&&record.minScore<=2490&&record.minScore>0&&record.distance==='50'">
              当前国际级健将
            </template>
            <template
              v-else-if="record.matchItem==='100米自由泳'&&record.distance==='25'&&record.minScore<=5285&&record.minScore>0">
              当前国际级健将
            </template>
            <template
              v-else-if="record.matchItem==='100米自由泳'&&record.distance==='50'&&record.minScore<=5435&&record.minScore>0">
              当前国际级健将
            </template>
            <template
              v-else-if="record.matchItem==='100米自由泳'&&record.distance==='25'&&record.minScore<=5480&&record.minScore>5285">
              当前运动健将,距国际级健将差{{parseInt((record.minScore-5285) / 10000) + ":" + parseInt((record.minScore-5285) / 1000 %
              10) +
              parseInt((record.minScore-5285) / 100 % 10) + "." + parseInt((record.minScore-5285) / 10 % 10) +
              parseInt((record.minScore-5285) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='100米自由泳'&&record.distance==='50'&&record.minScore<=5630&&record.minScore>5435">
              当前运动健将,距国际级健将差{{parseInt((record.minScore-5435) / 10000) + ":" + parseInt((record.minScore-5435) / 1000 %
              10) +
              parseInt((record.minScore-5435) / 100 % 10) + "." + parseInt((record.minScore-5435) / 10 % 10) +
              parseInt((record.minScore-5435) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='100米自由泳'&&record.distance==='25'&&record.minScore<=10100&&record.minScore>5480">
              当前一级,距运动健将差{{parseInt((record.minScore-5480) / 10000) + ":" + parseInt((record.minScore-5480) / 1000 % 10)
              +
              parseInt((record.minScore-5480) / 100 % 10) + "." + parseInt((record.minScore-5480) / 10 % 10) +
              parseInt((record.minScore-5480) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='100米自由泳'&&record.distance==='50'&&record.minScore<=10250&&record.minScore>5630">
              当前一级,距运动健将差{{parseInt((record.minScore-5630) / 10000) + ":" + parseInt((record.minScore-5630) / 1000 % 10)
              +
              parseInt((record.minScore-5630) / 100 % 10) + "." + parseInt((record.minScore-5630) / 10 % 10) +
              parseInt((record.minScore-5630) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='100米自由泳'&&record.distance==='25'&&record.minScore<=11100&&record.minScore>10100">
              当前二级,距一级差{{parseInt((record.minScore-10100) / 10000) + ":" + parseInt((record.minScore-10100) / 1000 % 10)
              + parseInt((record.minScore-10100) / 100 % 10) + "." + parseInt((record.minScore-10100) / 10 % 10) +
              parseInt((record.minScore-10100) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='100米自由泳'&&record.distance==='50'&&record.minScore<=11300&&record.minScore>10250">
              当前二级,距一级差{{parseInt((record.minScore-10250) / 10000) + ":" + parseInt((record.minScore-10250) / 1000 % 10)
              + parseInt((record.minScore-10250) / 100 % 10) + "." + parseInt((record.minScore-10250) / 10 % 10) +
              parseInt((record.minScore-10250) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='100米自由泳'&&record.distance==='25'&&record.minScore<=13300&&record.minScore>11100">
              当前三级,距二级差{{parseInt((record.minScore-11100) / 10000) + ":" + parseInt((record.minScore-11100) / 1000 % 10)
              + parseInt((record.minScore-11100) / 100 % 10) + "." + parseInt((record.minScore-11100) / 10 % 10) +
              parseInt((record.minScore-11100) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='100米自由泳'&&record.distance==='50'&&record.minScore<=13400&&record.minScore>11300">
              当前三级,距二级差{{parseInt((record.minScore-11300) / 10000) + ":" + parseInt((record.minScore-11300) / 1000 % 10)
              + parseInt((record.minScore-11300) / 100 % 10) + "." + parseInt((record.minScore-11300) / 10 % 10) +
              parseInt((record.minScore-11300) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='100米自由泳'&&record.distance==='25'&&record.minScore>13300">
              距三级差{{parseInt((record.minScore-13300) / 10000) + ":" + parseInt((record.minScore-13300) / 1000 % 10)
              + parseInt((record.minScore-13300) / 100 % 10) + "." + parseInt((record.minScore-13300) / 10 % 10) +
              parseInt((record.minScore-13300) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='100米自由泳'&&record.distance==='50'&&record.minScore>13400">
              距三级差{{parseInt((record.minScore-13400) / 10000) + ":" + parseInt((record.minScore-13400) / 1000 % 10)
              + parseInt((record.minScore-13400) / 100 % 10) + "." + parseInt((record.minScore-13400) / 10 % 10) +
              parseInt((record.minScore-13400) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='200米自由泳'&&record.distance==='25'&&record.minScore<=15430&&record.minScore>0">
              当前国际级健将
            </template>
            <template
              v-else-if="record.matchItem==='200米自由泳'&&record.distance==='50'&&record.minScore<=15780&&record.minScore>0">
              当前国际级健将
            </template>
            <template
              v-else-if="record.matchItem==='200米自由泳'&&record.distance==='25'&&record.minScore<=15770&&record.minScore>15430">
              当前运动健将,距国际级健将差{{parseInt((record.minScore-15430) / 10000) + ":" + parseInt((record.minScore-15430) / 1000
              % 10)
              + parseInt((record.minScore-15430) / 100 % 10) + "." + parseInt((record.minScore-15430) / 10 % 10) +
              parseInt((record.minScore-15430) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='200米自由泳'&&record.distance==='50'&&record.minScore<=20120&&record.minScore>15780">
              当前运动健将,距国际级健将差{{parseInt((record.minScore-15780) / 10000) + ":" + parseInt((record.minScore-15780) / 1000
              % 10)
              + parseInt((record.minScore-15780) / 100 % 10) + "." + parseInt((record.minScore-15780) / 10 % 10) +
              parseInt((record.minScore-15780) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='200米自由泳'&&record.distance==='25'&&record.minScore<=21150&&record.minScore>15770">
              当前一级,距运动健将差{{parseInt((record.minScore-15770) / 10000) + ":" + parseInt((record.minScore-15770) / 1000 %
              10)
              + parseInt((record.minScore-15770) / 100 % 10) + "." + parseInt((record.minScore-15770) / 10 % 10) +
              parseInt((record.minScore-15770) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='200米自由泳'&&record.distance==='50'&&record.minScore<=21500&&record.minScore>20120">
              当前一级,距运动健将差{{parseInt((record.minScore-20120) / 10000) + ":" + parseInt((record.minScore-20120) / 1000 %
              10)
              + parseInt((record.minScore-20120) / 100 % 10) + "." + parseInt((record.minScore-20120) / 10 % 10) +
              parseInt((record.minScore-20120) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='200米自由泳'&&record.distance==='25'&&record.minScore<=23550&&record.minScore>21150">
              当前二级,距一级差{{parseInt((record.minScore-21150) / 10000) + ":" + parseInt((record.minScore-21150) / 1000 % 10)
              + parseInt((record.minScore-21150) / 100 % 10) + "." + parseInt((record.minScore-21150) / 10 % 10) +
              parseInt((record.minScore-21150) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='200米自由泳'&&record.distance==='50'&&record.minScore<=23900&&record.minScore>21500">
              当前二级,距一级差{{parseInt((record.minScore-21500) / 10000) + ":" + parseInt((record.minScore-21500) / 1000 % 10)
              + parseInt((record.minScore-21500) / 100 % 10) + "." + parseInt((record.minScore-21500) / 10 % 10) +
              parseInt((record.minScore-21500) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='200米自由泳'&&record.distance==='25'&&record.minScore<=31950&&record.minScore>23550">
              当前三级,距二级差{{parseInt((record.minScore-23550) / 10000) + ":" + parseInt((record.minScore-23550) / 1000 % 10)
              + parseInt((record.minScore-23550) / 100 % 10) + "." + parseInt((record.minScore-23550) / 10 % 10) +
              parseInt((record.minScore-23550) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='200米自由泳'&&record.distance==='50'&&record.minScore<=32300&&record.minScore>23900">
              当前三级,距二级差{{parseInt((record.minScore-23900) / 10000) + ":" + parseInt((record.minScore-23900) / 1000 % 10)
              + parseInt((record.minScore-23900) / 100 % 10) + "." + parseInt((record.minScore-23900) / 10 % 10) +
              parseInt((record.minScore-23900) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='200米自由泳'&&record.distance==='25'&&record.minScore>31950">
              距三级差{{parseInt((record.minScore-31950) / 10000) + ":" + parseInt((record.minScore-31950) / 1000 % 10)
              + parseInt((record.minScore-31950) / 100 % 10) + "." + parseInt((record.minScore-31950) / 10 % 10) +
              parseInt((record.minScore-31950) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='200米自由泳'&&record.distance==='50'&&record.minScore>32300">
              距三级差{{parseInt((record.minScore-32300) / 10000) + ":" + parseInt((record.minScore-32300) / 1000 % 10)
              + parseInt((record.minScore-32300) / 100 % 10) + "." + parseInt((record.minScore-32300) / 10 % 10) +
              parseInt((record.minScore-32300) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='400米自由泳'&&record.distance==='25'&&record.minScore<=40220&&record.minScore>0">
              当前国际级健将
            </template>
            <template
              v-else-if="record.matchItem==='400米自由泳'&&record.distance==='50'&&record.minScore<=40920&&record.minScore>0">
              当前国际级健将
            </template>
            <template
              v-else-if="record.matchItem==='400米自由泳'&&record.distance==='25'&&record.minScore<=40880&&record.minScore>40220">
              当前运动健将,距国际级健将差{{parseInt((record.minScore-40220) / 10000) + ":" + parseInt((record.minScore-40220) / 1000
              % 10)
              + parseInt((record.minScore-40220) / 100 % 10) + "." + parseInt((record.minScore-40220) / 10 % 10) +
              parseInt((record.minScore-40220) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='400米自由泳'&&record.distance==='50'&&record.minScore<=41580&&record.minScore>40920">
              当前运动健将,距国际级健将差{{parseInt((record.minScore-40920) / 10000) + ":" + parseInt((record.minScore-40920) / 1000
              % 10)
              + parseInt((record.minScore-40920) / 100 % 10) + "." + parseInt((record.minScore-40920) / 10 % 10) +
              parseInt((record.minScore-40920) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='400米自由泳'&&record.distance==='25'&&record.minScore<=43700&&record.minScore>40880">
              当前一级,距运动健将差{{parseInt((record.minScore-40880) / 10000) + ":" + parseInt((record.minScore-40880) / 1000
              % 10)
              + parseInt((record.minScore-40880) / 100 % 10) + "." + parseInt((record.minScore-40880) / 10 % 10) +
              parseInt((record.minScore-40880) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='400米自由泳'&&record.distance==='50'&&record.minScore<=44400&&record.minScore>41580">
              当前一级,距运动健将差{{parseInt((record.minScore-41580) / 10000) + ":" + parseInt((record.minScore-41580) / 1000
              % 10)
              + parseInt((record.minScore-41580) / 100 % 10) + "." + parseInt((record.minScore-41580) / 10 % 10) +
              parseInt((record.minScore-41580) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='400米自由泳'&&record.distance==='25'&&record.minScore<=53900&&record.minScore>43700">
              当前二级,距一级差{{parseInt((record.minScore-43700) / 10000) + ":" + parseInt((record.minScore-43700) / 1000
              % 10)
              + parseInt((record.minScore-43700) / 100 % 10) + "." + parseInt((record.minScore-43700) / 10 % 10) +
              parseInt((record.minScore-43700) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='400米自由泳'&&record.distance==='50'&&record.minScore<=54600&&record.minScore>44400">
              当前二级,距一级差{{parseInt((record.minScore-44400) / 10000) + ":" + parseInt((record.minScore-44400) / 1000
              % 10)
              + parseInt((record.minScore-44400) / 100 % 10) + "." + parseInt((record.minScore-44400) / 10 % 10) +
              parseInt((record.minScore-44400) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='400米自由泳'&&record.distance==='25'&&record.minScore<=65900&&record.minScore>53900">
              当前三级,距二级差{{parseInt((record.minScore-53900) / 10000) + ":" + parseInt((record.minScore-53900) / 1000
              % 10)
              + parseInt((record.minScore-53900) / 100 % 10) + "." + parseInt((record.minScore-53900) / 10 % 10) +
              parseInt((record.minScore-53900) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='400米自由泳'&&record.distance==='50'&&record.minScore<=70600&&record.minScore>54600">
              当前三级,距二级差{{parseInt((record.minScore-54600) / 10000) + ":" + parseInt((record.minScore-54600) / 1000
              % 10)
              + parseInt((record.minScore-54600) / 100 % 10) + "." + parseInt((record.minScore-54600) / 10 % 10) +
              parseInt((record.minScore-54600) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='400米自由泳'&&record.distance==='25'&&record.minScore>65900">
              距三级差{{parseInt((record.minScore-65900) / 10000) + ":" + parseInt((record.minScore-65900) / 1000
              % 10)
              + parseInt((record.minScore-65900) / 100 % 10) + "." + parseInt((record.minScore-65900) / 10 % 10) +
              parseInt((record.minScore-65900) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='400米自由泳'&&record.distance==='50'&&record.minScore>70600">
              距三级差{{parseInt((record.minScore-70600) / 10000) + ":" + parseInt((record.minScore-70600) / 1000
              % 10)
              + parseInt((record.minScore-70600) / 100 % 10) + "." + parseInt((record.minScore-70600) / 10 % 10) +
              parseInt((record.minScore-70600) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='800米自由泳'&&record.distance==='25'&&record.minScore<=82000&&record.minScore>0">
              当前国际级健将
            </template>
            <template
              v-else-if="record.matchItem==='800米自由泳'&&record.distance==='50'&&record.minScore<=83390&&record.minScore>0">
              当前国际级健将
            </template>
            <template
              v-else-if="record.matchItem==='800米自由泳'&&record.distance==='25'&&record.minScore<=83940&&record.minScore>82000">
              当前运动健将,距国际级健将差{{parseInt((record.minScore-82000) / 10000) + ":" + parseInt((record.minScore-82000) / 1000
              % 10)
              + parseInt((record.minScore-82000) / 100 % 10) + "." + parseInt((record.minScore-82000) / 10 % 10) +
              parseInt((record.minScore-82000) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='800米自由泳'&&record.distance==='50'&&record.minScore<=85340&&record.minScore>83390">
              当前运动健将,距国际级健将差{{parseInt((record.minScore-83390) / 10000) + ":" + parseInt((record.minScore-83390) / 1000
              % 10)
              + parseInt((record.minScore-83390) / 100 % 10) + "." + parseInt((record.minScore-83390) / 10 % 10) +
              parseInt((record.minScore-83390) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='800米自由泳'&&record.distance==='25'&&record.minScore<=92800&&record.minScore>83940">
              当前一级,距运动健将差{{parseInt((record.minScore-83940) / 10000) + ":" + parseInt((record.minScore-83940) / 1000
              % 10)
              + parseInt((record.minScore-83940) / 100 % 10) + "." + parseInt((record.minScore-83940) / 10 % 10) +
              parseInt((record.minScore-83940) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='800米自由泳'&&record.distance==='50'&&record.minScore<=94200&&record.minScore>85340">
              当前一级,距运动健将差{{parseInt((record.minScore-85340) / 10000) + ":" + parseInt((record.minScore-85340) / 1000
              % 10)
              + parseInt((record.minScore-85340) / 100 % 10) + "." + parseInt((record.minScore-85340) / 10 % 10) +
              parseInt((record.minScore-85340) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='800米自由泳'&&record.distance==='25'&&record.minScore<=114800&&record.minScore>92800">
              当前二级,距一级差{{parseInt((record.minScore-92800) / 10000) + ":" + parseInt((record.minScore-92800) / 1000
              % 10)
              + parseInt((record.minScore-92800) / 100 % 10) + "." + parseInt((record.minScore-92800) / 10 % 10) +
              parseInt((record.minScore-92800) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='800米自由泳'&&record.distance==='50'&&record.minScore<=120200&&record.minScore>94200">
              当前二级,距一级差{{parseInt((record.minScore-94200) / 10000) + ":" + parseInt((record.minScore-94200) / 1000
              % 10)
              + parseInt((record.minScore-94200) / 100 % 10) + "." + parseInt((record.minScore-94200) / 10 % 10) +
              parseInt((record.minScore-94200) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='800米自由泳'&&record.distance==='25'&&record.minScore<=144800&&record.minScore>114800">
              当前三级,距二级差{{parseInt((record.minScore-114800) / 10000) + ":" + parseInt((record.minScore-114800) / 1000
              % 10)
              + parseInt((record.minScore-114800) / 100 % 10) + "." + parseInt((record.minScore-114800) / 10 % 10) +
              parseInt((record.minScore-114800) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='800米自由泳'&&record.distance==='50'&&record.minScore<=150200&&record.minScore>120200">
              当前三级,距二级差{{parseInt((record.minScore-120200) / 10000) + ":" + parseInt((record.minScore-120200) / 1000
              % 10)
              + parseInt((record.minScore-120200) / 100 % 10) + "." + parseInt((record.minScore-120200) / 10 % 10) +
              parseInt((record.minScore-120200) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='800米自由泳'&&record.distance==='25'&&record.minScore>144800">
              距三级差{{parseInt((record.minScore-144800) / 10000) + ":" + parseInt((record.minScore-144800) / 1000
              % 10)
              + parseInt((record.minScore-144800) / 100 % 10) + "." + parseInt((record.minScore-144800) / 10 % 10) +
              parseInt((record.minScore-144800) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='800米自由泳'&&record.distance==='50'&&record.minScore>150200">
              距三级差{{parseInt((record.minScore-150200) / 10000) + ":" + parseInt((record.minScore-150200) / 1000
              % 10)
              + parseInt((record.minScore-150200) / 100 % 10) + "." + parseInt((record.minScore-150200) / 10 % 10) +
              parseInt((record.minScore-150200) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='1500米自由泳'&&record.distance==='25'&&record.minScore<=161250&&record.minScore>0">
              当前国际级健将
            </template>
            <template
              v-else-if="record.matchItem==='1500米自由泳'&&record.distance==='50'&&record.minScore<=163740&&record.minScore>0">
              当前国际级健将
            </template>
            <template
              v-else-if="record.matchItem==='1500米自由泳'&&record.distance==='25'&&record.minScore<=164900&&record.minScore>161250">
              当前运动健将,距国际级健将差{{parseInt((record.minScore-161250) / 10000) + ":" + parseInt((record.minScore-161250) /
              1000
              % 10)
              + parseInt((record.minScore-161250) / 100 % 10) + "." + parseInt((record.minScore-161250) / 10 % 10) +
              parseInt((record.minScore-161250) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='1500米自由泳'&&record.distance==='50'&&record.minScore<=171400&&record.minScore>163740">
              当前运动健将,距国际级健将差{{parseInt((record.minScore-163740) / 10000) + ":" + parseInt((record.minScore-163740) /
              1000
              % 10)
              + parseInt((record.minScore-163740) / 100 % 10) + "." + parseInt((record.minScore-163740) / 10 % 10) +
              parseInt((record.minScore-163740) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='1500米自由泳'&&record.distance==='25'&&record.minScore<=181000&&record.minScore>164900">
              当前一级,距运动健将差{{parseInt((record.minScore-164900) / 10000) + ":" + parseInt((record.minScore-164900) / 1000
              % 10)
              + parseInt((record.minScore-164900) / 100 % 10) + "." + parseInt((record.minScore-164900) / 10 % 10) +
              parseInt((record.minScore-164900) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='1500米自由泳'&&record.distance==='50'&&record.minScore<=183500&&record.minScore>171400">
              当前一级,距运动健将差{{parseInt((record.minScore-171400) / 10000) + ":" + parseInt((record.minScore-171400) / 1000
              % 10)
              + parseInt((record.minScore-171400) / 100 % 10) + "." + parseInt((record.minScore-171400) / 10 % 10) +
              parseInt((record.minScore-171400) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='1500米自由泳'&&record.distance==='25'&&record.minScore<=232000&&record.minScore>181000">
              当前二级,距一级差{{parseInt((record.minScore-181000) / 10000) + ":" + parseInt((record.minScore-181000) / 1000
              % 10)
              + parseInt((record.minScore-181000) / 100 % 10) + "." + parseInt((record.minScore-181000) / 10 % 10) +
              parseInt((record.minScore-181000) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='1500米自由泳'&&record.distance==='50'&&record.minScore<=234500&&record.minScore>183500">
              当前二级,距一级差{{parseInt((record.minScore-183500) / 10000) + ":" + parseInt((record.minScore-183500) / 1000
              % 10)
              + parseInt((record.minScore-183500) / 100 % 10) + "." + parseInt((record.minScore-183500) / 10 % 10) +
              parseInt((record.minScore-183500) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='1500米自由泳'&&record.distance==='25'&&record.minScore<=272000&&record.minScore>232000">
              当前三级,距二级差{{parseInt((record.minScore-232000) / 10000) + ":" + parseInt((record.minScore-232000) / 1000
              % 10)
              + parseInt((record.minScore-232000) / 100 % 10) + "." + parseInt((record.minScore-232000) / 10 % 10) +
              parseInt((record.minScore-232000) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='1500米自由泳'&&record.distance==='50'&&record.minScore<=274500&&record.minScore>234500">
              当前三级,距二级差{{parseInt((record.minScore-234500) / 10000) + ":" + parseInt((record.minScore-234500) / 1000
              % 10)
              + parseInt((record.minScore-234500) / 100 % 10) + "." + parseInt((record.minScore-234500) / 10 % 10) +
              parseInt((record.minScore-234500) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='1500米自由泳'&&record.distance==='25'&&record.minScore>272000">
              距三级差{{parseInt((record.minScore-272000) / 10000) + ":" + parseInt((record.minScore-272000) / 1000
              % 10)
              + parseInt((record.minScore-272000) / 100 % 10) + "." + parseInt((record.minScore-272000) / 10 % 10) +
              parseInt((record.minScore-272000) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='1500米自由泳'&&record.distance==='50'&&record.minScore>274500">
              距三级差{{parseInt((record.minScore-274500) / 10000) + ":" + parseInt((record.minScore-274500) / 1000
              % 10)
              + parseInt((record.minScore-274500) / 100 % 10) + "." + parseInt((record.minScore-274500) / 10 % 10) +
              parseInt((record.minScore-274500) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='50米仰泳'&&record.distance==='25'&&record.minScore<=2761&&record.minScore>0">
              当前国际级健将
            </template>
            <template
              v-else-if="record.matchItem==='50米仰泳'&&record.distance==='50'&&record.minScore<=2861&&record.minScore>0">
              当前国际级健将
            </template>
            <template
              v-else-if="record.matchItem==='50米仰泳'&&record.distance==='25'&&record.minScore<=2955&&record.minScore>2761">
              当前运动健将,距国际级健将差{{parseInt((record.minScore-2761) / 10000) + ":" + parseInt((record.minScore-2761) / 1000
              % 10)
              + parseInt((record.minScore-2761) / 100 % 10) + "." + parseInt((record.minScore-2761) / 10 % 10) +
              parseInt((record.minScore-2761) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='50米仰泳'&&record.distance==='50'&&record.minScore<=3055&&record.minScore>2861">
              当前运动健将,距国际级健将差{{parseInt((record.minScore-2861) / 10000) + ":" + parseInt((record.minScore-2861) / 1000
              % 10)
              + parseInt((record.minScore-2861) / 100 % 10) + "." + parseInt((record.minScore-2861) / 10 % 10) +
              parseInt((record.minScore-2861) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='50米仰泳'&&record.distance==='25'&&record.minScore<=3200&&record.minScore>2955">
              当前一级,距运动健将差{{parseInt((record.minScore-2955) / 10000) + ":" + parseInt((record.minScore-2955) / 1000
              % 10)
              + parseInt((record.minScore-2955) / 100 % 10) + "." + parseInt((record.minScore-2955) / 10 % 10) +
              parseInt((record.minScore-2955) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='50米仰泳'&&record.distance==='50'&&record.minScore<=3300&&record.minScore>3055">
              当前一级,距运动健将差{{parseInt((record.minScore-3055) / 10000) + ":" + parseInt((record.minScore-3055) / 1000
              % 10)
              + parseInt((record.minScore-3055) / 100 % 10) + "." + parseInt((record.minScore-3055) / 10 % 10) +
              parseInt((record.minScore-3055) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='50米仰泳'&&record.distance==='25'&&record.minScore<=3750&&record.minScore>3200">
              当前二级,距一级差{{parseInt((record.minScore-3200) / 10000) + ":" + parseInt((record.minScore-3200) / 1000
              % 10)
              + parseInt((record.minScore-3200) / 100 % 10) + "." + parseInt((record.minScore-3200) / 10 % 10) +
              parseInt((record.minScore-3200) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='50米仰泳'&&record.distance==='50'&&record.minScore<=3850&&record.minScore>3300">
              当前二级,距一级差{{parseInt((record.minScore-3300) / 10000) + ":" + parseInt((record.minScore-3300) / 1000
              % 10)
              + parseInt((record.minScore-3300) / 100 % 10) + "." + parseInt((record.minScore-3300) / 10 % 10) +
              parseInt((record.minScore-3300) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='50米仰泳'&&record.distance==='25'&&record.minScore<=4550&&record.minScore>3750">
              当前三级,距二级差{{parseInt((record.minScore-3750) / 10000) + ":" + parseInt((record.minScore-3750) / 1000
              % 10)
              + parseInt((record.minScore-3750) / 100 % 10) + "." + parseInt((record.minScore-3750) / 10 % 10) +
              parseInt((record.minScore-3750) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='50米仰泳'&&record.distance==='50'&&record.minScore<=4650&&record.minScore>3850">
              当前三级,距二级差{{parseInt((record.minScore-3850) / 10000) + ":" + parseInt((record.minScore-3850) / 1000
              % 10)
              + parseInt((record.minScore-3850) / 100 % 10) + "." + parseInt((record.minScore-3850) / 10 % 10) +
              parseInt((record.minScore-3850) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='50米仰泳'&&record.distance==='25'&&record.minScore>4550">
              距三级差{{parseInt((record.minScore-4550) / 10000) + ":" + parseInt((record.minScore-4550) / 1000
              % 10)
              + parseInt((record.minScore-4550) / 100 % 10) + "." + parseInt((record.minScore-4550) / 10 % 10) +
              parseInt((record.minScore-4550) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='50米仰泳'&&record.distance==='50'&&record.minScore>4650">
              距三级差{{parseInt((record.minScore-4650) / 10000) + ":" + parseInt((record.minScore-4650) / 1000
              % 10)
              + parseInt((record.minScore-4650) / 100 % 10) + "." + parseInt((record.minScore-4650) / 10 % 10) +
              parseInt((record.minScore-4650) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='100米仰泳'&&record.distance==='25'&&record.minScore<=5969&&record.minScore>0">
              当前国际级健将
            </template>
            <template
              v-else-if="record.matchItem==='100米仰泳'&&record.distance==='50'&&record.minScore<=10070&&record.minScore>0">
              当前国际级健将
            </template>
            <template
              v-else-if="record.matchItem==='100米仰泳'&&record.distance==='25'&&record.minScore<=10330&&record.minScore>5969">
              当前运动健将,距国际级健将差{{parseInt((record.minScore-5969) / 10000) + ":" + parseInt((record.minScore-5969) / 1000
              % 10)
              + parseInt((record.minScore-5969) / 100 % 10) + "." + parseInt((record.minScore-5969) / 10 % 10) +
              parseInt((record.minScore-5969) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='100米仰泳'&&record.distance==='50'&&record.minScore<=10430&&record.minScore>10070">
              当前运动健将,距国际级健将差{{parseInt((record.minScore-10070) / 10000) + ":" + parseInt((record.minScore-10070) / 1000
              % 10)
              + parseInt((record.minScore-10070) / 100 % 10) + "." + parseInt((record.minScore-10070) / 10 % 10) +
              parseInt((record.minScore-10070) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='100米仰泳'&&record.distance==='25'&&record.minScore<=10800&&record.minScore>10330">
              当前一级,距运动健将差{{parseInt((record.minScore-10330) / 10000) + ":" + parseInt((record.minScore-10330) / 1000
              % 10)
              + parseInt((record.minScore-10330) / 100 % 10) + "." + parseInt((record.minScore-10330) / 10 % 10) +
              parseInt((record.minScore-10330) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='100米仰泳'&&record.distance==='50'&&record.minScore<=10900&&record.minScore>10430">
              当前一级,距运动健将差{{parseInt((record.minScore-10430) / 10000) + ":" + parseInt((record.minScore-10430) / 1000
              % 10)
              + parseInt((record.minScore-10430) / 100 % 10) + "." + parseInt((record.minScore-10430) / 10 % 10) +
              parseInt((record.minScore-10430) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='100米仰泳'&&record.distance==='25'&&record.minScore<=12000&&record.minScore>10800">
              当前二级,距一级差{{parseInt((record.minScore-10800) / 10000) + ":" + parseInt((record.minScore-10800) / 1000
              % 10)
              + parseInt((record.minScore-10800) / 100 % 10) + "." + parseInt((record.minScore-10800) / 10 % 10) +
              parseInt((record.minScore-10800) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='100米仰泳'&&record.distance==='50'&&record.minScore<=12100&&record.minScore>10900">
              当前二级,距一级差{{parseInt((record.minScore-10900) / 10000) + ":" + parseInt((record.minScore-10900) / 1000
              % 10)
              + parseInt((record.minScore-10900) / 100 % 10) + "." + parseInt((record.minScore-10900) / 10 % 10) +
              parseInt((record.minScore-10900) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='100米仰泳'&&record.distance==='25'&&record.minScore<=14000&&record.minScore>12000">
              当前三级,距二级差{{parseInt((record.minScore-12000) / 10000) + ":" + parseInt((record.minScore-12000) / 1000
              % 10)
              + parseInt((record.minScore-12000) / 100 % 10) + "." + parseInt((record.minScore-12000) / 10 % 10) +
              parseInt((record.minScore-12000) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='100米仰泳'&&record.distance==='50'&&record.minScore<=14100&&record.minScore>12100">
              当前三级,距二级差{{parseInt((record.minScore-12100) / 10000) + ":" + parseInt((record.minScore-12100) / 1000
              % 10)
              + parseInt((record.minScore-12100) / 100 % 10) + "." + parseInt((record.minScore-12100) / 10 % 10) +
              parseInt((record.minScore-12100) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='100米仰泳'&&record.distance==='25'&&record.minScore>14000">
              距三级差{{parseInt((record.minScore-14000) / 10000) + ":" + parseInt((record.minScore-14000) / 1000
              % 10)
              + parseInt((record.minScore-14000) / 100 % 10) + "." + parseInt((record.minScore-14000) / 10 % 10) +
              parseInt((record.minScore-14000) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='100米仰泳'&&record.distance==='50'&&record.minScore>14100">
              距三级差{{parseInt((record.minScore-14100) / 10000) + ":" + parseInt((record.minScore-14100) / 1000
              % 10)
              + parseInt((record.minScore-14100) / 100 % 10) + "." + parseInt((record.minScore-14100) / 10 % 10) +
              parseInt((record.minScore-14100) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='200米仰泳'&&record.distance==='25'&&record.minScore<=20810&&record.minScore>0">
              当前国际级健将
            </template>
            <template
              v-else-if="record.matchItem==='200米仰泳'&&record.distance==='50'&&record.minScore<=21010&&record.minScore>0">
              当前国际级健将
            </template>
            <template
              v-else-if="record.matchItem==='200米仰泳'&&record.distance==='25'&&record.minScore<=21630&&record.minScore>20810">
              当前运动健将,距国际级健将差{{parseInt((record.minScore-20810) / 10000) + ":" + parseInt((record.minScore-20810) / 1000
              % 10)
              + parseInt((record.minScore-20810) / 100 % 10) + "." + parseInt((record.minScore-20810) / 10 % 10) +
              parseInt((record.minScore-20810) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='200米仰泳'&&record.distance==='50'&&record.minScore<=21830&&record.minScore>21010">
              当前运动健将,距国际级健将差{{parseInt((record.minScore-21010) / 10000) + ":" + parseInt((record.minScore-21010) / 1000
              % 10)
              + parseInt((record.minScore-21010) / 100 % 10) + "." + parseInt((record.minScore-21010) / 10 % 10) +
              parseInt((record.minScore-21010) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='200米仰泳'&&record.distance==='25'&&record.minScore<=22700&&record.minScore>21630">
              当前一级,距运动健将差{{parseInt((record.minScore-21630) / 10000) + ":" + parseInt((record.minScore-21630) / 1000
              % 10)
              + parseInt((record.minScore-21630) / 100 % 10) + "." + parseInt((record.minScore-21630) / 10 % 10) +
              parseInt((record.minScore-21630) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='200米仰泳'&&record.distance==='50'&&record.minScore<=22950&&record.minScore>21830">
              当前一级,距运动健将差{{parseInt((record.minScore-21830) / 10000) + ":" + parseInt((record.minScore-21830) / 1000
              % 10)
              + parseInt((record.minScore-21830) / 100 % 10) + "." + parseInt((record.minScore-21830) / 10 % 10) +
              parseInt((record.minScore-21830) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='200米仰泳'&&record.distance==='25'&&record.minScore<=25100&&record.minScore>22700">
              当前二级,距一级差{{parseInt((record.minScore-22700) / 10000) + ":" + parseInt((record.minScore-22700) / 1000
              % 10)
              + parseInt((record.minScore-22700) / 100 % 10) + "." + parseInt((record.minScore-22700) / 10 % 10) +
              parseInt((record.minScore-22700) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='200米仰泳'&&record.distance==='50'&&record.minScore<=25300&&record.minScore>22950">
              当前二级,距一级差{{parseInt((record.minScore-22950) / 10000) + ":" + parseInt((record.minScore-22950) / 1000
              % 10)
              + parseInt((record.minScore-22950) / 100 % 10) + "." + parseInt((record.minScore-22950) / 10 % 10) +
              parseInt((record.minScore-22950) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='200米仰泳'&&record.distance==='25'&&record.minScore<=33650&&record.minScore>25100">
              当前三级,距二级差{{parseInt((record.minScore-25100) / 10000) + ":" + parseInt((record.minScore-25100) / 1000
              % 10)
              + parseInt((record.minScore-25100) / 100 % 10) + "." + parseInt((record.minScore-25100) / 10 % 10) +
              parseInt((record.minScore-25100) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='200米仰泳'&&record.distance==='50'&&record.minScore<=33850&&record.minScore>25300">
              当前三级,距二级差{{parseInt((record.minScore-25300) / 10000) + ":" + parseInt((record.minScore-25300) / 1000
              % 10)
              + parseInt((record.minScore-25300) / 100 % 10) + "." + parseInt((record.minScore-25300) / 10 % 10) +
              parseInt((record.minScore-25300) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='200米仰泳'&&record.distance==='25'&&record.minScore>33650">
              距三级差{{parseInt((record.minScore-33650) / 10000) + ":" + parseInt((record.minScore-33650) / 1000
              % 10)
              + parseInt((record.minScore-33650) / 100 % 10) + "." + parseInt((record.minScore-33650) / 10 % 10) +
              parseInt((record.minScore-33650) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='200米仰泳'&&record.distance==='50'&&record.minScore>33850">
              距三级差{{parseInt((record.minScore-33850) / 10000) + ":" + parseInt((record.minScore-33850) / 1000
              % 10)
              + parseInt((record.minScore-33850) / 100 % 10) + "." + parseInt((record.minScore-33850) / 10 % 10) +
              parseInt((record.minScore-33850) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='50米蛙泳'&&record.distance==='25'&&record.minScore<=3023&&record.minScore>0">
              当前国际级健将
            </template>
            <template
              v-else-if="record.matchItem==='50米蛙泳'&&record.distance==='50'&&record.minScore<=3123&&record.minScore>0">
              当前国际级健将
            </template>
            <template
              v-else-if="record.matchItem==='50米蛙泳'&&record.distance==='25'&&record.minScore<=3070&&record.minScore>3023">
              当前运动健将,距国际级健将差{{parseInt((record.minScore-3023) / 10000) + ":" + parseInt((record.minScore-3023) / 1000
              % 10)
              + parseInt((record.minScore-3023) / 100 % 10) + "." + parseInt((record.minScore-3023) / 10 % 10) +
              parseInt((record.minScore-3023) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='50米蛙泳'&&record.distance==='50'&&record.minScore<=3170&&record.minScore>3123">
              当前运动健将,距国际级健将差{{parseInt((record.minScore-3123) / 10000) + ":" + parseInt((record.minScore-3123) / 1000
              % 10)
              + parseInt((record.minScore-3123) / 100 % 10) + "." + parseInt((record.minScore-3123) / 10 % 10) +
              parseInt((record.minScore-3123) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='50米蛙泳'&&record.distance==='25'&&record.minScore<=3500&&record.minScore>3070">
              当前一级,距运动健将差{{parseInt((record.minScore-3070) / 10000) + ":" + parseInt((record.minScore-3070) / 1000
              % 10)
              + parseInt((record.minScore-3070) / 100 % 10) + "." + parseInt((record.minScore-3070) / 10 % 10) +
              parseInt((record.minScore-3070) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='50米蛙泳'&&record.distance==='50'&&record.minScore<=3600&&record.minScore>3170">
              当前一级,距运动健将差{{parseInt((record.minScore-3170) / 10000) + ":" + parseInt((record.minScore-3170) / 1000
              % 10)
              + parseInt((record.minScore-3170) / 100 % 10) + "." + parseInt((record.minScore-3170) / 10 % 10) +
              parseInt((record.minScore-3170) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='50米蛙泳'&&record.distance==='25'&&record.minScore<=4000&&record.minScore>3500">
              当前二级,距一级差{{parseInt((record.minScore-3500) / 10000) + ":" + parseInt((record.minScore-3500) / 1000
              % 10)
              + parseInt((record.minScore-3500) / 100 % 10) + "." + parseInt((record.minScore-3500) / 10 % 10) +
              parseInt((record.minScore-3500) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='50米蛙泳'&&record.distance==='50'&&record.minScore<=4100&&record.minScore>3600">
              当前二级,距一级差{{parseInt((record.minScore-3600) / 10000) + ":" + parseInt((record.minScore-3600) / 1000
              % 10)
              + parseInt((record.minScore-3600) / 100 % 10) + "." + parseInt((record.minScore-3600) / 10 % 10) +
              parseInt((record.minScore-3600) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='50米蛙泳'&&record.distance==='25'&&record.minScore<=4700&&record.minScore>4000">
              当前三级,距二级差{{parseInt((record.minScore-4000) / 10000) + ":" + parseInt((record.minScore-4000) / 1000
              % 10)
              + parseInt((record.minScore-4000) / 100 % 10) + "." + parseInt((record.minScore-4000) / 10 % 10) +
              parseInt((record.minScore-4000) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='50米蛙泳'&&record.distance==='50'&&record.minScore<=4800&&record.minScore>4100">
              当前三级,距二级差{{parseInt((record.minScore-4100) / 10000) + ":" + parseInt((record.minScore-4100) / 1000
              % 10)
              + parseInt((record.minScore-4100) / 100 % 10) + "." + parseInt((record.minScore-4100) / 10 % 10) +
              parseInt((record.minScore-4100) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='50米蛙泳'&&record.distance==='25'&&record.minScore>4700">
              距三级差{{parseInt((record.minScore-4700) / 10000) + ":" + parseInt((record.minScore-4700) / 1000
              % 10)
              + parseInt((record.minScore-4700) / 100 % 10) + "." + parseInt((record.minScore-4700) / 10 % 10) +
              parseInt((record.minScore-4700) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='50米蛙泳'&&record.distance==='50'&&record.minScore>4800">
              距三级差{{parseInt((record.minScore-4800) / 10000) + ":" + parseInt((record.minScore-4800) / 1000
              % 10)
              + parseInt((record.minScore-4800) / 100 % 10) + "." + parseInt((record.minScore-4800) / 10 % 10) +
              parseInt((record.minScore-4800) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='100米蛙泳'&&record.distance==='25'&&record.minScore<=10540&&record.minScore>0">
              当前国际级健将
            </template>
            <template
              v-else-if="record.matchItem==='100米蛙泳'&&record.distance==='50'&&record.minScore<=10740&&record.minScore>0">
              当前国际级健将
            </template>
            <template
              v-else-if="record.matchItem==='100米蛙泳'&&record.distance==='25'&&record.minScore<=10870&&record.minScore>10540">
              当前运动健将,距国际级健将差{{parseInt((record.minScore-10540) / 10000) + ":" + parseInt((record.minScore-10540) / 1000
              % 10)
              + parseInt((record.minScore-10540) / 100 % 10) + "." + parseInt((record.minScore-10540) / 10 % 10) +
              parseInt((record.minScore-10540) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='100米蛙泳'&&record.distance==='50'&&record.minScore<=11070&&record.minScore>10740">
              当前运动健将,距国际级健将差{{parseInt((record.minScore-10740) / 10000) + ":" + parseInt((record.minScore-10740) / 1000
              % 10)
              + parseInt((record.minScore-10740) / 100 % 10) + "." + parseInt((record.minScore-10740) / 10 % 10) +
              parseInt((record.minScore-10740) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='100米蛙泳'&&record.distance==='25'&&record.minScore<=11600&&record.minScore>10870">
              当前一级,距运动健将差{{parseInt((record.minScore-10870) / 10000) + ":" + parseInt((record.minScore-10870) / 1000
              % 10)
              + parseInt((record.minScore-10870) / 100 % 10) + "." + parseInt((record.minScore-10870) / 10 % 10) +
              parseInt((record.minScore-10870) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='100米蛙泳'&&record.distance==='50'&&record.minScore<=11800&&record.minScore>11070">
              当前一级,距运动健将差{{parseInt((record.minScore-11070) / 10000) + ":" + parseInt((record.minScore-11070) / 1000
              % 10)
              + parseInt((record.minScore-11070) / 100 % 10) + "." + parseInt((record.minScore-11070) / 10 % 10) +
              parseInt((record.minScore-11070) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='100米蛙泳'&&record.distance==='25'&&record.minScore<=12700&&record.minScore>11600">
              当前二级,距一级差{{parseInt((record.minScore-11600) / 10000) + ":" + parseInt((record.minScore-11600) / 1000
              % 10)
              + parseInt((record.minScore-11600) / 100 % 10) + "." + parseInt((record.minScore-11600) / 10 % 10) +
              parseInt((record.minScore-11600) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='100米蛙泳'&&record.distance==='50'&&record.minScore<=12900&&record.minScore>11800">
              当前二级,距一级差{{parseInt((record.minScore-11800) / 10000) + ":" + parseInt((record.minScore-11800) / 1000
              % 10)
              + parseInt((record.minScore-11800) / 100 % 10) + "." + parseInt((record.minScore-11800) / 10 % 10) +
              parseInt((record.minScore-11800) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='100米蛙泳'&&record.distance==='25'&&record.minScore<=14200&&record.minScore>12700">
              当前三级,距二级差{{parseInt((record.minScore-12700) / 10000) + ":" + parseInt((record.minScore-12700) / 1000
              % 10)
              + parseInt((record.minScore-12700) / 100 % 10) + "." + parseInt((record.minScore-12700) / 10 % 10) +
              parseInt((record.minScore-12700) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='100米蛙泳'&&record.distance==='50'&&record.minScore<=14400&&record.minScore>12900">
              当前三级,距二级差{{parseInt((record.minScore-12900) / 10000) + ":" + parseInt((record.minScore-12900) / 1000
              % 10)
              + parseInt((record.minScore-12900) / 100 % 10) + "." + parseInt((record.minScore-12900) / 10 % 10) +
              parseInt((record.minScore-12900) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='100米蛙泳'&&record.distance==='25'&&record.minScore>14200">
              距三级差{{parseInt((record.minScore-14200) / 10000) + ":" + parseInt((record.minScore-14200) / 1000
              % 10)
              + parseInt((record.minScore-14200) / 100 % 10) + "." + parseInt((record.minScore-14200) / 10 % 10) +
              parseInt((record.minScore-14200) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='100米蛙泳'&&record.distance==='50'&&record.minScore>14400">
              距三级差{{parseInt((record.minScore-14400) / 10000) + ":" + parseInt((record.minScore-14400) / 1000
              % 10)
              + parseInt((record.minScore-14400) / 100 % 10) + "." + parseInt((record.minScore-14400) / 10 % 10) +
              parseInt((record.minScore-14400) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='200米蛙泳'&&record.distance==='25'&&record.minScore<=22120&&record.minScore>0">
              当前国际级健将
            </template>
            <template
              v-else-if="record.matchItem==='200米蛙泳'&&record.distance==='50'&&record.minScore<=22520&&record.minScore>0">
              当前国际级健将
            </template>
            <template
              v-else-if="record.matchItem==='200米蛙泳'&&record.distance==='25'&&record.minScore<=23260&&record.minScore>22120">
              当前运动健将,距国际级健将差{{parseInt((record.minScore-22120) / 10000) + ":" + parseInt((record.minScore-22120) / 1000
              % 10)
              + parseInt((record.minScore-22120) / 100 % 10) + "." + parseInt((record.minScore-22120) / 10 % 10) +
              parseInt((record.minScore-22120) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='200米蛙泳'&&record.distance==='50'&&record.minScore<=23660&&record.minScore>22520">
              当前运动健将,距国际级健将差{{parseInt((record.minScore-22520) / 10000) + ":" + parseInt((record.minScore-22520) / 1000
              % 10)
              + parseInt((record.minScore-22520) / 100 % 10) + "." + parseInt((record.minScore-22520) / 10 % 10) +
              parseInt((record.minScore-22520) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='200米蛙泳'&&record.distance==='25'&&record.minScore<=24700&&record.minScore>23260">
              当前一级,距运动健将差{{parseInt((record.minScore-23260) / 10000) + ":" + parseInt((record.minScore-23260) / 1000
              % 10)
              + parseInt((record.minScore-23260) / 100 % 10) + "." + parseInt((record.minScore-23260) / 10 % 10) +
              parseInt((record.minScore-23260) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='200米蛙泳'&&record.distance==='50'&&record.minScore<=25100&&record.minScore>23660">
              当前一级,距运动健将差{{parseInt((record.minScore-23660) / 10000) + ":" + parseInt((record.minScore-23660) / 1000
              % 10)
              + parseInt((record.minScore-23660) / 100 % 10) + "." + parseInt((record.minScore-23660) / 10 % 10) +
              parseInt((record.minScore-23660) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='200米蛙泳'&&record.distance==='25'&&record.minScore<=30900&&record.minScore>24700">
              当前二级,距一级差{{parseInt((record.minScore-24700) / 10000) + ":" + parseInt((record.minScore-24700) / 1000
              % 10)
              + parseInt((record.minScore-24700) / 100 % 10) + "." + parseInt((record.minScore-24700) / 10 % 10) +
              parseInt((record.minScore-24700) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='200米蛙泳'&&record.distance==='50'&&record.minScore<=31300&&record.minScore>25100">
              当前二级,距一级差{{parseInt((record.minScore-25100) / 10000) + ":" + parseInt((record.minScore-25100) / 1000
              % 10)
              + parseInt((record.minScore-25100) / 100 % 10) + "." + parseInt((record.minScore-25100) / 10 % 10) +
              parseInt((record.minScore-25100) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='200米蛙泳'&&record.distance==='25'&&record.minScore<=34400&&record.minScore>30900">
              当前三级,距二级差{{parseInt((record.minScore-30900) / 10000) + ":" + parseInt((record.minScore-30900) / 1000
              % 10)
              + parseInt((record.minScore-30900) / 100 % 10) + "." + parseInt((record.minScore-30900) / 10 % 10) +
              parseInt((record.minScore-30900) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='200米蛙泳'&&record.distance==='50'&&record.minScore<=34800&&record.minScore>31300">
              当前三级,距二级差{{parseInt((record.minScore-31300) / 10000) + ":" + parseInt((record.minScore-31300) / 1000
              % 10)
              + parseInt((record.minScore-31300) / 100 % 10) + "." + parseInt((record.minScore-31300) / 10 % 10) +
              parseInt((record.minScore-31300) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='200米蛙泳'&&record.distance==='25'&&record.minScore>34400">
              距三级差{{parseInt((record.minScore-34400) / 10000) + ":" + parseInt((record.minScore-34400) / 1000
              % 10)
              + parseInt((record.minScore-34400) / 100 % 10) + "." + parseInt((record.minScore-34400) / 10 % 10) +
              parseInt((record.minScore-34400) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='200米蛙泳'&&record.distance==='50'&&record.minScore>34800">
              距三级差{{parseInt((record.minScore-34800) / 10000) + ":" + parseInt((record.minScore-34800) / 1000
              % 10)
              + parseInt((record.minScore-34800) / 100 % 10) + "." + parseInt((record.minScore-34800) / 10 % 10) +
              parseInt((record.minScore-34800) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='50米蝶泳'&&record.distance==='25'&&record.minScore<=2547&&record.minScore>0">
              当前国际级健将
            </template>
            <template
              v-else-if="record.matchItem==='50米蝶泳'&&record.distance==='50'&&record.minScore<=2647&&record.minScore>0">
              当前国际级健将
            </template>
            <template
              v-else-if="record.matchItem==='50米蝶泳'&&record.distance==='25'&&record.minScore<=2650&&record.minScore>2547">
              当前运动健将,距国际级健将差{{parseInt((record.minScore-2547) / 10000) + ":" + parseInt((record.minScore-2547) / 1000
              % 10)
              + parseInt((record.minScore-2547) / 100 % 10) + "." + parseInt((record.minScore-2547) / 10 % 10) +
              parseInt((record.minScore-2547) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='50米蝶泳'&&record.distance==='50'&&record.minScore<=2750&&record.minScore>2647">
              当前运动健将,距国际级健将差{{parseInt((record.minScore-2647) / 10000) + ":" + parseInt((record.minScore-2647) / 1000
              % 10)
              + parseInt((record.minScore-2647) / 100 % 10) + "." + parseInt((record.minScore-2647) / 10 % 10) +
              parseInt((record.minScore-2647) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='50米蝶泳'&&record.distance==='25'&&record.minScore<=2950&&record.minScore>2650">
              当前一级,距运动健将差{{parseInt((record.minScore-2650) / 10000) + ":" + parseInt((record.minScore-2650) / 1000
              % 10)
              + parseInt((record.minScore-2650) / 100 % 10) + "." + parseInt((record.minScore-2650) / 10 % 10) +
              parseInt((record.minScore-2650) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='50米蝶泳'&&record.distance==='50'&&record.minScore<=3050&&record.minScore>2750">
              当前一级,距运动健将差{{parseInt((record.minScore-2750) / 10000) + ":" + parseInt((record.minScore-2750) / 1000
              % 10)
              + parseInt((record.minScore-2750) / 100 % 10) + "." + parseInt((record.minScore-2750) / 10 % 10) +
              parseInt((record.minScore-2750) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='50米蝶泳'&&record.distance==='25'&&record.minScore<=3550&&record.minScore>2950">
              当前二级,距一级差{{parseInt((record.minScore-2950) / 10000) + ":" + parseInt((record.minScore-2950) / 1000
              % 10)
              + parseInt((record.minScore-2950) / 100 % 10) + "." + parseInt((record.minScore-2950) / 10 % 10) +
              parseInt((record.minScore-2950) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='50米蝶泳'&&record.distance==='50'&&record.minScore<=3650&&record.minScore>3050">
              当前二级,距一级差{{parseInt((record.minScore-3050) / 10000) + ":" + parseInt((record.minScore-3050) / 1000
              % 10)
              + parseInt((record.minScore-3050) / 100 % 10) + "." + parseInt((record.minScore-3050) / 10 % 10) +
              parseInt((record.minScore-3050) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='50米蝶泳'&&record.distance==='25'&&record.minScore<=4450&&record.minScore>3550">
              当前三级,距二级差{{parseInt((record.minScore-3550) / 10000) + ":" + parseInt((record.minScore-3550) / 1000
              % 10)
              + parseInt((record.minScore-3550) / 100 % 10) + "." + parseInt((record.minScore-3550) / 10 % 10) +
              parseInt((record.minScore-3550) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='50米蝶泳'&&record.distance==='50'&&record.minScore<=4550&&record.minScore>3650">
              当前三级,距二级差{{parseInt((record.minScore-3650) / 10000) + ":" + parseInt((record.minScore-3650) / 1000
              % 10)
              + parseInt((record.minScore-3650) / 100 % 10) + "." + parseInt((record.minScore-3650) / 10 % 10) +
              parseInt((record.minScore-3650) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='50米蝶泳'&&record.distance==='25'&&record.minScore>4450">
              距三级差{{parseInt((record.minScore-4450) / 10000) + ":" + parseInt((record.minScore-4450) / 1000
              % 10)
              + parseInt((record.minScore-4450) / 100 % 10) + "." + parseInt((record.minScore-4450) / 10 % 10) +
              parseInt((record.minScore-4450) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='50米蝶泳'&&record.distance==='50'&&record.minScore>4550">
              距三级差{{parseInt((record.minScore-4550) / 10000) + ":" + parseInt((record.minScore-4550) / 1000
              % 10)
              + parseInt((record.minScore-4550) / 100 % 10) + "." + parseInt((record.minScore-4550) / 10 % 10) +
              parseInt((record.minScore-4550) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='100米蝶泳'&&record.distance==='25'&&record.minScore<=5661&&record.minScore>0">
              当前国际级健将
            </template>
            <template
              v-else-if="record.matchItem==='100米蝶泳'&&record.distance==='50'&&record.minScore<=5811&&record.minScore>0">
              当前国际级健将
            </template>
            <template
              v-else-if="record.matchItem==='100米蝶泳'&&record.distance==='25'&&record.minScore<=5900&&record.minScore>5661">
              当前运动健将,距国际级健将差{{parseInt((record.minScore-5661) / 10000) + ":" + parseInt((record.minScore-5661) / 1000
              % 10)
              + parseInt((record.minScore-5661) / 100 % 10) + "." + parseInt((record.minScore-5661) / 10 % 10) +
              parseInt((record.minScore-5661) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='100米蝶泳'&&record.distance==='50'&&record.minScore<=10050&&record.minScore>5811">
              当前运动健将,距国际级健将差{{parseInt((record.minScore-5811) / 10000) + ":" + parseInt((record.minScore-5811) / 1000
              % 10)
              + parseInt((record.minScore-5811) / 100 % 10) + "." + parseInt((record.minScore-5811) / 10 % 10) +
              parseInt((record.minScore-5811) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='100米蝶泳'&&record.distance==='25'&&record.minScore<=10650&&record.minScore>5900">
              当前一级,距运动健将差{{parseInt((record.minScore-5900) / 10000) + ":" + parseInt((record.minScore-5900) / 1000
              % 10)
              + parseInt((record.minScore-5900) / 100 % 10) + "." + parseInt((record.minScore-5900) / 10 % 10) +
              parseInt((record.minScore-5900) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='100米蝶泳'&&record.distance==='50'&&record.minScore<=10800&&record.minScore>10050">
              当前一级,距运动健将差{{parseInt((record.minScore-10050) / 10000) + ":" + parseInt((record.minScore-10050) / 1000
              % 10)
              + parseInt((record.minScore-10050) / 100 % 10) + "." + parseInt((record.minScore-10050) / 10 % 10) +
              parseInt((record.minScore-10050) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='100米蝶泳'&&record.distance==='25'&&record.minScore<=11850&&record.minScore>10650">
              当前二级,距一级差{{parseInt((record.minScore-10650) / 10000) + ":" + parseInt((record.minScore-10650) / 1000
              % 10)
              + parseInt((record.minScore-10650) / 100 % 10) + "." + parseInt((record.minScore-10650) / 10 % 10) +
              parseInt((record.minScore-10650) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='100米蝶泳'&&record.distance==='50'&&record.minScore<=12000&&record.minScore>10800">
              当前二级,距一级差{{parseInt((record.minScore-10800) / 10000) + ":" + parseInt((record.minScore-10800) / 1000
              % 10)
              + parseInt((record.minScore-10800) / 100 % 10) + "." + parseInt((record.minScore-10800) / 10 % 10) +
              parseInt((record.minScore-10800) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='100米蝶泳'&&record.distance==='25'&&record.minScore<=13750&&record.minScore>11850">
              当前三级,距二级差{{parseInt((record.minScore-11850) / 10000) + ":" + parseInt((record.minScore-11850) / 1000
              % 10)
              + parseInt((record.minScore-11850) / 100 % 10) + "." + parseInt((record.minScore-11850) / 10 % 10) +
              parseInt((record.minScore-11850) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='100米蝶泳'&&record.distance==='50'&&record.minScore<=13900&&record.minScore>12000">
              当前三级,距二级差{{parseInt((record.minScore-12000) / 10000) + ":" + parseInt((record.minScore-12000) / 1000
              % 10)
              + parseInt((record.minScore-12000) / 100 % 10) + "." + parseInt((record.minScore-12000) / 10 % 10) +
              parseInt((record.minScore-12000) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='100米蝶泳'&&record.distance==='25'&&record.minScore>13750">
              距三级差{{parseInt((record.minScore-13750) / 10000) + ":" + parseInt((record.minScore-13750) / 1000
              % 10)
              + parseInt((record.minScore-13750) / 100 % 10) + "." + parseInt((record.minScore-13750) / 10 % 10) +
              parseInt((record.minScore-13750) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='100米蝶泳'&&record.distance==='50'&&record.minScore>13900">
              距三级差{{parseInt((record.minScore-13900) / 10000) + ":" + parseInt((record.minScore-13900) / 1000
              % 10)
              + parseInt((record.minScore-13900) / 100 % 10) + "." + parseInt((record.minScore-13900) / 10 % 10) +
              parseInt((record.minScore-13900) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='200米蝶泳'&&record.distance==='25'&&record.minScore<=20580&&record.minScore>0">
              当前国际级健将
            </template>
            <template
              v-else-if="record.matchItem==='200米蝶泳'&&record.distance==='50'&&record.minScore<=20880&&record.minScore>0">
              当前国际级健将
            </template>
            <template
              v-else-if="record.matchItem==='200米蝶泳'&&record.distance==='25'&&record.minScore<=21120&&record.minScore>20580">
              当前运动健将,距国际级健将差{{parseInt((record.minScore-20580) / 10000) + ":" + parseInt((record.minScore-20580) / 1000
              % 10)
              + parseInt((record.minScore-20580) / 100 % 10) + "." + parseInt((record.minScore-20580) / 10 % 10) +
              parseInt((record.minScore-20580) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='200米蝶泳'&&record.distance==='50'&&record.minScore<=21420&&record.minScore>20880">
              当前运动健将,距国际级健将差{{parseInt((record.minScore-20880) / 10000) + ":" + parseInt((record.minScore-20880) / 1000
              % 10)
              + parseInt((record.minScore-20880) / 100 % 10) + "." + parseInt((record.minScore-20880) / 10 % 10) +
              parseInt((record.minScore-20880) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='200米蝶泳'&&record.distance==='25'&&record.minScore<=22200&&record.minScore>21120">
              当前一级,距运动健将差{{parseInt((record.minScore-21120) / 10000) + ":" + parseInt((record.minScore-21120) / 1000
              % 10)
              + parseInt((record.minScore-21120) / 100 % 10) + "." + parseInt((record.minScore-21120) / 10 % 10) +
              parseInt((record.minScore-21120) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='200米蝶泳'&&record.distance==='50'&&record.minScore<=22500&&record.minScore>21420">
              当前一级,距运动健将差{{parseInt((record.minScore-21420) / 10000) + ":" + parseInt((record.minScore-21420) / 1000
              % 10)
              + parseInt((record.minScore-21420) / 100 % 10) + "." + parseInt((record.minScore-21420) / 10 % 10) +
              parseInt((record.minScore-21420) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='200米蝶泳'&&record.distance==='25'&&record.minScore<=25150&&record.minScore>22200">
              当前二级,距一级差{{parseInt((record.minScore-22200) / 10000) + ":" + parseInt((record.minScore-22200) / 1000
              % 10)
              + parseInt((record.minScore-22200) / 100 % 10) + "." + parseInt((record.minScore-22200) / 10 % 10) +
              parseInt((record.minScore-22200) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='200米蝶泳'&&record.distance==='50'&&record.minScore<=25450&&record.minScore>22500">
              当前二级,距一级差{{parseInt((record.minScore-22500) / 10000) + ":" + parseInt((record.minScore-22500) / 1000
              % 10)
              + parseInt((record.minScore-22500) / 100 % 10) + "." + parseInt((record.minScore-22500) / 10 % 10) +
              parseInt((record.minScore-22500) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='200米蝶泳'&&record.distance==='25'&&record.minScore<=33500&&record.minScore>25150">
              当前三级,距二级差{{parseInt((record.minScore-25150) / 10000) + ":" + parseInt((record.minScore-25150) / 1000
              % 10)
              + parseInt((record.minScore-25150) / 100 % 10) + "." + parseInt((record.minScore-25150) / 10 % 10) +
              parseInt((record.minScore-25150) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='200米蝶泳'&&record.distance==='50'&&record.minScore<=33800&&record.minScore>25450">
              当前三级,距二级差{{parseInt((record.minScore-25450) / 10000) + ":" + parseInt((record.minScore-25450) / 1000
              % 10)
              + parseInt((record.minScore-25450) / 100 % 10) + "." + parseInt((record.minScore-25450) / 10 % 10) +
              parseInt((record.minScore-25450) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='200米蝶泳'&&record.distance==='25'&&record.minScore>33500">
              距三级差{{parseInt((record.minScore-33500) / 10000) + ":" + parseInt((record.minScore-33500) / 1000
              % 10)
              + parseInt((record.minScore-33500) / 100 % 10) + "." + parseInt((record.minScore-33500) / 10 % 10) +
              parseInt((record.minScore-33500) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='200米蝶泳'&&record.distance==='50'&&record.minScore>33800">
              距三级差{{parseInt((record.minScore-33800) / 10000) + ":" + parseInt((record.minScore-33800) / 1000
              % 10)
              + parseInt((record.minScore-33800) / 100 % 10) + "." + parseInt((record.minScore-33800) / 10 % 10) +
              parseInt((record.minScore-33800) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='200米混合泳'&&record.distance==='25'&&record.minScore<=20980&&record.minScore>0">
              当前国际级健将
            </template>
            <template
              v-else-if="record.matchItem==='200米混合泳'&&record.distance==='50'&&record.minScore<=21280&&record.minScore>0">
              当前国际级健将
            </template>
            <template
              v-else-if="record.matchItem==='200米混合泳'&&record.distance==='25'&&record.minScore<=21540&&record.minScore>20980">
              当前运动健将,距国际级健将差{{parseInt((record.minScore-20980) / 10000) + ":" + parseInt((record.minScore-20980) / 1000
              % 10)
              + parseInt((record.minScore-20980) / 100 % 10) + "." + parseInt((record.minScore-20980) / 10 % 10) +
              parseInt((record.minScore-20980) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='200米混合泳'&&record.distance==='50'&&record.minScore<=21840&&record.minScore>21280">
              当前运动健将,距国际级健将差{{parseInt((record.minScore-21280) / 10000) + ":" + parseInt((record.minScore-21280) / 1000
              % 10)
              + parseInt((record.minScore-21280) / 100 % 10) + "." + parseInt((record.minScore-21280) / 10 % 10) +
              parseInt((record.minScore-21280) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='200米混合泳'&&record.distance==='25'&&record.minScore<=22700&&record.minScore>21540">
              当前一级,距运动健将差{{parseInt((record.minScore-21540) / 10000) + ":" + parseInt((record.minScore-21540) / 1000
              % 10)
              + parseInt((record.minScore-21540) / 100 % 10) + "." + parseInt((record.minScore-21540) / 10 % 10) +
              parseInt((record.minScore-21540) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='200米混合泳'&&record.distance==='50'&&record.minScore<=23000&&record.minScore>21840">
              当前一级,距运动健将差{{parseInt((record.minScore-21840) / 10000) + ":" + parseInt((record.minScore-21840) / 1000
              % 10)
              + parseInt((record.minScore-21840) / 100 % 10) + "." + parseInt((record.minScore-21840) / 10 % 10) +
              parseInt((record.minScore-21840) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='200米混合泳'&&record.distance==='25'&&record.minScore<=25500&&record.minScore>22700">
              当前二级,距一级差{{parseInt((record.minScore-22700) / 10000) + ":" + parseInt((record.minScore-22700) / 1000
              % 10)
              + parseInt((record.minScore-22700) / 100 % 10) + "." + parseInt((record.minScore-22700) / 10 % 10) +
              parseInt((record.minScore-22700) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='200米混合泳'&&record.distance==='50'&&record.minScore<=25800&&record.minScore>23000">
              当前二级,距一级差{{parseInt((record.minScore-23000) / 10000) + ":" + parseInt((record.minScore-23000) / 1000
              % 10)
              + parseInt((record.minScore-23000) / 100 % 10) + "." + parseInt((record.minScore-23000) / 10 % 10) +
              parseInt((record.minScore-23000) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='200米混合泳'&&record.distance==='25'&&record.minScore<=34500&&record.minScore>25500">
              当前三级,距二级差{{parseInt((record.minScore-25500) / 10000) + ":" + parseInt((record.minScore-25500) / 1000
              % 10)
              + parseInt((record.minScore-25500) / 100 % 10) + "." + parseInt((record.minScore-25500) / 10 % 10) +
              parseInt((record.minScore-25500) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='200米混合泳'&&record.distance==='50'&&record.minScore<=34800&&record.minScore>25800">
              当前三级,距二级差{{parseInt((record.minScore-25800) / 10000) + ":" + parseInt((record.minScore-25800) / 1000
              % 10)
              + parseInt((record.minScore-25800) / 100 % 10) + "." + parseInt((record.minScore-25800) / 10 % 10) +
              parseInt((record.minScore-25800) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='200米混合泳'&&record.distance==='25'&&record.minScore>34500">
              距三级差{{parseInt((record.minScore-34500) / 10000) + ":" + parseInt((record.minScore-34500) / 1000
              % 10)
              + parseInt((record.minScore-34500) / 100 % 10) + "." + parseInt((record.minScore-34500) / 10 % 10) +
              parseInt((record.minScore-34500) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='200米混合泳'&&record.distance==='50'&&record.minScore>34800">
              距三级差{{parseInt((record.minScore-34800) / 10000) + ":" + parseInt((record.minScore-34800) / 1000
              % 10)
              + parseInt((record.minScore-34800) / 100 % 10) + "." + parseInt((record.minScore-34800) / 10 % 10) +
              parseInt((record.minScore-34800) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='400米混合泳'&&record.distance==='25'&&record.minScore<=43560&&record.minScore>0">
              当前国际级健将
            </template>
            <template
              v-else-if="record.matchItem==='400米混合泳'&&record.distance==='50'&&record.minScore<=44160&&record.minScore>0">
              当前国际级健将
            </template>
            <template
              v-else-if="record.matchItem==='400米混合泳'&&record.distance==='25'&&record.minScore<=45080&&record.minScore>43560">
              当前运动健将,距国际级健将差{{parseInt((record.minScore-43560) / 10000) + ":" + parseInt((record.minScore-43560) / 1000
              % 10)
              + parseInt((record.minScore-43560) / 100 % 10) + "." + parseInt((record.minScore-43560) / 10 % 10) +
              parseInt((record.minScore-43560) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='400米混合泳'&&record.distance==='50'&&record.minScore<=45680&&record.minScore>44160">
              当前运动健将,距国际级健将差{{parseInt((record.minScore-44160) / 10000) + ":" + parseInt((record.minScore-44160) / 1000
              % 10)
              + parseInt((record.minScore-44160) / 100 % 10) + "." + parseInt((record.minScore-44160) / 10 % 10) +
              parseInt((record.minScore-44160) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='400米混合泳'&&record.distance==='25'&&record.minScore<=51200&&record.minScore>45080">
              当前一级,距运动健将差{{parseInt((record.minScore-45080) / 10000) + ":" + parseInt((record.minScore-45080) / 1000
              % 10)
              + parseInt((record.minScore-45080) / 100 % 10) + "." + parseInt((record.minScore-45080) / 10 % 10) +
              parseInt((record.minScore-45080) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='400米混合泳'&&record.distance==='50'&&record.minScore<=51800&&record.minScore>45680">
              当前一级,距运动健将差{{parseInt((record.minScore-45680) / 10000) + ":" + parseInt((record.minScore-45680) / 1000
              % 10)
              + parseInt((record.minScore-45680) / 100 % 10) + "." + parseInt((record.minScore-45680) / 10 % 10) +
              parseInt((record.minScore-45680) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='400米混合泳'&&record.distance==='25'&&record.minScore<=61500&&record.minScore>51200">
              当前二级,距一级差{{parseInt((record.minScore-51200) / 10000) + ":" + parseInt((record.minScore-51200) / 1000
              % 10)
              + parseInt((record.minScore-51200) / 100 % 10) + "." + parseInt((record.minScore-51200) / 10 % 10) +
              parseInt((record.minScore-51200) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='400米混合泳'&&record.distance==='50'&&record.minScore<=62100&&record.minScore>51800">
              当前二级,距一级差{{parseInt((record.minScore-51800) / 10000) + ":" + parseInt((record.minScore-51800) / 1000
              % 10)
              + parseInt((record.minScore-51800) / 100 % 10) + "." + parseInt((record.minScore-51800) / 10 % 10) +
              parseInt((record.minScore-51800) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='400米混合泳'&&record.distance==='25'&&record.minScore<=80000&&record.minScore>61500">
              当前三级,距二级差{{parseInt((record.minScore-61500) / 10000) + ":" + parseInt((record.minScore-61500) / 1000
              % 10)
              + parseInt((record.minScore-61500) / 100 % 10) + "." + parseInt((record.minScore-61500) / 10 % 10) +
              parseInt((record.minScore-61500) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='400米混合泳'&&record.distance==='50'&&record.minScore<=80600&&record.minScore>62100">
              当前三级,距二级差{{parseInt((record.minScore-62100) / 10000) + ":" + parseInt((record.minScore-62100) / 1000
              % 10)
              + parseInt((record.minScore-62100) / 100 % 10) + "." + parseInt((record.minScore-62100) / 10 % 10) +
              parseInt((record.minScore-62100) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='400米混合泳'&&record.distance==='25'&&record.minScore>80000">
              距三级差{{parseInt((record.minScore-80000) / 10000) + ":" + parseInt((record.minScore-80000) / 1000
              % 10)
              + parseInt((record.minScore-80000) / 100 % 10) + "." + parseInt((record.minScore-80000) / 10 % 10) +
              parseInt((record.minScore-80000) % 10)}}
            </template>
            <template
              v-else-if="record.matchItem==='400米混合泳'&&record.distance==='50'&&record.minScore>80600">
              距三级差{{parseInt((record.minScore-80600) / 10000) + ":" + parseInt((record.minScore-80600) / 1000
              % 10)
              + parseInt((record.minScore-80600) / 100 % 10) + "." + parseInt((record.minScore-80600) / 10 % 10) +
              parseInt((record.minScore-80600) % 10)}}
            </template>
            <template v-else>-</template>
          </template>
        </div>

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
    <ItemShow-modal ref="modalForm" @ok="modalFormOk"/>
  </a-card>
</template>

<script>
  import ItemShowModal from './modules/ItemShowModal'
  import {JeecgListMixin} from '@/mixins/JeecgListMixin'
  import {getAction} from '@/api/manage'

  export default {
    name: "ItemShowList",
    mixins: [JeecgListMixin],
    components: {
      ItemShowModal,
    },
    data() {
      return {
        description: '信息',
        // 表头
        columns: [
          {
            title: '比赛项目',
            align: "center",
            dataIndex: 'matchItem'
          },
          {
            title: '最好成绩',
            align: "center",
            dataIndex: 'minScore',
            customRender: function (t, r, index) {
              return parseInt(r.minScore / 10000) + ":" + parseInt(r.minScore / 1000 % 10) + parseInt(r.minScore / 100 % 10) + "." + parseInt(r.minScore / 10 % 10) + parseInt(r.minScore % 10);
            }
          },
          {
            title: '距上一等级差距',
            align: "center",
            dataIndex: 'rankScore',
            scopedSlots: {customRender: 'level'},
          }],
        url: {
          list: "/bestscoreshow/nameShow/listItemShowByMainId",
          delete: "/bestscoreshow/nameShow/deleteItemShow",
          deleteBatch: "/bestscoreshow/nameShow/deleteBatchItemShow",
        }
      }
    },
    methods: {
      loadData(arg) {
        if (arg === 1) {
          this.ipagination.current = 1;
        }
        var params = this.getQueryParams();
        getAction(this.url.list, {
          sportsmanId: params.mainId
          , pageNo: this.ipagination.current, pageSize: this.ipagination.pageSize
        }).then((res) => {
          if (res.success) {
            this.dataSource = res.result.records;
            this.ipagination.total = res.result.total;
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
        this.$refs.modalForm.title = "添加项目展示";
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
