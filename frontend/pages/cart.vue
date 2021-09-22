<template>
  <b-container fluid class="shopping-cart">
    <b-row>
      <b-col v-show="cart.length < 1">
        <Bubble1 style="transform: rotate(180deg) translateY(25%)" />
        <div class="emtycart">
          <v-img
            contain
            max-height="150"
            width="100%"
            position="top center"
            :src="require('@/assets/logo/cart.png')"
          />
          <div class="d-flex justify-content-center">
            <h4>Giỏ hàng của bạn đang trống</h4>
          </div>
          <div v-show="user === null">
            <div class="d-flex justify-content-center">
              <h4>Đăng nhập để xem giỏ hàng của bạn và tiếp tục mua sắm</h4>
            </div>
            <div class="d-flex justify-content-center">
              <router-link
                :to="{ name: $local.vn.memu_account[0].url }"
                style="text-decoration: none"
              >
                <b-button class="btn" type="submit"
                  >{{ $local.vn.memu_account[0].title }}
                </b-button></router-link
              >
            </div>
          </div>
          <div class="d-flex justify-content-center">
            <router-link :to="{ name: 'shop' }" style="text-decoration: none">
              <b-button id="shopnow" class="btn" type="submit"
                >Mua ngay
              </b-button></router-link
            >
          </div>
        </div>
      </b-col>
      <b-col v-show="cart.length >= 1" cols="12" md="12">
        <div class="cart-table">
          <table>
            <thead>
              <tr>
                <th v-for="item in $local.vn.cart_tille" :key="item">
                  {{ item }}
                </th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="(item, index) in cart" :key="index">
                <td class="cart-pic" style="width: 21%">
                  <a data-tip="Xem chi tiết" @click="productdetail(item)">
                    <img
                      :src="item.image"
                      alt=""
                      style="width: 50%"
                      aspect-ratio="1"
                    />
                  </a>
                </td>
                <td class="cart-title">
                  <h5>
                    {{ item.name }} / {{ item.color }} /
                    {{ item.size }}
                  </h5>
                </td>
                <td class="p-price" aria-readonly="readonly">
                  {{ parseInt(item.price).toLocaleString() }}
                  {{ $local.vn.currency }}
                </td>
                <td class="qua-col">
                  <div class="quantity">
                    <div class="pro-qty">
                      <v-icon
                        slot="prepend"
                        color="green"
                        class="minus"
                        @click="minus(item, index)"
                      >
                        mdi-minus
                      </v-icon>
                      <input
                        class="count"
                        type="number"
                        min="1"
                        readonly
                        :value="item.quantity"
                      />
                      <v-icon
                        slot="append"
                        color="red"
                        class="plus"
                        @click="plus(item, index)"
                      >
                        mdi-plus
                      </v-icon>
                    </div>
                  </div>
                </td>
                <td class="total-price">
                  {{ parseInt(item.price * item.quantity).toLocaleString() }}
                  {{ $local.vn.currency }}
                </td>
                <td class="close-td" @click="removeProductCart(item, index)">
                  <b-icon icon="trash" style="cursor: pointer"></b-icon>
                </td>
              </tr>
            </tbody>
          </table>
        </div>
      </b-col>
    </b-row>
    <b-row v-show="cart.length >= 1">
      <b-col class="col-lg-4 offset-lg-8">
        <div class="proceed-checkout">
          <ul>
            <li class="cart-total">
              {{ $local.vn.total }}
              <span
                >{{ parseInt(total).toLocaleString() }}
                {{ $local.vn.currency }}</span
              >
            </li>
          </ul>
          <div @click="setcheckout()">
            <a class="proceed-btn">{{ $local.vn.checkout }}</a>
          </div>
        </div>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import { mapGetters } from 'vuex'
import Bubble1 from '../components/Bubble1'
export default {
  name: 'Cart',
  components: { Bubble1 },
  computed: {
    cart() {
      return this.$store.state.user.cart.cart
    },
    user() {
      return this.$auth.user ? this.$auth.user : null
    },
    ...mapGetters({
      // map `this.doneCount` to `this.$store.getters.doneTodosCount`
      total: 'user/cart/total',
    }),
  },
  watch: {
    user() {},
    cart() {},
  },
  created() {
    if (this.user !== null) {
      this.$store.dispatch(this.$constant.user.ACTION_SHOW_CART_USER)
    }
  },

  mounted() {
    this.$store.dispatch(this.$constant.user.ACTION_SHOW_CART)
  },
  methods: {
    productdetail(item) {
      this.$router.push({
        name: 'productdetail-id',
        params: {
          id: item.idProduct,
        },
      })
    },
    removeProductCart(item, index) {
      if (this.user !== null) {
        this.$store.dispatch(
          this.$constant.user.ACTION_DELETE_PRODUCTCART_USER,
          item
        )
      } else if (this.user === null) {
        this.$store.dispatch(this.$constant.user.ACTION_REMOVE_CART, index)
      }
    },
    plus(item, index) {
      if (this.user !== null) {
        this.$store.dispatch(
          this.$constant.user.ACTION_PLUS_ITEMCART_USER,
          item
        )
        this.$store.dispatch(this.$constant.user.ACTION_UPDATE_CART_USER, item)
      } else if (this.user === null) {
        this.$store.dispatch(this.$constant.user.ACTION_PLUS_ITEMCART, index)
      }
    },
    minus(item, index) {
      if (this.user !== null) {
        this.$store.dispatch(
          this.$constant.user.ACTION_MINUS_ITEMCART_USER,
          item
        )
        this.$store.dispatch(this.$constant.user.ACTION_UPDATE_CART_USER, item)
      } else if (this.user === null) {
        this.$store.dispatch(this.$constant.user.ACTION_MINUS_ITEMCART, index)
      }
    },
    setcheckout() {
      if (this.user === null) {
        this.$router.push({ name: 'loginregister' })
        this.$toast.global.login()
      } else if (this.user !== null) {
        this.$router.push({ name: 'checkout' })
        this.$store.dispatch(this.$constant.user.ACTION_CHECKOUT_SET, this.cart)
      }
    },
  },
}
</script>

<style lang="scss" scoped>
.shopping-cart {
  padding-top: 20px;
  padding-bottom: 40px;
  .cart-table {
    margin-bottom: 20px;
    table {
      width: 100%;
      border: 1px solid #ebebeb;
      tr th {
        font-size: 16px;
        color: #252525;
        font-weight: 500;
        border-bottom: 1px solid #ebebeb;
        text-align: center;
        padding: 18px 0 19px;
        text-transform: uppercase;
        .p-name {
          text-align: left;
        }
      }
      tr td {
        text-align: center;
        padding-bottom: 34px;
        .p-price {
          width: 16%;
        }
        .qua-col {
          width: 16%;
          .quantity {
            display: -webkit-box;
            display: -ms-flexbox;
            display: flex;
            -webkit-box-pack: center;
            -ms-flex-pack: center;
            justify-content: center;
          }
        }
        .total-price {
          width: 12%;
        }
      }
    }
  }
  .proceed-checkout {
    .proceed-btn {
      font-size: 14px;
      font-weight: 700;
      color: #000000;
      background: #ce785c;
      text-transform: uppercase;
      padding: 15px 25px 14px 25px;
      display: block;
      text-align: center;
      text-decoration: none;
    }
    .proceed-btn:hover {
      color: red;
    }
    ul {
      border: 2px solid #ebebeb;
      background: #f3f3f3;
      padding-left: 25px;
      padding-right: 25px;
      padding-top: 16px;
      padding-bottom: 20px;
      li {
        list-style: none;
        font-size: 16px;
        font-weight: 700;
        color: #252525;
        overflow: hidden;
        .cart-total {
          padding-top: 10px;
        }
        span {
          float: right;
          color: red;
        }
      }
    }
  }
}
.btn {
  display: block;
  height: 38px;
  width: 250px;
  border-radius: 20px;
  outline: none;
  border: none;
  background: rgb(0, 0, 0);
  color: white;
  background-size: 200%;
  text-transform: uppercase;
  margin: 0.5rem 0;
  cursor: pointer;
  font-size: 16px;
  transition: 0.5s;
}
.btn:hover {
  background: rgb(110, 110, 110);
}
#shopnow {
  width: 250px;
}
.count {
  text-align: center;
  width: 60px;
}
.minus {
  font-size: 17px;
  margin-right: 10px;
  cursor: pointer;
}
.plus {
  font-size: 17px;
  margin-left: -7px;
  cursor: pointer;
}
.emtycart {
  margin-top: -10%;
}
@media screen and (max-width: 600px) {
  .shopping-cart {
    .cart-table {
      table {
        tr th {
          font-size: 10px;
        }
      }
    }
    .proceed-checkout {
      .proceed-btn {
        font-size: 10px;
      }
      ul {
        li {
          font-size: 10px;
        }
      }
    }
  }
  * {
    font-size: 8px;
  }
  .count {
    width: 27px;
  }
  .minus {
    font-size: 10px;
    margin-right: 0px;
  }
  .plus {
    font-size: 10px;
    margin-left: -25px;
  }
  .btn {
    font-size: 10px;
  }
}
</style>
