<template>
  <a-spin :spinning="confirmLoading">
    <j-form-container :disabled="formDisabled">
      <!-- 主表单区域 -->
      <a-form :form="form" slot="detail">
        <a-row>
          <a-col :span="12">
            <a-form-item label="姓名" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['name']" placeholder="请输入姓名"></a-input>
            </a-form-item>
          </a-col>
          <a-col :span="12">
            <a-form-item label="性别" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['sex']" placeholder="请输入性别"></a-input>
            </a-form-item>
          </a-col>
          <a-col :span="12">
            <a-form-item label="照片" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <j-image-upload v-decorator="['avatar']" placeholder="请输入照片"></j-image-upload>
            </a-form-item>
          </a-col>
        </a-row>
      </a-form>
    </j-form-container>
    <!-- 子表单区域 -->
    <a-tabs v-model="activeKey" @change="handleChangeTabs">
      <a-tab-pane tab="科目" :key="refKeys[0]" :forceRender="true">
        <j-editable-table
          :ref="refKeys[0]"
          :loading="studyPostTable.loading"
          :columns="studyPostTable.columns"
          :dataSource="studyPostTable.dataSource"
          :maxHeight="300"
          :disabled="formDisabled"
          :rowNumber="true"
          :rowSelection="true"
          :actionButton="true">
          <template v-slot:action="props">
            <a @click="handleDelete(props)">删除</a>
          </template>
        </j-editable-table>
      </a-tab-pane>
    </a-tabs>
  </a-spin>
</template>

<script>

import pick from 'lodash.pick'
import { getAction } from '@/api/manage'
import { FormTypes, getRefPromise } from '@/utils/JEditableTableUtil'
import { JEditableTableMixin } from '@/mixins/JEditableTableMixin'
import { validateDuplicateValue } from '@/utils/util'
import JFormContainer from '@/components/jeecg/JFormContainer'
import JImageUpload from '@/components/jeecg/JImageUpload'

export default {
  name: 'StudyScoreForm',
  mixins: [JEditableTableMixin],
  components: {
    JFormContainer,
    JImageUpload
  },
  data() {
    return {
      labelCol: {
        xs: { span: 24 },
        sm: { span: 6 },
      },
      wrapperCol: {
        xs: { span: 24 },
        sm: { span: 16 },
      },
      labelCol2: {
        xs: { span: 24 },
        sm: { span: 3 },
      },
      wrapperCol2: {
        xs: { span: 24 },
        sm: { span: 20 },
      },
      // 新增时子表默认添加几行空数据
      addDefaultRowNum: 1,
      validatorRules: {},
      refKeys: ['studyPost',],
      tableKeys: ['studyPost',],
      activeKey: 'studyPost',
      // 科目
      studyPostTable: {
        loading: false,
        dataSource: [],
        columns: [
          {
            title: '年级',
            key: 'grade',
            type: FormTypes.input,
            width: '200px',
            placeholder: '请输入${title}',
            defaultValue: '',
          },
          {
            title: '学期',
            key: 'term',
            type: FormTypes.select,
            width: '200px',
            placeholder: '请输入${title}',
            defaultValue: '',
            allowInput: true,
            options: [ // 下拉选项
              { title: '上学期', value: '上学期' },
              { title: '下学期', value: '下学期' }
            ],
          },
          {
            title: '语文',
            key: 'chinese',
            type: FormTypes.input,
            width: '200px',
            placeholder: '请输入${title}',
            defaultValue: '',
          },
          {
            title: '数学',
            key: 'math',
            type: FormTypes.input,
            width: '200px',
            placeholder: '请输入${title}',
            defaultValue: '',
          },
          {
            title: '英语',
            key: 'english',
            type: FormTypes.input,
            width: '200px',
            placeholder: '请输入${title}',
            defaultValue: '',
          },
          {
            title: '生物',
            key: 'biology',
            type: FormTypes.input,
            width: '200px',
            placeholder: '请输入${title}',
            defaultValue: '',
          },
          {
            title: '地理',
            key: 'geography',
            type: FormTypes.input,
            width: '200px',
            placeholder: '请输入${title}',
            defaultValue: '',
          },
          {
            title: '化学',
            key: 'chemistry',
            type: FormTypes.input,
            width: '200px',
            placeholder: '请输入${title}',
            defaultValue: '',
          },
          {
            title: '物理',
            key: 'physics',
            type: FormTypes.input,
            width: '200px',
            placeholder: '请输入${title}',
            defaultValue: '',
          },
          {
            title: '政治',
            key: 'politics',
            type: FormTypes.input,
            width: '200px',
            placeholder: '请输入${title}',
            defaultValue: '',
          },
          {
            title: '历史',
            key: 'history',
            type: FormTypes.input,
            width: '200px',
            placeholder: '请输入${title}',
            defaultValue: '',
          },
          {
            title: '操作',
            key: 'action',
            width: '8%',
            type: FormTypes.slot, // 定义该列为 自定义插值列
            slotName: 'action' // slot 的名称，对应 v-slot 冒号后面和等号前面的内容
          }
        ]
      },
      url: {
        add: '/sportsman/studyScore/add',
        edit: '/sportsman/studyScore/edit',
        queryById: '/sportsman/studyScore/queryById',
        studyPost: {
          list: '/sportsman/studyScore/queryStudyPostByMainId'
        },
      }
    }
  },
  props: {
    //流程表单data
    formData: {
      type: Object,
      default: () => {
      },
      required: false
    },
    //表单模式：false流程表单 true普通表单
    formBpm: {
      type: Boolean,
      default: false,
      required: false
    },
    //表单禁用
    disabled: {
      type: Boolean,
      default: false,
      required: false
    }
  },
  computed: {
    formDisabled() {
      if (this.formBpm === true) {
        if (this.formData.disabled === false) {
          return false
        }
        return true
      }
      return this.disabled
    },
    showFlowSubmitButton() {
      if (this.formBpm === true) {
        if (this.formData.disabled === false) {
          return true
        }
      }
      return false
    }
  },
  created() {
    //如果是流程中表单，则需要加载流程表单data
    this.showFlowData()
  },
  methods: {
    /* a 标签的点击事件，删除当前选中的行 */
    handleDelete(props) {
      // 参数解释
      // props.index ：当前行的下标
      // props.text ：当前值，可能是defaultValue定义的值，也可能是从dataSource中取出的值
      // props.rowId ：当前选中行的id，如果是新增行则是临时id
      // props.column ：当前操作的列
      // props.getValue ：这是一个function，执行后可以获取当前行的所有值（禁止在template中使用）
      //                  例：const value = props.getValue()
      // props.target ：触发当前事件的实例，可直接调用该实例内的方法（禁止在template中使用）
      //                  例：target.add()

      // 使用实例：删除当前操作的行
      let { rowId, target } = props
      target.removeRows(rowId)
    },
    addBefore() {
      this.form.resetFields()
      this.studyPostTable.dataSource = []
    },
    getAllTable() {
      let values = this.tableKeys.map(key => getRefPromise(this, key))
      return Promise.all(values)
    },
    /** 调用完edit()方法之后会自动调用此方法 */
    editAfter() {
      let fieldval = pick(this.model, 'name', 'sex', 'avatar')
      this.$nextTick(() => {
        this.form.setFieldsValue(fieldval)
      })
      // 加载子表数据
      if (this.model.id) {
        let params = { id: this.model.id }
        this.requestSubTableData(this.url.studyPost.list, params, this.studyPostTable)
      }
    },
    /** 整理成formData */
    classifyIntoFormData(allValues) {
      let main = Object.assign(this.model, allValues.formValue)
      return {
        ...main, // 展开
        studyPostList: allValues.tablesValue[0].values,
      }
    },
    //渲染流程表单数据
    showFlowData() {
      if (this.formBpm === true) {
        let params = { id: this.formData.dataId }
        getAction(this.url.queryById, params).then((res) => {
          if (res.success) {
            this.edit(res.result)
          }
        })
      }
    },
    validateError(msg) {
      this.$message.error(msg)
    },
    popupCallback(row) {
      this.form.setFieldsValue(pick(row, 'name', 'sex', 'avatar'))
    },

  }
}
</script>

<style scoped>
</style>