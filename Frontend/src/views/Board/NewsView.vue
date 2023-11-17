<script setup>

import {ref} from "vue";
import Pagenation from "@/components/Pagenation.vue";

const contents = ref([{
  boardId: 1,
  title: '역이어때 공지사항',
  createTime: '2023-08-10',
  memberId: 'ADMIN',

}]);


</script>

<template>
  <v-container>
    <v-row>
      <v-col :cols="4" offset="4" class="text-center">
        <div class="title mt-9 mb-9 Jalnan"><h1>공지사항</h1></div>
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

        </tr>
        </thead>
        <tbody>
        <tr v-for="content in contents" :key="content.boardId">
          <td>{{ content.boardId }}</td>
          <td>{{ content.title }}</td>
          <td>{{ content.memberId }}</td>
          <td>{{ content.createTime }}</td>
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
