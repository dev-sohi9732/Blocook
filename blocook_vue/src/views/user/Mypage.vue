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
                <div class="container" style="width: 90%;margin-top:10px;" v-if="like">
                  <div class="row">
                    <div class="col-6">
                    <!-- <div class="col-6" v-for="myRecipe in myRecipes" :key="myRecipe.recipeId"> -->
                      <div class="card">
                        <div class="foodimg">
                          <img src="@/assets/img/logo.png" class="card-img-top" alt="...">
                        </div>
                        <div class="card-body" style="padding:5px;">
                          <h5 class="card-title" style="margin-bottom:3px;">좋아요 레시피</h5>
                          <p class="card-text">레시피 설명</p>
                          <div>
                            <!-- <router-link :to="" class="btn" style="background-color:#B3D662;">레시피&nbsp;<img style="width:25px; height: 25px; margin-top:0px;" src="@/assets/img/recipeicon.png"></router-link> -->
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
            </div>
        </div>
    </div>
</div>
</template>
<script>

export default {
  data() {
    return {
      title: "login page",
      like: false,
    };
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

<style>
@import url('https://fonts.googleapis.com/css2?family=Fredoka+One&display=swap');

button {
	margin-bottom: 20px;
}
.btn {
	width: 98%;
}
.col-6 {
	margin-bottom: 10px;
}
img {
	margin-top: 10px;
	width: 100px;
	height: 100px;
}
.card {
	height: 306px;
}
.card-body {
	height: 200px;
	/* display: inline-block;   */
	/* // inline-block으로 설정 필요 */
}
.card-title {
  height: 30px;
  background-image: url("../../assets/img/highlight.png");
	background-size: 85%;
	background-repeat: no-repeat;
}
.card-text {
	text-align: left;
	height: 80px;
	width: 120px;
	margin-bottom: 10px;
	overflow: hidden;
	text-overflow: ellipsis;
	display: -webkit-box;
    -webkit-line-clamp: 3; /* 라인수 */
    -webkit-box-orient: vertical;
    word-wrap:break-word; 
}
.foodimg img {
	margin-top: 10px;
	width: 120px;
	height: 120px;
}
.recipebook {
	width:40px;
	height: 43px;
}
</style>