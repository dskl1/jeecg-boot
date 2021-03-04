<template>
  <j-modal
    :title="title"
    :width="1200"
    :visible="visible"
    :maskClosable="false"
    :confirmLoading="confirmLoading"
    switchFullscreen
    @ok="handleOk"
    @cancel="handleCancel">
    <a-spin :spinning="confirmLoading">
      <!-- 主表单区域 -->
      <a-form :form="form">
        <a-row>
          <a-col :xs="24" :sm="12">
            <a-form-item label="开票编号" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['invoiceId']" placeholder="请输入开票编号"></a-input>
            </a-form-item>
          </a-col>
          <a-col :xs="24" :sm="12">
            <a-form-item label="开票日期" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <j-date placeholder="请选择开票日期" v-decorator="['invoiceDate']" :trigger-change="true" style="width: 100%"/>
            </a-form-item>
          </a-col>
          <a-col :xs="24" :sm="12">
            <a-form-item label="客户方" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['client']" placeholder="请输入客户方"></a-input>
            </a-form-item>
          </a-col>
          <a-col :xs="24" :sm="12">
            <a-form-item label="分包公司" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['subcontractingCompany']" placeholder="请输入分包公司"></a-input>
            </a-form-item>
          </a-col>

        </a-row>
      </a-form>

      <!-- 子表单区域 -->
      <a-tabs v-model="activeKey" @change="handleChangeTabs">
        <a-tab-pane tab="人员名单" :key="refKeys[0]" :forceRender="true">
          <j-editable-table
            :ref="refKeys[0]"
            :loading="supplierManagementMemberTable.loading"
            :columns="supplierManagementMemberTable.columns"
            :dataSource="supplierManagementMemberTable.dataSource"
            :maxHeight="300"
            :rowNumber="true"
            :rowSelection="true"
            :actionButton="true"/>
        </a-tab-pane>

      </a-tabs>

    </a-spin>
  </j-modal>
</template>

<script>

import pick from 'lodash.pick'
import { FormTypes, getRefPromise } from '@/utils/JEditableTableUtil'
import { JEditableTableMixin } from '@/mixins/JEditableTableMixin'
import { validateDuplicateValue } from '@/utils/util'

export default {
  name: 'SupplierManagementModal',
  mixins: [JEditableTableMixin],
  components: {},
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
      refKeys: ['supplierManagementMember',],
      tableKeys: ['supplierManagementMember',],
      activeKey: 'supplierManagementMember',
      // 人员名单
      supplierManagementMemberTable: {
        loading: false,
        dataSource: [],
        columns: [
          {
            title: '姓名',
            key: 'name',
            type: FormTypes.input,
            width: '200px',
            placeholder: '请输入${title}',
            defaultValue: '',
          },
          {
            title: '结算月份',
            key: 'billingMonth',
            type: FormTypes.input,
            width: '200px',
            placeholder: '请输入${title}',
            defaultValue: '',
          },
          {
            title: '结算日期',
            key: 'invoiceDate',
            type: FormTypes.input,
            width: '200px',
            placeholder: '请输入${title}',
            defaultValue: '',
          },
          {
            title: '开票额',
            key: 'invoiceAmount',
            type: FormTypes.input,
            width: '200px',
            placeholder: '请输入${title}',
            defaultValue: '',
          },
        ]
      },
      url: {
        add: '/salary/supplierManagement/add',
        edit: '/salary/supplierManagement/edit',
        supplierManagementMember: {
          list: '/salary/supplierManagement/querySupplierManagementMemberByMainId'
        },
      }
    }
  },
  methods: {
    getAllTable() {
      let values = this.tableKeys.map(key => getRefPromise(this, key))
      return Promise.all(values)
    },
    /** 调用完edit()方法之后会自动调用此方法 */
    editAfter() {
      let fieldval = pick(this.model, 'invoiceId', 'invoiceDate', 'client', 'subcontractingCompany', 'totalAmount')
      this.$nextTick(() => {
        this.form.setFieldsValue(fieldval)
      })
      // 加载子表数据
      if (this.model.id) {
        let params = { id: this.model.id }
        this.requestSubTableData(this.url.supplierManagementMember.list, params, this.supplierManagementMemberTable)
      }
    },
    /** 整理成formData */
    classifyIntoFormData(allValues) {
      let main = Object.assign(this.model, allValues.formValue)

      return {
        ...main, // 展开
        supplierManagementMemberList: allValues.tablesValue[0].values,
      }
    },
    validateError(msg) {
      this.$message.error(msg)
    },
    popupCallback(row) {
      this.form.setFieldsValue(pick(row, 'invoiceId', 'invoiceDate', 'client', 'subcontractingCompany', 'totalAmount'))
    },

  }
}
</script>

<style scoped>
</style>