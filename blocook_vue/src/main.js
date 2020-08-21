import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import Antd from 'ant-design-vue'
import 'ant-design-vue/dist/antd.css'
import BootstrapVue from 'bootstrap-vue'
import firebase from 'firebase'

import './assets/css/bootstrap.min.css'
import './assets/css/font-awesome.min.css'
import './assets/css/linearicons.css'
import './assets/css/magnific-popup.css'
import './assets/css/owl.carousel.min.css'
import './assets/css/responsive.css'
import './assets/css/style.css'

import { library } from '@fortawesome/fontawesome-svg-core'
import { faUserSecret } from '@fortawesome/free-solid-svg-icons'
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome'
import * as TastyBurgerButton from 'vue-tasty-burgers'
import CKEditor from 'ckeditor4-vue'
Vue.use(CKEditor);
Vue.use(Antd);
Vue.use(BootstrapVue);
Vue.use(TastyBurgerButton);
library.add(faUserSecret);
 
Vue.component('font-awesome-icon', FontAwesomeIcon)

Vue.config.productionTip = false

firebase.initializeApp({
  apiKey: "AIzaSyAL5ke3mfrEBah9-l38Q88HlGmFZJpgW1c",
  authDomain: "blocook-upload.firebaseapp.com",
  databaseURL: "https://blocook-upload.firebaseio.com",
  projectId: "blocook-upload",
  storageBucket: "blocook-upload.appspot.com",
  messagingSenderId: "1075648850115",
  appId: "1:1075648850115:web:0eb4a7b97cc5c9bc151a76"
})

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
