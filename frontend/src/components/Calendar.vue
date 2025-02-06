<template>
  <el-calendar v-model="value"  class="calendar"/>
</template>

<script lang="ts" setup>
import { ref } from 'vue'
const value = ref(new Date())
</script>

<style scoped>
  .calendar {
      background-color: rgba(253, 155, 0, 0);
  }
</style>