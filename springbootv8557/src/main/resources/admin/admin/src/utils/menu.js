const menu = {
    list() {
        return [{"backMenu":[{"child":[{"appFrontIcon":"cuIcon-present","buttons":["新增","查看","修改","删除"],"menu":"用戶","menuJump":"列表","tableName":"user"}],"menu":"用戶管理"},
        {"child":[{"appFrontIcon":"cuIcon-explore","buttons":["新增","查看","修改","删除","查看评论","农产登记"],"menu":"智果园","menuJump":"列表","tableName":"fruit"}],"menu":"智果园管理"},
        {"child":[{"appFrontIcon":"cuIcon-similar","buttons":["新增","查看","修改","删除","查看评论"],"menu":"智果商超","menuJump":"列表","tableName":"supermarket"}],"menu":"智果商超管理"},
        {"child":[{"appFrontIcon":"cuIcon-similar","buttons":["新增","查看","修改","删除","查看评论"],"menu":"果园寻知","menuJump":"列表","tableName":"knowledge"}],"menu":"果园寻知管理"},
        {"child":[{"appFrontIcon":"cuIcon-message","buttons":["查看","修改","回复","删除"],"menu":"留言板","tableName":"messages"}],"menu":"留言板"}],
        
        "frontMenu":[{"child":[{"appFrontIcon":"cuIcon-clothes","buttons":["查看","预订农产"],"menu":"农产预售列表","menuJump":"列表","tableName":"nongchanyushou"}],"menu":"农产预售模块"},
        {"child":[{"appFrontIcon":"cuIcon-explore","buttons":["查看","农产登记"],"menu":"农产求购列表","menuJump":"列表","tableName":"nongchanqiugou"}],"menu":"农产求购模块"},
        {"child":[{"appFrontIcon":"cuIcon-similar","buttons":["查看"],"menu":"公告信息列表","menuJump":"列表","tableName":"gonggaoxinxi"}],"menu":"公告信息模块"}],
        
        "hasBackLogin":"是","hasBackRegister":"否","hasFrontLogin":"否","hasFrontRegister":"否","roleName":"管理员","tableName":"users"},
        
        {"backMenu":[{"child":[{"appFrontIcon":"cuIcon-skin","buttons":["查看","删除","支付","农户违约"],"menu":"预订农产","menuJump":"列表","tableName":"yudingnongchan"}],"menu":"预订农产管理"},
        {"child":[{"appFrontIcon":"cuIcon-qrcode","buttons":["查看","支付"],"menu":"商家违约","menuJump":"列表","tableName":"shangjiaweiyue"}],"menu":"商家违约管理"},
        {"child":[{"appFrontIcon":"cuIcon-copy","buttons":["查看","支付"],"menu":"预售尾款","menuJump":"列表","tableName":"yushouweikuan"}],"menu":"预售尾款管理"},
        {"child":[{"appFrontIcon":"cuIcon-explore","buttons":["新增","查看","修改","删除","查看评论"],"menu":"农产求购","menuJump":"列表","tableName":"nongchanqiugou"}],"menu":"农产求购管理"},
        {"child":[{"appFrontIcon":"cuIcon-rank","buttons":["查看","审核","支付","农户违约"],"menu":"农产登记","menuJump":"列表","tableName":"nongchandengji"}],"menu":"农产登记管理"},
        {"child":[{"appFrontIcon":"cuIcon-similar","buttons":["查看","删除"],"menu":"农户违约","menuJump":"列表","tableName":"nonghuweiyue"}],"menu":"农户违约管理"},
        {"child":[{"appFrontIcon":"cuIcon-list","buttons":["查看","支付"],"menu":"求购尾款","menuJump":"列表","tableName":"qiugouweikuan"}],"menu":"求购尾款管理"}],

        "frontMenu":[{"child":[{"appFrontIcon":"cuIcon-clothes","buttons":["查看","预订农产"],"menu":"农产预售列表","menuJump":"列表","tableName":"nongchanyushou"}],"menu":"农产预售模块"},
        {"child":[{"appFrontIcon":"cuIcon-explore","buttons":["查看","农产登记"],"menu":"农产求购列表","menuJump":"列表","tableName":"nongchanqiugou"}],"menu":"农产求购模块"},
        {"child":[{"appFrontIcon":"cuIcon-similar","buttons":["查看"],"menu":"公告信息列表","menuJump":"列表","tableName":"gonggaoxinxi"}],"menu":"公告信息模块"}],
        "hasBackLogin":"否","hasBackRegister":"否","hasFrontLogin":"是","hasFrontRegister":"是","roleName":"商户","tableName":"shanghu"},
        
        {"backMenu":[{"child":[{"appFrontIcon":"cuIcon-explore","buttons":["新增","查看","修改","删除","查看评论"],"menu":"农产预售","menuJump":"列表","tableName":"nongchanyushou"}],"menu":"农产预售管理"},
        {"child":[{"appFrontIcon":"cuIcon-skin","buttons":["查看","删除","审核","违约付款","预售尾款"],"menu":"预订农产","menuJump":"列表","tableName":"yudingnongchan"}],"menu":"预订农产管理"},
        {"child":[{"appFrontIcon":"cuIcon-qrcode","buttons":["查看","删除"],"menu":"商家违约","menuJump":"列表","tableName":"shangjiaweiyue"}],"menu":"商家违约管理"},
        {"child":[{"appFrontIcon":"cuIcon-copy","buttons":["查看","删除"],"menu":"预售尾款","menuJump":"列表","tableName":"yushouweikuan"}],"menu":"预售尾款管理"},
        {"child":[{"appFrontIcon":"cuIcon-rank","buttons":["查看","删除","商家违约","求购尾款"],"menu":"农产登记","menuJump":"列表","tableName":"nongchandengji"}],"menu":"农产登记管理"},
        {"child":[{"appFrontIcon":"cuIcon-similar","buttons":["查看","支付"],"menu":"农户违约","menuJump":"列表","tableName":"nonghuweiyue"}],"menu":"农户违约管理"},
        {"child":[{"appFrontIcon":"cuIcon-list","buttons":["查看","删除"],"menu":"求购尾款","menuJump":"列表","tableName":"qiugouweikuan"}],"menu":"求购尾款管理"}],
        
        "frontMenu":[{"child":[{"appFrontIcon":"cuIcon-clothes","buttons":["查看","预订农产"],"menu":"农产预售列表","menuJump":"列表","tableName":"nongchanyushou"}],"menu":"农产预售模块"},
        {"child":[{"appFrontIcon":"cuIcon-explore","buttons":["查看","农产登记"],"menu":"农产求购列表","menuJump":"列表","tableName":"nongchanqiugou"}],"menu":"农产求购模块"},
        {"child":[{"appFrontIcon":"cuIcon-similar","buttons":["查看"],"menu":"公告信息列表","menuJump":"列表","tableName":"gonggaoxinxi"}],"menu":"公告信息模块"}],
        
        "hasBackLogin":"否","hasBackRegister":"否","hasFrontLogin":"是","hasFrontRegister":"是","roleName":"农户","tableName":"nonghu"}]
    }
}
export default menu;
