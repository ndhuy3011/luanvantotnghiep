import constants from './constants'

export default {
  getSearch: (context, key) => {
    context.commit(constants.MUTATIONS_SEARCH, key)
  },
}
