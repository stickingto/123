import request from '@/utils/request'

export function fetchList(query) {
  return request({
    url: '/menu1/list',
    method: 'get',
    params: query
  })
}

