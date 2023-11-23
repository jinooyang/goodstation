<script>
import axios from 'axios';

export default {
  props: ['totalItems'],
  data () {
    return {
      page: 1,
      itemsPerPage: 8,
    }
  },  watch: {
    page(newPage) {
      this.$emit('update:modelValue', newPage);
    }
  },
  computed: {
    totalPages() {
      return Math.ceil(this.totalItems / this.itemsPerPage);
    }
  },
}
</script>
<template>
  <v-row justify="center">
    <v-col cols="6">
      <v-container class="max-width">
        <v-pagination
            v-model="page"
            class="my-4"
            :length="totalPages"
        >{{ totalItems }}</v-pagination>
      </v-container>
    </v-col>
  </v-row>
</template>


<style scoped>

</style>