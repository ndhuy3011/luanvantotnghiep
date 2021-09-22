<template>
  <v-container>
    <v-row>
      <v-col class="d-flex justify-content-end">
        <v-dialog v-model="dialog" max-width="500px">
          <template #[`activator`]="{ on, attrs }">
            <v-btn
              outlined
              fab
              color="teal"
              small
              v-bind="attrs"
              v-on="on"
              @click="editItem(info)"
            >
              <v-icon> mdi-pencil </v-icon>
            </v-btn>
          </template>
          <v-card>
            <v-form
              ref="form"
              v-model="valid"
              lazy-validation
              @submit.prevent="save"
            >
              <v-card-title>
                <span class="headline">Thông tin Website</span>
              </v-card-title>
              <v-card-text>
                <v-container>
                  <v-row>
                    <v-col cols="6">
                      <v-text-field
                        v-model="editedItem.name"
                        :label="$local.vn_admin_infoweb.SHOP_NAME"
                        :rules="$rule.ADMIN_MAP"
                        required
                      ></v-text-field>
                    </v-col>
                    <v-col cols="6">
                      <v-text-field
                        v-model="editedItem.numberphone"
                        :label="$local.vn_admin_infoweb.PHONE"
                        :rules="$rule.ADMIN_NUMBERPHONE"
                        required
                      ></v-text-field>
                    </v-col>
                    <v-col cols="12">
                      <v-text-field
                        v-model="editedItem.address"
                        :label="$local.vn_admin_infoweb.ADDRESS"
                        :rules="$rule.ADMIN_MAP"
                        required
                      ></v-text-field>
                    </v-col>
                    <v-col cols="12">
                      <v-text-field
                        v-model="editedItem.facebook"
                        :label="$local.vn_admin_infoweb.FB"
                        :rules="$rule.ADMIN_MAP"
                        required
                      ></v-text-field>
                    </v-col>
                  </v-row>
                  <v-row>
                    <v-col cols="12">
                      <v-text-field
                        v-model="editedItem.linkAddress"
                        :label="$local.vn_admin_infoweb.LINK_MAP"
                        :rules="$rule.ADMIN_MAP"
                        required
                      ></v-text-field>
                    </v-col>
                  </v-row>
                </v-container>
              </v-card-text>

              <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn color="blue darken-1" text @click="close">
                  {{ $local.vn_admin_general.BTN_CANCEL }}
                </v-btn>
                <v-btn
                  :disabled="!valid"
                  color="blue darken-1"
                  type="submit"
                  text
                  @click="validate"
                >
                  {{ $local.vn_admin_general.BTN_SAVE }}
                </v-btn>
              </v-card-actions>
            </v-form>
          </v-card>
        </v-dialog>
      </v-col>
    </v-row>
    <v-row v-show="info.length != 0">
      <v-col cols="12" md="6" sm="12">
        <Map :map="info.linkAddress" />
      </v-col>
      <v-col cols="12" md="6" sm="12" class="infoweb">
        <h3 align="center" justify="center">Tên cửa hàng: {{ info.name }}</h3>
        <h5 align="center" justify="center">SĐT: {{ info.numberphone }}</h5>
        <h5 align="center" justify="center">Facebook: {{ info.facebook }}</h5>
        <h5 align="center" justify="center">Địa chỉ: {{ info.address }}</h5>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import Map from '~/components/user/Map.vue'
export default {
  components: { Map },
  layout: 'admin',
  data: () => {
    return {
      valid: true,
      dialog: false,
      editedItem: {
        name: '',
        address: '',
        numberphone: '',
        facebook: '',
        linkAddress: '',
      },
    }
  },
  computed: {
    info() {
      return this.$store.state.admin.infoweb.info
    },
  },
  watch: {
    dialog(val) {
      val || this.close()
    },
  },
  methods: {
    init() {
      this.$store.dispatch(this.$constant.admin.ACTION_ADMIN_INFOWEB_INIT)
    },
    editItem(item) {
      this.editedItem = Object.assign({}, item)
    },
    close() {
      this.dialog = false
      this.$nextTick(() => {
        this.editedItem = Object.assign({}, this.editedItem)
      })
    },

    validate() {
      this.$refs.form.validate()
    },
    async save(event) {
      event.preventDefault()
      if (!this.valid) return
      let flap = false
      flap = await this.$store.dispatch(
        this.$constant.admin.ACTION_ADMIN_INFOWEB_ADD,
        this.editedItem
      )
      !flap || this.close()
      !flap || this.init()
    },
  },
}
</script>
<style lang="scss" scoped>
.infoweb {
  margin-top: 17%;
}
@media screen and (max-width: 800px) {
  .infoweb {
    margin-top: 0;
  }
}
</style>
