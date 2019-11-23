import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'

/** antd start*/
import antd from "ant-design-vue/dist/antd";
import 'ant-design-vue/dist/antd.css';
Vue.use(antd)
/** antd end*/

Vue.config.productionTip = false

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
