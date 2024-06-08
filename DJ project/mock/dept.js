const Mock = require('mockjs')

const List = []
const count = 100
for (let i = 0; i < count; i++) {
  List.push(Mock.mock({
    id: '@increment',
    deptName: '@ctitle( 8, 10 )'
  }))
}
module.exports = [
  {
    url: '/dept/list',
    type: 'get',
    response: config => {
      const { deptName, page = 1, limit = 20, sort } = config.query

      let mockList = List.filter(item => {
        if (deptName && item.deptName !== deptName) return false
        return true
      })

      if (sort === '-id') {
        mockList = mockList.reverse()
      }

      const pageList = mockList.filter((item, index) => index < limit * page && index >= limit * (page - 1))

      return {
        code: 20000,
        data: {
          total: mockList.length,
          items: pageList
        }
      }
    }
  },

  {
    url: '/dept/detail',
    type: 'get',
    response: config => {
      const { id } = config.query
      for (const dept of List) {
        if (dept.id === +id) {
          return {
            code: 20000,
            data: dept
          }
        }
      }
    }
  },

  {
    url: '/dept/create',
    type: 'post',
    response: _ => {
      return {
        code: 20000,
        data: 'success'
      }
    }
  },

  {
    url: '/dept/update',
    type: 'post',
    response: _ => {
      return {
        code: 20000,
        data: 'success'
      }
    }
  }
]
