import {createRouter,createWebHistory} from 'vue-router'
import {unauthorized} from "@/net/index.js";

import LoginPage from "@/pages/LoginPage.vue";
import HomePage from "@/pages/HomePage.vue";
import MyAccount from "@/pages/MyAccount.vue";
import TodoApp from "@/components/TodoApp.vue";

const router = createRouter({
    history:createWebHistory(),
    routes:[
        {
            path:'/',
            redirect:'/login'
        },
        {
            path:'/login',
            component:LoginPage

        },
        {
            path:'/home',
            component:HomePage,
            meta: { isAuth: true, title:'主页' },
            children: [
                {
                    path: '/myaccount',
                    component:MyAccount
                },
                {
                    path: '/todo',
                    component:TodoApp
                }]
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