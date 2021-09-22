<template>
  <v-container>
    <v-row align="center">
      <v-col cols="12">
        <v-btn @click="toast">Test</v-btn>
      </v-col>
    </v-row>
    <hr />
    {{ value }}
  </v-container>
</template>

<script>
export default {
  data: () => ({
    value: undefined,

    items: [
      {
        id: 12,
        name: 'Áo',
        sort: 1,
        children: [
          {
            id: 35,
            name: 'Áo thun',
            sort: 1,
            children: [
              {
                id: 36,
                name: 'Áo thun ngắn',
                sort: 2,
                children: [],
                url: 'ao-thun-ngan',
              },
              {
                id: 37,
                name: 'Áo thun kaki',
                sort: 3,
                children: [],
                url: 'ao-thun-kaki',
              },
            ],
            url: 'ao-thun',
          },
        ],
        url: 'ao',
      },
      {
        id: 17,
        name: 'Quần',
        sort: 1,
        children: [
          {
            id: 16,
            name: 'Quần dài',
            sort: 3,
            children: [],
            url: 'quan-dai',
          },
          {
            id: 26,
            name: 'Quần Short',
            sort: 2,
            children: [
              {
                id: 28,
                name: 'Quần Short thun',
                sort: 2,
                children: [],
                url: 'quan-short-thun',
              },
              {
                id: 30,
                name: 'Quần Short kaki',
                sort: 2,
                children: [],
                url: 'quan-short-kaki',
              },
            ],
            url: 'quan-short',
          },
        ],
        url: 'quan',
      },
    ],
  }),
  methods: {
    select(item) {
      this.value = item
    },

    toast() {
      this.$toast.global.cart()
    },
  },
}
</script>

<style></style>
