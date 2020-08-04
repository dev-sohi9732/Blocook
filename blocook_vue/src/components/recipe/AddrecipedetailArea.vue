<template>
	<div style="margin-bottom: 20px;">
		<div class="step" v-for="step in steps" v-bind:key="step.cookingNo">
			<center class="menu">
				<h5>STEP {{ step.cookingNo }}</h5>
				<img src="@/assets/img/addimage.jpg">
				<div>
					<button class="button btn" style="width:180px;">과정 사진 등록&nbsp;<i class="fa fa-camera"></i></button>
				</div>
			</center>

			<div class="container inputbox">
				<table>
				<tbody>
					<tr>
						<th>
							<h6 style="margin-bottom:5px;">{{ step.cookingNo }}단계 설명</h6>
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
			<div class="minusbutton">
				<button @click="removestep(step)" id="minusbtn" class="button btn" v-if="minus"><i class="fa fa-minus"></i></button>
			</div>
		</div>

		<div class="addbutton">
			<button @click="addstep()" id="plusbtn" class="button btn"><i class="fa fa-plus"></i></button>
			<button @click="checkHandler" class="button btn" style="float:right; border-radius: 5px; background-color: rgb(241, 196, 15); ">
				<i class="fa fa-check"></i>&nbsp;레시피 등록 완료
			</button>
		</div>
	</div>
</template>

<script>
	import http from "@/util/http-common.js";

  export default {
    data() {
      return {
				minus: false,
				steps: [
					{cookingNo: 1, cookingDc: ""}
				],
      }
	},
	methods: {
		addstep() {
			this.steps.push({cookingNo:this.steps.length + 1, cookingDc:""});
			this.minus = true;
		},
		removestep(rmstep) {
			this.steps.splice(this.steps.indexOf(this.steps.length), 1);
			if(this.steps.length == 1) this.minus = false;
		},
		checkHandler: function() {
			let err = true;
			let msg = '';
			for(var i = 0; i < this.steps.length; i++) {
				!this.steps[i].cookingDc  && (msg = '레시피 상세 설명을 입력해주세요', err = false);
			}

			if (!err) alert(msg);
			else {
				this.saveHandler();
			}
		},
		saveHandler : function(){
			http
			.post("/recipes/add/cookings",{ // 레시피 상세 과정 등록
				recipeId: this.$store.state.recipe.addRecipeId,
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
	width: 15%;
	text-align: left;
	vertical-align: middle;
}
.inputbox td {
	vertical-align: top;
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
	margin: 0px 30px 30px 30px;
}
#plusbtn {
	border-radius: 50%;
	width:40px;
	margin-right: 5px;
}
#minusbtn {
	background-color: rgb(255, 93, 72); 
	border-radius: 50%;
	width:40px;
}
.minusbutton {
	margin: 10px 30px 0px 30px;
}
</style>