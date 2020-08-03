<template>
<center>
        <div class="user"> 
            <div class>
            
                <div class>
                    <div class>
                        <i class="fa fa-envelope"></i>
                        <input v-model="email" 
                            id="email"
                            type="text"
                            readonly/>
                    </div>
                    <div class>
                        <i class="fa fa-user"></i>
                        <input v-model="nickname"
                            id="nickname"
                            type="text"/>
                    </div>
                    <div class ="password">
                        <i class="fa fa-lock"></i>
                        <input v-model="password"
                            id="password"
                            type="text" 
                            placeholder="비밀번호를 입력해주세요"/>
                        <!-- <span :class="{active : passwordType==='text'}">
                            <i class="fas fa-eye"></i>
                            :type="passwordType"
                        </span> -->
                    </div>
                    <div class="password">
                        <i class="fa fa-lock"></i>
                        <input v-model="passwordConfirm" 
                            id="password-confirm"
                            type="password"
                            placeholder="비밀번호를 한번 더 입력해주세요"/>
                        <!-- <span :class="{active : passwordConfirmType==='text'}">
                            <i class="fas fa-eye"></i>
                            :type="passwordConfirmType" 
                        </span> -->
                    </div>
                </div>
                <button class="button btn" @click="checkHandler">
                    <span>
                        회원정보수정
                    </span>
                </button>
                <button class="button btn" @click="deleteHandler">
                    <span>
                        탈퇴하기
                    </span>
                </button>
            </div>
        
        </div>
    </center>
</template>

<script>
import http from "@/util/http-common.js";

export default {
    name: "UserInfo",
    data() {
    return {
        userid: null,
        email: '',
        originNickname: '',
        nickname: '',
        password: '',
        passwordConfirm: ''
    };
  },
  created() {
    const params = new URL(document.location).searchParams;
    console.log(params.get('email'));
    http
        .get(`/account/userinfo/${params.get('email')}`)
        .then(({ data }) => {
            console.log(data);
            this.userid = data.uid;
            this.email = data.email;
            this.originNickname = data.nickname;
            this.nickname = data.nickname;
            this.password = data.password;
        })
        .catch(() => {
            alert("조회 처리시 에러가 발생했습니다.");
        })
  },
  methods: {
      updateHandler(){
          if(this.password == this.passwordConfirm){
              let user = {
                  nickname: this.nickname,
                  email: this.email,
                  password: this.password
              };
              http
                .put('/account/update', user)
                .then(({ data }) => { // 회원가입 성공
                    console.log(data)
                    if (data === "success") {
                        alert('정보수정을 성공하였습니다!')
                        this.$store.dispatch('user/UPDATE', { payload: user }) // store 반영
                        this.$router.push('mypage')
                    } else {
                        alert('정보수정에 문제가 발생하였습니다!')
                    }
                })
                .catch((error) => {
                    alert('정보 수정에 실패하였습니다.')
                    console.log(error)
                    console.log('UPDATE FAILED..')
                })
          } else {
              alert("비밀번호를 정확히 확인해주세요.");
          }
      },
      //닉네임 중복 처리
      checkHandler() {
          http
            .get(`/account/update/${this.nickname}`)
            .then(({ data }) => {
                if(data == 'success' || this.nickname == this.originNickname){
                    this.updateHandler();
                } else {
                    alert("해당 닉네임은 이미 사용중입니다.");
                }
            })
            .catch(() => {
                alert("조회 처리시 에러가 발생했습니다.");
            })
      },
      deleteHandler() {
          var confirmflag = confirm("정말 계정을 삭제하시겠습니까?");
          if(confirmflag){
              http
                .delete(`/account/delete/${this.userid}`)
                .then(({ data }) => {
                    if(data == 'success'){
                        alert("정상적으로 탈퇴되었습니다.");
                        this.$store.dispatch('user/LOGOUT');
                        this.$router.push('/');
                    } else {
                        alert("탈퇴 처리시 에러가 발생하였습니다.");
                    }
                })
                .catch(() => {
                    alert("탈퇴 처리시 에러가 발생하였습니다.");
                })
          }
      }
  }
}
</script>
