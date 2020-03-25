/**
 * 基础JS类，封装常用的文档和AJAX操作
 *
 * @author Daniel
 */
function Base() {
};
Base.prototype = {
    /**
     * 必须选择或镇充的控件的ID，多个时以","分隔
     */
    requestParam: null,
    /**
     * 检查必选项，如必选项没选择或镇充则进行提示
     * requestParam 必须选择或镇充的控件的ID，多个时以","分隔
     * 使用说明：必选项需写在requestParam中，并在页面初始化时对其进行赋值，另外必选项中添加title作为提示内容，需以中文命名
     *  @author gwl
     */
    checkRequest: function () {
        if (this.requestParams) {
            var request = this.requestParams.split(',');
            for (i = 0; i < request.length; i++) {
                tmp = $('#' + request[i]).attr('value');
                if (!tmp || -1 == tmp || 0 == tmp) {
                    base.alert('请选择' + $('#' + request[i]).attr('title'), '温馨提示');
                    return false;
                }
            }
        }
        return true;
    },
    /**
     * 格式化日期
     * @param date
     * @param fmt
     * @returns {string}
     */
    formatDate: function (date, fmt) { //author: meizz
        if (!date)
            date = new Date();
        if (!fmt)
            fmt = "yyyy-MM-dd";

        var o = {
            "M+": date.getMonth() + 1,                 //月份
            "d+": date.getDate(),                    //日
            "h+": date.getHours(),                   //小时
            "m+": date.getMinutes(),                 //分
            "s+": date.getSeconds(),                 //秒
            "q+": Math.floor((date.getMonth() + 3) / 3), //季度
            "S": date.getMilliseconds()             //毫秒
        };
        if (/(y+)/.test(fmt))
            fmt = fmt.replace(RegExp.$1, (date.getFullYear() + "").substr(4 - RegExp.$1.length));
        for (var k in o)
            if (new RegExp("(" + k + ")").test(fmt))
                fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
        return fmt;
    },
    /**
     * 获取当前日期
     * format 为输出日期格式，默认格式为yyyy-MM-dd
     */
    getCurrDate: function (format) {
        if (!format) {
            format = 'yyyy-MM-dd';
        }
        var curDate = new Date();
        var year = '' + curDate.getFullYear();
        var month = '' + (curDate.getMonth() + 1);
        var day = '' + curDate.getDate();
        var len = 0;
        if (month < 10)
            month = '0' + month;
        if (day < 10)
            day = '0' + day;
        var tmp = '';
        var result = format;
        for (i = 0; i < format.length; i++) {
            chr = format.substr(i, 1);
            if (tmp.indexOf(chr) > -1) {
                tmp += format.substr(i, 1);
            }
            if (('yMd'.indexOf(chr) > -1 && (tmp.indexOf(chr) == -1 || i == format.length - 1))) {
                if (tmp.indexOf('y') >= 0) {
                    if (tmp.length > 4) len = 4; else len = tmp.length;
                    result = result.replace(tmp, year.substr(4 - len));
                } else if (tmp.indexOf('M') >= 0) {
                    if (tmp.length > 2) len = 2; else len = tmp.length;
                    result = result.replace(tmp, month.substr(2 - len));
                } else if (tmp.indexOf('d') >= 0) {
                    if (tmp.length > 2) len = 2; else len = tmp.length;
                    result = result.replace(tmp, day.substr(2 - len));
                }
                tmp = chr;
            }
        }
        return result;
    },
    /**
     * 获取当前日期
     * 格式为"yyyy年MM月dd日 星期N"
     */
    getCurrentDateAndWeek: function () {
        var todayDate = new Date();
        var timeStr = "";
        if (navigator.appName == "Netscape") {
            timeStr = (1900 + todayDate.getYear()) + "年" + (todayDate.getMonth() + 1) + "月" + todayDate.getDate() + "日";
        }
        if (navigator.appVersion.indexOf("MSIE") != -1) {
            timeStr = todayDate.getYear() + "年" + (todayDate.getMonth() + 1) + "月" + todayDate.getDate() + "日";
        }
        if (todayDate.getDay() == 0) timeStr += " 星期日";
        if (todayDate.getDay() == 1) timeStr += " 星期一";
        if (todayDate.getDay() == 2) timeStr += " 星期二";
        if (todayDate.getDay() == 3) timeStr += " 星期三";
        if (todayDate.getDay() == 4) timeStr += " 星期四";
        if (todayDate.getDay() == 5) timeStr += " 星期五";
        if (todayDate.getDay() == 6) timeStr += " 星期六";
        return timeStr;
    },
    /**
     * 注册全局LOADING事件，用于“正在载入。。”提示
     */
    regLoadingEvent: function () {
        $("#loading").bind("ajaxSend", function () {
            $(this).show();
        }).bind("ajaxComplete", function () {
            $(this).hide();
        });
    },
    /**
     * 弹出提示信息
     * content 提示内容
     * title 提示框标题，默认“系统信息”
     */
    alert: function (/*string*/content, /*string*/title) {
        var t = (title || '系统信息');
        layer.open({
            title: t,
            content: content
        });
    },
    /**
     * 弹出提示信息
     * content 提示内容
     * title 提示框标题，默认“系统信息”
     * returnUrl 返回的url地址
     */
    alertAndReturn: function (/*string*/content, /*string*/title, /*string*/returnUrl) {
        var t = (title || '系统信息');
        var button = "<input id=ensure type=button class=but_03 value='确 定' onClick=\"$('#windownbg').remove();$('#windown-box').fadeOut('slow',function(){$(this).remove();});location.href = '" + returnUrl + "';\" />";
        tipsWindown(t, "text:<div class='cRed'><table width='100%' height=80><tr><td height=60 valign=middle align=center>" + content + "</td></tr><tr><td align=center>" + button + "</td></tr></table></div>", "250", "100", "true", "", "true", "text");
    },
    /**
     * 弹出确认框提示信息
     * content 提示内容
     * title 提示框标题，默认“系统信息”
     */
    confirm: function (/*string*/content, /*string*/title) {
        return confirm(content);
    },
    /**
     * 绑定点击按钮或链接进行AJAX请求时记录到浏览器history，需记录的按钮或链接统一加上标签：rel='history'
     * elt 需要绑定AJAX请求记录的按钮或链接元素
     */
    bindClickHistory: function (elt) {
        if (!elt) {
            elt = $("input[rel='history'],a[rel='history']");
        }
        elt.click(function () {
            var hash = window.location.href;
            hash = hash.replace(/^.*#/, '');
            $.historyLoad(hash);
            return false;
        });
    },
    /**
     * 添加、编辑等页面的返回
     */
    goBack: function (/*string*/ showId, /*string*/ hideId, /*string*/ showId2) {
        $("#" + showId).show();
        $("#" + hideId).hide();
        $("#" + showId2).show();
    },
    winGoBack: function () {
        window.history.back();
    },
    /**
     * 载入html文件并插入到showId元素中
     * url 请求URL
     * elementId 显示请求结果的DOM元素
     * hash URL 上的描点标识，主要用于实现AJAX请求历史记录
     * onsuccess 回调函数
     */
    load: function (/*string*/url, /*string*/showId, /*string*/hash, /*function*/onsuccess) {
        url = encodeURI(url);
        if (url.indexOf('&ofield=') == -1 && this.ofield && this.ofield.length > 0) {
            url += '&ofield=' + this.ofield + '&otype=' + this.otype;
        }
        if (url.indexOf('&_temp=') == -1) {
            url += ('&_temp=' + Math.random());
        }
        //加上pageNo页码参数，以实现翻页后新增删除等操作能够回到当时所在第几页，配合方法：base._beforeQueryClick
        if (url.indexOf('&pageNo=') == -1) {
            var e = $("input[name='rollhidden'][type='hidden']");
            if (e && e.length > 0) {
                url += '&pageNo=' + e.attr('value');
            }
        }
        $('#' + showId).load(url, function (result) {
            if (onsuccess) {
                onsuccess(result);
                //base._beforeQueryClick();
            }
            base.setTrClass();
            $('td').each(function (i) {
                var style = "word-wrap:break-word;word-break:break-all;";
                if ($(this).attr('style') != '') {
                    style += ";" + $(this).attr('style');
                }
                $(this).attr('style', style);
            });
        });
        if (hash) {
            location.hash = hash;
        }
    },
    /**
     * 保存表单
     * url  请求地址
     * formId 表单ID
     * onsuccess  成功后执行的函数
     * type 请求类型
     */
    save: function (/*string*/url, /*string*/formId, onsuccess, type) {
        url = encodeURI(url);
        if (!$('#' + formId).valid()) {
            return false;
        }
        var params = this._getFormValues(formId);
        $("#" + formId + " :submit,#" + formId + " :input[ref='submit']").attr('disabled', 'true');
        $.ajax({
            type: "POST",
            url: url,
            data: params,
            success: function (result) {
                //如果返回脚本，则执行并返回
                if (base.evalScripts(result)) {
                    return;
                }
                var suc = base.json(result);
                if (!suc) {
                    base.alert('出现未知异常，操作失败！');
                    return;
                }
                base.alert(suc.message);
                if (suc.success) {
                    onsuccess(result);
                } else {
                    $("#" + formId + " :submit,#" + formId + " :input[ref='submit']").removeAttr('disabled');
                }
            },
            error: function (result) {
                base.alert('出现未知异常，操作失败！');
            },
            dataType: type
        });
    },
    /**
     * 删除操作
     */
    del: function (/*string*/url, /*string*/params, onsuccess, type) {
        this.post(/*string*/url, /*string*/params, function (result) {
            onsuccess(result);
        }, type);
    },
    /**
     * 发送AJAX请求动作，用于删除、保存设置等，若返回JSON对象会自动提示JSON的信息
     * url 请求地址
     * params 请求参数 如： '&userId=1'
     */
    post: function (/*string*/url, /*string*/params, onsuccess, type) {
        this.req(/*string*/url, /*string*/params, function (result) {
            //如果返回脚本，则执行并返回
            if (base.evalScripts(result)) {
                return;
            }
            var suc = base.json(result);
            if (suc) {
                base.alert(suc.message);
            }
            onsuccess(result);
        }, type);
    },
    /**
     * 发送AJAX请求动作
     * url 请求地址
     * params 请求参数 如： '&userId=1'
     */
    req: function (/*string*/url, /*string*/params, onsuccess, type) {
        url = encodeURI(url);
        if (url.indexOf('&_temp=') == -1) {
            url += ('&_temp=' + Math.random());
        }
        $.ajax({
            type: "POST",
            url: url,
            data: params,
            success: function (result) {
                if (onsuccess) {
                    onsuccess(result);
                }
            },
            error: function (result) {
                base.alert('出现未知异常，操作失败！');
            },
            dataType: type
        });
    },
    /**
     * 对查询列表要排序的表头进行CSS等设置，用于点击列表表头进行排序查询
     * queryfunction 查询函数名，如：student.query
     */
    initListOrder: function (/*function*/queryfunction) {
        var elts = $("th[ofield]").addClass('listhead_order');
        elts.each(function () {
            $(this).attr('otype', 'DESC').attr('title', '点击可按[' + $.trim($(this).text()) + ']进行排序查询').click(function () {
                base._queryList(queryfunction, this);
            });
            if ($(this).attr('ofield') == base.ofield) {
                var images = base.otype == 'DESC' ? '/images/school/ico/adown.gif' : '/images/school/ico/aup.gif';
                $(this).attr('otype', base.otype).append("<img src='" + images + "' width='16' height='16' align='absmiddle' />");
            }
        });
    },
    _queryList: function (/*function*/queryfunction, /*element*/clickelt) {
        if (this.ofield == $(clickelt).attr('ofield')) {
            this.otype = this.otype == 'DESC' ? 'ASC' : 'DESC';
        } else {
            this.otype = 'DESC';
        }
        this.ofield = $(clickelt).attr('ofield');
        queryfunction(clickelt);
    },
    /**
     * 将服务器端返回的JSON对象转换成js中的对象
     */
    json: function (/*string*/jsonString) {
        try {
            var isjson = typeof(jsonString) == "object" && Object.prototype.toString.call(jsonString).toLowerCase() == "[object object]" && !jsonString.length;
            if (!isjson) {
                jsonString = jsonString.replace(/<\/?pre.*?>/g, "");
            }
            return isjson ? jsonString : eval('(' + jsonString + ')');
        } catch (ex) {
            return null;
        }
    },
    /**
     * 使指定的checkbox框全部被选中或不选中
     */
    checkAll: function (/*string*/ checkboxName, /*boolean*/ check, /*function*/ callBack) {
        var input = $("input[name='" + checkboxName + "']:enabled");
        for (var i = 0; i < input.length; i++) {
            input[i].checked = check;
        }
        if (callBack) {
            callBack();
        }
    },
    /**
     * 使指定的checkbox框被选中或不选中
     */
    checkGradeByNum: function (/*string*/ checkboxName, /*boolean*/ check, /*int*/ startNum, /*int*/ endNum) {
        var input = $("input[name='" + checkboxName + "']:enabled");
        for (var i = 0; i < input.length; i++) {
            if (i >= startNum && i <= endNum) {
                input[i].checked = check;
            }
        }
    },
    /**
     * 使指定的checkbox框全部被选中或不选中
     */
    checkAllGrade: function (/*boolean*/ check, /*function*/ callBack) {
        var input = $("input[name='littleCourseGradeIds']:enabled");
        for (var i = 0; i < input.length; i++) {
            input[i].checked = check;
        }
        var input = $("input[name='primaryCourseGradeIds']:enabled");
        for (var i = 0; i < input.length; i++) {
            input[i].checked = check;
        }
        var input = $("input[name='highCourseGradeIds']:enabled");
        for (var i = 0; i < input.length; i++) {
            input[i].checked = check;
        }
        $("#check_little")[0].checked = check;
        $("#check_primary")[0].checked = check;
        $("#check_junior_heigh")[0].checked = check;
        if (callBack) {
            callBack();
        }
    },
    /**
     * 使指定的checkbox框全部被选中或不选中
     */
    checkGrade: function (/*string*/ checkboxName, /*boolean*/ check, /*function*/ callBack) {
        var input = $("input[name='" + checkboxName + "']:enabled");
        for (var i = 0; i < input.length; i++) {
            input[i].checked = check;
        }
        if (callBack) {
            callBack();
        }
    },
    /**
     * 按多选框名称取得已勾选的多选框对象数组
     */
    getChecked: function (/*string*/ checkboxName) {
        return $("input[name='" + checkboxName + "']:checked");
    },
    /**
     * 按多选框名称取得已勾选的值并组装成参数
     * checkboxName 多选框名称
     * paramName 要组装成的参数名，为空则默认 = checkboxName
     */
    getCheckedValuesParam: function (/*string*/ checkboxName, /*String*/ paramName) {
        var ckbs = $("input[name='" + checkboxName + "']:checked");
        if (!ckbs || ckbs.length < 1) {
            return '';
        }
        var params = '';
        var pn = (paramName || checkboxName);
        ckbs.each(function () {
            params += '&' + pn + '=' + this.value;
        });
        return params;
    },
    /**
     *  自动生成URL中的参数
     *  paramStr 参数条件ID，多个以','分隔
     *  filterLen 在生成URL参数变量时截掉前几位字符
     *  @author gwl
     */
    getUrlParams: function (/*string*/ paramStr, filterLen) {
        var param = '';
        if (!filterLen)
            filterLen = 0;
        if (paramStr) {
            var params = paramStr.split(',');
            for (i = 0; i < params.length; i++) {
                if ('checkbox' == $('#' + params[i]).attr('type'))
                    param += this.getCheckedValuesParam($('#' + params[i]).attr('name'), params[i]);
                else
                    param += '&' + params[i].substr(filterLen) + '=' + $('#' + params[i]).attr('value');
            }
        }
        return param;
    },
    /**
     * 初始化文件上传操作，依赖于 /js/base/ajaxupload.js ，使用此方法前必须引入ajaxupload.js
     * moduleFlag（必填） 模块标识，用于指定上传到服务端所在的文件目录，
     *        对应于后台cn.qtone.xxt.base.upload.domain.UploadModuleConfig类配置的模块标识
     *
     * buttonId 页面上的上传按钮ID
     * fileType 允许上传的文件类型，即文件后缀名，多个用逗号隔开，默认为 'xls,xlsx', '*'代表允许上传所有格式的文件
     * maxCount 一个页面上允许上传的文件数，默认1，上传文件在这个数的时候，上传按钮会自动隐藏.
     * fileEltName 生成的随机文件名表单元素名 默认ufilename，用于生成上传成功后的表单字段，用于服务端返回的随机文件名
     * ofileEltName 生成的原始文件名表单元素名 默认uofilename，用于生成上传成功后的表单字段，用于服务端返回的原始文件名
     * fileSize 允许上传的文件大小,必须是整数,以K为单位,为null时表示不限制大小
     * compress 是否压缩，只对图片有效，1-压缩，0-不压缩，默认不压缩
     * icon 是否生成缩略图，只对图片有效，1-生成缩略图，0-不生成缩略图，默认不生成缩略图
     * iconwidth 生成缩略图大小，icon为1时有效
     * width 图片压缩大小
     * 使用实例：页面上需要添加上传的地方加个 <input id="button1" type="button" class="but_03" value="点击选择文件上传"  /> 元素
     *            在页面加载完成以后JS调用 base.initUpload('admin_edu_school','button1')方法即可实现 参数自定;
     *           可参考学籍管理 - 学校管理 - 班级管理 - 兴趣生导入功能.
     */
    initUpload: function (/*string*/moduleFlag, /*string*/buttonId, /*string*/fileType, /*int*/maxCount, /*string*/fileEltName, /*string*/ofileEltName, /*function*/onsuccess, /*int*/fileSize, /*int*/compress, /*int*/icon, /*int*/iconwidth, /*int*/width) {
        if (!maxCount || maxCount < 1) maxCount = 1;
        var ufilename = (fileEltName || 'ufilename');//新的随机文件名hidden name
        var uofilename = (ofileEltName || 'uofilename');//原始文件名hidden name
        var ft = (fileType || 'xls,xlsx,csv').replace(new RegExp(',', 'gm'), '|');
        var button = $('#' + buttonId), interval;
        var buttonval = button.val();
        var param = '&moduleFlag=' + moduleFlag;
        if (fileSize != null) {
            param += '&fileSize=' + fileSize;
        }
        if (compress != null) {
            param += '&compress=' + compress;
        }
        if (icon != null) {
            param += '&icon=' + icon;
        }
        if (iconwidth != null) {
            param += '&iconwidth=' + iconwidth;
        }
        if (width != null) {
            param += '&width=' + width;
        }
        //if($('#filecount_'+buttonId).length==0){
        button.after("<input id='filecount_" + buttonId + "' value='0' type='hidden' />");
        //}
        new AjaxUpload(buttonId, {
            action: '/system/base/upload?action=uploadFile' + param,
            name: 'file',
            responseType: 'json',
            onSubmit: function (file, ext) {
                if (ft != '*' && (!ext || !(eval('/^(' + ft + ')$/i')).test(ext))) {
                    base.alert('只能上传格式为[' + ft + ']的文件！');
                    return false;
                }
                button.attr('value', '正在上传文件');
                this.disable();
                interval = window.setInterval(function () {
                    var text = button.attr('value');
                    if (text.length < 15) {
                        button.attr('value', text + '.');
                    } else {
                        button.attr('value', buttonval);
                    }
                }, 200);
            },
            onComplete: function (file, response) {
                var res = base.json(response);
                button.attr('value', buttonval);
                window.clearInterval(interval);
                //上传成功
                if (res.success) {
                    var newFileName = res.rfname;
                    var c = parseInt($('#filecount_' + buttonId).attr('value')) + 1;
                    $('#filecount_' + buttonId).attr('value', c);
                    if (c >= maxCount) {
                        $('#' + buttonId).hide();
                    }
                    this.enable();
                    $('#imptips').html('<p><input name=\'' + ufilename + '\' type=\'hidden\' value=\'' + newFileName + '\'/>'
                        + '<input name=\'' + uofilename + '\' type=\'hidden\' value=\'' + file + '\'/>'
                        + file + '  <a href=\'javascript:void(0)\' onclick=\"base._delfile(this,\'' + moduleFlag + '\',\'' + newFileName + '\','
                        + maxCount + ',\'' + buttonId + '\')\" class=\"cblue\">删除</a></p>');
                    if (onsuccess) {
                        onsuccess(file, response);
                        //base._beforeQueryClick();
                    }
                }
                else {
                    this.enable();
                    base.alert(res.msg);
                }
            }
        });
    },
    //COPY上面的方法，为了不显示删除按钮
    initPhotoUpload: function (/*string*/moduleFlag, /*string*/buttonId, /*string*/fileType, /*int*/maxCount, /*string*/fileEltName, /*string*/ofileEltName, /*function*/onsuccess, /*int*/fileSize, /*int*/compress, /*int*/icon, /*int*/iconwidth, /*int*/width) {
        if (!maxCount || maxCount < 1) maxCount = 1;
        var ufilename = (fileEltName || 'ufilename');//新的随机文件名hidden name
        var uofilename = (ofileEltName || 'uofilename');//原始文件名hidden name
        var ft = (fileType || 'xls,xlsx').replace(new RegExp(',', 'gm'), '|');
        var button = $('#' + buttonId), interval;
        var buttonval = button.val();
        var param = '&moduleFlag=' + moduleFlag;
        if (fileSize != null) {
            param += '&fileSize=' + fileSize;
        }
        if (compress != null) {
            param += '&compress=' + compress;
        }
        if (icon != null) {
            param += '&icon=' + icon;
        }
        if (iconwidth != null) {
            param += '&iconwidth=' + iconwidth;
        }
        if (width != null) {
            param += '&width=' + width;
        }
        button.after("<input id='filecount_" + buttonId + "' value='0' type='hidden' />");
        new AjaxUpload(buttonId, {
            action: '/system/base/upload.do?action=upload' + param,
            name: 'file',
            onSubmit: function (file, ext) {
                if (ft != '*' && (!ext || !(eval('/^(' + ft + ')$/i')).test(ext))) {
                    base.alert('只能上传格式为[' + ft + ']的文件！');
                    return false;
                }
                button.attr('value', '正在上传文件');
                this.disable();
                interval = window.setInterval(function () {
                    var text = button.attr('value');
                    if (text.length < 15) {
                        button.attr('value', text + '.');
                    } else {
                        button.attr('value', buttonval);
                    }
                }, 200);
            },
            onComplete: function (file, response) {
                var res = base.json(response);
                button.attr('value', buttonval);
                window.clearInterval(interval);
                //上传成功
                if (res.success) {
                    var newFileName = res.rfname;
                    var c = parseInt($('#filecount_' + buttonId).attr('value')) + 1;
                    $('#filecount_' + buttonId).attr('value', c);
                    if (c >= maxCount) {
                        $('#' + buttonId).hide();
                    }
                    this.enable();
                    $('#' + buttonId).after('<input name=\'' + ufilename + '\' type=\'hidden\' value=\'' + newFileName + '\' id=\'ufile_' + c + '\'/>'
                        + '<input name=\'' + uofilename + '\' type=\'hidden\' value=\'' + file + '\' id=\'uofile_' + c + '\'/>');
                    if (onsuccess) {
                        onsuccess(file, response);
                        //base._beforeQueryClick();
                    }
                }
                else {
                    this.enable();
                    base.alert(res.message);
                }
            }
        });
    },
    /**
     * 删除已经上传的临时文件，为内部调用，对应于initUpload 自动生成的删除链接
     * e 文件“删除”链接元素对象
     * moduleFlag 模块标识
     * fileName 生成的文件名
     * maxCount 页面上最多允许上传的文件数
     * buttonId 页面上传按钮元素ID
     */
    _delfile: function (/*element*/e, /*string*/moduleFlag, /*string*/ fileName, /*int*/maxCount, /*string*/ buttonId) {
        $.ajax({
            type: "POST",
            url: '/system/base/upload.do?action=removeTempFile',
            data: '&fileName=' + fileName + '&moduleFlag=' + moduleFlag,
            success: function (result) {
                var c = parseInt($('#filecount_' + buttonId).attr('value')) - 1;
                if (e)
                    $(e).parent().remove();
                $('#filecount_' + buttonId).attr('value', c);
                if (c < (maxCount || 1)) {
                    $('#' + buttonId).show();
                }
            }
        });
    },
    /**
     * 执行指定的文本中的所有脚本,执行成功返回true，否则返回false
     */
    evalScripts: function (/*string*/str) {
        if (!str || typeof(str) != 'string') return false;
        var reg = '<script[^>]*>([\\S\\s]*?)<\/script>';
        var str = str.match(new RegExp(reg, 'img'));
        var suc = false;
        try {
            for (var i = 0, num = str.length; i < num; i++) {
                eval(str[i].match(new RegExp(reg, 'im'))[1]);
                suc = true;
            }
        } catch (ex) {
            suc = false;
        }
        return suc;
    },
    /**
     * 表单可提交元素过滤器.
     */
    _formFilter: function (/*object*/node) {
        var type = (node.type || "").toLowerCase();
        return !node.disabled && node.name && !($.inArray(type, ["file", "submit", "image", "reset", "button"]) > -1);
    },
    /**
     * 设置查询列表的calss样式，及鼠标移出移入动作
     */
    setTrClass: function () {
        $(".m_table tr:even").addClass("alt");
        $(".m_table tr").mouseover(function () {
            $(this).addClass("over");
        }).mouseout(function () {
            $(this).removeClass("over");
        }).click(function () {
            $(this).toggleClass("tr_chouse");
        })
    },
    /**
     * 将指定form节点的所有可提交元素组合成字符串返回
     */
    _getFormValues: function (/*string*/formId) {
        var formNode = $('#' + formId)[0];
        if ((!formNode) || (!formNode.tagName) || (!formNode.tagName.toLowerCase() == "form")) {
            base.alert('请指定一个正确的form节点！');
            return null;
        }
        var values = [];
        for (var i = 0; i < formNode.elements.length; i++) {
            var elm = formNode.elements[i];
            if (!elm || elm.tagName.toLowerCase() == "fieldset" || !this._formFilter(elm)) {
                continue;
            }
            var name = encodeURIComponent(elm.name);
            var type = elm.type.toLowerCase();
            if (type == "select-multiple") {
                for (var j = 0; j < elm.options.length; j++) {
                    if (elm.options[j].selected) {
                        values.push(name + "=" + encodeURIComponent(elm.options[j].value));
                    }
                }
            } else {
                if ($.inArray(type, ["radio", "checkbox"]) > -1) {
                    if (elm.checked) {
                        values.push(name + "=" + encodeURIComponent(elm.value));
                    }
                } else {
                    values.push(name + "=" + encodeURIComponent(elm.value));
                }
            }
        }
        var inputs = $("input");
        for (var i = 0; i < inputs.length; i++) {
            var input = inputs[i];
            if (input.type.toLowerCase() == "image" && input.form == formNode && this._formFilter(input)) {
                var name = encodeURIComponent(input.name);
                values.push(name + "=" + encodeURIComponent(input.value));
                values.push(name + ".x=0");
                values.push(name + ".y=0");
            }
        }
        return values.join("&") + "&";
    },
    /**
     取两个日期时间之差
     **/
    getDateDiff: function (date1, date2) {
        var re = /^(\d{4})\S(\d{1,2})\S(\d{1,2})$/;
        var dt1, dt2;
        if (re.test(date1)) {
            dt1 = new Date(RegExp.$1, RegExp.$2 - 1, RegExp.$3);
        }
        if (re.test(date2)) {
            dt2 = new Date(RegExp.$1, RegExp.$2 - 1, RegExp.$3);
        }
        return Math.floor((dt2 - dt1) / (1000 * 60 * 60 * 24))
    },
    /*
    * Added by ytfei 2010-03-31
    * 对Firefox console.log包装
    */
    log: function () {
        if (typeof(console) != 'undefined' && typeof(console.log) == 'function') {
            Array.prototype.unshift.call(arguments, '[DEBUG]');
            console.log(Array.prototype.join.call(arguments, ' '));
        }
    }
}
//生成base类实例，注册loading事件
var base = null;
$(document).ready(function () {
    base = new Base();
    base.regLoadingEvent();
});
/*
 * --------------------------------------------------------------------------------
 * jQuery命名空间
 * 
 * 使用方法：
 * 
 * 先声明命名空间
 * $.namespace('xxt.dorplist');
 * 
 * 然后在该命名空间中构造自己的类等
 * xxt.droplist.Droplist = function(){
 * 	...
 * }
 * 
 * @author ytfei	2010-03-03
 */
jQuery.extend({
    namespace: function () {
        var o, d;
        jQuery.each(arguments, function (idx, v) {
            d = v.split(".");
            o = window[d[0]] = window[d[0]] || {};
            jQuery.each(d.slice(1), function (idx2, v2) {
                o = o[v2] = o[v2] || {};
            });
        });
        return o;
    }
});
//自定义一个函数将表单转为json对象
$.fn.serializeJsonObject = function () {
    var json = {};
    var form = this.serializeArray();
    $.each(form, function () {
        if (json[this.name]) {
            if (!json[this.name].push) {
                json[this.name] = [json[this.name]];
            }
            json[this.name].push(this.value);
        } else {
            json[this.name] = this.value || '';
        }
    });
    return json;
}
//折叠ibox
$('.collapse-search').click(function () {
    var ibox = $(this).closest('div.ibox');
    var button = $(this).find('i');
    var content = ibox.find('div.ibox-search');
    content.slideToggle(200);
    button.toggleClass('fa-chevron-up').toggleClass('fa-chevron-down');
    ibox.toggleClass('').toggleClass('border-bottom');
    setTimeout(function () {
        ibox.resize();
        ibox.find('[id^=map-]').resize();
    }, 50);
});

function getDate(a) {
    var dd = new Date();
    dd.setTime(dd.getTime() + (a == undefined || isNaN(parseInt(a)) ? 0 : parseInt(a)) * 86400000);
    var y = dd.getFullYear();
    var m = dd.getMonth() + 1;
    var d = dd.getDate();
    return y + "-" + (m < 10 ? ('0' + m) : m) + "-" + (d < 10 ? ('0' + d) : d);
}

/**
 * 初始化范围日期选择控件
 * 需要在html中增加两个hidden的input标签，id 分别为 start_ 与 end_ + 入参的id
 * 如：入参id为creTime
 *    <input type="text" id="start_creTime" name="creStartTime" hidden>
 *    <input type="text" id="end_creTime" name="creEndTime" hidden>
 *
 * @param id 显示的日期控件标签
 * @param defaultValue 初始化时间，格式'yyyy-MM-dd - yyyy-MM-dd'，不传值则不进行初始化
 */
function iniLaydateRange(id, defaultValue) {
    var expDate = laydate.render({
        elem: '#' + id,
        max: 0,
        range: true,
        value: defaultValue,
        done: function (value, date, endDate) {
            var dateArray = value.split(" - ");
            $('#start_' + id).val(dateArray[0])
            $('#end_' + id).val(dateArray[1])
        }
    });
    if (defaultValue != undefined && defaultValue != null) {
        var defaultArray = defaultValue.split(" - ");
        $('#start_' + id).val(defaultArray[0])
        $('#end_' + id).val(defaultArray[1])
    }
}

// 一般日期选择
function iniLayDateTime(id, value) {
    laydate.render({
        elem: '#' + id,
        type: 'datetime',
        value: value
    });
}

function get(url) {
    var msg = "-";
    $.ajax({
        type: 'GET',
        async: false,
        url: url,
        success: function (r) {
            if (r.code == 1) {
                msg = r.msg;
            }
        }
    });
    return msg;
}