import Vue from 'vue'
import Vuex from 'vuex'
import router from '@/router'
import http from '@/util/http-common.js'

Vue.use(Vuex)

export default {
  namespaced: true,
  state: {
    nickname: '', // 닉네임
    email: '', // 이메일
    info: '',  // 자기소개
    authorized: false, // false is default value (로그인 상태)
  },
  mutations: {
    SET_STATE(state, payload) { // payload 값으로 현재 state를 업데이트 한다.
      Object.assign(state, {
        ...payload,
      })
    },
  },
  actions: {
    // 로그인 성공 후 db 저장된 사용자 정보로 갱신
    LOGIN({ commit, dispatch, rootState }, { payload }) {
      const { nickname, email, info } = { nickname: payload.nickname, email: payload.email, info: payload.info }
      commit('SET_STATE', {
        nickname,
        email,
        info,
        authorized: true,
      })
    //   console.log('LOGIN SUCCESS! => nickname: ' + rootState.user.nickname)
    //   console.log('LOGIN SUCCESS! => email: ' + rootState.user.email)
    //   console.log('LOGIN SUCCESS! => authorized: ' + rootState.user.authorized)
    },
    UPDATE({ commit, dispatch, rootState }, { payload }) {
      const { nickname, email, info, password } = { nickname: payload.nickname, email: payload.email, info: payload.info, password: payload.password, }
      commit('SET_STATE', {
        nickname,
        email,
        info,
        password,
      })
    },
    LOGOUT({ commit, rootState }) {
        commit('SET_STATE', {
            nickname: '',
            email: '',
            info: '',
            authorized: false,
        })
        // router.push('/')
    },
  },
  getters: {
    user: state => state,
  },
}