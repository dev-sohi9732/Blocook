<template>
<div style="margin-top:90px;">
    <center>
    <h2>Edit Post</h2>
    <hr>
    </center>
  <form>
    <span style="color:rgba(0,0,0,.600); margin-left:5px; font-size:1.5rem; font-weight:bold;">Title: </span>
    <input v-model="Epost.title"  style="width:250px; border-radius:4px; border:3px rgba(0,0,0,.125) solid; margin-bottom:5px;" placeholder="제목을 입력해주세요.">
    <ckeditor v-model="Epost.content" :config="editorConfig"></ckeditor>
    <center>
        <button @click.prevent="Editing()" class="successpost" style="margin-top:10px;">수정완료</button>
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
        Epost:[],
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
      Editing() {
          let post = {
              id : this.Epost.id,
              title : this.Epost.title,
              content : this.Epost.content 
          };
          http
            .put('/posts/update', post)
            .then(res => {
                console.log(res);
                console.log(res.data)
                alert("글이 수정 완료되었습니다.");
                this.$router.push('/community');
            })
            .catch(err => console.log('err!!'))
      }
  },
  created() {
      const params = new URL(document.location).searchParams;
      http.get(`/posts/${params.get('Id')}`)
      .then(res => {
          console.log(res.data)
          this.Epost = res.data
      })
      .catch(err => console.log('errrr'))
  },
  
  mounted() {
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