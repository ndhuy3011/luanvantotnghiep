export default {
  MUTATIONS_CART_ADDTOCART_USER(state, cartItem) {
    const cart = state.cart.find(
      (content) => content.sizeId === cartItem.sizeId
    )
    if (!cart) {
      state.cart.push(cartItem)
    } else {
      cart.quantity += cartItem.quantity
    }
  },
  MUTATIONS_SHOW_CART_USER(state, payload) {
    state.cart = payload
  },
  MUTATIONS_REMOVE_CART_USER(state) {
    state.cart = []
  },
  MUTATIONS_REMOVE_PRODUCTCART_USER(state, payload) {
    const contentIndex = state.cart.findIndex(
      (items) => parseInt(items.idCart) === parseInt(payload.idCart)
    )
    state.cart = state.cart.filter((item, index) => {
      return index !== contentIndex
    })
  },
  MUTATIONS_PLUS_ITEMCART_USER(state, payload) {
    const contentIndex = state.cart.findIndex(
      (items) => parseInt(items.idCart) === parseInt(payload.idCart)
    )
    state.cart.forEach((item, index) => {
      if (index === contentIndex) {
        item.quantity++
      }
    })
  },
  MUTATIONS_MINUS_ITEMCART_USER(state, payload) {
    const contentIndex = state.cart.findIndex(
      (items) => parseInt(items.idCart) === parseInt(payload.idCart)
    )
    state.cart.forEach((item, index) => {
      if (index === contentIndex) {
        if (item.quantity > 1) {
          item.quantity--
        }
      }
    })
  },

  MUTATIONS_CART_ADDTOCART(state, cartItem) {
    const cart = state.cart.find(
      (content) => content.sizeId === cartItem.sizeId
    )
    this.$toast.global.cart()
    if (!cart) {
      state.cart.push(cartItem)
    } else {
      cart.quantity += cartItem.quantity
    }
    localStorage.setItem('cart', JSON.stringify(state.cart))
  },
  MUTATIONS_REMOVE_PRODUCTCART(state, indexRemove) {
    state.cart = state.cart.filter((item, index) => {
      return index !== indexRemove
    })
    localStorage.setItem('cart', JSON.stringify(state.cart))
  },
  MUTATIONS_LOAD_CART(state) {
    const cart = localStorage.getItem('cart')
    if (cart != null) {
      state.cart = JSON.parse(cart)
    }
  },
  MUTATIONS_PLUS_ITEMCART(state, i) {
    state.cart.forEach((item, index) => {
      if (index === i) {
        item.quantity++
        localStorage.setItem('cart', JSON.stringify(state.cart))
      }
    })
  },
  MUTATIONS_MINUS_ITEMCART(state, i) {
    state.cart.forEach((item, index) => {
      if (index === i) {
        if (item.quantity > 1) {
          item.quantity--
          localStorage.setItem('cart', JSON.stringify(state.cart))
        }
      }
    })
  },
}
