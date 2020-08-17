<template>
    <div>
        <NavBackgroundCommunity></NavBackgroundCommunity>
        <Editor></Editor>
    </div>
</template>
<script>
import Editor from "@/components/community/Editor";
import NavBackgroundCommunity from "@/components/main/NavBackgroundCommunity";

export default {
  name: "posteditor",
  components: {
    Editor,
    NavBackgroundCommunity
  }
};
</script>
