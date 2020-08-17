<template>
  <td>
    <router-link :to="'detailpost?Id=' +comment.postId" class="title">{{ comment.content}}
        <p style="font-weight:bold;font-size:0.8rem;color:gray;">{{post.title}}</p>
        <div class="info"><span style="color:gray;">{{comment.createDate}}</span></div>
    </router-link>
  </td>
</template>

<script>
import http from "@/util/http-common.js";
export default {
    data() {
        return {
            post:[]
        }
    },
    props: {
        comment: {
            type: Object,
        },
    },
    created() {
        http.get(`/posts/${this.comment.postId}`)
        .then((res) => {
            this.post = res.data
        })
        .catch((err) =>{
            console.log('err!!')
        })
    }   
}
</script>

<style>

</style>