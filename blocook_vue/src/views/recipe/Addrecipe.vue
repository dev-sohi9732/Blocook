<template>
  <div class="mainview">
    <b-card no-body>
			<b-tabs pills card>
				<!------------------------------------------------ 레시피 기본 정보 ------------------------------------------------>
				<b-tab title="레시피 기본 정보" active>
					<b-card-text>
            <center class="menu">
              <input type="file" @change="previewGibonImage" accept="image/*" style="width:223px;">
              <img :src="gibonpicture.picture">
              <div>
                <p style="margin-top:15px;">Uploading : {{gibonpicture.uploadValue.toFixed() + "%"}}
                  <progress :value="gibonpicture.uploadValue" max="100"></progress>
                </p>
                <button class="button btn" @click="onUploadGibon" style="width:185px;">
                  대표 이미지 등록&nbsp;<i class="fa fa-camera"></i>
                </button>
              </div>
            </center>

            <div class="container inputbox">
              <table>
              <tbody>
                <tr>
                  <th>
                    <h6>레시피명</h6>
                  </th>
                  <td colspan="3">
                    <b-form-input v-model="recipenm" size="sm" placeholder="레시피명 입력"></b-form-input>
                  </td>
                </tr>
                <tr>
                  <th>
                    <h6>주재료</h6>
                  </th>
                  <td>
                    <b-form-input v-model="mainirdnt" list="my-list-id" size="sm" placeholder="필수재료"></b-form-input>
                    <datalist id="my-list-id">
                      <option v-for="(irdnt, index) in irdnts" :key="index">{{ irdnt }}</option>
                    </datalist>
                  </td>
                  <td style="width:25%;">
                    <b-form-input v-model="mainirdnt_amount" size="sm" placeholder="양(단위)"></b-form-input>
                  </td>
                  <td>
                    <button class="btn" @click="addMainIrdnt()"><i class="fa fa-plus" style="color:blue;"></i></button>
                  </td>
                </tr>
                <tr v-for="(mainirdnt, i) in arr_mainirdnt" :key="i">
                  <td></td>
                  <td colspan="3">
                    <p style="margin-bottom:8px;">
                      <i class="fa fa-minus" @click="rmMainIrdnt(i)" style="color:red;"></i>
                      &nbsp;{{ mainirdnt.mainirdnt_nm }}&nbsp;{{ mainirdnt.mainirdnt_vol }}
                    </p>
                  </td>
                </tr>
                <tr>
                  <th>
                    <h6>부재료</h6>
                  </th>
                  <td>
                    <b-form-input v-model="subirdnt" list="my-list-id" size="sm" placeholder="선택재료"></b-form-input>
                    <datalist id="my-list-id">
                      <option v-for="(irdnt, index) in irdnts" :key="index">{{ irdnt }}</option>
                    </datalist>
                  </td>
                  <td>
                    <b-form-input v-model="subirdnt_amount" size="sm" placeholder="양(단위)"></b-form-input>
                  </td>
                  <td>
                    <button class="btn" @click="addSubIrdnt()"><i class="fa fa-plus" style="color:blue;"></i></button>
                  </td>
                </tr>
                <tr v-for="(subirdnt, i) in arr_subirdnt" :key="`A-${i}`">
                  <td></td>
                  <td colspan="3">
                    <p style="margin-bottom:8px;">
                      <i class="fa fa-minus" @click="rmSubIrdnt(`A-${i}`)" style="color:red;"></i>
                      &nbsp;{{ subirdnt.subirdnt_nm }}&nbsp;{{ subirdnt.subirdnt_vol }}
                    </p>
                  </td>
                </tr>
                <tr>
                  <th>
                    <h6>양념</h6><h6 style="color:rgb(91, 183, 251);">(선택)</h6>
                  </th>
                  <td>
                    <b-form-input v-model="seasoningirdnt" list="my-list-id" size="sm" placeholder="양념"></b-form-input>
                    <datalist id="my-list-id">
                      <option v-for="(irdnt, index) in irdnts" :key="index">{{ irdnt }}</option>
                    </datalist>
                  </td>
                  <td>
                    <b-form-input v-model="seasoningirdnt_amount" size="sm" placeholder="양(단위)"></b-form-input>
                  </td>
                  <td>
                    <button class="btn" @click="addSeasoningIrdnt()"><i class="fa fa-plus" style="color:blue;"></i></button>
                  </td>
                </tr>
                <tr v-for="(seasoningirdnt, i) in arr_seasoningirdnt" :key="`B-${i}`">
                  <td></td>
                  <td colspan="3">
                    <p style="margin-bottom:8px;">
                      <i class="fa fa-minus" @click="rmSeasoningIrdnt(`B-${i}`)" style="color:red;"></i>
                      &nbsp;{{ seasoningirdnt.seasoningirdnt_nm }}&nbsp;{{ seasoningirdnt.seasoningirdnt_vol }}
                    </p>
                  </td>
                </tr>
                <tr>
                  <th>
                    <h6>레시피</h6><h6>개요</h6>
                  </th>
                  <td colspan="3">
                    <b-form-textarea
                      v-model="recipedc"
                      id="textarea-small"
                      size="sm"
                      placeholder="레시피에 대해 간단히 설명해주세요."
                    ></b-form-textarea>
                  </td>
                </tr>
              </tbody>
              </table>
            </div>

            <div class="container selectbox">
              <table>
              <tbody>
                <tr>
                  <th><h6 style="margin-bottom: 8px;">카테고리</h6></th>
                  <th><h6 style="margin-bottom: 8px;">종류</h6></th>
                </tr>
                <tr class="shortbox">
                  <td><b-form-select v-model="selectedcat" :options="categories" size="sm"></b-form-select></td>
                  <td><b-form-select v-model="selectedty" :options="options" size="sm"></b-form-select></td>
                </tr>
                <tr>
                  <th><h6 style="margin-bottom: 8px;">소요 시간(분)</h6></th>
                  <th><h6 style="margin-bottom: 8px;">칼로리(kcal)</h6></th>
                </tr>
                <tr>
                  <td><b-form-input v-model="time" size="sm" type="number" min="0" placeholder="숫자만 입력"></b-form-input></td>
                  <td><b-form-input v-model="kcal" size="sm" type="number" min="0" placeholder="숫자만 입력"></b-form-input></td>
                </tr>
                <tr>
                  <th><h6 style="margin-bottom: 8px;">인분(명)</h6></th>
                  <th><h6 style="margin-bottom: 8px;">난이도 : {{ level.text }}</h6></th>
                </tr>
                <tr>
                  <td><b-form-input v-model="person" size="sm" type="number" min="0" placeholder="숫자만 입력"></b-form-input></td>
                  <td><b-form-input v-on:change="flevel" v-model="level.value" type="range" min="1" max="3"></b-form-input></td>
                </tr>
              </tbody>
              </table>
            </div>
          </b-card-text>
				</b-tab>

				<!------------------------------------------------ 레시피 과정 정보 ------------------------------------------------>
				<b-tab title="레시피 과정 정보">
					<b-card-text>
            <div class="step" v-for="step in steps" v-bind:key="step.cookingNo">
              <center class="menu">
                <h5>STEP {{ steps.indexOf(step) + 1 }}</h5>
                <input type="file" @change="previewCookingImage($event, steps.indexOf(step))" accept="image/*" style="width:223px;margin-top:15px;">
                <img :src="steps[steps.indexOf(step)].streStepImageUrl">
                <div>
                  <p style="margin-top:15px;">Uploading : {{cookingpicture[steps.indexOf(step)].uploadValue.toFixed() + "%"}}
                    <progress :value="cookingpicture[steps.indexOf(step)].uploadValue" max="100"></progress>
                  </p>
                  <button class="button btn" @click="onUploadCooking(steps.indexOf(step))" style="width:185px;">
                    과정 이미지 등록&nbsp;<i class="fa fa-camera"></i>
                  </button>
                </div>
              </center>

              <div class="container inputbox">
                <table>
                <tbody>
                  <tr>
                    <th>
                      <h6 style="margin-bottom:5px;">{{ steps.indexOf(step) + 1 }}단계 설명</h6>
                    </th>
                  </tr>
                  <tr>
                    <td>
                      <b-form-textarea
                        v-model="step.cookingDc"
                        id="textarea"
                        placeholder="레시피 상세 설명을 입력해주세요."
                      ></b-form-textarea>
                    </td>
                  </tr>
                </tbody>
                </table>
              </div>
              <!-- v-if="step.timerYN == 'Y'" -->
              <div class="container" style="margin-top:10px;" v-if="step.timerYN == 'Y'">
                <div class="timer-container">
                  <img src="@/assets/img/stopwatch.png" id="stopwatch">
                  <table>
                    <tr>
                      <td id="time"><b-form-input size="sm" type="number" min="0" placeholder="시" style="width:50px;"></b-form-input></td>
                      <td style="width:5px;vertical-align:middle;">:</td>
                      <td id="time"><b-form-input size="sm" type="number" min="0" placeholder="분" style="width:50px;"></b-form-input></td>
                      <td style="width:5px;vertical-align:middle;">:</td>
                      <td id="time"><b-form-input size="sm" type="number" min="0" placeholder="초" style="width:50px;"></b-form-input></td>
                    </tr>
                  </table>
                </div>
              </div>

              <div class="minusbutton">
                <button @click="removestep(step)" id="minusbtn" class="button btn" v-if="minus"><i class="fa fa-minus"></i></button>
              </div>

              <div class="timerbutton">
                <button @click="addtimer(steps.indexOf(step))" id="plusbtn" class="button btn" style="background-color:rgb(150, 111, 193);"><i class="fa fa-clock-o"></i></button>
              </div>
            </div>

            <div class="addbutton">
              <button @click="addstep()" id="plusbtn" class="button btn"><i class="fa fa-plus"></i></button>
            </div>
          </b-card-text>
				</b-tab>
			</b-tabs>
		</b-card>

    <div class="user">
			<button class="button btn" @click="checkHandler()" style="width:190px; margin-bottom:30px; background-color:rgb(241, 196, 15);">
        <i class="fa fa-check"></i>&nbsp;레시피 등록 완료
      </button>
		</div>
  </div>
</template>

<script>
import http from "@/util/http-common.js";
import firebase from 'firebase';

export default {
  name: 'addrecipe',
  data() {
    return {
      title: "addrecipe page",
      minus: false,
      steps: [
        {cookingNo: 1, cookingDc: "", streStepImageUrl: require('@/assets/img/addimage.jpg'), timerYN: "N", timerTime: 0}
      ],
      addRecipeId: '',

      //form 정보
      recipenm: '',
      mainirdnt: '',
      mainirdnt_amount: '',
      subirdnt: '',
      subirdnt_amount: '',
      seasoningirdnt: '',
      seasoningirdnt_amount: '',
      recipedc: '',
      selectedcat: null,
      selectedty: null,
      time : '',
      kcal : '',
      person : '',

      arr_mainirdnt: [],
      arr_subirdnt: [],
      arr_seasoningirdnt: [],
      all_irdnts: [],

      //select 박스 정보
      irdnts: [],
      options: [
        { value: null, text: '종류 선택' },
        { value: '밥', text: '밥' },
        { value: '국', text: '국' },
        { value: '조림', text: '조림' },
        { value: '구이', text: '구이' },
        { value: '튀김/커틀릿', text: '튀김/커틀릿' },
        { value: '찜', text: '찜' },
        { value: '나물/생채/샐러드', text: '나물/생채/샐러드' },
        { value: '밑반찬/김치', text: '밑반찬/김치' },
        { value: '기타', text: '기타' },
      ],
      categories: [
        { value: null, text: '카테고리 선택' },
        { value: '한식', text: '한식' },
        { value: '서양', text: '서양' },
        { value: '이탈리아', text: '이탈리아' },
        { value: '일본', text: '일본' },
        { value: '중국', text: '중국' },
        { value: '동남아시아', text: '동남아시아' },
        { value: '퓨전', text: '퓨전' },
        { value: '기타', text: '기타' },
		  ],
      units: [
        { value: null, text: '단위' },
        { value: 'g', text: 'g' },
		  ],
      level: { value: '2', text: '보통'},
      
      //image
      gibonpicture: {picture: require('@/assets/img/addimage.jpg'), uploaded: false, imageData: null, uploadValue: 0},
      cookingpicture: [
        {uploaded: false, imageData: null, uploadValue: 0}
      ],
    }
  },
  created() {
		//재료 데이터 가져오기
		http
		.post("/recipes/get/irdnts")
		.then(({ data }) => {
			this.irdnts = Array.from(data);
		})
		.catch((error) => {
			console.dir(error);
		});
	},
	methods: {
    previewGibonImage(event) {
			this.gibonpicture.uploadValue = 0;
			this.gibonpicture.imageData = event.target.files[0];
    },
    onUploadGibon() {
			const storageRef = firebase.storage().ref(`${this.gibonpicture.imageData.name}`).put(this.gibonpicture.imageData);
			storageRef.on(`state_changed`, snapshot => {
					this.gibonpicture.uploadValue = (snapshot.bytesTransferred/snapshot.totalBytes) * 100;
				}, error => {console.log(error.message)},
				() => {this.gibonpicture.uploadValue = 100;
					storageRef.snapshot.ref.getDownloadURL().then((url) => {
            this.gibonpicture.picture = url;
            this.gibonpicture.uploaded = true;
					});
				}
			);
    },
    previewCookingImage(event, index) {
			this.cookingpicture[index].uploadValue = 0;
			this.cookingpicture[index].imageData = event.target.files[0];
    },
    onUploadCooking(index) {
			const storageRef = firebase.storage().ref(`${this.cookingpicture[index].imageData.name}`).put(this.cookingpicture[index].imageData);
			storageRef.on(`state_changed`, snapshot => {
					this.cookingpicture[index].uploadValue = (snapshot.bytesTransferred/snapshot.totalBytes) * 100;
				}, error => {console.log(error.message)},
				() => {this.cookingpicture[index].uploadValue = 100;
					storageRef.snapshot.ref.getDownloadURL().then((url) => {
            this.steps[index].streStepImageUrl = url;
            this.cookingpicture[index].uploaded = true;
					});
				}
			);
		},
    addMainIrdnt() {
      let err = true;
      let msg = '';

      !this.mainirdnt && (msg = '주재료를 입력해주세요', err = false);
      err && !this.mainirdnt_amount && ((msg = '주재료 양을 입력해주세요'), (err = false));

      if (!err) alert(msg);
			else {
        this.arr_mainirdnt.push({mainirdnt_nm:this.mainirdnt, mainirdnt_vol:this.mainirdnt_amount});
        this.mainirdnt = "";
        this.mainirdnt_amount = "";
      }
    },
    rmMainIrdnt(index) {
      this.arr_mainirdnt.splice(index, 1);
    },
    addSubIrdnt() {
      let err = true;
      let msg = '';

      !this.subirdnt && (msg = '부재료를 입력해주세요', err = false);
      err && !this.subirdnt_amount && ((msg = '부재료 양을 입력해주세요'), (err = false));

      if (!err) alert(msg);
			else {
        this.arr_subirdnt.push({subirdnt_nm:this.subirdnt, subirdnt_vol:this.subirdnt_amount});
        this.subirdnt = "";
        this.subirdnt_amount = "";
      }
    },
    rmSubIrdnt(index) {
      this.arr_subirdnt.splice(index, 1);
    },
    addSeasoningIrdnt() {
      let err = true;

      !this.seasoningirdnt && (err = false);
      err && !this.seasoningirdnt_amount && (err = false);

			if(err) {
        this.arr_seasoningirdnt.push({seasoningirdnt_nm:this.seasoningirdnt, seasoningirdnt_vol:this.seasoningirdnt_amount});
        this.seasoningirdnt = "";
        this.seasoningirdnt_amount = "";
      }
    },
    rmSeasoningIrdnt(index) {
      this.arr_seasoningirdnt.splice(index, 1);
    },
    flevel: function() {
			if(this.level.value == '1') {
				this.level.text = '초보환영';
			}	else if(this.level.value == '2') {
				this.level.text = '보통';
			}	else if(this.level.value == '3') {
				this.level.text = '어려움';
			}
		},
    addtimer(timerstep) {
      if(this.steps[timerstep].timerYN == "N") this.steps[timerstep].timerYN = "Y";
      else if(this.steps[timerstep].timerYN == "Y") this.steps[timerstep].timerYN = "N";
		},
    addstep() {
      this.cookingpicture.push({uploaded: false, imageData: null, uploadValue: 0});
			this.steps.push({cookingNo:this.steps.length + 1, cookingDc:"", streStepImageUrl: require('@/assets/img/addimage.jpg'), timerYN:"N", timerTime:0});
			this.minus = true;
		},
		removestep(rmstep) {
			this.steps.splice(this.steps.indexOf(this.steps.length), 1);
			if(this.steps.length == 1) this.minus = false;
		},
    checkHandler() {
			let err = true;
      let msg = '';
      
      for(var i = 0; i < this.steps.length; i++) {
        !this.steps[i].cookingDc && (msg = '레시피 과정 정보를 입력해주세요.', err = false);
        !this.cookingpicture[i].uploaded && (msg = '레시피 과정 이미지를 등록해주세요.', err = false);
      }
      
			!this.recipenm && (msg = '레시피 이름을 입력해주세요.', err = false);
			err && this.arr_mainirdnt.length == 0 && ((msg = '주재료를 입력해주세요.'), (err = false));
      err && this.arr_subirdnt.length == 0 && ((msg = '부재료를 입력해주세요.'), (err = false));
      //양념은 입력하지 않아도 됨
			//err && this.arr_seasoningirdnt.length == 0 && ((msg = '양념 재료를 입력해주세요.'), (err = false));
			err && !this.recipedc && ((msg = '레시피 설명을 입력해주세요.'), (err = false));
			err && !this.selectedcat && ((msg = '카테고리를 선택해주세요.'), (err = false));
      err && !this.selectedty && ((msg = '종류를 선택해주세요.'), (err = false));
      err && !this.gibonpicture.uploaded && ((msg = '대표 이미지를 등록해주세요.'), (err = false));

			if (!err) alert(msg);
			else {
				this.saveGibonHandler();
			}
    },
    saveGibonHandler() {
      for(var i = 0; i < this.arr_mainirdnt.length; i++) {
        this.all_irdnts.push({
          irdntNm: this.arr_mainirdnt[i].mainirdnt_nm,
          irdntCpcty: this.arr_mainirdnt[i].mainirdnt_vol,
          irdntTyCode: 3060001,
          irdntTyNm: '주재료'
        });
      }
      for(var i = 0; i < this.arr_subirdnt.length; i++) {
        this.all_irdnts.push({
          irdntNm: this.arr_subirdnt[i].subirdnt_nm,
          irdntCpcty: this.arr_subirdnt[i].subirdnt_vol,
          irdntTyCode: 3060002,
          irdntTyNm: '부재료'
        });
      }
      for(var i = 0; i < this.arr_seasoningirdnt.length; i++) {
        this.all_irdnts.push({
          irdntNm: this.arr_seasoningirdnt[i].seasoningirdnt_nm,
          irdntCpcty: this.arr_seasoningirdnt[i].seasoningirdnt_vol,
          irdntTyCode: 3060003,
          irdntTyNm: '양념'
        });
      }
      console.log(this.all_irdnts);
      
      http
      .post("/recipes/add/recipe",{ // 레시피 기본 정보 등록
        recipe: {
          recipeNmKo: this.recipenm,
          sumry: this.recipedc,
          nationNm: this.selectedcat,
          tyNm: this.selectedty,
          cookingTime: this.time,
          calorie: this.kcal,
          qnt: this.person,
          levelNm: this.level.text,
          userUid: this.$store.state.user.uid,
          imgUrl: this.gibonpicture.picture
        },
        irdnts: this.all_irdnts
      })
      .then(({ data }) => {
        this.addRecipeId = data;
        this.saveCookingHandler(this.addRecipeId);
      })
      .catch((error) => {
          console.dir(error);
      });
    },
    saveCookingHandler(recipeId) {
      http
      .post("/recipes/add/cookings",{ // 레시피 상세 정보 등록
        recipeId: recipeId,
        cookings: this.steps
      })
      .then(({ data }) => {
        alert("레시피가 등록 완료되었습니다.");
        this.$router.push('myrecipe');
      })
      .catch((error) => {
          console.dir(error);
      });
    }
  }
}
</script>

<style scoped>
.user {
  margin: 15px auto;
  text-align: center;
}
.mainview {
  padding-top: 80px;
}
center {
	margin-top: 10px;
	font-weight: bold;
}
img {
	margin-top: 10px;
	max-width: 80%;
	height: auto;
}
h1, h4, h5, h6 {
	font-weight: bold;
}
.container {
	max-width: 500px;
  margin-top: 20px;
  text-align: center;
}
.timer-container {
  display: inline-block;
  width: 50%;
}
th {
	width: 20%;
	text-align: left;
	vertical-align: middle;
}
.inputbox td {
	vertical-align: top;
  text-align: left;
}
td {
	vertical-align: top;
}
a {
	float: right;
}
.button {
	width : 268px;
	background-color: rgb(45, 180, 0); 
	margin-top: 10px;
	color:white;
	font-weight: bold;
}
table {
	width: 100%;
}
select {
	margin-top: auto;
}
.shortbox select, input {
	margin-bottom: 8px;
}
#textarea {
	height: 150px;
}
.button {
	width : 170px;
	margin-top: 10px;
	color:white;
	font-weight: bold;
}
.addbutton {
  margin: 0px 30px 0px 30px;
  float: left;
}
.timerbutton {
  margin: 0px 30px 0px 30px;
  float: none;
}
#plusbtn {
	border-radius: 50%;
	width:40px;
	margin-right: 5px;
  float: none;
}
#minusbtn {
	background-color: rgb(255, 93, 72); 
	border-radius: 50%;
	width:40px;
  float: none;
}
.minusbutton {
	margin: 10px 30px 0px 30px;
}
.card {
  border:none;
}
#stopwatch {
  width: 50px;
  margin-bottom: 10px;
}
#time {
  width: 50px;
  vertical-align: bottom;
}
#imginput {
  margin-top:8px;
}
</style>