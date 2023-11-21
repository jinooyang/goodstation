<script setup>
import {ref, onMounted} from 'vue';
import {useRoute, useRouter} from 'vue-router';
import axios from 'axios';

const route = useRoute();
const router = useRouter();
const boardId = route.params.boardId;
const board = ref();


const fetchBoard = async () => {
  const response = await axios.get(`http://localhost:8080/board/${boardId}`);
  board.value = response.data;
  console.log(board);
};

const updateBoard = async () => {
  const requestData = {
    title: board.value.title,
    content: board.value.content,
    tripId: 2
  };

  const response = await axios.put(`http://localhost:8080/board/edit/${boardId}`, requestData);

  if(response.status === 200){
    console.log("게시물이 성공적으로 수정되었습니다.");
    router.push(`/board/${boardId}`);
  } else {
    console.log("게시물 수정에 실패하였습니다.");
  }
};


onMounted(fetchBoard);

</script>

<template>
  <div class="red Jalnan ps-5">
    <v-container
    ><h1>글 수정</h1>다녀온 추억을 공유해봐요
    </v-container>
  </div>
  <v-container>
    <v-text-field v-if="board"
                  v-model="board.title"
                  class="mb-8" hide-details color="red-accent-3" label="제목" variant="outlined"></v-text-field>
    <v-textarea v-if="board"
                v-model="board.content"
                hide-details color="red-accent-3" label="내용" variant="outlined" rows="20"></v-textarea>
    <v-col class="d-flex flex-column align-center justify-end">
      <v-btn class="summit-btn Jalnan" @click="updateBoard">수정하기</v-btn>
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