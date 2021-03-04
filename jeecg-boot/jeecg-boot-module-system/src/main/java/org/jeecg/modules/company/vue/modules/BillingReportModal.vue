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
          <a-input placeholder="请输入运动员id" v-decorator="['sportsmanId', {'initialValue':this.mainId}]" />
        </a-form-item>

        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="岗位"
          hasFeedback>
          <a-input placeholder="请输入岗位" v-decorator="['job', {}]" />
        </a-form-item>

        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="单价"
          hasFeedback>
          <a-input-number v-decorator="[ 'price', {}]" />
        </a-form-item>

        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="入职时间"
          hasFeedback>
          <a-date-picker showTime format="YYYY-MM-DD HH:mm:ss" v-decorator="[ 'entryTime', {}]" />
        </a-form-item>

        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="入场时间"
          hasFeedback>
          <a-date-picker showTime format="YYYY-MM-DD HH:mm:ss" v-decorator="[ 'enterTime', {}]" />
        </a-form-item>

        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="离场时间"
          hasFeedback>
          <a-date-picker showTime format="YYYY-MM-DD HH:mm:ss" v-decorator="[ 'awayTime', {}]" />
        </a-form-item>

        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="客户方"
          hasFeedback>
          <a-input placeholder="请输入客户方" v-decorator="['client', {}]" />
        </a-form-item>

        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="开票月份"
          hasFeedback>
          <a-input-number v-decorator="[ 'billingMonth', {}]" />
        </a-form-item>

        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="回款月份"
          hasFeedback>
          <a-input-number v-decorator="[ 'paymentMonth', {}]" />
        </a-form-item>

        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="是否分包"
          hasFeedback>
          <a-input placeholder="请输入是否分包" v-decorator="['isSubcontract', {}]" />
        </a-form-item>

        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="分包单价"
          hasFeedback>
          <a-input-number v-decorator="[ 'subcontractPrice', {}]" />
        </a-form-item>

        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="分包公司"
          hasFeedback>
          <a-input placeholder="请输入分包公司" v-decorator="['subcontractingCompany', {}]" />
        </a-form-item>

        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="是否开票(分包公司)"
          hasFeedback>
          <a-input placeholder="请输入是否开票(分包公司)" v-decorator="['isInvoicing', {}]" />
        </a-form-item>

        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="回款(分包公司)"
          hasFeedback>
          <a-input-number v-decorator="[ 'payment', {}]" />
        </a-form-item>

        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="备注"
          hasFeedback>
          <a-input placeholder="请输入备注" v-decorator="['note', {}]" />
        </a-form-item>

        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="所属部门编码"
          hasFeedback>
          <a-input placeholder="请输入所属部门编码" v-decorator="['sysOrgCode', {}]" />
        </a-form-item>
      </a-form>
    </a-spin>
  </a-modal>
</template>

<script>
  import {httpAction} from '@/api/manage'
  import pick from 'lodash.pick'
  import moment from 'moment'
  import JDate from '@/components/jeecg/JDate'

  export default {
    components: {
      JDate
    },
    name: 'BillingReportModal',
    data() {
      return {
        title: '操作',
        visible: false,
        model: {},
        labelCol: {
          xs: {span: 24},
          sm: {span: 5}
        },
        wrapperCol: {
          xs: {span: 24},
          sm: {span: 16}
        },
        moment,
        format: 'YYYY-MM-DD HH:mm:ss',
        disableSubmit: false,
        mainId: '',
        hiding: false,
        confirmLoading: false,
        form: this.$form.createForm(this),
        validatorRules: {
        },
        url: {
          add: '/company/employee/addBillingReport',
          edit: '/company/employee/editBillingReport'
        }
      }
    },
    created() {
    },
    methods: {
      add(mainId) {
        if (mainId) {
          this.edit({mainId}, '')
        } else {
          this.$message.warning('请选择一条数据')
        }
      },
      detail(record) {
        this.edit(record, 'd')
      },
      edit(record, v) {
        if (v == 'e') {
          this.hiding = false;
          this.disableSubmit = false;
        } else if (v == 'd') {
          this.hiding = false;
          this.disableSubmit = true;
        } else {
          this.hiding = true;
          this.disableSubmit = false;
        }
        this.form.resetFields();
        this.mainId = record.mainId;
        this.model = Object.assign({}, record);
        this.visible = true;
        this.$nextTick(() => {
          this.form.setFieldsValue(pick(this.model, 'sportsmanId', 'job', 'price', 'entryTime', 'enterTime', 'awayTime', 'client', 'billingMonth', 'paymentMonth', 'isSubcontract', 'subcontractPrice', 'subcontractingCompany', 'isInvoicing', 'payment', 'note', 'sysOrgCode', ))
          // 时间格式化
          this.form.setFieldsValue({ entryTime: this.model.entryTime ? moment(this.model.entryTime) : null })
          this.form.setFieldsValue({ enterTime: this.model.enterTime ? moment(this.model.enterTime) : null })
          this.form.setFieldsValue({ awayTime: this.model.awayTime ? moment(this.model.awayTime) : null })
        })
      },
      close() {
        this.$emit('close');
        this.visible = false;
      },
      handleOk() {
        const that = this;
        // 触发表单验证
        this.form.validateFields((err, values) => {
          if (!err) {
            that.confirmLoading = true;
            let httpurl = '';
            let method = '';
            if (!this.model.id) {
              httpurl += this.url.add;
              method = 'post';
            } else {
              httpurl += this.url.edit;
              method = 'put';
            }
            let formData = Object.assign(this.model, values);
            //时间格式化
            formData.entryTime = formData.entryTime?formData.entryTime.format('YYYY-MM-DD HH:mm:ss'):null;
            formData.enterTime = formData.enterTime?formData.enterTime.format('YYYY-MM-DD HH:mm:ss'):null;
            formData.awayTime = formData.awayTime?formData.awayTime.format('YYYY-MM-DD HH:mm:ss'):null;
            httpAction(httpurl, formData, method).then((res) => {
              if (res.success) {
                that.$message.success(res.message);
                that.$emit('ok');
              } else {
                that.$message.warning(res.message);
              }
            }).finally(() => {
              that.confirmLoading = false;
              that.close();
            })
          }
        })
      },
      handleCancel() {
        this.close();
      }
    }
  }
</script>

<style scoped>

</style>
