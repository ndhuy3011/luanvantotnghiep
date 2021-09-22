export default {
  async addSize({ dispatch }, item) {
    this.$toast.global.loading()
    try {
      const res = await this.$repositories.sizeAdmin.create(item)
      const { status } = res
      if (status === 201) {
        this.$toast.global.success()
      } else {
        this.$toast.global.error()
      }
    } catch (e) {
      this.$toast.global.error()
    }
  },
  async updateAmount({ dispatch }, item) {
    this.$toast.global.loading()
    let res = null
    try {
      res = await this.$repositories.couponAdmin.create(item)
      const { status } = res
      if (status === 200) {
        this.$toast.global.success()
      }
    } catch (e) {
      this.$toast.global.error()
    }
  },
  async deleteSize({ commit }, item) {
    this.$toast.global.loading()
    let res = null
    try {
      res = await this.$repositories.sizeAdmin.delete(item)
      const { status } = res
      if (status === 200) {
        this.$toast.global.success()
      }
    } catch (e) {
      this.$toast.global.error()
    }
  },
}
