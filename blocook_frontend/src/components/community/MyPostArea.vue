<template>
	<center class="mypost">
		<div class="left">
			작성한 글 : {{posts.length}}개 / 댓글 : {{comments.length}}개
		</div>
		<div>
			<button @click="moveToNewPost()" class="writing"><i class="fa fa-pencil">&nbsp;글쓰기</i></button>
		</div>
		<div>
			<table class="whole">
			<tbody>
				<b-tabs>
					<b-tab title="작성글" active>
						<h3 style="font-family: 'Poor Story', cursive;text-align:center;margin-top:30px;" v-if="noPost">작성한 글이 없습니다.</h3>
						<table v-else>
						<tbody>
							<tr v-for="(post, index) in posts" :key="index + '_items'">
								<td>
									<router-link :to="'detailpost?Id=' +post.id">{{post.title}}
										<div class="info"><span style="color:gray;">{{post.createDate}}</span></div>
									</router-link>
								</td>
								<td class="heart">
									<div><i class="fa fa-eye"></i>&nbsp;{{post.view_cnt}}</div>
									<div><i class="fa fa-heart" style="color:red;"></i>&nbsp;15</div>
									<div><i class="fa fa-comment-o"></i>&nbsp;4</div>
								</td>
							</tr>
						</tbody>
						</table>
					</b-tab>
					<b-tab title="작성댓글">
						<h3 style="font-family: 'Poor Story', cursive;text-align:center;margin-top:30px;" v-if="noComment">작성한 댓글이 없습니다.</h3>
						<table v-else>
						<tbody>
							<tr v-for="(comment, index) in comments" :key="index + '_comments'">
								<MyPostCommentItem :comment="comment" />
							</tr>
						</tbody>
						</table>
					</b-tab>
				</b-tabs>
			</tbody>
			</table>
		</div>
	</center>
</template>

<script>
import http from "@/util/http-common.js";
import MyPostCommentItem from "./MyPostCommentItem.vue"
export default {
	data() {
		return {
			posts: [],
			comments: [],
			article: [],
			noPost: false,
			noComment: false
		}
	},
	components: {
		MyPostCommentItem
	},
	methods: {
		moveToNewPost() {
			this.$router.push('/posteditor')
		},
	},
	created() {
		http.get(`/posts/search/uid/${this.$store.state.user.uid}`)
			.then(res => {
				this.posts = res.data
				if(res.data.length == 0) this.noPost = true;
			})
			.catch(err => {
				console.log("error!!!")
			})
		http.get(`/comments/search/uid/${this.$store.state.user.uid}`)
			.then(res => {
				this.comments = res.data
				if(res.data.length == 0) this.noComment = true;
			})
			.catch(err => {
				console.log("error!!!")
			})
	},
}
</script>

<style scoped>
.mypost {
	max-width: 700px;
	margin: 15px auto;
}
.whole {
	width: 98%;
}
table {
	width: 100%;
}
tr {
	width: 370px;
	cursor: pointer;
}
tr:hover {
	background-color: #f5f5f5;
}
td {
  padding: 14px;
  text-align: left;
	border-bottom: 1px solid #ddd;
}
.title {
	font-weight: bold;
}
.info {
	font-size: 14px;
	margin-top: 5px;
}
.heart {
	width: 21%
}
.left {
	margin: 0px 15px 7px;
  float: left;
}
.writing {
	cursor: pointer;
	margin: 26px 10px 0px;
  float: right;
	background-color :rgb(132, 192, 22);
	border-radius: 5px;
	border-top:1px solid;
	border-bottom: 1px solid;
	border-left: none;
	border-right: none;
	color:white;
}
</style>