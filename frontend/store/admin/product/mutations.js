export default {
  MUTATION_ADMIN_PRODUCT_INIT(state, product) {
    state.product = []
    state.product = product
  },
  MUTATION_ADMIN_PRODUCT_ADD(state, product) {
    state.product.push(product)
  },
  MUTATION_ADMIN_PRODUCT_UPDATE(state, products) {
    const contentIndex = state.product.findIndex(
      (content) => parseInt(content.id) === parseInt(products.id)
    )
    if (contentIndex === -1) {
      return
    }
    state.product.splice(contentIndex, 1, products)
  },
  MUTATION_ADMIN_PRODUCT_DELETE(state, product) {
    const contentIndex = state.product.findIndex(
      (content) => content.id === parseInt(product.id)
    )
    state.product.splice(contentIndex, 1)
  },
}
