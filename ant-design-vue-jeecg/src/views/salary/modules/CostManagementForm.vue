<template>
  <a-spin :spinning="confirmLoading">
    <j-form-container :disabled="formDisabled">
      <a-form :form="form" slot="detail">
        <a-row>
          <a-col :span="12">
            <a-form-item label="开票额" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input-number v-decorator="['invoiceAmount']" placeholder="请输入开票额" style="width: 100%"/>
            </a-form-item>
          </a-col>
          <a-col :span="12">
            <a-form-item label="开票日期" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-date-picker v-decorator="['invoiceDate']" placeholder="请输入开票日期" style="width: 100%"/>
            </a-form-item>
          </a-col>
          <a-col :span="12">
            <a-form-item label="社保(公司)" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input-number v-decorator="['socialSecurityCo']" placeholder="请输入社保(公司)" style="width: 100%"/>
            </a-form-item>
          </a-col>
          <a-col :span="12">
            <a-form-item label="管理成本" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input-number v-decorator="['managementCost']" placeholder="请输入管理成本" style="width: 100%"/>
            </a-form-item>
          </a-col>
          <a-col :span="12">
            <a-form-item label="报销" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input-number v-decorator="['reimbursement']" placeholder="请输入报销" style="width: 100%"/>
            </a-form-item>
          </a-col>
          <a-col :span="12">
            <a-form-item label="其他" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input-number v-decorator="['otherThing']" placeholder="请输入其他" style="width: 100%"/>
            </a-form-item>
          </a-col>
          <a-col v-if="showFlowSubmitButton" :span="24" style="text-align: center">
            <a-button @click="submitForm">提 交</a-button>
          </a-col>
        </a-row>
      </a-form>
    </j-form-container>
  </a-spin>
</template>

<script>

import { httpAction, getAction } from '@/api/manage'
import pick from 'lodash.pick'
import { validateDuplicateValue } from '@/utils/util'
import JFormContainer from '@/components/jeecg/JFormContainer'

export default {
  name: 'CostManagementForm',
  components: {
    JFormContainer,
  },
  props: {
    //流程表单data
    formData: {
      type: Object,
      default: () => {
      },
      required: false
    },
    //表单模式：true流程表单 false普通表单
    formBpm: {
      type: Boolean,
      default: false,
      required: false
    },
    //表单禁用
    disabled: {
      type: Boolean,
      default: false,
      required: false
    }
  },
  data() {
    return {
      form: this.$form.createForm(this),
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
        add: '/salary/costManagement/add',
        edit: '/salary/costManagement/edit',
        queryById: '/salary/costManagement/queryById'
      }
    }
  },
  computed: {
    formDisabled() {
      if (this.formBpm === true) {
        if (this.formData.disabled === false) {
          return false
        }
        return true
      }
      return this.disabled
    },
    showFlowSubmitButton() {
      if (this.formBpm === true) {
        if (this.formData.disabled === false) {
          return true
        }
      }
      return false
    }
  },
  created() {
    //如果是流程中表单，则需要加载流程表单data
    this.showFlowData()
  },
  methods: {
    add() {
      this.edit({})
    },
    edit(record) {
      this.form.resetFields()
      this.model = Object.assign({}, record)
      this.visible = true
      this.$nextTick(() => {
        this.form.setFieldsValue(pick(this.model, 'name', 'invoiceDate', 'invoiceAmount', 'personIncome', 'preTaxIncome', 'socialSecurityCo', 'providentFund', 'capitalCost', 'managementCost', 'reimbursement', 'otherThing', 'profit', 'createBy', 'createTime', 'updateBy', 'updateTime', 'sysOrgCode'))
      })
    },
    //渲染流程表单数据
    showFlowData() {
      if (this.formBpm === true) {
        let params = { id: this.formData.dataId }
        getAction(this.url.queryById, params).then((res) => {
          if (res.success) {
            this.edit(res.result)
          }
        })
      }
    },
    submitForm() {
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
          })
        }

      })
    },
    popupCallback(row) {
      this.form.setFieldsValue(pick(row, 'name', 'invoiceDate', 'invoiceAmount', 'personIncome', 'preTaxIncome', 'socialSecurityCo', 'providentFund', 'capitalCost', 'managementCost', 'reimbursement', 'otherThing', 'profit', 'createBy', 'createTime', 'updateBy', 'updateTime', 'sysOrgCode'))
    },
  }
}
</script>