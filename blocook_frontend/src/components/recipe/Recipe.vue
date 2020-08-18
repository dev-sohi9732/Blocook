<template>
	<div style="padding-bottom: 20px;">
		<center class="menu" id="recipetitle">
			<h1 style="margin-top: 28px;">{{recipe.recipeNmKo}}</h1>
		</center>

		<div id="carousel">
				<carousel :loop="true" :perPage="1" :navigationEnabled="true" :centerMode="true" :navigateTo="slideIndex" v-on:pageChange="pageChange">
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
									<b-card no-body>
									<b-tabs card >
									<b-tab no-body title="레시피" id="recipe">
										<center class="menu">
											<h5 style="margin-top: 15px;">Step {{ cookings.indexOf(cooking) + 1 }}</h5>
											<br>
											<div class="cookingimg">
												<b-card-img :src="cooking.streStepImageUrl"></b-card-img>
											</div>
										</center>
										<div class="container inputbox">
											<table>
											<tbody>
												<tr>
													<td style="width:300px;">
														{{cooking.cookingDc}}
													</td>
												</tr>
											</tbody>
											</table>
											<!-- v-if="cooking.timerYN==Y" -->
											<!-- <router-link :to="'timer?time='+cooking.timerTime" class="timer_btn">타이머 보기</router-link> -->
										</div>
									</b-tab>
									<b-tab v-if="cooking.timerYN=='Y'" title="타이머" id="timer">
									<!-- v-if="cooking.timerYN==Y" -->
										<Timer :cooking="cooking" />
									</b-tab>
									</b-tabs>
								</b-card>
								</div>
						</slide>
				</carousel>
		</div>

		<button class="button btn" @click="record">음성인식</button>
		<button class="button btn" @click="stop">음성종료</button>
		<!-- <button class="button btn" @click="textToSpeech" value="">음성합성</button> -->
	</div>
</template>
<script>
import http from "@/util/http-common.js";
import Timer from '@/components/recipe/Timer.vue'

var audio = new Audio();

export default {
	components: {
        Timer,
    },
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
			slideIndex: 0,
			cookingsLen: 0,
			micStat: true
		};
	},
	watch:{
		slideIndex: function() {
			audio.pause();
			//tts
			if(this.slideIndex != 0){
				this.textToSpeech(this.cookings[this.slideIndex-1].cookingDc);
			} else {
				this.textToSpeech(this.recipe.recipeNmKo);
			}
		}
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
				this.textToSpeech(this.recipe.recipeNmKo);
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
			this.cookings = res.data;
			this.cookingsLen = this.cookings.length;
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

		// STT
		this.$store.state.speechRecognition.continuous = true;
		this.$store.state.speechRecognition.addEventListener('result', event => {
				var text = event.results[event.results.length-1][0].transcript.trim();
				this.speechHandler(text);
			})
		this.$store.state.speechRecognition.onend = function() {
			if(this.micStat)
				this.$store.state.speechRecognition.start();
		};
		this.$store.state.speechRecognition.start();
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
		pageChange(i) { 
			this.slideIndex = i;
		},		
		record() {
			this.$store.state.speechRecognition.stop();
			this.$store.state.speechRecognition.start();
		},
		stop(){
			this.$store.state.speechRecognition.stop();
		},
		textToSpeech(sumary) {
			audio.pause();
			http
				.post('/recipes/tts', {
					content: sumary
				},{
					responseType: "blob"
				})
				.then(({ data }) => {
					var blob=new Blob([data], {type : 'audio/ogg'});
					const audioUrl = URL.createObjectURL(blob);
					audio = new Audio(audioUrl);
					audio.play();
				})
				.catch((error) => {
					alert('처리 실패하였습니다.');
					console.log(error);
				})
		},
		speechHandler(text){
			if(text == "다음") {
				if(this.slideIndex == this.cookingsLen){
					audio.pause();
					this.textToSpeech("마지막 단계 입니다.");
				}
				else
					this.slideIndex++;
			}
			else if(text == "이전") {
				if(this.slideIndex == 0){
					audio.pause();
					this.textToSpeech("첫 페이지입니다.");
				}
				else
					this.slideIndex--;
			}
			else if(text == "다시") 
				this.textToSpeech(this.cookings[this.slideIndex-1].cookingDc);
			else if(text == "레시피") 
				document.getElementById('recipe___BV_tab_button__').click();
			else if(text == "타이머") { 
				var ele = document.getElementById('timer___BV_tab_button__');
				if(ele != null)
					ele.click();
			}
			else if(text == "타이머 시작") {
				var ele = document.getElementById('timer_start');
				if(ele != null)
					ele.click();
			}
			else if(text == "타이머 정지") {
				var ele = document.getElementById('timer_pause');
				if(ele != null)
					ele.click();
			}
			else if(text == "타이머 리셋"){
				var ele = document.getElementById('timer_reset');
				if(ele != null)
					ele.click();
			}
			else if(text == "마이크 꺼 줘"){
				this.micStat = false;
				this.$store.state.speechRecognition.stop();
			}
			else {
				audio.pause();
				this.textToSpeech("다시 한 번 말씀해주세요.");
			}

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