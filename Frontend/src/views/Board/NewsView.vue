<script setup>

import {ref} from "vue";
import Pagenation from "@/components/Pagenation.vue";
import {useMemberStore} from "@/stores/member";
const memberStore = useMemberStore();
const {userInfo} = memberStore;

const contents = ref([{
  boardId: 1,
  title: '역이어때 공지사항',
  createTime: '2023-08-10',
  memberId: 'ADMIN',

}]);

console.log("userInfo : " + userInfo);
</script>

<template>
  <v-container>
    <v-row>
      <v-col :cols="4" offset="4" class="text-center">
        <div class="title mt-9 mb-9 Jalnan"><h1>공지사항</h1></div>
      </v-col>
      <v-col :cols="2" offset="2" class = "d-flex align-center">

        <v-btn v-if="userInfo!==null && userInfo.role==='ADMIN'" color="red-accent-3 Jalnan">글쓰기</v-btn>
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
          <th class = "text-center">조회</th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="content in contents" :key="content.boardId">
          <td>{{ content.boardId }}</td>
          <td>{{ content.title }}</td>
          <td>{{ content.memberId }}</td>
          <td>{{ content.createTime }}</td>
          <td><v-btn variant="outlined" color="red-accent-3">글보기</v-btn></td>
        </tr>
        <tr >
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
