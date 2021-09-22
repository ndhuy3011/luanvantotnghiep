export default {
  async init({ commit }) {
    const banner = await this.$repositories.bannerAdmin.get()
    const payload = {
      banner: banner.data,
    }
    commit(this.$constant.admin.MUTATION_ADMIN_BANNER_INIT, payload)
  },
  async addBanner({ commit }, banner) {
    this.$toast.global.loading()
    try {
      const res = await this.$repositories.bannerAdmin.create(banner)
      const { status } = res
      if (status === 200) {
        this.$toast.global.success()
        return true
      }
    } catch (e) {
      return false
    }
  },
  async deleteBanner({ commit }, item) {
    this.$toast.global.loading()
    try {
      const res = await this.$repositories.bannerAdmin.delete(item.id)
      const { status } = res
      if (status === 200) {
        this.$toast.global.success()
        commit(this.$constant.admin.MUTATION_ADMIN_BANNER_DELETE, item)
        return true
      }
    } catch (e) {
      return false
    }
  },
}
