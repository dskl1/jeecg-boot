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
          label="入队后拟选专项">
          <a-input placeholder="请输入入队后拟选专项" v-decorator="['afterSportItem', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="开始训练时间">
          <a-date-picker v-decorator="[ 'attendTrainTime', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="照片">
          <a-input placeholder="请输入照片" v-decorator="['avatar', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="生物">
          <a-input-number v-decorator="[ 'biology', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="出生日期">
          <a-date-picker v-decorator="[ 'birthday', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="出生地">
          <a-input placeholder="请输入出生地" v-decorator="['birthplace', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="身份证号码">
          <a-input placeholder="请输入身份证号码" v-decorator="['cardNumber', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="化学">
          <a-input-number v-decorator="[ 'chemistry', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="教练">
          <a-input placeholder="请输入教练" v-decorator="['coachName', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="距离">
          <a-input placeholder="请输入距离" v-decorator="['distance', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="文化程度">
          <a-input placeholder="请输入文化程度" v-decorator="['educationalLevel', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="英语">
          <a-input-number v-decorator="[ 'english', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="入队(校)时间">
          <a-date-picker v-decorator="[ 'entryTeamDate', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="父亲身高(CM)">
          <a-input-number v-decorator="[ 'fatherHeight', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="原教练">
          <a-input placeholder="请输入原教练" v-decorator="['formerCoach', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="原学校">
          <a-input placeholder="请输入原学校" v-decorator="['formerSchool', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="入队前专项">
          <a-input placeholder="请输入入队前专项" v-decorator="['formerSportItem', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="地理">
          <a-input-number v-decorator="[ 'geography', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="年级">
          <a-input placeholder="请输入年级" v-decorator="['grade', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="组数">
          <a-input placeholder="请输入组数" v-decorator="['groupNo', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="历史">
          <a-input-number v-decorator="[ 'history', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="有何伤病史">
          <a-input placeholder="请输入有何伤病史" v-decorator="['injuryHistory', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="语文">
          <a-input-number v-decorator="[ 'language', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="直系亲属中有无遗传疾病">
          <a-input placeholder="请输入直系亲属中有无遗传疾病" v-decorator="['linealRelativeGeneticDiseases', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="直系亲属中有无突出身高者">
          <a-input placeholder="请输入直系亲属中有无突出身高者" v-decorator="['linealRelativeHeightProminent', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="数学">
          <a-input-number v-decorator="[ 'math', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="母亲身高(CM)">
          <a-input-number v-decorator="[ 'motherHeight', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="姓名">
          <a-input placeholder="请输入姓名" v-decorator="['name', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="民族">
          <a-input placeholder="请输入民族" v-decorator="['nation', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="籍贯">
          <a-input placeholder="请输入籍贯" v-decorator="['nativePlace', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="时间段">
          <a-input placeholder="请输入时间段" v-decorator="['period', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="物理">
          <a-input-number v-decorator="[ 'physics', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="政治">
          <a-input-number v-decorator="[ 'politics', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="政治面貌">
          <a-input placeholder="请输入政治面貌" v-decorator="['politicsStatus', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="状态">
          <a-input placeholder="请输入状态" v-decorator="['retire', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="性别">
          <a-input placeholder="请输入性别" v-decorator="['sex', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="运动项目">
          <a-input placeholder="请输入运动项目" v-decorator="['sportItem', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="阶段">
          <a-input placeholder="请输入阶段" v-decorator="['stage', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="辅助道具">
          <a-input placeholder="请输入辅助道具" v-decorator="['supportItem', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="学期">
          <a-input placeholder="请输入学期" v-decorator="['term', {}]" />
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
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="训练序号">
          <a-input placeholder="请输入训练序号" v-decorator="['trainingItemNo', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="成绩">
          <a-input placeholder="请输入成绩" v-decorator="['trainingScore', {}]" />
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
    name: 'SportsmanPersonalArchivesModal',
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
          add: "/sportsman/sportsmanPersonalArchives/add",
          edit: "/sportsman/sportsmanPersonalArchives/edit",
          sportsmanRankList: '/sportsman/sportsmanPersonalArchives/querySportsmanRankByMainId',
          sportsmanResumeList: '/sportsman/sportsmanPersonalArchives/querySportsmanResumeByMainId',
          sportsmanFamilySituationList: '/sportsman/sportsmanPersonalArchives/querySportsmanFamilySituationByMainId',
          sportsmanDataList: '/sportsman/sportsmanPersonalArchives/querySportsmanDataByMainId',
          developSituationList: '/sportsman/sportsmanPersonalArchives/queryDevelopSituationByMainId',
          bodyTypeList: '/sportsman/sportsmanPersonalArchives/queryBodyTypeByMainId',
          bodyAbilityList: '/sportsman/sportsmanPersonalArchives/queryBodyAbilityByMainId',
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
          this.form.setFieldsValue(pick(this.model, 'afterSportItem', 'attendTrainTime', 'avatar', 'biology', 'birthday', 'birthplace', 'cardNumber', 'chemistry', 'coachName', 'distance', 'educationalLevel', 'english', 'entryTeamDate', 'fatherHeight', 'formerCoach', 'formerSchool', 'formerSportItem', 'geography', 'grade', 'groupNo', 'history', 'injuryHistory', 'language', 'linealRelativeGeneticDiseases', 'linealRelativeHeightProminent', 'math', 'motherHeight', 'name', 'nation', 'nativePlace', 'period', 'physics', 'politics', 'politicsStatus', 'retire', 'sex', 'sportItem', 'stage', 'supportItem', 'term', 'trainingDate', 'trainingDate1', 'trainingGroupId', 'trainingItem', 'trainingItemNo', 'trainingScore', ))
          // 时间格式化
          this.form.setFieldsValue({ attendTrainTime: this.model.attendTrainTime ? moment(this.model.attendTrainTime) : null })
          this.form.setFieldsValue({ birthday: this.model.birthday ? moment(this.model.birthday) : null })
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
            formData.birthday = formData.birthday?formData.birthday.format():null;
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
