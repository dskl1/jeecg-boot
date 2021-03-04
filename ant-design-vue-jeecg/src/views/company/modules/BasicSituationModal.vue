<template>
  <a-modal
    :title="title"
    :width="width"
    :visible="visible"
    :confirmLoading="confirmLoading"
    @ok="handleOk"
    @cancel="handleCancel"
    cancelText="关闭">
    <div class="no-print" style="text-align: right">
      <a-button v-print="'#printContent'" ghost type="primary">打印</a-button>
    </div>
    <a-spin :spinning="confirmLoading">
      <a-form id="printContent" :form="form">
        <a-form-item align="center" :labelCol="labelCol1" :wrapperCol="wrapperCol1"
                     style="font-size: 28px;font-weight: 800">
          <a-input style="width : 30%; border : 0px; font-size: 28px;font-weight: 800" labelCol="span:30"
                   :wrapperCol="wrapperCol" v-decorator="[ 'name', validatorRules.name]" placeholder="请输入姓名"></a-input>
          简历
        </a-form-item>
        <a-row align="center">
          <a-col :span="12" :gutter="2">
            <a-form-item label="职位" style="margin-left:100px;font-weight: 800" :labelCol="labelCol"
                         :wrapperCol="wrapperCol">
              <a-input style="width : 100%; border : 0px;" v-decorator="[ 'job', validatorRules.job]"
                       placeholder="请输入职位"></a-input>
            </a-form-item>
          </a-col>
          <a-col :span="12" :gutter="2">
            <a-form-item label="性别" style="font-weight: 800" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input style="width : 100%; border : 0px;" v-decorator="[ 'sex', validatorRules.sex]"
                       placeholder="请输入性别"></a-input>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row align="center">
          <a-form-item label="出生年月" style="font-weight: 800" :labelCol="labelCol" :wrapperCol="wrapperCol">
            <a-input placeholder="请选择出生年月" v-decorator="[ 'birthday', validatorRules.birthday]" :trigger-change="true"
                     style="width: 30%; border : 0px"/>
          </a-form-item>
          <a-form-item label="籍贯" style="font-weight: 800" :labelCol="labelCol" :wrapperCol="wrapperCol">
            <a-input style="width : 30%; border : 0px;" v-decorator="[ 'hometown', validatorRules.hometown]"
                     placeholder="请输入籍贯"></a-input>
          </a-form-item>
        </a-row>
        <a-form-item label="教育背景" style="font-weight: 800" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input style="width : 60%; border : 0px;"
                   v-decorator="[ 'educationBackground', validatorRules.educationBackground]"
                   placeholder="请输入教育背景"></a-input>
        </a-form-item>
        <a-form-item label="专业" style="font-weight: 800" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input style="width : 30%; border : 0px;" v-decorator="[ 'profession', validatorRules.profession]"
                   placeholder="请输入专业"></a-input>
        </a-form-item>
        <a-form-item label="工作经历" style="font-weight: 800" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <j-editor style="border : 0px;" autosize v-decorator="['workExperience', validatorRules.workExperience]"
                      rows="4" placeholder="请输入工作经历"/>
        </a-form-item>
        <a-form-item label="项目经验" style="font-weight: 800" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <j-editor style="border : 0px;" autosize
                      v-decorator="['projectExperience', validatorRules.projectExperience]" rows="4"
                      placeholder="请输入项目经验"/>
        </a-form-item>
<!--        <a-form-item label="自我评价" style="font-weight: 800" :labelCol="labelCol" :wrapperCol="wrapperCol">-->
<!--          <j-editor style="border : 0px;" autosize v-decorator="['selfEditorEvaluation', {}]"-->
<!--                    rows="4" placeholder="请输入自我评价"/>-->
<!--        </a-form-item>-->
          <a-form-item
            style="font-weight: 800"
            :labelCol="labelCol"
            :wrapperCol="wrapperCol"
            label="模板内容">
            <j-editor  v-model="selfEditorEvaluation"></j-editor>
          </a-form-item>

      </a-form>
    </a-spin>
  </a-modal>
</template>

<script>

  import { httpAction } from '@/api/manage'
  import pick from 'lodash.pick'
  import JDate from '@/components/jeecg/JDate'
  import JDictSelectTag from '@/components/dict/JDictSelectTag'
  import JEditor from '@/components/jeecg/JEditor'

  export default {
    name: 'BasicSituationModal',
    components: {
      JDate,
      JDictSelectTag,
      JEditor
    },
    data () {
      return {
        form: this.$form.createForm(this),
        title: '操作',
        width: 800,
        visible: false,
        model: {},
        selfEditorEvaluation:'',
        labelCol: {
          xs: { span: 24 },
          sm: { span: 5 },
        },
        wrapperCol: {
          xs: { span: 24 },
          sm: { span: 16 },
        },

        confirmLoading: false,
        validatorRules: {
          job: {},
          sex: {},
          birthday: {},
          hometown: {},
          educationBackground: {},
          profession: {},
          workExperience: {},
          projectExperience: {},
          selfEvaluation: {},
        },
        url: {
          add: '/company/basicSituation/add',
          edit: '/company/basicSituation/edit',
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
        this.selfEditorEvaluation=record.selfEvaluation
        this.visible = true
        this.$nextTick(() => {
          this.form.setFieldsValue(pick(this.model, 'name', 'job', 'sex', 'birthday', 'hometown', 'educationBackground', 'profession', 'workExperience', 'projectExperience','selfEvaluation'))
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
            formData.selfEvaluation=this.selfEditorEvaluation
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
        this.form.setFieldsValue(pick(row, 'job', 'sex', 'birthday', 'hometown', 'educationBackground', 'profession', 'workExperience', 'projectExperience'))
      },

    }
  }
</script>