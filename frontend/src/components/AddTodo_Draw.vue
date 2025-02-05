<template>
  <div class="AddTodo_Draw">
    <el-button text @click="dialog = true"
    >Open Drawer with nested form</el-button
    >
    <el-drawer
        v-model="dialog"
        title="I have a nested form inside!"
        :before-close="handleClose"
        direction="ltr"
        class="demo-drawer"
        size="50%"
    >
      <div class="demo-drawer__content">

        <el-form :model="form" size="large" class="from-body">
          <!-- 标题 -->
          <el-form-item label="Title" :label-width="formLabelWidth">
            <el-input v-model="form.title"
                      autocomplete="off"
                      style="width: 300px"
                      placeholder="Title Here"
            /><el-button  :icon="Star" circle style="margin-left: 10px" id="star-btn" @click="handStared" />
          </el-form-item>
          <!-- 描述 -->
          <el-form-item label="Description" :label-width="formLabelWidth">
            <el-input
                v-model="form.detail"
                style="width: 240px"
                :autosize="{ minRows: 2, maxRows: 4 }"
                type="textarea"
                placeholder="Please input description"
            />
          </el-form-item>

          <!-- 截止日期 -->
          <el-form-item label="Deadline" :label-width="formLabelWidth">
            <DateTime_Selector v-model="form.deadline" />
          </el-form-item>

          <!-- 标签 -->
          <el-form-item label="Tags" :label-width="formLabelWidth">
            <TagEdit_FormItem />
          </el-form-item>
        </el-form>
        <div class="demo-drawer__footer">
          <el-button @click="cancelForm">Cancel</el-button>
          <el-button type="primary" :loading="loading" @click="onClick" v-model="isStared">
            {{ loading ? 'Submitting ...' : 'Submit' }}
          </el-button>
        </div>

      </div>
    </el-drawer>
  </div>

</template>

<script lang="ts" setup>
import { reactive, ref } from 'vue'
import { ElDrawer, ElMessageBox } from 'element-plus'
import DateTime_Selector from './DateTime_Selector.vue'
import TagEdit_FormItem from '@/components/TagEdit_FormItem.vue'
import {Star} from "@element-plus/icons-vue";

const formLabelWidth = '80px'
let timer:any

const dialog = ref(false)
const loading = ref(false)
const isStared = ref(false)


const form = reactive({
    title: '',
    detail: '',
    deadline: '',
    tags: []
})

const handStared = () => {
  isStared.value = !isStared.value
}

const onClick = () => {
  loading.value = true
  setTimeout(() => {
    loading.value = false
    dialog.value = false
  }, 400)
}

const handleClose = (done:any) => {
  if (loading.value) {
    return
  }
  ElMessageBox.confirm('Do you want to submit?')
      .then(() => {
        loading.value = true
        timer = setTimeout(() => {
          done()
          // 动画关闭需要一定的时间
          setTimeout(() => {
            loading.value = false
          }, 400)
        }, 2000)
      })
      .catch(() => {
        // catch error
      })
}

const cancelForm = () => {
  loading.value = false
  dialog.value = false
  clearTimeout(timer)
}
</script>
<style scoped>
  #star-btn .isclicked {
    color: #c7a313;
    background-color: rgba(255, 255, 153, 0.08);
    border-color: #fff554;
  }
  #star-btn:hover {
      color: #f90;
      background-color: rgba(255, 153, 0, 0.08);
      border-color: #f90;
  }
  #star-btn:active {
    color: #ffffff;
    background-color: rgba(253, 164, 4, 0.91);
    border-color: #c7a313;
  }

</style>