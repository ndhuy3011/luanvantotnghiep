import constants from './constants'

export default {
  async postCommit({ commit }, payload) {
    try {
      const res = await this.$repositories.reviewRepostory.post(payload)
      const { status, data } = res
      if (status === 201) {
        commit(constants.MUTATIONS_REVIEW_SHOW, data)
      }
    } catch (error) {
      alert(error)
    }
  },
  async getCommemt({ commit }, payload) {
    try {
      const res = await this.$repositories.reviewRepostory.show(payload)
      const { status, data } = res
      if (status === 200) {
        commit(constants.MUTATIONS_REVIEW_GETALL, data)
      }
    } catch (error) {}
  },
}
