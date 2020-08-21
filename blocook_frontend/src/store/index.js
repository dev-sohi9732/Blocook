import Vue from 'vue'
import Vuex from 'vuex'
import user from './user'
import recognition from './recognition'
import createPersistedState from 'vuex-persistedstate';
Vue.use(Vuex)

export default new Vuex.Store({
    modules: {
        user,
        recognition
    },
    state: {
        audio: new Audio()
    },
    mutations: {},
    actions: {},

    plugins: [
        createPersistedState({
            paths: ['user'],
        })
    ]
})