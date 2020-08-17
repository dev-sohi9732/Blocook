<template>
    <div>
        <LogoArea></LogoArea>
        <MyPostArea></MyPostArea>
    </div>
</template>
<script>
import MyPostArea from "@/components/community/MyPostArea";
import LogoArea from "@/components/main/LogoArea";

export default {
  name: "mypost",
  components: {
    MyPostArea,
    LogoArea
  }
};
</script>
