export default {
  async login({ commit }, item) {
    try {
      const res = await this.$repositories.authAdmin.login(item)
      const { status, data } = res
      if (status === 200) {
        this.$auth.strategy.token.set(data.token)
        await this.$auth.fetchUser()
        return true
      }
    } catch (e) {
      if (e.response.status) {
        if (parseInt(e.response.status) === 404) {
          return 404
        }
      }

      return false
    }
  },
  async user({ commit }) {
    try {
      const res = await this.$repositories.authAdmin.user()
      const { status, data } = res
      if (status === 200) {
        this.$auth.setUser(data)
        return true
      }
    } catch (e) {
      return false
    }
  },
}
