
import { ElMessage } from "element-plus";
import axios from "axios";

const authItemName = 'access_token'

const defaultFailure = (message: string, code: number, url: string) => {
    console.warn(`请求地址: ${url}, 状态码: ${code}, 错误信息: ${message}`)
    ElMessage.warning(message)
}

const defaultError = (err:any) => {
    console.error(err)
    ElMessage.warning('发生了一些错误，请联系管理员')
}
//一次封装axios
function internalGet(url:string,header:object,success:any,failure:any,error=defaultError) {
    axios.get(url,{headers:header}).then(({data}) => {
        if(data.code === 200) {
            success(data.data)
        }else{
            failure(data.message, data.code, url)
        }
    }).catch((err => error(err)))
}
function internalPost(url:string,data:object,header:object,success:any,failure:any,error=defaultError) {
    console.log(data)
    axios.post(url,data,{headers:header}).then(({data}) => {
        console.log(data)
        if(data.code === 200) {
            success(data.data)
        }else{
            failure(data.message,data.code,url)
        }
    }).catch((err => error(err)))
}
//二次封装axios

function get(url:string, success:any, failure = defaultFailure) {
    internalGet(url, accessHeader(), success, failure)
}

function post(url:string, data:object, success:any, failure = defaultFailure) {
    internalPost(url, data, accessHeader(), success, failure)
}

function login(username:string,password:string,remember:boolean,success:any,failure=defaultFailure) {
    internalPost('http://localhost:8081/api/user/auth/login', {
        name: username,
        password: password
    }, {
        'Content-Type': 'application/json'
    }, (response:any) => {
        storeAccessToken(response.token, remember, response.expire)
        ElMessage.success(`登录成功，欢迎 ${response.username} 来到我们的系统`)
        console.log("@@",response)
        success(response)
    }, failure)
}
function logout(success:any, failure = defaultFailure) {
    get('http://localhost:8081/api/user/logout', () => {
        deleteAccessToken()
        ElMessage.success('退出登录成功，欢迎您再次使用')
        success()
    }, failure)
}

//token处理
function storeAccessToken(token:object, remember:boolean, expire:number) {
    const authObj = { token: token, expire: expire }
    const str = JSON.stringify(authObj)
    if (remember)
        localStorage.setItem(authItemName, str)
    else
        sessionStorage.setItem(authItemName, str)
}

function takeAccessToken() {
    const str = localStorage.getItem(authItemName) || sessionStorage.getItem(authItemName)
    if (!str) return null
    const authObj = JSON.parse(str)
    if (authObj.expire <= new Date()) {
        deleteAccessToken()
        ElMessage.warning('登录已过期，请重新登录')
        return null
    }
    return authObj.token
}

function accessHeader() {
    const token = takeAccessToken()
    return token ? {'Authorization': `Bearer: ${takeAccessToken()}`} : {}
}

function deleteAccessToken() {
    localStorage.removeItem(authItemName)
    sessionStorage.removeItem(authItemName)
}

function unauthorized() {
    return !takeAccessToken()
}
export { login ,logout ,unauthorized}