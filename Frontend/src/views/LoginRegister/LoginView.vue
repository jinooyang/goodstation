<script setup>
import { useRoute, useRouter } from "vue-router";
import {useMemberStore} from "@/stores/member";
import {ref} from "vue";
import {storeToRefs} from "pinia";
const memberStore = useMemberStore();
const { isLogin } = storeToRefs(memberStore);
const { userLogin, getUserInfo } = memberStore;

const route = useRoute();
const router = useRouter();
const loginUser = ref({
  memberId: "",
  password: "",
});
const goToRegister = () => {
  router.push("/register").then(()=>{
    window.scrollTo(0,0);
  });
};
const login = async () => {
  console.log("login loginview" + loginUser.value.memberId + loginUser.value.password);
  await userLogin(loginUser.value);
  let token = sessionStorage.getItem("accessToken");
  if (isLogin) {
    getUserInfo(token);
    // changeMenuState();
  }
  router.push("/").then(()=>{
    window.scrollTo(0,0);
  });
};



</script>

<template>  <div class="red Jalnan ps-5">
  <v-container
  ><h1>로그인</h1>다시 오신 것을 환영합니다
  </v-container>
</div>
  <v-container>
    <v-col class="Jalnan red-font text-center mt-5 mb-5" >
     <h1>역이어때.</h1>
    </v-col>

    <v-col :cols="4" offset="4">
      <v-text-field
          color="red-accent-3"
          label="아이디"
          variant="outlined"
          v-model="loginUser.memberId"
          prepend-inner-icon="mdi-account-key"
      ></v-text-field>
<!--    </v-col>-->

<!--    <v-col :cols="4" offset="4">-->
      <v-text-field
          color="red-accent-3"
          label="비밀번호"
          variant="outlined"
          v-model="loginUser.password"
          prepend-inner-icon="mdi-lock"
          type = "password"
      ></v-text-field>
    </v-col>
    <v-row class = "mb-5 ">
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
              @click="login"
          >
            로그인
          </v-btn>
        </div>
      </v-col>
    </v-row>
  <v-row class = "mb-5 mt-5">
    <v-col :cols="6" offset="3">
      <div class="text-center Jalnan">
        아직 회원이 아니신가요?
        <v-btn variant="outlined" @click="goToRegister">
          회원가입 페이지로 이동하기
        </v-btn>
      </div>
    </v-col>
  </v-row>
  </v-container>
</template>

<style scoped></style>
