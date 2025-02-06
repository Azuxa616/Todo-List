<script setup lang="ts">
import { ref, reactive } from 'vue';
import {Star, StarFilled} from "@element-plus/icons-vue";
const isStared = ref(false);
const icon = ref(isStared ? Star : StarFilled);
const handleClick = () => {
  isStared.value = !isStared.value;
  icon.value = isStared.value ? StarFilled :Star ;
  console.log(isStared.value);
}
</script>

<template>
  <el-button  :icon="icon"
              circle
              id="star-btn"
              :class=" {'Stared':isStared }"
              class="star-btn"
              @click="handleClick"
              />
</template>

<style scoped>

 .Stared {
    color: #f5bf11;
    background-color: rgba(234, 204, 49, 0.2);
   border-color: rgb(255, 196, 0);
   }
   .star-btn:hover {
     background-color: rgb(252, 227, 71);
      border-color: rgba(255, 196, 0, 0.25);
     color: rgb(255, 196, 0);
   }
</style>