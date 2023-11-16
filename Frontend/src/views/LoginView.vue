<script setup>
import { useRoute, useRouter } from "vue-router";
const route = useRoute();
const router = useRouter();
const goToRegister = () => {
  router.push("/register");
};

const login = () => {
  router.push("/");
};
</script>

<template>  <div class="red Jalnan ps-5">
  <v-container
  ><h1>로그인</h1>다시 오신 것을 환영합니다
  </v-container>
</div>
  <v-container>

    <v-col class="Jalnan red-font text-center ma-5" >
     <h1>역이어때.</h1>
    </v-col>
    <v-col :cols="4" offset="4">
      <v-text-field
          color="red-accent-3"
          label="아이디"
          variant="outlined"
          v-model="userId"
          prepend-inner-icon="mdi-account"
      ></v-text-field>
    </v-col>
    <!--    <v-col :cols="4" offset="4" class = "text-center">-->
    <!--      <p> 사용 불가능한 아이디 입니다.</p>-->
    <!--    </v-col>-->
    <v-col :cols="4" offset="4">
      <v-text-field
          color="red-accent-3"
          label="비밀번호"
          variant="outlined"
          v-model="userId"
          prepend-inner-icon="mdi-key"
      ></v-text-field>
    </v-col>
    <v-row class = "mb-5 mt-5">
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
