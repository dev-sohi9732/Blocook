<template>
	<center class="area">
		<div class="community">
			<select v-model="selected">
				<!-- v-model="option" -->
				<!-- v-on:change="filter" -->
				<option value="title">제목</option>
				<!-- <option value="content">내용</option> -->
				<option value="writer">작성자</option>
			</select>
			<!-- <form v-if="selected=='content'" class="s-form">
				<input v-model="query" class="title-input" type="text">
				<button @click.prevent="searchcontent()"><i class="fa fa-search"></i></button>
			</form> -->
			<form v-if="selected=='writer'" class="s-form">
				<input v-model="query" class="title-input" type="text" placeholder="검색어를 입력하세요.">
				<button @click.prevent="searchwriter()" style="width:30px;cursor: pointer;">
					<i class="fa fa-search"></i>
				</button>
			</form>
			<form v-else class="s-form">
				<input v-model="query" class="title-input" type="text" placeholder="검색어를 입력하세요.">
				<button @click.prevent="searchposttitle()" style="width:30px;cursor: pointer;">
					<i class="fa fa-search"></i>
				</button>
			</form>
		</div>

		<div class="posts">
			<select v-model="sorting" class="sorting">
				<!-- v-model="option" -->
				<!-- v-on:change="filter" -->
				<!-- <option disabled value="">순서</option> -->
				<option value="new">최신순</option>
				<option value="see">조회순</option>
				<option value="like">좋아요순</option>
			</select>

			<b-dropdown id="dropdown-right" right text="내 포스트" variant="success" class="writing" no-caret v-if="this.$store.state.user.authorized">
				<b-dropdown-item @click="moveToNewPost()" style="text-align:center">새 글 쓰기</b-dropdown-item>
				<b-dropdown-item @click="moveToMyPost()" style="text-align:center">내가 쓴 글</b-dropdown-item>
			</b-dropdown>
		</div>

		<div class="posts">
			<table>
				<!-- 조회순 -->
				<tbody v-if="sorting=='see'">
					<tr v-for="(post, index) in viewPosts" :key="index + '_items'" >
						<td>
							<router-link :to="'detailpost?Id=' +post.id"><div>{{post.title}}</div>
								<div class="info">
									<span style="color:gray;">{{post.nickname}}</span>&emsp;
									<span style="color:gray;">{{post.createDate}}</span>
								</div>
							</router-link>
						</td>
						<td class="heart">
							<CommunityItem :post="post"/>
						</td>
					</tr>
				</tbody>
				<!-- 좋아요순 -->
				<tbody v-else-if="sorting=='like'">
					<tr v-for="(post, index) in likePosts" :key="index + '_items'" >
						<td>
							<router-link :to="'detailpost?Id=' +post.id"><div>{{post.title}}</div>
								<div class="info">
									<span style="color:gray;">{{post.nickname}}</span>&emsp;
									<span style="color:gray;">{{post.createDate}}</span>
								</div>
							</router-link>
						</td>
						<td class="heart">
							<CommunityItem :post="post"/>
						</td>
					</tr>
				</tbody>
				<!-- 최신순 -->
				<tbody v-else>
					<tr v-for="(post, index) in newPosts" :key="index + '_items'" >
						<td>
							<router-link :to="'detailpost?Id=' +post.id"><div>{{post.title}}</div>
								<div class="info">
									<span style="color:gray;">{{post.nickname}}</span>&emsp;
									<span style="color:gray;">{{post.createDate | formatDate}}</span>
								</div>
							</router-link>
						</td>
						<td class="heart">
							<CommunityItem :post="post"/>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		<a style="display:scroll;position:fixed;bottom:12px;right:12px;" href="#" title="맨 위로"><img style="width:45px; height:45px; opacity: 0.5;" src="@/assets/blocookImg/top.png"></a> 
	</center>
</template>

<script>
import http from "@/util/http-common.js";
import CommunityItem from "./CommunityItem.vue"
import VueLodash from 'vue-lodash'
import lodash from 'lodash'

export default {
	data() {
	  return {
		selected: 'title',
		articles: [],
		query:'',
		sorting:'new',
	  }
	},
	components: {
        CommunityItem,
    },
	methods: {
		moveToNewPost() {
			this.$router.push('/posteditor')
		},
		moveToMyPost() {
			this.$router.push('/mypost')
		},
		searchposttitle() {
			if (this.query== "") {
				http.get('/posts/search/all')
				.then(res => {
					this.articles = res.data
				})
				.catch(err => {
					console.log("error!!!")
				})
			} else {
				this.$router.push("/community?title=" + this.query ).catch(()=>{});
				
				const params = new URL(document.location).searchParams;
				http.get(`/posts/search/title/${params.get('title')}`)
					.then(response => {
					this.articles = response.data
					})
					.catch(error => {
					console.log(error)
					})
			}
		},
		searchwriter() {
			if (this.query== "") {
				http.get('/posts/search/all')
				.then(res => {
					this.articles = res.data
				})
				.catch(err => {
					console.log("error!!!")
				})
			} else {
				this.$router.push("/community?writer=" + this.query ).catch(()=>{});
				
				const params = new URL(document.location).searchParams;
				http.get(`/posts/search/nickname/${params.get('writer')}`)
					.then(response => {
					this.articles = response.data
					})
					.catch(error => {
					console.log(error)
					})
			}
		},
	},
	created() {
		http.get('/posts/search/all')
			.then(res => {
				this.articles = res.data
			})
			.catch(err => {
				console.log("error!!!")
			})
	},
	computed: {
		newPosts() {
			return _.orderBy(this.articles, 'createDate','desc')
		},
		likePosts() {
			return _.orderBy(this.articles, 'likeCnt','desc')
		},
		viewPosts() {
			return _.orderBy(this.articles, 'viewCnt','desc')
		}
	}
}
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Nanum+Gothic+Coding:wght@400;700&family=Poor+Story&display=swap');
.posts {
	max-width: 700px;
}
table {
	cursor: pointer;
  width: 100%;
}
tr:hover {
	background-color: #f5f5f5;
}
td {
  padding: 14px;
	border-top: 1px solid #ddd;
	border-bottom: 1px solid #ddd;
  text-align: left;
}
.title {
	font-weight: bold;
}
.info {
	font-size: 14px;
	margin-top: 5px;
}
.heart {
	width: 21%;
	padding-left: 15px;
}
.sorting {
	margin: 17px 8px 7px;
  float: left;
	display:inline;
	height: 43px;
	width: 100px;
	border-radius: 5px;
	border: 4px solid rgb(40, 167, 69);
	background-color:white;
	font-weight:bolder;
}
.writing {
	margin: 17px 8px 7px;
  float: right;
	background-color :rgb(40, 167, 69);
	border-radius: 5px;
	border-top:1px solid;
	border-bottom: 1px solid;
	border-left: none;
	border-right: none;
	color:white;
}
.area {
	margin-top: 10px;
}
select {
	cursor: pointer;
	display:inline;
	width: 75px;
	height: 40px;
	border-radius: 5px;
	border: 4px solid rgb(255, 204, 0);
	background-color:white;
	font-weight:bolder;
}
table {
	font-family: 'Poor Story', cursive;
}
.title-input {
	margin-top: 5px;
	width: 210px;
}
</style>