import Vue from 'vue'
import Vuex from 'vuex'
import recipe from './recipe'
import user from './user'

Vue.use(Vuex)

export default new Vuex.Store({
    modules: {
        recipe,
        user,
    },
    state: {},
    mutations: {},
    actions: {},
})