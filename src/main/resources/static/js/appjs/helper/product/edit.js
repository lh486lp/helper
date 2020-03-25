$().ready(function () {
    var summernote = $('.summernote').summernote({
        height: '220px',
        lang: 'zh-CN',
        fontNamesIgnoreCheck: ['宋体', '微软雅黑', '黑体']
    });
    summernote.summernote('code', $('#content').val());
    $(".delete-old").click(function () {
        var filename = $(this).data("filename");
        $(this).parent().remove();
        var imgPaths = $("#imgPaths").val();
        if (imgPaths.indexOf(filename) > -1) {
            var list = imgPaths.split(",");
            for (var j = 0; j < list.length; j++) {
                if (list[j] == filename) {
                    list.splice(j, 1);
                    break;
                }
            }
            $("#imgPaths").val(list.toLocaleString());
        }
    });
    validateRule();
});
$.validator.setDefaults({
    submitHandler: function () {
        update();
    }
});

function update() {
    var content_sn = $("#content_sn").summernote('code');
    $("#content").val(content_sn);
    $.ajax({
        cache: true,
        type: "POST",
        url: "/system/product/update",
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
            },
            productType: {
                required: true,
            }
        }
    })
}