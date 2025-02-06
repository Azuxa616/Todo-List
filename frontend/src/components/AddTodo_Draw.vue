<template>
  <div class="AddTodo_Draw">
    <el-button text @click="dialog = true"
    >Open Drawer with nested form</el-button
    >
    <el-drawer
        v-model="dialog"
        title="Add a Todo"
        :before-close="handleClose"
        direction="ltr"
        class="demo-drawer"
        size="40%"
    >
      <div class="demo-drawer__content">
        <el-form :model="form" size="large" class="from-body">
          <!-- 标题 -->
          <el-form-item label="Title" :label-width="formLabelWidth">
            <el-input v-model="form.title"
                      autocomplete="off"
                      style="width: 300px"
                      placeholder="Title Here"
            />
            <el-button type="text" >
              <Star_Btn  />
            </el-button>
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

          <!--分类-->
          <el-form-item label="Category" :label-width="formLabelWidth">
            <el-select
                v-model="form.category"
                placeholder="Select"
                size="large"
                clearable
                filterable
                style="width: 240px"
            >
              <el-option
                  v-for="item in categories"
                  :key="item.index"
                  :label="item.name"
                  :value="item.name"
              />
            </el-select>
          </el-form-item>

          <!-- 标签 -->
          <el-form-item label="Tags" :label-width="formLabelWidth">
            <TagEdit_FormItem />
          </el-form-item>
        </el-form>
          <!--按钮-->
        <div class="demo-drawer__footer">
          <el-button type="primary" :loading="loading" @click="onClick">
            {{ loading ? 'Submitting ...' : 'Submit' }}
          </el-button>
          <el-button @click="cancelForm">Cancel</el-button>
        </div>

      </div>
    </el-drawer>
  </div>

</template>

<script lang="ts" setup>
import {reactive, ref, watch} from 'vue'
import { ElDrawer, ElMessageBox } from 'element-plus'
import DateTime_Selector from './DateTime_Selector.vue'
import TagEdit_FormItem from '@/components/TagEdit_FormItem.vue'
import Star_Btn from "@/components/Star_Btn.vue";
import {useAccountStore} from '@/stores/UserStore.ts'
import {storeToRefs} from "pinia";

const userStore = useAccountStore()
const categories =storeToRefs(userStore).UserContent.value.categories

const formLabelWidth = '80px'
let timer:any

const dialog = ref(false)
const loading = ref(false)
// const isStared = ref(false)

//todo:pinia store
const form = reactive({
    title: '',
    detail: '',
    deadline: '',
    tags: [],
    category: '',
    isStared: false,
})


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
        // todo:catch error
      })
}

const cancelForm = () => {
  loading.value = false
  dialog.value = false
  clearTimeout(timer)
}
</script>
<style scoped>

</style>