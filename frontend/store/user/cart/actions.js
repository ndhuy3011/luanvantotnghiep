import constants from './constants'

export default {
  async addCartUser({ commit }, cartItem) {
    const newCartitem = {
      productID: cartItem.idProduct,
      quantity: cartItem.quantity,
      color: cartItem.color,
      sizeId: cartItem.sizeId,
    }
    try {
      const res = await this.$repositories.cartRepostory.addcart(newCartitem)
      const { status } = res
      if (status === 201) {
        commit(constants.MUTATIONS_CART_ADDTOCART_USER, cartItem)
        this.$toast.global.cart()
      }
    } catch (e) {
      return false
    }
  },

  async showcartUser({ commit }) {
    const res = await this.$repositories.cartRepostory.showcart()
    const { status, data } = res
    if (status === 200) {
      commit(constants.MUTATIONS_SHOW_CART_USER, data)
    }
  },
  async updateCartUser({ commit }, cart) {
    await this.$repositories.cartRepostory.updated(cart.idCart, cart)
  },
  async deleteCartUser({ commit }, item) {
    try {
      const res = await this.$repositories.cartRepostory.delete(item.idCart)
      const { status } = res
      if (status === 200) {
        commit(constants.MUTATIONS_REMOVE_PRODUCTCART_USER, item)
      }
    } catch (e) {}
  },
  removeCartUser: (context) => {
    context.commit(constants.MUTATIONS_REMOVE_CART_USER)
  },
  plusUser({ commit }, cartItems) {
    commit(constants.MUTATIONS_PLUS_ITEMCART_USER, cartItems)
  },
  minusUser: (context, cartItems) => {
    context.commit(constants.MUTATIONS_MINUS_ITEMCART_USER, cartItems)
  },

  addCart: (context, cartItem) => {
    context.commit(constants.MUTATIONS_CART_ADDTOCART, cartItem)
  },

  removeProductCart: (context, indexRemove) => {
    context.commit(constants.MUTATIONS_REMOVE_PRODUCTCART, indexRemove)
  },
  render: (context) => {
    context.commit(constants.MUTATIONS_LOAD_CART)
  },
  plus: (context, i) => {
    context.commit(constants.MUTATIONS_PLUS_ITEMCART, i)
  },
  minus: (context, i) => {
    context.commit(constants.MUTATIONS_MINUS_ITEMCART, i)
  },
}
