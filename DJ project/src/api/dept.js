import request from '@/utils/request'

export function fetchList(query) {
  return request({
    url: '/dept/list',
    method: 'get',
    params: query
  })
}

export function fetchDept(id) {
  return request({
    url: '/dept/detail',
    method: 'get',
    params: { id }
  })
}

export function createDept(data) {
  return request({
    url: '/dept/create',
    method: 'post',
    data
  })
}

export function updateDept(data) {
  return request({
    url: '/dept/update',
    method: 'post',
    data
  })
}
