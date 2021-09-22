<template>
  <v-form ref="form" v-model="valid" lazy-validation @submit.prevent="onSubmit">
    <v-container>
      <v-row>
        <v-col cols="12" sm="6">
          <v-card>
            <v-card-text>
              <v-row
                ><v-col
                  ><h5>
                    {{ $local.vn_admin_product.INFORMATION }}
                  </h5></v-col
                ><v-spacer></v-spacer>
                <v-col class="d-flex justify-content-end"
                  ><v-btn
                    :disabled="!valid"
                    type="submit"
                    color="orange"
                    elevation="2"
                    outlined
                    plain
                    @click="validate"
                    >{{ $local.vn_admin_product.BTN_ADD_PRODUCT }}</v-btn
                  ></v-col
                > </v-row
              ><v-divider></v-divider>
              <v-container>
                <v-row>
                  <v-col cols="12" sm="6">
                    <v-text-field
                      v-model="form.name"
                      :label="$local.vn_admin_product.NAME"
                      :rules="$rule.ADMIN_PRODUCT_NAME"
                      outlined
                      clearable
                      required
                    ></v-text-field>
                  </v-col>
                  <v-col cols="12" sm="6">
                    <v-text-field
                      v-model="form.price"
                      v-mask="'#########'"
                      type="tel"
                      :rules="$rule.ADMIN_PRODUCT_PRICE"
                      :label="$local.vn_admin_product.PRICE"
                      outlined
                      suffix="₫"
                    />
                  </v-col>
                </v-row>
                <v-row>
                  <v-col height="auto">
                    <v-card outlined>
                      <v-row>
                        <v-col>
                          <v-toolbar-bar>{{
                            $local.vn_admin_product.IMAGE_ADD
                          }}</v-toolbar-bar></v-col
                        ></v-row
                      >
                      <v-row>
                        <v-col cols="2" sm="2">
                          <v-file-input
                            :rules="$rule.ADMIN_PRODUCT_IMAGE"
                            hide-input
                            accept="image/png, image/jpeg, image/bmp"
                            @change="inputFile($event, form)"
                          ></v-file-input>
                          <v-btn text icon @click="form.image = ''"
                            ><v-icon>{{
                              $local.vn_admin_product.ICON_BTN_CLOSE
                            }}</v-icon></v-btn
                          >
                        </v-col>
                        <v-col cols="10" sm="10">
                          <v-img
                            :src="form.image"
                            max-height="200"
                            max-width="200"
                            contain
                          ></v-img>
                        </v-col>
                      </v-row>
                    </v-card>
                  </v-col>
                  <v-col cols="12" sm="6">
                    <v-toolbar-bar>{{
                      $local.vn_admin_category.CATEGORY_TILE
                    }}</v-toolbar-bar>
                    <Treeselect
                      v-model="form.category"
                      :options="category"
                      :normalizer="normalizer"
                      :disable-branch-nodes="false"
                      :rules="$rule.ADMIN_PRODUCT_SELECT"
                      required
                    >
                    </Treeselect>
                    <span v-if="info.valid" style="color: red">{{
                      info.msg
                    }}</span>
                    <v-select
                      v-model="form.supplier"
                      class="mt-2"
                      :items="supplier"
                      :rules="$rule.ADMIN_PRODUCT_SUPPLIER_NAME"
                      :label="$local.vn_admin_product.SUPPLIER_NAME"
                      item-text="name"
                      item-value="id"
                      outlined
                    ></v-select>
                  </v-col>
                </v-row>
                <v-row>
                  <v-col cols="12" sm="12">
                    <v-textarea
                      v-model="form.infoSmall"
                      :label="$local.vn_admin_product.SHORT_DESCRIPTION"
                      :rules="$rule.ADMIN_PRODUCT_SHORT_DESCRIPTION"
                      outlined
                      clearable
                    >
                    </v-textarea>
                  </v-col>
                </v-row>
                <v-row>
                  <v-col cols="12" sm="12">
                    <v-toolbar-bar>{{
                      $local.vn_admin_product.DETAILS_DESCRIPTION
                    }}</v-toolbar-bar>
                    <span v-if="selectCategoryValid.valid" style="color: red">{{
                      selectCategoryValid.msg
                    }}</span>
                    <vue-editor
                      id="editor"
                      v-model="form.info"
                      use-custom-image-handler
                      @image-added="handleImageAdded"
                      @image-removed="handleImagDelete"
                    ></vue-editor>
                  </v-col>
                </v-row>
              </v-container>
            </v-card-text>
          </v-card>
        </v-col>
        <v-col cols="12" sm="6">
          <v-card>
            <v-container>
              <v-row>
                <v-card-text
                  ><p>{{ $local.vn_admin_product.INFORMATION_DETAIL }}</p>
                  <v-divider></v-divider>

                  <v-card
                    v-for="(
                      itemDetailsProduct, indexColor
                    ) in form.detailsProduct"
                    :key="indexColor"
                    elevation="2"
                    class="mt-2"
                  >
                    <v-container>
                      <v-row>
                        <v-col>
                          <v-card-text
                            ><p>
                              {{ $local.vn_admin_product.COLOR }}
                            </p></v-card-text
                          ></v-col
                        >

                        <v-col class="d-flex justify-content-end">
                          <v-btn
                            class="mr-1"
                            color="primary"
                            small
                            fab
                            @click="addColor"
                            ><v-icon>
                              {{
                                $local.vn_admin_product.ICON_ADD_PLUS
                              }}</v-icon
                            ></v-btn
                          >
                          <v-btn
                            color="error"
                            fab
                            small
                            @click="deleteColor(indexColor)"
                            ><v-icon>{{
                              $local.vn_admin_product.ICON_DEL_MINUS
                            }}</v-icon></v-btn
                          ></v-col
                        >
                      </v-row>

                      <v-divider></v-divider>
                      <v-row>
                        <v-col sm="6" cols="12">
                          <v-select
                            v-model="itemDetailsProduct.color"
                            :items="color"
                            :rules="$rule.ADMIN_PRODUCT_COLOR_NAME"
                            item-text="name"
                            item-value="id"
                            :label="$local.vn_admin_product.COLOR"
                            outlined
                          >
                            <template #selection="{ item }">
                              <v-chip
                                :text-color="
                                  item.code == '#FFFFFF' ? 'black' : 'white'
                                "
                                :color="item.code"
                              >
                                <span class="mr-2">{{ item.name }}</span>
                                {{ item.code }}</v-chip
                              >
                            </template></v-select
                          ></v-col
                        >
                        <v-col height="auto">
                          <v-card outlined>
                            <v-row>
                              <v-col>
                                <v-toolbar-bar>{{
                                  $local.vn_admin_product.IMAGE
                                }}</v-toolbar-bar></v-col
                              ></v-row
                            >
                            <v-row>
                              <v-col cols="2" sm="2">
                                <v-file-input
                                  :rules="$rule.ADMIN_PRODUCT_COLOR_IMAGE"
                                  hide-input
                                  accept="image/png, image/jpeg, image/bmp"
                                  @change="
                                    inputFile($event, itemDetailsProduct)
                                  "
                                ></v-file-input>
                                <v-btn
                                  text
                                  icon
                                  @click="itemDetailsProduct.image = ''"
                                  ><v-icon>mdi-close</v-icon></v-btn
                                >
                              </v-col>
                              <v-col cols="10" sm="10">
                                <v-img
                                  :src="itemDetailsProduct.image"
                                  max-height="200"
                                  max-width="200"
                                  contain
                                ></v-img>
                              </v-col>
                            </v-row>
                          </v-card>
                        </v-col>
                      </v-row>
                      <v-row>
                        <v-col>
                          <v-card-text
                            ><p>{{ $local.vn_admin_product.SIZE }}</p>
                          </v-card-text></v-col
                        >
                        <v-col class="d-flex justify-content-end">
                          <v-btn
                            class="mr-1"
                            color="primary"
                            small
                            fab
                            @click="addSize(indexColor)"
                            ><v-icon>{{
                              $local.vn_admin_product.ICON_ADD_PLUS
                            }}</v-icon></v-btn
                          >
                        </v-col>
                      </v-row>
                      <v-row
                        v-for="(itemSize, indexSize) in itemDetailsProduct.size"
                        :key="indexSize"
                      >
                        <v-col sm="6" cols="12">
                          <v-text-field
                            v-model.trim="itemSize.name"
                            :label="$local.vn_admin_product.NAME_SIZE"
                            :rules="$rule.ADMIN_PRODUCT_NAME_SIZE"
                            outlined
                            clearable
                            required
                          ></v-text-field>
                        </v-col>
                        <v-col
                          ><v-btn
                            color="error"
                            fab
                            small
                            @click="deleteSize(indexColor, indexSize)"
                            ><v-icon>{{
                              $local.vn_admin_product.ICON_DEL_MINUS
                            }}</v-icon></v-btn
                          ></v-col
                        >
                      </v-row>
                    </v-container>
                  </v-card>
                </v-card-text>
              </v-row>
            </v-container>
          </v-card>
        </v-col>
      </v-row>
    </v-container>
  </v-form>
</template>

<script>
import { mapGetters } from 'vuex'
import Treeselect from '@riophae/vue-treeselect'
import '@riophae/vue-treeselect/dist/vue-treeselect.css'
export default {
  components: { Treeselect },
  layout: 'admin',
  data: () => ({
    valid: false,
    selectCategoryValid: {
      valid: false,
      msg: 'Bạn phải chọn danh mục',
    },
    info: {
      valid: false,
      msg: 'Bạn phải thêm mô tả vào',
    },
    form: {
      name: '',
      price: 0,
      image: '',
      info: '',
      infoSmall: '',
      category: null,
      detailsProduct: [
        {
          image: '',
          color: null,
          size: [
            {
              name: '',
              amount: 0,
            },
          ],
        },
      ],
    },
    normalizer(node) {
      return {
        id: node.id,
        label: node.name,
        children: node.children,
      }
    },
    imageproduct: undefined,
    image: '',
    menu: false,
  }),
  computed: {
    ...mapGetters({
      categorys: 'admin/category/getFindAll',
      colors: 'admin/color/getAll',
      suppliers: 'admin/supplier/getFindAll',
    }),
    category() {
      return this.categorys
    },
    color() {
      return this.colors
    },
    supplier() {
      return this.suppliers
    },
  },
  created() {
    this.init()
  },
  methods: {
    init() {
      this.$store.dispatch(this.$constant.admin.ACTION_ADMIN_CATEGORY_INIT)
      this.$store.dispatch(this.$constant.admin.ACTION_ADMIN_COLOR_INIT)
    },
    async handleImageAdded(file, Editor, cursorLocation, resetUploader) {
      if (!file) return
      try {
        this.$toast.global.loading()
        const formData = new FormData()
        formData.append('file', file)
        await this.$axios
          .$post('https://localhost:8080/api/admin/file', formData)
          .then((response) => {
            Editor.insertEmbed(cursorLocation, 'image', response.url)
            resetUploader()
            this.$toast.global.success()
          })
      } catch (e) {
        this.$toast.global.error()
      }
    },
    handleImagDelete(file) {},
    async inputFile(event, item) {
      item.image = await this.createBase64Image(event)
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
    addColor() {
      const itemDetailsProduct = {
        name: '',
        image: '',
        code: '#1976D2FF',
        size: [
          {
            name: '',
            amount: 0,
          },
        ],
      }
      this.form.detailsProduct.push(itemDetailsProduct)
    },
    addSize(indexColor) {
      const itemSize = {
        name: '',
        amount: 0,
      }
      this.form.detailsProduct[indexColor].size.push(itemSize)
    },
    deleteColor(indexColor) {
      if (indexColor === 0) return
      this.form.detailsProduct.splice(indexColor, 1)
    },
    deleteSize(indexColor, indexSize) {
      if (indexColor === 0) return
      this.form.detailsProduct[indexColor].size.splice(indexSize, 1)
    },
    async onSubmit(event) {
      event.preventDefault()
      if (this.valid === false) {
        return
      }
      const flap = await this.$store.dispatch(
        this.$constant.admin.ACTION_ADMIN_PRODUCT_ADD,
        this.form
      )
      !flap || this.$router.push({ name: 'admin-product' })
    },
    validate() {
      this.$refs.form.validate()
      if (this.form.category === null) {
        this.selectCategoryValid.valid = true
        this.valid = true
      }
      if (this.form.info === '') {
        this.info.valid = true
        this.valid = true
      }
    },
  },
}
</script>

<style></style>
