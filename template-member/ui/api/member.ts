import request from '@/config/axios'

export interface MemberVO {
  id: number
  userId: number
  nickname: string
  mobile: string
  avatar?: string
  levelId: number
  levelName?: string
  point: number
  status: number
  birthday?: Date
  gender?: number
  createTime?: Date
}

export interface MemberPageReqVO {
  pageNo: number
  pageSize: number
  nickname?: string
  mobile?: string
  levelId?: number
  status?: number
  createTime?: Date[]
}

// 查询会员分页
export const getMemberPage = (params: MemberPageReqVO) => {
  return request.get({ url: '/demo/member/page', params })
}

// 查询会员详情
export const getMember = (id: number) => {
  return request.get({ url: '/demo/member/get', params: { id } })
}

// 新增会员
export const createMember = (data: MemberVO) => {
  return request.post({ url: '/demo/member/create', data })
}

// 修改会员
export const updateMember = (data: MemberVO) => {
  return request.put({ url: '/demo/member/update', data })
}

// 删除会员
export const deleteMember = (id: number) => {
  return request.delete({ url: '/demo/member/delete', params: { id } })
}

// 导出会员
export const exportMember = (params: MemberPageReqVO) => {
  return request.download({ url: '/demo/member/export', params })
}
