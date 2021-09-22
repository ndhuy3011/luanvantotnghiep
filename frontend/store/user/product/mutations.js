export default {
  MUTATIONS_PRODUCT_GETALL(state, payload) {
    state.list_products = payload.listproduct
    payload.hot.forEach((el) => {
      el.hot = true
    })
    state.hot = payload.hot
    state.list_products.forEach((elm) => {
      if (elm.fresh === true) {
        state.fresh.push(elm)
      }
      elm.category.forEach((el) => {
        if (el === 'Nam') {
          state.men.push(elm)
        } else if (el === 'Nữ') {
          state.women.push(elm)
        } else if (el === 'Sale') {
          state.sale.push(elm)
        }
      })
    })
  },
  MUTATIONS_PC_GET(state, payload) {
    state.select = payload.product
    state.category = payload.category
  },
}
