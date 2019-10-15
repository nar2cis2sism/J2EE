Date.prototype.format = function (format) {//日期格式转换
    var o = {
        "M+": this.getMonth() + 1, //month
        "d+": this.getDate(), //day
        "h+": this.getHours(), //hour
        "m+": this.getMinutes(), //minute
        "s+": this.getSeconds(), //second
        "q+": Math.floor((this.getMonth() + 3) / 3), //quarter
        "S": this.getMilliseconds() //millisecond
    }
    if (/(y+)/.test(format)) format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o) if (new RegExp("(" + k + ")").test(format))format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));
    return format;
}

String.prototype.format = function(args) {
    var result = this;
    for (var i = 0; i < arguments.length; i++)
    {
    	if (arguments[i] != undefined)
    	{
    		var reg = new RegExp("({)" + i + "(})", "g");
            result = result.replace(reg, arguments[i]);
    	}
    }
    
    return result;
}

function getRequest(action, url, callback) {
	var time = new Date().getTime();
	console.log(action + "请求", url);
	$.ajax({
		url: url,
		type: "get",
        contentType: "application/json",
        success: function (data) {
            console.log("{0}响应-{1}s".format(action, (new Date().getTime() - time) / 1000), data);
            callback(true, data);
        },
        error:function (e) {
            console.log("{0}响应-{1}s".format(action, (new Date().getTime() - time) / 1000), e);
            callback(false, e);
        }
	});
}

function newArray(length) {
	var array = [];
	for (var i = 0; i < length; i++)
	{
		array.push(0);
	}
	
	return array;
}

function getParams(url) {
    var query = {}, i, params, param;
    if ((i = url.indexOf("?")) < 0)
    {
    	return query;
    }
    else
    {
    	url = url.substring(i + 1);
    }
    
    params = url.split("&");
    for (i = 0; i < params.length; i++)
    {
    	param = params[i].split("=");
    	query[param[0]] = param[1];
    }
    
    return query;
}

///////////////////////////////////////////////////////////////////////

function getWeek(date) {
	var week;
	switch (date.getDay()) {
		case 1:
            week = "星期一";
			break
        case 2:
            week = "星期二";
            break;
        case 3:
            week = "星期三";
            break;
        case 4:
            week = "星期四";
            break;
        case 5:
            week = "星期五";
            break;
        case 6:
            week = "星期六";
            break;
        default :
            week = "星期日";
            break;
	}
	
	return week;
}

function handleChartData(data, startTime) {
	var length = 24 * 6 + 1;
	var total = newArray(length);
	var project = newArray(length)
	var mcr = newArray(length);
	var kpi = newArray(length);
	var todo = newArray(length);
	var totalMax = 0;
	var projectMax = 0;
	var mcrMax = 0;
	var kpiMax = 0;
	var todoMax = 0;
	
	if (data)
	{
		var startTimeInMillis = new Date(startTime).getTime();
		for (var i = 0; i < data.length; i++)
      	{
      		var item = data[i];
      		if (item.queryTime)
      		{
      			var time = new Date(item.queryTime).getTime() - startTimeInMillis;
      			var index = parseInt(time / 1000 / 60 / 10) + 1;
      			if (0 < index < total.length)
      			{
      				if (item.moduleCode == "all")
      				{
      					total[index] = item.num;
      					totalMax = Math.max(totalMax, item.num);
      				}
      				else if (item.moduleCode == "project")
      				{
      					project[index] = item.num;
      					projectMax = Math.max(projectMax, item.num);
      				}
      				else if (item.moduleCode == "mcr")
      				{
      					mcr[index] = item.num;
      					mcrMax = Math.max(mcrMax, item.num);
      				}
      				else if (item.moduleCode == "kpi")
      				{
      					kpi[index] = item.num;
      					kpiMax = Math.max(kpiMax, item.num);
      				}
      				else if (item.moduleCode == "todo")
      				{
      					todo[index] = item.num;
      					todoMax = Math.max(todoMax, item.num);
      				}
      			}
      		}
      	}
	}
	
	return {
		"total": total,
		"project": project,
		"mcr": mcr,
		"kpi": kpi,
		"todo": todo,
		"totalMax": totalMax,
		"projectMax": projectMax,
		"mcrMax": mcrMax,
		"kpiMax": kpiMax,
		"todoMax": todoMax,
		"totalColor": "#FCC14D",
		"projectColor": "#4FB9ED",
		"mcrColor": "#4DD7B6",
		"kpiColor": "#2882B9",
		"todoColor": "#CEA1CC",
	};
}

/**
 * 绘制线性图表
 */
function setupLineChart(params) {
	var labels = // X轴显示文本
	[
		"00", "01", "02", "03", "04", "05", "06", "07", 
		"08", "09", "10", "11", "12", "13", "14", "15", 
		"16", "17", "18", "19", "20", "21", "22", "23", "24"
	];
	var data = // 线条绘制
	[
		{
			name: 'iSales+', // 线条名称
			value: params.total, // X坐标对应的Y值
			color: params.totalColor, // 线条颜色
			line_width: 2 // 线条宽度
		},
		{
			name: '销售项目运作',
			value: params.project,
			color: params.projectColor,
			line_width: 2
		},
		{
			name: '客户关系',
			value: params.mcr,
			color: params.mcrColor,
			line_width: 2
		},
//		{
//			name: '经营',
//			value: params.kpi,
//			color: params.kpiColor,
//			line_width: 2
//		},
		{
			name: '智能推荐',
			value: params.todo,
			color: params.todoColor,
			line_width: 2
		}
	];
	
	var chart = new iChart.LineBasic2D({
		render: params.element_id, // 绘制在哪个元素上
		width: params.width,
		height: params.height,
		background_color: null, // 背景颜色
        border: // 边框
        {
            enable: false,
            width: 2, // 边框宽度
            color: '#1D1D54', //边框颜色
            radius: "0 0 40px 40px" // 边框圆角
        },
//		title: // 标题
//		{
//			text: params.title,
//			fontsize: 24,
//			color: '#f7f7f7'
//		},
		coordinate: // 坐标轴
		{
			width: params.coordinate_width,
			height: params.coordinate_height,
			axis: // 轴线边框
			{
				color: 'rgba(255,255,255,0.1)',
				width: [0, 0, 1, 1]
			},
//			grids: // 矩形网格
//			{
//				horizontal:
//				{
//					way: 'share_alike',
//					value: 8 // 水平方向网格数
//				},
//				vertical:
//				{
//					value: 1 // 垂直方向网格数
//				}
//			},
			grid_color : 'rgba(255,255,255,0.1)', // 网格线框颜色
//			gridVStyle: // 两边的垂直轴线
//			{
//				color :'#383e46'
//			},
			scale: // 段落设置
			[
				{
					position: 'left', // 显示位置
					label: // 显示文本
					{
						color: '#6E6EA5',
						font: 'SourceHanSansSC-Regular',
						fontsize: params.scale_fontsize
					},
//					start_scale: 0.7, // 最小值
//					end_scale: 1.5, // 最大值
//					scale_space: 0.1, // 间隔差值
					scale_enable: false, // Y轴上的色块
					scale_size: 2, // 色块长度
					scale_color: '#9f9f9f' // 色块颜色
				},
				{
					position: 'bottom',
					label: // 显示文本
					{
						color: '#6E6EA5',
						font: 'SourceHanSansSC-Regular',
						fontsize: params.scale_fontsize
					},
					labels: labels
				}
			]
		},
		data: data, // 图表数据
		legend: // 线条说明
		{
			enable: true, // 开关打开有默认效果
			background_color: null, // 背景色
//			align: 'center', // 水平位置
			valign: 'top', // 垂直位置
			offsetx: -52, // 水平偏移
			offsety: 0, // 垂直偏移
			row: 1, // 排列行数（默认为线条数量）
			column: 'max', // 默认为1
			legend_space: 20, // item间距
			color: '#6E6EA5', // 文字颜色
			fontsize: 18, // 文字大小
			font: 'SourceHanSansSC-Regular',
			sign: 'round', // 色块形状
			sign_size: 8, // 色块大小
			border: // 边框
			{
				enable: false
			}
		},
		tip: // 鼠标经过提示
		{
			enable: true,
//			listeners:
//			{
//				parseText: function (tip, name, value, text, i) {
//					var color;
//					for (var i = 0; i < data.length; i++)
//					{
//						var item = data[i];
//						if (item.name == name)
//						{
//							color = item.color;
//							break;
//						}
//					}
//					
//					var tipHtml = 
//					('<div class="flexColumnCenter">'+
//						'<span class="max_number" style="color: {2};">{0}</span>'+
//						'<span class="max_name" style="color: {2};">{1}</span>'+
//					'</div>').format(value, name, color);
//					return tipHtml;
//				}
//			}
		},
		crosshair: // 鼠标经过Y轴显示
		{
			enable: true,
			line_color: '#DDDDDD'
		},
		align: 'center',
		shadow: false, // 线条阴影
		shadow_color: '#20262f',
		shadow_blur: 4,
		shadow_offsetx: 0,
		shadow_offsety: 2,
		sub_option: // 段落圆点
		{
			label: false, // 数值显示开关
			point_size: 0, // 圆点大小
			hollow: false, // 实心圆点
			hollow_inside: false // 圆点颜色取线条颜色
		},
		listeners:
		{
            /**
             * d:相当于data[j],即一个线段的对象
             * v:相当于data[j].value
             * x:计算出来的横坐标
             * x:计算出来的纵坐标
             * j:序号,从0开始
             */
            parsePoint: function(d,v,x,y,j) {
            	// 无数据不显示
            	if (v == 0)
            	{
            		return {ignored:true} // 表示忽略该点
            	}
            }
		}
	});
//	// 自定义绘制
//	chart.plugin(new iChart.Custom({
//		drawFn: function() {
//			// 获取坐标轴区域
//			var coo = chart.getCoordinate(),
//			x = coo.x,
//			y = coo.y,
//			w = coo.width,
//			h = coo.height;
//			// 左上角绘制标题
//			chart.target
//			.textFont('32px SourceHanSansSC-Medium')
//			.textAlign('start')
//			.textBaseline('bottom')
//			.fillText(params.title, x - 40, y - 20, false, '#FFFFFF');
//		}
//	}));
	// 开始画图
	chart.draw();
}