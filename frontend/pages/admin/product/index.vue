<template>
  <v-data-table
    :headers="$local.vn_admin_headerTable.PRODUCT_HEADER"
    :items="product"
    :search="search"
    sort-by="calories"
    class="elevation-1"
  >
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
        <v-dialog v-model="dialog" max-width="500px">
          <v-card>
            <v-card-text>
              <v-container>
                <v-row>
                  <v-col cols="12" sm="6" md="4">
                    <v-text-field label="Tên" required></v-text-field>
                  </v-col>
                </v-row>
              </v-container>
            </v-card-text>
          </v-card>
        </v-dialog>
        <v-dialog v-model="dialogDelete" max-width="500px">
          <v-card>
            <v-card-title class="headline">{{
              $local.vn_admin.DELETE_MSG
            }}</v-card-title>
            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn color="blue darken-1" text @click="closeDelete">{{
                $local.vn_admin_general.BTN_CANCEL
              }}</v-btn>
              <v-btn color="blue darken-1" text @click="deleteItemConfirm">{{
                $local.vn_admin_general.BTN_DELETE
              }}</v-btn>
              <v-spacer></v-spacer>
            </v-card-actions>
          </v-card>
        </v-dialog>
      </v-toolbar>
    </template>
    <template #[`item.actions`]="{ item }">
      <v-icon small class="mr-2" @click="editItem(item)"> mdi-pencil </v-icon>
      <v-icon small @click="deleteItem(item)"> mdi-delete </v-icon>
    </template>
  </v-data-table>
</template>

<script>
import { mapGetters } from 'vuex'
export default {
  layout: 'admin',
  data: () => ({
    title: 'Danh sách sản phẩm',
    dialog: false,
    dialogDelete: false,
    search: '',
    editedIndex: -1,
    btnNew: 'Thêm',
    editedItem: {
      name: '',
      sort: 1,
      category: '',
    },
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
      product: 'admin/product/getAll',
    }),
  },

  watch: {
    dialog(val) {
      val || this.close()
    },
    dialogDelete(val) {
      val || this.closeDelete()
    },
  },

  created() {
    this.init()
  },
  methods: {
    init() {
      this.$store.dispatch(this.$constant.admin.ACTION_ADMIN_PRODUCT_INIT)
    },
    editItem(item) {
      this.$router.push({
        name: 'admin-product-id',
        params: {
          id: item.id,
        },
      })
    },

    deleteItem(item) {
      this.editedIndex = this.product.indexOf(item)
      this.editedItem = Object.assign({}, item)
      this.dialogDelete = true
    },

    deleteItemConfirm() {
      if (this.editedIndex > -1) {
        const item = Object.assign(
          this.product[this.editedIndex],
          this.editedItem
        )
        this.$store.dispatch(
          this.$constant.admin.ACTION_ADMIN_PRODUCT_DELETE,
          item
        )
        this.init()
      } else {
        // this.desserts.push(this.editedItem)
      }
      this.closeDelete()
    },

    closeDelete() {
      this.dialogDelete = false
      this.$nextTick(() => {
        this.editedItem = Object.assign({}, this.defaultItem)
        this.editedIndex = -1
      })
    },
  },
}
</script>

<style></style>
