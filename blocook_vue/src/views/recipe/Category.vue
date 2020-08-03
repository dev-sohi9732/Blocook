<template>
    <div class= "category">
        <center>
            <div class="container">
                <div class="row">
                    <div class="col-4">
                      <button @click="categoryallGet">
                        <img src = @/assets/img/logo.png>
                      </button>
                        <h3> 전체</h3>
                    </div>
                    <div class="col-4">
                      <button @click="categoryGet(3020001)">
                        <img src = @/assets/img/category/korea.png>
                      </button>  
                        <h3> 한식</h3>
                    </div>
                    <div class="col-4">
                      <button @click="categoryGet(3020002)" >
                        <img src = @/assets/img/category/western.png>
                      </button>  
                        <h3> 서양</h3>
                    </div>
                    <div class="col-4">
                      <button @click="categoryGet(3020006)">
                        <img src = @/assets/img/category/italy.png>
                      </button>  
                        <h3> 이탈리아</h3>
                    </div>
                    <div class="col-4">
                      <button @click="categoryGet(3020003)">
                        <img src = @/assets/img/category/japan.png>
                      </button >  
                        <h3> 일본</h3>
                    </div>
                    <div class="col-4">
                      <button @click="categoryGet(3020004)">
                        <img src = @/assets/img/category/china.png>
                      </button>  
                        <h3> 중국</h3>
                    </div>
                    <div class="col-6">
                      <button style="margin-left:40px;" @click="categoryGet(3020005)">
                        <img src = @/assets/img/category/SoutheastAsia.png>
                      </button>  
                        <h3 style="margin-left:40px;"> 동남아시아</h3>
                    </div>
                    <div class="col-6">
                      <button style="margin-right:40px;" @click="categoryGet(3020009)">
                        <img src = @/assets/img/category/fusion.png>
                      </button>  
                        <h3 style="margin-right:40px;"> 퓨전</h3>
                    </div>

                    <!-- show==true이면 나타남 -->
                    <select class="sorting" v-if="show">
                        <option value="see">조회순</option>
                        <option value="calorie">칼로리순</option>
                        <option value="like">좋아요순</option>
                    </select>
                    <CategoryArea :recipes="recipes" />
                </div>
            </div>
        </center>
    </div>
</template>

<script>
import http from "@/util/http-common.js";
import CategoryArea from '@/components/recipe/CategoryArea.vue'

export default {
   name:'category',
   components: {
     CategoryArea,
   },
   data() {
    return {
      recipes: [],
      show: false,
    };
  },
  methods: {
    categoryallGet () {
      http.get("/recipes/search/all" )
        .then(response => {
          this.recipes = response.data
          // this.$router.push('/categoryresult')
        })
        .catch(error => {
          console.log(error)
        })
    },
    categoryGet (nationCode) {
      http.get("/recipes/search/nation/" + nationCode )
        .then(response => {
          this.recipes = response.data
          // this.$router.push('/categoryresult')
          this.show = true; // 정렬 select 보이게
        })
        .catch(error => {
          console.log(error)
        })
    }
  },
}

   

</script>
<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Nanum+Pen+Script&display=swap');
.sorting {
	margin: 15px 24px 5px;
  float: right;
	height: 28px;
	width: 100px;
	border-radius: 5px;
	border: 4px solid rgb(40, 167, 69);
	background-color:white;
	font-weight:bolder;
}
.col-4 button {
  width: 70px;
  height: 70px;
}
.col-4 button img {
  margin-top: 0px;
  width: 70px;
  height: 60px;
}
.col-6 button {
  width: 70px;
  height: 70px;
}
.col-6 button img {
  margin-top: 0px;
  width: 70px;
  height: 60px;
}
</style>
