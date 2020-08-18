import Vue from 'vue'
import Vuex from 'vuex'
import user from './user'
import createPersistedState from 'vuex-persistedstate';
Vue.use(Vuex)

export default new Vuex.Store({
    modules: {
        user,
    },
    state: {
        speechRecognition: new webkitSpeechRecognition(),
        audio: new Audio()
    },
    mutations: {},
    actions: {},
    plugins: [
        createPersistedState()
    ]
})