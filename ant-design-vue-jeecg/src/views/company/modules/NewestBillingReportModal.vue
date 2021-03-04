<template>
  <a-modal
    :title="title"
    :width="800"
    :visible="visible"
    :okButtonProps="{ props: {disabled: disableSubmit} }"
    :confirmLoading="confirmLoading"
    @ok="handleOk"
    @cancel="handleCancel"
    cancelText="关闭">
    <a-spin :spinning="confirmLoading">
      <a-form :form="form">

        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="运动员id"
          :hidden="hiding"
          hasFeedback>
          <a-input placeholder="请输入运动员id" v-decorator="['sportsmanId', {'initialValue':this.mainId}]"/>
        </a-form-item>

        <a-row>
          <a-col :span="12">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="开票编号"
              hasFeedback>
              <a-input v-decorator="[ 'invoiceId', {}]" style="width: 100%"/>
            </a-form-item>
          </a-col>
          <a-col :span="12">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="开票时间"
              hasFeedback>
              <a-date-picker v-decorator="[ 'invoiceTime', {}]" style="width: 100%"/>
            </a-form-item>
          </a-col>
          <a-col :span="12">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="(客户方)结算月份"
              hasFeedback>
              <a-input v-decorator="[ 'billingMonth', {}]" style="width: 100%"/>
            </a-form-item>
          </a-col>
          <a-col :span="12">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="开票金额"
              hasFeedback>
              <a-input-number v-decorator="[ 'billingPrice', {}]" style="width: 100%"/>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="12">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="回款日期"
              hasFeedback>
              <a-date-picker v-decorator="[ 'paymentMonth', {}]" style="width: 100%"/>
            </a-form-item>
          </a-col>
          <a-col :span="12">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="是否分包"
              hasFeedback>
              <a-input v-decorator="[ 'isSubcontract', {}]" style="width: 100%"/>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="12">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="分包单价"
              hasFeedback>
              <a-input-number v-decorator="[ 'subcontractPrice', {}]" style="width: 100%"/>
            </a-form-item>
          </a-col>
          <a-col :span="12">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="分包公司"
              hasFeedback>
              <a-input placeholder="请输入分包公司" v-decorator="['subcontractingCompany', {}]" style="width: 100%"/>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="12">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="开票日期(分包公司)"
              hasFeedback>
              <a-date-picker v-decorator="[ 'invoicingDay', {}]" style="width: 100%"/>
            </a-form-item>
          </a-col>
          <a-col :span="12">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="结算月份(分包公司)"
              hasFeedback>
              <a-input placeholder="请输入结算月份(分包公司)" v-decorator="['isInvoicing', {}]" style="width: 100%"/>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="12">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="开票金额(分包公司)"
              hasFeedback>
              <a-input-number v-decorator="[ 'payment', {}]" style="width: 100%"/>
            </a-form-item>
          </a-col>
          <a-col :span="12">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="回款日期(分包公司)"
              hasFeedback>
              <a-date-picker v-decorator="[ 'billingDate', {}]" style="width: 100%"/>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="12">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="备注"
              hasFeedback>
              <a-input placeholder="请输入备注" v-decorator="['note', {}]" style="width: 100%"/>
            </a-form-item>
          </a-col>
          <a-col :span="12">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="(分包公司)开票编号"
              hasFeedback>
              <a-input placeholder="请输入(分包公司)开票编号" v-decorator="['billingId', {}]" style="width: 100%"/>
            </a-form-item>
          </a-col>
        </a-row>
      </a-form>
    </a-spin>
  </a-modal>
</template>

<script>
import { httpAction } from '@/api/manage'
import pick from 'lodash.pick'
import moment from 'moment'
import JDate from '@/components/jeecg/JDate'

export default {
  components: {
    JDate
  },
  name: 'NewestBillingReportModal',
  data() {
    return {
      title: '操作',
      visible: false,
      model: {},
      labelCol: {
        xs: { span: 24 },
        sm: { span: 9 }
      },
      wrapperCol: {
        xs: { span: 24 },
        sm: { span: 12 }
      },
      moment,
      format: 'YYYY-MM-DD HH:mm:ss',
      disableSubmit: false,
      mainId: '',
      hiding: false,
      confirmLoading: false,
      form: this.$form.createForm(this),
      validatorRules: {},
      url: {
        add: '/company/newestEmployee/addNewestBillingReport',
        edit: '/company/newestEmployee/editNewestBillingReport'
      }
    }
  },
  created() {
  },
  methods: {
    add(mainId) {
      if (mainId) {
        this.edit({ mainId }, '')
      } else {
        this.$message.warning('请选择一条数据')
      }
    },
    detail(record) {
      this.edit(record, 'd')
    },
    edit(record, v) {
      if (v == 'e') {
        this.hiding = false
        this.disableSubmit = false
      } else if (v == 'd') {
        this.hiding = false
        this.disableSubmit = true
      } else {
        this.hiding = true
        this.disableSubmit = false
      }
      this.form.resetFields()
      this.mainId = record.mainId
      this.model = Object.assign({}, record)
      this.visible = true
      this.$nextTick(() => {
        this.form.setFieldsValue(pick(this.model, 'sportsmanId', 'job', 'invoiceId', 'invoiceTime', 'enterTime', 'awayTime', 'client', 'billingMonth', 'billingPrice', 'paymentMonth', 'isSubcontract', 'subcontractPrice', 'subcontractingCompany', 'billingId', 'invoicingDay', 'isInvoicing', 'payment', 'billingDate', 'note', 'sysOrgCode',))
        // 时间格式化
        this.form.setFieldsValue({ invoiceTime: this.model.invoiceTime ? moment(this.model.invoiceTime) : null })
        this.form.setFieldsValue({ enterTime: this.model.enterTime ? moment(this.model.enterTime) : null })
        this.form.setFieldsValue({ awayTime: this.model.awayTime ? moment(this.model.awayTime) : null })
        this.form.setFieldsValue({ paymentMonth: this.model.paymentMonth ? moment(this.model.paymentMonth) : null })
        this.form.setFieldsValue({ invoicingDay: this.model.invoicingDay ? moment(this.model.invoicingDay) : null })
        this.form.setFieldsValue({ billingDate: this.model.billingDate ? moment(this.model.billingDate) : null })
      })
    },
    close() {
      this.$emit('close')
      this.visible = false
    },
    handleOk() {
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
          formData.invoiceTime = formData.invoiceTime ? formData.invoiceTime.format() : null
          formData.enterTime = formData.enterTime ? formData.enterTime.format() : null
          formData.awayTime = formData.awayTime ? formData.awayTime.format() : null
          formData.paymentMonth = formData.paymentMonth ? formData.paymentMonth.format() : null
          formData.invoicingDay = formData.invoicingDay ? formData.invoicingDay.format() : null
          formData.billingDate = formData.billingDate ? formData.billingDate.format() : null
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
    handleCancel() {
      this.close()
    }
  }
}
</script>

<style scoped>

</style>
