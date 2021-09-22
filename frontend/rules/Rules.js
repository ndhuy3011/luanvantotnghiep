export default () => ({
  ADMIN_CATEGORY_NAME: [
    (v) => !!v || 'Bắt buộc nhập tên',
    (v) => v.length <= 15 || 'Bạn không được nhập quá 15 ký tự',
  ],
  ADMIN_CATEGORY_SORT: [
    (v) => !!v || 'Bắt buộc nhập độ ưu tiên',
    (v) => typeof v === 'string' || 'Bạn phải nhập số',
    (v) => v > 0 || 'Bạn phải nhập độ ưu tiên lớn hơn 0',
  ],
  EMAIL_LOGIN: [
    (v) => !!v || 'Vui lòng bạn nhập Email',
    (v) => /.+@.+\..+/.test(v) || 'Email của bạn không phù hợp',
  ],
  PASSWORD_LOGIN: [
    (v) => !!v || 'Vui lòng bạn nhập mật khẩu',
    (v) => v.length >= 6 || 'Mật khẩu hơn 6 ký tự',
  ],
  ADMIN_COLOR_NAME: [
    (v) => !!v || 'Bắt buộc nhập tên',
    (v) => v.length <= 15 || 'Bạn không được nhập quá 15 ký tự',
  ],
  ADMIN_COLOR_CODE: [(v) => !!v || 'Bắt buộc nhập tên'],
  ADMIN_PRODUCT_NAME: [
    (v) => !!v || 'Bắt buộc nhập tên',
    (v) => v.length <= 30 || 'Bạn không được nhập quá 30 ký tự',
  ],
  ADMIN_PRODUCT_PRICE: [(v) => !!v || 'Bắt buộc nhập số tiền'],
  ADMIN_PRODUCT_IMAGE: [(v) => !!v || 'Bắt buộc thêm hình ảnh sản phẩm'],
  ADMIN_PRODUCT_SELECT: [(v) => !!v || 'Bạn phải chọn danh mục'],
  ADMIN_PRODUCT_SHORT_DESCRIPTION: [(v) => !!v || 'Bắt buộc nhập mô tả ngắn'],
  ADMIN_PRODUCT_NAME_SIZE: [(v) => !!v || 'Bắt bược nhập tên sai'],
  ADMIN_PRODUCT_COLOR_IMAGE: [(v) => !!v || 'Bạn phải chọn màu'],
  ADMIN_PRODUCT_COLOR_NAME: [(v) => !!v || 'Bắt buộc nhập tên màu'],

  ADMIN_PRODUCT_SUPPLIER_NAME: [(v) => !!v || 'Bắt buộc nhập nhà cung cấp'],

  ADMIN_MAP: [(v) => !!v || 'Bắt buộc nhập'],
  ADMIN_NUMBERPHONE: [(v) => !!v || 'Bắt buộc nhập số điện thoại'],
})
