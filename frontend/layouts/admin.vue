<template>
  <div id="app">
    <v-app id="inspire">
      <Navigation :menu="menu" />
      <v-main>
        <Nuxt />
      </v-main>
    </v-app>
  </div>
</template>

<script>
import Navigation from '~/components/admin/header/Navigation'
export default {
  components: { Navigation },
  middleware: 'authenticated',
  computed: {
    menu() {
      return [
        // {
        //   name: this.$local.vn_admin_menu.REPORT_MANAGEMENT,
        //   function: [
        //     {
        //       name: this.$local.vn_admin_menu.PRODUCT_IMPORT_MANAGEMENT,
        //       function: [
        //         {
        //           name: this.$local.vn_admin_menu.PRODUCT_IMPORT_VIEW,
        //           url: 'admin-coupon',
        //         },
        //       ],
        //     },
        //   ],
        // },
        {
          name: this.$local.vn_admin_menu.CONTENT_MANAGEMENT,
          function: [
            {
              name: this.$local.vn_admin_menu.CATEGORY_MANAGEMENT,
              function: [
                {
                  name: this.$local.vn_admin_menu.CATEGORY_VIEW,
                  url: 'admin-category',
                },
              ],
            },
            {
              name: this.$local.vn_admin_menu.SUPPLIER_MANAGEMENT,
              function: [
                {
                  name: this.$local.vn_admin_menu.SUPPLIER_VIEW,
                  url: 'admin-supplier',
                },
              ],
            },
            {
              name: this.$local.vn_admin_menu.PRODUCT_MANAGEMENT,
              function: [
                {
                  name: this.$local.vn_admin_menu.PRODUCT_VIEW,
                  url: 'admin-product',
                },
                {
                  name: this.$local.vn_admin_menu.PRODUCT_ADD,
                  url: 'admin-product-add',
                },
              ],
            },
            {
              name: this.$local.vn_admin_menu.COLOR_MANAGEMENT,
              function: [
                {
                  name: this.$local.vn_admin_menu.COLOR_VIEW,
                  url: 'admin-color',
                },
              ],
            },
            {
              name: this.$local.vn_admin_menu.REVIEW_MANAGEMENT,
              function: [
                {
                  name: this.$local.vn_admin_menu.REVIEW_VIEW,
                  url: 'admin-review',
                },
              ],
            },
            {
              name: this.$local.vn_admin_menu.INFOWEB_MANAGEMENT,
              function: [
                {
                  name: this.$local.vn_admin_menu.INFOWEB_VIEW,
                  url: 'admin-info',
                },
              ],
            },
            {
              name: this.$local.vn_admin_menu.BANNER_MANAGEMENT,
              function: [
                {
                  name: this.$local.vn_admin_menu.BANNER_VIEW,
                  url: 'admin-banner',
                },
              ],
            },
          ],
        },
        {
          name: this.$local.vn_admin_menu.WAREHOUSE_MANAGEMENT,
          function: [
            {
              name: this.$local.vn_admin_menu.PRODUCT_IMPORT_MANAGEMENT,
              function: [
                {
                  name: this.$local.vn_admin_menu.PRODUCT_IMPORT_VIEW,
                  url: 'admin-coupon',
                },
              ],
            },
            {
              name: this.$local.vn_admin_menu.INVOICE_MANAGEMENT,
              function: [
                {
                  name: this.$local.vn_admin_menu.INVOICE_VIEW,
                  url: 'admin-invoice',
                },
                {
                  name: this.$local.vn_admin_menu.INVOICE_NOT_RECEIVE,
                  url: 'admin-invoice-not',
                },
                {
                  name: this.$local.vn_admin_menu.INVOICE_ACCURACY_RECEIVE,
                  url: 'admin-invoice-accuracy',
                },
                {
                  name: this.$local.vn_admin_menu.INVOICE_TRANSPORT_RECEIVE,
                  url: 'admin-invoice-transport',
                },
                {
                  name: this.$local.vn_admin_menu.INVOICE_COMPLETE_RECEIVE,
                  url: 'admin-invoice-complete',
                },
                {
                  name: this.$local.vn_admin_menu.INVOICE_CANCEL_RECEIVE,
                  url: 'admin-invoice-cancel',
                },
                {
                  name: this.$local.vn_admin_menu.INVOICE_RETURN_RECEIVE,
                  url: 'admin-invoice-return',
                },
              ],
            },
          ],
        },
      ]
    },
  },
  created() {
    this.$store.dispatch(this.$constant.admin.ACTION_ADMIN_REPORT_INIT)
    this.$store.dispatch(this.$constant.admin.ACTION_ADMIN_CATEGORY_INIT)
    this.$store.dispatch(this.$constant.admin.ACTION_ADMIN_COLOR_INIT)
    this.$store.dispatch(this.$constant.admin.ACTION_ADMIN_PRODUCT_INIT)
    this.$store.dispatch(this.$constant.admin.ACTION_ADMIN_COUPON_INIT)
    this.$store.dispatch(this.$constant.admin.ACTION_ADMIN_INVOICE_INIT)
    this.$store.dispatch(this.$constant.admin.ACTION_ADMIN_REVIEW_INIT)
    this.$store.dispatch(this.$constant.admin.ACTION_ADMIN_SUPPLIER_INIT)
    this.$store.dispatch(this.$constant.admin.ACTION_ADMIN_INFOWEB_INIT)
  },
}
</script>

<style scoped>
.row.content {
  height: 550px;
}

.sidenav {
  background-color: #f1f1f1;
  height: 100%;
}

@media screen and (max-width: 767px) {
  .row.content {
    height: auto;
  }
}
</style>
