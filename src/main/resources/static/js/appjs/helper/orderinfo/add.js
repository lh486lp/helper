$().ready(function () {
    validateRule();
    iniLayDateTime('payTime');
    iniLayDateTime('postTime');
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
        url: "/system/orderinfo/save",
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
            orderId: {
                required: true,
                number: true,
            },
            orderNo: {
                required: true,
                maxlength: 256,
            },
            userId: {
                required: true,
                number: true,
            },
            addrId: {
                required: true,
                number: true,
            },
            productId: {
                required: true,
                number: true,
            },
            productUnitPrice: {
                required: true,
            },
            productCount: {
                required: true,
                number: true,
            },
            payTime: {
                required: true,
            },
            postType: {
                required: true,
                maxlength: 256,
            },
            postNo: {
                required: true,
                maxlength: 256,
            },
            postTime: {
                required: true,
            },
            status: {
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