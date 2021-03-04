<template>
  <a-modal
    :title="title"
    :width="1000"
    :visible="visible"
    :confirmLoading="confirmLoading"
    @ok="handleOk"
    @cancel="handleCancel">
    <a-spin :spinning="confirmLoading">
      <!-- 主表单区域 -->
      <a-form :form="form">
        <a-row>
          <a-col :span="12">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="姓名">
              <a-input placeholder="请输入姓名" v-decorator="['name', {}]" style="width: 100%"/>
            </a-form-item>
          </a-col>
          <a-col :span="12">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="运动项目">
              <a-input placeholder="请输入运动项目" v-decorator="['sportItem', {}]" style="width: 100%"/>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="12">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="父亲身高(CM)">
              <a-input-number v-decorator="[ 'fatherHeight', {}]" style="width: 100%"/>
            </a-form-item>
          </a-col>
          <a-col :span="12">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="母亲身高(CM)">
              <a-input-number v-decorator="[ 'motherHeight', {}]" style="width: 100%"/>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="12">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="民族">
              <a-input placeholder="请输入民族" v-decorator="['nation', {}]" style="width: 100%"/>
            </a-form-item>
          </a-col>
          <a-col :span="12">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="籍贯">
              <a-input placeholder="请输入籍贯" v-decorator="['nativePlace', {}]" style="width: 100%"/>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="12">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="出生地">
              <a-input placeholder="请输入出生地" v-decorator="['birthplace', {}]" style="width: 100%"/>
            </a-form-item>
          </a-col>
          <a-col :span="12">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="文化程度">
              <a-input placeholder="请输入文化程度" v-decorator="['educationalLevel', {}]" style="width: 100%"/>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="12">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="政治面貌">
              <a-input placeholder="请输入政治面貌" v-decorator="['politicsStatus', {}]" style="width: 100%"/>
            </a-form-item>
          </a-col>
          <a-col :span="12">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="原学校">
              <a-input placeholder="请输入原学校" v-decorator="['formerSchool', {}]" style="width: 100%"/>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="12">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="原教练">
              <a-input placeholder="请输入原教练" v-decorator="['formerCoach', {}]" style="width: 100%"/>
            </a-form-item>
          </a-col>
          <a-col :span="12">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="入队(校)时间">
              <a-date-picker v-decorator="[ 'entryTeamDate', {}]" style="width: 100%"/>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="12">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="开始训练时间">
              <a-date-picker v-decorator="[ 'attendTrainTime', {}]" style="width: 100%"/>
            </a-form-item>
          </a-col>
          <a-col :span="12">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="入队前专项">
              <a-input placeholder="请输入入队前专项" v-decorator="['formerSportItem', {}]" style="width: 100%"/>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="12">
            <a-form-item

              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="入队后拟选专项">
              <a-input placeholder="请输入入队后拟选专项" v-decorator="['afterSportItem', {}]"
                       style="width: 100%"/>
            </a-form-item>
          </a-col>
          <a-col :span="12">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="有何伤病史">
              <a-input placeholder="请输入有何伤病史" v-decorator="['injuryHistory', {}]" style="width: 100%"/>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="12">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="直系亲属中有无突出身高者">
              <a-input placeholder="请输入直系亲属中有无突出身高者" v-decorator="['linealRelativeHeightProminent', {}]"
                       style="width: 100%"/>
            </a-form-item>
          </a-col>
          <a-col :span="12">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="直系亲属中有无遗传疾病">
              <a-input placeholder="请输入直系亲属中有无遗传疾病" v-decorator="['linealRelativeGeneticDiseases', {}]"
                       style="width: 100%"/>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="12">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="身份证号码">
              <a-input placeholder="请输入身份证号码" v-decorator="['cardNumber', {}]" style="width: 100%"/>
            </a-form-item>
          </a-col>
          <a-col :span="12">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="状态">
              <a-radio-group default-value="在役" v-decorator="['retire', {}]">
                <a-radio value="退队">退队</a-radio>
              </a-radio-group>
              <a-date-picker v-decorator="[ 'trainingDate', {}]" style="width: 100%"/>
            </a-form-item>
          </a-col>
        </a-row>

      </a-form>
    </a-spin>
  </a-modal>
</template>

<script>
  import { httpAction } from '@/api/manage'
  import JDate from '@/components/jeecg/JDate'
  import pick from 'lodash.pick'
  import moment from 'moment'

  export default {
    name: 'SportsmanPersonalArchivesModal',
    components: {
      JDate
    },
    data () {
      return {
        title: '操作',
        visible: false,
        model: {},
        labelCol: {
          xs: { span: 24 },
          sm: { span: 11 },
        },
        wrapperCol: {
          xs: { span: 24 },
          sm: { span: 11 },
        },
        confirmLoading: false,
        form: this.$form.createForm(this),
        validatorRules: {},
        url: {
          add: '/sportsman/sportsmanPersonalArchives/add',
          edit: '/sportsman/sportsmanPersonalArchives/edit',
          sportsmanRankList: '/sportsman/sportsmanPersonalArchives/querySportsmanRankByMainId',
          sportsmanResumeList: '/sportsman/sportsmanPersonalArchives/querySportsmanResumeByMainId',
          sportsmanFamilySituationList: '/sportsman/sportsmanPersonalArchives/querySportsmanFamilySituationByMainId',
          sportsmanDataList: '/sportsman/sportsmanPersonalArchives/querySportsmanDataByMainId',
          developSituationList: '/sportsman/sportsmanPersonalArchives/queryDevelopSituationByMainId',
          bodyTypeList: '/sportsman/sportsmanPersonalArchives/queryBodyTypeByMainId',
          bodyAbilityList: '/sportsman/sportsmanPersonalArchives/queryBodyAbilityByMainId',
        }
      }
    },
    methods: {
      add () {
        this.edit({})
      },
      edit (record) {
        this.form.resetFields()
        this.model = Object.assign({}, record)
        //初始化明细表数据
        console.log(this.model.id)
        this.visible = true
        this.$nextTick(() => {
          this.form.setFieldsValue(pick(this.model, 'afterSportItem', 'attendTrainTime', 'newTime', 'avatar', 'biology', 'birthday', 'birthplace', 'cardNumber', 'chemistry', 'coachName', 'distance', 'educationalLevel', 'english', 'entryTeamDate', 'fatherHeight', 'formerCoach', 'formerSchool', 'formerSportItem', 'geography', 'grade', 'groupNo', 'history', 'injuryHistory', 'language', 'linealRelativeGeneticDiseases', 'linealRelativeHeightProminent', 'math', 'motherHeight', 'name', 'nation', 'nativePlace', 'period', 'physics', 'politics', 'politicsStatus', 'retire', 'sex', 'sportItem', 'stage', 'supportItem', 'term', 'trainingDate', 'trainingDate1', 'trainingGroupId', 'trainingItem', 'trainingItemNo', 'trainingScore',))
          // 时间格式化
          this.form.setFieldsValue({ attendTrainTime: this.model.attendTrainTime ? moment(this.model.attendTrainTime) : null })
          this.form.setFieldsValue({ birthday: this.model.birthday ? moment(this.model.birthday) : null })
          this.form.setFieldsValue({ entryTeamDate: this.model.entryTeamDate ? moment(this.model.entryTeamDate) : null })
          this.form.setFieldsValue({ trainingDate: this.model.trainingDate ? moment(this.model.trainingDate) : null })
        })
      },
      close () {
        this.$emit('close')
        this.visible = false
      },
      handleOk () {
        const that = this
        // 触发表单验证
        this.form.validateFields((err, values) => {
          if (!err) {
            that.confirmLoading = true
            let httpurl = ''
            let method = ''
            if (!this.model.id) {
              httpurl += this.url.add
              method = 'post'
            } else {
              httpurl += this.url.edit
              method = 'put'
            }
            let formData = Object.assign(this.model, values)
            //时间格式化
            formData.attendTrainTime = formData.attendTrainTime ? formData.attendTrainTime.format() : null
            formData.birthday = formData.birthday ? formData.birthday.format() : null
            formData.entryTeamDate = formData.entryTeamDate ? formData.entryTeamDate.format() : null
            formData.trainingDate = formData.trainingDate ? formData.trainingDate.format() : null
            httpAction(httpurl, formData, method).then((res) => {
              if (res.success) {
                that.$message.success(res.message)
                that.$emit('ok')
              } else {
                that.$message.warning(res.message)
              }
            }).finally(() => {
              that.confirmLoading = false
              that.close()
            })
          }
        })
      },
      handleCancel () {
        this.close()
      }
    }
  }
</script>

<style scoped>

</style>