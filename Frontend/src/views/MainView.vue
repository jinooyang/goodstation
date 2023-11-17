<script setup>


import {ref} from "vue";

// const mainImg = ref([
//   {
//     src: "src/assets/goodchoice.jpg"
//   },
//   {
//     src: "src/assets/goodchoice.jpg"
//   }
// ]);

</script>

<template>
  <div class="page-container">
    <v-container>

      <div class="d-flex align-center justify-end jfont">
        <div>

            <h1 >여행 할때 역이어때?</h1>
              <h3>특별한 여행, 소중한 추억을 디자인하세요</h3>

        </div>
      </div>
    </v-container>
  </div>
</template>

<style scoped>

.page-container {
  background-image: url('/src/assets/mainbackground.jpg');
  background-size: cover;
  background-position: center;
  background-repeat: no-repeat;
  /* You can add more styles as needed */
  height: 100vh; /* Set the height to 100% of the viewport height if you want the background to cover the entire page */
}
.jfont{
  font-family: Jalnan;
  font-size: larger;
  color: white;
  margin-top: 25%;
  margin-right: 10%;
}
.h100{
  height : 100%;
}
</style>
