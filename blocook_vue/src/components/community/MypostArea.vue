<template>
	<center>
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
						<table>
						<tbody>
							<tr v-for="(post, index) in posts" :key="index + '_items'">
								<td>
									<router-link :to="'detailpost?Id=' +post.id">{{post.title}}</router-link>
									<div class="info"><span>{{post.createDate}}</span></div>
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
						<table>
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
import MyPostCommentItem from "@/components/community/MyPostCommentItem.vue"
export default {
	data() {
		return {
			posts: [],
			comments: [],
			article: [],
		}
	},
	components: {
		MyPostCommentItem
	},
	methods: {
		moveToNewPost() {
			this.$router.push('/post')
		},
	},
	created() {
		http.get(`/posts/search/uid/${this.$store.state.user.uid}`)
			.then(res => {
				this.posts = res.data
			})
			.catch(err => {
				console.log("error!!!")
			})
		http.get(`/comments/search/uid/${this.$store.state.user.uid}`)
			.then(res => {
				this.comments = res.data
				// for (var i=0 ;i<this.comments.length; i++ ) {
				// 	http.get(`posts/${this.comments[i].postId}`)
				// 	.then(res => {
				// 		this.commentpost.push(res.data.title)
				// 	})
				// 	.catch(err => {
				// 		console.log("error!!!")
		// 	})
		// }
			})
			.catch(err => {
				console.log("error!!!")
			})
	},
}
</script>

<style scoped>
.whole {
	width: 98%;
}
table {
	width: 100%;
}
tr {
  width: 370px;
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