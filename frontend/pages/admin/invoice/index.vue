<template>
  <v-data-table
    :headers="$local.vn_admin_headerTable.INVOICE_HEADER"
    :items="coupons"
    :search="search"
    sort-by="calories"
    class="elevation-1"
  >
    <template #[`item.status`]="{ item }">
      {{ item.status ? 'Bình thường' : 'Bị Xóa' }}
    </template>
    <template #[`top`]>
      <v-toolbar flat>
        <v-toolbar-title>{{ title }}</v-toolbar-title>
        <v-divider class="mx-4" inset vertical></v-divider>
        <v-text-field
          v-model="search"
          append-icon="mdi-magnify"
          label="Search"
          single-line
          hide-details
        ></v-text-field>

        <v-spacer></v-spacer>
        <v-dialog v-model="detailsDialog" max-width="900px">
          <v-card>
            <v-card-title
              >Chi tiết hóa đơn
              <v-spacer></v-spacer>

              <v-btn color="blue darken-1" text @click="close">
                {{ $local.vn_admin_general.BTN_CANCEL }}
              </v-btn></v-card-title
            >
            <v-container>
              <v-row>
                <v-col>
                  <span>Mã đơn hàng : {{ editedItem.id }}</span></v-col
                >
                <v-col
                  ><span
                    >Người đặt đơn hàng : {{ editedItem.user }}</span
                  ></v-col
                >
                <v-col
                  ><span
                    >Số điện thoại : {{ editedItem.numberPhone }}</span
                  ></v-col
                >
              </v-row>
              <v-row>
                <v-col
                  ><span>Địa chỉ : {{ editedItem.address }}</span></v-col
                ></v-row
              >
              <v-row>
                <v-col
                  ><span
                    >Trạng thái thanh toán :
                    {{ editedItem.statusPayment }}</span
                  ></v-col
                >
                <v-col
                  ><span>Mã thanh toán : {{ editedItem.billCode }}</span></v-col
                >
                <v-col
                  >Thời gian đặt đơn hàng : {{ editedItem.createDate }}</v-col
                >
              </v-row>
              <v-row>
                <v-col
                  ><span>Ghi chú : {{ editedItem.note }}</span></v-col
                >
              </v-row>
              <v-row>
                <v-col>
                  <span>Chi tiết đơn hàng</span>
                </v-col>
              </v-row>
              <v-row>
                <v-col>
                  <v-simple-table>
                    <template #default>
                      <thead>
                        <tr>
                          <th class="text-center"><h6>Tên sản phẩm</h6></th>
                          <th class="text-center"><h6>Giá tiền</h6></th>
                          <th class="text-center"><h6>Số lượng</h6></th>
                          <th class="text-center"><h6>Tổng tiền</h6></th>
                        </tr>
                      </thead>
                      <tbody>
                        <tr
                          v-for="(item, index) in editedItem.invoicedetals"
                          :key="index"
                        >
                          <td>{{ item.name }}</td>
                          <td>{{ item.price }}</td>
                          <td>{{ item.amount }}</td>
                          <td>{{ item.price * item.amount }}</td>
                        </tr>
                      </tbody>
                    </template></v-simple-table
                  ></v-col
                >
              </v-row>
            </v-container>
          </v-card>
        </v-dialog>
      </v-toolbar>
    </template>
    <template #[`item.actions`]="{ item }">
      <v-icon small @click="detail(item)"> mdi-eye </v-icon>
    </template>
  </v-data-table>
</template>

<script>
import { mapGetters } from 'vuex'
export default {
  layout: 'admin',
  data: () => ({
    title: 'Danh sách sản phẩm',
    detailsDialog: false,
    search: '',
    editedIndex: -1,
    btnNew: 'Thêm',
    editedItem: {},
    defaultItem: {},
    data: [],
    message: '',
    action: '',
    dialog: false,
  }),
  head() {
    return {
      title: this.title,
      meta: [
        {
          hid: 'Danh sách sản phẩm',
          name: 'Danh sách sản phẩm',
          content: 'Thêm sản phẩm vào cửa hàng 7755',
        },
      ],
    }
  },
  computed: {
    ...mapGetters({
      // map `this.doneCount` to `this.$store.getters.doneTodosCount`
      coupon: 'admin/invoice/getAll',
    }),
    coupons() {
      return this.coupon
    },
  },
  watch: {
    detail(val) {
      val || this.close()
    },
    billSubmit(val) {
      val || this.closeDialog()
    },
    billPaid(val) {
      val || this.closeDialog()
    },
    billDel(val) {
      val || this.closeDialog()
    },
  },

  methods: {
    detail(item) {
      this.detailsDialog = true
      this.editedItem = Object.assign({}, item)
    },
    close() {
      this.detailsDialog = false
      this.$nextTick(() => {
        this.editedItem = Object.assign({}, this.defaultItem)
      })
    },
  },
}
</script>

<style></style>
