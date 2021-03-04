<template>
  <a-card :bordered="false" :class="'cust-erp-sub-tab'">
    <!-- 操作按钮区域 -->
    <div class="table-operator" v-if="mainId">
      <a-button @click="handleAdd" type="primary" icon="plus">新增</a-button>
      <a-button type="primary" icon="download" @click="handleExportXls('基础体能评分标准')">导出</a-button>
      <a-upload
        name="file"
        :showUploadList="false"
        :multiple="false"
        :headers="tokenHeader"
        :action="importExcelUrl"
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
          selectedRowKeys.length
        }}</a>项
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

        <template slot="htmlSlot" slot-scope="text">
          <div v-html="text"></div>
        </template>
        <template slot="imgSlot" slot-scope="text">
          <span v-if="!text" style="font-size: 12px;font-style: italic;">无图片</span>
          <img v-else :src="getImgView(text)" height="25px" alt=""
               style="max-width:80px;font-size: 12px;font-style: italic;"/>
        </template>
        <template slot="fileSlot" slot-scope="text">
          <span v-if="!text" style="font-size: 12px;font-style: italic;">无文件</span>
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

        <span slot="action" slot-scope="text, record">
          <a @click="handleEdit(record)">编辑</a>
          <a-divider type="vertical"/>
          <a-popconfirm title="确定删除吗?" @confirm="() => handleDelete(record.id)">
            <a>删除</a>
          </a-popconfirm>
        </span>

        <span slot="bm1" slot-scope="text, record">
          <template v-if="record.sex===1">
            <template v-if="record.bm1<18.0" style="color: #00DB00">
                {{ record.bm1s = 10 }}
            </template>
            <template v-else-if="record.bm1>=18.0&&record.bm1<=18.3">
                {{ record.bm1s = 9 }}
            </template>
            <template v-else-if="record.bm1>=18.4&&record.bm1<=18.7">
                {{ record.bm1s = 8 }}
            </template>
            <template v-else-if="record.bm1>=18.8&&record.bm1<=19.1">
                {{ record.bm1s = 7 }}
            </template>
            <template v-else-if="record.bm1>=19.2&&record.bm1<=19.5">
                {{ record.bm1s = 6 }}
            </template>
            <template v-else-if="record.bm1>=19.6&&record.bm1<=19.9" style="color: red">
                {{ record.bm1s = 5 }}
            </template>
            <template v-else-if="record.bm1>=20.0&&record.bm1<=20.3" style="color: red">
                {{ record.bm1s = 4 }}
            </template>
            <template v-else-if="record.bm1>=20.4&&record.bm1<=20.7" style="color: red">
                {{ record.bm1s = 3 }}
            </template>
            <template v-else-if="record.bm1>=20.8&&record.bm1<=21.1" style="color: red">
                {{ record.bm1s = 2 }}
            </template>
            <template v-else-if="record.bm1>=21.2&&record.bm1<=22.0" style="color: red">
                {{ record.bm1s = 1 }}
            </template>
            <template v-else-if="record.bm1>22" style="color: red">
                {{ record.bm1s = 0 }}
            </template>
          </template>
          <template v-else-if="record.sex===0">
            <template v-if="record.bm1<20.0" style="color: #00DB00">
                {{ record.bm1s = 10 }}
            </template>
            <template v-else-if="record.bm1>=20.0&&record.bm1<=20.3">
                {{ record.bm1s = 9 }}
            </template>
            <template v-else-if="record.bm1>=20.4&&record.bm1<=20.7">
                {{ record.bm1s = 8 }}
            </template>
            <template v-else-if="record.bm1>=20.8&&record.bm1<=21.1">
                {{ record.bm1s = 7 }}
            </template>
            <template v-else-if="record.bm1>=21.2&&record.bm1<=21.5">
                {{ record.bm1s = 6 }}
            </template>
            <template v-else-if="record.bm1>=21.6&&record.bm1<=21.9" style="color: red">
                {{ record.bm1s = 5 }}
            </template>
            <template v-else-if="record.bm1>=22.0&&record.bm1<=22.3" style="color: red">
                {{ record.bm1s = 4 }}
            </template>
            <template v-else-if="record.bm1>=22.4&&record.bm1<=22.7" style="color: red">
                {{ record.bm1s = 3 }}
            </template>
            <template v-else-if="record.bm1>=22.8&&record.bm1<=23.1" style="color: red">
                {{ record.bm1s = 2 }}
            </template>
            <template v-else-if="record.bm1>=23.2&&record.bm1<=24.0" style="color: red">
                {{ record.bm1s = 1 }}
            </template>
            <template v-else-if="record.bm1>24" style="color: red">
                {{ record.bm1s = 0 }}
            </template>
          </template>
        </span>
        <span slot="sittingForward" slot-scope="text, record">
          <tempalte v-if="record.sittingForward>=40" style="color: #00DB00">
            {{ record.sittingForwards = 10 }}
          </tempalte>
          <tempalte v-else-if="record.sittingForward===39">
            {{ record.sittingForwards = 9 }}
          </tempalte>
          <tempalte v-else-if="record.sittingForward===38">
            {{ record.sittingForwards = 8 }}
          </tempalte>
          <tempalte v-else-if="record.sittingForward===37">
            {{ record.sittingForwards = 7 }}
          </tempalte>
          <tempalte v-else-if="record.sittingForward===36">
            {{ record.sittingForwards = 6 }}
          </tempalte>
          <tempalte v-else-if="record.sittingForward===35" style="color: red">
            {{ record.sittingForwards = 5 }}
          </tempalte>
          <tempalte v-else-if="record.sittingForward===34" style="color: red">
            {{ record.sittingForwards = 4 }}
          </tempalte>
          <tempalte v-else-if="record.sittingForward>=31&&record.sittingForward<=33" style="color: red">
            {{ record.sittingForwards = 3 }}
          </tempalte>
          <tempalte v-else-if="record.sittingForward>=28&&record.sittingForward<=30" style="color: red">
            {{ record.sittingForwards = 2 }}
          </tempalte>
          <tempalte v-else-if="record.sittingForward>=25&&record.sittingForward<=27" style="color: red">
            {{ record.sittingForwards = 1 }}
          </tempalte>
          <tempalte v-else-if="record.sittingForward<25" style="color: red">
            {{ record.sittingForwards = 0 }}
          </tempalte>
        </span>
        <span slot="sprint" slot-scope="text, record">
          <tempalte v-if="record.sprint<=4.5" style="color: #00DB00">
            <a v-show="false">
              {{ record.sprints = 10 }}
            </a>10
          </tempalte>
          <tempalte v-else-if="record.sprint===4.6">
            {{ record.sprints = 9 }}
          </tempalte>
          <tempalte v-else-if="record.sprint===4.7">
            {{ record.sprints = 8 }}
          </tempalte>
          <tempalte v-else-if="record.sprint===4.8">
            {{ record.sprints = 7 }}
          </tempalte>
          <tempalte v-else-if="record.sprint===4.9">
            {{ record.sprints = 6 }}
          </tempalte>
          <tempalte v-else-if="record.sprint===5.0" style="color: red">
            {{ record.sprints = 5 }}
          </tempalte>
          <tempalte v-else-if="record.sprint===5.1" style="color: red">
            {{ record.sprints = 4 }}
          </tempalte>
          <tempalte v-else-if="record.sprint===5.2" style="color: red">
            {{ record.sprints = 3 }}
          </tempalte>
          <tempalte v-else-if="record.sprint>5.2" style="color: red">
            {{ record.sprints = 0 }}
          </tempalte>
        </span>
        <span slot="verticalJump" slot-scope="text, record">
          <template v-if="record.sex===1">
            <template v-if="record.verticalJump>=55" style="color: #00DB00">
                {{ record.verticalJumps = 10 }}
            </template>
            <template v-else-if="record.verticalJump===54">
                {{ record.verticalJumps = 9 }}
            </template>
            <template v-else-if="record.verticalJump===53">
                {{ record.verticalJumps = 8 }}
            </template>
            <template v-else-if="record.verticalJump===52">
                {{ record.verticalJumps = 7 }}
            </template>
            <template v-else-if="record.verticalJump===51">
                {{ record.verticalJumps = 6 }}
            </template>
            <template v-else-if="record.verticalJump===50" style="color: red">
                {{ record.verticalJumps = 5 }}
            </template>
            <template v-else-if="record.verticalJump>=47&&record.verticalJump<=49" style="color: red">
                {{ record.verticalJumps = 4 }}
            </template>
            <template v-else-if="record.verticalJump>=43&&record.verticalJump<=46" style="color: red">
                {{ record.verticalJumps = 3 }}
            </template>
            <template v-else-if="record.verticalJump>=39&&record.verticalJump<=42" style="color: red">
                {{ record.verticalJumps = 2 }}
            </template>
            <template v-else-if="record.verticalJump>=35&&record.verticalJump<=38" style="color: red">
                {{ record.verticalJumps = 1 }}
            </template>
            <template v-else-if="record.verticalJump<35" style="color: red">
                {{ record.verticalJumps = 0 }}
            </template>
          </template>
          <template v-else-if="record.sex===0">
            <template v-if="record.verticalJump>=50" style="color: #00DB00">
                {{ record.verticalJumps = 10 }}
            </template>
            <template v-else-if="record.verticalJump===49">
                {{ record.verticalJumps = 9 }}
            </template>
            <template v-else-if="record.verticalJump===48">
                {{ record.verticalJumps = 8 }}
            </template>
            <template v-else-if="record.verticalJump===47">
                {{ record.verticalJumps = 7 }}
            </template>
            <template v-else-if="record.verticalJump===46">
                {{ record.verticalJumps = 6 }}
            </template>
            <template v-else-if="record.verticalJump===45" style="color: red">
                {{ record.verticalJumps = 5 }}
            </template>
            <template v-else-if="record.verticalJump>=42&&record.verticalJump<=44" style="color: red">
                {{ record.verticalJumps = 4 }}
            </template>
            <template v-else-if="record.verticalJump>=38&&record.verticalJump<=41" style="color: red">
                {{ record.verticalJumps = 3 }}
            </template>
            <template v-else-if="record.verticalJump>=34&&record.verticalJump<=37" style="color: red">
                {{ record.verticalJumps = 2 }}
            </template>
            <template v-else-if="record.verticalJump>=30&&record.verticalJump<=33" style="color: red">
                {{ record.verticalJumps = 1 }}
            </template>
            <template v-else-if="record.verticalJump<30" style="color: red">
                {{ record.verticalJumps = 0 }}
            </template>
          </template>
        </span>
        <span slot="squatRelativeStrength" slot-scope="text, record">
          <tempalte v-if="record.squatRelativeStrength>1.2" style="color: #00DB00">
            {{ record.squatRelativeStrengths = 10 }}
          </tempalte>
          <tempalte v-else-if="record.squatRelativeStrength===1.1">
            {{ record.squatRelativeStrengths = 8 }}
          </tempalte>
          <tempalte v-else-if="record.squatRelativeStrength===1" style="color: red">
            {{ record.squatRelativeStrengths = 5 }}
          </tempalte>
          <tempalte v-else-if="record.squatRelativeStrength===0.9" style="color: red">
            {{ record.squatRelativeStrengths = 3 }}
          </tempalte>
          <tempalte v-else-if="record.squatRelativeStrength===0.8" style="color: red">
            {{ record.squatRelativeStrengths = 2 }}
          </tempalte>
          <tempalte v-else-if="record.squatRelativeStrength===0.7" style="color: red">
            {{ record.squatRelativeStrengths = 1 }}
          </tempalte>
          <tempalte v-else-if="record.squatRelativeStrength<=0.6" style="color: red">
            {{ record.squatRelativeStrengths = 0 }}
          </tempalte>
        </span>
        <span slot="benchPressRelativeStrength" slot-scope="text, record">
          <tempalte v-if="record.benchPressRelativeStrength>1.2" style="color: #00DB00">
            {{ record.benchPressRelativeStrengths = 10 }}
          </tempalte>
          <tempalte v-else-if="record.benchPressRelativeStrength===1.1">
            {{ record.benchPressRelativeStrengths = 8 }}
          </tempalte>
          <tempalte v-else-if="record.benchPressRelativeStrength===1" style="color: red">
            {{ record.benchPressRelativeStrengths = 5 }}
          </tempalte>
          <tempalte v-else-if="record.benchPressRelativeStrength===0.9" style="color: red">
            {{ record.benchPressRelativeStrengths = 3 }}
          </tempalte>
          <tempalte v-else-if="record.benchPressRelativeStrength===0.8" style="color: red">
            {{ record.benchPressRelativeStrengths = 2 }}
          </tempalte>
          <tempalte v-else-if="record.benchPressRelativeStrength===0.7" style="color: red">
            {{ record.benchPressRelativeStrengths = 1 }}
          </tempalte>
          <tempalte v-else-if="record.benchPressRelativeStrength<=0.6" style="color: red">
            {{ record.benchPressRelativeStrengths = 0 }}
          </tempalte>
        </span>
        <span slot="pullUps" slot-scope="text, record">
          <template v-if="record.sex===1">
            <template v-if="record.pullUps>=40" style="color: #00DB00">
                {{ record.pullUpss = 10 }}
            </template>
            <template v-else-if="record.pullUps===39">
                {{ record.pullUpss = 9 }}
            </template>
            <template v-else-if="record.pullUps===38">
                {{ record.pullUpss = 8 }}
            </template>
            <template v-else-if="record.pullUps===37">
                {{ record.pullUpss = 7 }}
            </template>
            <template v-else-if="record.pullUps===36">
                {{ record.pullUpss = 6 }}
            </template>
            <template v-else-if="record.pullUps===35" style="color: red">
                {{ record.pullUpss = 5 }}
            </template>
            <template v-else-if="record.pullUps>=31&&record.pullUps<=34" style="color: red">
                {{ record.pullUpss = 4 }}
            </template>
            <template v-else-if="record.pullUps>=27&&record.pullUps<=30" style="color: red">
                {{ record.pullUpss = 3 }}
            </template>
            <template v-else-if="record.pullUps>=23&&record.pullUps<=26" style="color: red">
                {{ record.pullUpss = 2 }}
            </template>
            <template v-else-if="record.pullUps>=18&&record.pullUps<=22" style="color: red">
                {{ record.pullUpss = 1 }}
            </template>
            <template v-else-if="record.pullUps<18" style="color: red">
                {{ record.pullUpss = 0 }}
            </template>
          </template>
          <template v-else-if="record.sex===0">
            <template v-if="record.pullUps>=35" style="color: #00DB00">
                {{ record.pullUpss = 10 }}
            </template>
            <template v-else-if="record.pullUps===34">
                {{ record.pullUpss = 9 }}
            </template>
            <template v-else-if="record.pullUps===33">
                {{ record.pullUpss = 8 }}
            </template>
            <template v-else-if="record.pullUps===32">
                {{ record.pullUpss = 7 }}
            </template>
            <template v-else-if="record.pullUps===31">
                {{ record.pullUpss = 6 }}
            </template>
            <template v-else-if="record.pullUps===30" style="color: red">
                {{ record.pullUpss = 5 }}
            </template>
            <template v-else-if="record.pullUps>=26&&record.pullUps<=29" style="color: red">
                {{ record.pullUpss = 4 }}
            </template>
            <template v-else-if="record.pullUps>=22&&record.pullUps<=25" style="color: red">
                {{ record.pullUpss = 3 }}
            </template>
            <template v-else-if="record.pullUps>=18&&record.pullUps<=21" style="color: red">
                {{ record.pullUpss = 2 }}
            </template>
            <template v-else-if="record.pullUps>=13&&record.pullUps<=17" style="color: red">
                {{ record.pullUpss = 1 }}
            </template>
            <template v-else-if="record.pullUps<13" style="color: red">
                {{ record.pullUpss = 0 }}
            </template>
          </template>
        </span>
        <span slot="absEndurance" slot-scope="text, record">
          <tempalte v-if="record.absEndurance>=120" style="color: #00DB00">
            {{ record.absEndurances = 10 }}
          </tempalte>
          <tempalte v-else-if="record.absEndurance>=115&&record.absEndurance<=119">
            {{ record.absEndurances = 9 }}
          </tempalte>
          <tempalte v-else-if="record.absEndurance>=110&&record.absEndurance<=114">
            {{ record.absEndurances = 8 }}
          </tempalte>
          <tempalte v-else-if="record.absEndurance>=105&&record.absEndurance<=109">
            {{ record.absEndurances = 7 }}
          </tempalte>
          <tempalte v-else-if="record.absEndurance>=100&&record.absEndurance<=104">
            {{ record.absEndurances = 6 }}
          </tempalte>
          <tempalte v-else-if="record.absEndurance>=90&&record.absEndurance<=99" style="color: red">
            {{ record.absEndurances = 5 }}
          </tempalte>
          <tempalte v-else-if="record.absEndurance>=80&&record.absEndurance<=89" style="color: red">
            {{ record.absEndurances = 4 }}
          </tempalte>
          <tempalte v-else-if="record.absEndurance>=70&&record.absEndurance<=79" style="color: red">
            {{ record.absEndurances = 3 }}
          </tempalte>
          <tempalte v-else-if="record.absEndurance>=60&&record.absEndurance<=69" style="color: red">
            {{ record.absEndurances = 2 }}
          </tempalte>
          <tempalte v-else-if="record.absEndurance>=30&&record.absEndurance<=59" style="color: red">
            {{ record.absEndurances = 1 }}
          </tempalte>
          <tempalte v-else-if="record.absEndurance<30" style="color: red">
            {{ record.absEndurances = 0 }}
          </tempalte>
        </span>
        <span slot="dorsalMuscleEndurance" slot-scope="text, record">
          <tempalte v-if="record.dorsalMuscleEndurance>=120" style="color: #00DB00">
            {{ record.dorsalMuscleEndurances = 10 }}
          </tempalte>
          <tempalte v-else-if="record.dorsalMuscleEndurance>=115&&record.dorsalMuscleEndurance<=119">
            {{ record.dorsalMuscleEndurances = 9 }}
          </tempalte>
          <tempalte v-else-if="record.dorsalMuscleEndurance>=110&&record.dorsalMuscleEndurance<=114">
            {{ record.dorsalMuscleEndurances = 8 }}
          </tempalte>
          <tempalte v-else-if="record.dorsalMuscleEndurance>=105&&record.dorsalMuscleEndurance<=109">
            {{ record.dorsalMuscleEndurances = 7 }}
          </tempalte>
          <tempalte v-else-if="record.dorsalMuscleEndurance>=100&&record.dorsalMuscleEndurance<=104">
            {{ record.dorsalMuscleEndurances = 6 }}
          </tempalte>
          <tempalte v-else-if="record.dorsalMuscleEndurance>=90&&record.dorsalMuscleEndurance<=99" style="color: red">
            {{ record.dorsalMuscleEndurances = 5 }}
          </tempalte>
          <tempalte v-else-if="record.dorsalMuscleEndurance>=80&&record.dorsalMuscleEndurance<=89" style="color: red">
            {{ record.dorsalMuscleEndurances = 4 }}
          </tempalte>
          <tempalte v-else-if="record.dorsalMuscleEndurance>=70&&record.dorsalMuscleEndurance<=79" style="color: red">
            {{ record.dorsalMuscleEndurances = 3 }}
          </tempalte>
          <tempalte v-else-if="record.dorsalMuscleEndurance>=60&&record.dorsalMuscleEndurance<=69" style="color: red">
            {{ record.dorsalMuscleEndurances = 2 }}
          </tempalte>
          <tempalte v-else-if="record.dorsalMuscleEndurance>=30&&record.dorsalMuscleEndurance<=59" style="color: red">
            {{ record.dorsalMuscleEndurances = 1 }}
          </tempalte>
          <tempalte v-else-if="record.dorsalMuscleEndurance<30" style="color: red">
            {{ record.dorsalMuscleEndurances = 0 }}
          </tempalte>
        </span>
        <span slot="meter" slot-scope="text, record">
          <template v-if="record.sex===1">
            <template v-if="record.meter<=1100" style="color: #00DB00">
                {{ record.meters = 10 }}
            </template>
            <template v-else-if="record.meter>=1101&&record.meter<=1130">
                {{ record.meters = 9 }}
            </template>
            <template v-else-if="record.meter>=1131&&record.meter<=1200">
                {{ record.meters = 8 }}
            </template>
            <template v-else-if="record.meter>=1201&&record.meter<=1230">
                {{ record.meters = 6 }}
            </template>
            <template v-else-if="record.meter>=1231&&record.meter<=1300" style="color: red">
                {{ record.meters = 4 }}
            </template>
            <template v-else-if="record.meter>=1301&&record.meter<=1330" style="color: red">
                {{ record.meters = 2 }}
            </template>
            <template v-else-if="record.meter>=1331&&record.meter<=1400" style="color: red">
                {{ record.meters = 1 }}
            </template>
            <template v-else-if="record.meter>1400" style="color: red">
                {{ record.meters = 0 }}
            </template>
          </template>
          <template v-else-if="record.sex===0">
            <template v-if="record.meter<=1130" style="color: #00DB00">
                {{ record.meters = 10 }}
            </template>
            <template v-else-if="record.meter>=1131&&record.meter<=1200">
                {{ record.meters = 9 }}
            </template>
            <template v-else-if="record.meter>=1201&&record.meter<=1230">
                {{ record.meters = 8 }}
            </template>
            <template v-else-if="record.meter>=1231&&record.meter<=1300">
                {{ record.meters = 6 }}
            </template>
            <template v-else-if="record.meter>=1301&&record.meter<=1330" style="color: red">
                {{ record.meters = 4 }}
            </template>
            <template v-else-if="record.meter>=1331&&record.meter<=1400" style="color: red">
                {{ record.meters = 2 }}
            </template>
            <template v-else-if="record.meter>=1401&&record.meter<=1430" style="color: red">
                {{ record.meters = 1 }}
            </template>
            <template v-else-if="record.meter>1430" style="color: red">
                {{ record.meters = 0 }}
            </template>
          </template>
        </span>
        <span slot="score" slot-scope="text,record">
          <template>
            {{
              record.bm1s + record.sittingForwards + record.sprints +
              record.verticalJumps + record.squatRelativeStrengths + record.benchPressRelativeStrengths +
              record.pullUpss + record.absEndurances + record.dorsalMuscleEndurances + record.meters
            }}
          </template>
        </span>
      </a-table>
    </div>

    <basicPhysicalTestScore-modal ref="modalForm" @ok="modalFormOk" :mainId="mainId"></basicPhysicalTestScore-modal>
  </a-card>
</template>

<script>

import { JeecgListMixin } from '@/mixins/JeecgListMixin'
import BasicPhysicalTestScoreModal from './modules/BasicPhysicalTestScoreModal'

export default {
  counter: '0',
  name: 'BasicPhysicalTestScoreList',
  mixins: [JeecgListMixin],
  components: { BasicPhysicalTestScoreModal },
  props: {
    mainId: {
      type: String,
      default: '',
      required: false
    },
  },
  watch: {
    mainId: {
      immediate: true,
      handler(val) {
        if (!this.mainId) {
          this.clearList()
        } else {
          this.queryParam['sportsmanId'] = val
          this.loadData(1)
        }
      }
    }
  },
  data() {
    return {
      description: '人员表管理页面',
      disableMixinCreated: true,
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
          title: '测试时间',
          align: 'center',
          dataIndex: 'testDate',
        },
        {
          title: 'BM1',
          align: 'center',
          dataIndex: 'bm1',
          scopedSlots: { customRender: 'bm1' },
        },
        {
          title: '坐位体前屈(cm)',
          align: 'center',
          dataIndex: 'sittingForward',
          scopedSlots: { customRender: 'sittingForward' },
        },
        {
          title: '30米冲刺(s)',
          align: 'center',
          dataIndex: 'sprint',
          scopedSlots: { customRender: 'sprint' },
        },
        {
          title: '垂直纵跳(cm)',
          align: 'center',
          dataIndex: 'verticalJump',
          scopedSlots: { customRender: 'verticalJump' },
        },
        {
          title: '深蹲相对力量',
          align: 'center',
          dataIndex: 'squatRelativeStrength',
          scopedSlots: { customRender: 'squatRelativeStrength' },
        },
        {
          title: '卧推相对力量',
          align: 'center',
          dataIndex: 'benchPressRelativeStrength',
          scopedSlots: { customRender: 'benchPressRelativeStrength' },
        },
        {
          title: '引体向上(次)',
          align: 'center',
          dataIndex: 'pullUps',
          scopedSlots: { customRender: 'pullUps' },
        },
        {
          title: '腹肌耐力(s)',
          align: 'center',
          dataIndex: 'absEndurance',
          scopedSlots: { customRender: 'absEndurance' },
        },
        {
          title: '背肌耐力(s)',
          align: 'center',
          dataIndex: 'dorsalMuscleEndurance',
          scopedSlots: { customRender: 'dorsalMuscleEndurance' },
        },
        {
          title: '3000m(分:秒)',
          align: 'center',
          dataIndex: 'meter',
          scopedSlots: { customRender: 'meter' },
        },
        {
          title: '得分',
          align: 'center',
          scopedSlots: { customRender: 'score' },
        },
        {
          title: '操作',
          dataIndex: 'action',
          align: 'center',
          scopedSlots: { customRender: 'action' },
        }
      ],
      url: {
        list: '/physicaltest/sportsmanTest/listBasicPhysicalTestScoreByMainId',
        delete: '/physicaltest/sportsmanTest/deleteBasicPhysicalTestScore',
        deleteBatch: '/physicaltest/sportsmanTest/deleteBatchBasicPhysicalTestScore',
        exportXlsUrl: '/physicaltest/sportsmanTest/exportBasicPhysicalTestScore',
        importUrl: '/physicaltest/sportsmanTest/importBasicPhysicalTestScore',
      },
      dictOptions: {},

    }
  },
  computed: {
    importExcelUrl() {
      return `${window._CONFIG['domianURL']}/${this.url.importUrl}/${this.mainId}`
    },
    sumMoney() {
      return this.order.prods.map(
        row => row.bm1 + row.counter).reduce(
        (acc, cur) => (parseFloat(cur) + acc), 0)
    }
  },
  methods: {
    clearList() {
      this.dataSource = []
      this.selectedRowKeys = []
      this.ipagination.current = 1
    },

  }
}
</script>
<style scoped>
@import '~@assets/less/common.less'
</style>
