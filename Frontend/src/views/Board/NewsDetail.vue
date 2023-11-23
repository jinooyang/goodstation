<script setup>
import {ref, onMounted} from "vue";
import axios from 'axios';
import {useRoute} from "vue-router";

const route = useRoute();
const newsId = ref(route.params.newsId);
const news = ref({});

const fetchNewsFromServer = async () => {
  try {
    const response = await axios.get(`http://localhost:8080/news/${newsId.value}`);
    news.value = response.data;
    const createTime = new Date(news.value.createTime);
    const formattedTime = createTime.toLocaleString("ko-KR", {
      year: "numeric",
      month: "2-digit",
      day: "2-digit",
      hour: "2-digit",
      minute: "2-digit",
      second: "2-digit"
    });
    news.value.createTime = formattedTime;
    console.log("공지사항 세부 정보를 성공적으로 불러왔습니다.");
  } catch (error) {
    console.error("공지사항 세부 정보를 불러오는데 실패했습니다.", error);
  }
};


onMounted(async () => {
  fetchNewsFromServer();
});

</script>

<template>
  <v-container>
    <v-row class="mt-5">
      <v-col :cols="8" offset="2" class="text-center">
        <div class="title"><h1>제목 : {{ news.title }}</h1></div>
      </v-col>
    </v-row>
    <v-row class="mb-5">
      <v-col :cols="12">
        <div class="title"><h2>작성자 : {{ news.memberRole }}</h2></div>
      </v-col
      >
    </v-row>
    <v-row class="mb-2">
      <v-col :cols="12" class="text-right">
        <div class="create-time"><p>작성 시간 : {{ news.createTime }}</p></div>
      </v-col>
    </v-row>
    <hr/>

    <v-row class="mt-5 mb-5">
      <v-col :cols="8" offset="1">
        <p>{{ news.content }}</p>
      </v-col>
    </v-row>
  </v-container>
</template>

<style scoped>

</style>