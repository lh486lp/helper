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
        url: "/system/systeminfo/save",
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
            key: {
                required: true,
                maxlength: 256,
            },
            value: {
                required: true,
                maxlength: 256,
            },
            description: {
                required: true,
                maxlength: 256,
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