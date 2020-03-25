$().ready(function () {
    validateRule();
});
$.validator.setDefaults({
    submitHandler: function () {
        save();
    }
});

function save() {
    $.ajax({
        cache: true,
        type: "POST",
        url: "/system/userinfo/save",
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
            userId: {
                required: true,
                number: true,
            },
            parentId: {
                required: true,
                number: true,
            },
            username: {
                required: true,
                maxlength: 256,
            },
            password: {
                required: true,
                maxlength: 256,
            },
            accountPassword: {
                required: true,
                maxlength: 256,
            },
            nickName: {
                required: true,
                maxlength: 256,
            },
            imgPath: {
                required: true,
                maxlength: 256,
            },
            idCard: {
                required: true,
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
            },
            visible: {
                required: true,
                number: true,
            },
            opsUserId: {
                required: true,
                number: true,
            }
        }
    })
}