// https://nuxt.com/docs/api/configuration/nuxt-config
export default defineNuxtConfig({
  target: 'static',
  devtools: { enabled: true },
  colorMode: {
    preference: 'light'
  },

  app
    : {
    pageTransition
      : {
      name
        : 'page', mode
        : 'out-in'
    }
  },

  css: ['~/assets/css/main.css'],

  postcss: {
    plugins: {
      tailwindcss: {},
      autoprefixer: {},
    },
  },

  modules: ["@nuxt/ui"]
})
