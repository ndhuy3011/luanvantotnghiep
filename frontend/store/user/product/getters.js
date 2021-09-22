export default {
  getFindProduct: (state) => (payload) => {
    return state.list_products.find((item) => item.id === parseInt(payload.id))
  },
}
