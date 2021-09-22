import Locales from '~/locales/Locales'
export default (ctx, inject) => {
  inject('local', Locales())
}
