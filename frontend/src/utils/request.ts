import axios from "axios";
import {ElMessage} from "element-plus";

let request = axios.create({
    timeout: 5000,
})

request.interceptors.request.use((  config)  => {
    return config;
})

request.interceptors.response.use((response)  => {
    //成功回调
    //TODO: 处理成功的响应数据
    return response.data
},(error)=>{
    //失败回调,处理HTTP错误或超时
    //TODO: 处理失败的响应数据
    let message='';
    let status=error.response.status;
    switch (status) {
        case 401:
            message='TOKEN过期';
            break;
        case 403:
            message='无权限访问';
            break;
        case 404:
            message='请求地址错误';
            break;
        case 500:
            message='服务器内部错误';
            break;
        case 502:
            message='网关错误';
            break;
        case 503:
            message='服务不可用';
            break;
        case 504:
            message='网关超时';
            break;
        default:
            message='请求失败';
            break;
    }
    ElMessage({
        type: 'error',
        message: message,
    })
    return Promise.reject(error);
})
export default request;