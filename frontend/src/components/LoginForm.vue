  <script setup lang="ts">
    import { RouterLink} from 'vue-router';
    import { ref,reactive} from 'vue';
    import {defineComponent} from 'vue'
    import axios from 'axios';

    defineComponent({name: 'LoginForm'})


    let username = ref('');
    let password = ref('');
    let error =reactive({
        value: false,
        message: '',
    });
    let id=ref('');


    function onSubmit() {
      // TODO: implement login logic here
      axios.post('http://127.0.0.1:4523/m1/5804096-5489112-default/Login/', {
        username: username.value,
        password: password.value,
      }).then((response) => {
        console.log(response)
        id=response.data.id;
      })
    }

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
        <button type="submit">Login</button>
        <div class="signup"> Don't have an account? <router-link to="/register">Join us</router-link> </div>
      </form>

      <div >
        {{id}}
      </div>
    </div>
  </template>

  <style scoped>

    label {
      font-weight: bold;
      margin-bottom: 10px;
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