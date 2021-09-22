<template>
  <div>
    <v-data-table
      :headers="$local.vn_admin_headerTable.COLOR_HEADER"
      :items="colorData"
      sort-by="calories"
      class="elevation-1"
    >
      <template #[`item.code`]="{ item }">
        <v-chip :color="item.code">
          {{ item.code }}
        </v-chip>
      </template>
      <template #[`top`]>
        <v-toolbar flat>
          <v-toolbar-title>{{
            $local.vn_admin_color.COLOR_TILE
          }}</v-toolbar-title>
          <v-divider class="mx-4" inset vertical></v-divider>
          <v-spacer></v-spacer>
          <v-dialog v-model="dialog" max-width="500px">
            <template #[`activator`]="{ on, attrs }">
              <v-btn color="primary" dark class="mb-2" v-bind="attrs" v-on="on">
                {{ $local.vn_admin_color.COLOR_SAVE_NEW }}
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
                          :rules="$rule.ADMIN_COLOR_NAME"
                          label="Tên"
                          required
                        ></v-text-field>
                      </v-col>
                      <v-col cols="12" sm="6" md="6">
                        <v-text-field
                          v-model="editedItem.code"
                          v-mask="'!#XXXXXXXXX'"
                          :rules="$rule.ADMIN_COLOR_CODE"
                          type="text"
                          hide-details
                          solo
                          class="ma-0 pa-0"
                          required
                        >
                          <template #append>
                            <v-menu
                              v-model="menu"
                              top
                              nudge-bottom="105"
                              nudge-left="16"
                              :close-on-content-click="false"
                            >
                              <template #activator="{ on }">
                                <div :style="swatchStyle" v-on="on" />
                              </template>
                              <v-card>
                                <v-card-text class="pa-0">
                                  <v-color-picker
                                    v-model="editedItem.code"
                                    flat
                                  />
                                </v-card-text>
                              </v-card>
                            </v-menu>
                          </template>
                        </v-text-field>
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
          <v-dialog v-model="dialogDelete" max-width="500px">
            <v-card>
              <v-card-title class="headline">{{
                $local.vn_admin.DELETE_MSG
              }}</v-card-title>
              <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn color="blue darken-1" text @click="closeDelete">
                  {{ $local.vn_admin_general.BTN_CANCEL }}</v-btn
                >
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
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
export default {
  layout: 'admin',
  data: () => ({
    valid: true,
    dialog: false,
    dialogDelete: false,
    editedIndex: -1,
    menu: false,
    color: '#1976D2FF',
    editedItem: {
      name: '',
      code: '#1976D2FF',
    },
    defaultItem: {
      name: '',
      code: '#1976D2FF',
    },
  }),
  head() {
    return {
      title: this.$local.vn_admin.color_title,
      meta: [
        {
          hid: this.$local.vn_admin.color_title,
          name: this.$local.vn_admin.color_title,
          content: this.$local.vn_admin.content_color,
        },
      ],
    }
  },

  computed: {
    formTitle() {
      return this.editedIndex === -1
        ? this.$local.vn_admin_color.COLOR_SAVE_NEW
        : this.$local.vn_admin_color.COLOR_SAVE_EDIT
    },
    ...mapGetters({
      // map `this.doneCount` to `this.$store.getters.doneTodosCount`
      colors: 'admin/color/getAll',
    }),
    colorData() {
      return this.colors
    },
    swatchStyle() {
      const { menu } = this
      const { code } = this.editedItem
      return {
        backgroundColor: code,
        cursor: 'pointer',
        height: '30px',
        width: '30px',
        borderRadius: menu ? '50%' : '4px',
        transition: 'border-radius 200ms ease-in-out',
      }
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
      this.$store.dispatch(this.$constant.admin.ACTION_ADMIN_COLOR_INIT)
    },
    editItem(item) {
      this.editedIndex = this.colorData.indexOf(item)
      this.editedItem = Object.assign({}, item)
      this.dialog = true
    },

    deleteItem(item) {
      this.editedIndex = this.colorData.indexOf(item)
      this.editedItem = Object.assign({}, item)
      this.dialogDelete = true
    },

    deleteItemConfirm() {
      if (this.editedIndex > -1) {
        const item = Object.assign(
          this.colorData[this.editedIndex],
          this.editedItem
        )
        this.$store.dispatch(
          this.$constant.admin.ACTION_ADMIN_COLOR_DELETE,
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
    save() {
      let flap = false
      if (this.editedIndex > -1) {
        const item = this.$_.clone(this.colorData[this.editedIndex])
        flap = this.$store.dispatch(
          this.$constant.admin.ACTION_ADMIN_COLOR_UPDATA,
          Object.assign(item, this.editedItem)
        )
      } else {
        flap = this.$store.dispatch(
          this.$constant.admin.ACTION_ADMIN_COLOR_ADD,
          this.editedItem
        )
      }
      !flap || this.close()
      !flap || this.init()
    },
  },
}
</script>

<style></style>
