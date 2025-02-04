<template>
  <div class="AccountInfo">
  <el-form :model="form" label-width="auto" style="max-width: 600px">

    <el-form-item  id="account-info" >
      <!--头像-->
      <img class="avatar" :src="form.avatarUrl" alt="avatar" />

      <div class="info-show-box">
        <el-form-item id="show-username" >
            <span class="username">{{form.username}}</span>
        </el-form-item>
        <!--账号id-->
        <el-form-item id="show-uid" ><span class="uid" >UID:{{form.uid}}</span> </el-form-item>
      </div>
      <!--用户名-->

    </el-form-item>
<!--昵称-->
    <el-form-item label="Nickname:">
      <el-input v-model="form.nickname" />
    </el-form-item>
<!--邮箱-->
    <el-form-item label="Email:">
      <el-input v-model="form.email" />
    </el-form-item>

<!--    电话号码-->
    <el-form-item label="Phone Number:">
      <el-input v-model="form.phonenumber" />
    </el-form-item>
<!--    性别-->
    <el-form-item label="Gender:">
      <el-radio-group v-model="form.gender">
        <el-radio-button value="Male">Male</el-radio-button>
        <el-radio-button value="Female">Female</el-radio-button>
      </el-radio-group>
    </el-form-item>
<!--    个签-->
    <el-form-item label="Signature">
      <el-input v-model="form.signature" type="textarea" />
    </el-form-item>

    <div>
      <div>
        <el-button id="save-btn" type="primary" @click="onSubmit">Save</el-button>
      </div>
      <div class="Btn-group">
        <el-button @click="onCancel" id="cancel-btn">Cancel</el-button>
        <el-button @click="onChangePassword" id="change-password-btn">Change Password</el-button>
      </div>

    </div>
  </el-form>
  </div>
</template>

<script lang="ts" setup>
import { reactive } from 'vue'
import router from '@/router/index.ts'
// do not use same name with ref
const form = reactive({
  avatarUrl: '../src/assets/img/TestAvatar.png',
  nickname: '',
  username: 'TestUser',
  uid:'000000',
  email: '',
  phonenumber: '',
  gender: '',
  signature: '',
})

const onSubmit = () => {
  console.log('submit!')
}
const onCancel = () => {
  router.go(0)
}
const onChangePassword = () => {

}
</script>
<style scoped>
.Btn-group {
  display:flex;
  justify-content: space-evenly;
  margin-top: 20px;
}

#save-btn{
  width: 80%;
  margin-left: 10%;
}
.info-show-box {
  display: flex;
  flex-direction: column;
  margin-top: 20px;
}
.AccountInfo {
  contain: layout;
  height: 100%;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
}
.avatar {
  width: 100px;
  height: 100px;
  border-radius: 50%;
  margin-top: 20px;
  margin-bottom: 20px;
}
.username {
  font-size: 24px;
  font-weight: bold;
  color: #303133;
  margin-left: 20px
}
.uid{
  margin-left: 20px
}

</style>