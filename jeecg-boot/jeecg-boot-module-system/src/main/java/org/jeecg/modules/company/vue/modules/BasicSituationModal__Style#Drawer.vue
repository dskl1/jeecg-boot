<template>
  <a-drawer
    :title="title"
    :width="width"
    placement="right"
    :closable="false"
    @close="close"
    :visible="visible">
  
    <a-spin :spinning="confirmLoading">
      <a-form :form="form">

        <a-form-item label="职位" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'job', validatorRules.job]" placeholder="请输入职位"></a-input>
        </a-form-item>
        <a-form-item label="性别" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'sex', validatorRules.sex]" placeholder="请输入性别"></a-input>
        </a-form-item>
        <a-form-item label="出生年月" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <j-date placeholder="请选择出生年月" v-decorator="[ 'birthday', validatorRules.birthday]" :trigger-change="true" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="籍贯" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'hometown', validatorRules.hometown]" placeholder="请输入籍贯"></a-input>
        </a-form-item>
        <a-form-item label="教育背景" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'educationBackground', validatorRules.educationBackground]" placeholder="请输入教育背景"></a-input>
        </a-form-item>
        <a-form-item label="专业" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'profession', validatorRules.profession]" placeholder="请输入专业"></a-input>
        </a-form-item>
        <a-form-item label="工作经历" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-textarea v-decorator="['workExperience', validatorRules.workExperience]" rows="4" placeholder="请输入工作经历"/>
        </a-form-item>
        <a-form-item label="项目经验" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-textarea v-decorator="['projectExperience', validatorRules.projectExperience]" rows="4" placeholder="请输入项目经验"/>
        </a-form-item>
        <a-form-item label="自我评价" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-textarea v-decorator="['selfEvaluation', validatorRules.selfEvaluation]" rows="4" placeholder="请输入自我评价"/>
        </a-form-item>
        <a-form-item label="创建人" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'createBy', validatorRules.createBy]" placeholder="请输入创建人"></a-input>
        </a-form-item>
        <a-form-item label="创建时间" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <j-date placeholder="请选择创建时间" v-decorator="[ 'createTime', validatorRules.createTime]" :trigger-change="true" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="修改人" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'updateBy', validatorRules.updateBy]" placeholder="请输入修改人"></a-input>
        </a-form-item>
        <a-form-item label="修改时间" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <j-date placeholder="请选择修改时间" v-decorator="[ 'updateTime', validatorRules.updateTime]" :trigger-change="true" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="所属部门编码" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'sysOrgCode', validatorRules.sysOrgCode]" placeholder="请输入所属部门编码"></a-input>
        </a-form-item>
        
      </a-form>
    </a-spin>
    <a-button type="primary" @click="handleOk">确定</a-button>
    <a-button type="primary" @click="handleCancel">取消</a-button>
  </a-drawer>
</template>

<script>

  import { httpAction } from '@/api/manage'
  import pick from 'lodash.pick'
  import { validateDuplicateValue } from '@/utils/util'
  import JDate from '@/components/jeecg/JDate'  
  
  export default {
    name: "BasicSituationModal",
    components: { 
      JDate,
    },
    data () {
      return {
        form: this.$form.createForm(this),
        title:"操作",
        width:800,
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
        validatorRules: {
          job: {rules: [
          ]},
          sex: {rules: [
          ]},
          birthday: {rules: [
          ]},
          hometown: {rules: [
          ]},
          educationBackground: {rules: [
          ]},
          profession: {rules: [
          ]},
          workExperience: {rules: [
          ]},
          projectExperience: {rules: [
          ]},
          selfEvaluation: {rules: [
          ]},
          createBy: {rules: [
          ]},
          createTime: {rules: [
          ]},
          updateBy: {rules: [
          ]},
          updateTime: {rules: [
          ]},
          sysOrgCode: {rules: [
          ]},
        },
        url: {
          add: "/company/basicSituation/add",
          edit: "/company/basicSituation/edit",
        }
      }
    },
    created () {
    },
    methods: {
      add () {
        this.edit({});
      },
      edit (record) {
        this.form.resetFields();
        this.model = Object.assign({}, record);
        this.visible = true;
        this.$nextTick(() => {
          this.form.setFieldsValue(pick(this.model,'job','sex','birthday','hometown','educationBackground','profession','workExperience','projectExperience','selfEvaluation','createBy','createTime','updateBy','updateTime','sysOrgCode'))
        })
      },
      close () {
        this.$emit('close');
        this.visible = false;
      },
      handleOk () {
        const that = this;
        // 触发表单验证
        this.form.validateFields((err, values) => {
          if (!err) {
            that.confirmLoading = true;
            let httpurl = '';
            let method = '';
            if(!this.model.id){
              httpurl+=this.url.add;
              method = 'post';
            }else{
              httpurl+=this.url.edit;
               method = 'put';
            }
            let formData = Object.assign(this.model, values);
            console.log("表单提交数据",formData)
            httpAction(httpurl,formData,method).then((res)=>{
              if(res.success){
                that.$message.success(res.message);
                that.$emit('ok');
              }else{
                that.$message.warning(res.message);
              }
            }).finally(() => {
              that.confirmLoading = false;
              that.close();
            })
          }
         
        })
      },
      handleCancel () {
        this.close()
      },
      popupCallback(row){
        this.form.setFieldsValue(pick(row,'job','sex','birthday','hometown','educationBackground','profession','workExperience','projectExperience','selfEvaluation','createBy','createTime','updateBy','updateTime','sysOrgCode'))
      }
      
    }
  }
</script>

<style lang="less" scoped>
/** Button按钮间距 */
  .ant-btn {
    margin-left: 30px;
    margin-bottom: 30px;
    float: right;
  }
</style>