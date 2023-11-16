<script setup>
import { useRoute, useRouter } from "vue-router";

const route = useRoute();
const router = useRouter();
const goToTrip = () => {
  router.push("/trip");
};
const goToHome = () => {
  router.push("/");
};
const goToLogin = () => {
  router.push("/login");
};
</script>

<template>
  <v-app-bar scroll-behavior="elevate" class="cust-app-bar">
    <v-container>
      <v-row align="center">
        <v-col class="jalnan">
          <v-btn @click="goToHome"><h1>역이어때.</h1></v-btn>
        </v-col>
        <v-col class="buttons">
          <v-btn @click="goToTrip">
            <svg xmlns="http://www.w3.org/2000/svg" height="1em" viewBox="0 0 448 512">
              <!--! Font Awesome Free 6.4.2 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license (Commercial License) Copyright 2023 Fonticons, Inc. -->

              <path
                d="M96 0C43 0 0 43 0 96V352c0 48 35.2 87.7 81.1 94.9l-46 46C28.1 499.9 33.1 512 43 512H82.7c8.5 0 16.6-3.4 22.6-9.4L160 448H288l54.6 54.6c6 6 14.1 9.4 22.6 9.4H405c10 0 15-12.1 7.9-19.1l-46-46c46-7.1 81.1-46.9 81.1-94.9V96c0-53-43-96-96-96H96zM64 128c0-17.7 14.3-32 32-32h80c17.7 0 32 14.3 32 32v96c0 17.7-14.3 32-32 32H96c-17.7 0-32-14.3-32-32V128zM272 96h80c17.7 0 32 14.3 32 32v96c0 17.7-14.3 32-32 32H272c-17.7 0-32-14.3-32-32V128c0-17.7 14.3-32 32-32zM64 352a32 32 0 1 1 64 0 32 32 0 1 1 -64 0zm288-32a32 32 0 1 1 0 64 32 32 0 1 1 0-64z"
              />
            </svg>
            &nbsp여행생성
          </v-btn>
          <v-btn @click="goToLogin">
            <svg xmlns="http://www.w3.org/2000/svg" height="1em" viewBox="0 0 640 512">
              <!--! Font Awesome Free 6.4.2 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license (Commercial License) Copyright 2023 Fonticons, Inc. -->

              <path
                d="M224 256A128 128 0 1 0 224 0a128 128 0 1 0 0 256zm-45.7 48C79.8 304 0 383.8 0 482.3C0 498.7 13.3 512 29.7 512H392.6c-5.4-9.4-8.6-20.3-8.6-32V352c0-2.1 .1-4.2 .3-6.3c-31-26-71-41.7-114.6-41.7H178.3zM528 240c17.7 0 32 14.3 32 32v48H496V272c0-17.7 14.3-32 32-32zm-80 32v48c-17.7 0-32 14.3-32 32V480c0 17.7 14.3 32 32 32H608c17.7 0 32-14.3 32-32V352c0-17.7-14.3-32-32-32V272c0-44.2-35.8-80-80-80s-80 35.8-80 80z"
              />
            </svg>
            &nbsp로그인</v-btn
          >
        </v-col>
      </v-row>
    </v-container>
  </v-app-bar>
</template>

<style scoped>
svg {
  fill: #ffffff;
}
.cust-app-bar {
  background-color: #f7323f !important;
  color: white !important;
}
.buttons {
  text-align: right;
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
}
.jalnan {
  font-family: "Jalnan";
}
</style>
