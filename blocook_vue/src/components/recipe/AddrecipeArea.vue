<template>
	<div style="margin-bottom: 40px;">
		<center class="menu">
			<img src="@/assets/img/addimage.jpg">
			<div>
				<button class="button btn" style="width:180px;">대표 이미지 등록&nbsp;<i class="fa fa-camera"></i></button>
			</div>
		</center>

		<div class="container inputbox">
			<table>
			<tbody>
				<tr>
					<th>
						<h6>레시피명</h6>
					</th>
					<td>
						<b-form-input v-model="recipenm" size="sm" placeholder="레시피명 입력"></b-form-input>
					</td>
				</tr>
				<tr>
					<th>
						<h6>주재료</h6>
					</th>
					<td>
						<b-form-input v-model="mainirdnt" list="my-list-id" size="sm" placeholder="필수재료 입력"></b-form-input>
						<datalist id="my-list-id">
							<option v-for="(irdnt, index) in irdnts" :key="index">{{ irdnt }}</option>
						</datalist>
					</td>
				</tr>
				<tr>
					<th>
						<h6>부재료</h6>
					</th>
					<td>
						<b-form-input v-model="subirdnt" list="my-list-id" size="sm" placeholder="선택재료 입력"></b-form-input>
						<datalist id="my-list-id">
							<option v-for="(irdnt, index) in irdnts" :key="index">{{ irdnt }}</option>
						</datalist>
					</td>
				</tr>
				<tr>
					<th>
						<h6>양념</h6>
					</th>
					<td>
						<b-form-input v-model="seasoningirdnt" list="my-list-id" size="sm" placeholder="선택재료 입력"></b-form-input>
						<datalist id="my-list-id">
							<option v-for="(irdnt, index) in irdnts" :key="index">{{ irdnt }}</option>
						</datalist>
					</td>
				</tr>
				<tr>
					<th>
						<h6>레시피</h6>
						<h6>개요</h6>
					</th>
					<td>
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

		<div class="container">
			<table>
			<tbody>
				<tr>
					<th>
						<h6>카테고리</h6>
					</th>
					<th>
						<h6>종류</h6>
					</th>
				</tr>
				<tr class="shortbox">
					<td>
    				<b-form-select v-model="selectedcat" :options="categories" size="sm"></b-form-select>
					</td>
					<td>
    				<b-form-select v-model="selectedty" :options="options" size="sm"></b-form-select>
					</td>
				</tr>
				<tr>
					<th>
						<h6>소요 시간(분)</h6>
					</th>
					<th>
						<h6>칼로리(kcal)</h6>
					</th>
				</tr>
				<tr>
					<td>
						<b-form-input v-model="time" size="sm" type="number" placeholder="숫자만 입력"></b-form-input>
					</td>
					<td>
						<b-form-input v-model="kcal" size="sm" type="number" placeholder="숫자만 입력"></b-form-input>
					</td>
				</tr>
				<tr>
					<th>
						<h6>인분(명)</h6>
					</th>
					<th>
						<h6>난이도 : {{ level.text }}</h6>
					</th>
				</tr>
				<tr>
					<td>
						<b-form-input v-model="person" size="sm" type="number" placeholder="숫자만 입력"></b-form-input>
					</td>
					<td>
    				<b-form-input v-on:change="flevel" v-model="level.value" type="range" min="1" max="3"></b-form-input>
					</td>
				</tr>
			</tbody>
			</table>
		</div>

		<div class="user" style="margin-top:15px; margin-right:30px;">
			<button class="button btn" @click="checkHandler" style="width:180px; margin-bottom:30px;">레시피 상세 등록&nbsp;<i class="fa fa-arrow-right"></i></button>
		</div>
	</div>
</template>

<script>
  import http from "@/util/http-common.js";

  export default {
    data() {
      return {
		//form 정보
		recipenm: '',
		mainirdnt: '',
		subirdnt: '',
		seasoningirdnt: '',
		recipedc: '',
		selectedcat: '',
		selectedty: '',
		time : '',
		kcal : '',
		person : '',
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
		level: { value: '2', text: '보통'},
      }
	},
	created(){
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
		flevel: function() {
			if(this.level.value == '1') {
				this.level.text = '초보환영';
			}	else if(this.level.value == '2') {
				this.level.text = '보통';
			}	else if(this.level.value == '3') {
				this.level.text = '어려움';
			}
		},
		checkHandler: function() {
			let err = true;
			let msg = '';
			!this.recipenm && (msg = '레시피 이름을 입력해주세요', err = false);
			err && !this.mainirdnt && ((msg = '주재료를 입력해주세요'), (err = false));
			err && !this.subirdnt && ((msg = '부재료를 입력해주세요'), (err = false));
			err && !this.seasoningirdnt && ((msg = '양념 재료를 입력해주세요'), (err = false));
			err && !this.recipedc && ((msg = '레시피 설명을 입력해주세요'), (err = false));
			err && !this.selectedcat && ((msg = '카테고리를 선택해주세요'), (err = false));
			err && !this.selectedty && ((msg = '종류를 선택해주세요'), (err = false));

			if (!err) alert(msg);
			else {
				this.saveHandler();
			}
		},
		saveHandler : function(){
			http
				.post("/recipes/add/recipe",{ // 레시피 기본정보 추가 완료
					recipe: {
						recipeNmKo: this.recipenm,
						sumry: this.recipedc,
						nationNm: this.selectedcat,
						tyNm: this.selectedty,
						cookingTime: this.time,
						calorie: this.kcal,
						qnt: this.person,
						levelNm: this.level.text
					},
					irdnts:[{
						irdntNm: this.mainirdnt,
						irdntTyNm: '주재료'
					},
					{
						irdntNm: this.subirdnt,
						irdntTyNm: '부재료'
					},
					{
						irdntNm: this.seasoningirdnt,
						irdntTyNm: '양념'
					}]
				})
        .then(({ data }) => {
					this.$store.dispatch('recipe/GET_RECIPE_ID', { payload: data }) // store 반영
					alert("레시피 기본정보가 등록 완료되었습니다. 레시피 상세를 등록해주세요.");
					this.$router.push('addrecipedetail');
				})
				.catch((error) => {
						console.dir(error);
				});
		}
	}
  }
</script>


<style scoped>
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
	width: 86%;
	margin-top: 20px;
}
th {
	width: 30%;
	text-align: left;
	vertical-align: middle;
}
.inputbox td {
	vertical-align: top;
	padding-left: 2%;
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
.user {
	float: right;
}
</style>