<template>
	<div style="padding-bottom: 20px;">
		<center class="menu" id="recipetitle">
			<h1 style="margin-top: 28px;">{{recipe.recipeNmKo}}</h1>
		</center>

		<div id="carousel">
				<carousel :loop="true" :perPage="1" :navigationEnabled="true" :centerMode="true" >
						<!--------------------------- 레시피 기본정보 --------------------------->
						<slide>
								<center class="menu">
									<img class="d-block" :src="imgUrl" alt="Second slide">
								</center>

								<center class="menu" v-if="this.$store.state.user.authorized == true">
									<button class="btn" style="color:red;font-weight:bold;" @click="addBookmark()" v-if="like == false">
										Like&nbsp;<i class="fa fa-heart-o"></i>
									</button>
									<button class="btn" style="color:red;font-weight:bold;" @click="rmBookmark()" v-else>
										Like&nbsp;<i class="fa fa-heart"></i>
									</button>
								</center>

								<div class="container">
									<h6 style="margin-bottom: 10px;">{{recipe.nationNm}} > {{recipe.tyNm}} &nbsp;
										<span><i class="fa fa-heart" style="color:red;font-weight:bold;"></i> {{ this.bookmarkCnt }}</span>
									</h6>
									<h6 style="color: rgb(228, 108, 28);">
										<i class="fa fa-user" style="margin-right: 10px;">&nbsp;{{recipe.qnt}}인분 </i>
										<i class="fa fa-clock-o" style="margin-right: 10px;">&nbsp; {{recipe.cookingTime}}분 </i>
										<i class="fa fa-apple" style="margin-right: 10px;">&nbsp; {{recipe.calorie}}kcal </i>
										<i class="fa fa-award" style="margin-right: 10px;"> 난이도 : {{recipe.levelNm}}</i>
									</h6>
									<b-card class="innercard">{{recipe.sumry}}</b-card>
									<table>
									<tbody>
										<tr>
											<th class="list">
												<h6>주재료</h6>
											</th>
											<td v-for="(irdnt,irdnt_key) in irdnts" :key="irdnt_key" v-if="irdnt.irdntTyNm.indexOf('주재료')>-1" style="display:inline;" > 
												{{irdnt.irdntNm}}
												<span style="font-size:0.8em;">({{irdnt.irdntCpcty}})</span>
											</td>
										</tr>
										<tr>
											<th class="list">
												<h6>부재료</h6>
											</th>
											<td v-for="(irdnt,irdnt_key) in irdnts" :key="irdnt_key" v-if="irdnt.irdntTyNm.indexOf('부재료')>-1" style="display:inline;" >
												{{irdnt.irdntNm}}
												<span style="font-size:0.8em;">({{irdnt.irdntCpcty}})</span>
											</td>
										</tr>
										<tr>
											<th class="list">
												<h6>양념</h6>
											</th>
											<td v-for="(irdnt,irdnt_key) in irdnts" :key="irdnt_key" v-if="irdnt.irdntTyNm.indexOf('양념')>-1" style="display:inline;" >
												{{irdnt.irdntNm}}
												<span style="font-size:0.8em;">({{irdnt.irdntCpcty}})</span>
											</td>
										</tr>
									</tbody>
									</table>
								</div>
						</slide>

						<!--------------------------- 레시피 과정정보 --------------------------->
						<slide v-for="(cooking,cooking_key) in cookings" :key="cooking_key">
								<div style="margin-bottom: 20px;">
									<center class="menu">
										<h5 style="margin-top: 15px;">Step {{ cookings.indexOf(cooking) + 1 }}</h5>
										<br>
										<div class="cookingimg">
											<img :src="cooking.streStepImageUrl">
										</div>
									</center>
									<div class="container inputbox">
										<table>
										<tbody>
											<tr>
												<td>
													{{cooking.cookingDc}}
												</td>
											</tr>
										</tbody>
										</table>
									</div>
								</div>
						</slide>
				</carousel>
		</div>

		<button class="button btn" @click="record">음성인식</button>
		<button class="button btn" @click="recordresult">인식결과</button>
	</div>
</template>
<script>
import http from "@/util/http-common.js";

var say = '';
export default {
	computed : {
			imgUrl() {
					return this.recipe.imgUrl
		},
	},
	data() {
		return {
			title: "recipe page",
			recipe: [],
			irdnts: [],
			cookings: [],
			bookmarkCnt: 0,
			like: false,
			// say: ""
		};
	},
	created() {
		const params = new URL(document.location).searchParams;
		http.get(`/recipes/${params.get('Id')}`)
			.then(response => {
				this.recipe = response.data
				if(this.$store.state.user.authorized == true) {
					http.post('/recipes/isbookmarked', {
						"recipeId": this.recipe.recipeId,
						"uid": this.$store.state.user.uid
					})
					.then(response => {
						console.log();
						if(response.data == "success"){
							this.like = true;
						} else {
							this.like = false;
						}
					})
					.catch(error => {
						console.log(error);
					})
				}
			})
			.catch(error => {
				console.log(error)
			})
		http.get(`/recipes/${params.get('Id')}/irdnts`)
			.then(res => {
				this.irdnts = res.data
			})
			.catch(err => {
				console.log(err)
			})
		http.get(`/recipes/${params.get('Id')}/cookings`)
		.then(res => {
			this.cookings = res.data
		})
		.catch(err => {
			console.log(err)
		})
		http.get(`/recipes/${params.get('Id')}/bookmark-count`)
		.then(res => {
			this.bookmarkCnt = res.data
		})
		.catch(err => {
			console.log(err)
		})
	},
	methods: {
		addBookmark() { // 좋아요 누름
			http
			.post(`/recipes/bookmark`, {
				"recipeId": this.recipe.recipeId,
				"uid": this.$store.state.user.uid
			})
			.then(({ data }) => {
				this.like = true;
			})
			.catch((error) => {
				alert('처리 실패하였습니다.')
				console.log(error)
			})
		},
		rmBookmark() { // 좋아요 취소
			http
			.delete(`/recipes/unbookmark/${this.$store.state.user.uid}/${this.recipe.recipeId}`)
			.then(({ data }) => {
				this.like = false;
			})
			.catch((error) => {
				alert('처리 실패하였습니다.')
				console.log(error)
			})
		},
		record() {
			var speechRecognition = new webkitSpeechRecognition();
			speechRecognition.onresult = function(event) {
				console.log(event.results[0][0].transcript);
				console.log(typeof(event.results[0][0].transcript));
				say = event.results[0][0].transcript;
				alert(say);
			};
			speechRecognition.start();
		},
		recordresult() {
			// alert(this.say);
			// console.log(typeof(this.say));
			// console.log(this.say);
			console.log(say);
		}
	}
}
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Nanum+Gothic+Coding:wght@400;700&family=Poor+Story&display=swap');
center {
	margin-top: 10px;
	font-weight: bold;
}
img {
	max-width: 552px;
	max-height: 552px;
	width: 92%;
	height: 70vw;
}
h1, h4, h5, h6 {
	font-family: 'Poor Story', cursive;
	font-weight: bold;
}
.container {
	width: 100%;
	margin-top: 12px;
	margin-bottom: 10px;
	max-width: 600px;
}
th {
	width: 22%;
	text-align: left;
	vertical-align: top;
}
td {
	vertical-align: top;
	padding-left: 2%;
}
a {
	float: right;
}
.card {
	width: 90%;
	margin: 15px auto;
	height: 100%;
}
.innercard {
	width: 100%;
}
.list {
	background-image: url("/img/blocook/highlight-blue.png");
	background-size: 55%;
	background-repeat: no-repeat;
}
center > h1 {
	background-image: url("/img/blocook/highlight-blue.png");
	background-size: 90%;
	background-repeat: no-repeat;
  background-position: center;
	max-width: 600px;
}
.cookingimg {
	background-image: url("../../assets/blocookImg/addimage.jpg");
	background-size: 92%;
	background-repeat: no-repeat;
  background-position: center;
	max-width: 600px;
}
</style>