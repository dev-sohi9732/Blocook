import Vue from 'vue'
import Vuex from 'vuex'
import router from '@/router'
import http from '@/util/http-common.js'
Vue.use(Vuex)

export default {
  namespaced: true,
  state: {
    // speechRecognition: new webkitSpeechRecognition(),
    speechRecognition: (typeof webkitSpeechRecognition === 'function') ? new webkitSpeechRecognition() : null,
  },
  mutations: {  // state 값 변경(setter)
    setOnEnd: function(state, payload) {
        // console.log("setOnEnd 호출  payload: "+payload);
        if(payload == 'start'){
            state.speechRecognition.onend = function() {
                state.speechRecognition.onend = null;
                state.speechRecognition.stop();
				state.speechRecognition.start();
			};   
        } else
            state.speechRecognition.onend = payload;
    },
    setContinuous: function(state, payload){
        // console.log("setContinuous 호출");
        state.speechRecognition.continuous = payload;
    },
    setMaxAlterNatives: function(state, payload){
        // console.log("setMaxAlterNatives 호출");
        state.speechRecognition.maxAlternatives = payload;
    },
    setOnResult: function(state, payload){
        // console.log("setOnResult 호출");
        state.speechRecognition.addEventListener('result', payload);
    },
  },
  actions: {
    
  },
  getters: {
    recognition: state => state,
  }
}