export default {
  async addDetailsproduct({ dispatch }, item) {
    this.$toast.global.loading()
    const res = await this.$repositories.detailsproductAdmin.create(item)
    const { status } = res
    if (status === 201) {
      this.$toast.global.success()
      dispatch(this.$constant.admin.ACTION_ADMIN_PRODUCT_INIT)
    } else {
      // Handle error here
      this.$toast.global.error()
    }
  },
}
