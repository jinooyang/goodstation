<script setup>
import HeaderView from "./views/HeaderFooter/HeaderView.vue";
import FooterView from "./views/HeaderFooter/FooterView.vue";
</script>

<template>
  <v-app>
    <HeaderView />
    <v-main>
      <router-view></router-view>
    </v-main>
    <FooterView />
  </v-app>
</template>

<style scoped></style>
<style>
.Jalnan {
  font-family: Jalnan;
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
.red {
  background-color: #f7323f;
  color: white;
}

.red-font {
  color: #f7323f;

}
.blue-grad-bg{
  background: linear-gradient(to right, #009abd 0%,#12b1a2 100%)
}
</style>
