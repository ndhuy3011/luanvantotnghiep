export default {
  MUTATION_ADMIN_COUPON_INIT(state, payload) {
    state.coupon = payload
  },
  MUTATION_ADMIN_COUPON_UPDATE(state, payload) {
    const index = state.coupon.findIndex(
      (content) => content.id === parseInt(payload.id)
    )
    if (index === -1) {
      return
    }
    state.coupon.splice(index, 1, payload)
  },
}
