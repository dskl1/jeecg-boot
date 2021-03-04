<template>
  <j-modal
    :title="title"
    :width="width"
    :visible="visible"
    :confirmLoading="confirmLoading"
    switchFullscreen
    @ok="handleOk"
    @cancel="handleCancel"
    cancelText="关闭">
    <a-spin :spinning="confirmLoading">
      <a-form :form="form">

        <a-row>
          <a-col :span="12">
            <a-form-item label="测试时间" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-date-picker v-decorator="['testDate']" placeholder="测试时间" style="width: 100%"/>
            </a-form-item>
          </a-col>
          <a-col :span="12">
            <a-form-item label="BM1" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input-number v-decorator="['bm1']" placeholder="请输入BM1" style="width: 100%"/>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="12">
            <a-form-item label="坐位体前屈(cm)" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input-number v-decorator="['sittingForward']" placeholder="请输入坐位体前屈(cm)" style="width: 100%"/>
            </a-form-item>
          </a-col>
          <a-col :span="12">
            <a-form-item label="30米冲刺(s)" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input-number v-decorator="['sprint']" placeholder="请输入30米冲刺(s)" style="width: 100%"/>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="12">
            <a-form-item label="垂直纵跳(cm)" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input-number v-decorator="['verticalJump']" placeholder="请输入垂直纵跳(cm)" style="width: 100%"/>
            </a-form-item>
          </a-col>
          <a-col :span="12">
            <a-form-item label="深蹲相对力量" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input-number v-decorator="['squatRelativeStrength']" placeholder="请输入深蹲相对力量" style="width: 100%"/>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="12">
            <a-form-item label="卧推相对力量" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input-number v-decorator="['benchPressRelativeStrength']" placeholder="请输入卧推相对力量" style="width: 100%"/>
            </a-form-item>
          </a-col>
          <a-col :span="12">
            <a-form-item label="引体向上(次)" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input-number v-decorator="['pullUps']" placeholder="请输入引体向上(次)" style="width: 100%"/>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="12">
            <a-form-item label="腹肌耐力(s)" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input-number v-decorator="['absEndurance']" placeholder="请输入腹肌耐力(s)" style="width: 100%"/>
            </a-form-item>
          </a-col>
          <a-col :span="12">
            <a-form-item label="背肌耐力(s)" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input-number v-decorator="['dorsalMuscleEndurance']" placeholder="请输入背肌耐力(s)" style="width: 100%"/>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="12">
            <a-form-item label="3000m(分:秒)" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input-number v-decorator="['meter']" placeholder="请输入3000m(分:秒)"
                              :formatter="meter => `${meter}`.replace(/\B(?=(\d{2})+(?!\d))/g, ':')"
                              :parser="meter => meter.replace(/\$\s?|(:*)/g, '')" style="width: 100%"/>
            </a-form-item>
          </a-col>
        </a-row>
      </a-form>
    </a-spin>
  </j-modal>
</template>

<script>

  import { httpAction } from '@/api/manage'
  import pick from 'lodash.pick'
  import { validateDuplicateValue } from '@/utils/util'
  import JDate from '@/components/jeecg/JDate'
  import moment from 'moment'

  export default {
    name: 'BasicPhysicalTestScoreModal',
    components: {
      JDate,
    },
    props: {
      mainId: {
        type: String,
        required: false,
        default: ''
      }
    },
    data () {
      return {
        form: this.$form.createForm(this),
        title: '操作',
        width: 800,
        visible: false,
        model: {},
        labelCol: {
          xs: { span: 24 },
          sm: { span: 8 },
        },
        wrapperCol: {
          xs: { span: 24 },
          sm: { span: 12 },
        },

        confirmLoading: false,
        validatorRules: {},
        url: {
          add: '/physicaltest/sportsmanTest/addBasicPhysicalTestScore',
          edit: '/physicaltest/sportsmanTest/editBasicPhysicalTestScore',
        }

      }
    },
    created () {
    },
    methods: {
      add () {
        this.edit({})
      },
      edit (record) {
        this.form.resetFields()
        this.model = Object.assign({}, record)
        this.visible = true
        this.$nextTick(() => {
          this.form.setFieldsValue(pick(this.model, 'sportsmanId', 'testDate', 'bm1', 'sex', 'sittingForward', 'sprint', 'verticalJump', 'squatRelativeStrength', 'benchPressRelativeStrength', 'pullUps', 'absEndurance', 'dorsalMuscleEndurance', 'meter', 'counter', 'bm1s', 'sexs', 'sittingForwards', 'sprints', 'verticalJumps', 'squatRelativeStrengths', 'benchPressRelativeStrengths', 'pullUpss', 'absEndurances', 'dorsalMuscleEndurances', 'meters', 'counters', 'createBy', 'createTime', 'updateBy', 'updateTime', 'sysOrgCode'))
          this.form.setFieldsValue({ testDate: this.model.testDate ? moment(this.model.testDate) : null })
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
            formData.testDate = formData.testDate ? formData.testDate.format() : null
            formData['sportsmanId'] = this.mainId
            console.log('表单提交数据', formData)
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
      },
      popupCallback (row) {
        this.form.setFieldsValue(pick(row, 'sportsmanId', 'testDate', 'bm1', 'sex', 'sittingForward', 'sprint', 'verticalJump', 'squatRelativeStrength', 'benchPressRelativeStrength', 'pullUps', 'absEndurance', 'dorsalMuscleEndurance', 'meter', 'counter', 'bm1s', 'sexs', 'sittingForwards', 'sprints', 'verticalJumps', 'squatRelativeStrengths', 'benchPressRelativeStrengths', 'pullUpss', 'absEndurances', 'dorsalMuscleEndurances', 'meters', 'counters', 'createBy', 'createTime', 'updateBy', 'updateTime', 'sysOrgCode'))

      },

    }
  }
</script>
