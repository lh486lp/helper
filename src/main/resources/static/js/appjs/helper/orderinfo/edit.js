$().ready(function () {
    validateRule();
});
$.validator.setDefaults({
    submitHandler: function () {
        update();
    }
});

function update() {
    console.log($('#signupForm').serializeJsonObject());
    $.ajax({
        cache: true,
        type: "POST",
        url: "/system/orderinfo/update",
        data: $('#signupForm').serializeJsonObject(),// 你的formid
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
                number: true
            }
        }
    })
}