<template>
  <v-row>
    <v-col
      v-for="(item, index) in img"
      :key="index"
      cols="12"
      sm="4"
      md="4"
      xs="6"
    >
      <div id="image">
        <img
          :src="require(`~/assets/category/${item.src}`)"
          alt="single banner"
        />
        <div class="center">
          <a :href="item.href">{{ item.mes }}</a>
        </div>
      </div>
    </v-col>
  </v-row>
</template>

<script>
export default {
  name: 'CategoryMenu',
  data() {
    return {
      img: [
        {
          src: 'man.jpg',
          mes: 'THỜI TRANG NAM',
          href: '/shop',
        },
        {
          src: 'women.jpg',
          mes: 'THỜI TRANG NỮ',
          href: '/shop',
        },
        {
          src: 'kid.jpg',
          mes: 'THỜI TRANG TRẺ EM',
          href: '/shop',
        },
      ],
    }
  },
}
</script>

<style lang="scss" scoped>
#image {
  img {
    opacity: 1;
    display: block;
    width: 100%;
    height: auto;
    transition: 0.5s ease;
    backface-visibility: hidden;
  }
}
#image:hover {
  z-index: 1;
  transform: scale(1.15);
  transition: 0.75s;
}
.center {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  font-size: 15px;
  background-color: rgb(0, 0, 0);
  padding: 10px;
  border-radius: 20px;
  a {
    text-decoration: none;
    color: rgb(255, 255, 255);
  }
}
@media screen and (max-width: 880px) {
  .center {
    font-size: 8px;
    padding: 6px;
  }
}
</style>
