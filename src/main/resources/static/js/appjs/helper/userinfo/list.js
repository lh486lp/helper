var prefix = "/system/userinfo"
$(function () {
    load();
    iniLayDateTime('createTimeBegin');
    iniLayDateTime('createTimeEnd');
    iniLayDateTime('updateTimeBegin');
    iniLayDateTime('updateTimeEnd');
});

function load() {
    $('#exampleTable').bootstrapTable({
        method: 'get', // 服务器数据的请求方式 get or post
        url: prefix + "/list", // 服务器数据的加载地址
        //	showRefresh : true,
        //	showToggle : true,
        //	showColumns : true,
        iconSize: 'outline',
        toolbar: '#exampleToolbar',
        striped: true, // 设置为true会有隔行变色效果
        dataType: "json", // 服务器返回的数据类型
        pagination: true, // 设置为true会在底部显示分页条
        // queryParamsType : "limit",
        // //设置为limit则会发送符合RESTFull格式的参数
        singleSelect: false, // 设置为true将禁止多选
        // contentType : "application/x-www-form-urlencoded",
        // //发送到服务器的数据编码类型
        pageSize: 10, // 如果设置了分页，每页数据条数
        pageNumber: 1, // 如果设置了分布，首页页码
        //search : true, // 是否显示搜索框
        showColumns: false, // 是否显示内容下拉框（选择显示的列）
        sidePagination: "server", // 设置在哪里进行分页，可选值为"client" 或者 "server"
        queryParams: function (params) {
            //说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
            var temp = $("#search").serializeJsonObject();
            temp["offset"] = params.offset;
            temp["limit"] = params.limit;
            return temp;
        },
        // //请求服务器数据时，你可以通过重写参数的方式添加一些额外的参数，例如 toolbar 中的参数 如果
        // queryParamsType = 'limit' ,返回参数必须包含
        // limit, offset, search, sort, order 否则, 需要包含:
        // pageSize, pageNumber, searchText, sortName,
        // sortOrder.
        // 返回false将会终止请求
        columns: [
            {
                checkbox: true
            },
            {
                field: 'imgPath',
                title: '头像',
                formatter: function (value) {
                    if (value) {
                        return '<img src="' + groupfolder.getFile(value) + '" width="80">';
                    }
                }
            },
            {
                field: 'nickName',
                title: '昵称',
            },
            {
                field: 'phone',
                title: '手机号',
            },
            {
                field: 'rcode',
                title: '推广码',
            },
            {
                field: 'vipLevel',
                title: '会员等级',
                formatter: function (value) {
                    if (value == 1) {
                        return '普通';
                    } else if (value == 2) {
                        return '金牌';
                    } else if (value == 3) {
                        return '钻石';
                    } else {
                        return '-';
                    }
                }
            },
            {
                field: 'castellanLevel',
                title: '城主类型',
                formatter: function (value) {
                    if (value == 2) {
                        return '金牌城主';
                    } else if (value == 3) {
                        return '钻石城主';
                    } else {
                        return '-';
                    }
                }
            },
            {
                field: 'goldCount',
                title: '金牌任务',
            },
            {
                field: 'diamondCount',
                title: '钻石任务',
            },
            {
                field: 'visible',
                title: '状态',
                formatter: function (value) {
                    if (value) {
                        return '<span class="label label-primary">启用</span>';
                    } else {
                        return '<span class="label label-primary">禁用</span>';
                    }
                }
            },
            {
                field: 'opsUserinfo.nickName',
                title: '操作人',
            },
            {
                field: 'createTime',
                title: '注册时间',
                formatter: function (value) {
                    if (!value)
                        return '-';
                    return base.formatDate(new Date(value));
                }
            },
            {
                field: 'updateTime',
                title: '修改时间',
                formatter: function (value) {
                    if (!value)
                        return '-';
                    return base.formatDate(new Date(value));
                }
            },
            {
                title: '操作',
                field: 'id',
                align: 'center',
                formatter: function (value, row, index) {
                    var e = '<a class="btn btn-primary btn-sm ' + s_edit_h + '" href="#" mce_href="#" title="编辑" onclick="edit(\''
                        + row.userId
                        + '\')"><i class="fa fa-edit"></i></a> ';
                    var d = '<a class="btn btn-warning btn-sm ' + s_remove_h + '" href="#" title="删除"  mce_href="#" onclick="remove(\''
                        + row.userId
                        + '\')"><i class="fa fa-remove"></i></a> ';
                    var f = '<a class="btn btn-success btn-sm" href="#" title="详情"  mce_href="#" onclick="detail(\''
                        + row.userId
                        + '\')"><i class="fa fa-info-circle"></i></a> ';
                    return e + d;
                }
            }]
    });
}

function reLoad() {
    $('#exampleTable').bootstrapTable('refresh');
}

function add() {
    var index = layer.open({
        type: 2,
        title: '添加',
        maxmin: true,
        shadeClose: false, // 点击遮罩关闭层
        area: ['800px', '520px'],
        content: prefix + '/add' // iframe的url
    });
    layer.full(index);
}

function edit(id) {
    var index = layer.open({
        type: 2,
        title: '编辑',
        maxmin: true,
        shadeClose: false, // 点击遮罩关闭层
        area: ['800px', '520px'],
        content: prefix + '/edit/' + id // iframe的url
    });
    layer.full(index);
}

function remove(id) {
    var index = layer.confirm('确定要删除选中的记录？', {
        btn: ['确定', '取消']
    }, function () {
        $.ajax({
            url: prefix + "/remove",
            type: "post",
            data: {
                'userId': id
            },
            success: function (r) {
                if (r.code == 1) {
                    layer.msg(r.msg);
                    reLoad();
                } else {
                    layer.msg(r.msg);
                }
            }
        });
    })
}

function detail(id) {
    var index = layer.open({
        type: 2,
        title: '编辑',
        maxmin: true,
        shadeClose: false, // 点击遮罩关闭层
        area: ['800px', '520px'],
        content: prefix + '/detail/' + id // iframe的url
    });
    layer.full(index);
}

function batchRemove() {
    var rows = $('#exampleTable').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
    if (rows.length == 0) {
        layer.msg("请选择要删除的数据");
        return;
    }
    layer.confirm("确认要删除选中的'" + rows.length + "'条数据吗?", {
        btn: ['确定', '取消']
        // 按钮
    }, function () {
        var ids = new Array();
        // 遍历所有选择的行数据，取每条数据对应的ID
        $.each(rows, function (i, row) {
            ids[i] = row['userId'];
        });
        $.ajax({
            type: 'POST',
            data: {
                "ids": ids
            },
            url: prefix + '/batchRemove',
            success: function (r) {
                if (r.code == 1) {
                    layer.msg(r.msg);
                    reLoad();
                } else {
                    layer.msg(r.msg);
                }
            }
        });
    }, function () {
    });
}