<template>
    <div>

        <div class="position-relative">
            <!-- shape Hero -->
            <section class="section-hero section-shaped my-0" style="padding-top:20%; padding-bottom:20%">
                <div class="shape shape-style-1 shape-category">
                    <span class="span-150"></span>
                    <span class="span-50"></span>
                    <span class="span-50"></span>
                    <span class="span-75"></span>
                    <span class="span-100"></span>
                    <span class="span-75"></span>
                    <span class="span-50"></span>
                    <span class="span-100"></span>
                    <span class="span-50"></span>
                    <span class="span-100"></span>
                </div>
                <div class="container shape-container d-flex">
                    <div class="col px-3">
                        <div class="row">
                            <div class="container" style="text-align:center;">
                                <div class="row">
                                    <div class="col">
                                        <router-link to="/country?country=all">
                                        <img src="img/blocook/category/earth.png">
                                        <h3 class="display-3 text-white">전체<span>All</span></h3>
                                        </router-link>
                                    </div>
                                    <div class="col">
                                        <router-link to="/country?country=korea">
                                        <img src="img/blocook/category/rice.png">
                                        <h3 class="display-3 text-white">한식<span>Korea</span></h3>
                                        </router-link>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col">
                                        <router-link to="/country?country=japan">
                                        <img src="img/blocook/category/sushi.png">
                                        <h3 class="display-3 text-white">일식<span>Japan</span></h3>
                                        </router-link>
                                    </div>
                                    <div class="col">
                                        <router-link to="/country?country=china">
                                        <img src="img/blocook/category/dumpling.png">
                                        <h3 class="display-3 text-white">중식<span>China</span></h3>
                                        </router-link>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col">
                                        <router-link to="/country?country=western">
                                        <img src="img/blocook/category/burger.png">
                                        <h3 class="display-3 text-white">서양<span>Western</span></h3>
                                        </router-link>
                                    </div>
                                    <div class="col">
                                        <router-link to="/country?country=italy">
                                        <img src="img/blocook/category/pizza.png">
                                        <h3 class="display-3 text-white">이탈리아<span>Italy</span></h3>
                                        </router-link>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col">
                                        <router-link to="/country?country=asia">
                                        <img src="img/blocook/category/ramen.png">
                                        <h3 class="display-3 text-white">아시아<span>Asia</span></h3>
                                        </router-link>
                                    </div>
                                    <div class="col">
                                        <router-link to="/country?country=fusion">
                                        <img src="img/blocook/category/indian.png">
                                        <h3 class="display-3 text-white">퓨전<span>Fusion</span></h3>
                                        </router-link>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
            <!-- 1st Hero Variation -->
        </div>

    </div>
</template>

<script>
export default {
  name: "home",
  components: {},
  created() {this.$store.state.speechRecognition.stop()}
};
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Nanum+Gothic+Coding:wght@400;700&family=Poor+Story&display=swap');
h3 {
    cursor: pointer;
    font-family: 'Poor Story', cursive;
    font-size: 24px;
}
span {
    font-family: 'Poor Story', cursive;
    font-size: 18px;
}
img {
    cursor: pointer;
    width: 60px;
}
.col {
    margin-left: 10%;
    margin-right: 10%;
    padding: 5px 0 10px 0;
}
.shape-category {
    background: linear-gradient(150deg, #ffbe00 15%, #ff9900 70%, #ff5000 94%);
}
@media(min-width: 737px) {
    section {
        padding-top: 20px;
    }
}
@media(min-height: 680px) {
    section {
        height: 100vh;
    }
}
</style>