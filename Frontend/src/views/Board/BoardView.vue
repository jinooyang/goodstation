<script setup>
import { onMounted, ref, watch } from "vue";
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

const page = ref(1);

const fetchDataFromServer = async () => {
  try {
    const response = await axios.get(`http://localhost:8080/board?pageNo=${page.value}`);
    items.value = response.data;
    console.log("페이지 성공적으로 불러왔습니다.")
  } catch (error) {
    console.error('데이터를 불러오는데 실패했습니다.', error);
  }
};

watch(page, fetchDataFromServer);

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

      <v-col :cols="4" class="d-flex justify-center align-center" offset="4">
        <v-text-field hide-details color="red-accent-3" label="게시판 검색" variant="outlined" class="search-box"></v-text-field>
        <v-btn class="search-btn Jalnan">검색</v-btn>

      </v-col>

      <v-col :cols="4" class = "d-flex align-center justify-center">
        <v-btn class="write-btn Jalnan" @click="goToWrite">글쓰기</v-btn>
      </v-col>

    </v-row>
    <v-row class="mb-2">

    </v-row>
    <v-row>
      <v-col v-for="(item, index) in Array(8).fill().map((_, i) => items[i] || {})" :key="index" :cols="3">
        <div v-if="item.boardId" @click="goToDetail(item.boardId)">
          <BoardItem :title="item.title" :memberId="item.memberId" />
        </div>
      </v-col>
    </v-row>
    <Pagenation v-model="page" />
  </v-container>
</template>

<style scoped>

th {
  font-family: Jalnan
}


.search-btn {
  height: 56px;
  width: 60px;
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
