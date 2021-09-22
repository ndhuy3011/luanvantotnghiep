export default function ({ $auth, redirect }) {
  // If the user is not authenticated
  const user = $auth.user
  if (!user || user.role.length <= 0) {
    return redirect('/admin/login')
  }
}
