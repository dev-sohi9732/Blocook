<template>
	<div style="margin-top:30px;margin-bottom:30px;">
			<center>
			<h2>NEW Post</h2>
			<hr>
			</center>
		<form>
      <div style="padding-bottom:10px;">
			  <span style="color:rgba(0,0,0,.600);padding-left:15px;font-size:1.5rem; font-weight:bold;">Title: </span>
			  <input v-model="title" style="width:250px; border-radius:4px; border:3px rgba(0,0,0,.125) solid; margin-bottom:5px;" placeholder="제목을 입력해주세요.">
      </div>
      <div class="imgarea">
        <input type="file" @change="previewImage" accept="image/*">
        <p style="float:right;padding-right:5px;margin-bottom: 0px;width:100%">Uploading : {{uploadValue.toFixed() + "%"}}
          <progress :value="uploadValue" max="100"></progress>
        </p>
        <img :src="picture" style="width:100%;">
        <div style="margin-top:10px;">
          <span class="btn" @click="deleteimg" v-if="showX == true" style="background-color:rgb(138, 138, 138);float:right;margin-left:5px;">
            이미지 삭제 <i class="fa fa-close"></i>
          </span>
          <span class="btn" @click="uploadimg" style="background-color:rgb(255, 128, 128);float:right;margin-right:5px;">
            이미지 업로드 <i class="fa fa-pencil"></i>
          </span>
        </div>
      </div>
      <ckeditor id="editorD" v-model="editorData" :config="editorConfig"></ckeditor>
			<center>
					<button @click.prevent="Posting()" class="successpost" style="margin-top:10px;font-size:18px;cursor:pointer;">
						작성 완료</button>
			</center>
		</form>
	</div>
</template>

<script>
String.prototype.trim = function() { return this.replace(/^\s+|\s+$/g,""); }
import http from "@/util/http-common.js";
import firebase from 'firebase';

export default {
  data() {
    return {
        imageData: null,
        uploadValue: 0,
        picture: '',
        imgUploaded: false,
        showX: false,
        title: '',
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
            removeButtons : 'Flash,Iframe,PageBreak,Source,Save,Templates,NewPage,Print,Preview,Cut,Copy,Paste,PasteText,PasteFromWord,Undo,Redo,Find,Replace,SelectAll,Scayt,Form,HiddenField,ImageButton,Button,Select,Textarea,TextField,Checkbox,Radio,Subscript,Superscript,CopyFormatting,RemoveFormat,CreateDiv,Language,BidiRtl,BidiLtr,Link,Anchor,Unlink,Maximize,ShowBlocks,Blockquote,Outdent,Indent,About,Image'
      }
    }
  },
  methods: {
      Posting() {
        if (this.title.trim()=='')
          alert("제목을 입력해주세요.");
        else if (this.editorData=='')
          alert("내용을 입력해주세요.");
        else {
          http.post('/posts/write', {
            "uid": this.$store.state.user.uid,
            "nickname": this.$store.state.user.nickname,
            "title" : this.title,
            "content" : this.editorData,
            "img" : this.picture
          })
          .then(res => {
            alert("글이 등록되었습니다.");
            this.$router.push('/community').catch(()=>{});
          })
          .catch(err => {
            console.log('err!!')
          })
        }
      },
      previewImage(event) {
          this.uploadValue = 0;
          this.imageData = event.target.files[0];
      },
      uploadimg() {
          const storageRef = firebase.storage().ref(`${this.imageData.name}`).put(this.imageData);
          storageRef.on(`state_changed`, snapshot => {
              this.uploadValue = (snapshot.bytesTransferred/snapshot.totalBytes) * 100;
            }, error => {console.log(error.message)},
            () => {this.uploadValue = 100;
              storageRef.snapshot.ref.getDownloadURL().then((url) => {
                this.picture = url;
                this.imgUploaded = true;
                this.showX = true;
              });
            }
          );
      },
      deleteimg() {
        this.picture = '';
        this.uploadValue = 0;
        this.imageData = null;
        this.imgUploaded = false;
        this.showX = false;
      }
  },
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
#imagebtn {
  margin-left: 10px;
  margin-bottom: 10px;
}
.imgarea {
  background-color:rgb(212, 212, 212);
  margin: 0 10px 10px 10px;
  padding: 8px 0px 70px 8px;
}
</style>