export default {
  INIT(state, payload) {
    state.contents = payload.category
    state.select = payload.select
  },
  MUTATION_ADMIN_CATEGORY_SET_CONTENT(state, payload) {
    state.contents = payload
  },
  MUTATION_ADMIN_CATEGORY_ADD_CONTENT(state, payload) {
    state.contents.unshift(payload)
  },
  MUTATION_ADMIN_CATEGORY_UPDATE_CONTENT(state, payload) {
    const contentIndex = state.contents.findIndex(
      (content) => content.id === parseInt(payload.id)
    )
    if (contentIndex === -1) {
      return
    }
    state.contents.splice(contentIndex, 1, payload)
  },
  MUTATION_ADMIN_CATEGORY_DELETE_CONTENT(state, payload) {
    const contentIndex = state.contents.findIndex(
      (content) => parseInt(content.id) === parseInt(payload.id)
    )
    state.contents.splice(contentIndex, 1)
  },
}
