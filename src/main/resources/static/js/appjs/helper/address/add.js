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
        url: "/system/address/save",
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
            receiverName: {
                required: true,
                maxlength: 256,
            },
            receiverPhone: {
                required: true,
                maxlength: 256,
            },
            province: {
                required: true,
                maxlength: 256,
            },
            area: {
                required: true,
                maxlength: 256,
            },
            town: {
                required: true,
                maxlength: 256,
            },
            detail: {
                required: true,
                maxlength: 256,
            },
            defaultAddr: {
                required: true,
            }
        }
    })
}