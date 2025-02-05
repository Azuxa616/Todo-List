<script setup lang="ts">
  import {logout} from "@/net/index.ts";
  import router from "@/router";
  import TodoListApp from "@/components/TodoList_App.vue";
  import AsideNavigator_Menu from "@/components/AsideNavigator_Menu.vue";
  function toMyAccount() {
    console.log("111")
    router.push({path : "/home/user"})
  }
  function Logout() {
    logout(()=>{
      router.push({path : "/login"})
    })
  }
</script>

<template>
  <div class="home-page">
    <el-header class="header">
      <div class=" Head_InFo">
        <div class="logo">
          LOGO:MYTODO
        </div>
        <div class="right-box">
          <img class="avatar" src="../assets/img/TestAvatar.png" alt="avatar" />
          <div class="info-show" id="username">
            <span>用户名</span>
            <div class="info-content" id="signature">个性签名</div>
          </div>
          <div class="btn-box">
            <el-button type="primary"  plain @click="toMyAccount()">个人中心</el-button>
            <el-button type="danger" plain>退出登录</el-button>
          </div>
        </div>
      </div>
    </el-header>
    <el-container class="content">
      <el-aside width="auto">
        <div class="aside-box" >
          <AsideNavigator_Menu  />
        </div>
      </el-aside>
      <el-container>
        <el-main class="main">
          <div class="main-box">
            <RouterView />
          </div>
        </el-main>
      </el-container>
    </el-container>
  </div>

</template>

<style scoped>
.header {
  padding: 0;
}
.content {
  height: 92%;
  width: 90%;
  padding-top:3%;
  margin-left: 5%;
}
.main{
  padding: 0;
  background: #ffffff;
  height: 100%;
}
.aside-box {
  height: 100%;
  width: 100%;
  background: rgb(87, 87, 87);
}
.logo {
  font-size: 30px;
  font-weight: bold;
  color: #333333;
  margin-left: 20px;
}
.right-box {
  display: flex;
  align-items: center;
  justify-content: flex-end;
  width: 50%;
  margin-right: 20px;
}
  .el-button+.el-button {
    margin: 5px 0;
    height: 15px;
  }
  el-header {
    position: relative;
    height: 8%;
    background: linear-gradient(to right, #f8f3e5, #f5e9da);
  }

  .Head_InFo {
    display: flex;
    align-items: center;
    justify-content: space-between;
    left:20%;
    background: linear-gradient(to right, #f8f3e5, #f5e9da);
  }

  .avatar{
    width:50px;
    height: 50px;
    border-radius: 50%
  }
  .main-box{
     height: 99%
  }

.info-show {
  padding-left:20px;
  width:20%;
}
.info-show span{
  font-size: 25px;
}
.info-show.info-content{
  font-size: 16px;
}
.btn-box{
  display: flex;
  justify-content: space-around;
  flex-direction: column;
  margin-top: 10px;
  margin-right: 20px;
}
.home-page{
  height: 100%;
}
</style>