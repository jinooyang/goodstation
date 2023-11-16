<script setup>
import {useRoute, useRouter} from "vue-router";

const route = useRoute();
const router = useRouter();
const goToTripStation = () => {
  router.push("/trip/create");
};
</script>

<template>
  <v-container>
    <v-row class="mt-5">
      <v-col :cols="8" offset="2" class="text-center">
        <div class="title"><h1>제목 : 서울 여행 후기입니다.서울 여행 후기입니다.서울 여행 후기입니다.서울 여행 후기입니다.서울 여행 후기입니다.서울 여행 후기입니다.</h1></div>
      </v-col>
    </v-row>
    <v-row class="mb-5">
      <v-col :cols="12">
        <div class="title"><h2>작성자 : 김싸피</h2></div>
      </v-col
      >
    </v-row>
    <hr/>


    <v-row class="mt-5 mb-5">
      <v-col :cols="8" offset="2">
        <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore
          magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo
          consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla
          pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id
          est laborum.

          Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse potenti. Nulla facilisi. Ut ac odio sit
          amet velit vestibulum tristique. Fusce bibendum, lacus ac volutpat consequat, justo turpis tincidunt turpis,
          in commodo nulla ligula quis orci. Sed aliquam, velit id tincidunt faucibus, elit leo tristique dolor, vel
          rhoncus erat felis ut libero. Proin nec efficitur enim.

          Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Vestibulum
          aliquet erat vel efficitur scelerisque. Curabitur in leo eu urna iaculis commodo. Integer sed nunc nec leo
          vestibulum aliquet vel nec massa. Duis ac orci vitae velit vestibulum tincidunt. Fusce a felis nec sem
          fringilla lacinia. Nulla facilisi. In hac habitasse platea dictumst.</p>
      </v-col>
    </v-row>
    <div class="d-flex flex-column align-center justify-center ma-9 pa-9">
      <div v-for="n in 3">
        <img src="@/assets/goodchoice.jpg" width="500"/>
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
