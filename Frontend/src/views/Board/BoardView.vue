<script setup>
import {onMounted, ref} from "vue";
import axios from "axios";
import BoardItem from "@/components/Board/BoardItem.vue";
import {useRoute, useRouter} from "vue-router";
import Pagenation from "@/components/Pagenation.vue";

const route = useRoute();
const router = useRouter();
const goToDetail = (boardId) => {
  router.push(`/board/${boardId}`);
  console.log(boardId);
};

const goToWrite = () => {
  router.push('/board/write');
}
const items = ref([{}]);

const fetchDataFromServer = async () => {
  try {
    const response = await axios.get('http://localhost:8080/board');
    items.value = response.data;
  } catch (error) {
    console.error('데이터를 불러오는데 실패했습니다.', error);
  }
};

onMounted(() => {
  fetchDataFromServer();
});
</script>

<template>
  <div class="red Jalnan ps-5">
    <v-container>
      <h1>게시판</h1>
      다양한 방문 후기를 즐기세요~
    </v-container>
  </div>
  <v-container>


    <v-row class="mt-4">
      <v-col :cols="4" class="text-center" offset="4">
        <v-row class="align-center">
        <v-text-field color="red-accent-3" label="게시판 검색" variant="outlined" class="search-box"></v-text-field>
        <v-btn class="search-btn Jalnan">검색</v-btn>
        </v-row>
      </v-col>
    </v-row>
    <v-row class="mb-2">
      <v-col :offset="11">
        <v-btn class="write-btn Jalnan" @click="goToWrite">글쓰기</v-btn>
      </v-col>
    </v-row>
    <v-sheet
        class="d-flex align-content-start flex-wrap justify-space-evenly"
        min-height="200"
    >
      <v-sheet
          v-for="item in items"
          :key="item.id"
          class="ma-2 pa-2"
      >
        <div @click="goToDetail(item.boardId)">
          <BoardItem :title="item.title" :memberId="item.memberId" />
        </div>
      </v-sheet>
    </v-sheet>
    <Pagenation/>
  </v-container>
</template>

<style scoped>

th {
  font-family: Jalnan
}
.search-box {
  margin-top: 10px;
}
.search-btn {
  height: 56px;
  width: 60px;
  margin-top: -13px;
  background-color: rgb(247, 50, 63);
  color: rgb(255, 255, 255);
  caret-color: rgb(255, 255, 255);
}
.write-btn {
  background-color: rgb(247, 50, 63);
  color: rgb(255, 255, 255);
  caret-color: rgb(255, 255, 255);
}
</style>
