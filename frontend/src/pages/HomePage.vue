<script setup lang="ts">
  import {logout} from "@/net/index.ts";
  import router from "@/router";
  function toMyAccount() {
    console.log("111")
    router.push({path : "/myaccount"})
  }
  function Logout() {
    logout(()=>{
      router.push({path : "/login"})
    })
  }
</script>

<template>
  <div class="header">
    <div class=" Head_InFo">
      <img class="avatar" src="../assets/img/TestAvatar.png" alt="avatar" />
      <div class="info-show" id="username">
        <span>用户名</span>
        <div class="info-content" id="signature">个性签名</div>
      </div>

      <button class="toMyAccount" id="toMyAccount" @click="toMyAccount()"> 我的账户 </button>
    </div>

  </div>
  <div class="main">
    <div class="content-show">
      <RouterView />
    </div>
  </div>
</template>

<style scoped>

  .header {
    position: relative;
    height: 20%;
    display: flex;
    justify-content: center;
    align-items: center;
  }
  .Head_InFo {
    position: absolute;
    display: flex;
    align-items: center;
    justify-content: center;
    bottom: 10px;
    left:20%;
    width: 60%;
    margin-bottom: 10px;
  }

  .avatar{
    width:70px;
    height: 70px;
    border-radius: 50%
  }
  .main {
     height: 80%;

  }
  .content-show {
    width: 80%;
    height: 95%;
    margin: 0 auto;
    background: #ffffff;
    padding-top: 20px;
    border-radius: 5%;
  }
.info-show {
  padding-left:20px;
  width:90%;
}
.info-show span{
  font-size: 25px;
}
.info-show.info-content{
  font-size: 16px;
}
.toMyAccount {
  background-color: #3572fa;
  color: #fff;
  border: none;
  width: 100px;
  height: 40px;
}

</style>