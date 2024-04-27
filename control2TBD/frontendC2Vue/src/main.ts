import './assets/base.css'
import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import PrimeVue from 'primevue/config';
//@ts-expect-error idk ts stuff
import Lara from './presets/lara';

const app = createApp(App)

app.use(PrimeVue, {
    unstyled: true,
    pt: Lara
});

app.use(router)
app.mount('#app')
