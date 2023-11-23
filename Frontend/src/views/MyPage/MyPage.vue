<script setup>

import {onMounted, ref} from "vue";
import axios from "axios";
import {useTripStore} from "@/stores/trip";
import {useRoute, useRouter} from "vue-router";
const tripStore = useTripStore();
const route = useRoute();
const router = useRouter();

const {VITE_VUE_API_URL} = import.meta.env;
const axiosInstance = axios.create({
  baseURL: VITE_VUE_API_URL,
});
const goToResult = (index) => {
  tripStore.newTripId = index;
  console.log("마이페이지에서 변경된 newTripId : " , tripStore.newTripId.value);
  router.push("/result").then(()=>{
    window.scrollTo(0,0);
  });
};

//비밀번호 관련!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
const passwordDialog = ref(false);
const editUserPassword = ref({});
const editUserPasswordCheck = ref({});
const openPasswordDialog = () => {
  editUserPassword.value = "";
  editUserPasswordCheck.value = "";

  //changePassword()
}
const updateUserPassword = () => {
  if (editUserPassword.value !== editUserPasswordCheck.value) {
    alert("비밀번호가 일치하지 않습니다.");
    return;
  }
  if (editUserPassword.value.length === 0) {
    alert("새로운 비밀번호를 입력해주세요");
    return;
  }

  axiosInstance.post('/member/updatepassword',
      {
        password: editUserPassword.value,
        memberId: userInformation.value.memberId,
      },

      {
        headers: {
          "X-AUTH-TOKEN": sessionStorage.getItem("accessToken")
        }
      }
  ).then((response) => {
    passwordDialog.value = false;
    alert("비밀번호 변경 성공! ");
  }).catch((error) => {
    passwordDialog.value = false;
    alert("비밀번호 변경 실패!");
  });

}


//회원 수정 관련!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
const dialog = ref(false);
const userInformation = ref({
  name: "",
  memberId: "",
  nickname: "",
  email: "",
  role: ""
});
const editUserInformation = ref({});
const opendialog = () => {
  // dialog.value = true;
  editUserInformation.value = {...userInformation.value}
}
const updateUserInformation = () => {
  dialog.value = false;
  axiosInstance.post('/member/updateInfo',

      editUserInformation.value
      ,
      {
        headers: {
          "X-AUTH-TOKEN": sessionStorage.getItem("accessToken")
        }
      })
      .then((response) => {
        console.log("회원 업데이트 성공!", response.data.data.memberInfo.name);

        userInformation.value.name = response.data.data.memberInfo.name;
        userInformation.value.memberId = response.data.data.memberInfo.memberId;
        userInformation.value.nickname = response.data.data.memberInfo.nickname;
        userInformation.value.email = response.data.data.memberInfo.email;
        userInformation.value.role = response.data.data.memberInfo.role;
        // console.log("변경된 유저 정보 : " + userInformation.value.memberInfo.name);
      })
      .catch((error) => {
            console.log(error);
            console.log("회원정보 업데이트 실패");
          }
      );
}


//회원 여행 관련!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
const tripList = ref([]);


//onMounted!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
const getUserInformation = () => {
  axiosInstance.get('/member/info',
      {
        headers: {
          "X-AUTH-TOKEN": sessionStorage.getItem("accessToken")
        }
      })
      .then((response) => {
        console.log("axios요청 성공!", response.data.data.memberInfo.name);

        userInformation.value.name = response.data.data.memberInfo.name;
        userInformation.value.memberId = response.data.data.memberInfo.memberId;
        userInformation.value.nickname = response.data.data.memberInfo.nickname;
        userInformation.value.email = response.data.data.memberInfo.email;
        userInformation.value.role = response.data.data.memberInfo.role;
        console.log("변경된 유저 정보 : " + userInformation.value.name);
        getUserTrip();
      })
      .catch((error) => {
            console.log(error);
          }
      );
}

const getUserTrip = () => {
  axiosInstance.get('/member/triplist/' + userInformation.value.memberId)
      .then((response) => {
        console.log("여행 목록 가져오기 성공!!");
        console.log(response.data.data.list);
        tripList.value = response.data.data.list;
  })
      .catch((error) => {
        console.log("여행 목록 가져오기 실패ㅠㅠ");
        console.log(error);
  });
}


onMounted(() => {
  console.log("mounted");
  getUserInformation();
});
</script>
<template>

  <div class="red Jalnan ps-5">
    <v-container
    ><h1>마이페이지</h1>{{ userInformation.name }}님 환영합니다
    </v-container>
  </div>
  <v-container>
    <!--회원 정보 !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!-->
    <v-col :cols="4" class="text-left">
      <div class="title mt-9 Jalnan"><h1>회원정보</h1></div>
    </v-col>
    <hr/>
    <div class="tableborder text-center">
      <v-table fixed-header>
        <thead>
        <tr>
          <th class="text-center Jalnan">이름</th>
          <th class="text-center Jalnan">아이디</th>
          <th class="text-center Jalnan">이메일</th>
          <th class="text-center Jalnan">닉네임</th>
          <th class="text-center Jalnan">등급</th>
          <th class="text-center Jalnan">수정</th>
        </tr>
        </thead>
        <tbody>

        <tr>
          <td>{{ userInformation.name }}</td>
          <td>{{ userInformation.memberId }}</td>
          <td>{{ userInformation.email }}</td>
          <td>{{ userInformation.nickname }}</td>
          <td>{{ userInformation.role }}</td>
          <td>
            <v-row justify="center">
              <v-dialog
                  v-model="dialog"
                  persistent
                  width="1024"
              >
                <template v-slot:activator="{ props }">
                  <v-btn
                      variant="plain"
                      v-bind="props"
                      @click="opendialog"
                  >
                    <v-icon>mdi-pencil</v-icon>
                  </v-btn>
                </template>
                <v-card>
                  <v-card-title>
                    <span class="Jalnan">
                      <h1 class="mt-9 ms-7">회원정보 수정</h1>
                      <p class="ms-7 mt-3">회원 정보를 수정하세요</p>
                    </span>
                  </v-card-title>
                  <v-card-text>
                    <v-container>
                      <v-row>

                        <v-col cols="12">
                          <v-text-field
                              label="이름"
                              v-model="editUserInformation.name"
                              variant="outlined"
                              color="red-accent-3"
                          ></v-text-field>

                        </v-col>
                        <v-col cols="12">
                          <v-text-field
                              label="닉네임"
                              v-model="editUserInformation.nickname"
                              variant="outlined"
                              color="red-accent-3"
                          ></v-text-field>

                        </v-col>
                        <v-col cols="12">
                          <v-text-field
                              label="이메일"
                              v-model="editUserInformation.email"
                              variant="outlined"
                              color="red-accent-3"
                          ></v-text-field>

                        </v-col>
                      </v-row>
                    </v-container>

                  </v-card-text>
                  <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn
                        color="red-accent-3"
                        variant="text"
                        @click="dialog = false"
                    >
                      Close
                    </v-btn>
                    <v-btn
                        color="red-accent-3"
                        variant="text"
                        @click="updateUserInformation"
                    >
                      Save
                    </v-btn>
                  </v-card-actions>
                </v-card>
              </v-dialog>
            </v-row>
          </td>
        </tr>

        </tbody>
      </v-table>
<!--!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! -->
      <v-col :cols="4" class="text-left">
        <div class="title mt-9 Jalnan"><h1>여행 내역</h1></div>
      </v-col>
      <hr/>
      <v-table fixed-header>
        <thead>
        <tr>
          <th class="text-center Jalnan">여행아이디</th>
          <th class="text-center Jalnan">여행이름</th>
          <th class="text-center Jalnan">보기</th>
        </tr>
        </thead>
        <tbody>

        <tr v-for="(trip,index) in tripList" :key="index">
          <td>{{ trip.tripId }}</td>
          <td>{{ trip.tripName }}</td>
          <td>
            <v-btn variant="plain" @click="goToResult(trip.tripId)">
              <v-icon>mdi-magnify</v-icon>
            </v-btn>
          </td>
        </tr>

        </tbody>
      </v-table>


      <!--비밀번호 수정하기!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!-->
      <v-col :cols="4" class="text-left">
        <div class="title mt-9  Jalnan"><h1>비밀번호 변경</h1></div>
      </v-col>
      <hr/>
      <v-table fixed-header>
        <thead>
        <tr>
          <th class="text-left Jalnan ps-9">비밀번호 변경하기

            <v-dialog
                v-model="passwordDialog"
                persistent
                width="1024"
            >
              <template v-slot:activator="{ props }">
                <v-btn
                    variant="plain"
                    v-bind="props"
                    @click="openPasswordDialog"
                >
                  <v-icon>mdi-pencil</v-icon>
                </v-btn>
              </template>
              <v-card>
                <v-card-title>
                  <span class="Jalnan"><h1 class="mt-9 ms-7">비밀번호 수정</h1><p class="ms-7 mt-3">비밀번호를 수정하세요</p></span>
                </v-card-title>
                <v-card-text>
                  <v-container>
                    <v-row>

                      <v-col cols="12">
                        <v-text-field
                            label="새로운 비밀번호"
                            v-model="editUserPassword"
                            variant="outlined"
                            color="red-accent-3"
                            type="password"
                        ></v-text-field>
                      </v-col>

                      <v-col cols="12">
                        <v-text-field
                            label="새로운 비밀번호 확인"
                            v-model="editUserPasswordCheck"
                            variant="outlined"
                            color="red-accent-3"
                            type="password"
                        ></v-text-field>
                      </v-col>

                    </v-row>
                  </v-container>

                </v-card-text>
                <v-card-actions>
                  <v-spacer></v-spacer>
                  <v-btn
                      color="red-accent-3"
                      variant="text"
                      @click="passwordDialog = false"
                  >
                    Close
                  </v-btn>
                  <v-btn
                      color="red-accent-3"
                      variant="text"
                      @click="updateUserPassword"
                  >
                    Save
                  </v-btn>
                </v-card-actions>
              </v-card>
            </v-dialog>


          </th>

        </tr>
        </thead>
        <tbody>


        </tbody>
      </v-table>

      <!--&lt;!&ndash; 회원BOARD관련!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! &ndash;&gt;-->


      <v-row class="pt-9 pb-9 mt-5"></v-row>
    </div>
  </v-container>
</template>
<style scoped></style>