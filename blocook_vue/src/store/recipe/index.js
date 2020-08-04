import Vue from 'vue'
import Vuex from 'vuex'
import router from '@/router'
import http from '@/util/http-common.js'

Vue.use(Vuex)

export default {
  namespaced: true,
  state: {
    addRecipeId: '', // 등록 레시피 아이디
  },
  mutations: {
    SET_STATE(state, payload) { // payload 값으로 현재 state를 업데이트 한다.
      Object.assign(state, {
        ...payload,
      })
    },
  },
  actions: {
    // 레시피 기본 정보 등록 후 레시피 아이디 저장
    GET_RECIPE_ID({ commit, dispatch, rootState }, { payload }) {
      const addRecipeId = payload;
      commit('SET_STATE', {
        addRecipeId
      })
    },
  },
  getters: {
    recipe: state => state,
  },
}