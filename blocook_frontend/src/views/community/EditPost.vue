<template>
    <div>
        <NavBackgroundCommunity></NavBackgroundCommunity>
        <EditPostArea></EditPostArea>
    </div>
</template>
<script>
import EditPostArea from "@/components/community/EditPostArea";
import NavBackgroundCommunity from "@/components/main/NavBackgroundCommunity";

export default {
  name: "editpost",
  components: {
    EditPostArea,
    NavBackgroundCommunity
  }
};
</script>
