export default {
  async init({ commit }) {
    const coupon = await this.$repositories.couponAdmin.all()
    commit(this.$constant.admin.MUTATION_ADMIN_COUPON_INIT, coupon.data)
  },
  async delete({ commit }, payload) {
    try {
      this.$toast.global.loading()
      const res = await this.$repositories.couponAdmin.update(
        payload.id,
        payload
      )
      const { status, data } = res

      if (status === 200) {
        this.$toast.global.success()
        commit(this.$constant.admin.MUTATION_ADMIN_COUPON_UPDATE, data)
        return true
      }
    } catch (e) {
      return false
    }
  },
}
