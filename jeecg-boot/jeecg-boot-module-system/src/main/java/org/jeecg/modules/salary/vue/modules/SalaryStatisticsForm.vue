<template>
  <a-spin :spinning="confirmLoading">
    <j-form-container :disabled="formDisabled">
      <a-form :form="form" slot="detail">
        <a-row>
          <a-col :span="12">
            <a-form-item label="姓名" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['name']" placeholder="请输入姓名"></a-input>
            </a-form-item>
          </a-col>
          <a-col :span="12">
            <a-form-item label="社保(公司)" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['socialSecurityCo']" placeholder="请输入社保(公司)"></a-input>
            </a-form-item>
          </a-col>
          <a-col :span="12">
            <a-form-item label="社保(个人)" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['socialSecuityPerson']" placeholder="请输入社保(个人)"></a-input>
            </a-form-item>
          </a-col>
          <a-col :span="12">
            <a-form-item label="税前工资" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['preTaxIncome']" placeholder="请输入税前工资"></a-input>
            </a-form-item>
          </a-col>
          <a-col :span="12">
            <a-form-item label="报销" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['reimbursement']" placeholder="请输入报销"></a-input>
            </a-form-item>
          </a-col>
          <a-col :span="12">
            <a-form-item label="个人所得税" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['personIncome']" placeholder="请输入个人所得税"></a-input>
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
    name: "SalaryStatisticsForm",
    components: {
      JFormContainer,
    },
    props: {
      //流程表单data
      formData: {
        type: Object,
        default: ()=>{},
        required: false
      },
      //表单模式：false流程表单 true普通表单
      normal: {
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
    data () {
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
        validatorRules: {
        },
        url: {
          add: "/salary/salaryStatistics/add",
          edit: "/salary/salaryStatistics/edit",
          queryById: "/salary/salaryStatistics/queryById"
        }
      }
    },
    computed: {
      formDisabled(){
        if(this.normal===false){
          if(this.formData.disabled===false){
            return false
          }else{
            return true
          }
        }
        return this.disabled
      },
      showFlowSubmitButton(){
        if(this.normal===false){
          if(this.formData.disabled===false){
            return true
          }else{
            return false
          }
        }else{
          return false
        }
      }
    },
    created () {
      //如果是流程中表单，则需要加载流程表单data
      this.showFlowData();
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
          this.form.setFieldsValue(pick(this.model,'name','socialSecurityCo','socialSecuityPerson','preTaxIncome','reimbursement','personIncome'))
        })
      },
      //渲染流程表单数据
      showFlowData(){
        if(this.normal === false){
          let params = {id:this.formData.dataId};
          getAction(this.url.queryById,params).then((res)=>{
            if(res.success){
              this.edit (res.result);
            }
          });
        }
      },
      submitForm () {
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
            })
          }
         
        })
      },
      popupCallback(row){
        this.form.setFieldsValue(pick(row,'name','socialSecurityCo','socialSecuityPerson','preTaxIncome','reimbursement','personIncome'))
      },
    }
  }
</script>