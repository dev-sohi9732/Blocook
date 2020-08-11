<template>
	<center class="area">
		<div class="community">
			<select>
				<!-- v-model="option" -->
				<!-- v-on:change="filter" -->
				<option value="title">제목</option>
				<option value="content">내용</option>
				<option value="writer">작성자</option>
			</select>
			<form class="s-form">
				<input type="text">
				<!-- v-model="search" -->
				<!-- v-on:keyup="filter" -->
				<button><i class="fa fa-search"></i></button>
			</form>
		</div>

		<div>
			<select class="sorting">
				<!-- v-model="option" -->
				<!-- v-on:change="filter" -->
				<option value="new">최신순</option>
				<option value="see">조회순</option>
				<option value="like">좋아요순</option>
			</select>

			<b-dropdown id="dropdown-right" right text="내 포스트" variant="success" class="writing" no-caret>
				<b-dropdown-item @click="moveToNewPost()" style="text-align:center">새 글 쓰기</b-dropdown-item>
				<b-dropdown-item @click="moveToMyPost()" style="text-align:center">내가 쓴 글</b-dropdown-item>
			</b-dropdown>
		</div>

		<div class="posts">
			<table>
				<tbody>
				<tr v-for="(post, index) in articles" :key="index + '_items'" >
					<td>
						<!-- :to="'detailpost?Id=' +post.id" -->
						<router-link :to="'detailpost?Id=' +post.id">{{post.title}}</router-link>
						<div class="info"><span>{{post.nickname}}</span>&emsp;<span>{{post.createDate}}</span></div>
					</td>
					<td class="heart">
						<div><i class="fa fa-eye"></i>&nbsp;{{post.view_cnt}}</div>
						<div><i class="fa fa-heart" style="color:red;"></i>&nbsp;15</div>
						<div><i class="fa fa-comment-o"></i>&nbsp;4</div>
					</td>
				</tr>
			</tbody>
			</table>
		</div>
	</center>
</template>

<script>
import http from "@/util/http-common.js";
export default {
	data() {
	  return {
		articles: []
	  }
	},
	methods: {
		moveToNewPost() {
			this.$router.push('/post')
		},
		moveToMyPost() {
			this.$router.push('/mypost')
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
	}
}
</script>

<style scoped>
.posts {
	margin-top: 10px;
}
table {
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
	height: 39px;
	width: 100px;
	border-radius: 5px;
	border: 4px solid rgb(40, 167, 69);
	background-color:white;
	font-weight:bolder;
}
.writing {
	margin: 17px 8px 7px;
  float: right;
	background-color :rgb(132, 192, 22);
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
</style>