<script setup>
import {useRoute, useRouter} from "vue-router";
import {onMounted, ref} from "vue";
import axios   from "axios";

const route = useRoute();
const router = useRouter();
const board = ref();

const fetchDataFromServer = async () => {
  try {
    const response = await axios.get(`http://localhost:8080/board/${route.params.boardId}`);
    board.value = response.data;
    console.log(board);
  } catch (error) {
    console.error('데이터를 불러오는데 실패했습니다.', error);
  }
};

onMounted(() => {
  fetchDataFromServer();
});
const goToTripStation = () => {
  router.push("/trip/create");
};
</script>

<template>
  <v-container v-if="board">
    <v-row class="mt-5">
      <v-col :cols="8" offset="2" class="text-center">
        <div class="title"><h1>제목 : {{ board.title }}</h1></div>
      </v-col>
    </v-row>
    <v-row class="mb-5">
      <v-col :cols="12">
        <div class="title"><h2>작성자 : {{ board.memberId }}</h2></div>
      </v-col
      >
    </v-row>
    <hr/>


    <v-row class="mt-5 mb-5">
      <v-col :cols="8" offset="2">
        <p>{{ board.content }}</p>
      </v-col>
    </v-row>
    <div class="d-flex flex-column align-center justify-center ma-9 pa-9">
      <div v-for="n in 3">
        <img src="../../assets/goodchoice.jpg" width="500"/>
        <!--        <v-img src="src/assets/goodchoice.jpg"/>-->
      </div>
    </div>
    <v-row class="mt-6 mb-6"></v-row>
  </v-container>
</template>

<style scoped>
.title {
  text-align: center;
  font-family: Jalnan;

}

</style>
