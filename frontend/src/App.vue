<script setup lang="ts">


  import LoginPage from '@/pages/LoginPage.vue'
  import {RouterView,useRouter} from 'vue-router'
</script>

<template>
  <div class="app">
    <RouterView />
  </div>
</template>

<style scoped>
  .app {
    width: 100%;
    height: 100%;
    background:linear-gradient(to bottom right, rgba(57, 146, 218, 0.8), rgba(222, 252, 137, 0.8));
  }
</style>
