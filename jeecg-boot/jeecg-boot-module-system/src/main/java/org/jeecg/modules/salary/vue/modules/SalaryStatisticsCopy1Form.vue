<template>
  <a-spin :spinning="confirmLoading">
    <j-form-container :disabled="formDisabled">
      <a-form :form="form" slot="detail">
        <a-row>
          <a-col :span="8">
            <a-form-item label="姓名" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['name']" placeholder="请输入姓名"></a-input>
            </a-form-item>
          </a-col>
          <a-col :span="8">
            <a-form-item label="电子邮件" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['email', validatorRules.email]" placeholder="请输入电子邮件"></a-input>
            </a-form-item>
          </a-col>
          <a-col :span="8">
            <a-form-item label="基本工资标准" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input-number v-decorator="['basicWageStandard']" placeholder="请输入基本工资标准" style="width: 100%"/>
            </a-form-item>
          </a-col>
          <a-col :span="8">
            <a-form-item label="外派补贴标准" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input-number v-decorator="['dispatchSubsidyStandard']" placeholder="请输入外派补贴标准" style="width: 100%"/>
            </a-form-item>
          </a-col>
          <a-col :span="8">
            <a-form-item label="转正日期" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <j-date placeholder="请选择转正日期" v-decorator="['positiveDate']" :trigger-change="true" style="width: 100%"/>
            </a-form-item>
          </a-col>
          <a-col :span="8">
            <a-form-item label="转正薪资" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input-number v-decorator="['positiveSalary']" placeholder="请输入转正薪资" style="width: 100%"/>
            </a-form-item>
          </a-col>
          <a-col :span="8">
            <a-form-item label="试用工资" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input-number v-decorator="['trialSalary']" placeholder="请输入试用工资" style="width: 100%"/>
            </a-form-item>
          </a-col>
          <a-col :span="8">
            <a-form-item label="转正天数" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input-number v-decorator="['positiveDay']" placeholder="请输入转正天数" style="width: 100%"/>
            </a-form-item>
          </a-col>
          <a-col :span="8">
            <a-form-item label="试用天数" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input-number v-decorator="['trialDay']" placeholder="请输入试用天数" style="width: 100%"/>
            </a-form-item>
          </a-col>
          <a-col :span="8">
            <a-form-item label="应出勤天数" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input-number v-decorator="['totalDay']" placeholder="请输入应出勤天数" style="width: 100%"/>
            </a-form-item>
          </a-col>
          <a-col :span="8">
            <a-form-item label="实际出勤天数" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input-number v-decorator="['actualDay']" placeholder="请输入实际出勤天数" style="width: 100%"/>
            </a-form-item>
          </a-col>
          <a-col :span="8">
            <a-form-item label="外派天数" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input-number v-decorator="['dispatchDay']" placeholder="请输入外派天数" style="width: 100%"/>
            </a-form-item>
          </a-col>
          <a-col :span="8">
            <a-form-item label="加班时间" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input-number v-decorator="['overtimeHours']" placeholder="请输入加班时间" style="width: 100%"/>
            </a-form-item>
          </a-col>
          <a-col :span="8">
            <a-form-item label="基本工资" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input-number v-decorator="['basicWage']" placeholder="请输入基本工资" style="width: 100%"/>
            </a-form-item>
          </a-col>
          <a-col :span="8">
            <a-form-item label="外派补贴" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input-number v-decorator="['dispatchSubsidy']" placeholder="请输入外派补贴" style="width: 100%"/>
            </a-form-item>
          </a-col>
          <a-col :span="8">
            <a-form-item label="餐补" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input-number v-decorator="['mealSupplement']" placeholder="请输入餐补" style="width: 100%"/>
            </a-form-item>
          </a-col>
          <a-col :span="8">
            <a-form-item label="加班工资" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input-number v-decorator="['overtimeSalary']" placeholder="请输入加班工资" style="width: 100%"/>
            </a-form-item>
          </a-col>
          <a-col :span="8">
            <a-form-item label="其他" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input-number v-decorator="['otherThing']" placeholder="请输入其他" style="width: 100%"/>
            </a-form-item>
          </a-col>
          <a-col :span="8">
            <a-form-item label="电脑补贴" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input-number v-decorator="['computerSubsidy']" placeholder="请输入电脑补贴" style="width: 100%"/>
            </a-form-item>
          </a-col>
          <a-col :span="8">
            <a-form-item label="预支工资" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input-number v-decorator="['advanceSalary']" placeholder="请输入预支工资" style="width: 100%"/>
            </a-form-item>
          </a-col>
          <a-col :span="8">
            <a-form-item label="公积金" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input-number v-decorator="['providentFund']" placeholder="请输入公积金" style="width: 100%"/>
            </a-form-item>
          </a-col>
          <a-col :span="8">
            <a-form-item label="社保(个人)" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input-number v-decorator="['socialSecurityPerson']" placeholder="请输入社保(个人)" style="width: 100%"/>
            </a-form-item>
          </a-col>
          <a-col :span="8">
            <a-form-item label="社保(公司)" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input-number v-decorator="['socialSecurityCo']" placeholder="请输入社保(公司)" style="width: 100%"/>
            </a-form-item>
          </a-col>
          <a-col :span="8">
            <a-form-item label="工资总计" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input-number v-decorator="['totalIncome']" placeholder="请输入工资总计" style="width: 100%"/>
            </a-form-item>
          </a-col>
          <a-col :span="8">
            <a-form-item label="税前工资" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input-number v-decorator="['preTaxIncome']" placeholder="请输入税前工资" style="width: 100%"/>
            </a-form-item>
          </a-col>
          <a-col :span="8">
            <a-form-item label="专项抵扣项目" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['specialDeductionItems']" placeholder="请输入专项抵扣项目"></a-input>
            </a-form-item>
          </a-col>
          <a-col :span="8">
            <a-form-item label="专项抵扣金额" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input-number v-decorator="['specialDeductionAmount']" placeholder="请输入专项抵扣金额" style="width: 100%"/>
            </a-form-item>
          </a-col>
          <a-col :span="8">
            <a-form-item label="申请时间" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <j-date placeholder="请选择申请时间" v-decorator="['applicationDate']" :trigger-change="true" style="width: 100%"/>
            </a-form-item>
          </a-col>
          <a-col :span="8">
            <a-form-item label="到期时间" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <j-date placeholder="请选择到期时间" v-decorator="['expireDate']" :trigger-change="true" style="width: 100%"/>
            </a-form-item>
          </a-col>
          <a-col :span="8">
            <a-form-item label="个人所得税" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input-number v-decorator="['personIncome']" placeholder="请输入个人所得税" style="width: 100%"/>
            </a-form-item>
          </a-col>
          <a-col :span="8">
            <a-form-item label="实发工资" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input-number v-decorator="['actualSalary']" placeholder="请输入实发工资" style="width: 100%"/>
            </a-form-item>
          </a-col>
          <a-col :span="8">
            <a-form-item label="总计" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input-number v-decorator="['totalSalary']" placeholder="请输入总计" style="width: 100%"/>
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
  import JDate from '@/components/jeecg/JDate'  

  export default {
    name: 'SalaryStatisticsCopy1Form',
    components: {
      JFormContainer,
      JDate,
    },
    props: {
      //流程表单data
      formData: {
        type: Object,
        default: ()=>{},
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
          email: {
            rules: [
              { required: false},
              { pattern: /^([\w]+\.*)([\w]+)@[\w]+\.\w{3}(\.\w{2}|)$/, message: '请输入正确的电子邮件!'},
            ]
          },
        },
        url: {
          add: "/salary/salaryStatisticsCopy1/add",
          edit: "/salary/salaryStatisticsCopy1/edit",
          queryById: "/salary/salaryStatisticsCopy1/queryById"
        }
      }
    },
    computed: {
      formDisabled(){
        if(this.formBpm===true){
          if(this.formData.disabled===false){
            return false
          }
          return true
        }
        return this.disabled
      },
      showFlowSubmitButton(){
        if(this.formBpm===true){
          if(this.formData.disabled===false){
            return true
          }
        }
        return false
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
          this.form.setFieldsValue(pick(this.model,'name','email','basicWageStandard','dispatchSubsidyStandard','positiveDate','positiveSalary','trialSalary','positiveDay','trialDay','totalDay','actualDay','dispatchDay','overtimeHours','basicWage','dispatchSubsidy','mealSupplement','overtimeSalary','otherThing','computerSubsidy','advanceSalary','providentFund','socialSecurityPerson','socialSecurityCo','totalIncome','preTaxIncome','specialDeductionItems','specialDeductionAmount','applicationDate','expireDate','personIncome','actualSalary','totalSalary'))
        })
      },
      //渲染流程表单数据
      showFlowData(){
        if(this.formBpm === true){
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
        this.form.setFieldsValue(pick(row,'name','email','basicWageStandard','dispatchSubsidyStandard','positiveDate','positiveSalary','trialSalary','positiveDay','trialDay','totalDay','actualDay','dispatchDay','overtimeHours','basicWage','dispatchSubsidy','mealSupplement','overtimeSalary','otherThing','computerSubsidy','advanceSalary','providentFund','socialSecurityPerson','socialSecurityCo','totalIncome','preTaxIncome','specialDeductionItems','specialDeductionAmount','applicationDate','expireDate','personIncome','actualSalary','totalSalary'))
      },
    }
  }
</script>