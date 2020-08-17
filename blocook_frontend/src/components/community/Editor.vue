<template>
	<div style="margin-top:30px;">
			<center>
			<h2>NEW Post</h2>
			<hr>
			</center>
		<form>
			<span style="color:rgba(0,0,0,.600); margin-left:5px; font-size:1.5rem; font-weight:bold;">Title: </span>
			<input v-model="title" style="width:250px; border-radius:4px; border:3px rgba(0,0,0,.125) solid; margin-bottom:5px;" placeholder="제목을 입력해주세요.">
			<ckeditor v-model="editorData" :config="editorConfig"></ckeditor>
			<center>
					<button @click.prevent="Posting()" class="successpost" style="margin-top:10px;font-size:18px;cursor:pointer;">
						작성 완료</button>
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
        editorData: '',
        editorConfig: {
            toolbarGroups: [
              { name: 'document', groups: [ 'mode', 'document', 'doctools' ] },
              { name: 'clipboard', groups: [ 'clipboard', 'undo' ] },
              { name: 'editing', groups: [ 'find', 'selection', 'spellchecker', 'editing' ] },
              { name: 'forms', groups: [ 'forms' ] },
              { name: 'basicstyles', groups: [ 'basicstyles', 'cleanup' ] },
              { name: 'paragraph', groups: [ 'list', 'indent', 'blocks', 'align', 'bidi', 'paragraph' ] },
              { name: 'links', groups: [ 'links' ] },
              { name: 'insert', groups: [ 'insert' ] },
              '/',
              { name: 'styles', groups: [ 'styles' ] },
              { name: 'colors', groups: [ 'colors' ] },
              { name: 'tools', groups: [ 'tools' ] },
              { name: 'others', groups: [ 'others' ] },
              { name: 'about', groups: [ 'about' ] }
            ],
            removeButtons : 'Flash,Iframe,PageBreak,Source,Save,Templates,NewPage,Print,Preview,Cut,Copy,Paste,PasteText,PasteFromWord,Undo,Redo,Find,Replace,SelectAll,Scayt,Form,HiddenField,ImageButton,Button,Select,Textarea,TextField,Checkbox,Radio,Subscript,Superscript,CopyFormatting,RemoveFormat,CreateDiv,Language,BidiRtl,BidiLtr,Link,Anchor,Unlink,Maximize,ShowBlocks,Blockquote,Outdent,Indent,About'

      }
    }
  },
  methods: {
      Posting() {
          http.post('/posts/write', {
            "uid": this.$store.state.user.uid,
            "nickname": this.$store.state.user.nickname,
            "title" : this.title,
            "content" : this.editorData
          })
          .then(res => {
            console.log(res.data)
            alert("글이 등록 완료되었습니다.");
            this.$router.push('/community').catch(()=>{});
          })
          .catch(err => console.log('err!!'))
      }
  },
  mounted() {
  }
}
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Nanum+Gothic+Coding:wght@400;700&family=Poor+Story&display=swap');
.successpost {
    background-color:#B3D662;
    color:black;
    font-weight: bold;
    width:100px;
    height: 40px;
    border:none;
    border-radius: 7px;
}
h2 {
	font-family: 'Poor Story', cursive;
	font-weight: bold;
}
</style>