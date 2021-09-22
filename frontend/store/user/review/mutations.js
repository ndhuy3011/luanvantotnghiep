export default {
  MUTATIONS_REVIEW_SHOW(state, payload) {
    state.usercomment = payload
  },
  MUTATIONS_REVIEW_GETALL(state, payload) {
    if (payload.length === 0) {
      state.comment = []
    } else {
      state.comment = payload
    }
  },
}
