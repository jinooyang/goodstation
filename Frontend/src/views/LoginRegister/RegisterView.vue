<script setup>
import {useRoute, useRouter} from "vue-router";

const route = useRoute();
const router = useRouter();
const goToLogin = () => {
  router.push("/login");
};
</script>

<template>
  <div class="red Jalnan ps-5">
    <v-container
    ><h1>회원가입</h1>역이어때에 오신 것을 환영합니다
    </v-container>
  </div>
  <v-container>
    <v-col class="Jalnan red-font text-center mb-5 mt-5">
      <h1>역이어때.</h1>
    </v-col>
    <v-col :cols="4" offset="4">
      <v-text-field
          color="red-accent-3"
          label="이름"
          variant="outlined"
          v-model="userId"
          prepend-inner-icon="mdi-account"
          hide-details
      ></v-text-field>
    </v-col>


    <v-col :cols="4" offset="4">
      <v-text-field
          color="red-accent-3"
          label="아이디"
          variant="outlined"
          v-model="userId"
          prepend-inner-icon="mdi-account-key"
          hide-details
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
          prepend-inner-icon="mdi-lock"
          hide-details
      ></v-text-field>
    </v-col>
    <v-col :cols="4" offset="4">
      <v-text-field
          color="red-accent-3"
          label="비밀번호확인"
          variant="outlined"
          v-model="userId"
          prepend-inner-icon="mdi-lock-check"
          hide-details
      ></v-text-field>
    </v-col>
    <v-col :cols="4" offset="4">
      <v-text-field
          color="red-accent-3"
          label="이메일"
          variant="outlined"
          v-model="userId"
          prepend-inner-icon="mdi-email"
          hide-details
      ></v-text-field>
    </v-col>

    <v-col :cols="4" offset="4">
      <v-text-field
          color="red-accent-3"
          label="닉네임"
          variant="outlined"
          v-model="userId"
          prepend-inner-icon="mdi-star-circle-outline"
          hide-details
      ></v-text-field>
    </v-col>
    <v-row class="mb-5 mt-5">
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
              @click="goToLogin"
          >
            회원가입
          </v-btn>
        </div>
      </v-col>
    </v-row>
    <v-row>
      <v-col :cols="4" offset="4">
        <div class="text-center Jalnan">
          이미 회원이신가요?
          <v-btn variant="outlined" @click="goToLogin">
            로그인 페이지로 이동하기
          </v-btn>
        </div>
      </v-col>
    </v-row>
    <v-row class="mb-5"/>
  </v-container>
</template>

<style scoped>
svg {
  fill: #000000
}
.v-input__details{
  display :none !important;
}
</style>
