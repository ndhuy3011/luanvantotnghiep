<template>
  <v-data-table
    :headers="$local.vn_admin_headerTable.REVIEW_HEADER"
    :items="reviews"
    :search="search"
    sort-by="calories"
    class="elevation-1"
  >
    <template #[`top`]>
      <v-toolbar flat>
        <v-toolbar-title>{{
          $local.vn_admin_review.REVIEW_TILE
        }}</v-toolbar-title>
        <v-divider class="mx-4" inset vertical></v-divider>
        <v-text-field
          v-model="search"
          append-icon="mdi-magnify"
          :label="$local.vn_admin_category.CATEGORY_SEARCH"
          single-line
          hide-details
        ></v-text-field>
        <v-spacer></v-spacer>
        <v-dialog v-model="dialogCheck" max-width="500px">
          <v-card>
            <v-card-title class="headline">{{
              $local.vn_admin_review.REVIEW_SUBMIT_MSG
            }}</v-card-title>
            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn color="blue darken-1" text @click="closeCheck">{{
                $local.vn_admin_general.BTN_CANCEL
              }}</v-btn>
              <v-btn color="blue darken-1" text @click="saveCheck">{{
                $local.vn_admin_general.BTN_SUBMIT
              }}</v-btn>
              <v-spacer></v-spacer>
            </v-card-actions>
          </v-card>
        </v-dialog>
        <v-dialog v-model="dialogDelete" max-width="500px">
          <v-card>
            <v-card-title class="headline">{{
              $local.vn_admin_review.REVIEW_DELETE_MSG
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
      <v-icon small class="mr-2" @click="editItem(item)">
        mdi-check-circle
      </v-icon>
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
    dialogCheck: false,
    dialogDelete: false,
    search: '',
    editedIndex: -1,
    editedItem: {
      name: '',
      sort: 1,
      reviews: '',
    },
    defaultItem: {
      name: '',
      sort: 1,
      reviews: '',
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
        ? this.$local.vn_admin_category.CATEGORY_SAVE_NEW
        : this.$local.vn_admin_category.CATEGORY_SAVE_EDIT
    },
    ...mapGetters({
      // map `this.doneCount` to `this.$store.getters.doneTodosCount`
      review: 'admin/review/getFindAll',
    }),
    reviews() {
      return this.review
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
    async init() {
      await this.$store.dispatch(this.$constant.admin.ACTION_ADMIN_REVIEW_INIT)
    },
    editItem(item) {
      this.editedIndex = this.reviews.indexOf(item)
      this.editedItem = Object.assign({}, item)
      this.dialogCheck = true
    },

    deleteItem(item) {
      this.editedIndex = this.reviews.indexOf(item)
      this.editedItem = Object.assign({}, item)
      this.dialogDelete = true
    },

    async deleteItemConfirm() {
      if (this.editedIndex > -1) {
        await this.$store.dispatch(
          this.$constant.admin.ACTION_ADMIN_REVIEW_DELETE,
          this.editedItem.userProductKey
        )
        await this.init()
      }
      this.closeDelete()
    },

    closeCheck() {
      this.dialogCheck = false
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
    async saveCheck(event) {
      event.preventDefault()
      if (!this.valid) return
      if (this.editedIndex > -1) {
        await this.$store.dispatch(
          this.$constant.admin.ACTION_ADMIN_REVIEW_SUBMIT,
          this.editedItem.userProductKey
        )
        await this.init()
      }
      this.closeCheck()
    },
  },
}
</script>
