<template>
  <v-data-table
    :headers="$local.vn_admin_headerTable.SUPPLIER_HEADER"
    :items="supplier"
    :search="search"
    sort-by="calories"
    class="elevation-1"
  >
    <template #[`top`]>
      <v-toolbar flat>
        <v-toolbar-title>{{
          $local.vn_admin_supplier.SUPPLIER_TILE
        }}</v-toolbar-title>
        <v-divider class="mx-4" inset vertical></v-divider>
        <v-text-field
          v-model="search"
          append-icon="mdi-magnify"
          :label="$local.vn_admin_supplier.SUPPLIER_SEARCH"
          single-line
          hide-details
        ></v-text-field>
        <v-spacer></v-spacer>
        <v-dialog v-model="dialog" max-width="500px">
          <template #[`activator`]="{ on, attrs }">
            <v-btn color="primary" dark class="mb-2" v-bind="attrs" v-on="on">
              {{ $local.vn_admin_supplier.SUPPLIER_BTN_SAVE }}
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
                <span class="headline">{{ formTitle }}</span>
              </v-card-title>
              <v-card-text>
                <v-container>
                  <v-row>
                    <v-col cols="12" sm="6" md="6">
                      <v-text-field
                        v-model="editedItem.name"
                        :label="$local.vn_admin_supplier.SUPPLIER_NAME"
                        :rules="$rule.ADMIN_CATEGORY_NAME"
                        required
                      ></v-text-field>
                    </v-col>
                    <v-col cols="12" sm="6" md="6">
                      <v-text-field
                        v-model="editedItem.number"
                        :label="$local.vn_admin_supplier.SUPPLIER_NUMBER"
                        required
                      ></v-text-field>
                    </v-col>
                  </v-row>
                  <v-row>
                    <v-col cols="12" sm="12" md="12">
                      <v-text-field
                        v-model="editedItem.address"
                        :label="$local.vn_admin_supplier.SUPPLIER_ADDRESS"
                        required
                      ></v-text-field></v-col
                  ></v-row>
                  <v-row>
                    <v-col cols="12" sm="12" md="12">
                      <v-text-field
                        v-model="editedItem.website"
                        :label="$local.vn_admin_supplier.SUPPLIER_WEBSITE"
                        required
                      ></v-text-field></v-col
                  ></v-row>
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
    valid: true,
    dialog: false,
    dialogDelete: false,
    search: '',
    editedIndex: -1,
    editedItem: {
      name: '',
      number: '',
      address: '',
      website: '',
    },
    defaultItem: {
      name: '',
      number: '',
      address: '',
      website: '',
    },
    data: [],
  }),
  head() {
    return {
      title: this.$local.vn_admin.category_title,
      meta: [
        {
          hid: this.$local.vn_admin.category_title,
          name: this.$local.vn_admin.category_title,
          content: this.$local.vn_admin.content_category,
        },
      ],
    }
  },

  computed: {
    formTitle() {
      return this.editedIndex === -1
        ? this.$local.vn_admin_supplier.SUPPLIER_SAVE_NEW
        : this.$local.vn_admin_supplier.SUPPLIER_SAVE_EDIT
    },
    ...mapGetters({
      suppliers: 'admin/supplier/getFindAll',
    }),
    supplier() {
      return this.suppliers
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
      this.$store.dispatch(this.$constant.admin.ACTION_ADMIN_SUPPLIER_INIT)
    },
    editItem(item) {
      this.editedIndex = this.supplier.indexOf(item)
      this.editedItem = Object.assign({}, item)
      this.dialog = true
    },

    deleteItem(item) {
      this.editedIndex = this.supplier.indexOf(item)
      this.editedItem = Object.assign({}, item)
      this.dialogDelete = true
    },

    deleteItemConfirm() {
      if (this.editedIndex > -1) {
        const item = Object.assign(
          this.supplier[this.editedIndex],
          this.editedItem
        )
        this.$store.dispatch(
          this.$constant.admin.ACTION_ADMIN_SUPPLIER_DELETE,
          item
        )
        this.init()
      } else {
        // this.desserts.push(this.editedItem)
      }
      this.closeDelete()
    },

    close() {
      this.dialog = false
      this.$nextTick(() => {
        this.editedItem = Object.assign({}, this.defaultItem)
        this.editedIndex = -1
      })
    },

    closeDelete() {
      this.dialogDelete = false
      this.$nextTick(() => {
        this.editedItem = Object.assign({}, this.defaultItem)
        this.editedIndex = -1
      })
    },
    validate() {
      this.$refs.form.validate()
    },
    async save(event) {
      event.preventDefault()
      if (!this.valid) return
      let flap = false
      if (this.editedIndex > -1) {
        const item = this.$_.clone(this.supplier[this.editedIndex])
        flap = await this.$store.dispatch(
          this.$constant.admin.ACTION_ADMIN_SUPPLIER_UPDATA,
          Object.assign(item, this.editedItem)
        )
      } else {
        flap = await this.$store.dispatch(
          this.$constant.admin.ACTION_ADMIN_SUPPLIER_ADD,
          this.editedItem
        )
      }
      !flap || this.close()
      !flap || this.init()
    },
  },
}
</script>
