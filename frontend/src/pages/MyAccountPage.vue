  <template >
  <div class="myaccount-page">
    <div class="page-header">
      <button class="returnBtn" @click="goBack()"><-返回</button>
      <hr>
    </div>
    <div class="page-content">
      <AccountInfoForm/>
    </div>
</div>
</template>

<script setup lang="ts">
  import AccountInfoForm from "@/components/AccountInfo_Form.vue";
  import {onMounted, ref} from "vue";
  import router from "@/router/index.ts";

  onMounted(() => {

  })

  function goBack() {
      router.push('/home');
  }
</script>
<style  scoped>
  .myaccount-page {
    background-color: #ffffff;
    width: 98%;
    margin-left: 1%;
  }
  .page-header {
    height: 50px;
  }
  .returnBtn{
    border: none;
    background-color: #ffffff;
    font-size: 16px;
    color: #333;
    padding: 10px;
    border-radius: 5px;
    cursor: pointer;
  }
</style>