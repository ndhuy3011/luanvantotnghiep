export default {
  MUTATION_ADMIN_SUPPLIER_INIT(state, payload) {
    console.log(payload.data)
    state.contents = payload.data
  },
  MUTATION_ADMIN_SUPPLIER_ADD_CONTENT(state, payload) {
    state.contents.unshift(payload)
  },
  MUTATION_ADMIN_SUPPLIER_UPDATE_CONTENT(state, payload) {
    const contentIndex = state.contents.findIndex(
      (content) => content.id === parseInt(payload.id)
    )
    if (contentIndex === -1) {
      return
    }
    state.contents.splice(contentIndex, 1, payload)
  },
  MUTATION_ADMIN_SUPPLIER_DELETE_CONTENT(state, payload) {
    const contentIndex = state.contents.findIndex(
      (content) => parseInt(content.id) === parseInt(payload.id)
    )
    state.contents.splice(contentIndex, 1)
  },
}
