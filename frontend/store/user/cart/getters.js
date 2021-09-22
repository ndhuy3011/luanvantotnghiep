export default {
  total(state) {
    let total = 0
    state.cart.forEach((item) => {
      total += item.price * item.quantity
    })
    return total
  },
  amount(state) {
    let amount = 0
    state.cart.forEach((item) => {
      amount += item.quantity
    })
    return amount
  },
  getData(state) {
    return state.cart
  },
}
