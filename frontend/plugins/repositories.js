import createRepository from '~/repositories/Repostory'

export default (ctx, inject) => {
  inject('repositories', createRepository(ctx.$axios))
}
