import RULE from '~/rules/Rules.js'
export default (ctx, inject) => {
  inject('rule', RULE())
}
