<script setup>
import {useMemberStore} from "@/stores/member";
import axios from 'axios';
import {onMounted, ref} from "vue";
import {useRoute, useRouter} from "vue-router";

const memberStore = useMemberStore();
const route = useRoute();
const router = useRouter();


const writeForm = ref({
  title: "",
  content: "",
  memberId: "",
  tripId: 2
});

const summitForm = async () => {
  try {
    const response = await axios.post('http://localhost:8080/board', {
      title: writeForm.value.title,
      content: writeForm.value.content,
      memberId: memberStore.userInfo.memberId,
      tripId: writeForm.value.tripId
    });
    console.log("글이 등록되었습니다.")
    router.push(`/board/${response.data}`);
  } catch (error) {
    console.error('글 작성에 실패했습니다.', error);
  }
};
</script>

<template>
  <div class="red Jalnan ps-5">
    <v-container
    ><h1>글쓰기</h1>다녀온 추억을 공유해봐요
    </v-container>
  </div>
  <v-container>
    <v-text-field class="mb-8" hide-details color="red-accent-3" label="제목" variant="outlined" v-model="writeForm.title"></v-text-field>
    <v-textarea hide-details color="red-accent-3" label="내용" variant="outlined" rows="20" v-model="writeForm.content"></v-textarea>
    <v-col class="d-flex flex-column align-center justify-end">
    <v-btn class="summit-btn Jalnan" @click="summitForm">글쓰기</v-btn>
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