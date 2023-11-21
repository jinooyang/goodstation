<script setup>
import {useRoute, useRouter} from "vue-router";
import {ref} from "vue";
import {useMemberStore} from "@/stores/member";

const memberStore = useMemberStore();
const route = useRoute();
const router = useRouter();
const goToLogin = () => {
  router.push("/login").then(() => {
    window.scrollTo(0, 0);
  });
};

const regForm = ref({
  memberId: "",
  name: "",
  password: "",
  passwordCheck: "",
  email: "",
  nickname: ""
});
const idPos = ref(true);
const checkMemberId = () => {

  memberStore.checkId(regForm.value.memberId).then((response) => {
    if (response === 1) {
      idPos.value = true;
    } else if (response === 0) {
      idPos.value = false;
    }
  });
}

const nickPos = ref(true);
const checkMemberNN = () => {

  memberStore.checkNick(regForm.value.nickname).then((response) => {
    if (response === 1) {
      nickPos.value = true;
    } else if (response === 0) {
      nickPos.value = false;
    }
  });
}


const emailPos = ref(true);
const checkEmailDup = () => {

  memberStore.checkEmail(regForm.value.email).then((response) => {
    if (response === 1) {
      emailPos.value = true;
    } else if (response === 0) {
      emailPos.value = false;
    }
  });
}


const register = async () => {
  if (regForm.value.memberId === '' || regForm.value.name === ''
      || regForm.value.password === '' || regForm.value.passwordCheck === ''
      || regForm.value.email === '' || regForm.value.nickname === '') {
    alert("모든 항목을 입력해주세요");
    return;
  }
  if (regForm.value.password !== regForm.value.passwordCheck) {
    alert("비밀번호가 일치하지 않습니다");
    return;
  }
  if (!idPos) {
    alert("아이디 중복을 확인해주세요");
    return;
  }
  if (!nickPos) {
    alert("닉네임 중복을 확인해주세요");
    return;
  }
  if (!emailPos) {
    alert("이메일 중복을 확인해주세요");
    return;
  }
  console.log("입력 성공 회원가입 시도");
  await memberStore.registerNewMember({
    memberId: regForm.value.memberId,
    name: regForm.value.name,
    password: regForm.value.password,
    email: regForm.value.email,
    nickname: regForm.value.nickname

  }).then(() => {
    goToLogin()
  }).catch(() => {
    console.log("회원가입 에러 발생");
  });


}
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
    <v-row>
      <v-col :cols="4" offset="4">
        <v-text-field
            color="red-accent-3"
            label="이름"
            variant="outlined"
            v-model="regForm.name"
            prepend-inner-icon="mdi-account"
            hide-details
        ></v-text-field>
      </v-col>
    </v-row>
    <v-row>
      <v-col :cols="4" offset="4">
        <v-text-field
            color="red-accent-3"
            label="아이디"
            variant="outlined"
            v-model="regForm.memberId"
            prepend-inner-icon="mdi-account-key"
            hide-details
            @input="checkMemberId"
        ></v-text-field>
      </v-col>
      <v-col v-if="!idPos" class="d-flex align-center redText"><p>이미 존재하는 아이디 입니다.</p></v-col>

    </v-row>
    <!--    <v-col :cols="4" offset="4" class = "text-center">-->
    <!--      <p> 사용 불가능한 아이디 입니다.</p>-->
    <!--    </v-col>-->
    <v-row>
      <v-col :cols="4" offset="4">
        <v-text-field
            color="red-accent-3"
            label="비밀번호"
            variant="outlined"
            v-model="regForm.password"
            prepend-inner-icon="mdi-lock"
            hide-details
            type="password"
        ></v-text-field>
      </v-col>

    </v-row>
    <v-row>
      <v-col :cols="4" offset="4">
        <v-text-field
            color="red-accent-3"
            label="비밀번호확인"
            variant="outlined"
            v-model="regForm.passwordCheck"
            prepend-inner-icon="mdi-lock-check"
            hide-details
            type="password"
        ></v-text-field>
      </v-col>
      <v-col v-if="regForm.passwordCheck !== '' && regForm.password !== regForm.passwordCheck"
             class="d-flex align-center redText"><p>비밀번호가 일치하지 않습니다.</p></v-col>
    </v-row>
    <v-row>
      <v-col :cols="4" offset="4">
        <v-text-field
            color="red-accent-3"
            label="이메일"
            variant="outlined"
            v-model="regForm.email"
            prepend-inner-icon="mdi-email"
            hide-details
            @input="checkEmailDup"
        ></v-text-field>
      </v-col>
      <v-col v-if="!emailPos" class="d-flex align-center redText"><p>이미 존재하는 이메일 입니다.</p></v-col>
    </v-row>
    <v-row>
      <v-col :cols="4" offset="4">
        <v-text-field
            color="red-accent-3"
            label="닉네임"
            variant="outlined"
            v-model="regForm.nickname"
            prepend-inner-icon="mdi-star-circle-outline"
            hide-details
            @input="checkMemberNN"
        ></v-text-field>
      </v-col>
      <v-col v-if="!nickPos" class="d-flex align-center redText"><p>이미 존재하는 닉네임 입니다.</p></v-col>
    </v-row>
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
              @click="register"
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

.v-input__details {
  display: none !important;
}

.redText {
  color: red;
}
</style>
