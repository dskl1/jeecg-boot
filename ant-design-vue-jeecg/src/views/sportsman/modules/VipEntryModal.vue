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
          label="姓名">
          <a-input style="width: 40%" placeholder="请输入姓名" v-decorator="['name', {}]"/>
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="性别">
          <j-dict-select-tag style="width: 40%" v-decorator="['sex', {}]" :triggerChange="true" placeholder="请输入性别"
                             dictCode="sex"/>
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="身份证号">
          <a-input style="width: 40%" placeholder="请输入身份证号" v-decorator="['cardNumber', {}]"/>
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="年龄">
          <a-input-number v-decorator="[ 'age', {}]"/>
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="手机">
          <a-input style="width: 40%" placeholder="请输入手机" v-decorator="['phone', {}]"/>
        </a-form-item>
        <a-form-item
          v-has="'vip'"
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="场馆">
            <j-multi-select-tag
              style="width: 40%"
              placeholder="请做出你的选择"
              v-decorator="[ 'sysOrgCode', {}]"
              dictCode="sys_depart,depart_name,org_code">
            </j-multi-select-tag>
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

  export default {
    components: {
      JMultiSelectTag
    },
    name: 'VipEntryModal',
    data () {
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

        confirmLoading: false,
        form: this.$form.createForm(this),
        validatorRules: {},
        url: {
          add: '/sportsman/vipEntry/add',
          edit: '/sportsman/vipEntry/edit',
        },
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
          this.form.setFieldsValue(pick(this.model, 'name', 'sex', 'cardNumber', 'age', 'phone', 'sysOrgCode'))
          //时间格式化
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
      },
      handleCancel () {
        this.close()
      },

    }
  }
</script>

<style lang="less" scoped>

</style>