<template>
  <a-modal
    :title="title"
    :width="1200"
    :visible="visible"
    :maskClosable="false"
    :confirmLoading="confirmLoading"
    @ok="handleOk"
    @cancel="handleCancel">
    <a-spin :spinning="confirmLoading">
      <!-- 主表单区域 -->
      <a-form :form="form">
        <a-row>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="姓名">
              <a-input placeholder="请输入姓名" v-decorator="['name', {}]"/>
            </a-form-item>
          </a-col>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="性别">
              <a-input placeholder="请输入性别" v-decorator="['sex', {}]"/>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="照片">
              <a-input placeholder="请输入照片" v-decorator="['avatar', {}]"/>
            </a-form-item>
          </a-col>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="出生日期">
              <a-date-picker
                placeholder="请输入出生日期"
                style="width:100%"
                v-decorator="[ 'birthday', {}]"/>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="运动项目">
              <a-input placeholder="请输入运动项目" v-decorator="['sportItem', {}]"/>
            </a-form-item>
          </a-col>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="父亲身高(CM)">
              <a-input-number placeholder="请输入父亲身高(CM)" style="width:100%" v-decorator="[ 'fatherHeight', {}]"/>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="母亲身高(CM)">
              <a-input-number placeholder="请输入母亲身高(CM)" style="width:100%" v-decorator="[ 'motherHeight', {}]"/>
            </a-form-item>
          </a-col>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="直系亲属中有无突出身高者">
              <a-input placeholder="请输入直系亲属中有无突出身高者" v-decorator="['linealRelativeHeightProminent', {}]"/>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="直系亲属中有无遗传疾病">
              <a-input placeholder="请输入直系亲属中有无遗传疾病" v-decorator="['linealRelativeGeneticDiseases', {}]"/>
            </a-form-item>
          </a-col>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="年级">
              <a-input placeholder="请输入年级" v-decorator="['grade', {}]"/>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="学期">
              <a-input placeholder="请输入学期" v-decorator="['term', {}]"/>
            </a-form-item>
          </a-col>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="语文">
              <a-input-number placeholder="请输入语文" style="width:100%" v-decorator="[ 'language', {}]"/>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="数学">
              <a-input-number placeholder="请输入数学" style="width:100%" v-decorator="[ 'math', {}]"/>
            </a-form-item>
          </a-col>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="英语">
              <a-input-number placeholder="请输入英语" style="width:100%" v-decorator="[ 'english', {}]"/>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="生物">
              <a-input-number placeholder="请输入生物" style="width:100%" v-decorator="[ 'biology', {}]"/>
            </a-form-item>
          </a-col>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="地理">
              <a-input-number placeholder="请输入地理" style="width:100%" v-decorator="[ 'geography', {}]"/>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="化学">
              <a-input-number placeholder="请输入化学" style="width:100%" v-decorator="[ 'chemistry', {}]"/>
            </a-form-item>
          </a-col>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="物理">
              <a-input-number placeholder="请输入物理" style="width:100%" v-decorator="[ 'physics', {}]"/>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="政治">
              <a-input-number placeholder="请输入政治" style="width:100%" v-decorator="[ 'politics', {}]"/>
            </a-form-item>
          </a-col>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="历史">
              <a-input-number placeholder="请输入历史" style="width:100%" v-decorator="[ 'history', {}]"/>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="民族">
              <a-input placeholder="请输入民族" v-decorator="['nation', {}]"/>
            </a-form-item>
          </a-col>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="籍贯">
              <a-input placeholder="请输入籍贯" v-decorator="['nativePlace', {}]"/>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="出生地">
              <a-input placeholder="请输入出生地" v-decorator="['birthplace', {}]"/>
            </a-form-item>
          </a-col>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="文化程度">
              <a-input placeholder="请输入文化程度" v-decorator="['educationalLevel', {}]"/>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="政治面貌">
              <a-input placeholder="请输入政治面貌" v-decorator="['politicsStatus', {}]"/>
            </a-form-item>
          </a-col>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="原学校">
              <a-input placeholder="请输入原学校" v-decorator="['formerSchool', {}]"/>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="原教练">
              <a-input placeholder="请输入原教练" v-decorator="['formerCoach', {}]"/>
            </a-form-item>
          </a-col>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="入队(校)时间">
              <a-date-picker
                placeholder="请输入入队(校)时间"
                style="width:100%"
                v-decorator="[ 'entryTeamDate', {}]"/>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="开始训练时间">
              <a-date-picker
                placeholder="请输入开始训练时间"
                style="width:100%"
                v-decorator="[ 'attendTrainTime', {}]"/>
            </a-form-item>
          </a-col>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="入队前专项">
              <a-input placeholder="请输入入队前专项" v-decorator="['formerSportItem', {}]"/>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="入队后拟选专项">
              <a-input placeholder="请输入入队后拟选专项" v-decorator="['afterSportItem', {}]"/>
            </a-form-item>
          </a-col>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="有何伤病史">
              <a-input placeholder="请输入有何伤病史" v-decorator="['injuryHistory', {}]"/>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="身份证号码">
              <a-input placeholder="请输入身份证号码" v-decorator="['cardNumber', {}]"/>
            </a-form-item>
          </a-col>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="状态">
              <a-input placeholder="请输入状态" v-decorator="['retire', {}]"/>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="阶段">
              <a-input placeholder="请输入阶段" v-decorator="['stage', {}]"/>
            </a-form-item>
          </a-col>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="训练日期">
              <a-date-picker
                placeholder="请输入训练日期"
                style="width:100%"
                v-decorator="[ 'trainingDate', {}]"/>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="训练时间">
              <a-input placeholder="请输入训练时间" v-decorator="['trainingDate1', {}]"/>
            </a-form-item>
          </a-col>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="时间段">
              <a-input placeholder="请输入时间段" v-decorator="['period', {}]"/>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="训练小组">
              <a-input placeholder="请输入训练小组" v-decorator="['trainingGroupId', {}]"/>
            </a-form-item>
          </a-col>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="训练序号">
              <a-input placeholder="请输入训练序号" v-decorator="['trainingItemNo', {}]"/>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="训练项目">
              <a-input placeholder="请输入训练项目" v-decorator="['trainingItem', {}]"/>
            </a-form-item>
          </a-col>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="距离">
              <a-input placeholder="请输入距离" v-decorator="['distance', {}]"/>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="组数">
              <a-input placeholder="请输入组数" v-decorator="['groupNo', {}]"/>
            </a-form-item>
          </a-col>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="教练">
              <a-input placeholder="请输入教练" v-decorator="['coachName', {}]"/>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="成绩">
              <a-input placeholder="请输入成绩" v-decorator="['trainingScore', {}]"/>
            </a-form-item>
          </a-col>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="辅助道具">
              <a-input placeholder="请输入辅助道具" v-decorator="['supportItem', {}]"/>
            </a-form-item>
          </a-col>
        </a-row>
      </a-form>

      <!-- 子表单区域 -->
      <a-tabs v-model="activeKey" @change="handleChangeTabs">
        <a-tab-pane tab="学习成绩" :key="refKeys[0]" :forceRender="true">
          <j-editable-table
            :ref="refKeys[0]"
            :loading="studyPostTable.loading"
            :columns="studyPostTable.columns"
            :dataSource="studyPostTable.dataSource"
            :maxHeight="300"
            :rowNumber="true"
            :rowSelection="true"
            :actionButton="true"/>
        </a-tab-pane>
      </a-tabs>

    </a-spin>
  </a-modal>
</template>

<script>

  import moment from 'moment'
  import pick from 'lodash.pick'
  import { FormTypes } from '@/utils/JEditableTableUtil'
  import { JEditableTableOneToManyMixin } from '@/mixins/JEditableTableOneToManyMixin'

  export default {
    name: 'StudyScoreModal',
    mixins: [JEditableTableOneToManyMixin],
    data() {
      return {
        // 新增时子表默认添加几行空数据
        addDefaultRowNum: 1,
        validatorRules: {
        },
        refKeys: ['studyPost', ],
        activeKey: 'studyPost',
        // 学习成绩
        studyPostTable: {
          loading: false,
          dataSource: [],
          columns: [
            {
              title: '运动员id',
              key: 'sportsmanId',
              type: FormTypes.input,
              defaultValue: '',
              placeholder: '请输入${title}',
            },
            {
              title: '年级',
              key: 'grade',
              type: FormTypes.input,
              defaultValue: '',
              placeholder: '请输入${title}',
            },
            {
              title: '学期',
              key: 'term',
              type: FormTypes.input,
              defaultValue: '',
              placeholder: '请输入${title}',
            },
            {
              title: '语文',
              key: 'chinese',
              type: FormTypes.input,
              defaultValue: '',
              placeholder: '请输入${title}',
            },
            {
              title: '数学',
              key: 'math',
              type: FormTypes.input,
              defaultValue: '',
              placeholder: '请输入${title}',
            },
            {
              title: '英语',
              key: 'english',
              type: FormTypes.input,
              defaultValue: '',
              placeholder: '请输入${title}',
            },
            {
              title: '生物',
              key: 'biology',
              type: FormTypes.input,
              defaultValue: '',
              placeholder: '请输入${title}',
            },
            {
              title: '地理',
              key: 'geography',
              type: FormTypes.input,
              defaultValue: '',
              placeholder: '请输入${title}',
            },
            {
              title: '化学',
              key: 'chemistry',
              type: FormTypes.input,
              defaultValue: '',
              placeholder: '请输入${title}',
            },
            {
              title: '物理',
              key: 'physics',
              type: FormTypes.input,
              defaultValue: '',
              placeholder: '请输入${title}',
            },
            {
              title: '政治',
              key: 'politics',
              type: FormTypes.input,
              defaultValue: '',
              placeholder: '请输入${title}',
            },
            {
              title: '历史',
              key: 'history',
              type: FormTypes.input,
              defaultValue: '',
              placeholder: '请输入${title}',
            },
          ]
        },
        url: {
          add: "/sportsman/studyScore/add",
          edit: "/sportsman/studyScore/edit",
          studyPost: {
            list: '/sportsman/studyScore/queryStudyPostByMainId'
          },
        }
      }
    },
    methods: {

      /** 调用完edit()方法之后会自动调用此方法 */
      editAfter() {
        this.$nextTick(() => {
          this.form.setFieldsValue(pick(this.model, 'name', 'sex', 'avatar', 'birthday', 'sportItem', 'fatherHeight', 'motherHeight', 'linealRelativeHeightProminent', 'linealRelativeGeneticDiseases', 'grade', 'term', 'language', 'math', 'english', 'biology', 'geography', 'chemistry', 'physics', 'politics', 'history', 'nation', 'nativePlace', 'birthplace', 'educationalLevel', 'politicsStatus', 'formerSchool', 'formerCoach', 'entryTeamDate', 'attendTrainTime', 'formerSportItem', 'afterSportItem', 'injuryHistory', 'cardNumber', 'retire', 'stage', 'trainingDate', 'trainingDate1', 'period', 'trainingGroupId', 'trainingItemNo', 'trainingItem', 'distance', 'groupNo', 'coachName', 'trainingScore', 'supportItem', ))
          // 时间格式化
          this.form.setFieldsValue({ birthday: this.model.birthday ? moment(this.model.birthday) : null })
          this.form.setFieldsValue({ entryTeamDate: this.model.entryTeamDate ? moment(this.model.entryTeamDate) : null })
          this.form.setFieldsValue({ attendTrainTime: this.model.attendTrainTime ? moment(this.model.attendTrainTime) : null })
          this.form.setFieldsValue({ trainingDate: this.model.trainingDate ? moment(this.model.trainingDate) : null })
        })
        // 加载子表数据
        if (this.model.id) {
          let params = { id: this.model.id }
          this.requestSubTableData(this.url.studyPost.list, params, this.studyPostTable)
        }
      },

      /** 整理成formData */
      classifyIntoFormData(allValues) {
        let main = Object.assign(this.model, allValues.formValue)
        //时间格式化
        main.birthday = main.birthday ? main.birthday.format() : null;
        main.entryTeamDate = main.entryTeamDate ? main.entryTeamDate.format() : null;
        main.attendTrainTime = main.attendTrainTime ? main.attendTrainTime.format() : null;
        main.trainingDate = main.trainingDate ? main.trainingDate.format() : null;
        return {
          ...main, // 展开
          studyPostList: allValues.tablesValue[0].values,
        }
      }
    }
  }
</script>

<style scoped>
</style>
