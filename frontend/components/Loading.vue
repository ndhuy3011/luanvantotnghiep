<template>
  <v-progress-linear
    v-if="loading"
    color="orange"
    indeterminate
    reverse
  ></v-progress-linear>
</template>

<script>
export default {
  data: () => ({
    loading: false,
  }),
  methods: {
    start() {
      this.loading = true
    },
    finish() {
      this.loading = false
    },
  },
}
</script>

<style scoped></style>
