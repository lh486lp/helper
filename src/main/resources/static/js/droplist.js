/*
 * 下拉菜单
 * @author ytfei
 */

//声明下拉菜单所属的命名空间
$.namespace('xxt.droplist');

/**
 * 将ID映射成与DroplistVO字段相应的名字
 */
function mapIdToName(v_id) {
    if (!v_id)
        return v_id;

    switch (v_id) {
        case 'prov':
            return 'provinceId';
        case 'area':
            return 'areaId';
        case 'town':
            return 'townId';
        case 'school':
            return 'schoolId';
        case 'board':
            return 'boardId';
        case 'boardtype':
            return 'boardtypeId';
        case 'coursegrade':
            return 'gradeId';
        case 'expert':
            return 'expertId';
        case 'weekly':
            return 'p_regionids';
        case 'weekNum':
            return 'weekNum';
        case 'member':
            return 'memberId';
        default:
            return v_id;
    }
}

//select标签上namespace属性的默认值
xxt.droplist.DEFAULT_NS = '__droplist__';

/*
 * droplist模板中的一些常用方法全部封装在这个Utils类中
 */
xxt.droplist.Utils = function () {

    /**
     * 自动获取下拉菜单变化时所需要的参数
     *
     * 注意：该处理收集的参数是当前命名空间下的下拉菜单参数
     */
    this.collectParams = function (namespace) {
        var params = {};
        var str = 'select';
        if (namespace && namespace != xxt.droplist.DEFAULT_NS)
            str += '[namespace=' + namespace + ']';

        $('body').find(str).each(function (idx, v) {
            v = $(v);

            var selectedKey = mapIdToName(v.attr('droplist'));
            var selectedVal = $(v).val();
            if (!selectedVal)
                selectedVal = '';

            params[selectedKey] = selectedVal;
        });

        // console.log(params);
        return params;
    };

    /*
     * 根据用户的请求类型以及传入的请求参数，动态地构造下拉菜单Url @type string 下拉列表的类型 @params object
     * JSON格式的对象
     */
    this.generateUrl = function (droplist, params) {
        var url = '/czbs/droplist/';
        if (droplist)
            url += droplist + "?_dl=0";

        $.each(params, function (k, v) {
            url += '&' + k + '=' + v;
        });

        return encodeURI(url);
    };

    /**
     * 将两个Hash对象进行融合。如果有相同的key值，则用第二个对象的值覆盖第一个对象的值。
     */
    this.uniqueMerge = function (obj1, obj2) {
        if (obj1 && obj2 && typeof obj2 == 'object') {
            for (var p in obj2)
                obj1[p] = obj2[p];
        }

        return obj1;
    };

    /*
     * @src
     * 	object	产生变化的源对象
     * @isReset
     * 	boolean	是否要获取重置级联关系，如果不是（flase），则是获取更新目标
     */
    this.getDefaultCascade = function (src, isReset) {
        //alert(src + "|" + src.droplist + "|" + src.namespace + "|" + isReset);
        var ns = src.namespace;
        if (!ns)
            ns = xxt.droplist.DEFAULT_NS;

        // 级联关系
        var relatedObj = {
            load_prov: {droplist: 'prov', namespace: ns},
            prov: {droplist: 'area', namespace: ns},
            area: {droplist: 'town', namespace: ns},
            town: {droplist: 'school', namespace: ns},
            school: {droplist: 'class', namespace: ns},
            load_board: {droplist: 'board', namespace: ns},
            board: {droplist: 'boardtype', namespace: ns},
            load_coursegrade: {droplist: 'coursegrade', namespace: ns},
            load_expert: {droplist: 'expert', namespace: ns},
            load_weekly: {droplist: 'weekly', namespace: ns},
            load_weekNum: {droplist: 'weekNum', namespace: ns},
            memberType: {droplist: 'member', namespace: ns}
        };

        switch (src.droplist) {
            case 'load_prov':
                return isReset ? $.merge([relatedObj.load_prov], this.getDefaultCascade(relatedObj.load_prov, isReset)) : [relatedObj.load_prov];
            case 'prov':
                return isReset ? $.merge([relatedObj.prov], this.getDefaultCascade(relatedObj.prov, isReset)) : [relatedObj.prov];
            case 'area':
                return isReset ? $.merge([relatedObj.area], this.getDefaultCascade(relatedObj.area, isReset)) : [relatedObj.area];
            case 'town':
                return isReset ? $.merge([relatedObj.town], this.getDefaultCascade(relatedObj.town, isReset)) : [relatedObj.town];
            case 'school':
                return isReset ? $.merge([relatedObj.school], this.getDefaultCascade(relatedObj.school, isReset)) : [relatedObj.school];
            case 'load_board':
                return isReset ? $.merge([relatedObj.load_board], this.getDefaultCascade(relatedObj.load_board, isReset)) : [relatedObj.load_board];
            case 'board':
                return isReset ? $.merge([relatedObj.board], this.getDefaultCascade(relatedObj.board, isReset)) : [relatedObj.board];
            case 'load_coursegrade':
                return isReset ? $.merge([relatedObj.load_coursegrade], this.getDefaultCascade(relatedObj.load_coursegrade, isReset)) : [relatedObj.load_coursegrade];
            case 'load_expert':
                return isReset ? $.merge([relatedObj.load_expert], this.getDefaultCascade(relatedObj.load_expert, isReset)) : [relatedObj.load_expert];
            case 'load_weekly':
                return isReset ? $.merge([relatedObj.load_weekly], this.getDefaultCascade(relatedObj.load_weekly, isReset)) : [relatedObj.load_weekly];
            case 'load_weekNum':
                return isReset ? $.merge([relatedObj.load_weekNum], this.getDefaultCascade(relatedObj.load_weekNum, isReset)) : [relatedObj.load_weekNum];
            case 'memberType':
                return isReset ? $.merge([relatedObj.memberType], this.getDefaultCascade(relatedObj.memberType, isReset)) : [relatedObj.memberType];

            default:
                return [];
        }
    }

}
/*
 * jquery cookie操作插件
 * name cookie名称
 * value cookie值
 * options cookie属性，参数，如有效期等 {expires: 7, path: '/', domain: 'jquery.com', secure: true}
 * 
 *  $.cookie('the_cookie'); //读取Cookie值
	$.cookie('the_cookie', 'the_value'); //设置cookie的值
	$.cookie('the_cookie', 'the_value', {expires: 7, path: '/', domain: 'jquery.com', secure: true});//新建一个cookie 包括有效期 路径 域名等
	$.cookie('the_cookie', 'the_value'); //新建cookie
	$.cookie('the_cookie', null); //删除一个cookie
 */
jQuery.cookie = function (name, value, options) {
    if (typeof value != 'undefined') {
        options = options || {};
        if (value === null) {
            value = '';
            options = $.extend({}, options);
            options.expires = -1;
        }
        var expires = '';
        if (options.expires && (typeof options.expires == 'number' || options.expires.toUTCString)) {
            var date;
            if (typeof options.expires == 'number') {
                date = new Date();
                date.setTime(date.getTime() + (options.expires * 24 * 60 * 60 * 1000));
            } else {
                date = options.expires;
            }
            expires = '; expires=' + date.toUTCString();
        }
        var path = options.path ? '; path=' + (options.path) : '';
        var domain = options.domain ? '; domain=' + (options.domain) : '';
        var secure = options.secure ? '; secure' : '';
        document.cookie = [name, '=', encodeURIComponent(value), expires, path, domain, secure].join('');
    } else {
        var cookieValue = null;
        if (document.cookie && document.cookie != '') {
            var cookies = document.cookie.split(';');
            for (var i = 0; i < cookies.length; i++) {
                var cookie = jQuery.trim(cookies[i]);
                if (cookie.substring(0, name.length + 1) == (name + '=')) {
                    cookieValue = decodeURIComponent(cookie.substring(name.length + 1));
                    break;
                }
            }
        }
        return cookieValue;
    }
};


/*
 * 得到下一个下拉名称
 */
function getNextDropList(srcDropList) {
    switch (srcDropList) {
        case 'load_prov':
            return 'prov';
        case 'prov':
            return 'area';
        case 'area':
            return 'town';
        case 'town':
            return 'school';
        case 'school':
            return 'class';
        case 'load_board':
            return 'board';
        case 'board':
            return 'boardtype';
        case 'load_expert':
            return "expert";
        case 'load_weekly':
            return "weekly";
        case 'load_weekNum':
            return "weekNum";
        case 'memberType':
            return "member";
        default:
            return;
    }
}

/*
 * @p_src 
 * 	object {type:'area'} 
 * 	变更产生源
 * @p_params
 * 	object	{param1:'value1',param2:'value2',...}	
 * 	用户自定义参数选项
 * @p_dest 
 * 	array	[{type:'grade',id:'grade_id_dl'},{type:'clazz',id:'clazz_id_dl'}]
 * 	变更关联目标
 * @p_reset 
 * 	array	同p_dest	
 * 	变更重置目标
 * 
 * 以上参数如果都为空，则系统将采用默认的处理方式。
 */
xxt.droplist.Droplist = function (/*object*/p_src,
                                  /*object*/p_params,
                                  /*object array*/p_dest,
                                  /*object array*/p_reset) {

    //cookie下拉默认值start===========================
    //放入cookie
    srcCookieName = p_src.droplist;
    srcCookieValue = p_src.value;
    if (p_src.droplist) {
        srcCookieName = p_src.droplist;
        srcCookieValue = p_src.value;
    } else {
        srcCookieName = "load_prov";
        srcCookieValue = null;
    }
    $.cookie(srcCookieName, srcCookieValue, {path: "/"});
    //alert(srcCookieName+"="+$.cookie(srcCookieName));
    //取出cookie
    var targetCookieName = getNextDropList(srcCookieName);
    //alert(targetCookieName);
    if (!v_params && targetCookieName) {//前台没传默认值 且目标cookie不为无
        if ($.cookie(targetCookieName)) {//存在该cookie
            p_params = {selectedValue: $.cookie(targetCookieName)};
        }
    }
    //cookie下拉默认值end=============================*/


    // 设置默认值
    var v_src = p_src, v_dest = p_dest, v_reset = p_reset, v_params = p_params;
    var util = new xxt.droplist.Utils();

    // --------------------------------------------------------------------------------
    // 如果源对象不存在，则默认为加载地区选项
    if (!v_src) {
        alert('JS异常，下拉菜单触发源不能为空！');
        return;
    }
    v_src = $(v_src);


    if (!v_src.attr('namespace')) {
        // 默认下拉菜单命名空间
        v_src.attr('namespace', xxt.droplist.DEFAULT_NS);
    }


    //--------------------------------------------------------------------------------
    //alert(v_src)

    if (!v_dest) {
        v_dest = util.getDefaultCascade({namespace: v_src.attr('namespace'), droplist: v_src.attr('droplist')});
    }

    //alert("v_reset");
    if (!v_reset) {
        v_reset = util.getDefaultCascade({namespace: v_src.attr('namespace'), droplist: v_src.attr('droplist')}, true);
        //添加对SI下拉菜单的重置支持
//		if(v_src.attr('droplist') == 'area' || v_src.attr('droplist') == 'load_area'){
//			v_reset.push({namespace:v_src.attr('namespace'),droplist:'si'});
//			v_reset.push({namespace:v_src.attr('namespace'),droplist:'transaction'});
//			v_reset.push({namespace:v_src.attr('namespace'),droplist:'schoolRole'});
//		}

        if (v_src.attr('droplist') == 'school') {
//			v_reset.push({namespace:v_src.attr('namespace'),droplist:'schoolRole'});
        }
    }

    //alert("v_params");
    if (!v_params)
        v_params = {};

    //收集当前命名空间下的下拉菜单参数
    var v_collectedParams = util.collectParams(v_src.attr('namespace'));
    v_params = util.uniqueMerge(v_collectedParams, v_params);

//	 console.log(v_src);
//	 console.log(v_dest);
//	 console.log(v_reset);

    // --------------------------------------------------------------------------------
    // 当源选项改变时，其它的关联选项应该先进行重置
    $.each(v_reset, function (idx, v) {
        if (!v)
            return;

        var str = 'select[droplist=' + v.droplist + ']';
        if (v.namespace && v.namespace != xxt.droplist.DEFAULT_NS)
            str += '[namespace=' + v.namespace + ']'

        v = $(str);
        var placeholder = v.attr('placeholder');
        v.empty();
        v.attr('class', v.class);
        $('<option value="">' + (!placeholder?"请选择":placeholder) + '</option>').appendTo(v);

    });

    $.each(v_dest, function (idx, v) {
        // util.generateUrl(v.type, p_params);
        var str = 'select[droplist=' + v.droplist + ']';
        if (v.namespace && v.namespace != xxt.droplist.DEFAULT_NS)
            str += '[namespace=' + v.namespace + ']';
        // console.log(util.generateUrl(v.type, v_params));
        $(str).attr('class', v.class);

        $(str).load(util.generateUrl(v.droplist, v_params), function () {
            //如果设置了默认选项值，则触发下一级菜单的onchange事件
            if (v_params.selectedKey || v_params.selectedValue)
                $(str).trigger('onchange');
        });

    });
}