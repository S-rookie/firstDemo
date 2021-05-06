import Vue from 'vue';
import VueRouter from 'vue-router';
import UserLogin from './components/UserLogin';
import index from './components/index';
import userList from './components/UserList'
import myInfo from './components/My';

Vue.use(VueRouter)

const routes = [
    {
        path: "/",
        redirect: '/users'
    },
    {
        path: "/users",
        name: "users",
        component: UserLogin
    },
    {
        path: "/index",
        name: "index",
        component: index
    },
    {
        path: "/userList",
        mame: "userList",
        component : userList
    },
    {
        path: '/my',
        name: 'my',
        component: myInfo
    }
]

const vueRouter = new VueRouter({
    mode: 'hash',
    routes: routes 
  })

//   vueRouter.beforeEach((to, from, next) => {
//     if (to.meta.title) {
//       document.title = to.meta.title
//     }
//     debugger
//     const whiteList = ['/users']// 不重定向白名单
//     const accessToken = sessionStorage.getItem('token') // 获取浏览器缓存的用户信息
//    if (localStorage.userInfo) {
//       if (to.path === '/login') {
//         next({ path: '/' })
//       }
//       if (to.path === '/404' || to.path === '/' || to.path === '/login') {
//         next('/login')
//       } else {
//         next()
//      }
//     } else {
//       if (whiteList.indexOf(to.path) !== -1) {
//         next()
//       } else {
//         next('/users')
//       }
//   }
// })
export default vueRouter