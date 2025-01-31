  <script setup lang="ts">
    import { RouterLink} from 'vue-router';
    import { ref,reactive} from 'vue';
    import {defineComponent} from 'vue'
    import {login} from '@/net/index.ts'
    import router from '../router/index';

    defineComponent({name: 'LoginForm'})


    let username = ref('');
    let password = ref('');
    let remember = ref(false);
    let error =reactive({
        value: false,
        message: '',
    });
    let id=ref('');


    function onSubmit() {
      login(username.value, password.value, remember.value, () => {
        router.push({path:'/home'})
      })
    }
      // router.push({path:'/home'})

  </script>

  <template>
    <div class="LoginForm">
      <div class="title">Login</div>
      <form @submit.prevent="onSubmit">
        <div class="form-group">
          <div class="form">
            <label for="username">Username:</label><br>
            <input type="text" id="username" v-model="username" required placeholder="Enter your username" />
          </div>

          <div class="form">
            <label for="password">Password:</label><br>
            <input type="password" id="password" v-model="password" required placeholder="Enter your password"/>
          </div>

        </div>

        <div class="error-info" v-if="error.value"> {{ error.message }} </div>
        <div class="checkbox-group">
          <label for="remember">Remember me</label>
          <input type="checkbox" v-model="remember" id="remember" />
        </div>
        <button type="submit">Login</button>
        <div class="signup"> Don't have an account? <router-link to="/register">Join us</router-link> </div>
      </form>

    </div>
  </template>
  <style scoped>
    #remember {
      width: 15px;
      height: 15px;
      margin-left :10px
    }
    .checkbox-group {
      display: flex;
      justify-content: flex-start;
      align-items: center;

    }
    label {
      font-weight: bold;
      margin-bottom: 10px;
      margin-top: 5px;
      font-size: 16px;
    }
    input{
      border: none;
      border-bottom: 1px solid #898989;
      padding: 10px;
      width: 100%;
      transition:color 2s;
      position: relative;/* 为伪元素定位做准备 */

    }
    input:focus{
      outline: none;
      border-bottom: 2px solid #14a8f1;
    }
    .title {
      font-size: 30px;
      font-weight: bold;
      text-align: center;
      margin-bottom: 20px;
    }
    .LoginForm {
      background: #ffffff;
      border : 1px solid black;
      border-radius: 15px;
      padding: 20px;
      margin: 50px auto;
      width: 400px;
      display: flex;
      flex-direction: column;
      box-shadow: 5px  5px 10px gray;
    }
    button {
      margin: 20px auto;
      background-color: #14a8f1;
      color: #ffffff;
      border: none;
      border-radius: 5px;
      padding: 10px 20px;
      font-size: 16px;
      cursor: pointer;
      transition: all 0.3s ease-in-out;
      width: 100%;
    }
    button:hover {
      background-color: #8ecbec;
      cursor: pointer;
    }
    .signup {
      font-size: 18px;
      text-align: left;
    }
  </style>