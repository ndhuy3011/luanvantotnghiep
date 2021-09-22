import ConstantAdmin from './admin/index.js'
import ConstantUser from './user/index.js'
export default () => ({
  admin: ConstantAdmin(),
  user: ConstantUser(),
})
