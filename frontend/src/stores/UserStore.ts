import { ref, reactive } from 'vue'
import { defineStore } from 'pinia'

export const useAccountStore = defineStore('counter', () => {
    const UserInfo = reactive({
        avatarUrl: '../src/assets/img/TestAvatar.png',
        nickname: 'User0001',
        username: 'TestUser',
        password: '123456',
        uid:'000000',
        email: 'admin@example.com',
        phone: '17878787878',
        gender: 'male',
        signature: 'TestSignatureTestSignatureTestSignatureTestSignatureTestSignature',
    })
    const UserContent=reactive({
        categories:[
            {
                index:1,
                name:'Category01',
            },
            {
                index:2,
                name:'Category02',
            },
            {
                index:3,
                name:'Category03',
            },
        ]
    })
    return { UserInfo ,UserContent }
})
