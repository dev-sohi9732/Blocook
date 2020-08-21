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
                          style="height:520px;">
                        <template>
                            <div class="text-muted text-center mb-3">
                                <h4 style="font-weight:bold;">이메일/비밀번호 찾기</h4>
                            </div>
                        </template>
                        <template class="accordion">
														<vsa-list>
															<vsa-item>
																<vsa-heading>
																	Email 찾기
																</vsa-heading>
																<vsa-content>
																	<center>
																		<p style="margin-top:5px;margin-bottom:15px;font-weight:bold; font-size:0.7rem; color:gray;">이메일은 가입하신 닉네임과 비밀번호를 통해 찾을 수 있습니다.</p>
																		<div>
																		<i class="fa fa-user" style="margin-right:6px;"></i>
																			<input
																					v-model="nicknameE"
																					id="nicknameE"
																					placeholder="닉네임을 입력해주세요" type="text"/>
																		</div>
																		<div style="margin-top:5px;">
																		<i class="fa fa-lock" style="margin-right:6px;"></i>
																			<input
																					v-model="password"
																					id="password"
																					type="password" 
																					placeholder="비밀번호를 입력해주세요"/>
																		</div>
																		<button class="button btn" style="margin-bottom:10px;background-color:rgb(255, 204, 0);" @click="checkEmailHandler">
																			<span>찾기</span>
																		</button>
																	</center>
																</vsa-content>
															</vsa-item>
															<vsa-item>
																<vsa-heading>
																	Password 찾기
																</vsa-heading>
																<vsa-content>
																	<center>
																		<p style="margin-top:5px;margin-bottom:15px;font-weight:bold; font-size:0.7rem; color:gray;">비밀번호는 가입하신 닉네임과 Email을 통해 찾을 수 있습니다.</p>
																		<div>
																		<i class="fa fa-user" style="margin-left:4px;margin-right:8px;"></i>
																			<input
																					v-model="nicknameP"
																					id="nicknameP"
																					placeholder="닉네임을 입력해주세요" type="text"/>
																		</div>
																		<div style="margin-top:5px;">
																		<i class="fa fa-envelope" style="margin-right:6px;"></i>
																			<input  
																					v-model="email"
																					id="email"
																					placeholder="이메일을 입력해주세요"
																					type="text"/>
																		</div>
																		<button class="button btn" style="margin-bottom:10px;background-color:rgb(255, 204, 0);" @click="checkPasswordHandler">
																			<span>찾기</span>
																		</button>
																	</center>
																</vsa-content>
															</vsa-item>
														</vsa-list>
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
    components: {
  },
  data() {
      return {
          nicknameE: '',
          password: '',
          nicknameP: '',
          email: ''
      };
  },
  methods:{
      checkEmailHandler() {
					let err = true;
					let msg = '';

					!this.nicknameE && ((msg = '닉네임을 입력해주세요'), (err = false));
					err && !this.password && ((msg = '비밀번호를 입력해주세요'), (err = false));

					if (!err) alert(msg);
					else {
							this.findEmailHandler();
					}
			},
			findEmailHandler() {
					http
					.get(`/account/findemail/${this.nicknameE}/${this.password}/`)
					.then(({ data }) => {
							if(data == ''){
									alert("닉네임 또는 비밀번호가 맞지 않습니다.");
							} else {
									alert("이메일은 '"+data+"'입니다.");
									this.$router.push("/login");
							}
					})
					.catch(() => {
							alert("조회 처리시 에러가 발생했습니다.");
					})
			},
			checkPasswordHandler() {
					let err = true;
					let msg = '';

					!this.nicknameP && ((msg = '닉네임을 입력해주세요'), (err = false));
					err && !this.email && ((msg = '이메일을 입력해주세요'), (err = false));

					if (!err) alert(msg);
					else {
							this.findPasswordHandler();
					}
			},
			findPasswordHandler() {
					http
					.get(`/account/findpw/${this.nicknameP}/${this.email}/`)
					.then(({ data }) => {
							if(data == ''){
									alert("닉네임 또는 이메일이 맞지 않습니다.");
							} else {
									alert("비밀번호는 '"+data+"'입니다.");
									this.$router.push("/login");
							}
					})
					.catch(() => {
							alert("조회 처리시 에러가 발생했습니다.");
					})
			}
  }
};

</script>

<style scoped>
.accordion {
    border-radius: 0px;
    background: #fff;
    border: none;
    margin-top: 30px;
    -webkit-box-shadow: 0px 3px 12.35px 0.65px rgba(0, 0, 0, 0.1);
    box-shadow: 0px 3px 12.35px 0.65px rgba(0, 0, 0, 0.1);
}
input {
    padding-left: 12px;
    border: 2px solid rgb(255, 204, 0);
    border-radius: 6px;
    height : 37px;
    width : 240px;
    font-weight: bold;
    font-size: 13px;
    
}
button {
    width : 268px;
    border:2px solid rgb(255, 204, 0);
    margin-top: 10px;
    margin-bottom: 5px;
    color:black;
    font-weight: bold;
}
</style>