<script setup>


import {ref} from "vue";

const desserts = ref([
  {
    name: 'Frozen Yogurt',

  },
  {
    name: 'Ice cream sandwich',

  },
  {
    name: 'Eclair',

  },
  {
    name: 'Cupcake',

  },
  {
    name: 'Gingerbread',

  },
  {
    name: 'Jelly bean',

  },
  {
    name: 'Lollipop',

  },
  {
    name: 'Honeycomb',

  },
  {
    name: 'Donut',

  },
  {
    name: 'KitKat',

  },

]);

const handleTableRowClick = (item) => {
  alert(`Row clicked: ${item.name}`);
}

</script>
<template>

  <v-col :cols="3">
    <v-table height="500">
      <thead>
      <tr>
        <th class="text-left">
          관광지
        </th>
        <th>추가</th>
      </tr>
      </thead>
      <tbody>
      <tr
          v-for="item in desserts"
          :key="item.name"

      >

        <td>{{ item.name }}</td>

        <td>
          <v-btn
              variant="plain"
              color="red-accent-3"
              size="auto"
              class="rounded-circle"

              @click="handleTableRowClick(item)">
            <v-icon>mdi-plus-circle-outline</v-icon>
            <v-tooltip
                activator="parent"
                location="end"
         
            >추가
            </v-tooltip>
          </v-btn>
        </td>
      </tr>
      </tbody>
    </v-table>
  </v-col>

</template>
<style scoped></style>