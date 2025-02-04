<script lang="ts" setup>
import {RouterLink} from 'vue-router';
import {ref, watch, reactive} from 'vue';
import {defineComponent} from 'vue'

defineComponent({name: 'RegisterForm',})

let username = ref('');
let password = ref('');
let confirmPassword = ref('');
let email = ref('');

let usernameError = reactive({
  value: false,
  message: ''
});
let emailError = reactive({
  value: false,
  message: ''
});
let passwordError = reactive({
  value: false,
  message: ''
});
let confirmPasswordError = reactive({
  value: false,
  message: ''
});

//密码正则表达式
const regex_password = /^[a-zA-Z0-9@_]+$/;
const regex_email = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;

//监视用户名输入
watch(username, (newValue: string) => {
  // console.log(newValue.match(regex_password));
  if (newValue.length == 0) {
    usernameError.value = false;
    usernameError.message = '';
  } else if (newValue.length < 5) {
    usernameError.value = true;
    usernameError.message = 'Username must more  than 5 characters!';
  } else if (newValue.length > 15) {
    usernameError.value = true;
    usernameError.message = 'Username must less than 15 characters!';
  } else if (!newValue.match(regex_password)) {
    usernameError.value = true;
    usernameError.message = 'Username can only contain letters, numbers, and special characters (@ and _)';
  } else {
    usernameError.value = false;
    usernameError.message = '';
  }
});
//监视电子邮件输入
watch(email, (newValue: string) => {
  console.log(newValue.match(regex_email));
  if (newValue.length == 0) {
    emailError.value = false;
    emailError.message = '';
  } else if (!newValue.match(regex_email)) {
    emailError.value = true;
    emailError.message = 'Please enter the correct email format!';

  } else {
    emailError.value = false;
    emailError.message = '';
  }
});
//监视密码输入
watch(password, (newValue: string) => {
  if (newValue.length == 0) {
    passwordError.value = false;
    passwordError.message = '';
  } else if (newValue.length < 7) {
    passwordError.value = true;
    passwordError.message = 'Password must more than 7 characters!';
  } else if (newValue.length > 20) {
    passwordError.value = true;
    passwordError.message = 'Password must less than 20 characters!';
  }
});

//监视确认密码输入
watch(confirmPassword, (newValue: string) => {
  if (newValue != password.value) {
    confirmPasswordError.value = true;
    confirmPasswordError.message = 'The two passwords are different!';
  } else {
    confirmPasswordError.value = false;
    confirmPasswordError.message = '';
  }
});

function onSubmit() {
  // TODO: implement login logic here

}

</script>

<template>
  <div class="RegisterForm">
    <div class="title">Register</div>
    <form @submit.prevent="onSubmit">
      <div class="form-group">
        <!-- 用户名输入框-->
        <div id="username-form" class="form">
          <label for="username">Username:</label><br>
          <input id="username" v-model="username" placeholder="Enter your username" required type="text"/>
          <div v-if="usernameError.value" class="error">{{ usernameError.message }}</div>
        </div>
        <!--电子邮件输入框-->
        <div id="email-form" class="form">
          <label for="email">Email:</label><br>
          <input id="email" v-model="email" placeholder="Enter your email" required type="email"/>
          <div v-if="emailError.value" class="error">{{ emailError.message }}</div>
        </div>
        <!--密码输入框 -->
        <div id="password-form" class="form">
          <label for="password">Password:</label><br>
          <input id="password" v-model="password" placeholder="Enter your password" required type="password"/>
          <div v-if="passwordError.value" class="error">{{ passwordError.message }}</div>
        </div>
        <!--确认密码输入框 -->
        <div id="confirm-password-form" class="form">
          <label for="confirm-password">Confirm Password:</label><br>
          <input id="confirm-password" v-model="confirmPassword" placeholder="Confirm your password" required
                 type="password"/>
          <div v-if="confirmPasswordError.value" class="error">{{ confirmPasswordError.message }}</div>
        </div>

      </div>
      <button type="submit">Register</button>
      <div class="signup"> Already have an account?
        <router-link to="/login">Login now</router-link>
      </div>
    </form>
  </div>
</template>

<style scoped>

label {
  font-weight: bold;
  margin-bottom: 10px;
  font-size: 16px;
}

input {
  border: none;
  border-bottom: 1px solid #898989;
  padding: 10px;
  width: 100%;
  transition: color 2s;
  position: relative; /* 为伪元素定位做准备 */

}

.error {
  color: red;
  font-size: 14px;
  margin-top: 5px;
}

input:focus {
  outline: none;
  border-bottom: 2px solid #14a8f1;
}

.title {
  font-size: 30px;
  font-weight: bold;
  text-align: center;
  margin-bottom: 20px;
}

.RegisterForm {
  background: #ffffff;
  border: 1px solid black;
  border-radius: 15px;
  padding: 20px;
  margin: 50px auto;
  width: 500px;
  display: flex;
  flex-direction: column;
  box-shadow: 5px 5px 10px gray;
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