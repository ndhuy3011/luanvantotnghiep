export default {
  MUTATION_ADMIN_BANNER_INIT(state, payload) {
    state.banner = payload.banner
  },
  MUTATION_ADMIN_BANNER_DELETE(state, payload) {
    const contentIndex = state.banner.findIndex(
      (banner) => parseInt(banner.id) === parseInt(payload.id)
    )
    state.banner.splice(contentIndex, 1)
  },
}
