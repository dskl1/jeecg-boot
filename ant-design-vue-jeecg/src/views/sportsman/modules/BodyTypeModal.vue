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
          label="测试日期"
          hasFeedback>
          <a-date-picker v-decorator="[ 'testDate', {}]" />
        </a-form-item>

        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="身高(cm)"
          hasFeedback>
          <a-input-number v-decorator="[ 'height', {}]" />
        </a-form-item>

        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="体重(kg)"
          hasFeedback>
          <a-input-number v-decorator="[ 'bodyWeight', {}]" />
        </a-form-item>

        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="坐高(cm)"
          hasFeedback>
          <a-input-number v-decorator="[ 'sitHeight', {}]" />
        </a-form-item>

        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="指距(cm)"
          hasFeedback>
          <a-input-number v-decorator="[ 'zhiJu', {}]" />
        </a-form-item>

        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="胸围(cm)"
          hasFeedback>
          <a-input-number v-decorator="[ 'xiongWei', {}]" />
        </a-form-item>

        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="肩宽(cm)"
          hasFeedback>
          <a-input-number v-decorator="[ 'jianKuan', {}]" />
        </a-form-item>

        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="骨盆宽(cm)"
          hasFeedback>
          <a-input-number v-decorator="[ 'guPenKuan', {}]" />
        </a-form-item>

        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="上肢长(cm)"
          hasFeedback>
          <a-input-number v-decorator="[ 'shangZhiChang', {}]" />
        </a-form-item>

        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="下肢长(cm)"
          hasFeedback>
          <a-input-number v-decorator="[ 'xiaZhiChang', {}]" />
        </a-form-item>

        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="小腿围(cm)"
          hasFeedback>
          <a-input-number v-decorator="[ 'smallLeg', {}]" />
        </a-form-item>

        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="大腿围(cm)"
          hasFeedback>
          <a-input-number v-decorator="[ 'bigLeg', {}]" />
        </a-form-item>


        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="腰围(cm)"
          hasFeedback>
          <a-input-number v-decorator="[ 'butt', {}]" />
        </a-form-item>

        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="小腿长(cm)"
          hasFeedback>
          <a-input-number v-decorator="[ 'xiaoTuiChang', {}]" />
        </a-form-item>

        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="腿腱长(cm)"
          hasFeedback>
          <a-input-number v-decorator="[ 'tuiJianChang', {}]" />
        </a-form-item>

        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="踝围(cm)"
          hasFeedback>
          <a-input-number v-decorator="[ 'huaiWei', {}]" />
        </a-form-item>

        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="运动员id"
          :hidden="hiding"
          hasFeedback>
          <a-input placeholder="请输入运动员id" v-decorator="['sportsmanId', {'initialValue':this.mainId}]" />
        </a-form-item>

        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="背"
          hasFeedback>
          <a-input placeholder="请输入背" v-decorator="['back', {}]" />
        </a-form-item>

        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="腹"
          hasFeedback>
          <a-input placeholder="请输入腹" v-decorator="['belly', {}]" />
        </a-form-item>

        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="臂"
          hasFeedback>
          <a-input placeholder="请输入臂" v-decorator="['arm', {}]" />
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
    name: 'BodyTypeModal',
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
          add: '/sportsman/sportsmanPersonalArchives/addBodyType',
          edit: '/sportsman/sportsmanPersonalArchives/editBodyType'
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
          this.form.setFieldsValue(pick(this.model, 'arm', 'back', 'belly', 'bigLeg', 'bodyWeight', 'butt', 'guPenKuan', 'height', 'huaiWei', 'jianKuan', 'shangZhiChang', 'sitHeight', 'smallLeg', 'sportsmanId', 'testDate', 'tuiJianChang', 'xiaZhiChang', 'xiaoTuiChang', 'xiongWei', 'zhiJu', ))
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
