<template>
    <div style="margin-top:30px; margin-bottom:30px;">
        <center>
            <h2 style="font-weight:bold;font-family: 'Poor Story', cursive;color:#bd3e3e;">
                <i class="fa fa-star-o"></i>&nbsp;요리Talk! 조리Talk!&nbsp;<i class="fa fa-star-o"></i>
            </h2>
            <hr>
            <h4 class="post-title" style="font-family:'Noto Sans KR',sans-serif;">{{post.title}}</h4>
            <div>
							<p class="post-nickname" style="text-align:left;margin-left:20px;font-family:'Poor Story',cursive;margin-bottom: 0px;">
								작성자 : {{post.nickname}}
							</p>
            </div>
            <div>
							<p style="text-align:left; margin-left:20px; font-size:1rem;font-family:'Poor Story',cursive;margin-bottom: 0px;">작성일 : {{post.createDate | formatDate}}</p>
            	<p style="text-align:right; margin-right:20px; font-size:0.8rem;">
								<i class="fa fa-eye"></i>&nbsp;{{post.viewCnt}}&emsp;
								<i class="fa fa-heart" style="color:red;"></i>&nbsp;{{post.likeCnt}}&emsp;
								<i class="fa fa-comment-o"></i>&nbsp;{{comments.length}}
							</p>
						</div>
						<hr style="margin-top:25px;">
        </center>
            <div class="post-content" style="margin-left:20px; margin-right:15px; min-height:150px;">
                <span v-html="post.content"></span>
            </div>
        <center class="menu" v-if="this.$store.state.user.authorized == true">
            <button class="btn" style="color:red;font-weight:bold;" @click="addBookmark()" v-if="like == false">
                Like <i class="fa fa-heart-o"></i>
            </button>
            <button class="btn" style="color:red;font-weight:bold;" @click="rmBookmark()" v-else>
                Like <i class="fa fa-heart"></i>
            </button>
        </center>
        <center>
            <hr style="margin-bottom:0px;">
            <div>
                <button @click="MoveToList()" class="listbtn" ><p style="font-size:0.65rem; font-weight:bold;margin-bottom: 0px;color:black;">목록</p></button>
                <div v-if="post.uid==this.$store.state.user.uid" style="display:inline;">
                    <router-link :to="'editpost?Id='+post.id" style="border-top:2px solid rgb(236, 236, 236); background-color:rgb(236, 236, 236); color:black;" class="detailbtns"><span style="font-size:0.65rem; font-weight:bold;">수정</span></router-link>
                    <button @click="DeletePost(post.id)" class="detailbtns" ><p style="font-size:0.65rem; font-weight:bold;margin-bottom: 0px;color:black;">삭제</p></button>
                </div>
            </div>
            <div style="margin-top: 20px;">
            <h5 style="float:left; margin-left:20px;">Comment</h5> 
            <h6 style="float:left; margin-top:2px; margin-left:6px; font-size:0.8rem;"><i class="fa fa-comment-o"></i>&nbsp;{{comments.length}}</h6>
            </div>
            <div style="margin-right:20px; margin-left:20px; margin-top:50px; padding:5px; background-color: #f9f9f9; border: 1px solid #ececec;">
                <input v-model="content" style="border:0.3px solid gray; height:26px; width: 75%;" type="text">
                <button @click="CreateComment()" class="comment-btn" style="" :key="$route.fullPath">
                    작성
                </button>
            </div>
        </center>
        <div style="margin-top:10px; margin-right:20px; margin-left:20px;">
            <tr v-for="(comment, index) in comments" :key="index + '_items'">
                <span style="font-weight:bold;">{{comment.nickname}} :</span>
                <span> {{comment.content}}</span>
                <i v-if="comment.nickname==unickname" class="fa fa-times-circle" @click="DeleteComment(comment.id,comment.uid)" style="margin-left:10px; border:none; cursor:pointer;"></i>
            </tr>
        </div>
    </div>
</template>

<script>
import http from "@/util/http-common.js";
String.prototype.trim = function() { return this.replace(/^\s+|\s+$/g,""); }
export default {
    data() {
        return {
            post: [],
            content: '',
            comments: [],
            like: false,
            unickname:'',
            leng:0
        }
    },
    created() {
		const params = new URL(document.location).searchParams;
		http.get(`/posts/${params.get('Id')}`)
			.then(response => {
                this.post = response.data
                this.unickname = this.$store.state.user.nickname
                // 좋아요 표시했는지 여부 확인
                if(this.$store.state.user.authorized == true) {
					http.post('/posts/isbookmarked', {
						"id": this.post.id,
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
                // 조회수 +1 처리
                http.put(`/posts/addViewCount/${this.post.id}`)
                    .then(res => {})
                    .catch(err => console.log('add view_cnt error'))
            })
            .catch(error => {
			console.log(error)
            })
        http.get(`/comments/search/postid/${params.get('Id')}`)
			.then(response => {
                this.comments = response.data
                this.leng = this.comments.length
            })
            .catch(error => {
			console.log(error)
			})
    },
    methods: {
        CreateComment() {
            if (this.content.trim()=='')
                alert("댓글을 입력해주세요.");
            else
                http.post('/comments/write', {
                    "postId":this.post.id,
                    "uid": this.$store.state.user.uid,
                    "nickname": this.$store.state.user.nickname,
                    "content": this.content
                })
                .then(res => {
                    alert("댓글이 등록되었습니다.");
                    this.$router.go()
                })
                .catch(err => console.log('err!!'))
        },
        DeletePost(delId) {
            http.delete(`/posts/delete/${delId}`)
            .then(res => {
                alert("글이 삭제되었습니다.");
                this.$router.push('/community')
            })
            .catch(err => console.log('err!!'))
        },
        MoveToList() {
            this.$router.push('/community')
        },
        DeleteComment(delcommentId,commentUId) {
            if (commentUId == this.$store.state.user.uid) 
                http.delete(`/comments/delete/${delcommentId}`)
                .then(res => {
                    alert("댓글이 삭제되었습니다.");
                    this.$router.go()
                })
                .catch(err => console.log('err!!'))
            else
                alert("본인이 작성한 댓글만 삭제할 수 있습니다.")
        },
        addBookmark() { // 좋아요 누름
			http
			.post(`/posts/bookmark`, {
				"id":this.post.id,
				"uid":this.$store.state.user.uid
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
			.delete(`/posts/unbookmark/${this.$store.state.user.uid}/${this.post.id}`)
			.then(({ data }) => {
				this.like = false;
			})
			.catch((error) => {
				alert('처리 실패하였습니다.')
				console.log(error)
			})
		}
    }
}
</script>

<style>
@import url('https://fonts.googleapis.com/css2?family=Nanum+Gothic+Coding:wght@400;700&family=Poor+Story&display=swap');
@import url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@500&display=swap');
.comment-btn{
    color:white;
    text-align: center;
    font-size:0.8rem;
    border:1px solid #ff975c; 
    border-radius:2px; 
    border-top:1.5px solid #ff975c; 
    background-color:#ff975c; 
    margin-left:2px; 
    height:27px; 
    width: 55px;
    font-weight: bold;
}
.detailbtns {
    margin-top:10px;
    margin-right:2px;
    border:none;
    height:28px; 
    width:40px;
    float:right;
}
.listbtn {
    margin-top:10px;
    margin-right:20px;
    border:none;
    height:28px; 
    width:40px;
    float:right;
}
button {
	cursor: pointer;
}
</style>