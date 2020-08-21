<template>
<div class = "profile">
  <div class="profile_img">
        <center>
            <h1>MY PAGE</h1>
            <img src = "@/assets/img/noprofile.png">
        </center>
        <div class="container">
            <div class="row">
                <div class="col-lg-12 mt-3">
                    <div class="logo_text text-center">
                        <h2 class="text-black">{{ this.$store.state.user.nickname }}</h2>
                    </div>
                </div>
                <div class="col-lg-12 mt-3">
                  <router-link :to="'/userinfo?email='+$store.state.user.email">
                    <center><button class="userinformation" style="background-color:rgb(132, 210, 0)">회원정보변경</button></center>
                  </router-link>
                </div>
                <div class="col-lg-12">
                  <a @click.prevent="moveToAddRecipe()">
                    <center><button class="newrecipe" style="background-color:rgb(255, 93, 72)">레시피 등록</button></center>
                  </a>
                </div>
                <div class="col-lg-12">
                  <a @click.prevent="moveToMyRecipe()">
                  <center><button class="myrecipe" style="background-color:rgb(241, 196, 15)">내 레시피</button></center>
                  </a>
                </div>
                <div class="col-lg-12">
                    <center>
                      <button class="likerecipe" @click="showBookmark()" style="background-color:rgb(255, 172, 179)">
                        좋아요<i class="fa fa-heart" style="color:red;"></i>표시한 레시피
                      </button>
                    </center>
                </div>
                
                <!-- 좋아요 표시한 레시피 -->
                <div class="col-lg-12">
                  <BookmarkedRecipe v-if="like == true"/>
                </div>
            </div>
        </div>
    </div>
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
    };
  },
  created() {
    if(this.$store.state.user.nickname == "") {
      alert("로그인이 필요합니다.");
      this.$router.push("/login");
    }
  },
  methods: {
		moveToAddRecipe() {
      this.$router.push('/addrecipe')
		},
		moveToMyRecipe() {
      this.$router.push('/myrecipe')
    },
    showBookmark() {
      this.like = !this.like;
    }
	},
};
</script>
