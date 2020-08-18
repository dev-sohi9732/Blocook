<template>
    <section class="section section-shaped section-lg my-0" style="min-height:656px;">
        <div class="shape shape-style-1 bg-gradient-default">
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
                          style="height:420px;">
                        <template>
                            <div class="text-muted text-center mb-3">
                                <h4 id="signup" style="font-weight:bold;">회원가입</h4>
                            </div>
                        </template>
                        <template id="signup">
                            <!-- 서브1 프로젝트 회원가입 부분을 가져옴 -->
                            <a-form layout="vertical" :form="form" @submit.prevent="handleSubmit">
                                <a-input placeholder="닉네임" v-model="nickname">
                                    <a-icon slot="prefix" type="user" style="color:rgba(0,0,0,.25)" />
                                </a-input>
                                <a-input class="mt-3" placeholder="이메일 주소" v-model="email">
                                    <a-icon slot="prefix" type="mail" style="color:rgba(0,0,0,.25)" />
                                    <a-select slot="addonAfter" style="width: 150px" v-model="emailDomain">
                                    <a-select-option value="ssafy.com">
                                        @ssafy.com
                                    </a-select-option>
                                    <a-select-option value="naver.com">
                                        @naver.com
                                    </a-select-option>
                                    <a-select-option value="gmail.com">
                                        @gmail.com
                                    </a-select-option>
                                    <a-select-option value="daum.net">
                                        @daum.net
                                    </a-select-option>
                                    </a-select>
                                </a-input>
                                <br />
                                <a-input-password class="mt-3" placeholder="비밀번호" v-model="pw">
                                    <a-icon slot="prefix" type="lock" style="color:rgba(0,0,0,.25)" />
                                </a-input-password>
                                <span v-if="isValidPassword" class="color-good">사용 가능한 비밀번호 입니다.</span>
                                <span v-else-if="!pw" class="color-normal">비밀번호를 입력해주세요. (영문+숫자 조합 8~32자)</span>
                                <span v-else class="color-warn">영문+숫자 조합 8~32자로 입력해주세요</span>
                                <a-input-password class="mt-3" placeholder="비밀번호 확인" v-model="pw2" :disabled="isEmptyPassword || !isValidPassword">
                                    <a-icon slot="prefix" type="lock" style="color:rgba(0,0,0,.25)" />
                                </a-input-password>
                                <span v-if="isValidPassword2" class="color-good">비밀번호가 일치합니다.</span>
                                <span v-else-if="!pw2" class="color-normal">비밀번호를 한번 더 입력해주세요.</span>
                                <span v-else class="color-warn">비밀번호가 일치하지 않습니다. 다시 입력해주세요.</span>
                                <a-form-item>
                                    <a-button class="mt-3" html-type="submit" :disabled="hasErrors" block style="color:blue;">작성 완료</a-button>
                                </a-form-item>
                            </a-form>
                        </template>
                    </card>
                </div>
            </div>
        </div>
    </section>
</template>

<script>
import http from "@/util/http-common.js";

export default {
    name: "Signup",
    components: {},
    props: [],
    data() {
        return {
        form: this.$form.createForm(this, { name: "horizontal_login" }),
        nickname: "",
        email: "",
        pw: "",
        pw2: "",
        emailDomain: "ssafy.com",
        profileImage: "",
        };
    },
    mounted() {
    },
    created() {},
    computed: {
        isEmptyPassword: {
            get() {
                return this.pw.length == 0
            },
        },
        isValidPassword: {
            get() {
                return /^[a-zA-Z0-9]{8,32}$/.test(this.pw)
            },
        },
        isValidPassword2: {
            get() {
                return this.pw2 && this.pw === this.pw2
            },
        },
        hasErrors: {
            get() {
                return !this.nickname || !this.email || !this.pw || !this.pw2 || !this.isValidPassword2
            },
        },
    },
    watch: {
    },
    methods: {
        handleSubmit() {
            let newUser = {
                nickname: this.nickname,
                email: `${this.email}@${this.emailDomain}`,
                password: this.pw,
            }
            // 회원가입 요청
            http.post('/account/signup', newUser)
            .then(({ data }) => { // 회원가입 성공
                if (data === "success") {
                    alert('회원가입에 성공하였습니다!')
                    // 이메일 인증 생략 (임시)
                    // this.sendValidEmail(newUser.email);

                    // 로그인 페이지로 이동
                    this.$router.push('login')
                } else {
                    alert('닉네임 또는 이메일이 중복되었거나 회원가입에 문제가 발생하였습니다!')
                }
            })
            .catch((error) => {
                alert('회원가입에 실패하였습니다.')
                console.log(error)
                console.log('REGISTER FAILED..')
            })
            this.clearForm()
        },
        clearForm() {
            this.nickname = ""
            this.email = ""
            this.pw = ""
            this.pw2 = ""
        },
        sendValidEmail(email) {
            // 회원가입 인증 이메일 요청
            http.get(`/account/validation/send/${email}`)
            .then(({ data }) => { // 회원가입 성공
                if (data === "success") {
                alert('회원가입 인증 이메일이 전송되었습니다!')
                } else {
                alert('회원가입 인증 이메일 전송 중 문제가 발생하였습니다.')
                }
            })
            .catch((error) => {
                alert('회원가입 인증 이메일 전송 중 문제가 발생하였습니다.')
                console.log(error)
            })
        },
    },
}
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap');
#signup {
    font-family: 'Noto Sans KR', sans-serif;
}
.color-normal {
  color:rgb(148, 148, 148)
}
.color-good {
  color:rgb(30, 99, 247);
}
.color-warn {
  color:red;
}
</style>
