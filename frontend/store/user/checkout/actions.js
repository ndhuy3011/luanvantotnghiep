import constants from './constants'

export default {
  setCheckout: (context, cartItem) => {
    context.commit(constants.MUTATIONS_CHECKOUT_CHECKOUT, cartItem)
  },

  removeProduct: (context, indexRemove) => {
    context.commit(constants.MUTATIONS_REMOVE_PRODUCT_CHECKOUT, indexRemove)
  },
}
