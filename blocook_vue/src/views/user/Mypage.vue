<template>
<div class = "profile">
  <div class="profile_img">
        <center>
            <h1>MY PAGE</h1>
            <img src = @/assets/img/noprofile.png>
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
                    <center><button class="userinformation" style="background-color:rgb(52, 152, 219)">회원정보변경</button></center>
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
                    <center><button class="likerecipe" style="background-color:rgb(46, 204, 113)">즐겨찾기한 레시피</button></center>
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
      title: "login page"
    };
  },
  methods: {
		moveToAddRecipe() {
      this.$router.push('/addrecipe')
		},
		moveToMyRecipe() {
      this.$router.push('/myrecipe')
		},
	},
};

</script>

<style>
@import url('https://fonts.googleapis.com/css2?family=Fredoka+One&display=swap');
</style>