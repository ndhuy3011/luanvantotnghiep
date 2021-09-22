<template>
  <v-container fluid>
    <v-card elevation="0">
      <v-tabs v-show="!mobileView" vertical touchless>
        <v-tab style="margin-left: -55px">
          <b-icon icon="bookmark" style="margin-right: 10px"></b-icon>Chờ xác
          nhận
          <span>{{ not.length }}</span>
        </v-tab>
        <v-tab>
          <b-icon icon="bookmark-check" style="margin-right: 10px"></b-icon>
          Đang chuẩn bị hàng
          <span>{{ accuracy.length }}</span>
        </v-tab>
        <v-tab style="margin-left: -75px">
          <b-icon icon="truck" style="margin-right: 10px"></b-icon>Vận chuyển
          <span>{{ transport.length }}</span>
        </v-tab>
        <v-tab style="margin-left: -75px">
          <b-icon icon="clipboard-check" style="margin-right: 10px"></b-icon
          >Hoàn thành
          <span>{{ complete.length }}</span>
        </v-tab>
        <v-tab style="margin-left: -75px">
          <b-icon icon="clipboard-x" style="margin-right: 10px"></b-icon>Đơn đã
          hủy
          <span>{{ cancel.length }}</span>
        </v-tab>
        <v-tab style="margin-left: -25px">
          <b-icon icon="person" style="margin-right: 10px"></b-icon>
          {{ $local.vn.profile }}
        </v-tab>
        <v-tab-item>
          <InvoiceTable :invoice="not" :total="totalnot" />
        </v-tab-item>
        <v-tab-item>
          <InvoiceTable :invoice="accuracy" :total="totalaccuracy" />
        </v-tab-item>
        <v-tab-item>
          <InvoiceTable :invoice="transport" :total="totaltransport" />
        </v-tab-item>
        <v-tab-item>
          <InvoiceTable :invoice="complete" :total="totalcomplete" />
        </v-tab-item>
        <v-tab-item>
          <InvoiceTable :invoice="cancel" :total="totalcancel" />
        </v-tab-item>

        <v-tab-item>
          <v-card>
            <v-list-item three-line>
              <v-list-item-content>
                <v-list-item-title>
                  <h4 class="title">{{ $local.vn.profile }}</h4>
                </v-list-item-title>
                <v-list-item-subtitle>
                  <v-form v-model="valid">
                    <v-container>
                      <v-row>
                        <v-col cols="12" md="4">
                          <v-text-field
                            v-model="user.email"
                            label="E-mail"
                            readonly
                            required
                          ></v-text-field>
                        </v-col>
                        <v-col cols="12" md="4">
                          <v-text-field
                            v-model="user.userName"
                            :rules="userNameRules"
                            :label="$local.vn.account_name"
                            readonly
                            required
                          ></v-text-field>
                        </v-col>
                        <v-col cols="12" md="4">
                          <v-text-field
                            v-model="user.name"
                            :rules="nameRules"
                            :label="$local.vn.user_name"
                            required
                          ></v-text-field>
                        </v-col>
                        <v-col cols="12" md="8">
                          <v-text-field
                            v-model="user.address"
                            :rules="addressRules"
                            :label="$local.vn.address"
                            required
                          ></v-text-field>
                        </v-col>
                        <v-col cols="12" md="4">
                          <v-text-field
                            v-model="user.numberPhone"
                            :rules="numberPhoneRules"
                            type="number"
                            :label="$local.vn.numberPhone"
                            required
                          ></v-text-field>
                        </v-col>
                      </v-row>
                    </v-container>
                  </v-form>
                </v-list-item-subtitle>
              </v-list-item-content>
            </v-list-item>

            <v-card-actions class="d-flex justify-content-end">
              <v-btn class="btn_update" outlined rounded text @click="update()">
                {{ $local.vn.update }}
              </v-btn>
            </v-card-actions>
          </v-card>
        </v-tab-item>
      </v-tabs>

      <v-tabs v-show="mobileView" vertical>
        <v-tab>
          <b-icon icon="bookmark" style="margin-right: 10px"></b-icon>
          <span>{{ not.length }}</span>
        </v-tab>
        <v-tab>
          <b-icon icon="bookmark-check" style="margin-right: 10px"></b-icon>
          <span>{{ accuracy.length }}</span>
        </v-tab>
        <v-tab>
          <b-icon icon="truck" style="margin-right: 10px"></b-icon>
          <span>{{ transport.length }}</span>
        </v-tab>
        <v-tab>
          <b-icon icon="clipboard-check" style="margin-right: 10px"></b-icon>
          <span>{{ complete.length }}</span>
        </v-tab>
        <v-tab>
          <b-icon icon="clipboard-x" style="margin-right: 10px"></b-icon>
          <span>{{ cancel.length }}</span>
        </v-tab>
        <v-tab style="margin-left: -35px">
          <b-icon icon="person"></b-icon>
        </v-tab>

        <v-tab-item>
          <InvoiceTable :invoice="not" :total="totalnot" />
        </v-tab-item>
        <v-tab-item>
          <InvoiceTable :invoice="accuracy" :total="totalaccuracy" />
        </v-tab-item>
        <v-tab-item>
          <InvoiceTable :invoice="transport" :total="totaltransport" />
        </v-tab-item>
        <v-tab-item>
          <InvoiceTable :invoice="complete" :total="totalcomplete" />
        </v-tab-item>
        <v-tab-item>
          <InvoiceTable :invoice="cancel" :total="totalcancel" />
        </v-tab-item>

        <v-tab-item>
          <v-card>
            <v-list-item three-line>
              <v-list-item-content>
                <v-list-item-title>
                  <h4 class="title">{{ $local.vn.profile }}</h4>
                </v-list-item-title>
                <v-list-item-subtitle>
                  <v-form v-model="valid">
                    <v-container>
                      <v-row>
                        <v-col cols="12" md="4">
                          <v-text-field
                            v-model="user.email"
                            label="E-mail"
                            readonly
                            required
                          ></v-text-field>
                        </v-col>
                        <v-col cols="12" md="4">
                          <v-text-field
                            v-model="user.userName"
                            :rules="userNameRules"
                            :label="$local.vn.account_name"
                            readonly
                            required
                          ></v-text-field>
                        </v-col>
                        <v-col cols="12" md="4">
                          <v-text-field
                            v-model="user.name"
                            :rules="nameRules"
                            :label="$local.vn.user_name"
                            required
                          ></v-text-field>
                        </v-col>
                        <v-col cols="12" md="8">
                          <v-text-field
                            v-model="user.address"
                            :rules="addressRules"
                            :label="$local.vn.address"
                            required
                          ></v-text-field>
                        </v-col>
                        <v-col cols="12" md="4">
                          <v-text-field
                            v-model="user.numberPhone"
                            :rules="numberPhoneRules"
                            type="number"
                            :label="$local.vn.numberPhone"
                            required
                          ></v-text-field>
                        </v-col>
                      </v-row>
                    </v-container>
                  </v-form>
                </v-list-item-subtitle>
              </v-list-item-content>
            </v-list-item>

            <v-card-actions class="d-flex justify-content-end">
              <v-btn class="btn_update" outlined rounded text @click="update()">
                {{ $local.vn.update }}
              </v-btn>
            </v-card-actions>
          </v-card>
        </v-tab-item>
      </v-tabs>
    </v-card>
  </v-container>
</template>

<script>
import InvoiceTable from '../components/user/InvoiceTable.vue'
export default {
  components: { InvoiceTable },
  data: () => ({
    valid: false,
    nameRules: [(v) => !!v || 'Tên người dùng không được để tróng'],
    numberPhoneRules: [(v) => !!v || 'Số điện thoại không được để trống'],
    userNameRules: [(v) => !!v || 'Tên tài khoản không được để tróng'],
    addressRules: [(v) => !!v || 'Đại chỉ không được để trống'],
    emailRules: [
      (v) => !!v || 'E-mail không được để trống',
      (v) => /.+@.+/.test(v) || 'Không phải email',
    ],
    user: {
      email: '',
      name: '',
      userName: '',
      address: '',
      numberPhone: '',
    },
    mobileView: true,
  }),
  computed: {
    not() {
      return this.$store.state.user.invoice.not
    },
    accuracy() {
      return this.$store.state.user.invoice.accuracy
    },
    transport() {
      return this.$store.state.user.invoice.transport
    },
    complete() {
      return this.$store.state.user.invoice.complete
    },
    cancel() {
      return this.$store.state.user.invoice.cancel
    },
    totalnot() {
      return this.$store.state.user.invoice.totalnot
    },
    totalaccuracy() {
      return this.$store.state.user.invoice.totalaccuracy
    },
    totaltransport() {
      return this.$store.state.user.invoice.totaltransport
    },
    totalcomplete() {
      return this.$store.state.user.invoice.totalcomplete
    },
    totalcancel() {
      return this.$store.state.user.invoice.totalcancel
    },
  },
  watch() {
    this.editItem(this.$auth.user)
  },
  created() {
    this.handleView()
    addEventListener('resize', this.handleView)
    const item = this.$auth.user
    this.editItem(item)
    this.$store.dispatch(this.$constant.user.ACTION_INVOICE_INIT)
  },
  methods: {
    handleView() {
      this.mobileView = window.innerWidth <= 1050
    },
    initInvoice() {
      this.$store.dispatch(this.$constant.user.ACTION_INVOICE_INIT)
    },
    editItem(item) {
      this.user = Object.assign({}, item)
    },
    update() {
      this.$store.dispatch(this.$constant.user.ACTIONS_USER_UPDATE, this.user)
    },
  },
}
</script>

<style lang="scss" scoped>
.title {
  color: rgb(24, 149, 221);
}
.btn_update {
  background-color: rgb(24, 149, 221);
  color: aliceblue;
}
.member {
  display: block;
  height: 32px;
  border-radius: 30px;
  outline: none;
  border: none;
  background: rgb(241, 16, 16);
  color: white;
  background-size: 200%;
  padding: 3px 9px;
  cursor: pointer;
  font-size: 16px;
  transition: 0.5s;
}
.listmenu {
  cursor: pointer;
}
span {
  height: 20px;
  width: 20px;
  background: #ec410d;
  color: #ffffff;
  border-radius: 50%;
  font-size: 10px;
  font-weight: 650;
  text-align: center;
  line-height: 15px;
  padding: 3px;
  margin-left: 3px;
}
</style>
