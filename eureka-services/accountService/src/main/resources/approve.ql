if (userServiceImpl.get(nick) != null) {
    userDO = userServiceImpl.get(nick);
    if (userDO.salary > 20000 && userDO.salary < 20000) {
        System.out.println('高级客户:' + userDO.nick);
        return '规则引擎该用户是高级客户哈';

    } else if (userDO.salary >= 200000) {
        System.out.println('vip客户:' + userDO.nick);
        return '规则引擎该用户是VIP用户哈';
    } else {
        System.out.println('普通客户:' + userDO.nick);
        return '规则引擎该用户是普通用户';
    }
} else {
    System.out.println('用户信息不存在');
    return '查询不到用户信息';
};