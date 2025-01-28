<template >
  <div class="myaccount-page">
    <header class="page-header">
      //头像、名称、个签
    </header>
    <div class="page-content">
      <AccountInfo :name="name"
                   :username="username"
                   :headImg="headImg"
                   :gender="gender"
                   :email="email"
                   :phonenumber="phonenumber"
                   :signature="signature"/>
    </div>
</div>
</template>

<script setup lang="ts">
  import AccountInfo from "@/components/AccountInfo.vue";
  import {onMounted, ref} from "vue";
  import request from "@/utils/request.ts";

  let name = ref('');
  let username = ref('');
  let headImg = ref('');
  let email = ref('');
  let phonenumber = ref('');
  let signature = ref('');
  let gender = ref('');
  //todo: 输入验证

  //todo: 按钮实现
  function changeHeadImg(){}
  function save(){}
  function logout(){}
  function changePassword(){}

  onMounted(() => {
    request.get('http://127.0.0.1:4523/m1/5804096-5489112-default/user/1').then(res => {
      name.value = res.data.user_info.name;
      username.value = res.data.account_info.username;
      headImg.value = res.data.user_info.headimg;
      email.value = res.data.user_info.email;
      phonenumber.value = res.data.user_info.phonenumber;
      signature.value = res.data.user_info.signature;
      gender.value = res.data.user_info.gender;
    })
  })
</script>