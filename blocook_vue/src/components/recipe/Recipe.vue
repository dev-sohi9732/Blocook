<template>
	<div style="padding-bottom: 20px;">
		<center class="menu">
			<h1>{{recipe.recipeNmKo}}</h1>
		</center>

		<div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel" data-interval="false">
			<ol class="carousel-indicators">
				<li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
				<li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
				<li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
			</ol>

			<div class="carousel-inner">

				<div class="carousel-item active">
					<center class="menu">
					<img class="d-block" :src="imgUrl" alt="Second slide">
					</center>

					<div class="container">
						<h6 style="margin-bottom: 10px;">{{recipe.nationNm}} > {{recipe.tyNm}}</h6>
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
								</td>
							</tr>
							<tr>
								<th class="list">
									<h6>부재료</h6>
								</th>
								<td v-for="(irdnt,irdnt_key) in irdnts" :key="irdnt_key" v-if="irdnt.irdntTyNm.indexOf('부재료')>-1" style="display:inline;" >
									{{irdnt.irdntNm}}
								</td>
							</tr>
							<tr>
								<th class="list">
									<h6>양념</h6>
								</th>
								<td v-for="(irdnt,irdnt_key) in irdnts" :key="irdnt_key" v-if="irdnt.irdntTyNm.indexOf('양념')>-1" style="display:inline;" >
									{{irdnt.irdntNm}}
								</td>
							</tr>
						</tbody>
						</table>
					</div>
				</div>
					<div class="carousel-item" v-for="(cooking,cooking_key) in cookings" :key="cooking_key">
						<div style="margin-bottom: 20px;">
							<center class="menu">
								<h5 style="margin-top: 20px;">Step{{cooking.cookingNo}}</h5>
								<br>
								<div v-if="cooking.streStepImageUrl.length < 5">
									<img src="@/assets/img/addimage.jpg">
								</div>
								<div v-else>
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
									<tr>
										<td v-if="cooking.stepTip.length >3">
											Tip!! {{cooking.stepTip}}
										</td>
									</tr>
								</tbody>
								</table>
							</div>
						</div>
					</div>
				
			</div>

			<a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
				<span style="background-color:gray; border-radius:10px;" class="carousel-control-prev-icon" aria-hidden="true"></span>
				<span class="sr-only">Previous</span>
			</a>
			<a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
				<span style="background-color:gray; border-radius:10px;" class="carousel-control-next-icon" aria-hidden="true"></span>
				<span class="sr-only">Next</span>
			</a>
		</div>
	</div>
</template>
<script>
import http from "@/util/http-common.js";

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
		cookings: []
		};
	},
	created() {
		const params = new URL(document.location).searchParams;
		http.get(`/recipes/${params.get('Id')}`)
			.then(response => {
			this.recipe = response.data
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
	},
}
</script>
<style scoped>
center {
	margin-top: 10px;
	font-weight: bold;
}
img {
	width: 92%;
	height: 70vw;
}
h1, h4, h5, h6 {
	font-weight: bold;
}
.container {
	width: 92%;
	margin-top: 20px;
	margin-bottom: 10px;
}
th {
	width: 21%;
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
}
.innercard {
	width: 100%;
}
.list {
	background-image: url("../../assets/img/highlight.png");
	background-size: 85%;
	background-repeat: no-repeat;
}
center > h1 {
	background-image: url("../../assets/img/highlight.png");
	background-size: 85%;
}
</style>