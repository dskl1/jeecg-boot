<template>
  <a-modal
    :title="title"
    :width="800"
    :visible="visible"
    :confirmLoading="confirmLoading"
    @ok="handleOk"
    @cancel="handleCancel"
    cancelText="关闭">

    <template slot="title">
      <div style="width: 100%;">
        <span>{{ title }}</span>
        <span style="display:inline-block;width:calc(100% - 51px);padding-right:10px;text-align: right">
          <a-button @click="toggleScreen" icon="appstore" style="height:20px;width:20px;border:0px"></a-button>
        </span>
      </div>

    </template>

    <a-spin :spinning="confirmLoading">
      <a-form :form="form">

        <a-row>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="姓名">
          <a-input placeholder="请输入姓名" v-decorator="['name', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="训练小组">
          <j-dict-select-tag
            v-decorator="['trainingGroupId', {}]"
            :triggerChange="true"
            placeholder="请输入训练小组"
            dictCode="sportsman_group"/>
        </a-form-item>
        </a-row>


      </a-form>
    </a-spin>


  </a-modal>
</template>

<script>
  import pick from 'lodash.pick'
  import moment from "moment"

  export default {
    name: "TrainingGroupModal",
    components: {

    },
    data () {
      return {
        title:"操作",
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
        headers:{},
        validatorRules:{
        },
        url: {
          add: "/training/trainingGroup/add",
          edit: "/training/trainingGroup/edit",
        },
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
          this.form.setFieldsValue(pick(this.model,'afterSportItem','avatar','biology','birthday','birthplace','cardNumber','chemistry','coachName','distance','educationalLevel','english','fatherHeight','formerCoach','formerSchool','formerSportItem','geography','grade','groupNo','history','injuryHistory','language','linealRelativeGeneticDiseases','linealRelativeHeightProminent','math','motherHeight','name','nation','nativePlace','newTime','period','physics','politics','politicsStatus','retire','sex','sportItem','sportsmanId','stage','supportItem','term','trainingDate1','trainingGroupId','trainingItem','trainingItemNo','trainingScore'))
		  //时间格式化
          this.form.setFieldsValue({attendTrainTime:this.model.attendTrainTime?moment(this.model.attendTrainTime):null})
          this.form.setFieldsValue({entryTeamDate:this.model.entryTeamDate?moment(this.model.entryTeamDate):null})
          this.form.setFieldsValue({trainingDate:this.model.trainingDate?moment(this.model.trainingDate):null})
        });
      },
      //
      loadCheckedDeparts(){
        let that = this;
        getAction(that.url.userWithDepart,{userId:that.userId}).then((res)=>{
          that.checkedDepartNames = [];
          if(res.success){
            for (let i = 0; i < res.result.length; i++) {
              that.checkedDepartNames.push(res.result[i].title);
              this.checkedDepartNameString = this.checkedDepartNames.join(",");
              that.checkedDepartKeys.push(res.result[i].key);
            }
          }else{
            console.log(res.message);
          }
        })
      },
      close () {
        this.$emit('close');
        this.visible = false;
        this.disableSubmit = false;
        this.selectedRole = [];
        this.userDepartModel = {};
        this.checkedDepartNames = [];
      },
      moment,
      handleSubmit () {
        const that = this;
        // 触发表单验证
        this.form.validateFields((err, values) => {
          if (!err) {
            that.confirmLoading = true;
            let avatar = that.model.avatar;
            if(!values.birthday){
              values.birthday = '';
            }else{
              values.birthday = values.birthday.format(this.dateFormat);
            }
            let formData = Object.assign(this.model, values);
            formData.avatar = avatar;
            formData.selectedroles = this.selectedRole.length>0?this.selectedRole.join(","):'';

            that.addDepartsToUser(that,formData); // 调用根据当前用户添加部门信息的方法
            let obj;
            if(!this.model.id){
              formData.id = this.userId;
              obj=addUser(formData);
            }else{
              obj=editUser(formData);
            }
            obj.then((res)=>{
              if(res.success){
                that.$message.success(res.message);
                that.$emit('ok');
              }else{
                that.$message.warning(res.message);
              }
            }).finally(() => {
              that.confirmLoading = false;
              that.checkedDepartNames = [];
              that.close();
            })

          }
        })
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
            //时间格式化
            formData.attendTrainTime = formData.attendTrainTime?formData.attendTrainTime.format():null;
            formData.entryTeamDate = formData.entryTeamDate?formData.entryTeamDate.format():null;
            formData.trainingDate = formData.trainingDate?formData.trainingDate.format():null;

            console.log(formData)
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

    }
  }
</script>

<style scoped>

</style>