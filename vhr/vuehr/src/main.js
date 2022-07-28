import Vue from 'vue'
import App from './App.vue'
import router from './router'

// Vuex
import store from './store'

import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'

Vue.config.productionTip = false
Vue.use(ElementUI)



import { postRequest } from '@/utils/api'
import { getRequest } from '@/utils/api'
import { postKeyValueRequest } from '@/utils/api'
import { putRequest } from '@/utils/api'
import { deleteRequest } from '@/utils/api'

import {initMenu} from '@/utils/menus'

// 引入 font-awesome图库
import 'font-awesome/css/font-awesome.min.css'

Vue.prototype.postRequest = postRequest
Vue.prototype.getRequest = getRequest
Vue.prototype.postKeyValueRequest = postKeyValueRequest
Vue.prototype.putRequest = putRequest
Vue.prototype.deleteRequest = deleteRequest


router.beforeEach(((to, from, next) => {
    // console.log(to)
    // console.log(from)

    if(to.path=='/'){
        next();
    }else {
        // 判断有没有登陆
        if(window.sessionStorage.getItem("user")){
            initMenu(router,store);
            next();
        }else {
            // console.log(to)
            next('/?redirect='+to.path)
        }

    }

}))

new Vue({
    router,
    store,
    render: h => h(App),
}).$mount('#app')
