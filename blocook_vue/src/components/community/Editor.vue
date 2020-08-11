<template>
<div style="margin-top:90px;">
    <center>
    <h2>NEW Post</h2>
    <hr>
    </center>
  <form>
    <span style="color:rgba(0,0,0,.600); margin-left:5px; font-size:1.5rem; font-weight:bold;">Title: </span>
    <input v-model="title" style="width:250px; border-radius:4px; border:3px rgba(0,0,0,.125) solid; margin-bottom:5px;" placeholder="제목을 입력해주세요.">
    <textarea v-model="content" style="" id="summernote" name="editordata"></textarea>
    <center>
        <button @click="Posting" class="successpost" style="margin-top:10px;">완료</button>
    </center>
  </form>
</div>
</template>

<script>
import http from "@/util/http-common.js";
export default {
  data() {
    return {
        title: null,
        content: null,
    };
  },
  methods: {
      Posting() {
          // var editordata = $('textarea[name="editordata"]').html($('#summernote').code());
          // this.content = editordata
          // editordata=$('.summernote').summernote('code');
          // this.content ='act=save&content='+editordata
          http.post('/posts/write', {
            "uid": this.$store.state.user.uid,
            "nickname": this.$store.state.user.nickname,
            "title" : this.title,
            "content" : this.content
          })
          // $('textarea[name="editordata"]').html($('#summernote').code());
          // .val($('#summernote').summernote('code'))
          .then(res => {
            console.log(res.data)
            alert("글이 등록 완료되었습니다.");
            this.$router.push('/community')
          })
          .catch(err => console.log('err!!'))
      }
  },
  mounted() {
    
    $('#summernote').summernote({
        placeholder: '내용을 입력해주세요.',
        tabsize: 2,
        minHeight: 250,
        lang:'ko-KR',
        toolbar: [
            ['color', ['color']],
            ['style',['style']],
            ['para', ['paragraph']],
            ['font', ['bold', 'underline', 'clear']],
            ['insert', ['picture']],
        ]
    });
  }
}
</script>

<style >
.successpost {
    background-color:#B3D662;
    color:black;
    font-weight: bold;
    width:100px;
    height: 40px;
    border:none;
    border-radius: 7px;
}
</style>