import request from '@/utils/request'

export function fetchList(query) {
  return request({
    url: '/menu2/list',
    method: 'get',
    params: query
  })
}
