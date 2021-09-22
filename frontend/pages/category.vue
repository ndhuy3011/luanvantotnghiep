<template>
  <v-container fluid>
    <v-row>
      <v-col v-show="!mobileView" cols="12" sm="4" md="3">
        <FilterLeft />
      </v-col>
      <v-col v-show="!mobileView" cols="12" sm="8" md="9" class="list-product">
        <v-row>
          <v-col v-show="category === 'Nữ'"
            ><router-link :to="{ name: 'women' }" style="text-decoration: none">
              <v-card id="caterogy" elevation="0"
                ><h3>Thời trang Nữ</h3></v-card
              ></router-link
            ></v-col
          >
          <v-col v-show="category === 'Nam'"
            ><router-link :to="{ name: 'men' }" style="text-decoration: none">
              <v-card id="caterogy" elevation="0"
                ><h3>Thời trang Nam</h3></v-card
              ></router-link
            ></v-col
          >
        </v-row>
        <p v-show="search != ''" style="font-size: 18px; color: red">
          Kết quả tìm kiếm: <strong>{{ resultsSearch }}</strong>
        </p>
        <v-row>
          <v-col
            v-for="item in filteredList"
            :key="item.id"
            cols="6"
            md="4"
            lg="3"
            sm="6"
            xs="6"
          >
            <Product :product="item" />
          </v-col>
        </v-row>
      </v-col>

      <v-col v-show="mobileView" cols="12">
        <FiterMobile />
      </v-col>

      <v-col v-show="mobileView" cols="12" class="list-product">
        <p>Tất cả sản phẩm</p>
        <v-row>
          <v-col
            v-for="item in filteredList"
            :key="item.id"
            cols="6"
            md="4"
            lg="3"
            sm="6"
            xs="6"
          >
            <Product :product="item" />
          </v-col>
        </v-row>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import FilterLeft from '~/components/user/filter/Filter.vue'
import FiterMobile from '~/components/user/filter/FiterMobile.vue'
import Product from '~/components/user/Product.vue'
export default {
  name: 'Shop',
  components: { FilterLeft, Product, FiterMobile },
  data: () => {
    return {
      mobileView: true,
      resultsSearch: '',
    }
  },
  computed: {
    listproduct() {
      return this.$store.state.user.product.select
    },
    category() {
      return this.$store.state.user.product.category
    },
    search() {
      return this.$store.state.user.search.search
    },
    filteredList() {
      return this.listproduct.filter((item) => {
        return item.name.toLowerCase().includes(this.search.toLowerCase())
      })
    },
  },
  created() {
    this.handleView()
    addEventListener('resize', this.handleView)
  },
  updated() {
    this.resultsSearch = this.filteredList.length
  },
  methods: {
    handleView() {
      this.mobileView = window.innerWidth <= 700
    },
    nextpage(data) {
      this.$store.dispatch(this.$constant.user.ACTIONS_PC_GET, data)
    },
  },
}
</script>

<style lang="scss" scoped>
.menu {
  margin: 0px -47px;
}
.text {
  text-align: center;
  font-size: 20px;
}
#caterogy {
  text-align: center;
  border-radius: 30px;
  font-size: 20px;
  padding-top: 15px;
  height: 60px;
}
#caterogy h3:hover {
  color: #ce785c;
}
.collection {
  margin-top: -40px;
}
.list-product {
  p {
    font-size: 25px;
  }
}
@media screen and (max-width: 600px) {
  .list-product {
    p {
      font-size: 20px;
    }
  }
}
</style>
