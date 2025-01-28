<template>
  <div class="AccountInfo">
    <h1>
      {{name}}
    </h1>
    <!--昵称-->
    <div class="Info-item">
      <label class="label">Name:</label>
      <input type="text" placeholder="请输入昵称" v-model="props.name">
    </div>

    <!--头像-->
    <div class="Info-item">
      <label class="label">HeadImg:</label>
      <img :src="props.headImg" alt="头像">
      <button class="button" @click="changeHeadImg"> 更换头像 </button>
    </div>
    <!--性别-->
    <div class="Info-item">
      <label class="label">Gender:</label>
      <div>
        <input type="radio" name="gender"  value="male"  v-model="props.gender"> 男
        <input type="radio" name="gender"  value="female" v-model="props.gender">  女
      </div>
    </div>
    <!--用户名-->
    <div class="Info-item">
      <label class="label">Username:</label>
      <div class="info_readonly"> {{username}} </div>
    </div>

    <!--邮箱-->
    <div class="Info-item">
      <label class="label">Email:</label>
      <input type="text" placeholder="请输入邮箱" v-model="props.email">
    </div>

    <!--手机号-->
    <div class="Info-item">
      <label class="label">Phone Number:</label>
      <input type="text" placeholder="请输入手机号" v-model=" props.phonenumber">
    </div>

    <!--个性签名-->
    <div class="Info-item">
      <label class="label">Signature:</label>
      <textarea placeholder="请输入个性签名" v-model=" props.signature">  </textarea>
    </div>
    <div class = "button-box">
      <button class="button" @click="save">保存</button>
      <button class="button" @click="logout">退出登录</button>
      <button class="button" @click="changePassword">修改密码</button>
    </div>

  </div>
</template>

<script setup lang="ts">
import {defineComponent} from 'vue'



defineComponent({name: "AccountInfo"})

//todo: 输入验证

//todo: 按钮实现


let props = defineProps(['name','username','email','phonenumber','signature','headImg','gender'])
// console.log(props.name);

function changeHeadImg(){}
function save(){}
function logout(){}
function changePassword(){}

</script>
<style scoped>

</style>