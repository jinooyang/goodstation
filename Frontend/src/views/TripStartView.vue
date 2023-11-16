<script setup>
import { useRoute, useRouter } from "vue-router";
const route = useRoute();
const router = useRouter();
const goToTripStation = () => {
  router.push("/trip/create");
};
</script>

<template>
  <v-container>
    <v-row>
      <v-col :cols="4" offset="4" class="text-center">
        <div class="title"><h1>여행 생성하기</h1></div>
      </v-col>
    </v-row>
    <v-row class="mb-5">
      <v-col :cols="12">
        <div class="title"><h2>나만의 기차여행을 생성하세요!!</h2></div></v-col
      >
    </v-row>
    <hr />
    <v-row class="mt-5 mb-5">
      <v-col :cols="3" offset="2">
        <v-img
          :width="274"
          :height="154.13"
          cover
          src="src/assets/tripstartview1.jpg"
          class="rounded-xl"
        ></v-img>
      </v-col>
      <v-col :cols="5" class="d-flex flex-column justify-center"
        ><h4 class="Jalnan">✨ 나에게 맞는 기차 운영 정보를 확인하세요! 🚂</h4>
        <p>
          여행을 계획하고 있다면, 당신이 필요로 하는 기차 운행 정보를 손쉽게 찾을 수 있습니다.
          원하는 출발지와 도착지, 특정 날짜 및 시간을 선택하면, 여러 기차 회사의 다양한 운행 정보
          중에서 가장 편리하고 시간에 맞는 옵션을 찾을 수 있습니다.
        </p></v-col
      >
    </v-row>
    <v-row class="mb-5">
      <v-col :cols="3" offset="2">
        <v-img
          :width="300"
          :height="154.13"
          cover
          src="src/assets/tripstartview2.jpg"
          class="rounded-xl"
        ></v-img>
      </v-col>
      <v-col :cols="5" class="d-flex flex-column justify-center"
        ><h4 class="Jalnan">✨ 여행의 시작, 역 주변 관광지 한눈에! 🌍</h4>
        <p>
          당신의 여행을 더욱 풍요롭게 만들기 위해, 역 주변의 다양한 관광지를 편리하게 선택할 수 있는
          기회가 여기 있습니다. 클릭 한 번으로 현지의 아름다운 명소와 문화적 경험을 탐험하고,
          당신만의 특별한 여행을 계획하세요.
        </p></v-col
      >
    </v-row>
    <v-row>
      <v-col :cols="3" offset="2">
        <v-img
          :width="300"
          :height="154.13"
          cover
          src="src/assets/tripstartview3.jpg"
          class="rounded-xl"
        ></v-img>
      </v-col>
      <v-col :cols="5" class="d-flex flex-column justify-center"
        ><h4 class="Jalnan">✨ 생성된 여행을 마이페이지에서 한눈에! 👀</h4>
        <p>
          여행을 계획하고 있다면, 당신이 필요로 하는 기차 운행 정보를 손쉽게 찾을 수 있습니다.
          원하는 출발지와 도착지, 특정 날짜 및 시간을 선택하면, 여러 기차 회사의 다양한 운행 정보
          중에서 가장 편리하고 시간에 맞는 옵션을 찾을 수 있습니다.
        </p></v-col
      >
    </v-row>
    <v-row class="mt-6 mb-6"></v-row>
    <v-row>
      <v-col :cols="4" offset="4">
        <div class="text-center Jalnan">
          <v-btn
            v-btn
            v-btn--elevated
            v-theme--light
            bg-primary
            v-btn--density-default
            v-btn--size-default
            v-btn--variant-elevated
            size="large"
            color="#f7323f"
            @click="goToTripStation"
          >
            시작하기 =>
          </v-btn>
        </div>
      </v-col>
    </v-row>
    <v-row class="mt-6 mb-6"></v-row>
  </v-container>
</template>

<style scoped>
.title {
  text-align: center;
  font-family: Jalnan;
  /*#f7323f*/
}
/* .Jalnan {
  font-family: Jalnan;
}
@font-face {
  font-family: "Jalnan";
  font-weight: normal;
  font-style: normal;
  src: url("https://cdn.jsdelivr.net/gh/webfontworld/goodchoice/Jalnan.eot");
  src: url("https://cdn.jsdelivr.net/gh/webfontworld/goodchoice/Jalnan.eot?#iefix")
      format("embedded-opentype"),
    url("https://cdn.jsdelivr.net/gh/webfontworld/goodchoice/Jalnan.woff2") format("woff2"),
    url("https://cdn.jsdelivr.net/gh/webfontworld/goodchoice/Jalnan.woff") format("woff"),
    url("https://cdn.jsdelivr.net/gh/webfontworld/goodchoice/Jalnan.ttf") format("truetype");
  font-display: swap;
} */
</style>
