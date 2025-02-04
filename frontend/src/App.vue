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
    }
</style>
