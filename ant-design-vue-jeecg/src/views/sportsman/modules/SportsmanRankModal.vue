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

        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="比赛名称"
          hasFeedback>
          <a-input placeholder="请输入比赛名称" v-decorator="['matchName', {}]"/>
        </a-form-item>

        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="运动等级"
          hasFeedback>
          <a-input placeholder="请输入运动等级" v-decorator="['sportGrade', {}]"/>
        </a-form-item>

        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="达成日期"
          hasFeedback>
          <a-input placeholder="请输入达成日期" v-decorator="['dataItemScore', {}]"/>
        </a-form-item>

        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="项目"
          hasFeedback>
          <a-input placeholder="请输入项目" v-decorator="['matchItem', {}]"/>
        </a-form-item>

        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="成绩"
          hasFeedback>
          <a-input placeholder="请输入成绩" v-decorator="['matchScore', {}]"/>
        </a-form-item>

        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="是否申请证书"
          hasFeedback>
          <a-input placeholder="请输入是否申请证书" v-decorator="['certificate', {}]"/>
        </a-form-item>

        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="批准日期"
          hasFeedback>
          <a-date-picker v-decorator="[ 'approvalDate', {}]"/>
        </a-form-item>
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
  name: 'SportsmanRankModal',
  data() {
    return {
      title: '操作',
      visible: false,
      model: {},
      labelCol: {
        xs: { span: 24 },
        sm: { span: 5 }
      },
      wrapperCol: {
        xs: { span: 24 },
        sm: { span: 16 }
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
        add: '/sportsman/sportsmanPersonalArchives/addSportsmanRank',
        edit: '/sportsman/sportsmanPersonalArchives/editSportsmanRank'
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
        this.form.setFieldsValue(pick(this.model, 'approvalDate', 'certificate', 'dataItemScore', 'matchItem', 'distance', 'matchName', 'matchScore', 'sportGrade', 'sportsmanId',))
        // 时间格式化
        this.form.setFieldsValue({ approvalDate: this.model.approvalDate ? moment(this.model.approvalDate) : null })
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
          formData.approvalDate = formData.approvalDate ? formData.approvalDate.format() : null
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
