<template>
  <v-app id="inspire">
    <HeaderMobile />
    <div class="content_default" :class="{ open: showNav }">
      <div class="top-bar container">
        <div v-if="mobileView" class="w-100 row">
          <div id="navigation-icon" class="col" @click="showNav = !showNav">
            <v-app-bar-nav-icon></v-app-bar-nav-icon>
          </div>
          <div class="col d-flex justify-content-end">
            <BtnCart />
            <MenuAcc />
          </div>
        </div>
        <Header v-if="!mobileView" />
      </div>
      <v-main in>
        <Nuxt />
      </v-main>
      <Footer />
    </div>
  </v-app>
</template>
<script>
import Header from '~/components/user/header/Header.vue'
import Footer from '~/components/user/Footer.vue'
import HeaderMobile from '~/components/user/header/HeaderMobile.vue'
import MenuAcc from '~/components/user/MenuAccount.vue'
import BtnCart from '~/components/BtnCart'
export default {
  components: { Header, Footer, HeaderMobile, MenuAcc, BtnCart },
  build: {
    splitChunks: {
      layouts: true,
    },
  },
  data: () => {
    return {
      mobileView: true,
      showNav: false,
    }
  },
  mounted() {
    this.$nextTick(() => {
      this.$nuxt.$loading.start()
      setTimeout(() => this.$nuxt.$loading.finish(), 500)
    })
  },
  created() {
    this.handleView()
    addEventListener('resize', this.handleView)
    this.$store.dispatch(this.$constant.user.ACTIONS_CATEGORYS_GETALL)
    this.$store.dispatch(this.$constant.user.ACTIONS_PRODUCT_GETALL)
    this.$store.dispatch(this.$constant.admin.ACTION_ADMIN_INFOWEB_INIT)
    this.$store.dispatch(this.$constant.admin.ACTION_ADMIN_BANNER_INIT)
    if (this.$auth.user) {
      this.$store.dispatch(this.$constant.user.ACTION_SHOW_CART_USER)
    } else {
      this.$store.dispatch(this.$constant.user.ACTION_SHOW_CART)
    }
  },

  methods: {
    handleView() {
      this.mobileView = window.innerWidth <= 990
    },
  },
}
</script>
<style lang="scss" scoped>
* {
  font-family: Arial, Helvetica, sans-serif !important;
}
.top-bar {
  display: flex;
  width: 100%;
}
#navigation-icon {
  padding: 10px 10px 20px;
  margin-right: 10px;
  cursor: pointer;
  i {
    font-size: 2rem;
  }
}
.content_default {
  position: absolute;
  width: calc(100%);
  height: calc(80vh);
  padding: 20px;
  background-color: #fff;
  transition: 0.5s transform cubic-bezier(0, 0.12, 0.14, 1);
}
.open {
  transform: translateX(255px);
}
</style>
