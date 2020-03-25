//区域选择控制功能
function RegionUtil(){};

// 新框架会有两个select，第一列真正显示数据的是：bootstrap-duallistbox-nonselected-list_ + id


RegionUtil.prototype = {
	//页面初始化时的绑定动作
	//postionId:在指定控件下面查找
	//electedId:待选项控件
	init:function(postionId,electedId){
		var _self = this;
		var controlId = '';
		$('#'+postionId).find('select').bind('change',function(){
			controlId = _self.getControlId($(this).attr('id'),$(this).val()>0?1:0);
			if(controlId){
				//防止下拉选项还未读取到值
				var timer = window.setInterval(function(){
					var jObj = $('#'+controlId).find('option:gt(0)');
					if(jObj.length>0){
						$('#'+electedId).html(jObj.clone());
						$('#bootstrap-duallistbox-nonselected-list_'+electedId).html(jObj.clone());
						clearInterval(timer);
					}else{
						$('#'+electedId).html('');
					}
				},300);
			}
		});
	},
	//根据当前选择控件确定取数控件id
	//step：0，返回当前控件  1，取下一个控件
	getControlId:function(id,step){
		var controlId=id;
		if(step==1){
			if(id=='provinceid'){
				controlId='areaid';
			}else if(id=='areaid'){
				controlId='townid';
			}else if(id=='townid'){
				controlId='schoolid';
			}
		}
		return controlId;		
	},
	//页面操作行为，添加或删除项
	//flag: 1：add；-1：remove
	//electedId:待选区
	//selectedId:已选区
	addRegion: function(flag,electedId,selectedId){
		var _self = this;
		var choice_item = '';//选择项
		var process_item = '';//处理项
		if(flag==1){
			choice_item = $('#'+electedId+' option:selected');
			process_item = $('#'+selectedId);
		}else if(flag==-1){
			choice_item = $('#'+selectedId+' option:selected');
			process_item = $('#'+electedId);
		}
		var len = choice_item.length;
		if(len>0){
			choice_item.each(function(i,o){
				//当前选择项
				var curr_item = $(o).clone();
				if(flag==1){//添加新项
					var nodeVal = '';//选择项的value值
					var nodeTxt = '';//选择项的文本值
					var arr = ["provinceid","areaid","townid","schoolid"];
					for(var i=0; i<arr.length; i++){
						var curSelect = $('#'+arr[i]+' option:selected');  	
						var sval = curSelect.val()==0||curSelect.val()==''||curSelect.val()==undefined;
						if(sval){//当前节点未选中，则退出
							break;
						}else{
							nodeVal += curSelect.val() + '_'; 
							nodeTxt += curSelect.text() + '->';
						}
					}
					nodeVal += $(o).val();
					nodeTxt += $(o).text();
					//防重复添加相同数据
					if(process_item.find('option[value="'+nodeVal+'"]').length==0){
						curr_item.val(nodeVal);
						curr_item.text(nodeTxt);
						process_item.append(curr_item);
					}
				}else if(flag==-1){
					$(o).remove();
				}
			});
		}else{
			alert("请先选择目标项");
		}
	}
};

var regionUtil = null;
$(document).ready(function(){
	regionUtil = new RegionUtil();
});