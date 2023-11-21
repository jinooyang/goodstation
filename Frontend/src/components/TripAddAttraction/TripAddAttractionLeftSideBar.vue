<script setup>
import {ref} from "vue";
import {useRoute, useRouter} from "vue-router";

const route = useRoute();
const router = useRouter();

const props = defineProps({stationList : Object , listitems:Object});
const emit = defineEmits(["removeLeftSideBarItem"]);
const deleteAttraction = (index) => {
  emit("removeLeftSideBarItem",index);
}
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
                v-for="station in stationList"
                :key="station.stationName"
                dot-color="red-accent-3"
                size="x-small"
                class="timelineparent"
            >
              <div class="d-flex" justify="space-between">
                <div style="flex: 1">
                  <div class="font-weight-normal">
                    <strong class="Jalnan"
                    >{{station.stationName}}</strong
                    >
                  </div>

                  <div class="d-flex">

                    <v-list>
                      <v-list-item
                          class="custom"
                          v-for="(att, index) in listitems"
                          :key="index"
                      >
                        <div class="d-flex justify-space-between" v-if="station.stationId===att.stationId">
                          <v-list-item-title  v-text="att.title"></v-list-item-title>
                          <v-btn
                              variant="plain"
                              color="red-accent-3"
                              size="auto"
                              class="rounded-circle"

                              @click="deleteAttraction(index)"
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

.custom.v-list-item--density-default {
  min-height: 0px !important;
}
.custom.v-list-item--density-default.v-list-item--one-line{
  padding-top:0 !important;
  padding-bottom: 0 !important;
}

</style>

