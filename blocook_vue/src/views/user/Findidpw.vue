<template>
<div class="findidpw">
    <LogoArea/>
    <div>
        <div class="accordion" id="accordionExample">
            <div class="card">
                <div class="find-card-header" id="headingOne">
                <h2 class="mb-0">
                    <button class="btn btn-link btn-block text-left" type="button" data-toggle="collapse" data-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                    Email 찾기
                    </button>
                </h2>
                </div>
                <div id="collapseOne" class="collapse show" aria-labelledby="headingOne" data-parent="#accordionExample">
                <div class="find-card-body">
                    <center>
                    <p style="margin-top:5px;margin-bottom:5px;font-weight:bold; font-size:0.7rem; color:gray;">Email은 가입시 입력하신 닉네임과 비밀번호를 통해 찾을 수 있습니다.</p>
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
                    <button class="button btn" style="margin-bottom:10px;" @click="checkEmailHandler">
                    <span>
                        찾기
                    </span>
                    </button>
                    </center>
                </div>
                </div>
            </div>
            <div class="card">
                <div class="find-card-header" id="headingTwo">
                <h2 class="mb-0">
                    <button class="btn btn-link btn-block text-left collapsed" type="button" data-toggle="collapse" data-target="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                    Password 찾기
                    </button>
                </h2>
                </div>
                <div id="collapseTwo" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionExample">
                <div class="find-card-body">
                    <center>
                    <p style="margin-top:5px;margin-bottom:5px;font-weight:bold; font-size:0.7rem; color:gray;">Password는 가입시 입력하신 닉네임과 Email을 통해 찾을 수 있습니다.</p>
                    <div>
                    <i class="fa fa-user" style="margin-left:4px;margin-right:8px;"></i>
                        <input
                            v-model="nicknameP"
                            id="nicknameP"
                            placeholder="닉네임을 입력해주세요" type="text"/>
                            <!-- v-model="nickName" -->
                    </div>

                    <div style="margin-top:5px;">
                    <i class="fa fa-envelope" style="margin-right:6px;"></i>
                        <input  
                            v-model="email"
                            id="email"
                            placeholder="이메일을 입력해주세요"
                            type="text"/>
                    </div>
                    <button class="button btn" style="margin-bottom:10px;" @click="checkPasswordHandler">
                    <span>
                        찾기
                    </span>
                    </button>
                    </center>
                </div>
                </div>
            </div>
        </div>
    </div>
</div>
</template>

<script>
import LogoArea from "@/components/LogoArea";
import http from "@/util/http-common.js";

export default {
    components: {
    LogoArea,
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

            console.log(this.nicknameE);
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
                    console.log(data);
                    if(data == ''){
                        alert("닉네임 또는 비밀번호가 맞지 않습니다.");
                    } else {
                        alert("이메일은 "+data+"입니다.");
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

            console.log(this.nicknameP);
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
                    console.log(data);
                    if(data == ''){
                        alert("닉네임 또는 이메일이 맞지 않습니다.");
                    } else {
                        alert("비밀번호는 "+data+"입니다.");
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