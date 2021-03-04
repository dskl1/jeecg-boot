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
              label="岗位">
              <a-input placeholder="请输入岗位" v-decorator="['job', {}]" style="width: 100%"/>
            </a-form-item>
          </a-col>
          <a-col :span="12">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="入职时间">
              <a-date-picker v-decorator="[ 'entryTime', {}]" style="width: 100%"/>
            </a-form-item>
          </a-col>
          <a-col :span="12">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="入场时间">
              <a-date-picker v-decorator="[ 'enterTime', {}]" style="width: 100%"/>
            </a-form-item>
          </a-col>
          <a-col :span="12">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="级别">
              <a-input v-decorator="[ 'level', {}]" style="width: 100%"/>
            </a-form-item>
          </a-col>
          <a-col :span="12">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="薪资">
              <a-input-number v-decorator="[ 'salary', {}]" style="width: 100%"/>
            </a-form-item>
          </a-col>
          <a-col :span="12">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="单价">
              <a-input-number v-decorator="[ 'price', {}]" style="width: 100%"/>
            </a-form-item>
          </a-col>
          <a-col :span="12">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="调薪">
              <a-input-number v-decorator="[ 'raiseSalary', {}]" style="width: 100%"/>
            </a-form-item>
          </a-col>
          <a-col :span="12">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="调薪日期">
              <a-date-picker v-decorator="[ 'raiseDate', {}]" style="width: 100%"/>
            </a-form-item>
          </a-col>
          <a-col :span="12">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="客户方">
              <a-input placeholder="请输入客户方" v-decorator="['client', {}]" style="width: 100%"/>
            </a-form-item>
          </a-col>
          <a-col :span="12">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="离场时间">
              <a-date-picker v-decorator="[ 'awayTime', {}]" style="width: 100%"/>
            </a-form-item>
          </a-col>
          <a-col :span="12">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="银行名称">
              <a-input placeholder="请输入银行名称" v-decorator="['bankName', {}]" style="width: 100%"/>
            </a-form-item>
          </a-col>
          <a-col :span="12">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="银行卡号">
              <a-input placeholder="请输入银行卡号" v-decorator="['bankCard', {}]" style="width: 100%"/>
            </a-form-item>
          </a-col>
          <a-col :span="12">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="状态">
              <a-input placeholder="请输入状态" v-decorator="['status', {}]" style="width: 100%"/>
            </a-form-item>
          </a-col>
          <a-col :span="6">
            <a-form-item
              :labelCol="labelCol1"
              :wrapperCol="wrapperCol1"
              label="是否租用电脑"
              hasFeedback>
              <a-radio-group v-decorator="['isRentalComputer', {}]">
                <a-radio value="是">是</a-radio>
                <a-radio value="否">否</a-radio>
              </a-radio-group>
            </a-form-item>
          </a-col>
          <a-col :span="6">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              v-if="value === '是'"
              hasFeedback>
              <a-date-picker v-if="value === '是'" placeholder="租用日期"
                             v-decorator="[ 'rentalDate', {}]" style="width: 100%"/>
            </a-form-item>
          </a-col>
          <a-col :span="6">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              hasFeedback>
              <a-input v-if="value === '是'" placeholder="订单号" v-decorator="['orderNumber', {}]" style="width: 100%"/>
            </a-form-item>
          </a-col>
          <a-col :span="6">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              hasFeedback>
              <a-input-number v-if="value === '是'" placeholder="费用" v-decorator="[ 'cost', {}]" style="width: 100%"/>
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
  name: 'EmployeeModal',
  components: {
    JDate
  },
  data() {
    return {
      title: '操作',
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
      labelCol1: {
        xs: { span: 24 },
        sm: { span: 10 },
      },
      wrapperCol1: {
        xs: { span: 24 },
        sm: { span: 12 },
      },
      confirmLoading: false,
      form: this.$form.createForm(this),
      validatorRules: {},
      url: {
        add: '/company/employee/add',
        edit: '/company/employee/edit',
        billingReportList: '/company/employee/queryBillingReportByMainId',
      }
    }
  },
  methods: {
    add() {
      this.edit({})
    },
    edit(record) {
      this.form.resetFields()
      this.model = Object.assign({}, record)
      //初始化明细表数据
      console.log(this.model.id)
      this.visible = true
      this.$nextTick(() => {
        this.form.setFieldsValue(pick(this.model, 'name', 'job', 'level', 'salary', 'price', 'raiseSalary', 'raiseDate', 'entryTime', 'enterTime', 'awayTime', 'client', 'isRentalComputer', 'rentalDate', 'orderNumber', 'cost', 'bankName', 'bankCard', 'status', 'sysOrgCode'))
        // 时间格式化
        this.form.setFieldsValue({ entryTime: this.model.entryTime ? moment(this.model.entryTime) : null })
        this.form.setFieldsValue({ enterTime: this.model.enterTime ? moment(this.model.enterTime) : null })
        this.form.setFieldsValue({ awayTime: this.model.awayTime ? moment(this.model.awayTime) : null })
        this.form.setFieldsValue({ rentalDate: this.model.rentalDate ? moment(this.model.rentalDate) : null })
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
.ant-btn {
  padding: 0 10px;
  margin-left: 3px;
}

.ant-form-item-control {
  line-height: 0px;
}

/** 主表单行间距 */
.ant-form .ant-form-item {
  margin-bottom: 10px;
}

/** Tab页面行间距 */
.ant-tabs-content .ant-form-item {
  margin-bottom: 0px;
}
</style>