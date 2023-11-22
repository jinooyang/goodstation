<script>
import axios from 'axios';

export default {
  data () {
    return {
      page: 1,
      totalItems: 0,
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
  mounted() {
    axios.get('http://localhost:8080/board/count')
        .then(response => {
          console.log(response.data);
          this.totalItems = response.data;
        })
        .catch(error => {
          console.error(error);
        });
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
        ></v-pagination>
      </v-container>
    </v-col>
  </v-row>
</template>


<style scoped>

</style>