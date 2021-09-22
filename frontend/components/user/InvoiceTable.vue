<template>
  <v-container fluid>
    <v-row
      v-for="(data, index) in invoice"
      v-show="invoice.length > 0"
      :key="index"
    >
      <v-col>
        <v-dialog v-model="dialog" max-width="500px">
          <v-card
            ><v-card-title>{{ message }}</v-card-title>
            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn color="blue darken-1" text @click="closeDialog">{{
                $local.vn_admin_general.BTN_CANCEL
              }}</v-btn>
              <v-btn color="blue darken-1" text @click="save(data)">{{
                $local.vn_admin_general.BTN_SUBMIT
              }}</v-btn></v-card-actions
            ></v-card
          >
        </v-dialog>
        <v-row class="mt-1">
          <v-col cols="12" md="4">
            <h5 style="cursor: pointer">
              <strong>{{ $local.vn.billCode }}</strong>
              [{{ data.billCode }}]
            </h5>
          </v-col>
          <v-col
            ><h5>
              <strong>{{ $local.vn.createBillDate }}</strong>
              {{ data.createDate }}
            </h5></v-col
          >
        </v-row>
        <v-row>
          <v-col cols="12" md="7">
            <v-row
              v-for="(item, indexitem) in data.invoiceDetails"
              :key="indexitem"
            >
              <v-col cols="12" md="4" sm="3">
                <v-img
                  contain
                  max-height="200"
                  width="100%"
                  :src="item.image"
                  position="left"
                  style="cursor: pointer"
                  @click="productdetail(item)"
                />
              </v-col>
              <v-col cols="12" md="8" sm="6">
                <h6>{{ item.name }}</h6>
                <v-col class="money">
                  {{ parseInt(item.price).toLocaleString() }}
                  x {{ item.amount }}
                </v-col>
              </v-col>
            </v-row>
          </v-col>
          <v-col cols="12" md="5" sm="12">
            <h6><strong>Địa chỉ nhận hàng: </strong></h6>
            <p>{{ data.username }} - {{ data.numberPhone }}</p>
            <p>{{ data.address }}</p>
            <h6>
              <strong>Phương thức thanh toán:</strong>
            </h6>
            <span v-show="data.payment === 'COD'">
              Thanh toán khi nhận hàng
            </span>
            <span v-show="data.payment === 'MoMo'">
              Thanh toán quét mã MoMo
            </span>
          </v-col>
        </v-row>
        <div class="d-flex">
          <div
            v-for="(t, indextotal) in total"
            :key="indextotal"
            class="d-flex total"
          >
            <div v-show="index === indextotal">
              <v-img
                contain
                max-height="25"
                width="100%"
                :src="require('@/assets/logo/money.png')"
                position="left"
                style="margin-right: -12px"
              />
            </div>
            <h6 v-show="index === indextotal">
              <strong>Thành tiền:</strong>
              <strong class="money"
                >{{ parseInt(t).toLocaleString() }}
                {{ $local.vn.currency }}</strong
              >
              <br />
              <span v-show="data.status == 2">
                Đơn hàng đang được chuyển đến bạn.
              </span>
            </h6>
          </div>
        </div>
        <div v-show="data.status <= 1 && data.payment === 'COD'">
          <b-button class="btn" type="submit" @click="billDel"
            >Hủy đơn hàng
          </b-button>
        </div>
        <div
          v-show="data.status <= 1 && data.payment === 'MoMo'"
          style="margin-left: 10px"
        >
          <router-link :to="{ name: 'contact' }">
            <h6>Nếu bạn muốn hủy đơn vui lòng liên hệ trực tiếp</h6>
          </router-link>
        </div>
        <div
          v-show="data.status === 3"
          @click="productdetail(data.invoiceDetails[0])"
        >
          <b-button class="btn">Đánh giá </b-button>
        </div>
      </v-col>
    </v-row>
    <v-row v-show="invoice.length === 0">
      <v-col>
        <v-img
          contain
          max-height="220"
          width="100%"
          position="top center"
          :src="require('@/assets/logo/404.svg')"
        />
        <h5 class="d-flex justify-content-center mt-3">Chưa có đơn hàng</h5>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
export default {
  props: ['invoice', 'total'],
  data() {
    return {
      detailsDialog: false,
      dialog: false,
      message: '',
    }
  },
  watch: {
    billDel(val) {
      val || this.closeDialog()
    },
  },
  methods: {
    productdetail(item) {
      this.$router.push({
        name: 'productdetail-id',
        params: {
          id: item.productId,
        },
      })
    },
    closeDialog() {
      this.message = ''
      this.dialog = false
    },
    billDel() {
      this.action = 'del'
      this.message = 'Bạn muốn hủy đơn hàng ?'
      this.detailsDialog = false
      this.dialog = true
    },
    async save(item) {
      if (this.action === 'del') {
        await this.$store.dispatch(this.$constant.user.ACTION_INVOICE_DEL, item)
      }
      this.closeDialog()
    },
  },
}
</script>

<style lang="scss" scoped>
.btn {
  display: block;
  height: 35px;
  width: 130px;
  border-radius: 20px;
  outline: none;
  border: none;
  background: rgb(255, 0, 0);
  color: white;
  background-size: 200%;
  cursor: pointer;
  transition: 0.5s;
  margin: -5px 0px 0px 20px;
}
.btn:hover {
  background: rgb(110, 110, 110);
}
.money {
  color: red;
  margin-left: 5px;
}
h6 {
  font-size: 18px;
}
@media screen and (max-width: 600px) {
  h6 {
    font-size: 14px;
  }
}
</style>
