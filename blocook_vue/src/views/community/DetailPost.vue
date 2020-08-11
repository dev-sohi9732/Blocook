<template>
    <div style="margin-top:85px;">
        <center>
            <h2 style="font-weight:bold;">요리톡 조리톡</h2>
            <hr>
            <h4 class="post-title">{{post.title}}</h4>
            <p class="post-nickname" style="float:right; margin-right:20px;">{{post.nickname}}</p>
            <br>
            <p style="float:right; margin-right:20px; font-size:0.8rem;">{{post.createDate}}</p>
            <p style="float:left; margin-left:20px; font-size:0.8rem;"><i class="fa fa-eye"></i>&nbsp;{{post.view_cnt}}</p>
            <p style="float:left; margin-left:20px; font-size:0.8rem;"><i class="fa fa-heart" style="color:red;"></i>&nbsp;15</p>
            <p style="float:left; margin-left:20px; font-size:0.8rem;"><i class="fa fa-comment-o"></i>&nbsp;4</p>
            <hr style="margin-top:25px;">
            <div class="post-content" style="min-height:150px;">
                <p> {{post.content }}</p>
            </div>
            <hr style="margin-bottom:5px;">
            <h5 style="float:left; margin-left:20px;">Comment</h5> 
            <h6 style="float:left; margin-top:2px; margin-left:6px; font-size:0.8rem;"><i class="fa fa-comment-o"></i>&nbsp;4</h6>
            <div style="margin-right:20px; margin-left:20px; margin-top:30px; padding:5px; background-color: #f9f9f9; border: 1px solid #ececec;">
                <input v-model="content" style="border:0.3px solid gray; height:26px;" type="text">
                <button @click="CreateComment()" class="comment-btn" style="" :key="$route.fullPath">
                    작성
                </button>
            </div>
        </center>
        <div style="margin-top:10px; margin-right:20px; margin-left:20px;">
            <tr v-for="(comment, index) in comments" :key="index + '_items'">
                <span style="font-weight:bold;">{{comment.nickname}} :</span>
                <span> {{comment.content}}</span>
            </tr>
        </div>
    </div>
</template>

<script>
import http from "@/util/http-common.js";
export default {
    data() {
        return {
            post: [],
            content: null,
            comments: [],
        }
    },
    created() {
		const params = new URL(document.location).searchParams;
		http.get(`/posts/${params.get('Id')}`)
			.then(response => {
                this.post = response.data
            })
            .catch(error => {
			console.log(error)
            })
        http.get(`/comments/search/postid/${params.get('Id')}`)
			.then(response => {
                this.comments = response.data
            })
            .catch(error => {
			console.log(error)
			})
    },
    methods: {
        CreateComment() {
            http.post('/comments/write', {
                "postId":this.post.id,
                "uid": this.$store.state.user.uid,
                "nickname": this.$store.state.user.nickname,
                "content": this.content
            })
            .then(res => {
                // alert("댓글이 등록 완료되었습니다.");
                this.$router.go()
            })
            .catch(err => console.log('err!!'))
        }
    }
}
</script>

<style>
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
</style>