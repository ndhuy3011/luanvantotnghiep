import Constants from '~/constants/Constants.js'
export default (ctx, inject) => {
  inject('constant', Constants())
}
