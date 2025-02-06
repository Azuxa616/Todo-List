<script setup lang="ts">
  //todo:Welcome and Time Here
  import Calendar from '@/components/Calendar.vue'
  import { useAccountStore } from '@/stores/UserStore.ts'
  import { storeToRefs } from 'pinia'
  import {reactive, ref} from 'vue'
  import dayjs from 'dayjs'

  const userStore = useAccountStore()
  const nickname = storeToRefs(userStore).UserInfo.value.nickname
  let DateTimeNow = ref(dayjs())
  const isNight= ref(false)

  setInterval(() => {
    DateTimeNow.value = dayjs()
  },1000)
  const TimeJudge = () => {
    if(DateTimeNow.value.hour() >= 6 && DateTimeNow.value.hour() < 12){
      return 'Morning'
    }else if(DateTimeNow.value.hour() >= 12 && DateTimeNow.value.hour() < 18){
      return 'Afternoon'
    }else{
      isNight.value = true
      return 'Night'
    }
  }
</script>

<template>
  <div class="welcome-page">
    <div class="welcome-row" id="title">Good {{TimeJudge()}}, {{nickname}}!</div>
    <div class="welcome-row" id="time" :class="{'night':isNight}">Now is {{DateTimeNow.format('YYYY-MM-DD HH:mm:ss')}}</div>
    <div class="calendar"><Calendar /></div>
  </div>
</template>

<style scoped>

  .welcome-row{

    text-align: center;
    font-size: 20px;
    font-weight: bold;
    padding: 10px;
    border-radius: 10px;
    width: 50%;
    margin: auto;
  }
  .welcome-row#title{
    font-size: 30px;
  }
  .welcome-row#time{
    background:linear-gradient(to bottom , rgb(0, 230, 238), rgba(246, 239, 200, 0.79));
    color: rgb(250, 115, 7);
  }
  .welcome-row#time.night{

    background:linear-gradient(to bottom , rgba(19, 35, 255, 0.98), rgba(120, 58, 164, 0.48));
    color: #f5e22c;
  }
</style>