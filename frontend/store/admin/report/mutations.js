export default {
  MUTATION_ADMIN_REPORT_INIT(state, payload) {
    state.invoiceAmout = payload
  },
  MUTATION_ADMIN_REPORT_CATEGORY_INIT(state, payload) {
    payload.forEach((el) => {
      const o = Math.round
      const r = Math.random
      const s = 255
      state.colorCategory.push(
        'rgb(' + o(r() * s) + ', ' + o(r() * s) + ', ' + o(r() * s) + ')'
      )
      state.labelCategory.push(el.name)
      state.valueCategory.push(el.amount)
    })
  },
  MUTATION_ADMIN_REPORT_SUM_INIT(state, payload) {
    state.sumDay = payload.slice(-1)[0]
    payload.forEach((el) => {
      const o = Math.round
      const r = Math.random
      const s = 255
      state.colorSumWeek.push(
        'rgb(' + o(r() * s) + ', ' + o(r() * s) + ', ' + o(r() * s) + ')'
      )
      state.labelSumWeek.push(el.dateStart)
      state.valueSumWeek.push(el.sum)
    })
  },
}
