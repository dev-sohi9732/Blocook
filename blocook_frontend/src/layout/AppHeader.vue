<template>
    <header class="header-global">
        <base-nav class="navbar-main" transparent type="" effect="light" expand>

            <ul slot="brand" class="navbar-brand mr-lg-5 navbar-nav" style="cursor: pointer;margin-right: 8px;" @click.prevent="logout()" v-if="this.$store.state.user.authorized">
                Logout
            </ul>
            <router-link to="/login" slot="brand" class="navbar-brand mr-lg-5" v-else>
                Login
            </router-link>

            <router-link slot="brand" class="navbar-brand mr-lg-5" to="/">
                <img src="img/blocook/logo-en-white.png" alt="logo">
            </router-link>

            <div class="row" slot="content-header" slot-scope="{closeMenu}">
                <div class="col-6 collapse-brand">
                    <a href="/">
                        <img src="img/blocook/logo-en-violet.png">
                    </a>
                </div>
                <div class="col-6 collapse-close">
                    <close-button @click="closeMenu"></close-button>
                </div>
            </div>

            <ul class="navbar-nav align-items-lg-center ml-lg-auto">
                <li class="nav-item">
                    <router-link to="/category" class="nav-link nav-link-icon">
                        <i class="fa fa-cutlery"></i>
                        <span class="nav-link-inner--text">레시피 카테고리</span>
                    </router-link>
                </li>
                <li class="nav-item">
                    <router-link to="/community" class="nav-link nav-link-icon">
                        <i class="fa fa-comment-o"></i>
                        <span class="nav-link-inner--text">요리톡! 조리톡!</span>
                    </router-link>
                </li>
                <!-- 
                <li class="nav-item">
                    <router-link to="#" class="nav-link nav-link-icon">
                        <i class="fa fa-trophy"></i>
                        <span class="nav-link-inner--text">메뉴 월드컵</span>
                    </router-link>
                </li>
                -->
                <li class="nav-item" v-if="this.$store.state.user.authorized">
                    <router-link to="/mypage" class="nav-link nav-link-icon">
                        <i class="fa fa-user-circle-o"></i>
                        <span class="nav-link-inner--text">마이페이지</span>
                    </router-link>
                </li>
            </ul>
        </base-nav>
    </header>
</template>

<script>
import BaseNav from "@/components/BaseNav";
import CloseButton from "@/components/CloseButton";

export default {
  components: {
    BaseNav,
    CloseButton
  },
  methods: {
	logout() {
      //this.$router.push('/');
      this.$store.dispatch('user/LOGOUT');
      if(this.$store.state.user.nickname == "") {
        alert("로그아웃 되었습니다.");
        this.$router.push("/");
      }
    }
  }
};
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Nanum+Gothic+Coding:wght@400;700&family=Poor+Story&display=swap');
span {
  font-family: 'Poor Story', cursive;
  font-size: 18px;
}
</style>
