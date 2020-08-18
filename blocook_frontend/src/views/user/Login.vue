<template>
    <section class="section-hero section-shaped section-lg my-0">
        <div class="shape shape-style-1 bg-gradient-login">
            <span></span>
            <span></span>
            <span></span>
            <span></span>
            <span></span>
            <span></span>
            <span></span>
            <span></span>
        </div>
        <div class="container pt-lg-md" style="margin-top: 30px;">
            <div class="row justify-content-center">
                <div class="col-lg-5">
                    <card type="secondary" shadow
                          header-classes="bg-white pb-5"
                          body-classes="px-lg-5 py-lg-5"
                          class="border-0"
                          id="logincard">
                        <!-- <template>
                            <div class="text-muted text-center mb-3">
                                <small>간편 로그인하기</small>
                            </div>
                            <div class="btn-wrapper text-center">
                                <base-button type="neutral">
                                    <img slot="icon" src="img/icons/common/naver.svg">
                                    Naver
                                </base-button>

                                <base-button type="neutral">
                                    <img slot="icon" src="img/icons/common/google.svg">
                                    Google
                                </base-button>
                            </div>
                        </template> -->
                        <template>
                            <div class="text-center text-muted mb-4"><span>로그인하기</span></div>
                            <form role="form">
                                <base-input alternative
                                            class="mb-3"
							                v-model="email"
                                            placeholder="이메일을 입력해주세요"
                                            addon-left-icon="ni ni-email-83">
                                </base-input>
                                <base-input alternative
                                            type="password"
							                v-model="password"
                                            placeholder="비밀번호를 입력해주세요"
                                            addon-left-icon="ni ni-lock-circle-open">
                                </base-input>
                                <div class="text-center">
                                    <base-button type="primary" class="my-4" @click="login()">로그인</base-button>
                                </div>
                            </form>
                        </template>
                    </card>
                    <div class="row mt-3">
                        <div class="col-6">
                            <a href="/findidpw" class="text-white">
                                <small style="margin-left:6px;">이메일/비밀번호 찾기</small>
                            </a>
                        </div>
                        <div class="col-6 text-right">
                            <a href="/signup" class="text-white">
                                <small style="margin-right:12px;">회원가입하기</small>
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
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
				this.$router.push('/') // 메인 페이지로 이동
			})
			.catch((error) => {
				alert('잘못된 이메일 또는 비밀번호입니다.')
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

<style>
@media(min-height: 656px) {
    section {
        height: 100vh;
    }
}
.bg-gradient-login {
    background: linear-gradient(87deg, #74c8ff 0, #4d0cff 100%) !important;
}
#logincard {
    height: 90%;
}
@media(max-width: 360px) {
    #logincard {
        height:400px;
    }
}
</style>
