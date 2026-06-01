-- 服务费收入
EXEC sp_addextendedproperty
    @name = N'MS_Description',
    @value = N'服务费收入',
    @level0type = N'Schema', @level0name = 'dbo',
    @level1type = N'Table',  @level1name = 'OrderBase',
    @level2type = N'Column', @level2name = 'serviceFee';

-- 返现金额
EXEC sp_addextendedproperty
    @name = N'MS_Description',
    @value = N'返现金额',
    @level0type = N'Schema', @level0name = 'dbo',
    @level1type = N'Table',  @level1name = 'OrderBase',
    @level2type = N'Column', @level2name = 'rebateFee';

-- 应收（CNY）
EXEC sp_addextendedproperty
    @name = N'MS_Description',
    @value = N'应收（CNY）',
    @level0type = N'Schema', @level0name = 'dbo',
    @level1type = N'Table',  @level1name = 'OrderBase',
    @level2type = N'Column', @level2name = 'receivableFee';

-- 应付（CNY）
EXEC sp_addextendedproperty
    @name = N'MS_Description',
    @value = N'应付（CNY）',
    @level0type = N'Schema', @level0name = 'dbo',
    @level1type = N'Table',  @level1name = 'OrderBase',
    @level2type = N'Column', @level2name = 'payableFee';

-- 实收 （CNY）
EXEC sp_addextendedproperty
    @name = N'MS_Description',
    @value = N'实收 （CNY）',
    @level0type = N'Schema', @level0name = 'dbo',
    @level1type = N'Table',  @level1name = 'OrderBase',
    @level2type = N'Column', @level2name = 'receivedFee';

-- 实付 （CNY）
EXEC sp_addextendedproperty
    @name = N'MS_Description',
    @value = N'实付 （CNY）',
    @level0type = N'Schema', @level0name = 'dbo',
    @level1type = N'Table',  @level1name = 'OrderBase',
    @level2type = N'Column', @level2name = 'paidFee';

-- 欠收 使用公式计算：欠收=应收-实收； （CNY）
EXEC sp_addextendedproperty
    @name = N'MS_Description',
    @value = N'欠收 使用公式计算：欠收=应收-实收； （CNY）',
    @level0type = N'Schema', @level0name = 'dbo',
    @level1type = N'Table',  @level1name = 'OrderBase',
    @level2type = N'Column', @level2name = 'owedFee';

-- 欠付 使用公式计算：欠付=应付-实付 （CNY）
EXEC sp_addextendedproperty
    @name = N'MS_Description',
    @value = N'欠付 使用公式计算：欠付=应付-实付 （CNY）',
    @level0type = N'Schema', @level0name = 'dbo',
    @level1type = N'Table',  @level1name = 'OrderBase',
    @level2type = N'Column', @level2name = 'unpaidFee';

-- 一级部门（开拓）
EXEC sp_addextendedproperty
    @name = N'MS_Description',
    @value = N'一级部门（开拓）',
    @level0type = N'Schema', @level0name = 'dbo',
    @level1type = N'Table',  @level1name = 'OrderBase',
    @level2type = N'Column', @level2name = 'lv1NameKt';

-- 二级部门（开拓）
EXEC sp_addextendedproperty
    @name = N'MS_Description',
    @value = N'二级部门（开拓）',
    @level0type = N'Schema', @level0name = 'dbo',
    @level1type = N'Table',  @level1name = 'OrderBase',
    @level2type = N'Column', @level2name = 'lv2NameKt';

-- 三级部门（开拓）
EXEC sp_addextendedproperty
    @name = N'MS_Description',
    @value = N'三级部门（开拓）',
    @level0type = N'Schema', @level0name = 'dbo',
    @level1type = N'Table',  @level1name = 'OrderBase',
    @level2type = N'Column', @level2name = 'lv3NameKt';

-- 四级部门（开拓）
EXEC sp_addextendedproperty
    @name = N'MS_Description',
    @value = N'四级部门（开拓）',
    @level0type = N'Schema', @level0name = 'dbo',
    @level1type = N'Table',  @level1name = 'OrderBase',
    @level2type = N'Column', @level2name = 'lv4NameKt';

-- 五级部门（开拓）
EXEC sp_addextendedproperty
    @name = N'MS_Description',
    @value = N'五级部门（开拓）',
    @level0type = N'Schema', @level0name = 'dbo',
    @level1type = N'Table',  @level1name = 'OrderBase',
    @level2type = N'Column', @level2name = 'lv5NameKt';

-- 开拓人员
EXEC sp_addextendedproperty
    @name = N'MS_Description',
    @value = N'开拓人员',
    @level0type = N'Schema', @level0name = 'dbo',
    @level1type = N'Table',  @level1name = 'OrderBase',
    @level2type = N'Column', @level2name = 'saleName';

-- 一级部门（开拓）编码
EXEC sp_addextendedproperty
    @name = N'MS_Description',
    @value = N'一级部门（开拓）编码',
    @level0type = N'Schema', @level0name = 'dbo',
    @level1type = N'Table',  @level1name = 'OrderBase',
    @level2type = N'Column', @level2name = 'lv1CodeKt';

-- 二级部门（开拓）编码
EXEC sp_addextendedproperty
    @name = N'MS_Description',
    @value = N'二级部门（开拓）编码',
    @level0type = N'Schema', @level0name = 'dbo',
    @level1type = N'Table',  @level1name = 'OrderBase',
    @level2type = N'Column', @level2name = 'lv2CodeKt';

-- 三级部门（开拓）编码
EXEC sp_addextendedproperty
    @name = N'MS_Description',
    @value = N'三级部门（开拓）编码',
    @level0type = N'Schema', @level0name = 'dbo',
    @level1type = N'Table',  @level1name = 'OrderBase',
    @level2type = N'Column', @level2name = 'lv3CodeKt';

-- 四级部门（开拓）编码
EXEC sp_addextendedproperty
    @name = N'MS_Description',
    @value = N'四级部门（开拓）编码',
    @level0type = N'Schema', @level0name = 'dbo',
    @level1type = N'Table',  @level1name = 'OrderBase',
    @level2type = N'Column', @level2name = 'lv4CodeKt';

-- 五级部门（开拓）编码
EXEC sp_addextendedproperty
    @name = N'MS_Description',
    @value = N'五级部门（开拓）编码',
    @level0type = N'Schema', @level0name = 'dbo',
    @level1type = N'Table',  @level1name = 'OrderBase',
    @level2type = N'Column', @level2name = 'lv5CodeKt';

-- 开拓人员 编码
EXEC sp_addextendedproperty
    @name = N'MS_Description',
    @value = N'开拓人员 编码',
    @level0type = N'Schema', @level0name = 'dbo',
    @level1type = N'Table',  @level1name = 'OrderBase',
    @level2type = N'Column', @level2name = 'saleCode';

-- 一级部门（维护）
EXEC sp_addextendedproperty
    @name = N'MS_Description',
    @value = N'一级部门（维护）',
    @level0type = N'Schema', @level0name = 'dbo',
    @level1type = N'Table',  @level1name = 'OrderBase',
    @level2type = N'Column', @level2name = 'lv1NameWh';

-- 二级部门（维护）
EXEC sp_addextendedproperty
    @name = N'MS_Description',
    @value = N'二级部门（维护）',
    @level0type = N'Schema', @level0name = 'dbo',
    @level1type = N'Table',  @level1name = 'OrderBase',
    @level2type = N'Column', @level2name = 'lv2NameWh';

-- 三级部门（维护）
EXEC sp_addextendedproperty
    @name = N'MS_Description',
    @value = N'三级部门（维护）',
    @level0type = N'Schema', @level0name = 'dbo',
    @level1type = N'Table',  @level1name = 'OrderBase',
    @level2type = N'Column', @level2name = 'lv3NameWh';

-- 四级部门（维护）
EXEC sp_addextendedproperty
    @name = N'MS_Description',
    @value = N'四级部门（维护）',
    @level0type = N'Schema', @level0name = 'dbo',
    @level1type = N'Table',  @level1name = 'OrderBase',
    @level2type = N'Column', @level2name = 'lv4NameWh';

-- 五级部门（维护）
EXEC sp_addextendedproperty
    @name = N'MS_Description',
    @value = N'五级部门（维护）',
    @level0type = N'Schema', @level0name = 'dbo',
    @level1type = N'Table',  @level1name = 'OrderBase',
    @level2type = N'Column', @level2name = 'lv5NameWh';

-- 维护人员
EXEC sp_addextendedproperty
    @name = N'MS_Description',
    @value = N'维护人员',
    @level0type = N'Schema', @level0name = 'dbo',
    @level1type = N'Table',  @level1name = 'OrderBase',
    @level2type = N'Column', @level2name = 'maintainName';

-- 一级部门（维护）编码
EXEC sp_addextendedproperty
    @name = N'MS_Description',
    @value = N'一级部门（维护）编码',
    @level0type = N'Schema', @level0name = 'dbo',
    @level1type = N'Table',  @level1name = 'OrderBase',
    @level2type = N'Column', @level2name = 'lv1CodeWh';

-- 二级部门（维护）编码
EXEC sp_addextendedproperty
    @name = N'MS_Description',
    @value = N'二级部门（维护）编码',
    @level0type = N'Schema', @level0name = 'dbo',
    @level1type = N'Table',  @level1name = 'OrderBase',
    @level2type = N'Column', @level2name = 'lv2CodeWh';

-- 三级部门（维护）编码
EXEC sp_addextendedproperty
    @name = N'MS_Description',
    @value = N'三级部门（维护）编码',
    @level0type = N'Schema', @level0name = 'dbo',
    @level1type = N'Table',  @level1name = 'OrderBase',
    @level2type = N'Column', @level2name = 'lv3CodeWh';

-- 四级部门（维护）编码
EXEC sp_addextendedproperty
    @name = N'MS_Description',
    @value = N'四级部门（维护）编码',
    @level0type = N'Schema', @level0name = 'dbo',
    @level1type = N'Table',  @level1name = 'OrderBase',
    @level2type = N'Column', @level2name = 'lv4CodeWh';

-- 五级部门（维护）编码
EXEC sp_addextendedproperty
    @name = N'MS_Description',
    @value = N'五级部门（维护）编码',
    @level0type = N'Schema', @level0name = 'dbo',
    @level1type = N'Table',  @level1name = 'OrderBase',
    @level2type = N'Column', @level2name = 'lv5CodeWh';

-- 维护人员 编码
EXEC sp_addextendedproperty
    @name = N'MS_Description',
    @value = N'维护人员 编码',
    @level0type = N'Schema', @level0name = 'dbo',
    @level1type = N'Table',  @level1name = 'OrderBase',
    @level2type = N'Column', @level2name = 'maintainCode';

-- 一级部门（客服）
EXEC sp_addextendedproperty
    @name = N'MS_Description',
    @value = N'一级部门（客服）',
    @level0type = N'Schema', @level0name = 'dbo',
    @level1type = N'Table',  @level1name = 'OrderBase',
    @level2type = N'Column', @level2name = 'lv1NameKf';

-- 二级部门（客服）
EXEC sp_addextendedproperty
    @name = N'MS_Description',
    @value = N'二级部门（客服）',
    @level0type = N'Schema', @level0name = 'dbo',
    @level1type = N-- 服务费收入
EXEC sp_addextendedproperty 
    @name = N'MS_Description', 
    @value = N'服务费收入', 
    @level0type = N'Schema', @level0name = 'dbo', 
    @level1type = N'Table',  @level1name = 'OrderBase', 
    @level2type = N'Column', @level2name = 'serviceFee';

-- 返现金额
EXEC sp_addextendedproperty 
    @name = N'MS_Description', 
    @value = N'返现金额', 
    @level0type = N'Schema', @level0name = 'dbo', 
    @level1type = N'Table',  @level1name = 'OrderBase', 
    @level2type = N'Column', @level2name = 'rebateFee';

-- 应收（CNY）
EXEC sp_addextendedproperty 
    @name = N'MS_Description', 
    @value = N'应收（CNY）', 
    @level0type = N'Schema', @level0name = 'dbo', 
    @level1type = N'Table',  @level1name = 'OrderBase', 
    @level2type = N'Column', @level2name = 'receivableFee';

-- 应付（CNY）
EXEC sp_addextendedproperty 
    @name = N'MS_Description', 
    @value = N'应付（CNY）', 
    @level0type = N'Schema', @level0name = 'dbo', 
    @level1type = N'Table',  @level1name = 'OrderBase', 
    @level2type = N'Column', @level2name = 'payableFee';

-- 实收 （CNY）
EXEC sp_addextendedproperty 
    @name = N'MS_Description', 
    @value = N'实收 （CNY）', 
    @level0type = N'Schema', @level0name = 'dbo', 
    @level1type = N'Table',  @level1name = 'OrderBase', 
    @level2type = N'Column', @level2name = 'receivedFee';

-- 实付 （CNY）
EXEC sp_addextendedproperty 
    @name = N'MS_Description', 
    @value = N'实付 （CNY）', 
    @level0type = N'Schema', @level0name = 'dbo', 
    @level1type = N'Table',  @level1name = 'OrderBase', 
    @level2type = N'Column', @level2name = 'paidFee';

-- 欠收 使用公式计算：欠收=应收-实收； （CNY）
EXEC sp_addextendedproperty 
    @name = N'MS_Description', 
    @value = N'欠收 使用公式计算：欠收=应收-实收； （CNY）', 
    @level0type = N'Schema', @level0name = 'dbo', 
    @level1type = N'Table',  @level1name = 'OrderBase', 
    @level2type = N'Column', @level2name = 'owedFee';

-- 欠付 使用公式计算：欠付=应付-实付 （CNY）
EXEC sp_addextendedproperty 
    @name = N'MS_Description', 
    @value = N'欠付 使用公式计算：欠付=应付-实付 （CNY）', 
    @level0type = N'Schema', @level0name = 'dbo', 
    @level1type = N'Table',  @level1name = 'OrderBase', 
    @level2type = N'Column', @level2name = 'unpaidFee';

-- 一级部门（开拓）
EXEC sp_addextendedproperty 
    @name = N'MS_Description', 
    @value = N'一级部门（开拓）', 
    @level0type = N'Schema', @level0name = 'dbo', 
    @level1type = N'Table',  @level1name = 'OrderBase', 
    @level2type = N'Column', @level2name = 'lv1NameKt';

-- 二级部门（开拓）
EXEC sp_addextendedproperty 
    @name = N'MS_Description', 
    @value = N'二级部门（开拓）', 
    @level0type = N'Schema', @level0name = 'dbo', 
    @level1type = N'Table',  @level1name = 'OrderBase', 
    @level2type = N'Column', @level2name = 'lv2NameKt';

-- 三级部门（开拓）
EXEC sp_addextendedproperty 
    @name = N'MS_Description', 
    @value = N'三级部门（开拓）', 
    @level0type = N'Schema', @level0name = 'dbo', 
    @level1type = N'Table',  @level1name = 'OrderBase', 
    @level2type = N'Column', @level2name = 'lv3NameKt';

-- 四级部门（开拓）
EXEC sp_addextendedproperty 
    @name = N'MS_Description', 
    @value = N'四级部门（开拓）', 
    @level0type = N'Schema', @level0name = 'dbo', 
    @level1type = N'Table',  @level1name = 'OrderBase', 
    @level2type = N'Column', @level2name = 'lv4NameKt';

-- 五级部门（开拓）
EXEC sp_addextendedproperty 
    @name = N'MS_Description', 
    @value = N'五级部门（开拓）', 
    @level0type = N'Schema', @level0name = 'dbo', 
    @level1type = N'Table',  @level1name = 'OrderBase', 
    @level2type = N'Column', @level2name = 'lv5NameKt';

-- 开拓人员
EXEC sp_addextendedproperty 
    @name = N'MS_Description', 
    @value = N'开拓人员', 
    @level0type = N'Schema', @level0name = 'dbo', 
    @level1type = N'Table',  @level1name = 'OrderBase', 
    @level2type = N'Column', @level2name = 'saleName';

-- 一级部门（开拓）编码
EXEC sp_addextendedproperty 
    @name = N'MS_Description', 
    @value = N'一级部门（开拓）编码', 
    @level0type = N'Schema', @level0name = 'dbo', 
    @level1type = N'Table',  @level1name = 'OrderBase', 
    @level2type = N'Column', @level2name = 'lv1CodeKt';

-- 二级部门（开拓）编码
EXEC sp_addextendedproperty 
    @name = N'MS_Description', 
    @value = N'二级部门（开拓）编码', 
    @level0type = N'Schema', @level0name = 'dbo', 
    @level1type = N'Table',  @level1name = 'OrderBase', 
    @level2type = N'Column', @level2name = 'lv2CodeKt';

-- 三级部门（开拓）编码
EXEC sp_addextendedproperty 
    @name = N'MS_Description', 
    @value = N'三级部门（开拓）编码', 
    @level0type = N'Schema', @level0name = 'dbo', 
    @level1type = N'Table',  @level1name = 'OrderBase', 
    @level2type = N'Column', @level2name = 'lv3CodeKt';

-- 四级部门（开拓）编码
EXEC sp_addextendedproperty 
    @name = N'MS_Description', 
    @value = N'四级部门（开拓）编码', 
    @level0type = N'Schema', @level0name = 'dbo', 
    @level1type = N'Table',  @level1name = 'OrderBase', 
    @level2type = N'Column', @level2name = 'lv4CodeKt';

-- 五级部门（开拓）编码
EXEC sp_addextendedproperty 
    @name = N'MS_Description', 
    @value = N'五级部门（开拓）编码', 
    @level0type = N'Schema', @level0name = 'dbo', 
    @level1type = N'Table',  @level1name = 'OrderBase', 
    @level2type = N'Column', @level2name = 'lv5CodeKt';

-- 开拓人员 编码
EXEC sp_addextendedproperty 
    @name = N'MS_Description', 
    @value = N'开拓人员 编码', 
    @level0type = N'Schema', @level0name = 'dbo', 
    @level1type = N'Table',  @level1name = 'OrderBase', 
    @level2type = N'Column', @level2name = 'saleCode';

-- 一级部门（维护）
EXEC sp_addextendedproperty 
    @name = N'MS_Description', 
    @value = N'一级部门（维护）', 
    @level0type = N'Schema', @level0name = 'dbo', 
    @level1type = N'Table',  @level1name = 'OrderBase', 
    @level2type = N'Column', @level2name = 'lv1NameWh';

-- 二级部门（维护）
EXEC sp_addextendedproperty 
    @name = N'MS_Description', 
    @value = N'二级部门（维护）', 
    @level0type = N'Schema', @level0name = 'dbo', 
    @level1type = N'Table',  @level1name = 'OrderBase', 
    @level2type = N'Column', @level2name = 'lv2NameWh';

-- 三级部门（维护）
EXEC sp_addextendedproperty 
    @name = N'MS_Description', 
    @value = N'三级部门（维护）', 
    @level0type = N'Schema', @level0name = 'dbo', 
    @level1type = N'Table',  @level1name = 'OrderBase', 
    @level2type = N'Column', @level2name = 'lv3NameWh';

-- 四级部门（维护）
EXEC sp_addextendedproperty 
    @name = N'MS_Description', 
    @value = N'四级部门（维护）', 
    @level0type = N'Schema', @level0name = 'dbo', 
    @level1type = N'Table',  @level1name = 'OrderBase', 
    @level2type = N'Column', @level2name = 'lv4NameWh';

-- 五级部门（维护）
EXEC sp_addextendedproperty 
    @name = N'MS_Description', 
    @value = N'五级部门（维护）', 
    @level0type = N'Schema', @level0name = 'dbo', 
    @level1type = N'Table',  @level1name = 'OrderBase', 
    @level2type = N'Column', @level2name = 'lv5NameWh';

-- 维护人员
EXEC sp_addextendedproperty 
    @name = N'MS_Description', 
    @value = N'维护人员', 
    @level0type = N'Schema', @level0name = 'dbo', 
    @level1type = N'Table',  @level1name = 'OrderBase', 
    @level2type = N'Column', @level2name = 'maintainName';

-- 一级部门（维护）编码
EXEC sp_addextendedproperty 
    @name = N'MS_Description', 
    @value = N'一级部门（维护）编码', 
    @level0type = N'Schema', @level0name = 'dbo', 
    @level1type = N'Table',  @level1name = 'OrderBase', 
    @level2type = N'Column', @level2name = 'lv1CodeWh';

-- 二级部门（维护）编码
EXEC sp_addextendedproperty 
    @name = N'MS_Description', 
    @value = N'二级部门（维护）编码', 
    @level0type = N'Schema', @level0name = 'dbo', 
    @level1type = N'Table',  @level1name = 'OrderBase', 
    @level2type = N'Column', @level2name = 'lv2CodeWh';

-- 三级部门（维护）编码
EXEC sp_addextendedproperty 
    @name = N'MS_Description', 
    @value = N'三级部门（维护）编码', 
    @level0type = N'Schema', @level0name = 'dbo', 
    @level1type = N'Table',  @level1name = 'OrderBase', 
    @level2type = N'Column', @level2name = 'lv3CodeWh';

-- 四级部门（维护）编码
EXEC sp_addextendedproperty 
    @name = N'MS_Description', 
    @value = N'四级部门（维护）编码', 
    @level0type = N'Schema', @level0name = 'dbo', 
    @level1type = N'Table',  @level1name = 'OrderBase', 
    @level2type = N'Column', @level2name = 'lv4CodeWh';

-- 五级部门（维护）编码
EXEC sp_addextendedproperty 
    @name = N'MS_Description', 
    @value = N'五级部门（维护）编码', 
    @level0type = N'Schema', @level0name = 'dbo', 
    @level1type = N'Table',  @level1name = 'OrderBase', 
    @level2type = N'Column', @level2name = 'lv5CodeWh';

-- 维护人员 编码
EXEC sp_addextendedproperty 
    @name = N'MS_Description', 
    @value = N'维护人员 编码', 
    @level0type = N'Schema', @level0name = 'dbo', 
    @level1type = N'Table',  @level1name = 'OrderBase', 
    @level2type = N'Column', @level2name = 'maintainCode';

-- 一级部门（客服）
EXEC sp_addextendedproperty 
    @name = N'MS_Description', 
    @value = N'一级部门（客服）', 
    @level0type = N'Schema', @level0name = 'dbo', 
    @level1type = N'Table',  @level1name = 'OrderBase', 
    @level2type = N'Column', @level2name = 'lv1NameKf';

-- 二级部门（客服）
EXEC sp_addextendedproperty 
    @name = N'MS_Description', 
    @value = N'二级部门（客服）', 
    @level0type = N'Schema', @level0name = 'dbo', 
    @level1type = N