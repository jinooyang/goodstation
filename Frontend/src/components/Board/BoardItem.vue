<script setup>
const props = defineProps(['title', 'memberId'])
</script>

<template>
  <v-card
      :loading="loading"
      class="mb-5 rounded-shaped"
      max-width="260"
  >
    <template v-slot:loader="{ isActive }">
      <v-progress-linear
          :active="isActive"
          color="deep-purple"
          height="4"
          indeterminate
      ></v-progress-linear>
    </template>

    <v-img
        cover
        height="250"
        src="src/assets/goodchoice.jpg"
    ></v-img>

    <v-card-item>
      <v-card-title>{{title}}</v-card-title>

      <v-card-subtitle>
        <span class="me-1">{{memberId}}</span>


      </v-card-subtitle>
    </v-card-item>

    <v-card-text>
      <v-row
          align="center"
          class="mx-0"
      >
        <v-rating
            :model-value="4.5"
            color="amber"
            density="compact"
            half-increments
            readonly
            size="small"
        ></v-rating>

      </v-row>

      <div class="my-4 text-subtitle-1">

      </div>

    </v-card-text>

  </v-card>
</template>

<style scoped></style>