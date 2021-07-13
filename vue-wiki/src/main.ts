import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import Antd from 'ant-design-vue';
import 'ant-design-vue/dist/antd.css';
import * as Icon from '@ant-design/icons-vue';
import axios from 'axios';

//axios.defaults.baseURL = process.env.VUE_APP_SERVER;
axios.defaults.baseURL = 'http://localhost:8081';

const app = createApp(App);
app.use(store).use(router)
    .use(Antd)
    .mount('#app');

//全局使用 ant-design 图标库
const icons:any= Icon;
for(const i in icons){
    app.component(i,icons[i]);
}

console.log("环境:",process.env.NODE_ENV);
console.log("当前服务端:",process.env.VUE_APP_SERVER);