<script setup>
import {useMemberStore} from "@/stores/member";
import axios from 'axios';
import {onMounted, ref} from "vue";
import {useRoute, useRouter} from "vue-router";

const memberStore = useMemberStore();
const route = useRoute();
const router = useRouter();

const title = ref("");
const content = ref("");

const createNotion = () => {
  axios.post("http://localhost:8080/news", {
    title: title.value,
    memberRole: '관리자',
    content: content.value
  })
      .then(response => {
        console.log("공지사항이 작성되었습니다.", response.data);
        // 작성한 공지사항을 확인할 수 있는 경로로 이동하도록 설정하시면 됩니다.
        router.push(`/news/${response.data.newsId}`);
      })
      .catch(error => {
        console.error("공지사항 작성 중 오류가 발생했습니다.", error);
      });
};


</script>

<template>
  <div class="red Jalnan ps-5">
    <v-container
    ><h1>글쓰기</h1>다녀온 추억을 공유해봐요
    </v-container>
  </div>
  <v-container>
    <v-text-field v-model="title" class="mb-8" hide-details color="red-accent-3" label="제목"
                  variant="outlined"></v-text-field>
    <v-textarea v-model="content" hide-details color="red-accent-3" label="내용" variant="outlined"
                rows="20"></v-textarea>
    <v-col class="d-flex flex-column align-center justify-end">
      <v-btn @click="createNotion" class="summit-btn Jalnan">글쓰기</v-btn>
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