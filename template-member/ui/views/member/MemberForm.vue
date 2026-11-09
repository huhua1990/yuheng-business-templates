<template>
  <Dialog :title="dialogTitle" v-model="dialogVisible">
    <el-form
      ref="formRef"
      :model="formData"
      :rules="formRules"
      label-width="100px"
      v-loading="formLoading"
    >
      <el-form-item label="会员昵称" prop="nickname">
        <el-input v-model="formData.nickname" placeholder="请输入会员昵称" />
      </el-form-item>
      <el-form-item label="手机号" prop="mobile">
        <el-input v-model="formData.mobile" placeholder="请输入手机号" />
      </el-form-item>
      <el-form-item label="会员等级" prop="levelId">
        <el-select v-model="formData.levelId" placeholder="请选择会员等级">
          <!-- TODO: 从后端获取等级列表 -->
          <el-option label="普通会员" :value="1" />
          <el-option label="银卡会员" :value="2" />
          <el-option label="金卡会员" :value="3" />
          <el-option label="VIP会员" :value="4" />
        </el-select>
      </el-form-item>
      <el-form-item label="积分" prop="point">
        <el-input-number v-model="formData.point" :min="0" placeholder="请输入积分" />
      </el-form-item>
      <el-form-item label="状态" prop="status">
        <el-radio-group v-model="formData.status">
          <el-radio :label="0">正常</el-radio>
          <el-radio :label="1">禁用</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="生日" prop="birthday">
        <el-date-picker
          v-model="formData.birthday"
          type="date"
          value-format="YYYY-MM-DD"
          placeholder="选择生日"
        />
      </el-form-item>
      <el-form-item label="性别" prop="gender">
        <el-radio-group v-model="formData.gender">
          <el-radio :label="1">男</el-radio>
          <el-radio :label="2">女</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="头像" prop="avatar">
        <el-input v-model="formData.avatar" placeholder="请输入头像URL" />
        <!-- TODO: 集成文件上传组件 -->
        <!-- <UploadImg v-model="formData.avatar" /> -->
      </el-form-item>
    </el-form>
    <template #footer>
      <el-button @click="submitForm" type="primary" :disabled="formLoading">确 定</el-button>
      <el-button @click="dialogVisible = false">取 消</el-button>
    </template>
  </Dialog>
</template>

<script setup lang="ts">
import * as MemberApi from '@/api/demo/member'

const { t } = useI18n()
const message = useMessage()

const dialogVisible = ref(false)
const dialogTitle = ref('')
const formLoading = ref(false)
const formType = ref('')
const formData = ref({
  id: undefined,
  nickname: '',
  mobile: '',
  avatar: '',
  levelId: undefined,
  point: 0,
  status: 0,
  birthday: undefined,
  gender: undefined
})
const formRules = reactive({
  nickname: [{ required: true, message: '会员昵称不能为空', trigger: 'blur' }],
  mobile: [
    { required: true, message: '手机号不能为空', trigger: 'blur' },
    { pattern: /^1[3-9]\d{9}$/, message: '手机号格式不正确', trigger: 'blur' }
  ],
  levelId: [{ required: true, message: '会员等级不能为空', trigger: 'change' }]
})
const formRef = ref()

/** 打开弹窗 */
const open = async (type: string, id?: number) => {
  dialogVisible.value = true
  dialogTitle.value = type === 'create' ? '添加会员' : '修改会员'
  formType.value = type
  resetForm()
  if (id) {
    formLoading.value = true
    try {
      formData.value = await MemberApi.getMember(id)
    } finally {
      formLoading.value = false
    }
  }
}
defineExpose({ open })

/** 提交表单 */
const emit = defineEmits(['success'])
const submitForm = async () => {
  await formRef.value.validate()
  formLoading.value = true
  try {
    const data = formData.value as unknown as MemberApi.MemberVO
    if (formType.value === 'create') {
      await MemberApi.createMember(data)
      message.success(t('common.createSuccess'))
    } else {
      await MemberApi.updateMember(data)
      message.success(t('common.updateSuccess'))
    }
    dialogVisible.value = false
    emit('success')
  } finally {
    formLoading.value = false
  }
}

/** 重置表单 */
const resetForm = () => {
  formData.value = {
    id: undefined,
    nickname: '',
    mobile: '',
    avatar: '',
    levelId: undefined,
    point: 0,
    status: 0,
    birthday: undefined,
    gender: undefined
  }
  formRef.value?.resetFields()
}
</script>
