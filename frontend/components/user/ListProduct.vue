<template>
  <div>
    <v-tabs color="accent-4" centered>
      <v-tab
        v-for="(item, index) in categorys.children"
        id="menu_title"
        :key="index"
        >{{ item.name }}</v-tab
      >

      <v-tab-item
        v-for="(category, indexs) in categorys.children"
        :key="indexs"
      >
        <v-container fluid>
          <v-row>
            <v-col
              v-for="(item, index) in category.product"
              :key="index"
              cols="6"
              md="4"
              lg="4"
              sm="6"
              xs="6"
            >
              <div class="product-grid">
                <div class="product-image">
                  <img
                    class="pic-1"
                    :src="item.image"
                    @click="productdetail(item)"
                  />
                  <ul class="social">
                    <li>
                      <a data-tip="Xem chi tiết" @click="productdetail(item)"
                        ><b-icon icon="search"></b-icon
                      ></a>
                    </li>
                    <li>
                      <router-link to="#" data-tip="Yêu thích"
                        ><b-icon icon="heart"></b-icon
                      ></router-link>
                    </li>
                  </ul>
                  <p v-for="(value, i) in item.category" :key="i">
                    <span v-if="value == 'Sale'" class="product-new-label"
                      >Sale</span
                    >
                  </p>
                  <span class="product-discount-label"></span>
                </div>
                <ul class="rating">
                  <li class="fa fa-star"></li>
                  <li class="fa fa-star"></li>
                  <li class="fa fa-star"></li>
                  <li class="fa fa-star"></li>
                  <li class="fa fa-star disable"></li>
                </ul>
                <div class="product-content">
                  <h6 class="title">
                    <a @click="productdetail(item)">{{ item.name }}</a>
                  </h6>
                  <div class="price">
                    {{ item.price.toLocaleString() }}
                    {{ $local.vn.currency }}
                  </div>
                  <a class="add-to-cart">+ {{ $local.vn.add_cart }}</a>
                </div>
              </div>
            </v-col>
          </v-row>
        </v-container>
      </v-tab-item>
    </v-tabs>
  </div>
</template>

<script>
export default {
  props: ['categorys'],
  methods: {
    productdetail(item) {
      this.$router.push({
        name: 'productdetail-id',
        params: {
          id: item.id,
        },
      })
    },
  },
}
</script>

<style lang="scss" scoped>
#menu_title {
  font-weight: bold;
  font-size: 16px;
}
.product-grid {
  text-align: center;
  padding: 0 0 72px;
  border: 1px solid rgba(0, 0, 0, 0.1);
  overflow: hidden;
  position: relative;
  z-index: 1;
  box-shadow: 0 3px 20px rgba(0, 0, 0, 0.08);
  .product-image {
    position: relative;
    transition: all 0.3s ease 0s;
    a {
      display: block;
    }
    img {
      width: 100%;
      height: auto;
    }
  }

  .pic-1 {
    opacity: 1;
    transition: all 0.3s ease-out 0s;
  }
  .social {
    width: 150px;
    padding: 0;
    margin: 0;
    list-style: none;
    opacity: 0;
    transform: translateY(-50%) translateX(-50%);
    position: absolute;
    top: 60%;
    left: 50%;
    z-index: 1;
    transition: all 0.3s ease 0s;
    li {
      display: inline-block;
      a {
        color: #fff;
        background-color: #333;
        font-size: 20px;
        line-height: 40px;
        text-align: center;
        height: 40px;
        width: 40px;
        margin: 0 2px;
        display: block;
        position: relative;
        transition: all 0.3s ease-in-out;
      }
      a:hover {
        color: #fff;
        background-color: #ff0000;
      }
      a:after,
      a:before {
        content: attr(data-tip);
        color: #fff;
        background-color: #000;
        font-size: 12px;
        letter-spacing: 1px;
        line-height: 20px;
        padding: 1px 5px;
        white-space: nowrap;
        opacity: 0;
        transform: translateX(-50%);
        position: absolute;
        left: 50%;
        top: -30px;
      }
    }
  }

  .product-discount-label,
  .product-new-label {
    color: #fff;
    background-color: red;
    font-size: 12px;
    text-transform: uppercase;
    padding: 2px 7px;
    display: block;
    position: absolute;
    top: 10px;
    left: 0;
  }
}
.product-grid:hover .pic-1 {
  opacity: 1;
}
.product-grid:hover .social {
  opacity: 1;
  top: 50%;
}

.product-grid .social li a:after {
  content: '';
  height: 15px;
  width: 15px;
  border-radius: 0;
  transform: translateX(-50%) rotate(45deg);
  top: -20px;
  z-index: -1;
}
.product-grid .social li a:hover:after,
.product-grid .social li a:hover:before {
  opacity: 1;
}

.product-grid .product-discount-label {
  background-color: #333;
  left: auto;
  right: 0;
}
.product-grid .rating {
  color: #ffd200;
  font-size: 12px;
  padding: 12px 0 0;
  margin: 0;
  list-style: none;
  position: relative;
  z-index: -1;
}
.product-grid .rating li.disable {
  color: rgba(0, 0, 0, 0.2);
}
.product-grid .product-content {
  background-color: #fff;
  text-align: center;
  padding: 12px 0;
  margin: 0 auto;
  position: absolute;
  left: 0;
  right: 0;
  bottom: -30px;
  z-index: 1;
  transition: all 0.3s;
}
.product-grid:hover .product-content {
  bottom: 0;
}
.product-grid .title {
  font-size: 13px;
  font-weight: 400;
  letter-spacing: 0.5px;
  text-transform: capitalize;
  margin: 0 0 10px;
  transition: all 0.3s ease 0s;
}
.product-grid .title a {
  color: #828282;
}
.product-grid .title a:hover,
.product-grid:hover .title a {
  color: #ff0000;
}
.product-grid .price {
  color: #333;
  font-size: 17px;
  font-weight: 700;
  letter-spacing: 0.6px;
  margin-bottom: 8px;
  text-align: center;
  transition: all 0.3s;
}
.product-grid .price span {
  color: #999;
  font-size: 13px;
  font-weight: 400;
  text-decoration: line-through;
  margin-left: 3px;
  display: inline-block;
}
.product-grid .add-to-cart {
  color: #000;
  font-size: 16px;
  font-weight: 600;
}
@media only screen and (max-width: 990px) {
  .product-grid {
    margin-bottom: 30px;
  }
}
</style>
