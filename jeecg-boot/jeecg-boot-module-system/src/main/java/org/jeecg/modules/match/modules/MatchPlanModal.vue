<template>
  <a-modal
    :title="title"
    :width="800"
    :visible="visible"
    :confirmLoading="confirmLoading"
    @ok="handleOk"
    @cancel="handleCancel"
    cancelText="关闭">

    <a-spin :spinning="confirmLoading">
      <a-form :form="form">

        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="比赛名称">
          <a-input placeholder="请输入比赛名称" v-decorator="['matchName', validatorRules.matchName]"/>
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="比赛日期">
          <a-date-picker v-decorator="[ 'matchDate', validatorRules.matchDate]"/>
          ~
          <a-date-picker v-decorator="[ 'endDate', validatorRules.endDate]"/>
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="参赛人员">
          <j-multi-select-tag
            v-decorator="['matchItem', validatorRules.matchItem]"
            dictCode="sportsman_registration_entry,name,name,retire != '退队'" placeholder="请选择参赛人员">
          </j-multi-select-tag>
          {{ matchItem }}
        </a-form-item>

      </a-form>
    </a-spin>
  </a-modal>
</template>

<script>
  import { httpAction } from '@/api/manage'
  import pick from 'lodash.pick'
  import moment from 'moment'
  import JMultiSelectTag from '@/components/dict/JMultiSelectTag'
  import { queryMatchMember, queryalll, duplicateCheck } from '../../../api/api'

  export default {
    components: {
      JMultiSelectTag
    },
    name: 'MatchPlanModal',
    data () {
      return {
        sportsmans: {},
        selectedSportsman: [],
        sportsmanDisabled: false, //是否是比赛维护调用该页面
        sportsmanList: [],
        matchId: '', //保存比赛id
        title: '操作',
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
        matchItem: '',
        confirmLoading: false,
        form: this.$form.createForm(this),
        validatorRules: {
          matchName: {
            rules: [{
              required: true, message: '请输入比赛名称!'
            }, {
              validator: this.validateMatchname,
            }]
          },
          matchDate: {
            rules: [{
              required: true, message: '请输入开始日期!'
            }]
          },
          endDate: {
            rules: [{
              required: true, message: '请输入结束日期!'
            }]
          },
          matchItem: {
            rules: [{
              required: true, message: '请选择参赛人员!'
            }]
          },
        },
        url: {
          add: '/match/matchPlan/add',
          edit: '/match/matchPlan/edit',
          matchId: '/match/matchPlan/generateMatchId', // 引入生成添加用户情况下的url
        },
      }
    },
    created () {
    },
    methods: {
      validateMatchname (rule, value, callback) {
        var params = {
          tableName: 'match_plan',
          fieldName: 'match_name',
          fieldVal: value,
          dataId: this.matchId
        }
        duplicateCheck(params).then((res) => {
          if (res.success) {
            callback()
          } else {
            callback('比赛已存在!')
          }
        })
      },
      initialSportsmanList () {
        queryalll().then((res) => {
          if (res.success) {
            this.sportsmanList = res.result
          } else {
            console.log(res.message)
          }
        })
      },
      loadMatchSportsmans (matchid) {
        queryMatchMember({ matchid: matchid }).then((res) => {
          if (res.success) {
            this.selectedSportsman = res.result
          } else {
            console.log(res.message)
          }
        })
      },
      refresh () {
        this.matchId = ''
      },
      getFormFieldValue (field) {
        return this.form.getFieldValue(field)
      },
      add () {
        this.edit({})
        this.refresh()
      },
      edit (record) {
        let that = this
        that.initialSportsmanList()
        this.form.resetFields()
        if (record.hasOwnProperty('id')) {
          that.loadMatchSportsmans(record.id)
          this.picUrl = 'Has no pic url yet'
        }
        that.matchId = record.id
        this.model = Object.assign({}, record)
        this.visible = true
        this.$nextTick(() => {
          this.form.setFieldsValue(pick(this.model, 'sportsmanId', 'matchName', 'distance', 'matchItem', 'roleCode', 'description'))
          //时间格式化
          this.form.setFieldsValue({ matchDate: this.model.matchDate ? moment(this.model.matchDate) : null })
          this.form.setFieldsValue({ endDate: this.model.endDate ? moment(this.model.endDate) : null })
        })

      },
      close () {
        this.$emit('close')
        this.visible = false
        this.selectedSportsman = []
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
            formData.endDate = formData.endDate ? formData.endDate.format() : null
            formData.selectedsportsmans = this.selectedSportsman.length > 0 ? this.selectedSportsman.join(',') : ''

            console.log(formData)
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

    }
  }
</script>

<style lang="less" scoped>

</style>