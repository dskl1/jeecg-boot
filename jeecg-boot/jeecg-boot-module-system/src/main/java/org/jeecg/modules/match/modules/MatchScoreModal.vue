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
          label="运动员id"
          :hidden="hiding"
          hasFeedback>
          <a-input placeholder="请输入运动员id" v-decorator="['sportsmanId', {'initialValue':this.mainId}]"/>
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="比赛日期"
          :hidden="hiding"
          hasFeedback>
          <a-date-picker v-decorator="[ 'matchDate', {}]"></a-date-picker>
        </a-form-item>

        <a-row>
          <a-col :span="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="比赛项目"
              hasFeedback>
              <j-dict-select-tag
                v-decorator="['matchItem', {}]"
                :triggerChange="true"
                disabled="true"
                placeholder="请输入比赛项目"
                dictCode="match_item"/>
            </a-form-item>
          </a-col>
          <a-col :span="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="成绩"
              hasFeedback>
              <a-input-number placeholder="请输入成绩" v-decorator="[ 'matchScore', {}]"
                              :formatter="matchScore => `${matchScore}`.replace(/\B(?=(\d{2})+(?!\d))/g, ':')"
                              :parser="matchScore => matchScore.replace(/\$\s?|(:*)/g, '')"
              />
            </a-form-item>
          </a-col>
          <a-col :span="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="名次"
              hasFeedback>
              <a-input-number v-decorator="[ 'mingCi', {}]"/>
            </a-form-item>
          </a-col>
        </a-row>
      </a-form>
    </a-spin>
  </a-modal>
</template>

<script>
  import { httpAction } from '@/api/manage'
  import pick from 'lodash.pick'
  import moment from 'moment'
  import JDate from '@/components/jeecg/JDate'
  import { queryallll } from '@/api/api'

  export default {
    components: {
      JDate,
    },
    name: 'MatchScoreModal',
    data () {
      return {
        title: '操作',
        visible: false,
        model: {},
        labelCol: {
          xs: { span: 24 },
          sm: { span: 5 }
        },
        wrapperCol: {
          xs: { span: 24 },
          sm: { span: 16 }
        },
        moment,
        format: 'YYYY-MM-DD HH:mm:ss',
        disableSubmit: false,
        mainId: '',
        hiding: false,
        memberList: [],
        selectedMember: [],
        matchScore: '',
        confirmLoading: false,
        form: this.$form.createForm(this),
        validatorRules: {},
        url: {
          add: '/match/matchTotal/addMatchScore',
          edit: '/match/matchTotal/editMatchScore'
        }
      }
    },
    created () {
    },
    methods: {
      initialMemberList () {
        queryallll().then((res) => {
          if (res.success) {
            this.memberList = res.result
          } else {
            console.log(res.message)
          }
        })
      },
      add (mainId) {
        if (mainId) {
          this.edit({ mainId }, '')
        } else {
          this.$message.warning('请选择一条数据')
        }
      },
      detail (record) {
        this.edit(record, 'd')
      },
      edit (record, v) {
        if (v == 'e') {
          this.hiding = false
          this.disableSubmit = false
        } else if (v == 'd') {
          this.hiding = false
          this.disableSubmit = true
        } else {
          this.hiding = true
          this.disableSubmit = false
        }
        this.initialMemberList()
        this.form.resetFields()
        this.mainId = record.mainId
        this.model = Object.assign({}, record)
        this.visible = true
        this.$nextTick(() => {
          this.form.setFieldsValue(pick(this.model, 'sportsmanId', 'name', 'matchName', 'matchDate', 'distance', 'matchItem', 'matchScore', 'changCi', 'xiangCi', 'daoCi', 'mingCi',))
          // 时间格式化
          this.form.setFieldsValue({ matchDate: this.model.matchDate ? moment(this.model.matchDate) : null })
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
            //时间格式化
            formData.matchDate = formData.matchDate ? formData.matchDate.format() : null
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
      }
    }
  }
</script>

<style scoped>

</style>
