<template>
    <div class="profile-page">
        <section class="section-profile-cover section-shaped my-0">
            <div class="shape shape-style-1 shape-primary shape-skew alpha-4">
                <span></span>
                <span></span>
                <span></span>
                <span></span>
                <span></span>
                <span></span>
                <span></span>
            </div>
            <h1 id="title">User Info</h1>
        </section>
        <section class="section section-skew">
            <div class="container">
                <card shadow class="card-profile mt--300" id="whitecard" no-body>
                    <div class="px-4">
                        <div class="row justify-content-center">
                            <div class="col-lg-3 order-lg-2">
                                <div class="card-profile-image">
                                    <img :src="profileimg" class="rounded-circle">
                                </div>
                            </div>
                            <div class="col-lg-4 order-lg-3 text-lg-right align-self-lg-center">
                                <div class="card-profile-actions py-4 mt-lg-0">
                                </div>
                            </div>
                            <div class="col-lg-4 order-lg-1">
                            </div>
                        </div>

                        <div class="text-center col-lg-12" id="infocard">
                            <input type="file" @change="previewProfileImage" accept="image/*" style="width:210px;border-style:none;">
                            <img :src="picture">
                            <button class="button btn" id="imagebtn" @click="uploadimg">
                                <span>이미지 업로드 <i class="fa fa-pencil"></i></span>
                            </button>
                            <div style="width:300px;margin:3px auto 3px auto;font-weight:bold;">
                                <i class="fa fa-envelope"></i> 이메일&emsp;&emsp;&emsp;&nbsp;
                                <input v-model="email"
                                        id="email"
                                        type="text"
                                        readonly/>
                            </div>
                            <div style="width:300px;margin:3px auto 3px auto;font-weight:bold;">
                                <i class="fa fa-user"></i> 닉네임&emsp;&emsp;&emsp;&nbsp;&nbsp;
                                <input v-model="nickname"
                                        id="nickname"
                                        type="text"/>
                            </div>
                            <div style="width:300px;margin:3px auto 3px auto;font-weight:bold;">
                                <i class="fa fa-lock"></i> 비밀번호&emsp;&emsp;&nbsp;&nbsp;
                                <input v-model="password"
                                        id="password"
                                        type="password"
                                        placeholder="비밀번호를 입력해주세요"/>
                            </div>
                            <div style="width:300px;margin:3px auto 3px auto;font-weight:bold;">
                                <i class="fa fa-lock"></i> 비밀번호 확인&nbsp;
                                <input v-model="passwordConfirm" 
                                        id="password-confirm"
                                        type="password"
                                        placeholder="비밀번호를 확인해주세요"/>
                            </div>	
                        </div>

                        <div class="mt-3 text-center">
                            <div class="row justify-content-center">
                                <div class="col-lg-9">
                                    <button class="button btn" @click="checkHandler" style="background-color:rgb(78, 29, 255);border-radius: .55rem;margin-right:6px;">
                                        <span>회원정보수정</span>
                                    </button>
                                    <button class="button btn" @click="deleteHandler" style="background-color:rgb(255, 89, 89);border-radius: .55rem;margin-left:6px;">
                                        <span>탈퇴하기</span>
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                </card>
            </div>
        </section>
    </div>
</template>

<script>
import http from "@/util/http-common.js";
import firebase from 'firebase';

export default {
    name: "UserInfo",
    data() {
    return {
        userid: null,
        email: '',
        originNickname: '',
        nickname: '',
        password: '',
        passwordConfirm: '',
        // 이미지 데이터
        profileimg: require('@/assets/blocookImg/noprofile.png'),
        imageData: null,
        uploadValue: 0,
        picture: ''
    };
  },
  created() {
    if(this.$store.state.user.nickname == "") {
      alert("로그인이 필요합니다.");
      this.$router.push("/login");
    }
    const params = new URL(document.location).searchParams;
    http
        .get(`/account/userinfo/${params.get('email')}`)
        .then(({ data }) => {
            this.userid = data.uid;
            this.email = data.email;
            this.originNickname = data.nickname;
            this.nickname = data.nickname;
            this.password = data.password;
            if(data.imageUrl != null) this.profileimg = data.imageUrl;
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
                  password: this.password,
                  imageUrl: this.profileimg
              };
              http
                .put('/account/update', user)
                .then(({ data }) => { // 회원가입 성공
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
              alert("비밀번호를 확인해주세요.");
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
      },
      previewProfileImage(event) {
          this.uploadValue = 0;
          this.imageData = event.target.files[0];
      },
      uploadimg() {
        const storageRef = firebase.storage().ref(`${this.imageData.name}`).put(this.imageData);
        storageRef.on(`state_changed`, snapshot => {
                this.uploadValue = (snapshot.bytesTransferred/snapshot.totalBytes) * 100;
            }, error => {console.log(error.message)},
            () => {this.uploadValue = 100;
                storageRef.snapshot.ref.getDownloadURL().then((url) => {
                    this.picture = url;
                });
            }
        );
      }
  }
}
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Nanum+Gothic+Coding:wght@400;700&family=Poor+Story&display=swap');
#title {
    font-family: 'Poor Story', cursive;
    font-weight: bold;
    color: white;
    padding-top: 194px;
    text-align: center;
}
@media(min-width: 576px) {
    #infocard {
        margin-top: 85px;
    }
}
@media(max-width: 767px) {
    #whitecard {
        height: 520px;
    }
}
.card {
	height:500px;
}
#infocard {
    padding-left: 0px;
    padding-right: 0px;
}
#infocard input {
    padding-left: 12px;
    border: 1px solid rgb(0, 0, 0);
    border-radius: 6px;
    height : 37px;
    width : 185px;
    font-weight: bold;
    font-size: 13px; 
}
button {
	width : 134px;
    margin-top: 10px;
    margin-bottom: 5px;
    color:white;
    font-family:'Noto Sans KR',sans-serif;
}
.col {
	margin-left: 8px;
	margin-bottom: 8px;
  text-align: left;
}
#imagebtn {
    padding-top: 0px;
    padding-bottom: 0px;
    margin-bottom:15px;
    background-color:#c4c4c4;
    height:35px;
    border-radius: .55rem;
    width:140px;
    font-family:'Noto Sans KR',sans-serif;
}
</style>
