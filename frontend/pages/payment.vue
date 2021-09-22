<template>
  <b-container>
    <b-row>
      <b-col cols="12" class="d-flex justify-content-center">
        <h2>
          <strong style="color: red">Quét mã QR để thanh toán</strong>
        </h2></b-col
      >
    </b-row>
    <b-row>
      <b-col cols="12" md="7">
        <div class="w-100">
          <h3><strong>Cảm ơn bạn! Đơn hàng của bạn đã được nhận</strong></h3>
          <ul>
            <li>
              <h5>
                Mã đơn hàng: <strong>{{ billCode }}</strong>
              </h5>
            </li>
            <li>
              <h5>
                Người nhận:
                <strong>Chủ cửa hàng 7755 Nguyễn Thị Mỹ Duyên</strong>
              </h5>
            </li>
            <li>
              <h5>Số điện thoại: <strong>0352775970</strong></h5>
            </li>
            <li>
              <h5>
                Số tiền:
                <strong>
                  {{ parseInt(total).toLocaleString() }}
                  {{ $local.vn.currency }}</strong
                >
              </h5>
            </li>
            <li>
              <h5>
                Phươn thức thanh toán:
                <strong>Quét mã QR MOMO</strong>
              </h5>
            </li>
          </ul>
          <h4 style="color: red">
            <strong
              >Ghi chú chuyển tiền bạn ghi mã đơn hàng: {{ billCode }}</strong
            >
          </h4>
        </div>
        <div class="mt-5">
          <b-button class="btn" type="submit" @click="payment"
            >Đã thanh toán</b-button
          >
        </div>
        <div style="margin-left: -22px">
          <v-expansion-panels flat>
            <v-expansion-panel>
              <v-expansion-panel-header>
                <h4>Hướng dẫn thanh toán MoMo</h4>
              </v-expansion-panel-header>
              <v-expansion-panel-content>
                <ol style="font-size: 20px">
                  <li>Mở điện thoại có phần cài đặt ứng dụng MoMo</li>
                  <li>
                    Mở điện thoại có phần cài đặt ứng dụng MoMo. Chọn quét mã
                    <v-img
                      contain
                      max-height="500"
                      width="100%"
                      position="top left"
                    />
                  </li>
                  <li>Quét mã MoMo mà cửa hàng đã cung cấp</li>
                  <li>
                    Nhập
                    <strong style="color: red"
                      >số tiền thanh toán và mã đơn hàng</strong
                    >
                    <v-img
                      contain
                      max-height="500"
                      width="100%"
                      position="top left"
                    />
                  </li>
                  <li>Chọn chuyển tiền. Và đợi nhận hàng thôi!</li>
                </ol>
              </v-expansion-panel-content>
            </v-expansion-panel>
          </v-expansion-panels>
        </div>
      </b-col>
      <b-col cols="12" md="5">
        <b-row>
          <b-col></b-col>
        </b-row>
        <div class="d-flex">
          <div>
            <img
              src="https://res.cloudinary.com/df9s1orh9/image/upload/v1625059475/momo.jpg?fbclid=IwAR2q6_bvrJFdSI2rghDWNPARMysex9_axVll7dezQBPn390m3N-np5ZQzUI"
              width="100%"
              height="100%"
              max-height="500"
              alt="MOMO_CODE"
            />
          </div>
        </div>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import { mapGetters } from 'vuex'
export default {
  computed: {
    billCode() {
      return this.$store.state.user.invoice.billCode
    },
    ...mapGetters({
      // map `this.doneCount` to `this.$store.getters.doneTodosCount`
      total: 'user/checkout/total',
    }),
  },
  methods: {
    payment() {
      this.$store.dispatch(
        this.$constant.user.ACTION_INVOICE_SAVEPAYMENT,
        this.billCode
      )
    },
  },
}
</script>

<style lang="scss" scoped>
.btn {
  display: block;
  height: 38px;
  width: 250px;
  border-radius: 20px;
  outline: none;
  border: none;
  background: rgb(255, 0, 0);
  color: white;
  background-size: 200%;
  text-transform: uppercase;
  margin: 0.5rem 0;
  cursor: pointer;
  transition: 0.5s;
}
.btn:hover {
  background: rgb(110, 110, 110);
}
</style>
