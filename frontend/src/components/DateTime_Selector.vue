<script setup lang="ts">
import { ref } from 'vue'

const DateTime = ref('')
console.log(DateTime.value)

</script>

<template>
  <div class="demo-datetime-picker">

    <div class="block">
<!--      <span class="demonstration">Use value-format</span>-->
<!--      <div class="demonstration">Value：{{ DateTime }}</div>-->
      <el-date-picker
          v-model="DateTime"
          type="datetime"
          placeholder="Pick a Date"
          format="YYYY/MM/DD hh:mm:ss"
          value-format="YYYY-MM-DD h:m:s a"
      />
    </div>

  </div>
</template>

<style scoped>
.demo-datetime-picker {
  display: flex;
  width: 100%;
  padding: 0;
  flex-wrap: wrap;
}
.demo-datetime-picker .block {
  padding: 30px 0;
  text-align: center;
  border-right: solid 1px var(--el-border-color);
  flex: 1;
}
.demo-datetime-picker .block:last-child {
  border-right: none;
}
.demo-datetime-picker .demonstration {
  display: block;
  color: var(--el-text-color-secondary);
  font-size: 14px;
  margin-bottom: 20px;
}
</style>