<script setup>

</script>

<template>
  <div class = "text-center mt-5 mb-5 Jalnan"><h1>소중한 추억을 만들기 위한 여행 계획을 간단하게</h1></div>
  <div class = "mb-9">

    <v-timeline direction="horizontal">
      <v-timeline-item dot-color="red-accent-3">
        <!--      <div class="text-center">hello</div>-->
        <template v-slot:opposite>📅여행 생성하기📅</template>
      </v-timeline-item>
      <v-timeline-item dot-color="yellow-accent-3">
        <div class="text-center">🚂기차역 선택🚂</div>
        <!--      <template v-slot:opposite>hello</template>-->
      </v-timeline-item>
      <v-timeline-item dot-color="green-accent-3">
        <!--      <div class="text-center">hello</div>-->
        <template v-slot:opposite>👍관광지 선택👍</template>

      </v-timeline-item>
      <v-timeline-item dot-color="light-blue-accent-3">

        <div class="text-center">🌟여행 생성 완료🌟</div>
        <!--      <template v-slot:opposite>hello</template>-->
      </v-timeline-item>
    </v-timeline>
  </div>
</template>

<style scoped>
.red {
  background-color: #f7323f;
  color: white;
}


</style>
