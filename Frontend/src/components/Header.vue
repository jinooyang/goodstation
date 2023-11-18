<script setup>
import {useRoute, useRouter} from "vue-router";

const route = useRoute();
const router = useRouter();
const goToTrip = () => {
  router.push("/trip").then(()=>{
    window.scrollTo(0,0);
  });
};
const goToHome = () => {
  router.push("/").then(()=>{
    window.scrollTo(0,0);
  });
};
const goToLogin = () => {
  router.push("/login").then(()=>{
    window.scrollTo(0,0);
  });
};

const goToBoard = () => {
  router.push("/board").then(()=>{
    window.scrollTo(0,0);
  });
}

const goToNews = () => {
  router.push("/news").then(()=>{
    window.scrollTo(0,0);
  });
}
import {useMemberStore} from "@/stores/member";
import {ref} from "vue";

const memberStore = useMemberStore();
const {userLogout} = memberStore;
const logout = () => {
  userLogout();
  alert("로그아웃");
  router.push("/").then(()=>{
    window.scrollTo(0,0);
  });
}


</script>

<template>
  <v-app-bar scroll-behavior="elevate" class="cust-app-bar">
    <v-container>
      <v-row align="center">
        <v-col class="jalnan">
          <v-btn @click="goToHome"><h1>역이어때.</h1></v-btn>
        </v-col>
        <v-col class="buttons">
          <v-btn @click="goToNews">
            <svg xmlns="http://www.w3.org/2000/svg" height="1em" viewBox="0 0 448 512">
              <!--! Font Awesome Free 6.4.2 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license (Commercial License) Copyright 2023 Fonticons, Inc. -->
              <path
                  d="M224 0c-17.7 0-32 14.3-32 32V51.2C119 66 64 130.6 64 208v18.8c0 47-17.3 92.4-48.5 127.6l-7.4 8.3c-8.4
                  9.4-10.4 22.9-5.3 34.4S19.4 416 32 416H416c12.6 0 24-7.4 29.2-18.9s3.1-25-5.3-34.4l-7.4-8.3C401.3 319.2
                   384 273.9 384 226.8V208c0-77.4-55-142-128-156.8V32c0-17.7-14.3-32-32-32zm45.3 493.3c12-12 18.7-28.3
                    18.7-45.3H224 160c0 17 6.7 33.3 18.7 45.3s28.3 18.7 45.3 18.7s33.3-6.7 45.3-18.7z"/>
            </svg>
            &nbsp공지사항
          </v-btn>
          <v-btn @click="goToBoard">
            <svg xmlns="http://www.w3.org/2000/svg" height="1em" viewBox="0 0 512 512">
              <!--! Font Awesome Free 6.4.2 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license (Commercial License) Copyright 2023 Fonticons, Inc. -->
              <path d="M362.7 19.3L314.3 67.7 444.3 197.7l48.4-48.4c25-25 25-65.5 0-90.5L453.3 19.3c-25-25-65.5-25-90.5
               0zm-71 71L58.6 323.5c-10.4 10.4-18 23.3-22.2 37.4L1 481.2C-1.5 489.7 .8 498.8 7 505s15.3 8.5 23.7
               6.1l120.3-35.4c14.1-4.2 27-11.8 37.4-22.2L421.7 220.3 291.7 90.3z"/>
            </svg>
            &nbsp게시판
          </v-btn>
          <v-btn @click="goToTrip">
            <svg xmlns="http://www.w3.org/2000/svg" height="1em" viewBox="0 0 448 512">
              <!--! Font Awesome Free 6.4.2 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license (Commercial License) Copyright 2023 Fonticons, Inc. -->
              <path
                  d="M96 0C43 0 0 43 0 96V352c0 48 35.2 87.7 81.1 94.9l-46 46C28.1 499.9 33.1 512 43 512H82.7c8.5 0
                 16.6-3.4 22.6-9.4L160 448H288l54.6 54.6c6 6 14.1 9.4 22.6 9.4H405c10 0 15-12.1 7.9-19.1l-46-46c46-7.1
                  81.1-46.9 81.1-94.9V96c0-53-43-96-96-96H96zM64 128c0-17.7 14.3-32 32-32h80c17.7 0 32 14.3 32 32v96c0
                  17.7-14.3 32-32 32H96c-17.7 0-32-14.3-32-32V128zM272 96h80c17.7 0 32 14.3 32 32v96c0 17.7-14.3 32-32
                  32H272c-17.7 0-32-14.3-32-32V128c0-17.7 14.3-32 32-32zM64 352a32 32 0 1 1 64 0 32 32 0 1 1 -64 0zm288-32a32
                   32 0 1 1 0 64 32 32 0 1 1 0-64z"
              />
            </svg>
            &nbsp여행생성
          </v-btn>
          <v-btn @click="goToLogin" v-if="!memberStore.isLogin">
            <svg xmlns="http://www.w3.org/2000/svg" height="1em" viewBox="0 0 640 512">
              <!--! Font Awesome Free 6.4.2 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license (Commercial License) Copyright 2023 Fonticons, Inc. -->
              <path
                  d="M224 256A128 128 0 1 0 224 0a128 128 0 1 0 0 256zm-45.7 48C79.8 304 0 383.8 0 482.3C0 498.7 13.3
                 512 29.7 512H392.6c-5.4-9.4-8.6-20.3-8.6-32V352c0-2.1 .1-4.2 .3-6.3c-31-26-71-41.7-114.6-41.7H178.3zM528
                 240c17.7 0 32 14.3 32 32v48H496V272c0-17.7 14.3-32 32-32zm-80 32v48c-17.7 0-32 14.3-32 32V480c0 17.7 14.3
                  32 32 32H608c17.7 0 32-14.3 32-32V352c0-17.7-14.3-32-32-32V272c0-44.2-35.8-80-80-80s-80 35.8-80 80z"
              />
            </svg>
            &nbsp로그인
          </v-btn
          >
          <v-btn @click="logout" v-if="memberStore.isLogin">
            <svg xmlns="http://www.w3.org/2000/svg" height="1em" viewBox="0 0 512 512">
              <!--! Font Awesome Free 6.4.2 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license (Commercial License) Copyright 2023 Fonticons, Inc. -->
              <path d="M377.9 105.9L500.7 228.7c7.2 7.2 11.3 17.1 11.3 27.3s-4.1 20.1-11.3 27.3L377.9 406.1c-6.4 6.4-15
              9.9-24 9.9c-18.7 0-33.9-15.2-33.9-33.9l0-62.1-128 0c-17.7 0-32-14.3-32-32l0-64c0-17.7 14.3-32 32-32l128
              0 0-62.1c0-18.7 15.2-33.9 33.9-33.9c9 0 17.6 3.6 24 9.9zM160 96L96 96c-17.7 0-32 14.3-32 32l0 256c0 17.7
              14.3 32 32 32l64 0c17.7 0 32 14.3 32 32s-14.3 32-32 32l-64 0c-53 0-96-43-96-96L0 128C0 75 43 32 96 32l64
               0c17.7 0 32 14.3 32 32s-14.3 32-32 32z"/>
            </svg>
            &nbsp로그아웃
          </v-btn
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
  src: url("https://cdn.jsdelivr.net/gh/webfontworld/goodchoice/Jalnan.eot?#iefix") format("embedded-opentype"),
  url("https://cdn.jsdelivr.net/gh/webfontworld/goodchoice/Jalnan.woff2") format("woff2"),
  url("https://cdn.jsdelivr.net/gh/webfontworld/goodchoice/Jalnan.woff") format("woff"),
  url("https://cdn.jsdelivr.net/gh/webfontworld/goodchoice/Jalnan.ttf") format("truetype");
  font-display: swap;
}

.jalnan {
  font-family: "Jalnan";
}
</style>
