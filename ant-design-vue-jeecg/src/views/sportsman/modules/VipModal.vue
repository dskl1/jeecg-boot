<template>
  <a-modal
    :title="title"
    :width="800"
    :visible="visible"
    :confirmLoading="confirmLoading"
    @ok="handleOk"
    @cancel="handleCancel"
    cancelText="关闭">

    <a-spin :spinning="confirmLoading">
      <a-form :form="form">

        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="会员姓名">
          <j-search-select-tag
            placeholder="请选择会员"
            v-decorator="['name', {}]"
            dict="vip_entry,name,name ">
          </j-search-select-tag>
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="开卡日期">
          <a-date-picker show-time format='YYYY-MM-DD HH:mm:ss' v-decorator="[ 'startTime', {}]"/>
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="到期时间">
          <a-date-picker show-time format='YYYY-MM-DD HH:mm:ss' v-decorator="[ 'vipDate', {}]"/>
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="会员次数">
          <a-input placeholder="请输入会员次数" v-decorator="['vipTime', {}]"/>
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="会员类型">
          <j-dict-select-tag v-decorator="['vipType', {}]" :triggerChange="true" placeholder="请选择会员类型"
                             dictCode="vip_card"/>
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="会员积分">
          <a-input placeholder="请输入会员积分" v-decorator="['memberPoint', {}]"/>
        </a-form-item>
      </a-form>
    </a-spin>
  </a-modal>
</template>

<script>
import { httpAction } from '@/api/manage'
import pick from 'lodash.pick'
import moment from 'moment'
import JMultiSelectTag from '@/components/dict/JMultiSelectTag'
import JSelectMultiUser from '@/components/jeecgbiz/JSelectMultiUser'
import JSearchSelectTag from '@/components/dict/JSearchSelectTag'

export default {
  components: {
    JMultiSelectTag,
    JSelectMultiUser,
    JSearchSelectTag
  },
  name: 'VipModal',
  data() {
    return {
      users: '',
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

      confirmLoading: false,
      form: this.$form.createForm(this),
      validatorRules: {},
      url: {
        add: '/sportsman/vipAdd/add',
        edit: '/sportsman/vipAdd/edit',
      },
    }
  }
  ,
  created() {
  }
  ,
  methods: {
    getFormFieldValue(field) {
      return this.form.getFieldValue(field)
    },
    add() {
      this.edit({})
    }
    ,
    edit(record) {
      this.form.resetFields()
      this.model = Object.assign({}, record)
      this.visible = true
      this.$nextTick(() => {
        this.form.setFieldsValue(pick(this.model, 'name', 'sysOrgCode', 'vipTime', 'vipType', 'memberPoint', 'startTime', 'vipDate'))
        //时间格式化
        this.form.setFieldsValue({ vipDate: this.model.vipDate ? moment(this.model.vipDate) : null })
        this.form.setFieldsValue({ startTime: this.model.startTime ? moment(this.model.startTime) : null })
        this.form.setFieldsValue({ vipTime: this.model.vipTime ? moment(this.model.vipTime) : null })
      })

    }
    ,
    close() {
      this.$emit('close')
      this.visible = false
    }
    ,
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
          formData.vipDate = formData.vipDate ? formData.vipDate.format('YYYY-MM-DD HH:mm:ss') : null
          formData.startTime = formData.startTime ? formData.startTime.format('YYYY-MM-DD HH:mm:ss') : null
          formData.vipTime = formData.vipTime ? formData.vipTime.format('YYYY-MM-DD HH:mm:ss') : null


          console.log(formData)
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
    }
    ,
    handleCancel() {
      this.close()
    }
    ,


  }
}
</script>

<style lang="less" scoped>

</style>