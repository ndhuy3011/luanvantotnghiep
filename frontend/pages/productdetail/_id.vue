<template>
  <b-container>
    <b-row>
      <b-col cols="12" sm="6" md="6">
        <image-magnifier
          class="img"
          :src="imgActive"
          :zoom-src="imgActive"
          alt="img product"
          width="100%"
          height="100%"
          zoom-width="400"
          zoom-height="300"
        />
      </b-col>

      <b-col cols="12" sm="6" md="6">
        <div class="wrapper">
          <div class="outer">
            <div class="content">
              <span class="product__content">{{ products.name }}</span>
              <div class="colors-wrap">
                <h5>{{ $local.vn.color }}:</h5>
                <span
                  v-for="(item, indexColor) in products.detailsProduct"
                  :key="indexColor"
                  class="colors"
                  :style="{ background: item.color.code }"
                  :class="
                    colorActive.code === item.color.code ? 'selected' : ''
                  "
                  @click="ColorActive(item)"
                ></span>
              </div>

              <div class="size-wrap">
                <h5>{{ $local.vn.size }}:</h5>
                <span
                  v-for="(item, indexSize) in size"
                  :key="indexSize"
                  class="size"
                  :value="item.name"
                  :class="sizeActive.id === item.id ? 'selected' : ''"
                  @click="SizeActive(item)"
                  >{{ item.name }}</span
                >
              </div>
              <div class="size-wrap">
                <h5>Kho:</h5>
                <strong>
                  <h5 class="size" style="color: red; margin-top: -5px">
                    {{ sizeAmount }}
                  </h5>
                </strong>
              </div>

              <div class="size-wrap">
                <h5>{{ $local.vn.quantity }}:</h5>
                <v-icon
                  slot="prepend"
                  color="green"
                  class="minus"
                  @click="minus()"
                >
                  mdi-minus
                </v-icon>
                <b-form-input
                  id="input-amount"
                  v-model="quantity"
                  type="number"
                  min="1"
                  class="count"
                  :state="quantity <= sizeAmount && quantity > 0"
                  style="background-color: white; border: none"
                  >{{ quantity }}</b-form-input
                >
                <v-icon slot="append" color="red" class="plus" @click="plus()">
                  mdi-plus
                </v-icon>
              </div>

              <div class="button">
                <a href="#">
                  {{ parseInt(products.price).toLocaleString() }}
                  {{ $local.vn.currency }}</a
                >
                <a
                  v-show="
                    sizeAmount === 0 || quantity > sizeAmount || quantity <= 0
                  "
                  id="cart-none"
                >
                  <b-icon icon="cart"></b-icon> {{ $local.vn.add_cart }}</a
                >
                <a
                  v-show="
                    sizeAmount !== 0 && quantity <= sizeAmount && quantity > 0
                  "
                  @click="addCart()"
                >
                  <b-icon icon="cart"></b-icon> {{ $local.vn.add_cart }}</a
                >
              </div>
            </div>
          </div>
        </div>
      </b-col>
    </b-row>
    <b-row class="opinion">
      <v-col>
        <v-tabs color="accent-4">
          <v-tab
            v-for="item in $local.vn.menuoption"
            id="menu_title"
            :key="item"
            >{{ item }}</v-tab
          >

          <v-tab-item>
            <v-container fluid>
              <v-row>
                <v-col cols="12">
                  <!-- eslint-disable vue/no-v-html -->
                  <div class="info_img" v-html="products.info"></div>
                  <!--eslint-enable-->
                </v-col>
              </v-row>
            </v-container>
          </v-tab-item>

          <v-tab-item>
            <v-container fluid>
              <v-row>
                <v-col>
                  <v-form
                    ref="comment"
                    v-model="validComment"
                    lazy-validation
                    @submit.prevent="onComment"
                  >
                    <v-row>
                      <v-rating
                        v-model="userComment.rate"
                        background-color="grey lighten-2"
                        color="red"
                        empty-icon="mdi-heart-outline"
                        full-icon="mdi-heart"
                        half-icon="mdi-heart"
                        hover
                        length="5"
                        size="25"
                      ></v-rating
                    ></v-row>

                    <v-row>
                      <v-textarea
                        v-model="userComment.comment"
                        name="input-7-1"
                        rows="2"
                        auto-grow
                        label="Bình luận"
                        :rules="[$auth.user || 'Bạn phải đăng nhập']"
                      ></v-textarea
                    ></v-row>
                    <v-row class="d-flex justify-content-end"
                      ><v-btn
                        class="btn white--text"
                        :disabled="!validComment"
                        type="submit"
                        color="rgb(16, 120, 248)"
                        @click="validateComment"
                        >Đánh giá</v-btn
                      ></v-row
                    >
                  </v-form>
                </v-col>
              </v-row>
              <v-row>
                <v-col v-show="totalrate" cols="3">
                  <v-card elevation="0">
                    <v-row>
                      <h2>{{ totalrate }}</h2>
                      <div>
                        <v-rating
                          v-model="totalrate"
                          background-color="grey lighten-2"
                          color="red"
                          empty-icon="mdi-heart-outline"
                          full-icon="mdi-heart"
                          length="5"
                          size="20"
                        ></v-rating>
                        <h5>{{ totalcoment }} nhận xét</h5>
                      </div>
                    </v-row>
                  </v-card>
                </v-col>
                <v-col cols="9">
                  <v-row v-show="show">
                    <v-avatar color="primary" size="30"
                      ><v-icon dark> mdi-account </v-icon></v-avatar
                    >
                    <div style="margin-left: 10px">
                      <strong class="usercomment">{{
                        usercomment.user
                      }}</strong>
                      <div>
                        <v-rating
                          v-model="usercomment.rate"
                          background-color="grey lighten-2"
                          color="red"
                          empty-icon="mdi-heart-outline"
                          full-icon="mdi-heart"
                          half-icon="mdi-heart"
                          length="5"
                          size="18"
                        ></v-rating>
                        {{ usercomment.comment }}
                      </div>
                    </div>
                  </v-row>

                  <no-ssr v-if="comment != null">
                    <v-row v-for="(item, index) in comment" :key="index">
                      <v-avatar color="primary" size="30">
                        <v-icon dark> mdi-account </v-icon>
                      </v-avatar>
                      <div style="margin-left: 10px">
                        <strong class="usercomment">{{ item.user }}</strong>
                        <div>
                          <v-rating
                            v-model="item.rate"
                            background-color="grey lighten-2"
                            color="red"
                            empty-icon="mdi-heart-outline"
                            full-icon="mdi-heart"
                            half-icon="mdi-heart"
                            length="5"
                            size="18"
                          ></v-rating>
                          <h6 class="usercomment">{{ item.comment }}</h6>
                        </div>
                      </div>
                    </v-row>
                  </no-ssr>
                </v-col>
              </v-row>
            </v-container>
          </v-tab-item>
        </v-tabs>
      </v-col>
    </b-row>
    <h3>
      <strong>{{ $local.vn.same_product }}</strong>
    </h3>
    <b-row>
      <b-col
        v-for="item in filteredList"
        :key="item.id"
        cols="6"
        md="4"
        lg="3"
        sm="6"
        xs="6"
      >
        <Product :product="item" />
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import { mapGetters } from 'vuex'
import Product from '~/components/user/Product.vue'
export default {
  name: 'Productdetail',
  components: { Product },
  data() {
    return {
      validComment: false,
      userComment: {
        rate: 5,
        comment: '',
      },
      quantity: 1,
      imgActive: '',
      size: [],
      colorActive: {},
      sizeActive: {},
      show: false,
      sizeAmount: '',
    }
  },

  computed: {
    ...mapGetters({
      // map `this.doneCount` to `this.$store.getters.doneTodosCount`
      product: 'user/product/getFindProduct',
      totalrate: 'user/review/totalRate',
      totalcoment: 'user/review/totalComment',
      cart: 'user/cart/getData',
    }),
    listproduct() {
      return this.$store.state.user.product.list_products
    },
    comment() {
      return this.$store.state.user.review.comment
    },
    usercomment() {
      return this.$store.state.user.review.usercomment
    },
    products: {
      get() {
        const item = this.product({ id: parseInt(this.$route.params.id) })
        if (item) {
          return this.cloneProduct(item)
        }
        return {}
      },
      set(value) {
        this.products = value
      },
    },
    user() {
      return this.$auth.user ? this.$auth.user : ''
    },
    filteredList() {
      return this.listproduct.filter((item) => {
        return (
          item.category[0] === this.products.category[0] &&
          item.category[1] === this.products.category[1] &&
          item.id !== this.products.id
        )
      })
    },
  },
  watch: {
    ColorActive(item) {
      this.colorActive = item.color
      this.imgActive = item.image
      this.size = item.size
      this.sizeActive = this.size[0]
      this.sizeAmount = this.size[0].amount
    },
    user() {},
    comment() {},
  },
  created() {
    this.$store.dispatch(
      this.$constant.user.ACTION_GET_REVIEW,
      this.$route.params.id
    )
  },
  methods: {
    init() {},
    cloneProduct(item) {
      this.imgActive = item.image
      this.colorActive = item.detailsProduct[0].color
      this.size = item.detailsProduct[0].size
      this.sizeActive = item.detailsProduct[0].size[0]
      this.sizeAmount = item.detailsProduct[0].size[0].amount
      return this.$_.cloneDeep(item)
    },
    ColorActive(item) {
      this.colorActive = item.color
      this.imgActive = item.image
      this.size = item.size
      this.sizeActive = this.size[0]
      this.sizeAmount = this.size[0].amount
    },
    SizeActive(item) {
      this.sizeActive = item
      this.sizeAmount = item.amount
    },
    addCart() {
      const findCart = this.cart.find((el) => el.sizeId === this.sizeActive.id)
      if (findCart) {
        const temp = parseInt(this.quantity) + parseInt(findCart.quantity)
        if (this.sizeAmount < temp) {
          return
        }
      }
      const cartItem = {
        idProduct: this.products.id,
        name: this.products.name,
        sizeId: this.sizeActive.id,
        size: this.sizeActive.name,
        color: this.colorActive.name,
        image: this.imgActive,
        price: this.products.price,
        quantity: parseInt(this.quantity),
      }
      if (this.user !== '') {
        this.$store.dispatch(
          this.$constant.user.ACTION_CART_ADDTOCART_USER,
          cartItem
        )
      } else {
        this.$store.dispatch(
          this.$constant.user.ACTION_CART_ADDTOCART,
          cartItem
        )
      }
    },
    plus() {
      this.quantity++
    },
    minus() {
      if (this.quantity > 1) {
        this.quantity--
      }
    },
    validateComment() {
      this.$refs.comment.validate()
    },
    async onComment() {
      this.userComment.product = this.products.id
      await this.$store.dispatch(
        this.$constant.user.ACTION_POST_REVIEW,
        this.userComment
      )
      this.show = true
      this.userComment.comment = ''
    },
  },
}
</script>

<style lang="scss" scoped>
@import './assets/css/detailsproduct.scss';
</style>
