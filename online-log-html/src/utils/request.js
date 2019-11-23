import axios from 'axios'
import {
  Message
} from 'ant-design-vue/lib/message'
import store from '../store'
// import {
//   getToken
// } from '@/utils/auth'

/* 加载动画 start */
//导入
import NProgress from 'nprogress'
import 'nprogress/nprogress.css'
/* 加载动画 end */

const isProduction = process.env.NODE_ENV === 'production';
// 创建axios实例
const service = axios.create({
  baseURL: isProduction ? process.env.VUE_APP_BASE_API : "", // api的base_url
  timeout: 5 * 60 * 1000 // 请求超时时间 ms
})

// 记录请求数，以便等待所有请求完成后，关闭进度条。
var requestNum = 0;

// request拦截器
service.interceptors.request.use(config => {
  /**http请求拦截器, 打开loading start */
  // store.state.loading = true
  if (requestNum == 0) {
    NProgress.start()
  }
  requestNum++
  /**http请求拦截器, 打开loading end */

  if (store.getters.token) {
    // config.headers['Authorization'] = getToken() // 让每个请求携带自定义token 请根据实际情况自行修改
  }
  return config
}, error => {
  // Do something with request error
  console.log(error) // for debug
  Promise.reject(error)
})

// respone拦截器
service.interceptors.response.use(
  response => {
    // console.log(response)
    /**  响应成功关闭loading start */
    // store.state.loading = false
    requestNum--;
    if (requestNum == 0) {
      NProgress.done()
    }
    /**  响应成功关闭loading end */
    /**
     * code为非200是抛错 可结合自己业务进行修改
     */
    const res = response.data
    if (res.code !== 200) {
      console.log(response)
      Message({
        message: res.msg,
        type: 'error',
        duration: 3 * 1000
      })

      // 401:未登录;
      if (res.code === 401 || res.code === 403) {
        store.state.signed = false
        // window.location.href = "http://localhost:8080/#/login"
        // MessageBox.confirm('你已被登出，可以取消继续留在该页面，或者重新登录', '确定登出', {
        //   confirmButtonText: '重新登录',
        //   cancelButtonText: '取消',
        //   type: 'warning'
        // }).then(() => {
        //   store.dispatch('FedLogOut').then(() => {
        //     location.reload() // 为了重新实例化vue-router对象 避免bug
        //   })
        // })
      }
      return Promise.reject('error')
    } else {
      return response.data
    }
  },
  error => {
    console.log('err' + error) // for debug
    // 响应成功关闭loading
    // store.state.loading = false
    NProgress.done()
    Message({
      message: error,
      type: 'error',
      duration: 3 * 1000
    })
    return Promise.reject(error)
  }
)

export default service