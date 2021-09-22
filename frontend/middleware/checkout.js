export default function ({ store, redirect }) {
  const checkout = store.state.user.checkout.checkout
  if (checkout.length <= 0) {
    return redirect('cart')
  }
}
