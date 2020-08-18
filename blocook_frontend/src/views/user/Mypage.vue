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
            <h1 id="title">My Page</h1>
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
                        <div class="text-center col-lg-12" id="profilecard">
                            <h3>{{ this.$store.state.user.nickname }}</h3>
                            <base-button @click.prevent="moveToUserInfo()" type="primary" class="my-1 mypagebtn" style="background-color:rgb(132, 210, 0);border-color:rgb(132, 210, 0);">
                                회원정보</base-button>
                            <base-button @click.prevent="moveToAddRecipe()" type="primary" class="my-1 mypagebtn" style="background-color:rgb(255, 93, 72);border-color:rgb(255, 93, 72);">
                                레시피 등록</base-button>
                            <base-button @click.prevent="moveToMyRecipe()" type="primary" class="my-1 mypagebtn" style="background-color:rgb(241, 196, 15);border-color:rgb(241, 196, 15);">
                                내 레시피</base-button>
                            <base-button @click="showBookmark()" type="primary" class="my-1 mypagebtn" style="background-color:rgb(255, 172, 179);border-color:rgb(255, 172, 179);margin-right:.5rem;">
                                좋아요 <i class="fa fa-heart" style="color:red;"></i> 표시한 레시피</base-button>
                        </div>
                        <div class="mt-5 text-center">
                            <div class="row justify-content-center">
                                <div class="col-lg-9">
                                </div>
                            </div>
                        </div>
                    </div>
                </card>
            </div>
            <!---- 좋아요 표시한 레시피 ---->
            <div id="bookmarked">
                <BookmarkedRecipe v-if="like == true" />
            </div>
        </section>
    </div>
</template>

<script>
import BookmarkedRecipe from '@/components/recipe/BookmarkedRecipe.vue'

export default {
  components: {
    BookmarkedRecipe,
  },
  data() {
    return {
      title: "My page",
      like: false,
      profileimg : require('@/assets/blocookImg/noprofile.png')
    };
  },
  created() {
    if(this.$store.state.user.nickname == "") {
      alert("로그인이 필요합니다.");
      this.$router.push("/login");
    }
    if(this.$store.state.user.img != null) {
        this.profileimg = this.$store.state.user.img;
    }
    this.$store.state.speechRecognition.stop();
    this.$store.state.audio.pause();
  },
  methods: {
    moveToUserInfo() {
      this.$router.push('/userinfo?email='+this.$store.state.user.email)
    },
    moveToAddRecipe() {
      this.$router.push('/addrecipe')
	},
    moveToMyRecipe() {
      this.$router.push('/myrecipe')
    },
    showBookmark() {
      this.like = !this.like;
      if(this.like == true) document.getElementById('bookmarked').scrollIntoView();
    }
  },
};
</script>

<style>
@import url('https://fonts.googleapis.com/css2?family=Nanum+Gothic+Coding:wght@400;700&family=Poor+Story&display=swap');
#title {
    font-family: 'Poor Story', cursive;
    font-weight: bold;
    color: white;
    padding-top: 194px;
    text-align: center;
}
.mypagebtn {
    width: 210px;
}
@media(min-width: 576px) {
    #profilecard {
        margin-top: 85px;
    }
}
@media(max-width: 767px) {
    #whitecard {
        height: 450px;
    }
}
</style>
