import axios from 'axios'

// axios 객체를 생성
export default axios.create({
  baseURL: 'http://i3a505.p.ssafy.io:9999/api',
  headers: {
    // 'Content-type': 'applicatoin/json',
  },
})