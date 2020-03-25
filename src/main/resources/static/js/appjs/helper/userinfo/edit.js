var prefix = "/system/userinfo";
$().ready(function () {
    validateRule();
    getTreeData($("#userId").val());
});
$.validator.setDefaults({
    submitHandler: function () {
        update();
    }
});

function update() {
    $.ajax({
        cache: true,
        type: "POST",
        url: prefix + "/update",
        data: $('#signupForm').serialize(),// 你的formid
        async: false,
        error: function (request) {
            parent.layer.alert("Connection error");
        },
        success: function (data) {
            if (data.code == 1) {
                parent.layer.msg("操作成功");
                parent.reLoad();
                var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
                parent.layer.close(index);
            } else {
                parent.layer.alert(data.msg)
            }
        }
    });
}

function validateRule() {
    $("#signupForm").validate({
        rules: {
            username: {
                required: true,
                maxlength: 256,
            },
            nickName: {
                required: true,
                maxlength: 256,
            },
            imgPath: {
                maxlength: 256,
            },
            idCard: {
                maxlength: 256,
            },
            phone: {
                required: true,
                maxlength: 256,
            },
            rcode: {
                required: true,
                maxlength: 256,
            },
            vipLevel: {
                required: true,
                number: true,
            },
            castellanLevel: {
                required: true,
                number: true,
            },
            goldCount: {
                required: true,
                number: true,
            },
            diamondCount: {
                required: true,
                number: true,
            }
        }
    })
}

function getTreeData(id) {
    $.ajax({
        type: "GET",
        url: prefix + "/fansTree/" + id,
        success: function (tree) {
            loadTree(tree);
        }
    });
}

function loadTree(tree) {
    $('#jstree').jstree({
        'core': {
            'data': tree
        },
        "plugins": ["search"]
    });
    $('#jstree').jstree().open_all();
}