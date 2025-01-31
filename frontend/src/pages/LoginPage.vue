<template>
  <div class="login-page">
    <LoginForm />
  </div>
</template>

<script setup lang="ts">
import LoginForm from '@/components/LoginForm.vue'
import router from "@/router/index.ts";
import emitter from "@/utils/emitter.ts";

emitter.on("toHomePage", (data) => {
  router.push("/home");
})
</script>

<style>
.login-page {
  margin-top: 15%;
}
</style>
