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
          <a-input disabled placeholder="请输入姓名" v-decorator="['name', {}]" />
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
    name: 'MatchMemberpModal',
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
          add: "/match/matchMemberp/add",
          edit: "/match/matchMemberp/edit",
          matchTotalpList: '/match/matchMemberp/queryMatchTotalpByMainId',
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
          this.form.setFieldsValue(pick(this.model, 'sportsmanId', 'name', 'sex', 'avatar', 'sportItem', 'fatherHeight', 'motherHeight', 'linealRelativeHeightProminent', 'linealRelativeGeneticDiseases', 'grade', 'term', 'language', 'math', 'english', 'biology', 'geography', 'chemistry', 'physics', 'politics', 'history', 'nation', 'nativePlace', 'birthday', 'birthplace', 'educationalLevel', 'politicsStatus', 'formerSchool', 'formerCoach', 'entryTeamDate', 'attendTrainTime', 'formerSportItem', 'afterSportItem', 'injuryHistory', 'cardNumber', 'retire', 'stage', 'trainingDate', 'trainingDate1', 'period', 'trainingGroupId', 'trainingItemNo', 'trainingItem', 'distance', 'groupNo', 'coachName', 'trainingScore', 'supportItem', 'newTime', ))
          // 时间格式化
          this.form.setFieldsValue({ entryTeamDate: this.model.entryTeamDate ? moment(this.model.entryTeamDate) : null })
          this.form.setFieldsValue({ attendTrainTime: this.model.attendTrainTime ? moment(this.model.attendTrainTime) : null })
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
            formData.entryTeamDate = formData.entryTeamDate?formData.entryTeamDate.format():null;
            formData.attendTrainTime = formData.attendTrainTime?formData.attendTrainTime.format():null;
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