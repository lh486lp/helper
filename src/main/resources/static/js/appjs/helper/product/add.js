$().ready(function () {
    $('.summernote').summernote({
        height: '220px',
        lang: 'zh-CN'
    });
    validateRule();
});
$.validator.setDefaults({
    submitHandler: function () {
        save();
    }
});

function save() {
    var content_sn = $("#content_sn").summernote('code');
    $("#content").val(content_sn);
    $.ajax({
        cache: true,
        type: "POST",
        url: "/system/product/save",
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
            productName: {
                required: true,
                maxlength: 256,
            },
            productPrice: {
                required: true,
                number: true,
            },
            preferentialPrice: {
                required: true,
                number: true,
            },
            imgPath: {
                required: true,
                maxlength: 256,
            },
            productContent: {
                required: true,
            }
        }
    })
}