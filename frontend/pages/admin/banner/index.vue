<template>
  <v-container>
    <v-row>
      <v-col>
        <v-dialog v-model="dialog" max-width="500px">
          <template #[`activator`]="{ on, attrs }">
            <v-btn color="primary" dark class="mb-2" v-bind="attrs" v-on="on">
              {{ $local.vn_admin_banner.BANNER_BTN_ADD }}
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
                <span class="headline">Thêm banner</span>
              </v-card-title>

              <v-card-text>
                <v-container>
                  <v-row>
                    <v-col cols="12" sm="6" md="8">
                      <v-row>
                        <v-col cols="2" sm="2">
                          <v-file-input
                            :rules="$rule.ADMIN_PRODUCT_IMAGE"
                            hide-input
                            accept="image/png, image/jpeg, image/bmp"
                            @change="inputFile($event, banner)"
                          ></v-file-input>
                          <v-btn text icon @click="banner.file = ''"
                            ><v-icon>{{
                              $local.vn_admin_product.ICON_BTN_CLOSE
                            }}</v-icon></v-btn
                          >
                        </v-col>
                        <v-col cols="10" sm="10">
                          <v-img
                            :src="banner.file"
                            max-height="400"
                            max-width="300"
                            contain
                          ></v-img>
                        </v-col>
                      </v-row>
                    </v-col>
                    <v-col cols="12" sm="6" md="4">
                      <v-select
                        v-model="banner.sort"
                        :rules="$rule.ADMIN_MAP"
                        :items="$local.vn_admin_banner.BANNER_SELECT"
                        item-value="value"
                        item-text="text"
                        :label="$local.vn_admin_banner.BANNER"
                        required
                      ></v-select
                    ></v-col>
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
    <v-row>
      <v-col>
        <v-card flat>
          <v-simple-table>
            <template #default>
              <thead>
                <tr>
                  <th
                    v-for="(item, index) in $local.vn_admin_headerTable
                      .BANNER_HEADER"
                    :key="index"
                    class="text-center"
                  >
                    {{ item.text }}
                  </th>
                </tr>
              </thead>
              <tbody>
                <tr v-for="(item, index) in listbanner" :key="index">
                  <td>
                    <h6 class="d-flex justify-content-center">
                      {{ item.sort }}
                    </h6>
                  </td>
                  <td>
                    <v-img
                      id="img"
                      :src="item.file.url"
                      :lazy-src="require(`~/assets/loadding.png`)"
                    />
                  </td>
                  <td>
                    <v-btn
                      color="error"
                      outlined
                      fab
                      medium
                      dark
                      @click="deleteBanner(item)"
                    >
                      <v-icon>mdi-delete</v-icon>
                    </v-btn>
                  </td>
                </tr>
              </tbody>
            </template>
          </v-simple-table>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
export default {
  layout: 'admin',
  data: () => {
    return {
      valid: true,
      dialog: false,
      banner: {
        sort: '',
        file: '',
      },
    }
  },
  computed: {
    listbanner() {
      return this.$store.state.admin.banner.banner
    },
  },
  watch: {
    dialog(val) {
      val || this.close()
    },
  },
  created() {
    this.$store.dispatch(this.$constant.admin.ACTION_ADMIN_BANNER_INIT)
  },
  methods: {
    init() {
      this.$store.dispatch(this.$constant.admin.ACTION_ADMIN_BANNER_INIT)
    },
    async inputFile(event, item) {
      item.file = await this.createBase64Image(event)
    },
    async createBase64Image(file) {
      const readData = (f) =>
        new Promise((resolve) => {
          const reader = new FileReader()
          reader.onloadend = () => resolve(reader.result)
          reader.readAsDataURL(f)
        })
      /* Read data */
      return await readData(file)
    },
    close() {
      this.dialog = false
    },
    validate() {
      this.$refs.form.validate()
    },
    reset() {
      this.banner.sort = ''
      this.banner.file = ''
    },
    async save(event) {
      event.preventDefault()
      if (!this.valid) return
      let flap = false
      flap = await this.$store.dispatch(
        this.$constant.admin.ACTION_ADMIN_BANNER_ADD,
        this.banner
      )
      !flap || this.close()
      !flap || this.init()
      this.reset()
    },
    deleteBanner(item) {
      let flap = false
      flap = this.$store.dispatch(
        this.$constant.admin.ACTION_ADMIN_BANNER_DELETE,
        item
      )
      !flap || this.close()
      !flap || this.init()
    },
  },
}
</script>
<style lang="scss" scoped>
#img {
  display: block;
  margin-left: auto;
  margin-right: auto;
  width: 300px;
  margin-top: 10px;
}
</style>
