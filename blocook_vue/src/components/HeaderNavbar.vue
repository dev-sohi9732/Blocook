<template>
  <!-- Start Header Area -->
  <header class="header-area transition header-sticky">
    <div class="container">
      <div class="row">
        <div class="col-12">
          <nav class="main-nav">
            <b-container class="bv-example-row">
              <b-row class="text-center">
                <!-- 로그인 되어 있는 경우와 아닌 경우를 구분 -->
                <b-col>
                  <template v-if="this.$store.state.user.authorized">
                    <a @click.prevent="logout()" class="float-left" style="margin-left: 10px; margin-top:25px; color:black; font-weight:bolder; text-decoration:none; font-size: 15px;">
                      LOGOUT
                    </a>
                    <a @click.prevent="moveToMyPage()" class="float-left" style="margin-left: 10px; margin-top:25px; color:black; font-weight:bolder; text-decoration:none">
                      <i class="fa fa-user"></i>
                    </a>
                  </template>
                  <template v-else>
                    <router-link to="/login" class="float-left" style="margin-left: 10px; margin-top:25px; color:black; font-weight:bolder; text-decoration:none">
                      LOGIN
                    </router-link>
                  </template>
                </b-col>
                  
                <b-col cols="4">
                  <!-- Logo Start -->
                  <router-link to="/" class="logo transition" >
                    <span>블로쿡</span>
                  </router-link>
                  <!-- Logo End -->
                </b-col>

                <b-col>
                  <!-- Menu Start -->
                  <tasty-burger-button
                  :type="buttonType"
                  :active="isActive"
                  :size="size"
                  :color="color"
                  :active-color="activeColor"
                  v-on:toggle="onToggle" />
                </b-col>
              </b-row>
            </b-container>
            
            <transition name="slide">
            <ul class="list" v-if="isDropped">
              <li><router-link to="/" style="color:black; font-weight: bold;">Home</router-link></li>
              <li><router-link to="/category" style="color:black; font-weight: bold;">레시피</router-link></li>
              <li><router-link to="/worldcup" style="color:black; font-weight: bold;">메뉴 월드컵</router-link></li>
              <li><router-link to="/community" style="color:black; font-weight: bold;">요리톡 조리톡</router-link></li>
            </ul>
            </transition>
            <!-- Menu End -->
          </nav>
        </div>
      </div>
    </div>
  </header>
  <!-- End Header Area -->
</template>

<script>
export default {
  name: "HeaderNavbar",
  components: {},
	data: () => ({
    isDropped: false,
    buttonType: 'squeeze',
    isActive: false,
    size: 'l',
    color: 'black',
    activeColor: 'white'
  }),
	mounted() {
	},
	created() {},
	computed: {
	},
	watch: {
	},
	methods: {
		logout() {
      this.$router.push('/')
      this.$store.dispatch('user/LOGOUT')
		},
		moveToMyPage() {
      this.$router.push('/mypage')
		},
    onToggle (active) {
      // Toggle menu
      this.isDropped = !this.isDropped
    }
	},
};
</script>

<style scoped>
div, a {
  height:80px;
}
button {
  height: 80px;
}
.list{
  position: absolute;
  left: 10%;
  top: 81px;
  width: 80%;
  margin: 0;
  padding: 0;
  list-style-type: none;
  transform-origin: top;
  transition: transform .4s ease-in-out;
  overflow: hidden;
  float: left;
}
li {
  padding: 10px;
  background: #f9b058;
  border-bottom: solid thin rgb(248, 242, 242);
}
.slide-enter, .slide-leave-to{
  transform: scaleY(0);
}
</style>