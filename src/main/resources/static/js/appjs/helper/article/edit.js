$().ready(function () {
    var summernote = $('.summernote').summernote({
        height: '220px',
        lang: 'zh-CN',
        fontNamesIgnoreCheck: ['宋体', '微软雅黑', '黑体']
    });
    summernote.summernote('code', $('#articleContent').val());
    validateRule();
});
$.validator.setDefaults({
    submitHandler: function () {
        update();
    }
});

function update() {
    var content_sn = $("#content_sn").summernote('code');
    $("#articleContent").val(content_sn);
    $.ajax({
        cache: true,
        type: "POST",
        url: "/system/article/update",
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
            articleTitle: {
                required: true,
                maxlength: 256,
            },
            articleContent: {
                required: true,
            },
            imgPath: {
                required: true,
                maxlength: 256,
            },
            articleType: {
                required: true,
                number: true,
            }
        }
    })
}