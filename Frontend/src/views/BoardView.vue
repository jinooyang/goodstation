<script setup>
import {ref} from "vue";
import BoardItem from "@/components/BoardItem.vue";
import { useRoute, useRouter } from "vue-router";

const route = useRoute();
const router = useRouter();
const goToDetail = () => {
  router.push("/board/detail");
};
const items = ref([{

  boardId: 1,
  title: '서울여행다녀옴',
  content: '서울여행 꿀잼',
  createTime: '2023-08-10',
  memberId: 'SSAFY',
  tripId: '2'


}, {

  boardId: 1,
  title: '서울여행다녀옴',
  content: '서울여행 꿀잼',
  createTime: '2023-08-10',
  memberId: 'SSAFY',
  tripId: '2'


}, {

  boardId: 1,
  title: '서울여행다녀옴',
  content: '서울여행 꿀잼',
  createTime: '2023-08-10',
  memberId: 'SSAFY',
  tripId: '2'


}, {

  boardId: 1,
  title: '서울여행다녀옴',
  content: '서울여행 꿀잼',
  createTime: '2023-08-10',
  memberId: 'SSAFY',
  tripId: '2'


}, {

  boardId: 1,
  title: '서울여행다녀옴',
  content: '서울여행 꿀잼',
  createTime: '2023-08-10',
  memberId: 'SSAFY',
  tripId: '2'


}, {

  boardId: 1,
  title: '서울여행다녀옴',
  content: '서울여행 꿀잼',
  createTime: '2023-08-10',
  memberId: 'SSAFY',
  tripId: '2'


}]);
</script>
<script>
export default {
  data () {
    return {
      page: 1,
    }
  },
}
</script>
<template>
  <div class="red Jalnan ps-5">
    <v-container>
      <h1>게시판</h1>
      다양한 방문 후기를 즐기세요~
    </v-container>
  </div>
  <v-container>
    <v-sheet
        class="d-flex align-content-start flex-wrap justify-space-evenly"
        min-height="200"
    >
      <v-sheet
          v-for="n in 12"
          :key="n"
          class="ma-2 pa-2"
      >
        <div @click="goToDetail">
        <BoardItem/>
        </div>
      </v-sheet>
    </v-sheet>
    <v-row justify="center">
      <v-col cols="6">
        <v-container class="max-width">
          <v-pagination
              v-model="page"
              class="my-4"
              :length="15"
          ></v-pagination>
        </v-container>
      </v-col>
    </v-row>
  </v-container>

</template>

<style scoped>

th {
  font-family: Jalnan
}

</style>
