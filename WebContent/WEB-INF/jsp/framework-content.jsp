<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
</head>
<body>
	<div class="right-product right-full">
		<div class="container-fluid">
			<div class="info-center">
				<div class="page-header">
					<div class="pull-left">
						<h4>检修结构</h4>
					</div>
				</div>
				<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
				<div id="main" style="width: 100%;postion:relative;"></div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
   var doucmentHeight = $(document).height() - 107;
   $("#main").height(doucmentHeight);

	// 基于准备好的dom，初始化echarts实例
	var myChart = echarts.init(document.getElementById('main'));

	myChart.showLoading();

    myChart.setOption(option = {
        tooltip: {
            trigger: 'item',
            triggerOn: 'mousemove'
        },
        toolbox: {
            show : true,
            feature : {
                mark : {show: true},
                dataView : {show: true, readOnly: false},
                restore : {show: true},
                saveAsImage : {show: true}
            }
        },
        
        calculable : false,
        
        series:[
            {
                type: 'tree',

                data: [${json}],

                left: '2%',
                right: '2%',
                top: '8%',
                bottom: '20%',
                
                // 结点为空正方形
                symbol: 'emptyRect',
                
                // 垂直方向
                orient: 'vertical',
                
                // false为不可折叠，true为可折叠
                expandAndCollapse: false,

                label: {
                    normal: {
                        position: 'top',
                        rotate: -90,
                        verticalAlign: 'middle',
                        align: 'right',
                        fontSize: 9
                    }
                },

                leaves: {
                    label: {
                        normal: {
                            position: 'bottom',
                            rotate: -90,
                            verticalAlign: 'middle',
                            align: 'left'
                        }
                    }
                },

                animationDurationUpdate: 750
            }
        ]
    });
    
    myChart.hideLoading();
    
    myChart.on('click', function (params) {
    	console.log("block-id为：" + params.data.value + "    block-name为：" + params.data.name);
    	var e = event || window.event;
    	console.log( "x:" + e.clientX + '    y:' + e.clientY );
    	remind_start(params.data.value,params.data.name,e.clientX,e.clientY);
    });
	
</script>
</html>