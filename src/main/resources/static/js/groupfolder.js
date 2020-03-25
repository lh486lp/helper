/*
 * 图片上传
 * @author lijiangze
 */
//声明下拉菜单所属的命名空间
$.namespace('groupfolder');
window.groupfolderPath = '/system/sysFile';
var that;
/**
 * 后台图片上传
 * @param imgfile          文件
 * @param imgPath          返回地址id
 */
groupfolder.uploadimg = function (imgfile, imgPath) {
    defaultcompress = false;
    var fileObj = document.getElementById(imgfile).files[0]; // js 获取文件对象
    if (typeof (fileObj) == "undefined" || fileObj.size <= 0) {
        layer.msg("请选择图片");
        return;
    }
    var formFile = new FormData();
    formFile.append("file", fileObj); //加入文件对象
    //ajax 提交
    $.ajax({
        url: groupfolderPath + '/upload',
        data: formFile,
        type: "POST",
        dataType: "json",
        cache: false,//上传文件无需缓存
        processData: false,//用于对data参数进行序列化处理 这里必须false
        contentType: false, //必须
        success: function (result) {
            if (result.code == 1) {
                $('#' + imgPath).val(result.msg);
                layer.msg("上传完成!");
            } else {
                layer.msg("上传失败!");
            }
        },
    })
}
groupfolder.onchangeText = function (imgfile, onchangeText, preview) {
    var fileObj = document.getElementById(imgfile).files[0]; // js 获取文件对象
    if (typeof (fileObj) == "undefined" || fileObj.size <= 0) {
        layer.msg("请选择图片");
        return;
    } else {
        $('#' + onchangeText).text(fileObj.name)
        $('#' + preview).attr('src', URL.createObjectURL(fileObj));
    }
}
/**
 * 后台图片上传
 * @param imgfile          文件
 * @param imgPath          返回地址id
 */
groupfolder.uploadimgs = function (imgfile, imgPath) {
    var fileObjs = that.files; // js 获取文件对象
    if (typeof (fileObjs) == "undefined" || fileObjs.size <= 0) {
        layer.msg("请选择图片");
        return;
    }
    for (var index = 0; index < fileObjs.length; index++) {
        var fileObj = fileObjs[index];
        var formFile = new FormData();
        formFile.append("file", fileObj); //加入文件对象
        //ajax 提交
        $.ajax({
            url: groupfolderPath + '/upload',
            data: formFile,
            type: "POST",
            dataType: "json",
            async: false,
            cache: false,//上传文件无需缓存
            processData: false,//用于对data参数进行序列化处理 这里必须false
            contentType: false, //必须
            success: function (result) {
                if (result.code == 1) {
                    if (index == 0) {
                        $("#" + imgPath).val(result.msg);
                    } else {
                        $("#" + imgPath).val($("#" + imgPath).val() + "," + result.msg);
                    }
                }
            }
        })
    }
}
groupfolder.onchangeTexts = function (imgfile, onchangeText, preview) {
    document.getElementById(preview).innerHTML = "";
    var fileEl = document.getElementById(imgfile);
    that = this;
    var fileObjs = fileEl.files; // js 获取文件对象
    if (typeof (fileObjs) == "undefined" || fileObjs.size <= 0) {
        layer.msg("请选择图片");
        return;
    } else {
        for (var index = 0; index < fileObjs.length; index++) {
            var fileObj = fileObjs[index];
            var url = URL.createObjectURL(fileObj);
            var imgName = fileObj.name;
            var box = document.createElement("img");
            box.setAttribute("src", url);
            box.className = 'img-preview-sm';
            var deleteIcon = document.createElement("span");
            deleteIcon.className = 'delete';
            deleteIcon.innerText = 'x';
            deleteIcon.dataset.filename = imgName;
            var imgBox = document.createElement("div");
            imgBox.style.display = 'inline-block';
            imgBox.className = 'img-item';
            imgBox.appendChild(deleteIcon);
            imgBox.appendChild(box);
            //append到页面上
            var body = document.getElementById(preview);
            body.appendChild(imgBox);
            that.files = fileObjs;
            $(deleteIcon).click(function () {
                var filename = $(this).data("filename");
                $(this).parent().remove();
                var fileList = Array.from(that.files);
                for (var j = 0; j < fileList.length; j++) {
                    if (fileList[j].name = filename) {
                        fileList.splice(j, 1);
                        break;
                    }
                }
                that.files = fileList;
                // if ("createEvent" in document) {
                //     var evt = document.createEvent("HTMLEvents");
                //     evt.initEvent("change", false, true);
                //     fileEl.dispatchEvent(evt);
                // } else {
                //     fileEl.fireEvent("onchange");
                // }
            })
        }
    }
}
groupfolder.getFile = function (filePath) {
    if (filePath != '' && filePath != undefined && filePath.indexOf("http") != -1) {
        return filePath;
    } else {
        return groupfolderPath + '/getFile?fileName=' + filePath;
    }
}
/**
 * 富文本图片上传
 * @param files          文件
 * @param product          平台（czbs、aoliao）
 * @param moduleFlag       目标目录（枚举类，不存在枚举内的不能上传）
 * @param defaultcompress  是否压缩
 */
groupfolder.summernoteSendFile = function (files, editor, $editable, product, moduleFlag) {
    var formFile = new FormData();
    formFile.append("file", files[0]); //加入文件对象
    //ajax 提交
    var data = formFile;
    $.ajax({
        url: groupfolderPath + '/upload',
        data: data,
        type: "Post",
        dataType: "json",
        cache: false,//上传文件无需缓存
        processData: false,//用于对data参数进行序列化处理 这里必须false
        contentType: false, //必须
        success: function (result) {
            $('.summernote').summernote('editor.insertImage', window.groupfolderPath + '/getFile?fileName=' + result.msg);
        },
        error: function () {
            alert("上传失败");
        }
    })
}
/**
 * 文件上传
 * @param file             文件
 * @param product          平台（czbs、aoliao）
 * @param moduleFlag       目标目录（枚举类，不存在枚举内的不能上传）
 * @param filePath         保存文件返回的存储地址的插件ID
 */
groupfolder.uploadMultiFile = function (file, product, moduleFlag, filePath) {
    var fileObj = document.getElementById(file).files[0]; // js 获取文件对象
    if (typeof (fileObj) == "undefined" || fileObj.size <= 0) {
        layer.msg("请选择文件");
        return;
    }
    var formFile = new FormData();
    formFile.append("file", fileObj); //加入文件对象
    formFile.append("moduleFlag", moduleFlag);
    formFile.append("product", product);
    //ajax 提交
    $.ajax({
        url: groupfolderPath + '/files/uploadFile',
        data: formFile,
        type: "POST",
        dataType: "json",
        cache: false,//上传文件无需缓存
        processData: false,//用于对data参数进行序列化处理 这里必须false
        contentType: false, //必须
        success: function (result) {
            $('#' + filePath).append(
                '<div><input name="fileNames" type="hidden" value="' + result.originalName + '"/>'
                + '<input name="filePaths" type="hidden" value="' + result.fileUrl + '"/>'
                + '<span>' + result.originalName + '</span>'
                + '<a href="javascript:void(0)" onclick="groupfolder.rmFileDirectory(this);" class="cblue">删除</a></div>');
            layer.msg("上传完成!");
        },
    })
}
groupfolder.rmFileDirectory = function (e) {
    $(e).parent().remove();
}