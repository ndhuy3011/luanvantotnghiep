// user
import UserMsg from '~/locales/user/msg'
// admin
import AdminMsg from '~/locales/admin/msg'
import AdminMenu from '~/locales/admin/menu'
import AdminHeaderTable from '~/locales/admin/headerTable'
import ADMINCATEGORY from '~/locales/admin/category'
import AdminPorudct from '~/locales/admin/product'
import ADMINGENERAL from '~/locales/admin/general'
import ADMINCOLOR from '~/locales/admin/color'
import ADMINREVIEW from '~/locales/admin/review'
import ADMINSUPPLIER from '~/locales/admin/supplier'
import ADMININFOWWEB from '~/locales/admin/infoweb'
import ADMINBANNER from '~/locales/admin/banner'
export default () => ({
  vn: UserMsg(),

  vn_admin: AdminMsg(),
  vn_admin_menu: AdminMenu(),
  vn_admin_headerTable: AdminHeaderTable(),
  vn_admin_product: AdminPorudct(),
  vn_admin_category: ADMINCATEGORY(),
  vn_admin_general: ADMINGENERAL(),
  vn_admin_color: ADMINCOLOR(),
  vn_admin_review: ADMINREVIEW(),
  vn_admin_supplier: ADMINSUPPLIER(),
  vn_admin_infoweb: ADMININFOWWEB(),
  vn_admin_banner: ADMINBANNER(),
})
