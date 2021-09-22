export default {
  total(state) {
    let total = 0
    state.checkout.forEach((item) => {
      total += item.price * item.quantity
    })
    return total
  },
}
