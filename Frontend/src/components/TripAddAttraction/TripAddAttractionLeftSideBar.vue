<script setup>
import {ref} from "vue";
import {useRoute, useRouter} from "vue-router";

const route = useRoute();
const router = useRouter();
const handleTableRowClick = (item) => {
  alert(`Row clicked: ${item.text}`);
}

const listitems = ref([
  {text: '남한산성도립공원 [유네스코 세계문화유산]', icon: 'mdi-minus-circle-outline'},
  {text: '남한산성도립공원 [유네스코 세계문화유산]', icon: 'mdi-minus-circle-outline'},
  {text: '남한산성도립공원 [유네스코 세계문화유산]', icon: 'mdi-minus-circle-outline'},
]);
</script>
<template>
  <v-col :cols="3">
    <v-card>
      <v-toolbar>
        <v-toolbar-title class="text-h6 ">
          <p class="Jalnan ps-5">여행 장바구니</p>
        </v-toolbar-title>
      </v-toolbar>

      <!-- <v-card-text style="width: 100%"> -->
      <v-card-text>
        <v-table height="400px">
          <v-timeline density="compact" align="start">
            <v-timeline-item
                v-for="n in 5"
                :key="n"
                dot-color="red-accent-3"
                size="x-small"
                class="timelineparent"
            >
              <div class="d-flex" justify="space-between">
                <div style="flex: 1">
                  <div class="font-weight-normal">
                    <strong class="Jalnan"
                    >서울역</strong
                    >
                  </div>

                  <div class="d-flex">

                    <v-list>
                      <v-list-item
                          v-for="(item, i) in listitems"
                          :key="i"
                      >
                        <div class="d-flex">
                          <v-list-item-title v-text="item.text"></v-list-item-title>
                          <v-btn
                              variant="plain"
                              color="red-accent-3"
                              size="auto"
                              class="rounded-circle"

                              @click="handleTableRowClick(item)"
                          >

                          <v-icon>mdi-minus-circle-outline</v-icon>
                            <v-tooltip
                                activator="parent"
                                location="end"
                            >삭제</v-tooltip>
                          </v-btn>
                        </div>
                      </v-list-item>
                    </v-list>
                  </div>
                </div>

              </div>
            </v-timeline-item>
          </v-timeline>
        </v-table>
      </v-card-text>
    </v-card>
  </v-col>
</template>
<style scoped>
.red {
  background-color: #f7323f !important;
  color: white;
}
</style>
<style>

.v-toolbar__content {
  background-color: #f7323f;
  color: white;
}

</style>

