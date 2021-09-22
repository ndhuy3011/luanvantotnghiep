export default {
  async init({ commit }) {
    const infoweb = await this.$repositories.infowebAdmin.get()
    const payload = {
      infoweb: infoweb.data,
    }
    commit(this.$constant.admin.MUTATION_ADMIN_INFOWEB_INIT, payload)
  },
  async addInfo({ commit }, info) {
    this.$toast.global.loading()
    try {
      const res = await this.$repositories.infowebAdmin.create(info)
      const { status } = res
      if (status === 200) {
        this.$toast.global.success()
        return true
      }
    } catch (e) {
      return false
    }
  },
}
