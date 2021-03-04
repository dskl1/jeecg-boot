<template>
  <a-modal
    :title="title"
    :width="1000"
    :visible="visible"
    :confirmLoading="confirmLoading"
    @ok="handleOk"
    @cancel="handleCancel">
    <a-spin :spinning="confirmLoading">
      <!-- 主表单区域 -->
      <a-form :form="form">
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="姓名">
          <a-input placeholder="请输入姓名" v-decorator="['name', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="训练日期">
          <a-date-picker v-decorator="[ 'trainingDate', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="训练时间">
          <a-input placeholder="请输入训练时间" v-decorator="['trainingDate1', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="训练小组">
          <a-input placeholder="请输入训练小组" v-decorator="['trainingGroupId', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="训练项目">
          <a-input placeholder="请输入训练项目" v-decorator="['trainingItem', {}]" />
        </a-form-item>
      </a-form>
    </a-spin>
  </a-modal>
</template>

<script>
  import {httpAction} from '@/api/manage'
  import JDate from '@/components/jeecg/JDate'
  import pick from 'lodash.pick'
  import moment from "moment"

  export default {
    name: 'StatisticsModal',
    components: {
      JDate
    },
    data() {
      return {
        title: "操作",
        visible: false,
        model: {},
        labelCol: {
          xs: {span: 24},
          sm: {span: 5},
        },
        wrapperCol: {
          xs: {span: 24},
          sm: {span: 16},
        },
        confirmLoading: false,
        form: this.$form.createForm(this),
        validatorRules: {
        },
        url: {
          add: "/statistics/statistics/add",
          edit: "/statistics/statistics/edit",
          d25aList: '/statistics/statistics/queryD25aByMainId',
          d50aList: '/statistics/statistics/queryD50aByMainId',
          d100aList: '/statistics/statistics/queryD100aByMainId',
          d150aList: '/statistics/statistics/queryD150aByMainId',
          d200aList: '/statistics/statistics/queryD200aByMainId',
          d250aList: '/statistics/statistics/queryD250aByMainId',
          d300aList: '/statistics/statistics/queryD300aByMainId',
          d350aList: '/statistics/statistics/queryD350aByMainId',
          d400aList: '/statistics/statistics/queryD400aByMainId',
          d800aList: '/statistics/statistics/queryD800aByMainId',
          d1000aList: '/statistics/statistics/queryD1000aByMainId',
          d1500aList: '/statistics/statistics/queryD1500aByMainId',
        }
      }
    },
    methods: {
      add() {
        this.edit({});
      },
      edit(record) {
        this.form.resetFields();
        this.model = Object.assign({}, record);
        //初始化明细表数据
        console.log(this.model.id)
        this.visible = true;
        this.$nextTick(() => {
          this.form.setFieldsValue(pick(this.model, 'afterSportItem', 'attendTrainTime', 'avatar', 'biology', 'birthday', 'birthplace', 'cardNumber', 'chemistry', 'coachName', 'distance', 'educationalLevel', 'english', 'entryTeamDate', 'fatherHeight', 'formerCoach', 'formerSchool', 'formerSportItem', 'geography', 'grade', 'groupNo', 'history', 'injuryHistory', 'language', 'linealRelativeGeneticDiseases', 'linealRelativeHeightProminent', 'math', 'motherHeight', 'name', 'nation', 'nativePlace', 'newTime', 'period', 'physics', 'politics', 'politicsStatus', 'retire', 'sex', 'sportItem', 'sportsmanId', 'stage', 'supportItem', 'term', 'trainingDate', 'trainingDate1', 'trainingGroupId', 'trainingItem', 'trainingItemNo', 'trainingScore', ))
          // 时间格式化
          this.form.setFieldsValue({ attendTrainTime: this.model.attendTrainTime ? moment(this.model.attendTrainTime) : null })
          this.form.setFieldsValue({ entryTeamDate: this.model.entryTeamDate ? moment(this.model.entryTeamDate) : null })
          this.form.setFieldsValue({ trainingDate: this.model.trainingDate ? moment(this.model.trainingDate) : null })
        });
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
            formData.attendTrainTime = formData.attendTrainTime?formData.attendTrainTime.format():null;
            formData.entryTeamDate = formData.entryTeamDate?formData.entryTeamDate.format():null;
            formData.trainingDate = formData.trainingDate?formData.trainingDate.format():null;
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
        this.close()
      }
    }
  }
</script>

<style scoped>
  .ant-btn {
    padding: 0 10px;
    margin-left: 3px;
  }

  .ant-form-item-control {
    line-height: 0px;
  }

  /** 主表单行间距 */
  .ant-form .ant-form-item {
    margin-bottom: 10px;
  }

  /** Tab页面行间距 */
  .ant-tabs-content .ant-form-item {
    margin-bottom: 0px;
  }
</style>