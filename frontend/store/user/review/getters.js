export default {
  totalRate(state) {
    let total = 0
    let dem = 0
    state.comment.forEach((item) => {
      total += item.rate
      dem++
    })
    return total / dem
  },
  totalComment(state) {
    return state.comment.length
  },
}
