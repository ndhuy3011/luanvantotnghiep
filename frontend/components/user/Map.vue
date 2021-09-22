<template>
  <iframe
    :src="map"
    style="border: 0"
    allowfullscreen=""
    loading="lazy"
    width="100%"
    height="600"
  ></iframe>
</template>

<script>
export default {
  props: {
    map: {
      type: String,
      default: '',
    },
  },
}
</script>

<style lang="scss" scoped></style>
