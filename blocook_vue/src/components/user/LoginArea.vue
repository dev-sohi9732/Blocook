<template>
	<center>
    <div class="user"> 
			<div>
				<div>											
					<div>
						<i class="fa fa-user"></i>
						<input
							id="email"
							type="email"
							v-model="email"
							placeholder="이메일을 입력해주세요" />
					</div>
					<div class="password">
						<i class="fa fa-lock"></i>
						<input
							id="password"
							type="password" 
							v-model="password"
							placeholder="비밀번호를 입력해주세요" />
					</div>
				</div>
				<div class="center">
					<a href="/findidpw">이메일/비밀번호 찾기</a>
				</div>
				<div>
					<button class="button-green btn" @click="login()">
						<span>
							로그인
						</span>
					</button>
				</div>
				<div>
					<a href="/signup">
						<button class="button btn">
							<span>
								회원가입
							</span>
						</button>
					</a>
				</div>
				<div>
					<button class="button-naver btn">
						<div>
							N
						</div>
						<div class="textsize">
							네이버 로그인
						</div>
					</button>
					<button class="button-google btn">
						<i class="fa fa-google"></i>
						<div class="textsize">
							구글 로그인
						</div>
					</button>
				</div>		
			</div>
    </div>
  </center>
</template>

<script>
import http from "@/util/http-common.js";

export default {
	name: "Login",
	components: {},
	data() {
		return {
			email: "",
			password: "",
		};
	},
	mounted() {
	},
	created() {},
	computed: {
	},
	watch: {
	},
	methods: {
		login() {
			if (!this.validateFields()) {
				alert("입력 값을 확인해주세요!")
				return
			}
			// 로그인 요청
			http.post('/account/login', {
				email: this.email,
				password: this.password
			})
			.then(({ data }) => { // 로그인 성공
				console.log(data)
				this.$store.dispatch('user/LOGIN', { payload: data }) // store 반영
				// if (data.valid === 'false') { // 회원가입 이메일 인증이 안된 경우
				// 	alert('회원가입 이메일 인증을 먼저 진행해주세요.')
				// } else {
				// }
				// 메인 페이지로 이동
				this.$router.push('/')
			})
			.catch((error) => {
				alert('로그인에 실패하였습니다.')
				console.log(error)
				console.log('LOGIN FAILED..')
			})
		},
		validateFields() {
			if (this.email == '' || this.password == '') {
				return false;
			}
			return true;
		},
	},
}
</script>

<style scoped>
.textsize {
	font-size: 15px;
}
.center {
	padding-top: 5px;
}
i {
	margin-right: 6px;
}
</style>