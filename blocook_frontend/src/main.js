/*!

=========================================================
* Vue Argon Design System - v1.1.0
=========================================================

* Product Page: https://www.creative-tim.com/product/argon-design-system
* Copyright 2019 Creative Tim (https://www.creative-tim.com)
* Licensed under MIT (https://github.com/creativetimofficial/argon-design-system/blob/master/LICENSE.md)

* Coded by www.creative-tim.com

=========================================================

* The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

*/
import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from './store'
import Argon from "./plugins/argon-kit";
import './registerServiceWorker';
import BootstrapVue from 'bootstrap-vue';
import 'bootstrap-vue/dist/bootstrap-vue.css';
import VueCarousel from 'vue-carousel';
import { BCarousel } from 'bootstrap-vue';
import Antd from 'ant-design-vue'
import 'ant-design-vue/dist/antd.css'
import firebase from 'firebase'
import CKEditor from 'ckeditor4-vue'
import VueSimpleAccordion from 'vue-simple-accordion';
import 'vue-simple-accordion/dist/vue-simple-accordion.css';

Vue.config.productionTip = false;
Vue.use(Argon);
Vue.use(BootstrapVue);
Vue.use(VueCarousel);
Vue.component('b-carousel', BCarousel);
Vue.use(Antd);
Vue.use(CKEditor);
Vue.use(VueSimpleAccordion);

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
}).$mount("#app");
