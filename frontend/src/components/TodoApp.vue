<script setup lang="ts">
import LoginPage from "@/pages/LoginPage.vue";
import AddTodo_Draw from "@/components/AddTodo_Draw.vue";
</script>

<template class="TodoApp">
  <div class="TodoApp">
      <AddTodo_Draw />
  </div>
</template>

<style scoped>
  .TodoApp {
    min-height:700px;
    height: 100px;
    width: 100%;
  }

</style>