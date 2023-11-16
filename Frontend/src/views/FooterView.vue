<script setup></script>
<script>
export default {
  data: () => ({
    icons: ["mdi-facebook", "mdi-twitter", "mdi-linkedin", "mdi-instagram"],
  }),
};
</script>
<template>
  <v-footer class="red d-flex flex-column">
    <div class = "footer-text">
      <v-btn v-for="icon in icons" :key="icon" class="mx-4" :icon="icon" variant="text"></v-btn>
    </div>

    <div class="pt-0 footer-text mb-8">
      회사소개|이용약관|개인정보처리방침|소비자 분쟁해결 기준|사업자 정보확인<br />
      여기어때 마케팅센터|액티비티 호스트센터|HOTEL 여기어때|콘텐츠산업진흥법에 의한 표시<br />
      고객행복센터 1670-6250오전 9시 - 새벽 3시 <br />(주) 여기어때컴퍼니 주소 : 서울특별시 강남구
      봉은사로 479, 479타워 11층 | 대표이사 : 정명훈 | 사업자등록번호: 742-86-00224 | 전자우편주소 :
      help@goodchoice.kr<br />
      통신판매번호 : 2017-서울강남-01779 | 관광사업자 등록번호: 제1026-24호 | 전화번호 : 1670-6250 |
      호스팅서비스제공자의 상호 표시: (주)여기어때컴퍼니<br />
      (주) 여기어때컴퍼니는 통신판매중개자로서 통신판매의 당사자가 아니며, 상품의 예약, 이용 및 환불
      등과 관련한 의무와 책임은 각 판매자에게 있습니다.<br />
      Copyright GC COMPANY Corp. All rights reserved.<br />
      <strong> 2023 SSAFY</strong>
    </div>
  </v-footer>
</template>

<style scoped>
.red {
  background-color: rgb(245, 245, 245);
}
.footer-text {
  font-size: 0.8rem;
  color: rgb(173, 172, 172);
}
</style>
