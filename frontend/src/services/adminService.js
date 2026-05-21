import http from './http'

const adminService = {
  async getAllUsers() {
    const { data } = await http.get('/admin/users')
    return data
  }
}

export default adminService
