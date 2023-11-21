import { createApp } from 'vue'
import { createPinia } from 'pinia'

import App from './App.vue'
import router from './router'
import 'vuetify/styles'
import { createVuetify } from 'vuetify'
import * as components from 'vuetify/components'
import * as directives from 'vuetify/directives'
import '@mdi/font/css/materialdesignicons.css';
import piniaPluginPersistedstate from "pinia-plugin-persistedstate";

const app = createApp(App)
const vuetify = createVuetify({
    components,
    directives,
  })
const pinia = createPinia();
pinia.use(piniaPluginPersistedstate);

app.use(pinia)
app.use(router)
app.use(vuetify)
router.isReady().then(()=>{
    app.mount('#app');
});

