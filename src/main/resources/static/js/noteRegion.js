//区域选择控制功能
var noteRegion = {};
noteRegion={
	//类初始化
	param:{
		pregion:'p_regionids',//待添加的数据区域
		aregion:'a_regionids',//已添加的数据区域
		rs:'rs', //记录用户操作的select级别，保存触发控件id
		selectPostionId:'chooseschool', //定位select控件的位置id，函数将会根据此id查询其底下的select控件
	},
	init: function(param){
		$.extend(noteRegion.param,param);
	},
	//页面操作行为，添加或删除项
	addRegion: function(flag/*identifered flag 1-add -1-remove*/){
		var _self = this;
		var jobj1 = '';
		var jobj2 = '';
		if(flag==1){
			jobj1 = $('#'+_self.param.pregion+' option:selected');
			jobj2 = $('#'+_self.param.aregion);
		}else if(flag==-1){
			jobj1 = $('#'+_self.param.aregion+' option:selected');
			jobj2 = $('#'+_self.param.pregion+'');
		}
		var len = jobj1.length;
		var source = $('#'+_self.param.rs).val();
		var nodeVal = '';
		var nodeTxt = '';
		if(len>0){
			jobj1.each(function(i,o){
				//构建新的选项
				var jt = $(o).clone();
				if(flag==1 && source!=''){ //添加新项
					nodeVal = source+"_"+$(o).val();
					nodeTxt = _self.getOptionTxt(jt.text(),source);
					if(jobj2.find('option[value="'+nodeVal+'"]').length==0){
						jt.val(nodeVal);
						jt.text(nodeTxt);
						jobj2.append(jt);
					}
				}else if(flag==-1){
					jt.val($(o).val().split("_")[1]);
					$(o).remove();
				}
			});
		}else{
			alert("请先选择目标项");
		}
	},
	//获取选项显示值
	getOptionTxt: function(originalTxt,source){
		var _self = this;
		var txt = '';
		var arr = ["provinceid","areaid","townid","schoolid"];
		for(var i=0; i<arr.length; i++){
			var jo = $('#'+_self.param.selectPostionId).find('#'+arr[i]+' option:selected');
			if(jo.val()>0){
				txt += jo.text() + '->';
			}
		}
		txt+=originalTxt;
		return txt;
	},
	//将所选项转换为传递参数
	getRegionParams:function(){
		var _self = this;
		var paramsStr = '';
		$('#'+_self.param.aregion+' option').each(function(i,o){
			var prefix = $(o).val().split("_")[0];
			var id = $(o).val().split("_")[1];
			if(prefix=='provinceid'){
				paramsStr+='&provinceids='+id;
			}
			if(prefix=='areaid'){
				paramsStr+='&areaids='+id;
			}
			if(prefix=='townid'){
				paramsStr+='&townids='+id;
			}
			if(prefix=='schoolid'){
				paramsStr+='&schoolids='+id;
			}
		});
		return paramsStr;
	},
	//页面初始化时的绑定动作
	addInit:function(){
		var _self = this;
		var findId = '';
		$('#'+_self.param.selectPostionId).find('select').bind('change',function(){
			findId = _self.getFindId($(this).attr('id'),$(this).val()>0?1:0);
			var i = 1;
			if(findId){
				$('#rs').val(findId);
				var timer = window.setInterval(function(){
					var jObj = $('#'+findId).find('option:gt(0)');
					if(jObj.length>0){
						$('#'+_self.param.pregion).html(jObj.clone());
						clearInterval(timer);
					}else{
						$('#'+_self.param.pregion).html('');
					}
					if(i>3){
						clearInterval(timer);
					}
				},300);
			}
		});
	},
	//根据用户选择确定要取的列表id
	getFindId:function(id,step/*step for action 1-get next drop 0-get selected drop*/){
		if(id=='provinceid'){
			if(step==1){
				return 'areaid';
			}else{
				return 'provinceid'; 
			}
		}	
		if(id=='areaid'){
			if(step==1){
				return 'townid';
			}else{
				return 'areaid';
			}
		}
		if(id=='townid'){
			if(step==1){
				return 'schoolid';
			}else{
				return 'townid';
			}
		}
		return "";		
	}
	
};