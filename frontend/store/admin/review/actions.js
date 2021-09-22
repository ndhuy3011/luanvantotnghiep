export default {
  async init({ commit }) {
    const comment = await this.$repositories.reviewAdmin.all()
    commit(this.$constant.admin.MUTATION_ADMIN_REVIEW_INIT, comment)
  },
  async submit({ commit }, payload) {
    try {
      const comment = await this.$repositories.reviewAdmin.create(payload)
      const { status } = comment
      if (status === 200) {
        this.$toast.global.success()
        return true
      }
    } catch (e) {
      this.$toast.global.error()
      return true
    }
  },
  async delete({ commit }, payload) {
    try {
      const comment = await this.$repositories.reviewAdmin.delete(payload)
      const { status } = comment
      if (status === 200) {
        this.$toast.global.success()
        return true
      }
    } catch (e) {
      this.$toast.global.error()
      return true
    }
  },
}
