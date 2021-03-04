<template>
  <a-modal
    :title="title"
    :width="800"
    :visible="visible"
    :okButtonProps="{ props: {disabled: disableSubmit} }"
    :confirmLoading="confirmLoading"
    @ok="handleOk"
    @cancel="handleCancel"
    cancelText="关闭">
    <a-spin :spinning="confirmLoading">
      <a-form :form="form">

        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="日期"
          hasFeedback>
          <a-date-picker v-decorator="[ 'testDate', {}]" />
        </a-form-item>

        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="晨脉"
          hasFeedback>
          <a-input placeholder="请输入晨脉" v-decorator="['chenMai', {}]" />
        </a-form-item>

        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="尿蛋白"
          hasFeedback>
          <a-input placeholder="请输入尿蛋白" v-decorator="['niaoDanBai', {}]" />
        </a-form-item>

        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="血压"
          hasFeedback>
          <a-input placeholder="请输入血压" v-decorator="['xueYa', {}]" />
        </a-form-item>

        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="血色素"
          hasFeedback>
          <a-input placeholder="请输入血色素" v-decorator="['xueSeSu', {}]" />
        </a-form-item>

        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="肺活量"
          hasFeedback>
          <a-input placeholder="请输入肺活量" v-decorator="['feiHuoLiang', {}]" />
        </a-form-item>

        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="背力"
          hasFeedback>
          <a-input placeholder="请输入背力" v-decorator="['beiLi', {}]" />
        </a-form-item>

        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="台阶实验"
          hasFeedback>
          <a-input placeholder="请输入台阶实验" v-decorator="['taiJieShiYan', {}]" />
        </a-form-item>

        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="反应时"
          hasFeedback>
          <a-input placeholder="请输入反应时" v-decorator="['fanYingShi', {}]" />
        </a-form-item>

        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="骨龄"
          hasFeedback>
          <a-input placeholder="请输入骨龄" v-decorator="['guLing', {}]" />
        </a-form-item>

        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="预测身高"
          hasFeedback>
          <a-input placeholder="请输入预测身高" v-decorator="['forecastHeight', {}]" />
        </a-form-item>

        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="心理1"
          hasFeedback>
          <a-input placeholder="请输入心理1" v-decorator="['psychology1', {}]" />
        </a-form-item>

        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="心理2"
          hasFeedback>
          <a-input placeholder="请输入心理2" v-decorator="['psychology2', {}]" />
        </a-form-item>

        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="心理3"
          hasFeedback>
          <a-input placeholder="请输入心理3" v-decorator="['psychology3', {}]" />
        </a-form-item>

        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="心理4"
          hasFeedback>
          <a-input placeholder="请输入心理4" v-decorator="['psychology4', {}]" />
        </a-form-item>

        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="发育类型"
          hasFeedback>
          <a-input placeholder="请输入发育类型" v-decorator="['faYuType', {}]" />
        </a-form-item>

        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="月经初潮"
          hasFeedback>
          <a-input placeholder="请输入月经初潮" v-decorator="['yueJingChuChao', {}]" />
        </a-form-item>

        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="乳房"
          hasFeedback>
          <a-input placeholder="请输入乳房" v-decorator="['ruFang', {}]" />
        </a-form-item>

        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="阴毛"
          hasFeedback>
          <a-input placeholder="请输入阴毛" v-decorator="['yinMao', {}]" />
        </a-form-item>

        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="睾丸"
          hasFeedback>
          <a-input placeholder="请输入睾丸" v-decorator="['gaoWan', {}]" />
        </a-form-item>

        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="视力"
          hasFeedback>
          <a-input placeholder="请输入视力" v-decorator="['shiLi', {}]" />
        </a-form-item>

        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="运动员id"
          :hidden="hiding"
          hasFeedback>
          <a-input placeholder="请输入运动员id" v-decorator="['sportsmanId', {'initialValue':this.mainId}]" />
        </a-form-item>
      </a-form>
    </a-spin>
  </a-modal>
</template>

<script>
  import {httpAction} from '@/api/manage'
  import pick from 'lodash.pick'
  import moment from 'moment'
  import JDate from '@/components/jeecg/JDate'

  export default {
    components: {
      JDate
    },
    name: 'DevelopSituationModal',
    data() {
      return {
        title: '操作',
        visible: false,
        model: {},
        labelCol: {
          xs: {span: 24},
          sm: {span: 5}
        },
        wrapperCol: {
          xs: {span: 24},
          sm: {span: 16}
        },
        moment,
        format: 'YYYY-MM-DD HH:mm:ss',
        disableSubmit: false,
        mainId: '',
        hiding: false,
        confirmLoading: false,
        form: this.$form.createForm(this),
        validatorRules: {
        },
        url: {
          add: '/sportsman/sportsmanPersonalArchives/addDevelopSituation',
          edit: '/sportsman/sportsmanPersonalArchives/editDevelopSituation'
        }
      }
    },
    created() {
    },
    methods: {
      add(mainId) {
        if (mainId) {
          this.edit({mainId}, '')
        } else {
          this.$message.warning('请选择一条数据')
        }
      },
      detail(record) {
        this.edit(record, 'd')
      },
      edit(record, v) {
        if (v == 'e') {
          this.hiding = false;
          this.disableSubmit = false;
        } else if (v == 'd') {
          this.hiding = false;
          this.disableSubmit = true;
        } else {
          this.hiding = true;
          this.disableSubmit = false;
        }
        this.form.resetFields();
        this.mainId = record.mainId;
        this.model = Object.assign({}, record);
        this.visible = true;
        this.$nextTick(() => {
          this.form.setFieldsValue(pick(this.model, 'beiLi', 'chenMai', 'faYuType', 'fanYingShi', 'feiHuoLiang', 'forecastHeight', 'gaoWan', 'guLing', 'niaoDanBai', 'psychology1', 'psychology2', 'psychology3', 'psychology4', 'ruFang', 'shiLi', 'sportsmanId', 'taiJieShiYan', 'testDate', 'xueSeSu', 'xueYa', 'yinMao', 'yueJingChuChao', ))
          // 时间格式化
          this.form.setFieldsValue({ testDate: this.model.testDate ? moment(this.model.testDate) : null })
        })
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
            formData.testDate = formData.testDate?formData.testDate.format():null;
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
        this.close();
      }
    }
  }
</script>

<style scoped>

</style>
