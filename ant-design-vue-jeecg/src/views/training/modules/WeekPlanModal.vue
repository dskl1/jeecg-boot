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
        <a-row>
          <a-col :span="12">
            <a-form-item label="训练时间" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <j-dict-select-tag v-decorator="['trainingDate1']" placeholder="请输入训练时间"
                                 :triggerChange="true"
                                 dictCode="training_date"></j-dict-select-tag>
            </a-form-item>
          </a-col>
          <a-col :span="12">
            <a-form-item label="时间段" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <j-dict-select-tag v-decorator="['period']" placeholder="请输入时间段" :triggerChange="true"
                                 dictCode="period"></j-dict-select-tag>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="12">
            <a-form-item label="训练小组" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <j-dict-select-tag v-decorator="['trainingGroupId']" :triggerChange="true"
                                 placeholder="请输入训练小组"
                                 dictCode="sportsman_group"></j-dict-select-tag>
            </a-form-item>
          </a-col>
          <a-col :span="12">
            <a-form-item label="距离" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <j-dict-select-tag v-decorator="['distance']" :triggerChange="true"
                                 placeholder="请输入距离"
                                 dictCode="swim_distance"></j-dict-select-tag>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="12">
            <a-form-item label="组数" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input-number style="width: 100%" v-decorator="['groupNo']" placeholder="请输入组数"></a-input-number>
            </a-form-item>
          </a-col>
          <a-col :span="12">
            <a-form-item label="训练序号" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input-number style="width: 100%" v-decorator="['trainingItemNo']" placeholder="请输入训练序号"></a-input-number>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="12">
            <a-form-item label="训练项目" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <j-dict-select-tag v-decorator="['trainingItem']" :triggerChange="true"
                                 placeholder="请输入训练项目"
                                 dictCode="swim_event"></j-dict-select-tag>
            </a-form-item>
          </a-col>
          <a-col :span="12">
            <a-form-item label="是否高原" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <j-dict-select-tag v-decorator="['isHighland',{'initialValue':'否'}]"
                                 :triggerChange="true"
                                 placeholder="请输入是否高原"
                                 dictCode="ishighland"></j-dict-select-tag>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="12">
            <a-form-item label="趟数" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input-number style="width: 100%" v-decorator="['tangShu']" placeholder="请输入趟数"/>
            </a-form-item>
          </a-col>
          <a-col :span="12">
            <a-form-item label="包干时间" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input-number style="width: 100%" v-decorator="['baoGan']" placeholder="请输入包干时间"/>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="12">
            <a-form-item label="教练" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['coachName']" placeholder="请输入教练"></a-input>
            </a-form-item>
          </a-col>
          <a-col :span="12">
            <a-form-item label="辅助道具" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <j-dict-select-tag v-decorator="['supportItem']" :triggerChange="true"
                                 placeholder="请输入辅助道具"
                                 dictCode="support_item"></j-dict-select-tag>
            </a-form-item>
          </a-col>
        </a-row>

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
    name: 'WeekPlanModal',
    components: {
      JDate,
    },
    data () {
      return {
        form: this.$form.createForm(this),
        title: '操作',
        width: 800,
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
        validatorRules: {},
        url: {
          add: '/training/weekPlan/add',
          edit: '/training/weekPlan/edit',
        }

      }
    },
    created () {
    },
    methods: {
      add () {
        this.edit({})
      },
      edit (record) {
        this.form.resetFields()
        this.model = Object.assign({}, record)
        this.visible = true
        this.$nextTick(() => {
          this.form.setFieldsValue(pick(this.model, 'trainingDate', 'trainingDate1', 'period', 'trainingGroupId', 'trainingItemNo', 'trainingItem', 'distance', 'groupNo', 'isHighland', 'coachName', 'baoGan', 'tangShu', 'supportItem'))
        })
      },
      close () {
        this.$emit('close')
        this.visible = false
      },
      handleOk () {
        const that = this
        // 触发表单验证
        this.form.validateFields((err, values) => {
          if (!err) {
            that.confirmLoading = true
            let httpurl = ''
            let method = ''
            if (!this.model.id) {
              httpurl += this.url.add
              method = 'post'
            } else {
              httpurl += this.url.edit
              method = 'put'
            }
            let formData = Object.assign(this.model, values)
            console.log('表单提交数据', formData)
            httpAction(httpurl, formData, method).then((res) => {
              if (res.success) {
                that.$message.success(res.message)
                that.$emit('ok')
              } else {
                that.$message.warning(res.message)
              }
            }).finally(() => {
              that.confirmLoading = false
              that.close()
            })
          }

        })
      },
      handleCancel () {
        this.close()
      },
      popupCallback (row) {
        this.form.setFieldsValue(pick(row, 'trainingDate', 'trainingDate1', 'period', 'trainingGroupId', 'trainingItemNo', 'trainingItem', 'distance', 'groupNo', 'isHighland', 'coachName', 'baoGan', 'tangShu', 'supportItem'))
      },

    }
  }
</script>