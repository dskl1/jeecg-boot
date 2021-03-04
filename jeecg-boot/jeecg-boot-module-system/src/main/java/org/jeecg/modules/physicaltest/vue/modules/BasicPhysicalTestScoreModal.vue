<template>
  <j-modal
    :title="title"
    :width="width"
    :visible="visible"
    :confirmLoading="confirmLoading"
    switchFullscreen
    @ok="handleOk"
    @cancel="handleCancel"
    cancelText="关闭">
    <a-spin :spinning="confirmLoading">
      <a-form :form="form">

        <a-form-item label="外键" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="['sportsmanId']" placeholder="请输入外键"></a-input>
        </a-form-item>
        <a-form-item label="BM1" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="['bm1']" placeholder="请输入BM1" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="坐位体前屈(cm)" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="['sittingForward']" placeholder="请输入坐位体前屈(cm)" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="30米冲刺(s)" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="['sprint30m']" placeholder="请输入30米冲刺(s)" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="垂直纵跳(cm)" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="['verticalJump']" placeholder="请输入垂直纵跳(cm)" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="深蹲相对力量" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="['squatRelativeStrength']" placeholder="请输入深蹲相对力量" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="卧推相对力量" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="['benchPressRelativeStrength']" placeholder="请输入卧推相对力量" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="引体向上(次)" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="['pullUps']" placeholder="请输入引体向上(次)" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="腹肌耐力(s)" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="['absEndurance']" placeholder="请输入腹肌耐力(s)" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="背肌耐力(s)" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="['dorsalMuscleEndurance']" placeholder="请输入背肌耐力(s)" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="3000m(分:秒)" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="['meter3000']" placeholder="请输入3000m(分:秒)" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="创建人" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="['createBy']" placeholder="请输入创建人"></a-input>
        </a-form-item>
        <a-form-item label="创建日期" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <j-date placeholder="请选择创建日期" v-decorator="['createTime']" :trigger-change="true" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="更新人" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="['updateBy']" placeholder="请输入更新人"></a-input>
        </a-form-item>
        <a-form-item label="更新日期" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <j-date placeholder="请选择更新日期" v-decorator="['updateTime']" :trigger-change="true" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="所属部门" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="['sysOrgCode']" placeholder="请输入所属部门"></a-input>
        </a-form-item>

      </a-form>
    </a-spin>
  </j-modal>
</template>

<script>

  import { httpAction } from '@/api/manage'
  import pick from 'lodash.pick'
  import { validateDuplicateValue } from '@/utils/util'
  import JDate from '@/components/jeecg/JDate'

  export default {
    name: "BasicPhysicalTestScoreModal",
    components: {
      JDate,
    },
    props:{
      mainId:{
        type:String,
        required:false,
        default:''
      }
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
        },
        url: {
          add: "/physicaltest/sportsmanTest/addBasicPhysicalTestScore",
          edit: "/physicaltest/sportsmanTest/editBasicPhysicalTestScore",
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
          this.form.setFieldsValue(pick(this.model,'sportsmanId','bm1','sittingForward','sprint30m','verticalJump','squatRelativeStrength','benchPressRelativeStrength','pullUps','absEndurance','dorsalMuscleEndurance','meter3000','createBy','createTime','updateBy','updateTime','sysOrgCode'))
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
            formData['sportsmanId'] = this.mainId
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
        this.form.setFieldsValue(pick(row,'sportsmanId','bm1','sittingForward','sprint30m','verticalJump','squatRelativeStrength','benchPressRelativeStrength','pullUps','absEndurance','dorsalMuscleEndurance','meter3000','createBy','createTime','updateBy','updateTime','sysOrgCode'))
      },


    }
  }
</script>
