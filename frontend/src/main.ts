import 'element-plus/dist/index.css'
import './assets/main.css'
import { createApp } from 'vue'
import { createPinia } from 'pinia'
import router from '@/router/index'
import App from './App.vue'
import emitter from "@/utils/emitter.ts";
import ElementUI from 'element-plus'

declare module "vue" {
    export interface ComponentCustomProperties {
        $Bus: typeof emitter
    }
}

const app = createApp(App)

    app.config.globalProperties.$Bus = emitter
    app.use(createPinia())
    app.use(router)
    app.use(ElementUI)
    app.mount('#app')
