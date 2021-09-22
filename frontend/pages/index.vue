<template>
  <v-container fluid>
    <v-row class="memu">
      <v-col cols="12">
        <Carousel />
      </v-col>
    </v-row>
    <v-row>
      <v-col class="text">Mua theo thể loại</v-col>
    </v-row>
    <v-container>
      <v-row>
        <v-col cols="6" md="4" sm="4">
          <router-link :to="{ name: 'men' }" style="text-decoration: none">
            <v-card id="caterogy" elevation="0"
              >Thời trang nam</v-card
            ></router-link
          >
        </v-col>
        <v-col cols="6" md="4" sm="4"
          ><router-link :to="{ name: 'women' }" style="text-decoration: none">
            <v-card id="caterogy" elevation="0"
              >Thời trang nữ</v-card
            ></router-link
          ></v-col
        >
        <v-col cols="6" md="4" sm="4"
          ><router-link :to="{ name: 'sale' }" style="text-decoration: none">
            <v-card id="caterogy" elevation="0">Sale</v-card></router-link
          ></v-col
        >
      </v-row>
    </v-container>
    <v-row>
      <v-col class="text">Sản phẩm bán chạy</v-col>
    </v-row>
    <v-row>
      <v-col v-show="!mobileView" cols="12">
        <v-sheet>
          <v-slide-group v-model="model" show-arrows>
            <v-slide-item
              v-for="(item, index) in hot"
              :key="index"
              v-slot="{ toggle }"
            >
              <v-card class="ma-6" height="550" width="300" @mouseover="toggle">
                <v-row class="fill-height" align="center" justify="center">
                  <v-scale-transition
                    ><Product :product="item"
                  /></v-scale-transition>
                </v-row>
              </v-card>
            </v-slide-item>
          </v-slide-group>
        </v-sheet>
      </v-col>
      <v-col
        v-for="(item, index) in hot"
        v-show="mobileView"
        :key="index"
        cols="6"
      >
        <v-row>
          <v-col cols="12"><Product :product="item" /> </v-col>
        </v-row>
      </v-col>
    </v-row>
    <v-row class="mt-15">
      <v-col cols="12">
        <a v-for="(item, index) in bannerSale" :key="index" href="/sale">
          <img
            class="banner"
            :src="item.file.url"
            alt="new banner"
            width="100%"
            style="cursor: pointer"
        /></a>
      </v-col>
    </v-row>
    <v-row>
      <v-col class="text">Flash Sale</v-col>
    </v-row>
    <v-row>
      <v-col v-show="!mobileView" cols="12">
        <v-sheet>
          <v-slide-group v-model="model" show-arrows>
            <v-slide-item
              v-for="(item, index) in sale.product"
              :key="index"
              v-slot="{ toggle }"
            >
              <v-card class="ma-6" height="550" width="300" @mouseover="toggle">
                <v-row class="fill-height" align="center" justify="center">
                  <v-scale-transition
                    ><Product :product="item"
                  /></v-scale-transition>
                </v-row>
              </v-card>
            </v-slide-item>
          </v-slide-group>
        </v-sheet>
      </v-col>
      <v-col
        v-for="(item, index) in sale.product"
        v-show="mobileView"
        :key="index"
        cols="6"
      >
        <v-row>
          <v-col cols="12"><Product :product="item" /> </v-col>
        </v-row>
      </v-col>
    </v-row>
    <v-row class="mt-15">
      <v-col cols="12">
        <a v-for="(item, index) in bannerFresh" :key="index" href="/shop">
          <img
            class="banner"
            :src="item.file.url"
            alt="new banner"
            width="100%"
            style="cursor: pointer"
        /></a>
      </v-col>
    </v-row>
    <v-row>
      <v-col v-show="!mobileView" cols="12">
        <v-sheet>
          <v-slide-group v-model="model" show-arrows>
            <v-slide-item
              v-for="(item, index) in fresh"
              :key="index"
              v-slot="{ toggle }"
            >
              <v-card class="ma-6" height="550" width="300" @mouseover="toggle">
                <v-row class="fill-height" align="center" justify="center">
                  <v-scale-transition
                    ><Product :product="item"
                  /></v-scale-transition>
                </v-row>
              </v-card>
            </v-slide-item>
          </v-slide-group>
        </v-sheet>
      </v-col>
      <v-col
        v-for="(item, index) in fresh"
        v-show="mobileView"
        :key="index"
        cols="6"
      >
        <v-row>
          <v-col cols="12"><Product :product="item" /> </v-col>
        </v-row>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import Product from '../components/user/Product.vue'
import Carousel from '../components/user/Carousel.vue'
export default {
  components: {
    Product,
    Carousel,
  },
  data() {
    return {
      mobileView: true,
      model: null,
    }
  },
  computed: {
    fresh() {
      return this.$store.state.user.product.fresh
    },
    sale() {
      return this.$store.state.user.categorys.sale
    },
    hot() {
      return this.$store.state.user.product.hot
    },
    listbanner() {
      return this.$store.state.admin.banner.banner
    },
    bannerSale() {
      return this.listbanner.filter((item) => {
        return item.sort === 2
      })
    },
    bannerFresh() {
      return this.listbanner.filter((item) => {
        return item.sort === 5
      })
    },
  },
  created() {
    this.handleView()
    addEventListener('resize', this.handleView)
  },
  methods: {
    handleView() {
      this.mobileView = window.innerWidth <= 700
    },
  },
}
</script>

<style lang="scss" scoped>
.menu {
  margin-top: -50px;
}
.text {
  font-weight: bold;
  text-align: center;
  font-size: 24px;
}
#caterogy {
  background-color: #ce785c;
  text-align: center;
  border-radius: 30px;
  font-size: 20px;
  padding-top: 15px;
  height: 60px;
}
#caterogy:hover {
  background-color: #ffab91;
}
.center {
  position: absolute;
  top: 35%;
  left: 50%;
  transform: translate(-50%, -50%);
  font-size: 15px;
  p {
    font-size: 40px;
    color: white;
  }
}
.banner {
  margin-top: -40px;
}
@media screen and (max-width: 990px) {
  .menu {
    margin-top: 10px;
  }
}
@media screen and (max-width: 800px) {
  .menu {
    margin-top: 0px;
  }
  .banner {
    width: 100%;
  }
  .center {
    p {
      font-size: 30px;
    }
  }
  #caterogy {
    font-size: 16px;
    padding-top: 10px;
    height: 40px;
  }
}
</style>
