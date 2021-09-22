export default {
  MUTATIONS_CHECKOUT_CHECKOUT(state, cart) {
    state.checkout = cart
  },
  MUTATIONS_REMOVE_PRODUCT_CHECKOUT(state, indexRemove) {
    state.checkout = state.checkout.filter((item, index) => {
      return index !== indexRemove
    })
  },
}
