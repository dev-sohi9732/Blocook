<template>
    <div style="display:inline;">
        <div><i class="fa fa-eye"></i>&nbsp;{{post.viewCnt}}</div>
        <div><i class="fa fa-heart" style="color:red;"></i>&nbsp;{{post.likeCnt}}</div>
        <div><i class="fa fa-comment-o"> </i>&nbsp;{{comments.length}}</div>
    </div>
</template>

<script>
import http from "@/util/http-common.js";
export default {
    name: 'CommunityItem',
    props: {
        post: {
            type: Object,
        },
    },
    data() {
        return {
            comments: [],
        }
    },
    created() {
        http.get(`/comments/search/postid/${this.post.id}`)
			.then(response => {
                this.comments = response.data
            })
            .catch(error => {
			console.log(error)
			})
    }
}
</script>

<style>

</style>