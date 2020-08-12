import Vue from 'vue'
import Vuex from 'vuex'
import router from '@/router'
import http from '@/util/http-common.js'
import createPersistedState from 'vuex-persistedstate';
Vue.use(Vuex)

export default {
  namespaced: true,
  state: {
    uid: '',
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
      const { uid, nickname, email, info } = { uid: payload.uid, nickname: payload.nickname, email: payload.email, info: payload.info }
      commit('SET_STATE', {
        uid,
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
      const { uid, nickname, email, info, password } = { uid: payload.uid, nickname: payload.nickname, email: payload.email, info: payload.info, password: payload.password, }
      commit('SET_STATE', {
        uid,
        nickname,
        email,
        info,
        password,
      })
    },
    LOGOUT({ commit, rootState }) {
        commit('SET_STATE', {
          uid: '',
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
  plugins: [
    createPersistedState()
  ]
}