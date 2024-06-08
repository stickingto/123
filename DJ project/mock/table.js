const Mock = require('mockjs')

const data = Mock.mock({
  'items|30': [{
    id: '@id',
    title: '@ctitle( 24, 30 )',
    'status|1': ['通知', '新闻', '通报','会议'],
    author: '@cname',
    display_time: '@datetime',
    pageviews: '@integer(300, 5000)'
  }]
})//随机生成数据

module.exports = [
  {
    url: '/vue-admin-template/table/list',
    type: 'get',
    response: config => {
      const items = data.items
      return {
        code: 20000,
        data: {
          total: items.length,
          items: items
        }
      }
    }
  }
]
