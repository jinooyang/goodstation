<script setup>

</script>

<template>
  <div class="red Jalnan ps-5">
    <v-container
    ><h1>글 수정</h1>다녀온 추억을 공유해봐요
    </v-container>
  </div>
  <v-container>
    <v-text-field class="mb-8" hide-details color="red-accent-3" label="제목" variant="outlined"></v-text-field>
    <v-textarea hide-details color="red-accent-3" label="내용" variant="outlined" rows="20"></v-textarea>
    <v-col class="d-flex flex-column align-center justify-end">
      <v-btn class="summit-btn Jalnan">수정하기</v-btn>
    </v-col>
  </v-container>
</template>


<style scoped>
.summit-btn {
  background-color: rgb(247, 50, 63);
  color: rgb(255, 255, 255);
  caret-color: rgb(255, 255, 255);
}

</style>