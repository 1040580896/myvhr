import { getRequest } from '@/utils/api'

export const initMenu = (router, store) => {
    if (store.state.routes.length > 0) {
        return
    }

    getRequest('/system/config/menu').then(data => {
        if (data) {
            let fmtRoutes = formatRouters(data)
            router.addRoutes(fmtRoutes)
            // 菜单初始化
            store.commit('initRoutes', fmtRoutes)
            // 聊天初始化
            store.dispatch('connect');
        }
    })
}

export const formatRouters = (routes) => {
    let fmRoutes = []
    routes.forEach(router => {
        let {
            path,
            component,
            name,
            meta,
            iconCls,
            children,
        } = router

        if (children && children instanceof Array) {
            children = formatRouters(children)
        }

        let fmRouter = {
            path: path,
            name: name,
            iconCls: iconCls,
            meta: meta,
            children: children,
            component (resolve) {
                if (component.startsWith('Home')) {
                    require(['../views/' + component + '.vue'], resolve)
                } else if (component.startsWith('Emp')) {
                    require(['../views/emp/' + component + '.vue'], resolve)
                } else if (component.startsWith('Per')) {
                    require(['../views/per/' + component + '.vue'], resolve)
                } else if (component.startsWith('Sal')) {
                    require(['../views/sal/' + component + '.vue'], resolve)
                } else if (component.startsWith('Sta')) {
                    require(['../views/sta/' + component + '.vue'], resolve)
                } else if (component.startsWith('Sys')) {
                    require(['../views/sys/' + component + '.vue'], resolve)
                }
            },
        }
        fmRoutes.push(fmRouter)
    })
    return fmRoutes
}