export default {
  MUTATION_ADMIN_COLOR_INIT(state, color) {
    state.color = color
  },
  MUTATION_ADMIN_COLOR_ADD(state, color) {
    state.color.push(color)
  },
  MUTATION_ADMIN_COLOR_UPDATE(state, color) {
    const contentIndex = state.color.findIndex(
      (content) => parseInt(content.id) === parseInt(color.id)
    )
    if (contentIndex === -1) {
      return
    }
    state.color.splice(contentIndex, 1, color)
  },
  MUTATION_ADMIN_COLOR_DELETE(state, color) {
    const contentIndex = state.color.findIndex(
      (content) => content.id === parseInt(color.id)
    )
    state.color.splice(contentIndex, 1)
  },
}
