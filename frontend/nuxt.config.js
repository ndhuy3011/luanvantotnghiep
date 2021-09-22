import path from 'path'
import fs from 'fs'
export default {
  // Disable server-side rendering: https://go.nuxtjs.dev/ssr-mode
  ssr: false,

  router: {
    extendRoutes(routes, resolve) {
      routes.push({
        path: '*',
        component: resolve(__dirname, 'pages/404.vue'),
        name: '404',
      })
    },
  },

  server: {
    https: {
      key: fs.readFileSync(path.resolve(__dirname, 'server.key')),
      cert: fs.readFileSync(path.resolve(__dirname, 'server.crt')),
      passphrase: '3011',
    },
  },
  // Target: https://go.nuxtjs.dev/config-target
  target: 'static',

  // Global page headers: https://go.nuxtjs.dev/config-head
  head: {
    title: 'Cửa hàng 7755',
    htmlAttrs: {
      lang: 'vi',
    },
    meta: [
      { charset: 'utf-8' },
      { name: 'viewport', content: 'width=device-width, initial-scale=1' },
      { hid: 'description', name: 'description', content: '' },
    ],
    link: [{ rel: 'icon', type: 'image/x-icon', href: '/favicon.ico' }],
  },

  // Global CSS: https://go.nuxtjs.dev/config-css
  css: [],
  // Loadding : https://nuxtjs.org/docs/2.x/features/loading
  loading: {
    continuous: true,
    color: 'red',
  },

  // Plugins to run before rendering page: https://go.nuxtjs.dev/config-plugins
  plugins: [
    { src: '~/plugins/vue-fb-customer-chat.js', ssr: false },
    { src: '~/plugins/vuelidate.js', ssr: false },
    { src: '~/plugins/axios.js', ssr: false },
    { src: '~/plugins/vue2-editor.js' },
    '~/plugins/repositories.js',
    '~/plugins/local.js',
    '~/plugins/lodash.js',
    '~/plugins/vue-image-magnifier.js',
    '~/plugins/constants.js',
    '~/plugins/rule.js',
    { src: '~/plugins/mask' },
    '~/plugins/vue2-filters',
  ],

  // Auto import components: https://go.nuxtjs.dev/config-components
  components: true,

  // Modules for dev and build (recommended): https://go.nuxtjs.dev/config-modules
  buildModules: [
    // https://go.nuxtjs.dev/eslint
    '@nuxtjs/eslint-module',
    // Simple usage
    '@nuxtjs/vuetify',
    '@nuxtjs/toast',
  ],

  // Modules: https://go.nuxtjs.dev/config-modules
  modules: [
    '@nuxtjs/axios',
    '@nuxtjs/auth-next',
    '@nuxtjs/cloudinary',
    'nuxt-material-design-icons',
    '@nuxt/content',
    'bootstrap-vue/nuxt',
  ],
  // Content module configuration : https://content.nuxtjs.org/configuration
  content: {},
  bootstrapVue: {
    icons: true,
  },
  // Axios module configuration: https://go.nuxtjs.dev/config-axios
  axios: {
    baseURL: 'http://localhost:8080/api',
  },
  cloudinary: {
    cloudName: 'df9s1orh9',
    apiKey: '965953611862293',
    apiSecret: 'aL2Ez1PgQZNuGXTCHW01V1-fcbA',
  },
  // Build Configuration: https://go.nuxtjs.dev/config-build
  build: {},
  // Auth Configuration https://auth.nuxtjs.org/guide/setup
  auth: {
    plugins: ['~/plugins/auth.js'],
    common: {
      Accept: 'application/json, text/plain, */*',
    },
    strategies: {
      cookie: false,
      local: {
        token: {
          property: 'token',
          type: 'Bearer',
          name: 'Authorization',
          maxAge: 1800,
        },
        user: {
          property: false,
          autoFetch: true,
        },
        endpoints: {
          login: { url: '/login', method: 'post', propertName: 'token' },
          logout: false,
          user: { url: '/user', method: 'get' },
        },
        autoLogout: true,
      },
      local2: {
        scheme: 'local',
        token: {
          property: 'token',
          type: 'Bearer',
          name: 'Authorization',
          maxAge: 1800,
        },
        user: {
          property: false,
          autoFetch: true,
        },
        endpoints: {
          login: {
            url: '/login/facebook',
            method: 'post',
            propertName: 'token',
          },
          logout: false,
          user: { url: '/user', method: 'get' },
        },
      },
      facebook: {
        endpoints: {
          userInfo:
            'https://graph.facebook.com/v6.0/me?fields=id,name,picture{url}',
        },
        scope: ['public_profile', 'email'],
        clientId: '205092034803969',
        token: false,
        redirectUri: 'https://localhost:3000/callback',
      },
    },
  },
  toast: {
    register: [
      // Register custom toasts

      {
        name: 'error',
        message: 'Không thành công',
        options: {
          position: 'top-center',
          type: 'error',
          duration: 2000,
          theme: 'outline',
          icon: 'error',
        },
      },
      {
        name: 'loading',
        message: 'Vui lòng bạn chờ ...',

        options: {
          position: 'top-center',
          type: 'info',
          duration: 1000,
          theme: 'outline',
          icon: 'hourglass_empty',
        },
      },
      {
        name: 'success',
        message: 'Thành công',
        options: {
          position: 'top-center',
          type: 'success',
          duration: 2000,
          theme: 'outline',
          icon: 'check',
        },
      },
      {
        name: 'cart',
        message: 'Thêm giỏ hàng thành công',
        options: {
          position: 'top-center',
          type: 'success',
          duration: 1000,
          theme: 'outline',
          icon: 'check',
        },
      },
      {
        name: 'error_badrequest',
        message: 'Vui lòng bạn kiểm tra lại',
        options: {
          position: 'top-center',
          type: 'error',
          duration: 2000,
          theme: 'outline',
          icon: 'error',
        },
      },
      {
        name: 'conflitct',
        message: 'Không thể xoá vì có dự liệu liên quan',
        options: {
          position: 'top-center',
          type: 'error',
          duration: 2000,
          theme: 'outline',
          icon: 'error',
        },
      },
      {
        name: 'notfound',
        message: 'Không tìm thấy',
        options: {
          position: 'top-center',
          type: 'error',
          duration: 2000,
          theme: 'outline',
          icon: 'error',
        },
      },
      {
        name: 'login',
        message: 'Đăng nhập để thanh toán',
        options: {
          position: 'top-center',
          type: 'info',
          duration: 2500,
          theme: 'outline',
        },
      },
      {
        name: 'checkout',
        message: 'Đặt hàng thành công',
        options: {
          position: 'top-center',
          type: 'success',
          duration: 2000,
          theme: 'outline',
          icon: 'check',
        },
      },
      {
        name: 'paymet',
        message: 'Đã xác nhận thanh toán',
        options: {
          position: 'top-center',
          type: 'success',
          duration: 2000,
          theme: 'outline',
          icon: 'check',
        },
      },
      {
        name: 'checkemail',
        message: 'Đăng ký thành công. Kiểm tra email để kích hoạt tài khoản',
        options: {
          position: 'top-center',
          type: 'success',
          duration: 2000,
          theme: 'outline',
          icon: 'check',
        },
      },
      {
        name: 'errorSubmitInvoice',
        message: 'Số lượng có hạng vui lòng kiểm tra lại số lượng đặt mua',
        options: {
          position: 'top-center',
          type: 'error',
          duration: 2000,
          theme: 'outline',
          icon: 'error',
        },
      },
    ],
  },
}
