<script setup lang="ts">
  import router from "@/router";
  import AsideNavigator_Menu from "@/components/AsideNavigator_Menu.vue";
  import { useAccountStore } from "@/stores/UserStore.ts";

</script>

<template>
  <div class="home-page">
    <el-header class="header">
      <div class=" Head_InFo">
        <button class="logo" @click="router.push('/home')">
          LOGO:MYTODOLIST
        </button>
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
  padding-top:1%;
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
  cursor: pointer;
  background: none ;
  border: none;
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

  .main-box{
     height: 99%
  }

.info-show span{
  font-size: 25px;
}

.home-page{
  height: 100%;
}
</style>