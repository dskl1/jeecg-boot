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

        <a-form-item label="训练日期" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <j-date v-decorator="['trainingDate']" :trigger-change="true" style="width: 30%"/>
        </a-form-item>
        <a-form-item label="时间段" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <j-dict-select-tag style="width: 30%" v-decorator="['period']" :triggerChange="true"
                             dictCode="period"></j-dict-select-tag>
        </a-form-item>
        <a-form-item label="训练小组" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <j-dict-select-tag style="width: 30%" v-decorator="['trainingGroupId']"
                             :triggerChange="true" dictCode="swim_event"></j-dict-select-tag>
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="训练项目"
          hasFeedback>
          <j-dict-select-tag style="width: 30%" v-decorator="['trainingItem', {}]" :triggerChange="true"
                             dictCode="swim_event"/>
        </a-form-item>

        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="训练小组"
          hasFeedback>
          <a-input style="width: 30%" v-decorator="['trainingGroupId', {}]"/>
        </a-form-item>

        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="训练序号"
          hasFeedback>
          <a-input style="width: 30%" v-decorator="['trainingItemNo', {}]"/>
        </a-form-item>

        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="组数"
          hasFeedback>
          <a-input style="width: 30%" v-decorator="['groupNo', {}]"/>
        </a-form-item>

        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="教练"
          hasFeedback>
          <a-input style="width: 30%" v-decorator="['coachName', {}]"/>
        </a-form-item>

        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="是否高原"
          hasFeedback>
          <j-dict-select-tag
            style="width:30%"
            v-decorator="['isHighland',{'initialValue':'否'}]"
            :triggerChange="true"
            dictCode="ishighland"/>
        </a-form-item>

        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="趟数"
          hasFeedback>
          <a-input style="width: 30%" v-decorator="['tangShu', {}]"/>
        </a-form-item>

        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="包干时间"
          hasFeedback>
          <a-input style="width: 30%" v-decorator="['baoGan', {}]"/>
        </a-form-item>

        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="辅助道具"
          hasFeedback>
          <j-dict-select-tag style="width: 30%" v-decorator="['supportItem', {}]" :triggerChange="true"
                             dictCode="support_item"/>
        </a-form-item>

      </a-form>
    </a-spin>
  </j-modal>
</template>

<script>

  import { httpAction } from '@/api/manage'
  import pick from 'lodash.pick'
  import { validateDuplicateValue } from '@/utils/util'
  import JDate from '@/components/jeecg/JDate'

  export default {
    name: 'D150Modal',
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
          sm: { span: 5 },
        },
        wrapperCol: {
          xs: { span: 24 },
          sm: { span: 16 },
        },

        confirmLoading: false,
        validatorRules: {},
        url: {
          add: '/training/trainingScore/addD150',
          edit: '/training/trainingScore/editD150',
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
          this.form.setFieldsValue(pick(this.model, 'sportsmanId', 'trainingDate', 'period', 'trainingGroupId', 'trainingItemNo', 'trainingItem', 'groupNo', 'coachName', 'isHighland', 'baoGan', 'tangShu', 'supportItem', 'createBy', 'createTime', 'updateBy', 'updateTime', 'sysOrgCode'))
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
        this.form.setFieldsValue(pick(row, 'sportsmanId', 'trainingDate', 'period', 'trainingGroupId', 'trainingItemNo', 'trainingItem', 'groupNo', 'coachName', 'isHighland', 'baoGan', 'tangShu', 'supportItem', 'createBy', 'createTime', 'updateBy', 'updateTime', 'sysOrgCode'))
      },

    }
  }
</script>
