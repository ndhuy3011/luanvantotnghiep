// admin
import ProductAdminRepository from '~/repositories/admin/ProductRepostory'
import ColorAdminRepository from '~/repositories/admin/ColorRepostory'
import CategoryAdminRepository from '~/repositories/admin/CategoryRepostory'
import SizeAdminRepository from '~/repositories/admin/SizeRepostory'
import DetailsProductAdminRepository from '~/repositories/admin/DetailsProductRepostory'
import CouponAdminRepository from '~/repositories/admin/CouponRepostory'
import AuthAdminRepository from '~/repositories/admin/AuthRepostory'
import InvoiceAdminRepository from '~/repositories/admin/InvoiceRepostory'
import ReviewAdminRepository from '~/repositories/admin/ReviewRepostory'
import ReportAdminRepository from '~/repositories/admin/ReportRepostory'
import SupplierAdminRepository from '~/repositories/admin/SupplierRepostory'
import InfoWebAdminRepository from '~/repositories/admin/InfowebRepostory'
import BannerAdminRepository from '~/repositories/admin/BannerRepostory'
// user
import AccounRepostory from '~/repositories/user/AccounRepostory'
import CategoryReponstory from '~/repositories/user/CategoryRepostory'
import ProductReponstory from '~/repositories/user/ProductRepostory'
import CartRepostory from '~/repositories/user/CartRepostory'
import ReviewReponstory from '~/repositories/user/ReviewRepostory'
import UserInvoiceRepostory from '~/repositories/user/InvoiceRepostory'
export default ($axios) => ({
  // admin
  productAdmin: ProductAdminRepository($axios),
  colorAdmin: ColorAdminRepository($axios),
  categoryAdmin: CategoryAdminRepository($axios),
  sizeAdmin: SizeAdminRepository($axios),
  detailsproductAdmin: DetailsProductAdminRepository($axios),
  couponAdmin: CouponAdminRepository($axios),
  authAdmin: AuthAdminRepository($axios),
  invoiceAdmin: InvoiceAdminRepository($axios),
  reviewAdmin: ReviewAdminRepository($axios),
  reportAdmin: ReportAdminRepository($axios),
  supplierAdmin: SupplierAdminRepository($axios),
  infowebAdmin: InfoWebAdminRepository($axios),
  bannerAdmin: BannerAdminRepository($axios),
  // user
  account: AccounRepostory($axios),
  categoryUser: CategoryReponstory($axios),
  productUser: ProductReponstory($axios),
  cartRepostory: CartRepostory($axios),
  reviewRepostory: ReviewReponstory($axios),
  invoiceUser: UserInvoiceRepostory($axios),
})
