import { createApp } from 'vue'
import App from './App.vue'

import router from "./router";

// Vuetify
import 'vuetify/styles'
import { createVuetify } from 'vuetify'
// import { aliases, mdi } from 'vuetify/iconsets/mdi'
import '@mdi/font/css/materialdesignicons.css'
import { fa } from "vuetify/iconsets/fa";
import * as components from 'vuetify/components'
import * as directives from 'vuetify/directives'


const customTheme = {
    dark: false,
    colors: {
        background: "#ECF0F1",
        surface: "#fff",
        primary: "#096DD1",
        secondary: "#FF9623",
        error: "#ff0000",
    },
};


const vuetify = createVuetify({
    components,
    directives,
    icons: {
        defaultSet: 'mdi',
        // aliases,
        sets: {
            // mdi,
            fa
        },
    },
    theme: {
        defaultTheme: "customTheme",
        themes: {
            customTheme,
        },
    },
})

createApp(App)
    .use(vuetify)
    .use(router).mount('#app')
