import Vue from 'vue';
import VueRouter from 'vue-router';
import UserLogin from './components/UserLogin';
import index from './components/index';
import userList from './components/UserList'

Vue.use(VueRouter)

const routes = [
    {
        path: "/",
        redirect: '/index'
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
    }
]

export default new VueRouter({
    routes
})