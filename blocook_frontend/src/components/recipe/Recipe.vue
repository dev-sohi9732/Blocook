<template>
	<div class="section-recipe" style="background-color:white;padding-top:1px;">
		<center class="menu" id="recipetitle">
			<h1 style="margin-top: 28px;">{{recipe.recipeNmKo}}</h1>
			<h6 style="text-align:right;width:80%;max-width:550px;">음성으로 블로쿡 이용하기&nbsp;<i class="fa fa-bullhorn"></i>&nbsp;
				<button class="btn" id="popover-target-1" style="font-weight:bold;">사용법</button>
			</h6>
			<b-popover :show.sync="show" target="popover-target-1" triggers="hover" placement="bottomleft" style="width:300px;">
				<br>
				<b style="font-family: 'Poor Story', cursive;"><h5 style="color:#F77479;">&emsp;이렇게 말해보세요 :)&emsp;</h5></b><br>
				<b style="font-family: 'Poor Story', cursive;"><h6 style="color:#FABA5F;">&emsp;<i class="fa fa-volume-up"></i> 음성인식 이용 방법 <i class="fa fa-volume-up"></i>&emsp;</h6></b>
				<p style="font-family: 'Poor Story', cursive;">&emsp;<i class="fa fa-microphone-slash"></i> 음성인식 종료 :&emsp;"마이크 꺼줘" &nbsp;</p>
				<p style="font-family: 'Poor Story', cursive;">&emsp;<i class="fa fa-toggle-right"></i> 다음 페이지 :&emsp;"다음"</p>
				<p style="font-family: 'Poor Story', cursive;">&emsp;<i class="fa fa-toggle-left"></i> 이전 페이지 :&emsp;"이전"</p>
				<p style="font-family: 'Poor Story', cursive;">&emsp;<i class="fa fa-refresh"></i> 레시피 Replay :&emsp;"다시"</p>
				<p style="font-family: 'Poor Story', cursive;">&emsp;<i class="fa fa-eye"></i> 레시피 보기 :&emsp;"레시피"</p>
				<b style="font-family: 'Poor Story', cursive;"><h6 style="color:#FABA5F;">&emsp;<i class="fa fa-clock-o"></i> 타이머 이용 방법 <i class="fa fa-clock-o"></i>&emsp;</h6></b>
				<p style="font-family: 'Poor Story', cursive;">&emsp;<i class="	fa fa-eye"></i> 타이머 보기 :&emsp;"타이머"</p>
				<p style="font-family: 'Poor Story', cursive;">&emsp;<i class="fa fa-check-circle"></i> 타이머 시작 :&emsp;"타이머 시작"</p>
				<p style="font-family: 'Poor Story', cursive;">&emsp;<i class="fa fa-minus-circle"></i> 타이머 정지 :&emsp;"타이머 정지"</p>
				<p style="font-family: 'Poor Story', cursive;">&emsp;<i class="fa fa-history"></i> 타이머 Reset :&emsp;"타이머 리셋"</p>
			</b-popover>
		</center>

		<div id="carousel">
				<carousel ref="carousel" :loop="true" :perPage="1" :navigationEnabled="true" :centerMode="true" :navigateTo="slideIndex" v-on:pageChange="pageChange">
						<!--------------------------- 레시피 기본정보 --------------------------->
						<slide>
								<center class="menu">
									<img class="d-block" :src="imgUrl" alt="Second slide">
								</center>

								<center class="menu">
								<p style="width:90%;max-width:550px;padding-bottom:35px;">
									<button class="btn" id="popover-target-2" @click="record" style="background-color:#F77479;float:left;"><i class="fa fa-microphone"></i> ON</button>
									<b-popover target="popover-target-2" triggers="hover" placement="bottomright" style="padding-top:20px;">
										<p style="font-family: 'Poor Story', cursive;padding-bottom:0px;">음성인식 켜기</p>
									</b-popover>
									<button class="btn" id="popover-target-3" @click="stop" style="background-color:#FABA5F;float:left;"><i class="fa fa-microphone-slash"></i> OFF</button>
									<b-popover target="popover-target-3" triggers="hover" placement="bottomright" style="padding-top:20px;">
										<p style="font-family: 'Poor Story', cursive;padding-bottom:0px;">음성인식 끄기</p>
									</b-popover>
									<!-- 좋아요 누르기 전 -->
									<button class="btn" style="color:red;font-weight:bold;float:right;" @click="addBookmark()" v-if="like == false && this.$store.state.user.authorized == true">
										Like&nbsp;<i class="fa fa-heart-o"></i>
									</button>
									<!-- 좋아요 누른 후 -->
									<button class="btn" style="color:red;font-weight:bold;float:right;" @click="rmBookmark()" v-else-if="like == true && this.$store.state.user.authorized == true">
										Like&nbsp;<i class="fa fa-heart"></i>
									</button>
								</p> 
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
											</div>
										</b-tab>
										<b-tab v-if="cooking.timerYN=='Y'" title="타이머" id="timer">
											<Timer :cooking="cooking" />
										</b-tab>
										</b-tabs>
									</b-card>
								</div>
			<p style="margin-left:30px;width:80%;text-align:left;max-width:550px;">
				<button class="btn" @click="record" style="background-color:#F77479;"><i class="fa fa-microphone"></i> ON</button>
				<button class="btn" @click="stop" style="background-color:#FABA5F;"><i class="fa fa-microphone-slash"></i> OFF</button>
			</p> 
					</slide>
			</carousel>
		</div>
	</div>
</template>

<script>
import http from "@/util/http-common.js";
import Timer from '@/components/recipe/Timer.vue'

var audio;
var speechRecognition;

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
			micStat: true,
			show: false
		};
	},
	watch:{
		slideIndex: function() {
			this.$store.state.audio.pause();
			this.$store.state.audio.currentTime = 0;
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

		//STT
		if (typeof webkitSpeechRecognition === 'function') {
			this.stop();
			this.$store.state.recognition.speechRecognition = null;
			this.$store.state.recognition.speechRecognition = new webkitSpeechRecognition();

			this.$store.commit('recognition/setContinuous', true);
			this.$store.commit('recognition/setMaxAlterNatives', 3);
			this.$store.commit('recognition/setOnResult', event => {
				var text = event.results[event.results.length-1][0].transcript.trim();
				// console.log("say: "+text);
				this.speechHandler(text);
			});
			this.$store.commit('recognition/setOnEnd', 'start');
			this.record();
		}
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
			if (typeof webkitSpeechRecognition === 'function') {
				this.stop();
				this.$store.commit('recognition/setOnEnd', 'start');
				this.$store.state.recognition.speechRecognition.start();
			}
		},
		stop(){
			if (typeof webkitSpeechRecognition === 'function') {
				this.$store.commit('recognition/setOnEnd', null);
				this.$store.state.recognition.speechRecognition.stop();
			}
		},
		textToSpeech(sumary) {
			this.$store.state.audio.pause();
			this.$store.state.audio.currentTime = 0;
			http
				.post('/recipes/tts', {
					content: sumary
				},{
					responseType: "blob"
				})
				.then(({ data }) => {
					// console.log(data);
					var blob=new Blob([data], {type : 'audio/ogg'});
					const audioUrl = URL.createObjectURL(blob);
					this.$store.state.audio = null
					this.$store.state.audio = new Audio(audioUrl);
					audio = this.$store.state.audio;
					this.$store.state.audio.play();
				})
				.catch((error) => {
					alert('처리 실패하였습니다.');
					console.log(error);
				})
		},
		speechHandler(text){
			if(text.endsWith("다음")) {
				if(this.slideIndex != this.cookingsLen){
					this.slideIndex++;
				}
			} else if(text.endsWith("이전")) {
				if(this.slideIndex != 0){
					this.slideIndex--;
				}
			} else if(text.endsWith("다시")) {
				if(this.slideIndex != 0){
					this.textToSpeech(this.cookings[this.slideIndex-1].cookingDc);
				} else {
					this.textToSpeech(this.recipe.recipeNmKo);
				}
			} else if(text.endsWith("레시피")){
				if(this.slideIndex != 0){
					var ele = document.querySelectorAll(".card ul #recipe___BV_tab_button__")[this.slideIndex-1];
					ele.click();
				}
			} else if(text.endsWith("타이머")) {
				if(this.slideIndex != 0){
					var ele = document.querySelectorAll(".card ul")[this.slideIndex-1].childNodes[2].firstChild;
					if(ele != null)
						ele.click();
				}
			} else if(text.endsWith("타이머 시작")) {
				if(this.slideIndex != 0){
					var ele1 = document.querySelectorAll(".card ul")[this.slideIndex-1].childNodes[2].firstChild;
					if(ele1 != null){
						var ele2 = document.querySelectorAll(".tab-content")[this.slideIndex-1].childNodes[1].childNodes[0].childNodes[1].childNodes[0];
						if(ele2.nodeName == 'BUTTON')
							ele2.click();
					}
				}
			} else if(text.endsWith("타이머 정지")) {
				if(this.slideIndex != 0){
					var ele1 = document.querySelectorAll(".card ul")[this.slideIndex-1].childNodes[2].firstChild;
					if(ele1 != null){
						var ele2 = document.querySelectorAll(".tab-content")[this.slideIndex-1].childNodes[1].childNodes[0].childNodes[1].childNodes[1];
						if(ele2.nodeName == 'BUTTON')
							ele2.click();
					}
				}
			} else if(text.endsWith("타이머 리셋")){
				if(this.slideIndex != 0){
					var ele1 = document.querySelectorAll(".card ul")[this.slideIndex-1].childNodes[2].firstChild;
					if(ele1 != null){
						var ele2 = document.querySelectorAll(".tab-content")[this.slideIndex-1].childNodes[1].childNodes[0].childNodes[1].childNodes[2];
						if(ele2.nodeName == 'BUTTON')
							ele2.click();
					}
				}
			} else if(text.endsWith("마이크 꺼 줘")){
				if (typeof webkitSpeechRecognition === 'function') {
					this.$store.commit('recognition/setOnEnd', null);
					this.$store.state.recognition.speechRecognition.stop();
				}
			} else if(text.endsWith("사용법")) {
				this.show = true
				setTimeout(() => {
					this.show = false;
				},4000)
			} else {
				this.textToSpeech("정확하게 말씀해주세요.");
			} 
		}
	}
}
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Nanum+Gothic+Coding:wght@400;700&family=Poor+Story&display=swap');
@media(max-width: 991px) {
	.section-recipe {
		margin-top: 58px;
		margin-left:5vw;
		margin-right:5vw;
	}
}
@media(min-width: 991px) {
	.section-recipe {
		margin-top: 92px;
		margin-left:20vw;
		margin-right:20vw;
	}
}
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
	background-image: url("../../assets/blocookImg/cook.png");
	background-size: 92%;
	background-repeat: no-repeat;
  background-position: center;
	max-width: 600px;
}
.VueCarousel-slide {
  flex-basis: inherit;
}
</style>