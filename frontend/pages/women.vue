<template>
  <v-container fluid>
    <v-row>
      <v-col v-show="!mobileView" cols="12" sm="4" md="3">
        <FilterLeft />
      </v-col>
      <v-col v-show="!mobileView" cols="12" sm="8" md="9" class="list-product">
        <v-row>
          <v-col class="text">Thời trang Nữ</v-col>
        </v-row>
        <v-row>
          <v-col
            v-for="(value, indexcate) in category_women.children"
            :key="indexcate"
            cols="6"
            md="4"
            sm="4"
          >
            <v-card id="caterogy" elevation="0" @click="nextpage(value)">{{
              value.name
            }}</v-card>
          </v-col>
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
        <v-row>
          <v-col cols="12" class="text">Thời trang Nữ</v-col>
        </v-row>
        <v-row>
          <v-col
            v-for="(value, indexcate) in category_women.children"
            :key="indexcate"
            cols="6"
            md="4"
            sm="4"
          >
            <v-card id="caterogy" elevation="0" @click="nextpage(value)">{{
              value.name
            }}</v-card>
          </v-col>
        </v-row>
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
    category_women() {
      return this.$store.state.user.categorys.category_women
    },
    listproduct() {
      return this.$store.state.user.product.women
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
  font-weight: bold;
  text-align: center;
  font-size: 24px;
}
#caterogy {
  background-color: #ce785c;
  text-align: center;
  border-radius: 30px;
  font-size: 20px;
  padding-top: 10px;
  height: 50px;
}
#caterogy:hover {
  background-color: #ffab91;
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
  #caterogy {
    font-size: 16px;
    padding-top: 10px;
    height: 40px;
  }
}
</style>
