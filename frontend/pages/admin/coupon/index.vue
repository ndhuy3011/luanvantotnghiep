<template>
  <v-data-table
    :headers="$local.vn_admin_headerTable.COUPON_HEADER"
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
        <v-dialog v-model="dialogDelete" max-width="500px">
          <v-card>
            <v-card-title> </v-card-title>
            <v-card-text>
              <v-textarea
                v-model="editedItem.message"
                outlined
                name="input-7-4"
                label="Lý do xóa"
              ></v-textarea>
            </v-card-text>
            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn color="blue darken-1" text @click="closeDelete">
                {{ $local.vn_admin_general.BTN_CANCEL }}
              </v-btn>
              <v-btn color="blue darken-1" type="submit" text @click="validate">
                {{ $local.vn_admin_general.BTN_SAVE }}
              </v-btn>
            </v-card-actions>
          </v-card>
        </v-dialog>
      </v-toolbar>
    </template>
    <template #[`item.actions`]="{ item }">
      <v-icon v-if="item.status === true" small @click="deleteItem(item)">
        mdi-delete
      </v-icon>
    </template>
  </v-data-table>
</template>

<script>
import { mapGetters } from 'vuex'
export default {
  layout: 'admin',
  data: () => ({
    title: 'Danh sách sản phẩm',
    dialogDelete: false,
    search: '',
    editedIndex: -1,
    btnNew: 'Thêm',
    editedItem: {},
    data: [],
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
      coupon: 'admin/coupon/getAll',
    }),
    coupons() {
      return this.coupon
    },
  },
  watch: {
    dialog(val) {
      val || this.close()
    },
    dialogDelete(val) {
      val || this.closeDelete()
    },
  },

  methods: {
    init() {
      this.$store.dispatch(this.$constant.admin.ACTION_ADMIN_COUPON_INIT)
    },
    deleteItem(item) {
      this.editedIndex = this.coupons.indexOf(item)
      this.editedItem = Object.assign({}, item)
      this.dialogDelete = true
    },
    closeDelete() {
      this.dialogDelete = false
      this.$nextTick(() => {
        this.editedItem = Object.assign({}, this.defaultItem)
        this.editedIndex = -1
      })
    },
    async validate() {
      if (!this.editedItem.message) {
        this.$toast.error('Vui lòng bạn thêm lý do xóa', {
          position: 'top-center',
          type: 'error',
          duration: 5000,
          theme: 'outline',
          icon: 'error',
        })
        return
      }

      await this.$store.dispatch(
        this.$constant.admin.ACTION_ADMIN_COUPON_DELETE,
        this.editedItem
      )
      this.init()
      this.dialogDelete = false
    },
  },
}
</script>

<style></style>
