import request from '@/utils/request'

export function tail(params) {
    return request({
        url: '/log/tail',
        method: 'get',
        params: params
    })
}


export function names() {
    return request({
        url: '/log/names',
        method: 'get',
    })
}