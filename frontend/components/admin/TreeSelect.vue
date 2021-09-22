<template>
  <div>
    <Treeselect
      v-model="value"
      :options="select"
      :value="value"
      :normalizer="normalizer"
      :disable-branch-nodes="false"
    >
    </Treeselect>

    {{ value }}
  </div>
</template>

<script>
import Treeselect from '@riophae/vue-treeselect'
import '@riophae/vue-treeselect/dist/vue-treeselect.css'

export default {
  components: { Treeselect },
  props: {
    select: {
      type: Array,
      default: null,
    },
  },
  data: () => ({
    normalizer(node) {
      return {
        id: node.id,
        label: node.name,
        children: node.children,
      }
    },
    value: undefined,
  }),
  methods: {
    selects() {
      console.log(this.value)
      this.$emit('input', this.value)
    },
  },
}
</script>

<style></style>
