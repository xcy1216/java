import VueRouter from 'vue-router'

//引入组件
import Index from '../pages'
import homeList from '../pages/home/list'
import homeAdd from '../pages/home/add'
import homeDetail from '../pages/home/detail'
import chat from '../pages/chat/chat'
import aboutUs from '../pages/aboutus/aboutus'
import Login from '../pages/login/login'
import Register from '../pages/register/register'
import Center from '../pages/center/center'
import Messages from '../pages/messages/list'
//  import identify from '../pages/identify/identify'
import marketList from '../pages/market/list'
import marketDetail from '../pages/market/detail'
import marketAdd from '../pages/market/add'
import knowledgeList from '../pages/knowledge/list'
import knowledgeDetail from '../pages/knowledge/detail'
import knowledgeAdd from '../pages/knowledge/add'
import Storeup from '../pages/storeup/list'


const originalPush = VueRouter.prototype.push
VueRouter.prototype.push = function push(location) {
    return originalPush.call(this, location).catch(err => err)
}

//配置路由
export default new VueRouter({
    routes: [{
            path: '/',
            redirect: '/index/fruit'
        },
        {
            path: '/index',
            component: Index,
            children: [
                // 主页
                {
                    path: 'fruit',
                    component: homeList
                },
                {
                    path: 'fruitDetail',
                    component: homeDetail
                },
                {
                    path: 'fruitAdd',
                    component: homeAdd
                },
                // 商店
                {
                    path: 'supermarket',
                    component: marketList
                },
                {
                    path: 'supermarketDetail',
                    component: marketDetail
                },
                {
                    path: 'supermarketAdd',
                    component: marketAdd
                },
                // 知识
                {
                    path: 'knowledge',
                    component: knowledgeList
                },
                {
                    path: 'knowledgeDetail',
                    component: knowledgeDetail
                },
                {
                    path: 'knowledgeAdd',
                    component: knowledgeAdd
                },
                // 聊天
                {
                    path: 'chat',
                    component: chat
                },
                // 识别
/*                 {
                    path: 'identify',
                    component: identify
                }, */
                // 留言
                {
                    path: 'messages',
                    component: Messages
                },
                // 关于我们
                {
                    path: 'aboutus',
                    component: aboutUs
                },
                // 个人中心
                {
                    path: 'center',
                    component: Center,
                },
                {
                    path: 'storeup',
                    component: Storeup
                }

            ]
        },
        {
            path: '/login',
            component: Login
        },
        {
            path: '/register',
            component: Register
        },
    ]
})