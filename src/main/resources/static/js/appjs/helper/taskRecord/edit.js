$().ready(function () {
    validateRule();
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
        url: "/system/taskRecord/update",
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
            taskId: {
                required: true,
                number: true,
            },
            issue: {
                required: true,
                maxlength: 256,
            },
            reward: {
                required: true,
            },
            imgPath: {
                required: true,
                maxlength: 256,
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