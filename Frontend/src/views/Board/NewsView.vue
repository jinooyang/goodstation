<script setup>

import {onMounted, ref} from "vue";
import axios from 'axios';
import Pagenation from "@/components/Pagenation.vue";
import {useMemberStore} from "@/stores/member";

const memberStore = useMemberStore();
const {userInfo} = memberStore;

const contents = ref([{}]);

console.log("userInfo : " + userInfo);

const fetchDataFromServer = async () => {
  try {
    const response = await axios.get("http://localhost:8080/news");
    contents.value = response.data.map((content) => {
      const formattedTime = new Date(content.createTime).toLocaleDateString("ko-KR", {
        year: "numeric",
        month: "2-digit",
        day: "2-digit"
      });
      return {
        ...content,
        createTime: formattedTime
      };
    });
    console.log("공지사항을 성공적으로 불러왔습니다.");
  } catch (error) {
    console.error("공지사항을 불러오는데 실패했습니다.", error);
  }
};


onMounted(async () => {
  fetchDataFromServer();
});
</script>

<template>
  <v-container>
    <v-row>
      <v-col :cols="4" offset="4" class="text-center">
        <div class="title mt-9 mb-9 Jalnan"><h1>공지사항</h1></div>
      </v-col>
      <v-col :cols="2" offset="2" class="d-flex align-center">
        <router-link to="/news-write">
          <v-btn v-if="userInfo!==null && userInfo.role==='ADMIN'" color="red-accent-3 Jalnan">글쓰기</v-btn>
        </router-link>
      </v-col>
    </v-row>

    <hr/>
    <div class="tableborder text-center">
      <v-table fixed-header>
        <thead>
        <tr>
          <th class="text-center">번호</th>
          <th class="text-center">제목</th>
          <th class="text-center">작성자</th>
          <th class="text-center">작성일</th>
          <th class="text-center">조회</th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="content in contents" :key="content.boardId">
          <td>{{ content.newsId }}</td>
          <td>{{ content.title }}</td>
          <td>{{ content.memberRole }}</td>
          <td>{{ content.createTime }}</td>
          <router-link :to="`/news/${content.newsId}`">
            <v-btn variant="outlined" color="red-accent-3">글보기</v-btn>
          </router-link>
        </tr>
        <tr>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
        </tr>
        </tbody>
      </v-table>

    </div>
    <Pagenation/>
  </v-container>
</template>

<style scoped>
th {
  font-family: Jalnan;
}


</style>
