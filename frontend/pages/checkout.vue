<template>
  <v-container fluid>
    <v-row>
      <v-col class="checkout_title d-flex justify-content-center">
        <h2 class="hidden-sm-and-down">
          <strong style="color: red">{{ $local.vn.info_checkout }}</strong>
        </h2>
      </v-col>
    </v-row>
    <v-row>
      <v-col cols="12" md="6">
        <v-card elevation="0">
          <v-card-title>
            <v-avatar color="#FF0000" size="32" class="avatar">1</v-avatar>
            <span class="text-h6">{{ $local.vn.info_bill }}</span>
          </v-card-title>
          <v-card-text>
            <v-container>
              <!-- Có đăng nhập -->
              <v-row v-if="user">
                <v-col cols="12">
                  <v-form ref="form" v-model="valid" lazy-validation>
                    <v-row>
                      <v-col cols="6">
                        <v-text-field
                          v-model="user.name"
                          :rules="userNameRules"
                          :label="$local.vn.full_name"
                          readonly
                          required
                        ></v-text-field>
                      </v-col>

                      <v-col cols="6">
                        <v-text-field
                          v-model="user.email"
                          :rules="emailRules"
                          label="Email"
                          required
                        ></v-text-field> </v-col
                    ></v-row>
                    <v-row>
                      <v-col cols="6">
                        <v-text-field
                          v-model="user.numberPhone"
                          :label="$local.vn.phone"
                          :rules="numberPhoneRules"
                          required
                        ></v-text-field>
                      </v-col>
                      <v-col cols="6">
                        <v-text-field
                          v-model="user.address"
                          :label="$local.vn.address"
                          :rules="addressRules"
                          required
                        ></v-text-field>
                      </v-col>
                    </v-row>
                    <v-row>
                      <v-col cols="4">
                        <v-select
                          v-model="select_provice"
                          :rules="rules"
                          :items="provice"
                          :label="$local.vn.provice"
                          item-value="code"
                          item-text="name"
                          @change="$fetch"
                        ></v-select>
                      </v-col>
                      <v-col cols="4">
                        <v-select
                          v-model="select_district"
                          :rules="rules"
                          :items="district"
                          :label="$local.vn.district"
                          item-text="name_with_type"
                          item-value="code"
                          @change="$fetch"
                        ></v-select>
                      </v-col>
                      <v-col cols="4">
                        <v-select
                          v-model="address"
                          :items="commune"
                          :rules="rules"
                          :label="$local.vn.commune"
                          item-text="name_with_type"
                          item-value="path_with_type"
                        ></v-select>
                      </v-col>
                    </v-row>
                    <v-row>
                      <v-col cols="12">
                        <v-textarea
                          v-model="note"
                          :label="$local.vn.note_bill"
                        ></v-textarea>
                      </v-col>
                    </v-row>
                  </v-form>
                </v-col>
              </v-row>
            </v-container>
          </v-card-text>
        </v-card>
      </v-col>
      <v-col cols="12" md="6">
        <!-- Phương thức thanh toán -->
        <v-row>
          <v-col cols="12">
            <v-card elevation="0">
              <v-card-title>
                <v-avatar color="#FF0000" size="32" class="avatar">2</v-avatar>
                <span class="text-h6">{{ $local.vn.payment }}</span>
              </v-card-title>
              <v-card-text>
                <v-container>
                  <v-row>
                    <v-col cols="12">
                      <v-radio-group v-model="select_checkout">
                        <div class="d-flex">
                          <v-radio value="COD"></v-radio>
                          {{ $local.vn.COD }}
                        </div>

                        <div class="d-flex">
                          <div class="mt-3">
                            <v-radio value="MoMo"></v-radio>
                          </div>
                          <div>
                            <label>
                              Quét mã QR MoMo
                              <img
                                :src="require('@/assets/logo/momologo.png')"
                                width="40"
                                height="40"
                                alt=""
                            /></label>
                          </div>
                        </div>
                        <div class="d-flex">
                          <div class="mt-5">
                            <v-radio value="ZaloPay" disabled></v-radio>
                          </div>
                          <div>
                            <label>
                              Quét mã
                              <img
                                :src="require('@/assets/logo/zalopay.svg')"
                                width="60"
                                height="60"
                                alt=""
                            /></label>
                          </div>
                        </div>
                      </v-radio-group>
                    </v-col>
                  </v-row>
                </v-container>
              </v-card-text>
            </v-card>
          </v-col>
        </v-row>
        <!-- Thông tin sản phẩm -->
        <v-row>
          <v-col cols="12">
            <v-card elevation="0">
              <v-card-title>
                <v-avatar color="#FF0000" size="32" class="avatar">3</v-avatar>
                <span class="text-h6">{{ $local.vn.info_cart }}</span>
              </v-card-title>
              <v-card-text>
                <v-container fluid>
                  <v-row>
                    <v-col cols="12">
                      <div class="cart-table">
                        <table>
                          <tbody>
                            <tr v-for="(item, index) in checkout" :key="index">
                              <td class="cart-pic" style="width: 21%">
                                <a
                                  data-tip="Xem chi tiết"
                                  @click="productdetail(item)"
                                >
                                  <img
                                    :src="item.image"
                                    alt=""
                                    style="width: 50%"
                                  />
                                </a>
                              </td>
                              <td class="cart-title">
                                <h6>
                                  {{ item.name }} / {{ item.color }} /
                                  {{ item.size }}
                                </h6>
                              </td>
                              <td class="p-price" aria-readonly="readonly">
                                {{ parseInt(item.price).toLocaleString() }} x
                                {{ item.quantity }}
                              </td>
                              <td
                                v-show="checkout.length > 1"
                                class="close-td"
                                @click="removeProductCheckout(index)"
                              >
                                <b-icon
                                  icon="trash"
                                  style="cursor: pointer"
                                ></b-icon>
                              </td>
                            </tr>
                          </tbody>
                        </table>
                      </div>

                      <div>
                        <v-row>
                          <v-col>
                            <strong>{{ $local.vn.total }}</strong>
                          </v-col>
                          <v-col>
                            <h6>
                              {{ parseInt(total).toLocaleString() }}
                              {{ $local.vn.currency }}
                            </h6>
                          </v-col>
                        </v-row>
                        <v-row>
                          <v-col class="d-flex justify-content-center">
                            <v-btn
                              class="ma-2 white--text"
                              color="#FF0000"
                              type="submit"
                              @click="submitCheckout()"
                            >
                              {{ $local.vn.checkout }}
                            </v-btn>
                            <router-link
                              :to="{ name: 'index' }"
                              style="text-decoration: none"
                            >
                              <v-btn class="ma-2 white--text" color="black">
                                {{ $local.vn.keep_buying }}
                              </v-btn>
                            </router-link>
                          </v-col>
                        </v-row>
                      </div>
                    </v-col>
                  </v-row>
                </v-container>
              </v-card-text>
            </v-card>
          </v-col>
        </v-row>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import { mapGetters } from 'vuex'
export default {
  name: 'Checkout',
  middleware: 'checkout',
  data() {
    return {
      valid: true,
      discount: '',
      select_provice: '',
      select_district: '',
      address: '',
      provice: [],
      district: [],
      commune: [],
      select_checkout: 'COD',
      note: '',
      user: {
        email: '',
        name: '',
        userName: '',
        address: '',
        numberPhone: '',
      },
      nameRules: [
        (v) => !!v || 'Tên người dùng không được để tróng',
        (v) => v.length > 6 || 'Tên người dùng ít nhất 6 kí tự',
      ],
      numberPhoneRules: [(v) => !!v || 'Số điện thoại không được để trống'],
      userNameRules: [
        (v) => !!v || 'Tên tài khoản không được để tróng',
        (v) => v.length > 6 || 'Tên tài khoản ít nhất 6 kí tự',
      ],
      addressRules: [(v) => !!v || 'Địa chỉ không được để trống'],
      rules: [(v) => !!v || 'Không được để trống'],
      emailRules: [
        (v) => !!v || 'E-mail không được để trống',
        (v) => /.+@.+/.test(v) || 'Không phải email',
      ],
    }
  },
  async fetch() {
    const data = await this.$content('location').fetch()
    Object.entries(data[0]).forEach((item) => {
      if (item[0] === item[1].code) {
        this.provice.push(item[1])
      }
    })
    if (this.select_provice) {
      const temp = []
      const data = await this.$content('location', 'quan-huyen').fetch()
      data.forEach((el) => {
        if (el.slug === this.select_provice) {
          Object.entries(el).forEach((item) => {
            if (item[0] === item[1].code) {
              temp.push(item[1])
            }
          })
        }
      })
      this.district = temp
    }
    if (this.select_district) {
      const temp = []
      const data = await this.$content('location', 'xa-phuong').fetch()
      data.forEach((el) => {
        if (el.slug === this.select_district) {
          Object.entries(el).forEach((item) => {
            if (item[0] === item[1].code) {
              temp.push(item[1])
            }
          })
        }
      })
      this.commune = temp
    }
  },
  computed: {
    checkout() {
      return this.$store.state.user.checkout.checkout
    },
    ...mapGetters({
      // map `this.doneCount` to `this.$store.getters.doneTodosCount`
      total: 'user/checkout/total',
    }),
  },
  created() {
    const item = this.$auth.user
    this.editItem(item)
  },
  methods: {
    editItem(item) {
      this.user = Object.assign({}, item)
    },
    productdetail(item) {
      this.$router.push({
        name: 'productdetail-id',
        params: {
          id: item.idProduct,
        },
      })
    },
    removeProductCheckout(indexRemove) {
      if (this.checkout.length > 1) {
        this.$store.dispatch(
          this.$constant.user.ACTION_REMOVE_PRODUCTCART_CHECKOUT,
          indexRemove
        )
      }
    },
    submitCheckout() {
      this.$refs.form.validate()
      const tam = []
      this.checkout.forEach((el) => {
        const item = {
          productId: el.idProduct,
          name: el.name + ' / ' + el.color + ' / ' + el.size,
          amount: el.quantity,
          price: el.price,
          color: el.color,
          sizeId: el.sizeId,
        }
        tam.push(item)
      })
      if (this.$refs.form.validate() === true) {
        const invoice = {
          numberPhone: this.user.numberPhone,
          address: this.user.address + ' ' + this.address,
          note: this.note,
          payment: this.select_checkout,
          invoiceDetailsRequests: tam,
        }
        if (this.select_checkout === 'MoMo') {
          this.$store.dispatch(this.$constant.user.ACTION_INVOICE_SET, invoice)

          this.checkout.forEach((el) => {
            this.$store.dispatch(
              this.$constant.user.ACTION_DELETE_PRODUCTCART_USER,
              el
            )
          })
        } else if (this.select_checkout === 'COD') {
          this.$store.dispatch(this.$constant.user.ACTION_INVOICE_SET, invoice)
          this.checkout.forEach((el) => {
            this.$store.dispatch(
              this.$constant.user.ACTION_DELETE_PRODUCTCART_USER,
              el
            )
          })
          this.$toast.global.checkout()
          this.$router.push({ name: 'index' })
        }
      }
    },
  },
}
</script>

<style lang="scss" scoped>
.checkout_title {
  margin-top: -10px;
  margin-bottom: 10px;
}
.avatar {
  margin-right: 10px;
}
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
  }
  * {
    font-size: 8px;
  }
}
</style>
