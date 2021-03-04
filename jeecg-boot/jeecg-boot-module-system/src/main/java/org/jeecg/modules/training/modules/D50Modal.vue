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

        <a-form-item label="训练日期" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <j-date placeholder="请选择训练日期" v-decorator="['trainingDate']" :trigger-change="true" style="width: 30%"/>
        </a-form-item>
        <a-form-item label="时间段" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <j-dict-select-tag style="width: 30%" v-decorator="['period', {}]" :triggerChange="true"
                             dictCode="period"/>
        </a-form-item>
        <a-form-item label="训练小组" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input style="width: 30%" v-decorator="['trainingGroupId']" placeholder="请输入训练小组"></a-input>
        </a-form-item>
        <a-form-item label="训练序号" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input style="width: 30%" v-decorator="['trainingItemNo']" placeholder="请输入训练序号"></a-input>
        </a-form-item>
        <a-form-item label="训练项目" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <j-dict-select-tag style="width: 30%" v-decorator="['trainingItem', {}]" :triggerChange="true"
                             dictCode="swim_event"/>
        </a-form-item>
        <a-form-item label="组数" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="['groupNo']" placeholder="请输入组数" style="width: 30%"/>
        </a-form-item>
        <a-form-item label="教练" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input style="width: 30%" v-decorator="['coachName']" placeholder="请输入教练"></a-input>
        </a-form-item>
        <a-form-item label="是否高原" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <j-dict-select-tag
            style="width:30%"
            v-decorator="['isHighland',{'initialValue':'否'}]"
            :triggerChange="true"
            dictCode="ishighland"/>
        </a-form-item>
        <a-form-item label="包干时间" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="['baoGan']" placeholder="请输入包干时间" style="width: 30%"/>
        </a-form-item>
        <a-form-item label="趟数" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="['tangShu']" placeholder="请输入趟数" style="width: 30%"/>
        </a-form-item>
        <a-form-item label="辅助道具" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <j-dict-select-tag style="width: 30%" v-decorator="['supportItem', {}]" :triggerChange="true"
                             dictCode="support_item"/>
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
    name: "D50Modal",
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
          add: "/training/trainingScore/addD50",
          edit: "/training/trainingScore/editD50",
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
          this.form.setFieldsValue(pick(this.model,'sportsmanId','trainingDate','period','trainingGroupId','trainingItemNo','trainingItem','groupNo','coachName','isHighland','baoGan','tangShu','supportItem','createBy','createTime','updateBy','updateTime','sysOrgCode'))
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
        this.form.setFieldsValue(pick(row,'sportsmanId','trainingDate','period','trainingGroupId','trainingItemNo','trainingItem','groupNo','coachName','isHighland','baoGan','tangShu','supportItem','createBy','createTime','updateBy','updateTime','sysOrgCode'))
      },


    }
  }
</script>
