<template>
  <a-modal
    :title="title"
    :width="1000"
    :visible="visible"
    :confirmLoading="confirmLoading"
    @ok="handleOk"
    @cancel="handleCancel"
    cancelText="关闭">

    <a-spin :spinning="confirmLoading">
      <a-form :form="form">

        <a-row>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="姓名">
              <a-input placeholder="请输入姓名" v-decorator="['name', {}]"/>
            </a-form-item>
          </a-col>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="出生地">
              <a-input placeholder="请输入出生地" v-decorator="['birthplace', {}]"/>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="民族">
              <a-input placeholder="请输入民族" v-decorator="['nation', {}]"/>
            </a-form-item>
          </a-col>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="文化程度">
              <a-input placeholder="请输入文化程度" v-decorator="['educationalLevel', {}]"/>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="籍贯">
              <a-input placeholder="请输入籍贯" v-decorator="['nativePlace', {}]"/>
            </a-form-item>
          </a-col>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="运动项目">
              <a-input placeholder="请输入运动项目" v-decorator="['sportItem', {}]"/>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="入队后拟选专项">
              <a-input placeholder="请输入入队后拟选专项" v-decorator="['afterSportItem', {}]"/>
            </a-form-item>
          </a-col>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="开始训练时间">
              <a-date-picker
                placeholder="请输入开始训练时间"
                style="width:100%"
                v-decorator="[ 'attendTrainTime', {}]"/>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="入队(校)时间">
              <a-date-picker
                placeholder="请输入入队(校)时间"
                style="width:100%"
                v-decorator="[ 'entryTeamDate', {}]"/>
            </a-form-item>
          </a-col>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="入队前专项">
              <a-input placeholder="请输入入队前专项" v-decorator="['formerSportItem', {}]"/>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="父亲身高(CM)">
              <a-input-number placeholder="请输入父亲身高(CM)" style="width:100%" v-decorator="[ 'fatherHeight', {}]"/>
            </a-form-item>
          </a-col>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="母亲身高(CM)">
              <a-input-number placeholder="请输入母亲身高(CM)" style="width:100%" v-decorator="[ 'motherHeight', {}]"/>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="原学校">
              <a-input placeholder="请输入原学校" v-decorator="['formerSchool', {}]"/>
            </a-form-item>
          </a-col>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="原教练">
              <a-input placeholder="请输入原教练" v-decorator="['formerCoach', {}]"/>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="政治面貌">
              <a-input placeholder="请输入政治面貌" v-decorator="['politicsStatus', {}]"/>
            </a-form-item>
          </a-col>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="有何伤病史">
              <a-input placeholder="请输入有何伤病史" v-decorator="['injuryHistory', {}]"/>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="身份证号码">
              <a-input placeholder="请输入身份证号" v-decorator="['cardNumber', {}]"/>
            </a-form-item>
          </a-col>
          <a-col :span="12" :gutter="8">
            <a-form-item
              v-has="'register:yueshanxuan'"
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="单位">
              <j-multi-select-tag
                v-decorator="['sysOrgCode', {}]"
                dictCode="sys_depart,depart_name,org_code,parent_id='b90d0bc1260043d58655f98ac9652bbb'"
                placeholder="请选择单位">
              </j-multi-select-tag>
            </a-form-item>
          </a-col>
          <a-col :span="12" :gutter="8">
            <a-form-item
              v-has="'register:tiyuzhongxin'"
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="单位">
              <j-multi-select-tag
                v-decorator="['sysOrgCode', {}]"
                dictCode="sys_depart,depart_name,org_code,parent_id='177d59c90af84df48209e4762429b9f5'"
                placeholder="请选择单位">
              </j-multi-select-tag>
            </a-form-item>
          </a-col>
          <a-col :span="12" :gutter="8">
            <a-form-item
              v-has="'register:yuanguang'"
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="单位">
              <j-multi-select-tag
                v-decorator="['sysOrgCode', {}]"
                dictCode="sys_depart,depart_name,org_code,parent_id='9c620bbcd074457fa6cbc610fa33b3ef'"
                placeholder="请选择单位">
              </j-multi-select-tag>
            </a-form-item>
          </a-col>
          <a-col :span="12" :gutter="8">
            <a-form-item
              v-has="'register:admin'"
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="单位">
              <!--              <j-multi-select-tag-->
              <!--                v-decorator="['sysOrgCode', {}]"-->
              <!--                dictCode="sys_depart,depart_name,org_code"-->
              <!--                placeholder="请选择单位">-->
              <!--              </j-multi-select-tag>-->
              <j-select-depart v-decorator="['sysOrgCode', {}]" :multi="true" :trigger-change="true"
                               customReturnField="orgCode"/>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol1"
              :wrapperCol="wrapperCol1"
              label="直系亲属中有无遗传疾病">
              <a-input placeholder="请输入直系亲属中有无遗传疾病" v-decorator="['linealRelativeGeneticDiseases', {}]"/>
            </a-form-item>
          </a-col>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol1"
              :wrapperCol="wrapperCol1"
              label="直系亲属中有无突出身高者">
              <a-input placeholder="请输入直系亲属中有无突出身高者" v-decorator="['linealRelativeHeightProminent', {}]"/>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="12" :gutter="8">
            <a-form-item label="照片" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <j-image-upload class="avatar-uploader" text="上传" v-decorator="['avatar', {}]"></j-image-upload>
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
  import Vue from 'vue'
  import { ACCESS_TOKEN } from '@/store/mutation-types'
  import JMultiSelectTag from '@/components/dict/JMultiSelectTag'
  import JTreeSelect from '@/components/jeecg/JTreeSelect'
  import JDictSelectTag from '@/components/dict/JDictSelectTag.vue'
  import JSelectDepart from '@/components/jeecgbiz/JSelectDepart'
  import JTreeDict from '@/components/jeecg/JTreeDict.vue'
  import JCategorySelect from '@/components/jeecg/JCategorySelect'
  import JImageUpload from '@/components/jeecg/JImageUpload'

  export default {
    components: {
      JMultiSelectTag,
      JTreeSelect,
      JDictSelectTag,
      JSelectDepart,
      JTreeDict,
      JCategorySelect,
      JImageUpload
    },
    name: 'SportsmanRegistrationEntryModal',
    data () {
      return {
        title: '操作',
        sysOrgCode: '',
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
        labelCol1: {
          xs: { span: 24 },
          sm: { span: 10 },
        },
        wrapperCol1: {
          xs: { span: 24 },
          sm: { span: 12 },
        },
        uploadLoading: false,
        headers: {},
        confirmLoading: false,
        form: this.$form.createForm(this),
        validatorRules: {
          sysOrgCode: {
            rules: [{
              required: true, message: '请选择哪些单位可见!'
            }]
          },
        },
        url: {
          add: '/sportsman/sportsmanRegistrationEntry/add',
          edit: '/sportsman/sportsmanRegistrationEntry/edit',
          fileUpload: window._CONFIG['domianURL'] + '/sys/common/upload',
          imgerver: window._CONFIG['domianURL'] + '/sys/common/view',
        },
      }
    },
    created () {
      const token = Vue.ls.get(ACCESS_TOKEN)
      this.headers = { 'X-Access-Token': token }

    },
    computed: {
      uploadAction: function () {
        return this.url.fileUpload
      }
    },
    methods: {
      getFormFieldValue (field) {
        return this.form.getFieldValue(field)
      },
      getDepartIdValue () {
        return this.form.getFieldValue('sysOrgCode')
      },
      add () {
        this.edit({})
      },
      edit (record) {
        this.form.resetFields()
        this.model = Object.assign({}, record)
        this.visible = true
        this.$nextTick(() => {
          this.form.setFieldsValue(pick(this.model, 'name', 'sprotsmanId', 'sex', 'avatar', 'sportItem', 'fatherHeight', 'motherHeight', 'linealRelativeHeightProminent', 'linealRelativeGeneticDiseases', 'grade', 'term', 'language', 'math', 'english', 'biology', 'geography', 'chemistry', 'physics', 'politics', 'history', 'nation', 'nativePlace', 'birthplace', 'educationalLevel', 'politicsStatus', 'formerSchool', 'formerCoach', 'formerSportItem', 'afterSportItem', 'injuryHistory', 'cardNumber', 'retire', 'stage', 'supportItem', 'sysOrgCode'))
          this.form.setFieldsValue({ birthday: this.model.birthday ? moment(this.model.birthday) : null })
          this.form.setFieldsValue({ entryTeamDate: this.model.entryTeamDate ? moment(this.model.entryTeamDate) : null })
          this.form.setFieldsValue({ attendTrainTime: this.model.attendTrainTime ? moment(this.model.attendTrainTime) : null })
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
            formData.birthday = formData.birthday ? formData.birthday.format() : null
            formData.entryTeamDate = formData.entryTeamDate ? formData.entryTeamDate.format() : null
            formData.attendTrainTime = formData.attendTrainTime ? formData.attendTrainTime.format() : null

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
      normFile (e) {
        console.log('Upload event:', e)
        if (Array.isArray(e)) {
          return e
        }
        return e && e.fileList
      },
      beforeUpload: function (file) {
        var fileType = file.type
        if (fileType.indexOf('image') < 0) {
          this.$message.warning('请上传图片')
          return false
        }
        //TODO 验证文件大小
      },
      handleChange (info) {
        if (info.file.status === 'uploading') {
          this.uploadLoading = true
          return
        }
        if (info.file.status === 'done') {
          var response = info.file.response
          this.uploadLoading = false
          console.log(response)
          if (response.success) {
            this.model.avatar = response.message
          } else {
            this.$message.warning(response.message)
          }
        }
      },
      getAvatarView () {
        return this.url.imgerver + '/' + this.model.avatar
      },
    }
  }
</script>

<style scoped>

</style>