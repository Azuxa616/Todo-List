import {createRouter,createWebHistory} from 'vue-router'
import {unauthorized} from "@/net/index.js";

import LoginPage from "@/pages/LoginPage.vue";
import HomePage from "@/pages/HomePage.vue";
import MyAccountPage from "@/pages/MyAccountPage.vue";
import TodoListApp from "@/pages/TodoList_App.vue";
import RegisterPage from "@/pages/RegisterPage.vue";
import WelcomePage from "@/pages/WelcomePage.vue";
import AboutPage from "@/pages/AboutPage.vue";

const router = createRouter({
    history:createWebHistory(),
    routes:[
        {
            path:'/',
            redirect:'/login'
        },
        {
            name:'login',
            path:'/login',
            component:LoginPage

        },
        {
            name:'register',
            path:'/register',
            component:RegisterPage
        },
        {
            name:'index',
            path:'/home',
            component:HomePage,
            meta: { isAuth: true, title:'主页' },
            children: [
                {
                    name:'welcome',
                    path: '',
                    component:WelcomePage
                },
                {
                    name:'myAccount',
                    path: 'user',
                    component:MyAccountPage
                },
                {
                    name:'todoApp',
                    path: 'todo',
                    component:TodoListApp
                },
                {
                    name:'about',
                    path: 'about',
                    component:AboutPage
                }
                ]
        }
    ]
})
router.beforeEach((to, from, next) => {
    const isUnauthorized = unauthorized()
    console.log("@@@",isUnauthorized);
    if (!isUnauthorized) {
        console.log(1)
        // next()
    } else if (to.fullPath.startsWith('/index') && isUnauthorized) {
        console.log(2)
        // next('/')
    } else {
        console.log(3)
        next()
    }
})

export default router