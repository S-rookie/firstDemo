import Vue from 'vue'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import App from './App.vue';
import router from './router';
import axios from 'axios';
// import global from './components/Global' 

Vue.prototype.$axios = axios; 
// axios.defaults.baseURL= 'http://82.157.174.148:8090'
axios.defaults.baseURL= 'http://127.0.0.1:8090'
Vue.prototype.$isLogin = global.isLogin;  // eslint-disable-line no-unused-vars

Vue.use(ElementUI)

Vue.config.productionTip = false

new Vue({
  router,
  render: h => h(App),
}).$mount('#app')
